import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Room {

    String name;
    String description;
    Container items;
    ArrayList<Container> containers;
    ArrayList<Door> doors;

    //Creates a room with all given values
    public Room(String name, String description, Container items, ArrayList<Container> containers, ArrayList<Door> doors){

        this.name = name;
        this.description = description;
        this.items = items;
        this.containers = containers;
        this.doors = doors;
    }

    //Creates a room connected to an existing room with a default door that is open and unlocked
    public Room(String name, String description, Container items, ArrayList<Container> containers, Room neighbor){

        this.name = name;
        this.description = description;
        this.items = items;
        this.containers = containers;
        doors = new ArrayList<>();
        Door temp = new Door("Door",false, true, new Room[]{this,neighbor});
        doors.add(temp);
        neighbor.addDoor(temp);
    }

    //Creates a room connected to an existing room with a custom door state
    public Room(String name, String description, Container items, ArrayList<Container> containers, Room neighbor, Boolean open, Boolean locked){

        this.name = name;
        this.description = description;
        this.items = items;
        this.containers = containers;
        doors = new ArrayList<>();
        Door temp = new Door("Door",open, locked, new Room[]{this,neighbor});
        doors.add(temp);
        neighbor.addDoor(temp);
    }


    public void addDoor(Door door){
        doors.add(door);
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
                                                  