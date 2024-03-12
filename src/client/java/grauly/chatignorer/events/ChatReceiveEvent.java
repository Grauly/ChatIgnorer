package grauly.chatignorer.events;

import grauly.chatignorer.ChatIgnorer;
import grauly.chatignorer.config.ChatIgnorerConfig;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.minecraft.text.Text;

public class ChatReceiveEvent implements ClientReceiveMessageEvents.AllowGame {
    protected String fullRegex;

    @Override
    public boolean allowReceiveGameMessage(Text message, boolean overlay) {
        ChatIgnorer.LOGGER.info(message.getString());
        return !message.getString().matches(getRegex());
    }

    protected void rebuildRegex() {
        ChatIgnorerConfig config = AutoConfig.getConfigHolder(ChatIgnorerConfig.class).getConfig();
        StringBuilder builder = new StringBuilder();
        config.ignoredPlayers.forEach(p -> {
            builder.append("((§.){0,2}\\[.*\\]|(§.){0,2}From\\>\\>|(§.){0,2}GROUP\\:) ");
            for (char c : p.toCharArray()) {
                builder.append("((§.)*").append(c).append(")");
            }
            builder.append("(>|>>|:) .*|");
        });
        config.ignoredPatterns.forEach(p -> {
            builder.append(p).append("|");
        });
        builder.reverse().deleteCharAt(0).reverse();
        fullRegex = builder.toString();
        ChatIgnorer.LOGGER.info(fullRegex);
        ChatIgnorer.REGEX_NEEDS_REBUILD = false;
    }

    protected String getRegex() {
        if (ChatIgnorer.REGEX_NEEDS_REBUILD) rebuildRegex();
        return fullRegex;
    }
}
