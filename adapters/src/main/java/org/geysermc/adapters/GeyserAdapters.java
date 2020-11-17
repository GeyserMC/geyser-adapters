package org.geysermc.adapters;

import org.geysermc.common.PlatformType;

public class GeyserAdapters {
    private static WorldAdapter WORLD_ADAPTER;

    public static void registerWorldAdapter(PlatformType type, String version) {
        if (WORLD_ADAPTER != null) {
            throw new RuntimeException("A WorldAdapter instance has already been registered!");
        }
        try {
            Class<?> worldAdapterClass = Class.forName(String.format("org.geysermc.adapters.%s.%s.WorldAdapter_%s", type.getPlatformName().toLowerCase(), version, version));
            WORLD_ADAPTER = (WorldAdapter) worldAdapterClass.newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(String.format("A WorldAdapter for version %s could not be found for platform %s!", version, type.getPlatformName()), e);
        }
    }

    public static WorldAdapter getWorldAdapter() {
        return WORLD_ADAPTER;
    }
}
