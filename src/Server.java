import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private ServerSocket serverSocket;
    private Socket socket;
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;
    private Scanner scanner;
    private String massageToSent;
    private String massageFromClient;

    public Server() throws IOException {
        serverSocket = new ServerSocket(1234);
        socket = serverSocket.accept();
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        scanner = new Scanner(System.in);

        // sent massage
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        massageToSent = scanner.nextLine();
                        bufferedWriter.write(massageToSent);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();

                        if (massageToSent.equalsIgnoreCase("bye"))
                            break;
                    } catch (IOException e) {
                        e.printStackTrace();
                        close();
                    }
                }
            }
        }).start();

        // receive massage
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        massageFromClient = bufferedReader.readLine();
                        System.out.println("Client: " + massageFromClient);

                        if (massageFromClient.equalsIgnoreCase("bye"))
                            break;
                    } catch (IOException e) {
                        e.printStackTrace();
                        close();
                    }
                }
            }
        }).start();
    }

    public void close() {
        try {
            serverSocket.close();
            socket.close();
            bufferedWriter.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new Server();
    }
}
