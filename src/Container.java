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
    private Set<Item> items;

    /* Constructors */
    public Container(){
        this("", "", new HashSet<>());
    }

    public Container(String name, String description){
        this(name, description, new HashSet<>());
    }

    public Container(String name, String description, Set<Item> items){
        this.name = name;
        this.items = items;
        this.description = description;
    }

    /* Edit state */
    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        this.items.remove(item);
    }

    /* API functions*/
    public boolean equals(Container cont_comp) {
	    if(!this.name.equals(cont_comp.getName()) || !this.name.equals(cont_comp.getName())) {
		return false;
	    }
	    if(!this.items.equals(cont_comp.getItems())) {
		return false;
	    }
	    return true;
    }
    
    // Must have exact items, not just items with the same name
    public boolean hasItems(Container cont_comp) {
  	return this.items.containsAll(cont_comp.getItems());
    }

    // getters & setters & toString
    public Set<Item> getItems() {
        return this.items;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        String itemList = "";
        for (Item item : this.items) {
            itemList += item.getName() + ", ";
        }
        return "[container]" + name + ": " + description + "\n" + itemList + "\n";
    }

    public Item searchItem(String s) {
        Item search = null;
        for (Item item : this.items) {
            if (item.getName().equals(s)) {
                search = item;
            }
        }
        return search;
    }
}
