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

    public void addItem(String item_name, String description){
        this.items_in_room.addItem(new Item(item_name, description));
    }

    public void addUnlockItem(Item new_item) {
        this.unlock_items.addItem(new_item);
    }

    public void setUnlockItems(Container set_items) {
        this.unlock_items = set_items;
    }

    public void unlockRoom() {
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
    public boolean isConnected(String room_name) {
        return connectedRooms.contains(room_name);
    }

    /* getters, setters, toStrings */
    public Set<String> getConnectedRooms() {
        return this.connectedRooms;
    }

    public Container getItemsInRoom() {
        return this.items_in_room;
    }

    public Container setItemsInRoom(Container set_items) {
        return this.items_in_room = set_items;
    }

    public String getDescription() {
        return this.description;
    }

    public String getName() {
        return this.name;
    }

    // Describe room
    public String toString() {
        String ret_str = this.name + "\n";
        if(unlocked) {
            ret_str += this.description + "\n";
            ret_str += this.items_in_room.toString();
        } else {
            ret_str += "Seems locked...\n";
            ret_str += "There looks to be a socket for: ";
            ret_str += this.unlock_items.itemsToString();
            ret_str += "\n";
        }
        return ret_str;
    }
}
