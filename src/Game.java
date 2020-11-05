
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Game {
    public static void main(String[] args) throws IOException {


        GameMap gm = new GameMap();
        //Uses the constructor for an unconnected room
        Room room1 = new Room(gm,"Test Room","Test room description");
        room1.addItem("Screwdriver");
        //Uses the constructor to create a new room linked to an existing room, with default door state open and unlocked
        Room room2 = new Room(gm,"Test Room 2","Test room description 2",room1);

        gm.addRoom(room1);
        gm.addRoom(room2);

        System.out.println("GameMap 1:");
        System.out.println(gm.toString());

        System.out.println("Saving to file");

        gm.Save();

        System.out.println("Loading from file:");
        System.out.println();
        // Loads a new map from that JSON file
        GameMap gameMap2 = new GameMap("/");

        System.out.println("GameMap 2");
        System.out.println(gameMap2.toString());
    }

