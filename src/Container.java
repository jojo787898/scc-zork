/*
 *	Container
 */

import com.google.gson.*;

import java.io.*;
import java.util.*;

public class Container {
    String name;
    Set<Item> items;

    public Container(String name, Set<Item> items){
        this.name = name;
        this.items = items;
    }

    public Container(String name){
        this.name = name;
        this.items = new HashSet<>();
    }

    public void toJSON() throws IOException {
        Gson g = new Gson();
        FileWriter myWriter = new FileWriter(new File("Containers", this.name + ".container"));
        myWriter.write(g.toJson(this));
        myWriter.close();

        for (Item item:items) {
            item.toJSON();
        }
    }
}
