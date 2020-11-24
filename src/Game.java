/*
 *	Game
 */

import java.io.*;
import java.util.*;

public class Game {

    public static void main(String[] args) throws IOException {

        GameMap game_map = new GameMap();
        GameMap game_map2;

        Room room1 = new Room("Dungeon", "You wake up sitting on a cold and wet floor. The walls are dark and rough. There is a glint of light coming from above, illuminating a cracked open door...");
        Room room2 = new Room("Dining Room", "There is a large rectangular table, filled with plates a silverware. An old dusty chandellier hangs from the ceiling. Behind you is a cracked open door. You see a large gate at the end of the table...");
        Room room3 = new Room("Winning Room", "You win...");
	Container room1_cont = new Container("Chest", "A boxlike chest similar to one you would find in M*necraft.");
	Container room2_cont = new Container("Table", "Items scattered across a table, some look old and rusty, others look usable.");
	// No name or desc, just winning set
	Container room3_cont = new Container("", "");
	Item fork  = new Item("fork", "The pointy thing you eat with");
	Item stick  = new Item("stick", "A stick");


	// set up r1
	room1_cont.addItem(stick);
        room1.unlockRoom();
	room1.setItemsInRoom(room1_cont);
        room1.connectRoom(room2.getName());

	// set up r2
	room2_cont.addItem(fork);
	room2.unlockRoom();
	room2.setItemsInRoom(room2_cont);
        room2.connectRoom(room1.getName());
        room2.connectRoom(room3.getName());

	// set up r3
	room3_cont.addItem(stick);
	room3_cont.addItem(fork);
	room3.setUnlockItems(room3_cont);
        room3.connectRoom(room2.getName());
	
	game_map.addRoom(room1);
	game_map.addRoom(room2);
	game_map.addRoom(room3);

	// Tests serialization
        System.out.printf("%s\n", game_map.toString());
	/*
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println(game_map.roomToString(room1.getName()));
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println(game_map.roomToString(room2.getName()));
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println(game_map.roomToString(room3.getName()));
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXX");
	*/
	
	/*
        gm.save();

        System.out.println();
        // Loads a new map from that JSON file
        gameMap2 = new GameMap("json_out.txt");

        System.out.println("GameMap 2");
        System.out.println(gameMap2.toString());
	*/
    }
}
