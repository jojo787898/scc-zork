import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Item {
    String name;

    public void toJSON() throws IOException {

        Gson g = new Gson();
        FileWriter myWriter = new FileWriter(new File("Items", this.name + ".item"));
        myWriter.write(g.toJson(this));
        myWriter.close();
    }

    public Item(String name){
        this.name = name;
    }

    public String toString(){
        return this.name;
    }
}
