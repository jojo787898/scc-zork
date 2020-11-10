/*
 *	Item
 */

import com.google.gson.*;
import java.io.*;

public class Item {
    //fields
    String name;
    String description;
    Container location;

    public Item(String name){
        this.name = name;
    }

    public Item(String name, String description, Container location) {
        this.name = name;
        this.description = description;
        this.location = location;
    }

    public void toJSON() throws IOException {

        Gson g = new Gson();
        FileWriter myWriter = new FileWriter(new File("Items", this.name + ".item"));
        myWriter.write(g.toJson(this));
        myWriter.close();
    }

    //print
    public String toString() {
        return "Item is " + name + ". " + description + ". " + location;
    }
}
