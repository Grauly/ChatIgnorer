package grauly.chatignorer.events;

import com.mojang.authlib.GameProfile;
import grauly.chatignorer.ChatIgnorer;
import grauly.chatignorer.config.ChatIgnorerConfig;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.minecraft.network.message.MessageType;
import net.minecraft.network.message.SignedMessage;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.time.Instant;

public class ChatReceiveEvent implements ClientReceiveMessageEvents.AllowChat {
    protected String fullRegex;

    @Override
    public boolean allowReceiveChatMessage(Text message, @Nullable SignedMessage signedMessage, @Nullable GameProfile sender, MessageType.Parameters params, Instant receptionTimestamp) {
        return !message.toString().matches(getRegex());
    }

    protected void rebuildRegex() {
        ChatIgnorerConfig config = AutoConfig.getConfigHolder(ChatIgnorerConfig.class).getConfig();
        StringBuilder builder = new StringBuilder();
        config.ignoredPlayers.forEach(p -> {
            builder.append("\\[.*\\]").append(p).append(".*>.*|");
        });
        config.ignoredPatterns.forEach(p -> {
            builder.append(p).append("|");
        });
        builder.reverse().deleteCharAt(0).reverse();
        fullRegex = builder.toString();
    }

    protected String getRegex() {
        if(ChatIgnorer.REGEX_NEEDS_REBUILD) rebuildRegex();
        return fullRegex;
    }
}
