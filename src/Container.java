/*
 *	Container
 */

import com.google.gson.*;

import java.io.*;
import java.util.*;

public class Container {
    private String name;
    private String description;
    private Set<Item> items;

    public Container(String name, String description, Set<Item> items){
        this.name = name;
        this.description = description;
        this.items = items;
    }

    public Container(String name, String description){
        this(name, description, new HashSet<>());
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public Set<Item> getItems() {
        return this.items;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        String name = "Name : " + this.name + "\n";
        String description = "Description : " + this.description + "\n";
        String itemList = "Items : ";
        for (Item item : items) {
            itemList += item.toName() + "\n";
        }
        return name + description + itemList;
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
