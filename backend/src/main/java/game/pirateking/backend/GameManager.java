package game.pirateking.backend;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.UUID;

@Service
public class GameManager {
//	static GameManager instance = null;
	// gameId -> GameSession
	public HashMap<String, GameSession> games;

	public GameManager() {
		this.games = new HashMap<>();
	}

//	public static GameManager getInstance() {
//		if (instance == null) {
//			instance = new GameManager();
//		}
//		return instance;
//	}

	public String createGame() {
		String gameId = UUID.randomUUID().toString();
		this.games.put(gameId, new GameSession());
		return gameId;
	}

	public String initGame(String host, WebSocketSession session) {
		String gameId = createGame();
		addPlayer(gameId, host, session);
		System.out.printf("[Manager] - game size = %d\n", this.games.size());
		for (String game : this.games.keySet()) {
			System.out.printf("[Manager] - game %s contains %d players\n", game, this.games.get(game).players.size());
		}
		return gameId;
	}

	private Player createPlayer(String name, WebSocketSession session) {
		return new Player(session.getId(), name, session);
	}
	public void addPlayer(String gameId, String name, WebSocketSession session) {
		Player player = createPlayer(name, session);
		this.games.get(gameId).players.add(player);
		System.out.printf("[Manager] - game size = %d\n", this.games.get(gameId).players.size());
	}
}
