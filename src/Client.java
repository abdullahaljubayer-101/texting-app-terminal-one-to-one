import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;
    private Scanner scanner;
    private String massageToSent;
    private String massageFromClient;

    public Client() throws IOException {
        socket = new Socket("localhost", 1234);
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
                        System.out.println("Server: " + massageFromClient);

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
            socket.close();
            bufferedWriter.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new Client();
    }
}
