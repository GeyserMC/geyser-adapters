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

package org.geysermc.geyser.adapters.paper;

import java.util.Arrays;

public final class PaperAdapters {
    private static PaperWorldAdapter worldAdapter;

    private static final int[] protocols = new int[] {766, 768};

    public static void registerClosestWorldAdapter(int version) throws Exception {
        if (version < protocols[0]) {
            throw new IllegalArgumentException("Java versions pre-1.20.5 should use the Spigot mapped SpigotAdapters!");
        }

        if (Arrays.stream(protocols).anyMatch(value -> version == value)) {
            registerWorldAdapter(version); // Found exact match!
            return;
        }

        int closest = Arrays.stream(protocols)
                .filter(v -> v <= version)
                .max()
                .orElseThrow(() -> new IllegalArgumentException("No compatible protocol found for " + version + "!"));

        registerWorldAdapter(closest);
    }

    public static void registerWorldAdapter(int version) throws Exception {
        // This way we can have classes loaded on later Java versions.
        Class<?> adapterVersion = Class.forName("org.geysermc.geyser.adapters.paper.v" + version + ".WorldAdapter_v" + version);
        worldAdapter = (PaperWorldAdapter) adapterVersion.getConstructor().newInstance();
    }

    public static PaperWorldAdapter getWorldAdapter() {
        return worldAdapter;
    }

    private PaperAdapters() {
    }
}
