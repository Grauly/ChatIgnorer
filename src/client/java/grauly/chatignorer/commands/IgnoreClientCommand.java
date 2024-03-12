package grauly.chatignorer.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import grauly.chatignorer.config.ChatIgnorerConfig;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.text.Text;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;


public class IgnoreClientCommand implements ClientCommandRegistrationCallback {
    private static final LiteralArgumentBuilder<FabricClientCommandSource> BASE_NODE = literal("ignore").executes(context -> {
        context.getSource().sendError(Text.translatable("text.chatignorer.noargs"));
        return 1;
    });

    private static final RequiredArgumentBuilder<FabricClientCommandSource, String> PLAYER_SELECTOR_NODE = argument("player", new PlayerNameArgumentType()).executes(context -> {
        AutoConfig.getConfigHolder(ChatIgnorerConfig.class).getConfig().ignoredPlayers.add(context.getArgument("player", String.class));
        return 1;
    });

    @Override
    public void register(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandRegistryAccess registryAccess) {
        dispatcher.register(BASE_NODE.then(PLAYER_SELECTOR_NODE));
    }
}
