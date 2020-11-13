/*
 *	Room
 */

import java.io.*;
import java.util.*;

public class Room {

    private String name;
    private String description;
    private Container items;
    private Set<Container> containers;
    private Map<String, Boolean> connectedRooms;

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
        connectRoom(neighbor_room, false);
    }

    // Always make sure to connect both rooms in main
    public void connectRoom(String room_name, Boolean locked){
        this.connectedRooms.put(room_name, locked);
    }

    public void addItem(String itemName, String description){
        this.items.addItem(new Item(itemName, description));
    }
    
    // TODO Make prettier
    public String toString() {
	String ret_str = "";
        for(String room_name : connectedRooms.keySet()) {
	    ret_str += "-Connected rooms-\n";
            ret_str += room_name + "->";
            ret_str += (connectedRooms.get(room_name)) ? "unlocked" : "locked" ;
	    ret_str += "\n";
	    ret_str += "-Items-\n";
            ret_str += this.items.toString();
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
