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
    Map<Room, Boolean> connectedRooms;



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

    public Room(GameMap gameMap,String name, String description) {
        this.gameMap = gameMap;
        this.name = name;
        this.description = description;

        this.items = new Container(name+"container");
        this.containers = new HashSet<>();
        this.connectedRooms = new HashMap<>();
    }

    public Room(GameMap gameMap, String name, String description, Room neighbor, Boolean locked) {
        this.gameMap = gameMap;
        this.name = name;
        this.description = description;
        this.items = new Container(name+"container");
        this.containers = new HashSet<>();
        Map<Room, Boolean> connectedRooms = new HashMap<>();

        connectedRooms.put(neighbor,locked);

        this.connectedRooms = connectedRooms;
        neighbor.connectRooms(this,locked);
    }

    public Room(GameMap gameMap, String name, String description, Room neighbor) {
        this.gameMap = gameMap;
        this.name = name;
        this.description = description;
        this.items = new Container(name+"container");
        this.containers = new HashSet<>();
        Map<Room, Boolean> connectedRooms = new HashMap<>();

        connectedRooms.put(neighbor,false);
        this.connectedRooms = connectedRooms;
        neighbor.connectRooms(this,false);
    }


    public void connectRooms(Room room, Boolean locked){
        this.connectedRooms.put(room, locked);
    }

    public void serialize() throws IOException {
        RoomSerializable rs = new RoomSerializable(this);
        rs.toJSON();

        for (Item item:items.items) {
            item.toJSON();
        }

        for(Container container:containers){
            container.toJSON();
        }
    }

    public void addItem(String itemName){
        items.items.add(new Item(itemName));
    }
}
