package grauly.chatignorer;

import grauly.chatignorer.commands.IgnoreClientCommand;
import grauly.chatignorer.commands.ListIgnoredCommand;
import grauly.chatignorer.events.ChatReceiveEvent;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;

public class ChatIgnorerClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ClientReceiveMessageEvents.ALLOW_GAME.register(new ChatReceiveEvent());
		ClientCommandRegistrationCallback.EVENT.register(new IgnoreClientCommand());
		ClientCommandRegistrationCallback.EVENT.register(new ListIgnoredCommand());
	}
}