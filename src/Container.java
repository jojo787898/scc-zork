import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

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
