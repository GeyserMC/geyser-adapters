/*
 * Copyright (c) 2019-2023 GeyserMC. http://geysermc.org
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

package org.geysermc.geyser.adapters.spigot.v1_20_R3;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.chunk.LevelChunkSection;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_20_R3.CraftWorld;
import org.geysermc.geyser.adapters.spigot.SpigotWorldAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class WorldAdapter_v1_20_R3 extends SpigotWorldAdapter {
    @Override
    public int getBlockAt(World world, int x, int y, int z) {
        if (y < world.getMinHeight()) {
            return 0;
        }

        LevelChunk chunk = ((CraftWorld) world).getHandle().getChunkIfLoaded(x >> 4, z >> 4);
        if (chunk == null) { // should never happen but just to be on the safe side
            return 0;
        }
        int worldOffset = world.getMinHeight() >> 4;
        int chunkOffset = (y >> 4) - worldOffset;
        if (chunkOffset < chunk.getSections().length) {
            LevelChunkSection section = chunk.getSections()[chunkOffset];
            if (section != null && !section.hasOnlyAir()) {
                return Block.getId(section.getBlockState(x & 15, y & 15, z & 15));
            }
        }
        return 0;
    }

    @Override
    public IntList getAllBlockStates() {
        IntList blockStates = new IntArrayList();
        for (BlockState block : Block.BLOCK_STATE_REGISTRY) {
            blockStates.add(Block.getId(block));
        }
        return blockStates;
    }

    @Override
    public String[] getBiomeSuggestions(boolean tags) {
        Registry<Biome> registry = MinecraftServer.getServer()
            .registryAccess()
            .registry(Registries.BIOME).orElseThrow();
        if (!tags) {
            return getBiomes(registry).toArray(String[]::new);
        }

        List<String> keys = new ArrayList<>(registry.getTagNames().map(tag -> "#" + tag.location()).toList());
        keys.addAll(getBiomes(registry).toList());
        return keys.toArray(new String[0]);
    }

    private Stream<String> getBiomes(Registry<Biome> registry) {
        return registry.keySet().stream().map(ResourceLocation::toString);
    }
}
