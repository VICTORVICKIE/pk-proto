package game.pirateking.backend.config;

import game.pirateking.backend.conn.WsHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket

public class WsConfig implements WebSocketConfigurer {

    private final WsHandler wsHandler;

    public WsConfig(WsHandler wsMsgHandler) {
        this.wsHandler = wsMsgHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(wsHandler, "/game")
                .setAllowedOrigins("*");
    }
}
