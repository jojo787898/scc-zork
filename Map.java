import com.google.gson.Gson;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Map {
    ArrayList<Room> rooms;


    public Map(String path) throws FileNotFoundException {
        rooms = new ArrayList<>();
        loadFromFile(path);
    }

    public Map(ArrayList<Room> rooms){
        this.rooms = rooms;
    }

    public void loadFromFile(String path) throws FileNotFoundException {
        Gson g = new Gson();

        File file = new File(path);

        Scanner scan = new Scanner(file);

        String json = "";
        while(scan.hasNext()){
            json += scan.nextLine();
        }
        rooms.add(g.fromJson(json,Room.class));
    }

    public String toString(){
        String out = "";
        for (Room room:rooms) {
            out += room.name + ": " + room.description + "\n";
        }
        return out;
    }
}
