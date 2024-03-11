package grauly.chatignorer;

import grauly.chatignorer.config.ChatIgnorerConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChatIgnorer implements ModInitializer {
	public static final String MODID = "chatignorer";
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	@Override
	public void onInitialize() {
		AutoConfig.register(ChatIgnorerConfig.class, GsonConfigSerializer::new);
	}
}