package org.geysermc.adapters;

import org.geysermc.common.PlatformType;

import java.util.HashMap;
import java.util.Map;

public class GeyserAdapters {
    private static final Map<PlatformType, WorldAdapter> WORLD_ADAPTERS = new HashMap<>();

    public static WorldAdapter getWorldAdapter(PlatformType type, String version) {
        WorldAdapter adapter = WORLD_ADAPTERS.get(type);
        if (adapter != null) {
            return adapter;
        }
        try {
            Class<?> worldAdapterClass = Class.forName(String.format("org.geysermc.adapters.%s.%s.WorldAdapter_%s", type.getPlatformName().toLowerCase(), version, version));
            adapter = (WorldAdapter) worldAdapterClass.newInstance();
            WORLD_ADAPTERS.put(type, adapter);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            return null;
        }
        return adapter;
    }
}
