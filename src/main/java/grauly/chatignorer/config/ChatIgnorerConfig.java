package grauly.chatignorer.config;

import grauly.chatignorer.ChatIgnorer;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

import java.util.ArrayList;

@Config(name = ChatIgnorer.MODID)
public class ChatIgnorerConfig implements ConfigData {
    public ArrayList<String> ignoredPlayer = new ArrayList<>();
    public ArrayList<String> ignoredPatterns = new ArrayList<>();
}
