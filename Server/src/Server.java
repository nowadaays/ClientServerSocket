import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int portNumber = 8000;

        try(ServerSocket serverSocket = new ServerSocket(portNumber)){
            System.out.println("Сервер запущен и ожидает клиента!");

            while (true){
                Socket clientSocket = serverSocket.accept();
                System.out.println("Клиент подключен: " + clientSocket.getInetAddress());

                ClientHandler clientHandler = new ClientHandler(clientSocket);
                new Thread(clientHandler).start();
            }
        }catch (IOException e){
            System.err.println("Ошибка при запуске сервера: " + e.getMessage());
        }
    }
}