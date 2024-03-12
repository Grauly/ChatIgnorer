package grauly.chatignorer.config;

import grauly.chatignorer.ChatIgnorer;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@Config(name = ChatIgnorer.MODID)
public class ChatIgnorerConfig implements ConfigData {
    public ArrayList<String> ignoredPlayers = new ArrayList<>();
    public ArrayList<String> ignoredPatterns = new ArrayList<>();

    @Override
    public void validatePostLoad() throws ValidationException {
        ChatIgnorer.REGEX_NEEDS_REBUILD = true;
        ChatIgnorer.LOGGER.info("Config changed, validating");
        ArrayList<String> removals = new ArrayList<>();
        for (String ignoredPattern : ignoredPatterns) {
            try {
                Pattern.compile(ignoredPattern);
            } catch (PatternSyntaxException e) {
                ChatIgnorer.LOGGER.info("Could not compile pattern {} deleting pattern.", ignoredPattern);
                removals.add(ignoredPattern);
            }
        }
        ignoredPatterns.removeAll(removals);
    }
}
