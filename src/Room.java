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
    private Map<Room, Boolean> connectedRooms;

    public void addItem(String itemName, String description){
        items.addItem(new Item(itemName, description));
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public Set<Container> getContainers() {
        return this.containers;
    }

    public Container getItems() {
        return this.items;
    }

    public Map<Room, Boolean> getConnectedRoom() {
        return this.connectedRooms;
    }

    public Room(GameMap gameMap, RoomSerializable rs){
        this.name = rs.getName();
        this.description = rs.getDescription();
        this.items = gameMap.nameToContainer(rs.getItems());
        Set<Container> containers = new HashSet<>();

        for (String cs:rs.getContainers()) {
            containers.add(gameMap.nameToContainer(cs));
        }
        this.containers = containers;

        Map<Room,Boolean> connectedRooms = new HashMap<>();

        for (String room:rs.getConnectedRooms().keySet()) {
            connectedRooms.put(gameMap.nameToRoom(room),rs.getConnectedRooms().get(room));
        }
        this.connectedRooms = connectedRooms;
    }

    public Room(GameMap gameMap,String name, String description) {
        this.name = name;
        this.description = description;

        this.items = new Container(name + "container", "");
        this.containers = new HashSet<>();
        this.connectedRooms = new HashMap<>();
    }

    public Room(GameMap gameMap, String name, String description, Room neighbor, Boolean locked) {
        this.name = name;
        this.description = description;
        this.items = new Container(name + "container","");
        this.containers = new HashSet<>();
        Map<Room, Boolean> connectedRooms = new HashMap<>();

        connectedRooms.put(neighbor,locked);

        this.connectedRooms = connectedRooms;
        neighbor.connectRooms(this,locked);
    }

    public Room(GameMap gameMap, String name, String description, Room neighbor) {
        this.name = name;
        this.description = description;
        this.items = new Container(name + "container", "");
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

        for (Item item:items.getItems()) {
            item.toJSON();
        }

        for(Container container:containers){
            container.toJSON();
        }
    }

}
