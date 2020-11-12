/*
 *	Item
 */

import com.google.gson.*;
import java.io.*;

public class Item {
    //fields
    private String name;
    private String description;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String toName() {
        return this.name;
    }

    public void toJSON() throws IOException {
        Gson g = new Gson();
        FileWriter myWriter = new FileWriter(new File("Items", this.name + ".item"));
        myWriter.write(g.toJson(this));
        myWriter.close();
    }

    //print
    public String toString() {
        String total = "Name : " + toName() + "\nDescription : " + this.description;
        return total;
    }
}
