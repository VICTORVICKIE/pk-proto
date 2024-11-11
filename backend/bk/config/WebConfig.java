package game.pirateking.backend.config;

import game.pirateking.backend.connection.WSMsgHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebConfig implements WebSocketConfigurer {

	private final WSMsgHandler wsMsgHandler;

    public WebConfig(WSMsgHandler wsMsgHandler) {
        this.wsMsgHandler = wsMsgHandler;
    }

    @Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(wsMsgHandler, "/game")
				.setAllowedOrigins("*");
	}
}
