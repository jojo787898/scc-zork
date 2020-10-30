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

    public Room(String name, String description, Container items, ArrayList<Container> containers, ArrayList<Door> doors){

        this.name = name;
        this.description = description;
        this.items = items;
        this.containers = containers;
        this.doors = doors;
    }


    public void toJSON() throws IOException {
        Gson g = new Gson();
        FileWriter myWriter = new FileWriter(new File("Map", this.name + ".room"));
        myWriter.write(g.toJson(this));
        myWriter.close();
    }
}
