package grauly.chatignorer;

import grauly.chatignorer.events.ChatReceiveEvent;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;

public class ChatIgnorerClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ClientReceiveMessageEvents.ALLOW_GAME.register(new ChatReceiveEvent());
	}
}