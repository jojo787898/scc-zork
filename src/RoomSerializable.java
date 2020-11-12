/*
 *	RoomSerializable
 */

import com.google.gson.*;

import java.io.*;
import java.util.*;

public class RoomSerializable {
    String name;
    String description;
    String items;
    Set<String> containers;
    Map<String, Boolean> connectedRooms;

    public RoomSerializable(String name, String description, String items, Set<String>  containers, Map<String, Boolean> connectedRooms){

        this.name = name;
        this.description = description;
        this.items = items;
        this.containers = containers;
        this.connectedRooms = connectedRooms;
    }

    //Creates a room connected to an existing room with a default door that is open and unlocked
    public RoomSerializable(String name, String description, String items, Set<String> containers, RoomSerializable neighbor){

        this.name = name;
        this.description = description;
        this.items = items;
        this.containers = containers;
        connectedRooms = new HashMap<String, Boolean>();

        connectedRooms.put(neighbor.name,false);
        neighbor.connectRooms(this, false);

    }

    //Creates a room connected to an existing room with a custom door state
    public RoomSerializable(String name, String description, String items, Set<String> containers, RoomSerializable neighbor, Boolean locked){

        this.name = name;
        this.description = description;
        this.items = items;
        this.containers = containers;
        connectedRooms = new HashMap<String, Boolean>();

        connectedRooms.put(neighbor.name,locked);

        neighbor.connectRooms(this, locked);
    }

    public RoomSerializable(Room room){

        this.name = room.name;
        this.description = room.description;
        this.items = room.items.getName();

        Set<String> containers = new HashSet<>();
        for (Container container:room.containers) {
            containers.add(container.getName());
        }
        this.containers = containers;

        Map<String, Boolean> connectedRooms = new HashMap<>();

        for (Room rm:room.connectedRooms.keySet()) {
            connectedRooms.put(rm.name,room.connectedRooms.get(rm));
        }
        this.connectedRooms = connectedRooms;
    }

    public void connectRooms(RoomSerializable room, Boolean locked){
        this.connectedRooms.put(room.name, locked);
    }

    public void toJSON() throws IOException {
        Gson g = new Gson();
        FileWriter myWriter = new FileWriter(new File("Rooms", this.name + ".room"));
        myWriter.write(g.toJson(this));
        myWriter.close();
    }

    public void removeRoomConnection(RoomSerializable room){
        if(connectedRooms.containsKey(room)){
            connectedRooms.remove(room);
        }else{
            System.out.println("Room is not connected to " + this.name);
        }
    }
}
