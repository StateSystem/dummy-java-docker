import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class Main {
  public static void main(String[] args) throws IOException {
    int port = 8080;
    String fromEnv = System.getenv("PORT");
    if (fromEnv != null && !fromEnv.isBlank()) {
      port = Integer.parseInt(fromEnv.trim());
    }
    HttpServer server = HttpServer.create(new InetSocketAddress("0.0.0.0", port), 0);
    server.createContext("/", exchange -> {
      byte[] body = "{\"ok\":true,\"app\":\"dummy-java-docker\",\"framework\":\"Java\"}".getBytes(StandardCharsets.UTF_8);
      exchange.getResponseHeaders().set("Content-Type", "application/json; charset=utf-8");
      exchange.sendResponseHeaders(200, body.length);
      try (OutputStream os = exchange.getResponseBody()) {
        os.write(body);
      }
    });
    server.start();
    System.out.println("listening on " + port);
  }
}
