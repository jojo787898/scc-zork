/*
 *	Game
 */

import java.io.*;
import java.util.*;

public class Game {
    public static GameMap map;
    public static void main(String[] args) throws IOException {
        Room room1 = new Room("Dungeon Room", "You wake up sitting on a cold and wet floor. The walls are dark and rough. There is a glint of light coming from above, illuminating a cracked open door...");
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

        map = new GameMap();
        map.addRoom(room1);
        map.addRoom(room2);
        map.addRoom(room3);
        Player p1 = new Player(map.getRooms().get("Dungeon Room"));
        intro();
        map.save();
        gameStart(p1, map.getRooms().get("Winning Room"));
    }

    public static void intro() {
        System.out.println("Welcome to the game");
        System.out.println("You are stuck in the dungeon and you need to get out from there");
    }

    public static void gameStart(Player p1, Room endRoom) {
        while(!p1.getRoom().getName().equals(endRoom.getName())) {
            roomStart(p1, p1.getRoom());
        }
        System.out.println("You Win");
    }

    public static void roomStart(Player p1, Room now) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(now.toString());
        while (p1.getRoom().getName().equals(now.getName())) {
            printOutResponse();
            System.out.println("What do you want to do ?");
            String response = scanner.nextLine();
            processResponse(p1, response);
        }
    }

    public static void printOutResponse() {
        System.out.println("Examine \"room\"/\"item\"/\"container\"");
        System.out.println("Take \"item\"");
        System.out.println("Move \"room\"");
        System.out.println("Check inventory");
        System.out.println("Unlock \"room\"");
    }

    public static void processResponse(Player p1, String response) {
        Scanner temp = new Scanner(response);
        String s = temp.next();
        if (s.equals("Examine")) {
            s = temp.next();
            if (s.equals("room")) {
                System.out.println(p1.getRoom().getItemsInRoom().getDescription());
                System.out.println(p1.getRoom().getItemsInRoom().toString());
                System.out.println("Connected to : " + p1.getRoom().getConnectedRooms().toString());
            }
        } else if (s.equals("Move")) {
            s = temp.next() + " " + temp.next();
            Room next = map.getRooms().get(s);
            if (next.canAccess()) {
                p1.move(map.getRooms().get(s));
            } else {
                System.out.println("The door is locked");
            }
        } else if (s.equals("Take")) {
            s = temp.next();
            p1.take(p1.getRoom().getItemsInRoom(), p1.getRoom().getItemsInRoom().searchItem(s));
        } else if (s.equals("Check")) {
            p1.printInventory();
        } else if (s.equals("Unlock")) {
            s = temp.next() + " " + temp.next();
            Room next = map.getRooms().get(s);
            if (next.canAccess(p1.getInventory())) {
                next.unlockRoom();
            }
        }
        System.out.println();
    }
}
