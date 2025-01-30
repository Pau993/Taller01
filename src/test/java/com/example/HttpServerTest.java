package com.example;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HttpServerTest {

    private ByteArrayOutputStream outputStream;
    private PrintWriter writer;
    
    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        writer = new PrintWriter(outputStream, true);
    }

    @Test
    void testHandleApiRequestSaludo() {
        HttpServer.handleApiRequest("/api/saludo", writer);
        String response = outputStream.toString();
        assertTrue(response.contains("HTTP/1.1 200 OK"));
        assertTrue(response.contains("{\"mensaje\": \"¡Hola desde el servidor!\"}"));
    }

    @Test
    void testHandleApiRequestFecha() {
        HttpServer.handleApiRequest("/api/fecha", writer);
        String response = outputStream.toString();
        assertTrue(response.contains("HTTP/1.1 200 OK"));
        assertTrue(response.contains("\"fecha\":"));
    }

    @Test
    void testHandleApiRequestNotFound() {
        HttpServer.handleApiRequest("/api/desconocido", writer);
        String response = outputStream.toString();
        assertTrue(response.contains("HTTP/1.1 404 Not Found"));
    }

    @Test
    void testHandleApiPostRequest() throws IOException {
        String input = "{\"mensaje\":\"Hola\"}";
        BufferedReader reader = new BufferedReader(new StringReader(input));
        HttpServer.handleApiPostRequest("/api/enviar", reader, writer);
        String response = outputStream.toString();
        assertTrue(response.contains("HTTP/1.1 200 OK"));
        assertTrue(response.contains("Datos recibidos"));
    }
}
