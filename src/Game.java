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
        Room room2 = new Room("Jail", "holds the bad guys"); // construct room2 connected to room1
	Container con1 = new Container("matching", "generic description");
	Container con2 = new Container("not matching", "generic description");
	Item i1 = new Item("i1", "d1");
	Item i2 = new Item("i2", "d2");

        room1.connectRoom(room2.getName(), true);
        room2.connectRoom(room1.getName(), false);
        room1.addItem("Screwdriver", "");
	room1.addItem("handcuffs", "hand restrainers");
        gm.addRoom(room1);
        gm.addRoom(room2);

	// Tests serialization
	/*
        System.out.println("GameMap 1:");
        System.out.println(gm.toString());

        gm.save();

        System.out.println();
        // Loads a new map from that JSON file
        gameMap2 = new GameMap("json_out.txt");

        System.out.println("GameMap 2");
        System.out.println(gameMap2.toString());
	*/

	// tests
	con1.addItem(i1);
	con2.addItem(i1);
	con2.addItem(i2);
	if(con1.equals(con2)) {
		System.out.printf("con1 == con2\n");
	} else {
		System.out.printf("con1 != con2\n");
	}
	if(con1.hasItems(con2)) {
		System.out.printf("con1 has all of con2's items\n");
	} else {
		System.out.printf("con1 doesn't have con2's items\n");
	}
	if(con2.hasItems(con1)) {
		System.out.printf("con2 has all of con1's items\n");
	} else {
		System.out.printf("con2 doesn't have con1's items\n");
	}
	System.out.println();

	System.out.printf("%s %s access %s\n", room1.getName(), room1.canAccess(room2.getName()) ? "can" : "cannot", room2.getName());
	System.out.printf("%s %s access %s\n", room2.getName(), room2.canAccess(room1.getName()) ? "can" : "cannot", room1.getName());
	// TODO, null pointer exception on attempt to access invalid room
	//System.out.printf("%s %s access %s\n", room2.getName(), room2.canAccess("invalid_name") ? "can" : "cannot", "invalid_name");
    }
}
