/*
 *	Game
 */

import java.io.*;
import java.util.*;

public class Game {

    public static void main(String[] args) throws IOException {
        GameMap gm = new GameMap();
        GameMap gameMap2;
        Room room1 = new Room("Toolshed", "shack where you make things");
        Room room2 = new Room("Jail", "holds the bad guys", room1.getName()); // construct room2 connected to room1

	room1.connectRoom(room2.getName(), true); // Manually connect room1 to room2
        room1.addItem("Screwdriver", "");
        gm.addRoom(room1);
        gm.addRoom(room2);
	room1.addItem("handcuffs", "hand restrainers");

        System.out.println("GameMap 1:");
        System.out.println(gm.toString());

        gm.Save();

        System.out.println();
        // Loads a new map from that JSON file
        gameMap2 = new GameMap("json_out.txt");

        System.out.println("GameMap 2");
        System.out.println(gameMap2.toString());
    }
}
