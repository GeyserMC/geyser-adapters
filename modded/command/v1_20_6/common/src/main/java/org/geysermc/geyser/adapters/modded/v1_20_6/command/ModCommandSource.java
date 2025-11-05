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

package org.geysermc.geyser.adapters.modded.v1_20_6.command;

import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.resources.RegistryOps;
import net.minecraft.server.level.ServerPlayer;
import org.geysermc.geyser.adapters.command.CommandSenderDefinition;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;
import java.util.function.Consumer;

public class ModCommandSource implements CommandSenderDefinition {
    private final CommandSourceStack sender;
    private final Consumer<String> logger;

    public ModCommandSource(
            CommandSourceStack sender,
            Consumer<String> logger
    ) {
        this.sender = sender; // TODO find locale?
        this.logger = logger;
    }

    @Override
    public String name() {
        return sender.getTextName();
    }

    @Override
    public void sendMessage(String message) {
        if (sender.getEntity() instanceof ServerPlayer player) {
            player.sendSystemMessage(net.minecraft.network.chat.Component.literal(message), false);
        } else {
            logger.accept(message);
        }
    }

    @Override
    public void sendMessage(Component message, Consumer<Component> superDefinition) {
        if (sender.getEntity() instanceof ServerPlayer player) {
            JsonElement jsonComponent = GsonComponentSerializer.gson().serializeToTree(message);
            player.sendSystemMessage(ComponentSerialization.CODEC.parse(
                    RegistryOps.create(JsonOps.INSTANCE, player.registryAccess()), jsonComponent
            ).getOrThrow(), false);
            return;
        }
        superDefinition.accept(message);
    }

    @Override
    public boolean isConsole() {
        return !(sender.getEntity() instanceof ServerPlayer);
    }

    @Override
    public @Nullable UUID playerUuid() {
        if (sender.getEntity() instanceof ServerPlayer player) {
            return player.getUUID();
        }
        return null;
    }

    @Override
    public Object handle() {
        return sender;
    }
}
