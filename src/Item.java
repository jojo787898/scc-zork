/*
 *	Item
 */

import com.google.gson.*;
import java.io.*;

public class Item {

    private String name;
    private String description;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        String total = "Name : " + getName() + "\nDescription : " + this.description;
        return total;
    }
}
