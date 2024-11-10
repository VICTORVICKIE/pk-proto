console.log("Hello World");
const ws = new WebSocket("ws://localhost:8080/game");
ws.onmessage = () => {
    
};

ws.onopen = () => {
    ws.send("v1.player1.host");
};
// ws.send("v1.player2.join.")
