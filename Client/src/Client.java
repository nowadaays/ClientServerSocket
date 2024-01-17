import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {

        String serveradress = "Localhost";
        int portNumber = 8000;

        try(Socket socket = new Socket(serveradress , portNumber);
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            Scanner scanner = new Scanner(System.in)){
            System.out.println("Подключение к серверу. Чтобы выйти введите exit");

            while (true){
                System.out.println("Введите сообщение ");
                String message = scanner.nextLine();

                output.writeObject(message);
                output.flush();

                Object receivedResponse = input.readObject();
                System.out.println("Ответ от сервера: " + receivedResponse);

                if("exit".equalsIgnoreCase(message)){
                    break;
                }
            }
        }catch (IOException | ClassNotFoundException e){
            System.out.println("Ошибка при работе с сервером: " + e.getMessage());
        }
    }
}