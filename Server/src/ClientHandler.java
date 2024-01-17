import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private Socket clientSocket;
    private ObjectInputStream input;
    private ObjectOutputStream output;

    public ClientHandler(Socket socket){
        this.clientSocket = socket;
        try{
            this.output = new ObjectOutputStream(socket.getOutputStream());
            this.input = new ObjectInputStream(socket.getInputStream());
        }catch (IOException e){
            System.err.println("Ошибка при создании потока ввода или вывода для клиента: " + e.getMessage());
        }
    }
    @Override
    public void run() {
        try{
            while(true){
                Object receivedMessage = input.readObject();
                System.out.println("Полученно от клиента: " + receivedMessage);

                String response = "Сервер получил ваше сообщение: " + receivedMessage;

                output.writeObject(response);
                output.flush();
            }
        }catch (IOException | ClassNotFoundException e){
            System.err.println("Ошибка при обработке клиента: " + e.getMessage());
        }finally {
            try{
                clientSocket.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
