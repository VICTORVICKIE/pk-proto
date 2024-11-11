package game.pirateking.backend.conn;


import game.pirateking.backend.model.Command;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

@Service
public class WsHandler extends AbstractWebSocketHandler {

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		String payload = (String) message.getPayload();
		Command command = new Command(payload);
		System.out.println(command.getAction().toString());
        session.sendMessage(new TextMessage("Got " + command.getAction() + " command."));
	}
}