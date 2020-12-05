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
    
    // Must have exact items, not just items with the same name
    // O(n^2) search time, really bad, think of better way
    /*TODO Compare names and description strings*/
    public boolean hasItems(Container cont_comp) {
	int n = cont_comp.getItems().size();
	boolean checked_items[] = new boolean[n];
	for(int i = 0; i < n; i++) {
            checked_items[i] = false;
	}
	for(int i = 0; i < n; i++) {
            for(int j = 0; j < this.contained_items.size(); j++) {
                if(!checked_items[j] && cont_comp.getItems().get(j).equals(this.contained_items.get(i))) { // Match, mark as checked and stop early
                    checked_items[j] = true;
		    break;
		}
                if(j == this.contained_items.size() - 1) { // Got to end, missing item
                    return false;
		}
	    }
	}
        return true;
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
