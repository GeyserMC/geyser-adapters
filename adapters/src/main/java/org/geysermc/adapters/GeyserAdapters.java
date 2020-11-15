package org.geysermc.adapters;

import org.geysermc.common.PlatformType;

import java.util.HashMap;
import java.util.Map;

public class GeyserAdapters {
    private static final Map<PlatformType, WorldAdapter> WORLD_ADAPTERS = new HashMap<>();

    public static void registerWorldAdapter(PlatformType type, String version) {
        try {
            Class<?> worldAdapterClass = Class.forName(String.format("org.geysermc.adapters.%s.%s.WorldAdapter_%s", type.getPlatformName().toLowerCase(), version, version));
            WORLD_ADAPTERS.put(type, (WorldAdapter) worldAdapterClass.newInstance());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(String.format("Failed to load WorldAdapter version %s for %s platform", version, type.getPlatformName()), e);
        }
    }

    public static WorldAdapter getWorldAdapter(PlatformType type) {
        return WORLD_ADAPTERS.get(type);
    }
}
