package game.pirateking.backend.game;

import game.pirateking.backend.Player;

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
