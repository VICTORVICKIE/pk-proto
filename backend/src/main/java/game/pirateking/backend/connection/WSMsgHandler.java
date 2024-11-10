package game.pirateking.backend.connection;

import game.pirateking.backend.game.GameManager;
import game.pirateking.backend.model.Command;
import game.pirateking.backend.model.Command.ACTION;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
		String[] commandArray = payload.split("\\.");
		Command command = new Command(commandArray);

		ACTION action = command.getAction();
		String playerId = command.getPlayerId();

		if (action.equals(Command.ACTION.HOST)) {
			String gameId = gameManager.initGame(command.getPlayerId(), session);
			JSONObject resp = new JSONObject();
			resp.put("gameId", gameId);
			session.sendMessage(new TextMessage(resp.toString()));
		} else if (action.equals(Command.ACTION.JOIN)) {
			String gameId = command.getGameId();
			gameManager.addPlayer(gameId, playerId, session);
		} else if (action.equals(ACTION.ROLL)) {
			String gameId = command.getGameId();
			gameManager.addPlayer(gameId, playerId, session);
		}
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
