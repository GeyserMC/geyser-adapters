/*
 * Copyright (c) 2019-2020 GeyserMC. http://geysermc.org
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

package org.geysermc.adapters.spigot.v1_16_R1;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.server.v1_16_R1.Block;
import net.minecraft.server.v1_16_R1.Chunk;
import net.minecraft.server.v1_16_R1.ChunkSection;
import net.minecraft.server.v1_16_R1.IBlockData;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_16_R1.CraftWorld;
import org.geysermc.adapters.spigot.SpigotWorldAdapter;

public class WorldAdapter_v1_16_R1 extends SpigotWorldAdapter {
    @Override
    public int getBlockAt(World world, int x, int y, int z) {
        Chunk chunk = ((CraftWorld) world).getHandle().getChunkIfLoaded(x >> 4, z >> 4);
        if (chunk == null) { // should never happen but just to be on the safe side
            return 0;
        }
        if (y >= 0 && y >> 4 < chunk.getSections().length) {
            ChunkSection section = chunk.getSections()[y >> 4];
            if (!ChunkSection.a(section)) { // is chunk empty
                return Block.getCombinedId(section.getType(x & 15, y & 15, z & 15));
            }
        }
        return 0;
    }

    @Override
    public IntList getAllBlockStates() {
        IntList blockStates = new IntArrayList();
        for (IBlockData block : Block.REGISTRY_ID) {
            blockStates.add(Block.getCombinedId(block));
        }
        return blockStates;
    }
}
