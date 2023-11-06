import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HTTPRequestTest {
    @Test
    public void testGetTheFileName() throws IOException {
        String httpRequest = "GET /test.html HTTP/1.1\n" +
                "Host: localhost:8080\n" +
                "Connection: keep-alive\n\n";
        ByteArrayInputStream input = new ByteArrayInputStream(httpRequest.getBytes());
        HTTPRequest request = new HTTPRequest(input);
       // assertEquals("/Users/zhanyijun/Desktop/CS6011/Week1/Day1/test.html", request.getTheFileName());
    }

    @Test
    public void testIsWebSocketUpgrade() throws IOException {
        String httpRequest = "GET / HTTP/1.1\n" +
                "Host: localhost:8080\n" +
                "Upgrade: websocket\n" +
                "Connection: Upgrade\n" +
                "Sec-WebSocket-Key: dGhlIHNhbXBsZSBub25jZQ==\n\n";
        ByteArrayInputStream input = new ByteArrayInputStream(httpRequest.getBytes());
        HTTPRequest request = new HTTPRequest(input);
        request.parseHeaders();
        assertTrue(request.isWebSocketUpgrade());
        System.out.println("Headers: " + request.getHeaders());
        System.out.println("Is WebSocket Upgrade: " + request.isWebSocketUpgrade());
    }
}