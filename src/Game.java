import java.io.IOException;
import java.util.ArrayList;

public class Game {
    public static void main(String[] args) throws IOException {

        ArrayList<Room> rooms = new ArrayList<Room>();

        rooms.add(new Room("Test Room","Test room description", new Container(), new ArrayList<Container>(), new ArrayList<Door>()));

        Map map = new Map(rooms);

        for (Room room:map.rooms) {
            room.toJSON();
        }
        System.out.println(map.toString());

        Map map2 = new Map("Test Room");

        System.out.println(map2.toString());
    }
}
