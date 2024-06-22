package com.sistemaifes.sistemaifes.microterm;


import org.springframework.stereotype.Component;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.stream.JsonParsingException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

@Component
public class MicroterminalService {
    private final int port = 1025;
    private Socket socket = null;
    private ServerSocket serverSocket = null;
    private BufferedInputStream bis = null;
    private DataInputStream dis = null;

    public MicroterminalService() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + serverSocket.getLocalPort() + "...");
            System.out.println("Waiting for client...");
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    public void start() {
        try {
            socket = serverSocket.accept();
            System.out.println("Client " + socket.getRemoteSocketAddress() + " connected to the server...");

            bis = new BufferedInputStream(socket.getInputStream());
            dis = new DataInputStream(bis);

            while (true) {
                try {
                    DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    clearScreen(dos);

                    dos.writeBytes("Digite a matricula :"  + "\r\n");

                    StringBuilder userInput = new StringBuilder();
                    while (true) {
                        int inputChar = in.read();
                        if (inputChar == 13) {  // Check if the user pressed Enter
                            break;
                        }
                        userInput.append((char) inputChar);

                        dos.writeByte(inputChar);
                    }

                    String studentId = userInput.toString();
                    String response = getAulaInfo(studentId);
                    System.out.println("\n\n\n" + response + "\n\n\n");
                    displayAulaInfo(response, dos, studentId);

                    while (in.read() != 13) {
                        // Wait until the user presses Enter
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            }
            dis.close();
            socket.close();
            System.out.println("Client " + socket.getRemoteSocketAddress() + " disconnected from the server...");
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    private void playAudio() {
        try {
            String filepath =  "src/main/java/com/sistemaifes/sistemaifes/sfx/cupcakke.wav";
            AudioInputStream aui = AudioSystem.getAudioInputStream(new File(filepath).getAbsoluteFile());
            try {
                Clip clip = AudioSystem.getClip();
                clip.open(aui);
                clip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getAulaInfo(String studentId) throws IOException {
        String apiUrl = "http://localhost:8080/api/lesson/s/getNextLesson/" + studentId;
        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
    
        int responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            Scanner scanner = new Scanner(conn.getInputStream());
            StringBuilder response = new StringBuilder();
            while (scanner.hasNextLine()) {
                response.append(scanner.nextLine());
            }
            scanner.close();
            return response.toString();
        } else if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
            return "NOT_FOUND";
        } else {
            return "ERROR";
        }
    }    

    private void displayAulaInfo(String response, DataOutputStream dos, String studentId) {
        try {
            clearScreen(dos);
    
            if ("NOT_FOUND".equals(response)) {
                dos.writeBytes("Matricula nao foi encontrada" + "\r\n");
            } else if ("ERROR".equals(response)) {
                dos.writeBytes("Ocorreu um erro ao buscar as informacoes" + "\r\n");
            } else {
                JsonReader reader = Json.createReader(new StringReader(response));
                JsonObject aulaInfo = reader.readObject();
    
                String student = aulaInfo.getString("studentName").split(" ")[0];
                String discipline = aulaInfo.getString("disciplineName");
                String local = aulaInfo.getString("localName");
                String startTimeTs = aulaInfo.getString("startTimeTs");

                ZonedDateTime startTime = ZonedDateTime.parse(startTimeTs);
                ZonedDateTime adjustedStartTime = startTime.withZoneSameInstant(ZoneId.of("America/Sao_Paulo"));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                String formattedTime = adjustedStartTime.format(formatter);
    
                dos.writeBytes("Ola, " + student + "\r\n");
                dos.writeBytes("Disciplina: " + discipline + "\r\n");
                dos.writeBytes("Local: " + local + "\r\n");
                dos.writeBytes("Horario: " + formattedTime + "\r\n");

                playAudio();
            }
        } catch (JsonParsingException e) {
            try {
                dos.writeBytes("Aluno não encontrado" + "\r\n");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }    

    private void clearScreen(DataOutputStream dos) throws IOException {
        dos.writeBytes("\033[H\033[2J");
        dos.flush();
    }
}
