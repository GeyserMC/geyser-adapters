/*
 * Copyright (c) 2019-2021 GeyserMC. http://geysermc.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 * @author GeyserMC
 * @link https://github.com/GeyserMC/Geyser
 */

package org.geysermc.geyser.adapters.spigot;

import org.geysermc.geyser.adapters.spigot.v1_15_R1.WorldAdapter_v1_15_R1;
import org.geysermc.geyser.adapters.spigot.v1_16_R3.WorldAdapter_v1_16_R3;
import org.geysermc.geyser.adapters.spigot.v1_17_R1.WorldAdapter_v1_17_R1;
import org.geysermc.geyser.adapters.spigot.v1_18_R1.WorldAdapter_v1_18_R1;
import org.geysermc.geyser.adapters.spigot.v1_18_R1.WorldAdapter_v1_18_R2;
import org.geysermc.geyser.adapters.spigot.v1_19_R1.WorldAdapter_v1_19_R1;
import org.geysermc.geyser.adapters.spigot.v1_19_R3.WorldAdapter_v1_19_R3;

import java.util.Locale;
import java.util.function.Supplier;

public enum SpigotWorldAdapterVersion {
    V1_15_R1(WorldAdapter_v1_15_R1::new),
    V1_16_R3(WorldAdapter_v1_16_R3::new),
    V1_17_R1(WorldAdapter_v1_17_R1::new),
    V1_18_R1(WorldAdapter_v1_18_R1::new),
    V1_18_R2(WorldAdapter_v1_18_R2::new),
    V1_19_R1(WorldAdapter_v1_19_R1::new),
    V1_19_R2(WorldAdapter_v1_19_R3::new);

    private final Supplier<SpigotWorldAdapter> worldAdapterSupplier;

    SpigotWorldAdapterVersion(Supplier<SpigotWorldAdapter> worldAdapterSupplier) {
        this.worldAdapterSupplier = worldAdapterSupplier;
    }

    public static SpigotWorldAdapterVersion getByName(String versionName) {
        versionName = versionName.toUpperCase(Locale.ROOT);
        for (SpigotWorldAdapterVersion version : values()) {
            if (version.name().equals(versionName)) {
                return version;
            }
        }
        return null;
    }

    public SpigotWorldAdapter createAdapter() {
        return worldAdapterSupplier.get();
    }
}
