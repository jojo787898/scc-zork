/*
 *	GameMap
 */

import com.google.gson.*;
import java.io.*;
import java.util.*;

public class GameMap {
    Set<Room> rooms;
    Set<Item> items;
    Set<Container> containers;

    public GameMap(String path) throws FileNotFoundException {
        rooms = new HashSet<>();
        items = new HashSet<>();
        containers = new HashSet<>();
        loadFromFile(path);
    }

    public GameMap(){
        rooms = new HashSet<>();
        items = new HashSet<>();
        containers = new HashSet<>();
    }

    public GameMap(Set<Room> rooms){
        this.rooms = rooms;
        items = new HashSet<>();
        containers = new HashSet<>();
    }

    public void loadFromFile(String path) throws FileNotFoundException {

        Gson g = new Gson();

        File roomFile = new File( "Rooms/");
        File itemFile = new File( "Items/");
        File containerFile = new File("Containers/");

        if(itemFile.exists()) {
            for (File f : itemFile.listFiles()) {

                Scanner scan = new Scanner(f);
                if (scan.hasNext()) {
                    items.add(g.fromJson(scan.nextLine(), Item.class));
                }
            }
        }

        if(containerFile.exists()) {
            for (File f : containerFile.listFiles()) {

                Scanner scan = new Scanner(f);
                if (scan.hasNext()) {
                    containers.add(g.fromJson(scan.nextLine(), Container.class));
                }
            }
        }

        if(roomFile.exists()) {
            for (File f : roomFile.listFiles()) {

                Scanner scan = new Scanner(f);
                if (scan.hasNext()) {
                    rooms.add(new Room(this, g.fromJson(scan.nextLine(), RoomSerializable.class)));
                }
            }
        }
    }

    public String toString(){
        String out = "Rooms: \n";
        for (Room room:rooms) {
            out += room.name + ": " + room.description + "\n" + "Contains: \n";
            for (Item item:room.items.items) {
                out+= "    " + item.name + "\n";
            }
        }
        return out;
    }

    public Room nameToRoom(String name){
        for (Room room:rooms) {
            if (room.name.equals(name)){
                return room;
            }
        }
        System.out.println("Could not find room " + name);
        return null;
    }

    public Item nameToItem(String name){
        for(Item item:items){
            if(item.name.equals(name)){
                return item;
            }
        }
        System.out.println("Could not find item " + name);
        return null;
    }

    public Container nameToContainer(String name){
        for(Container container:containers){
            if(container.name.equals(name)){
                return container;
            }
        }
        System.out.println("Could not find container " + name);
        return null;
    }

    public void Save() throws IOException {

        File itemDirectory = new File("Items");

        if (!itemDirectory.exists()){
            itemDirectory.mkdir();

        }

        System.out.println("Saving");
        if(!items.isEmpty()) {
            for (Item item : this.items) {
                item.toJSON();
            }
        }

        File containerDirectory = new File("Containers");
        if (!containerDirectory.exists()){
            containerDirectory.mkdir();

        }
        if(!containers.isEmpty()) {
            for (Container container : this.containers) {
                container.toJSON();
            }
        }
        File roomDirectory = new File("Rooms");
        if (!roomDirectory.exists()){
            roomDirectory.mkdir();

        }
        if(!rooms.isEmpty()) {
            for (Room room : this.rooms) {
                room.serialize();
            }
        }
    }

    public void addRoom(Room room) {
        rooms.add(room);
        containers.add(room.items);
        for (Item item:room.items.items) {
            items.add(item);
        }
        for (Container container:room.containers) {
            containers.add(container);
            for (Item item:container.items) {
                items.add(item);
            }
        }
    }
}
