import java.io.IOException;
import java.util.ArrayList;

public class Game {
    public static void main(String[] args) throws IOException {

        // Creates a new map with one empty room called Test Room
        ArrayList<Room> rooms = new ArrayList<Room>();

        rooms.add(new Room("Test Room","Test room description", new Container(), new ArrayList<Container>(), new ArrayList<Door>()));

        Map map = new Map(rooms);

        // Saves the map to a JSON file
        for (Room room:map.rooms) {
            room.toJSON();
        }
        System.out.println(map.toString());

        // Loads a new map from that JSON file
        Map map2 = new Map("Test Room");

        System.out.println(map2.toString());
    }
}
