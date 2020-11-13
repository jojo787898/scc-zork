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

/*
    public Room(GameMap gameMap, RoomSerializable rs){
        this.gameMap = gameMap;
        this.name = rs.name;
        this.description = rs.description;
        this.items = gameMap.nameToContainer(rs.items);
        Set<Container> containers = new HashSet<>();

        for (String cs:rs.containers) {
            containers.add(gameMap.nameToContainer(cs));
        }
        this.containers = containers;

        Map<Room,Boolean> connectedRooms = new HashMap<>();

        for (String room:rs.connectedRooms.keySet()) {
            connectedRooms.put(gameMap.nameToRoom(room),rs.connectedRooms.get(room));
        }
        this.connectedRooms = connectedRooms;
    }

    public Room(GameMap gameMap, String name, String description, Room neighbor, Boolean locked) {
        this.gameMap = gameMap;
        this.name = name;
        this.description = description;
        this.items = new Container(name + "container","");
        this.containers = new HashSet<>();
        Map<Room, Boolean> connectedRooms = new HashMap<>();

        connectedRooms.put(neighbor,locked);

        this.connectedRooms = connectedRooms;
        neighbor.connectRooms(this,locked);
    }

*/


    // Always make sure to connect both rooms in main
    public void connectRooms(String room_name, Boolean locked){
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
        return "TODO: ROOM TO STRING\n";
    }

    public String getName() {
	    return this.name;
    }

    public String getDescription() {
	    return this.description;
    }
}
