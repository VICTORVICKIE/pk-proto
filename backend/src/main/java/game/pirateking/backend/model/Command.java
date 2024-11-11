package game.pirateking.backend.model;

import java.util.ArrayList;
import java.util.List;

public class Command {

    public enum ACTION {
        HOST("HOST"),
        JOIN("JOIN"),
        ROLL("ROLL");
        
        ACTION(String value) {
        }
    }

    private final Integer version;
    private final String playerId;
    private final ACTION action;
    private final List<String> args;

    public Command(String c) {
        List<String> command = decode(c);
        version = Integer.valueOf(command.removeFirst());
        playerId = command.removeFirst();
        action = ACTION.valueOf(command.removeFirst());
        args = command;
    }

    public String encode(List<String> strs) {
        StringBuilder encoded = new StringBuilder();
        for (String str : strs) {
            encoded.append(str.length()).append("#").append(str);
        }
        return encoded.toString();
    }

    public List<String> decode(String str) {
        var decoded = new ArrayList<String>();
        int index = 0;
        while (index < str.length()) {
            int lengthIndex = index;
            while (str.charAt(lengthIndex) != '#') {
                lengthIndex += 1;
            }
            int length = Integer.parseInt(str.substring(index, lengthIndex));
            index = lengthIndex + length + 1;
            decoded.add(str.substring(lengthIndex + 1, index));
        }
        return decoded;
    }
    public Integer getVersion() {
        return version;
    }

    public String getPlayerId() {
        return playerId;
    }

    public ACTION getAction() {
        return action;
    }
}
