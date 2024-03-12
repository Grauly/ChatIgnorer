package grauly.chatignorer.commands;

import com.mojang.brigadier.CommandDispatcher;
import grauly.chatignorer.config.ChatIgnorerConfig;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.text.Text;

public class ListIgnoredCommand implements ClientCommandRegistrationCallback {

    @Override
    public void register(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandRegistryAccess registryAccess) {
        dispatcher.register(ClientCommandManager.literal("ignorelist").executes(ctx -> {
            AutoConfig.getConfigHolder(ChatIgnorerConfig.class).getConfig().ignoredPlayers.forEach(p -> ctx.getSource().sendFeedback(Text.of(p)));
            return 1;
        }));
    }
}
