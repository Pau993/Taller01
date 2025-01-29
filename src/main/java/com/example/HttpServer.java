package com.example;

import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;

public class HttpServer {
    private static final int PORT = 35000;
    private static final String BASE_DIRECTORY = "src/main/resources/Files";

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Servidor iniciado en el puerto " + PORT);

        while (true) {
            try (Socket clientSocket = serverSocket.accept()) {
                handleRequest(clientSocket);
            }
        }
    }

    private static void handleRequest(Socket clientSocket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             OutputStream dataOut = clientSocket.getOutputStream()) {

            String requestLine = in.readLine();
            if (requestLine == null || requestLine.isEmpty()) return;

            System.out.println("Solicitud recibida: " + requestLine);
            String[] requestParts = requestLine.split(" ");
            String method = requestParts[0];
            String path = requestParts[1];

            if (method.equals("GET")) {
                if (path.startsWith("/api")) {
                    handleApiRequest(path, out);
                } else {
                    serveStaticFile(path, out, dataOut);
                }
            } else if (method.equals("POST") && path.startsWith("/api")) {
                handleApiPostRequest(path, in, out);
            } else {
                sendResponse(out, 405, "Method Not Allowed", "Método no permitido.");
            }

        } catch (IOException e) {
            System.err.println("Error al manejar la solicitud: " + e.getMessage());
        }
    }

    private static void serveStaticFile(String path, PrintWriter out, OutputStream dataOut) throws IOException {
        String filePath = BASE_DIRECTORY + (path.equals("/") ? "/index.html" : path);
        File file = new File(filePath);

        if (file.exists() && !file.isDirectory()) {
            String contentType = Files.probeContentType(file.toPath());
            byte[] fileData = Files.readAllBytes(file.toPath());

            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type: " + contentType);
            out.println("Content-Length: " + fileData.length);
            out.println();
            out.flush();

            dataOut.write(fileData, 0, fileData.length);
            dataOut.flush();
        } else {
            sendResponse(out, 404, "Not Found", "Archivo no encontrado.");
        }
    }

    private static void handleApiRequest(String path, PrintWriter out) {
        if (path.equals("/api/saludo")) {
            sendResponse(out, 200, "OK", "{\"mensaje\": \"¡Hola desde el servidor!\"}");
        } else if (path.equals("/api/fecha")) {
            sendResponse(out, 200, "OK", "{\"fecha\": \"" + new Date() + "\"}");
        } else {
            sendResponse(out, 404, "Not Found", "{\"error\": \"Recurso no encontrado\"}");
        }
    }

    private static void handleApiPostRequest(String path, BufferedReader in, PrintWriter out) throws IOException {
        if (path.startsWith("/api/enviar")) {
            StringBuilder body = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null && !line.isEmpty()) {
                body.append(line);
            }

            sendResponse(out, 200, "OK", "{\"mensaje\": \"Datos recibidos: " + body + "\"}");
        } else {
            sendResponse(out, 404, "Not Found", "{\"error\": \"Recurso no encontrado\"}");
        }
    }

    private static void sendResponse(PrintWriter out, int statusCode, String statusMessage, String body) {
        out.printf("HTTP/1.1 %d %s\r\n", statusCode, statusMessage);
        out.println("Content-Type: application/json");
        out.println("Content-Length: " + body.length());
        out.println();
        out.println(body);
    }
}
