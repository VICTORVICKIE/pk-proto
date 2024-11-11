package game.pirateking.backend;

import org.springframework.web.socket.WebSocketSession;

public class Player {
	public String id;
	public String name;

	public Player(String id, String name) {
		this.id = id;
		this.name = name;
	}
}
