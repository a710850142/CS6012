//import org.junit.jupiter.api.Test;
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.security.NoSuchAlgorithmException;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//class HTTPResponseTest {
//
//    @Test
//    void testSendTheExistingFile() throws IOException {
//        // 设置测试用的文件路径
//        String testFilePath = "/Users/zhanyijun/Desktop/CS6011/Week1/Day1/day1.html";
//
//        // 使用ByteArrayOutputStream来捕获写入流的数据
//        ByteArrayOutputStream output = new ByteArrayOutputStream();
//        HTTPResponse response = new HTTPResponse(testFilePath, output);
//
//        // 调用方法
//        response.sendTheExistingFile(testFilePath);
//
//        // 将输出流的内容转换为字符串
//        String responseString = output.toString();
//
//        // 验证响应的内容
//        assertTrue(responseString.contains("HTTP/1.1 200"));
//        assertTrue(responseString.contains("Content-Type: text/html"));
//        // 这里添加其他必要的验证
//    }
//
//    @Test
//    void testHandleWebSocketHandshake() throws IOException, NoSuchAlgorithmException {
//        // 创建HTTPRequest对象并设置必要的WebSocket头部
//        String httpRequest = "GET / HTTP/1.1\n" +
//                "Host: localhost:8080\n" +
//                "Upgrade: websocket\n" +
//                "Connection: Upgrade\n" +
//                "Sec-WebSocket-Key: dGhlIHNhbXBsZSBub25jZQ==\n\n";
//        ByteArrayInputStream input = new ByteArrayInputStream(httpRequest.getBytes());
//        HTTPRequest request = new HTTPRequest(input);
//        request.parseHeaders();
//
//        // 使用ByteArrayOutputStream来捕获写入流的数据
//        ByteArrayOutputStream output = new ByteArrayOutputStream();
//        HTTPResponse response = new HTTPResponse(null, output);
//
//        // 调用方法
//        response.handleWebSocketHandshake(request);
//
//        // 将输出流的内容转换为字符串
//        String responseString = output.toString();
//
//        // 验证响应的内容
//        assertTrue(responseString.contains("HTTP/1.1 101 Switching Protocols"));
//        assertTrue(responseString.contains("Upgrade: websocket"));
//        assertTrue(responseString.contains("Connection: Upgrade"));
//        assertTrue(responseString.contains("Sec-WebSocket-Accept:"));
//        // 这里添加其他必要的验证
//    }
//}
