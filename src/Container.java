/*
 *	Container
 */

import com.google.gson.*;

import java.io.*;
import java.util.*;

public class Container {

    /* Members */
    private String name;
    private String description;
    private ArrayList<Item> contained_items;

    /* Constructors */
    public Container(){
        this("", "", new ArrayList<>());
    }

    public Container(String name, String description){
        this(name, description, new ArrayList<>());
    }

    public Container(String name, String description, ArrayList<Item> items){
        this.name = name;
        this.contained_items = items;
        this.description = description;
    }

    /* Edit state */
    public void addItem(Item item) {
        this.contained_items.add(item);
    }

    public void removeItem(Item item) {
        this.contained_items.remove(item);
    }

    /* API functions*/
    public boolean equals(Container cont_comp) {
        if(!this.name.equals(cont_comp.getName()) || !this.name.equals(cont_comp.getName())) {
            return false;
        }
        if(!this.contained_items.equals(cont_comp.getItems())) {
            return false;
        }
        return true;
    }
    
    // Must have exact items, not just items with the same name
    public boolean hasItems(Container cont_comp) {
        return this.contained_items.containsAll(cont_comp.getItems());
    }

    // getters & setters & toString
    public ArrayList<Item> getItems() {
        return this.contained_items;
    }

    public String getName() {
        return this.name;
    }

    // @return:	only item list
    public String toString() {
        return this.contained_items.toString();
    }
}
