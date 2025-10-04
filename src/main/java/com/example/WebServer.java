package com.example;

import fi.iki.elonen.NanoHTTPD;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WebServer extends NanoHTTPD {

    public WebServer() throws IOException {
        super(8080);
        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
        System.out.println("🌍 Server running at http://localhost:8080/");
    }

    @Override
    public Response serve(IHTTPSession session) {
        if (session.getUri().equals("/run") && Method.POST.equals(session.getMethod())) {
            try {
                // 🌼 Parse form data
                Map<String, String> files = new HashMap<>();
                session.parseBody(files);
                String code = session.getParms().get("code");
                String input = session.getParms().get("input");

                // ✅ Save code to database
                CodeDatabase.saveCode(code);

                // ⚙️ Compile and run
                String output = CompilerLogic.compileAndRun(code, input);
                return newFixedLengthResponse("<pre>" + output + "</pre>");
            } catch (Exception e) {
                return newFixedLengthResponse("❌ Error: " + e.getMessage());
            }
        } else {
            // 🌐 Serve the homepage
            return serveFile("/index.html");
        }
    }

    private Response serveFile(String path) {
        try {
            return newFixedLengthResponse(
                Response.Status.OK,
                "text/html",
                getClass().getResourceAsStream(path),
                getClass().getResource(path).openConnection().getContentLength()
            );
        } catch (Exception e) {
            return newFixedLengthResponse("❌ File not found: " + path);
        }
    }

    public static void main(String[] args) {
        try {
            new WebServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}








