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

package org.geysermc.geyser.adapters;

import org.geysermc.geyser.adapters.command.CommandSenderDefinition;
import org.incendo.cloud.CommandManager;
import org.incendo.cloud.SenderMapper;

import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class CommandManagerAdapter<S, P> {
    public abstract <T> CommandManager<T> getCommandManager(
            Function<CommandSenderDefinition, T> converter,
            CommandSourceConverter<T, S, P> sourceConverter,
            Consumer<String> logger
    );

    public abstract CommandSenderDefinition getCommandSenderDefinition(P handle, Consumer<String> logger);

    public interface CommandSourceConverter<T, S, P> {
        SenderMapper<S, T> getSenderMapper(
                Class<S> senderClass,
                Function<UUID, P> playerLookup,
                Function<P, S> senderLookup,
                Supplier<S> consoleLookup,
                Function<S, T> commandSourceLookup
        );
    }
}
