package game.pirateking.backend;

import java.util.HashSet;

public class GameSession {
	public HashSet<Player> players;

	public GameSession() {
		this.players = new HashSet<>();
	}

	public int rollDice() {
		return 3;
	}
}
