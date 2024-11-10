package game.pirateking.backend;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.*;

@Service
public class WSMsgHandler implements WebSocketHandler {

	private final GameManager gameManager;

	@Autowired
    public WSMsgHandler(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.printf("Connection established on session: %s\n", session.getId());
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		String payload = (String) message.getPayload();
		System.out.printf("Message: %s\n", payload);
		session.sendMessage(new TextMessage("Started processing msg in server: " + session + " - " + payload));
		String[] command = payload.split("\\.");
		if (command.length >= 3  && command[2].equals("host")) {
			String gameId = gameManager.initGame(command[1], session);
			System.out.println("[App] - Init Game: " + gameId);
			JSONObject resp = new JSONObject();
			resp.put("gameId", gameId);
			session.sendMessage(new TextMessage(resp.toString()));
		} else if (command.length >= 4 && command[2].equals("join")) {
			String gameId = command[3];
			gameManager.addPlayer(gameId, command[1], session);
		} else if (command.length >= 4 && command[2].equals("roll")) {
			String gameId = command[3];
			gameManager.addPlayer(gameId, command[1], session);
		} else {
			session.sendMessage(new TextMessage("Invalid Payload: " + payload));
		}
		session.sendMessage(new TextMessage("Completed processing msg in server: " + payload));
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		System.out.printf("Exception occurred: %s on session: %s\n", exception.getMessage(), session.getId());
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		System.out.printf("Connection closed on session: %s with status: %s\n", session.getId(), closeStatus.getCode());
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}
}
