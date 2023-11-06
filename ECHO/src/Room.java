import java.net.Socket;
import java.util.ArrayList;

public class Room {
    ArrayList<Socket> clients = new ArrayList<>();
    private Socket client;
    private String roomName;
    public Room(Socket client,String roomName){
        this.client = client;
        this.roomName = roomName;
        clients.add(client);
    }

    public Room getRoomName() {


        return this;
    }
}
