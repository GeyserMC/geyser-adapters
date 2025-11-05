/*
 * Copyright (c) 2019-2025 GeyserMC. http://geysermc.org
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

package org.geysermc.geyser.adapters.modded.v1_21_5;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.level.ServerPlayer;
import org.geysermc.geyser.adapters.CommandManagerAdapter;
import org.geysermc.geyser.adapters.command.CommandSenderDefinition;
import org.geysermc.geyser.adapters.modded.v1_21_5.command.ModCommandSource;
import org.incendo.cloud.CommandManager;
import org.incendo.cloud.SenderMapper;

import java.util.function.Consumer;
import java.util.function.Function;

public abstract class CommandManagerAdapter_v1_21_5 extends CommandManagerAdapter<CommandSourceStack, ServerPlayer> {
    @Override
    public <T> CommandManager<T> getCommandManager(
            Function<CommandSenderDefinition, T> converter,
            CommandSourceConverter<T, CommandSourceStack, ServerPlayer> sourceConverter,
            Consumer<String> logger
    ) {
        SenderMapper<CommandSourceStack, T> mapper = sourceConverter.getSenderMapper(
                CommandSourceStack.class,
                id -> GeyserAdapter_v1_21_5.getServer().getPlayerList().getPlayer(id),
                ServerPlayer::createCommandSourceStack,
                () -> GeyserAdapter_v1_21_5.getServer().createCommandSourceStack(),
                commandSourceStack -> converter.apply(new ModCommandSource(
                        commandSourceStack,
                        logger
                ))
        );

        return getCommandManager(mapper);
    }

    @Override
    public CommandSenderDefinition getCommandSenderDefinition(ServerPlayer handle, Consumer<String> logger) {
        return new ModCommandSource(handle.createCommandSourceStack(), logger);
    }

    public abstract <T> CommandManager<T> getCommandManager(SenderMapper<CommandSourceStack, T> mapper);
}
