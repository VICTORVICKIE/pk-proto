package game.pirateking.backend;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WSConfig implements WebSocketConfigurer {

	private final WSMsgHandler wsMsgHandler;

    public WSConfig(WSMsgHandler wsMsgHandler) {
        this.wsMsgHandler = wsMsgHandler;
    }

    @Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(wsMsgHandler, "/game")
				.setAllowedOrigins("*");
	}
}
