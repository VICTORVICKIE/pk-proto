import { useState, useEffect } from 'react';

function encode(str: String) {
    let encoded = "";
    const strs = str.split("|");
    for (let str of strs) {
        encoded += str.length + "#" + str;
    }
    return encoded;
}

const App = () => {
    const [inputMessage, setInputMessage] = useState('');
    const [message, setMessage] = useState(null); // Store only the latest message
    const [ws, setWs] = useState<WebSocket | null>(null);

    // Establish WebSocket connection
    useEffect(() => {
        const socket = new WebSocket('ws://localhost:8080/game');
        setWs(socket);

        // Handle incoming messages
        socket.onmessage = (event) => {
            setMessage(event.data); // Update to the latest message only
        };

        // Cleanup on unmount
        return () => {
            socket.close();
        };
    }, []);

    // Handle sending a message
    const sendMessage = () => {
        if (ws && ws.readyState === WebSocket.OPEN && inputMessage.trim() !== '') {
            let encodedMessage = encode(inputMessage);
            ws.send(encodedMessage);
        }
    };
    const containerStyle: React.CSSProperties = {
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        minHeight: '100vh',
        padding: '20px',
        boxSizing: 'border-box',
    };

    const centerStyle: React.CSSProperties = {
        padding: '20px',
        backgroundColor: 'var(--primary)',
        color: 'var(--primary-foreground)',
        borderRadius: '8px',
        boxShadow: '0 4px 6px rgba(0, 0, 0, 0.1)',
        maxWidth: '80%',
        textAlign: 'center',
        display: 'flex',
        flexDirection: 'column',
        gap: '4px',
    };
    return (
        <div style={containerStyle}>
            <div style={centerStyle}>
                <h2>WebSocket Client</h2>
                <div>
                    <input
                        type="text"
                        value={inputMessage}
                        onChange={(e) => setInputMessage(e.target.value)}
                        placeholder="Type a command request..."
                    />
                    <button onClick={sendMessage}>Send</button>
                </div>
                <div>
                    <h3>Latest Command Response</h3>
                    <p>{message || "No message received yet"}</p>
                </div>
            </div>
        </div>
    );
};

export default App;
