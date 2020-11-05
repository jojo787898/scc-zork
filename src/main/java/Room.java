import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Room {

    String name;
    String description;
    Container items;
    ArrayList<Container> containers;
    Map<String, Boolean> connectedRooms;

    //ArrayList<Door> doors;

    //Creates a room with all given values
    public Room(String name, String description, Container items, ArrayList<Container> containers, Map<String, Boolean> connectedRooms){

        this.name = name;
        this.description = description;
        this.items = items;
        this.containers = containers;
        this.connectedRooms = connectedRooms;
    }

    //Creates a room connected to an existing room with a default door that is open and unlocked
    public Room(String name, String description, Container items, ArrayList<Container> containers, Room neighbor){

        this.name = name;
        this.description = description;
        this.items = items;
        this.containers = containers;
        connectedRooms = new HashMap<String, Boolean>();

        connectedRooms.put(neighbor.name,false);
        neighbor.connectRooms(this, false);

    }

    //Creates a room connected to an existing room with a custom door state
    public Room(String name, String description, Container items, ArrayList<Container> containers, Room neighbor, Boolean locked){

        this.name = name;
        this.description = description;
        this.items = items;
        this.containers = containers;
        connectedRooms = new HashMap<String, Boolean>();

        connectedRooms.put(neighbor.name,locked);

        neighbor.connectRooms(this, locked);
    }

    public void connectRooms(Room room, Boolean locked){
        this.connectedRooms.put(room.name, locked);
    }

    public void toJSON() throws IOException {
        File directory = new File("Map");
        if (! directory.exists()){
            directory.mkdir();

        }
        Gson g = new Gson();
        FileWriter myWriter = new FileWriter(new File("Map", this.name + ".room"));
        myWriter.write(g.toJson(this));
        myWriter.close();
    }
}
