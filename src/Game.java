
import java.io.IOException;
import java.util.ArrayList;

public class Game {
    public static void main(String[] args) throws IOException {

        // Creates a new map with one empty room called Test Room
        ArrayList<Room> rooms = new ArrayList<Room>();

        //Uses the constructor for an unconnected room
        Room room1 = new Room("Test Room","Test room description", new Container(), new ArrayList<Container>(), new ArrayList<Door>());

        //Uses the constructor to create a new room linked to an existing room, with default door state open and unlocked
        Room room2 = new Room("Test Room 2","Test room description 2", new Container(), new ArrayList<Container>(),room1);


        rooms.add(room1);
        rooms.add(room2);

        Map map = new Map(rooms);

        System.out.println(map.toString());

        // Saves the map to a JSON file
        for (Room room:map.rooms) {
            room.toJSON();
        }

        // Loads a new map from that JSON file
        Map map2 = new Map("Map/");

        System.out.println(map2.toString());
    }
}