/*
 *	Room
 */

import java.io.*;
import java.util.*;

public class Room {

    GameMap gameMap;
    String name;
    String description;
    Container items;
    Set<Container> containers;
    Map<String, Boolean> connectedRooms;

    // Basic constructor with name and desc
    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.items = new Container(name + "container", "");
        this.containers = new HashSet<>();
        this.connectedRooms = new HashMap<>();
    }

    // Constructor with connection
    public Room(String name, String description, String neighbor_room) {
        this(name, description);
        Map<String, Boolean> connectedRooms = new HashMap<>();
        connectedRooms.put(neighbor_room, false);
        this.connectedRooms = connectedRooms;
    }

    // Always make sure to connect both rooms in main
    public void connectRoom(String room_name, Boolean locked){
        this.connectedRooms.put(room_name, locked);
    }

    public void serialize() throws IOException {
        RoomSerializable rs = new RoomSerializable(this);
        rs.toJSON();

        for (Item item:items.getItems()) {
            item.toJSON();
        }

        for(Container container:containers){
            container.toJSON();
        }
    }

    public void addItem(String itemName, String description){
        items.addItem(new Item(itemName, description));
    }

    public String toString() {
	String ret_str = "";
        for(String room_name : connectedRooms.keySet()) {
            ret_str += room_name + "->";
            ret_str += (connectedRooms.get(room_name)) ? "unlocked" : "locked" ;
	    ret_str += "\n";
	}
        return ret_str;
    }

    public String getName() {
	    return this.name;
    }

    public String getDescription() {
	    return this.description;
    }
}
