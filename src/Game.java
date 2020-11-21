/*
 *	Game
 */

import java.io.*;
import java.util.*;

public class Game {

    public static void main(String[] args) throws IOException {

        GameMap gm = new GameMap();
        GameMap gameMap2;
        Room room1 = new Room("room1", "room1 description");
        Room room2 = new Room("room2", "room2 2 descripto");
	Container con1 = new Container("matching", "generic description");
	Container con2 = new Container("not matching", "generic description");
	Item i1  = new Item("i1", "d1");
	Item i2  = new Item("i2", "d2");

	// Testcase:
	// room1 cannot access room2
	// room1 has items Screwdriver & handcuffs
	// room2 cannot access room1
	// room2 has unlock status of i1 & i2
        room1.connectRoom(room2.getName());
        room1.addItem("Screwdriver", "");
	room1.addItem("handcuffs", "hand restrainers");
        room2.connectRoom(room1.getName());
	room2.add_unlock_item(i1);
	room2.add_unlock_item(i2);

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
	// Tests room functions
	/*
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
	*/
	/*
	 Assume player has inventory of con1 and in room 1
	System.out.printf("%s\n", con1.toString());
	System.out.printf("%s\n", room1.toString());
	System.out.printf("%s\n", room2.toString());
	*/
	
        System.out.println(gm.toString());


	System.out.printf("room1 into room2 -> %s\n", (gm.canChangeRoom("room1", "room2") ? "yes": "no"));
	System.out.printf("room2 into room1 -> %s\n\n", (gm.canChangeRoom("room2", "room1") ? "yes": "no"));

	con1.addItem(i1);
	con1.addItem(i2);
	System.out.printf("%s\n", con1.toString());
	gm.changeRoom("room2", "room1", con1);
	gm.changeRoom("room1", "room2", con1);

	System.out.printf("room1 into room2 -> %s\n", (gm.canChangeRoom("room1", "room2") ? "yes": "no"));
	System.out.printf("room2 into room1 -> %s\n\n", (gm.canChangeRoom("room2", "room1") ? "yes": "no"));

        System.out.println(gm.toString());
    }
}
