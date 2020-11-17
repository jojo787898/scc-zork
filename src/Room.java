/*
 *	Room
 */

import java.io.*;
import java.util.*;

public class Room {

    private String name;
    private String description;
    private Container unlock_items; // <- compare with players for unlock status
    private Container items;
    //private Set<Container> containers; <- adds complexity, maybe add back later
    private Map<String, Boolean> connectedRooms; // <- room name and unlock status

    // Basic constructor with name and desc
    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.items = new Container(name + "container", "");
	this.unlock_items = new Container();
        // this.containers = new HashSet<>(); <- check members
        this.connectedRooms = new HashMap<>();
    }

    // Constructor with connection
    public Room(String name, String description, String neighbor_room) {
        this(name, description);
        connectRoom(neighbor_room, false);
    }

    // Always make sure to connect both rooms in main
    public void connectRoom(String room_name, boolean lock_status){
        this.connectedRooms.put(room_name, lock_status);
    }

    public void addItem(String itemName, String description){
        this.items.addItem(new Item(itemName, description));
    }

    public void add_unlock_item(Item new_item) {
	this.unlock_items.addItem(new_item);
    }

    public void set_unlock_items(Container set_items) {
	this.unlock_items = set_items;
    }

    // TODO take items, attempt to enter room(check item list),
    public boolean canAccess(String connected_room_name) {
	if(this.connectedRooms.get(connected_room_name)) {
		return true;
	}
	return false;
    }

    public boolean canAccess(String connected_room_name, Container player_inv) {
	if(canAccess(connected_room_name)) { // already have access
		return true;
	} else if(!this.connectedRooms.containsKey(connected_room_name)) { // not a connected room
		return false;
	} else if(player_inv.hasItems(this.unlock_items)) {
		return true;
	} else {
		return false;
	}
    }

    public Map<String, Boolean> getConnectedRooms() {
	    return this.connectedRooms;
    }

    public String getDescription() {
        return this.description;
    }

    public String getName() {
        return this.name;
    }

    // TODO Make prettier, connected room names
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
}
