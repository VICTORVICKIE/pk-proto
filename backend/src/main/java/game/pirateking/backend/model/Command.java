package game.pirateking.backend.model;

public class Command {

    public enum ACTION {
        JOIN("JOIN"),
        HOST("HOST"),
        ROLL("ROLL");
        
        ACTION(String value) {
        }
    }

    public Command(String[] command) {
        version = Integer.valueOf(command[0]);
        playerId = command[1];
        action = ACTION.valueOf(command[2]);

        if(action == ACTION.JOIN){
            gameId = command[3];
        }
    }

    private Integer version;
    private String playerId;
    private ACTION action;
    private String gameId;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public ACTION getAction() {
        return action;
    }

    public void setAction(ACTION action) {
        this.action = action;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }
}
