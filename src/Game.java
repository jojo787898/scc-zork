/*
 *	Game
 */

import java.io.*;
import java.util.*;

public class Game {
    public static GameMap map;
    public static void main(String[] args) throws IOException {
        map = new GameMap("json_out.txt");
        Player player = new Player(map.getRooms().get("Dungeon"));
        intro();
        gameStart(player, map.getRooms().get("Winning Room"));
    }

    public static void intro() {
        System.out.println("Welcome to the game");
        System.out.println("You are stuck in the dungeon and you need to get out from there");
    }

    public static void gameStart(Player p1, Room endRoom) {
        while(!p1.getRoom().getName().equals(endRoom.getName())) {
            roomStart(p1, p1.getRoom());
        }
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
        System.out.println("Open the door");
        System.out.println("Take \"item\"");
        System.out.println("Move \"room\"");
        System.out.println("Check inventory");
    }

    public static void processResponse(Player p1, String response) {
        Scanner temp = new Scanner(response);
        String s = temp.next();
        if (s.equals("Examine")) {
            s = temp.next();
            if (s.equals("room")) {
                System.out.println(p1.getRoom().toString());
            } else {
                System.out.println(p1.getRoom().getItemsInRoom().toString());
            }
        } else if (s.equals("Open")) {
            Room now = p1.getRoom();
            for (String room : p1.getRoom().getConnectedRooms()) {
                map.getRooms().get(room).unlock_room();
            }
        } else if (s.equals("Move")) {
            s = temp.next() + " " + temp.next();
            Room next = map.getRooms().get(s);
            if (next.canAccess()) {
                p1.move(map.getRooms().get(s));
            }
        } else if (s.equals("Take")) {
            s = temp.next();
            Container now = p1.getRoom().getItemsInRoom();
            p1.take(now, now.searchItem(s));
        } else if (s.equals("Check")) {
            p1.printInventory();
        }
    }
}
