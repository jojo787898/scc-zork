/*
 *	Room
 *	holds information only about itself, connected room & their unlock status,
 *	nessesary times to unlock this room, items on the floor, name, desc
 */

import java.io.*;
import java.util.*;

public class Room {

    /* Members */
    private String name;
    private String description;
    private boolean unlocked;
    private Container unlock_items;		// <- compare with players for unlock status
    private Container items_in_room;		// <- items in the room
    private Set<String> connectedRooms;		// <- room name and unlock status

    /* Constructors */
    public Room() {
	    this("", "");
    }

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.items_in_room = new Container();
	this.unlock_items = new Container();
	this.unlocked = false;
        this.connectedRooms = new HashSet<>();
    }

    /* Edit room state functions */
    public void connectRoom(String room_name){
        this.connectedRooms.add(room_name);
    }

    public void addItem(String itemName, String description){
        this.items_in_room.addItem(new Item(itemName, description));
    }

    public void add_unlock_item(Item new_item) {
	this.unlock_items.addItem(new_item);
    }

    public void set_unlock_items(Container set_items) {
	this.unlock_items = set_items;
    }

    public void unlock_room() {
	    this.unlocked = true;
    }

    /* API functions */
    // Can this room be accessed
    public boolean canAccess(Container player_inv) {
	if(unlocked || player_inv.hasItems(this.unlock_items)) {
		return true;
	} else {
		return false;
	}
    }
    public boolean canAccess() {
	    return unlocked;
    }

    // Check if room is connected
    public boolean is_connected(String room_name) {
	return connectedRooms.contains(room_name);
    }

    /* getters, setters, toStrings */
    public Set<String> getConnectedRooms() {
	    return this.connectedRooms;
    }

    public Container getItemsInRoom() {
	return this.items_in_room;
    }

    public String getDescription() {
        return this.description;
    }

    public String getName() {
        return this.name;
    }

    // TODO Make prettier, print connected room names, print description
    public String toString() {
        String ret_str = this.name + "\n";
        for(String room_name : connectedRooms) {
            ret_str += "-Connected rooms-\n";
            ret_str += room_name + "\n";
        }
        ret_str += "-takable items-\n";
        ret_str += this.items_in_room.toString();
        ret_str += "-unlock items-\n";
        ret_str += this.unlock_items.toString();
        ret_str += (this.unlocked ? "unlocked" : "locked") + "\n";
        return ret_str;
    }
}
