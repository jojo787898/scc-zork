import com.google.gson.Gson;
import java.io.File;
import java.io.FileFilter;
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

        for (File f:file.listFiles()) {

            Scanner scan = new Scanner(f);
            if(scan.hasNext()){
                rooms.add(g.fromJson(scan.nextLine(),Room.class));
            }
        }
    }

    public String toString(){
        String out = "";
        for (Room room:rooms) {
            out += room.name + ": " + room.description + "\n";
        }
        return out;
    }

    public Room nameToRoom(String name){
        for (Room room:rooms) {
            if (room.name.equals(name)){
                return room;
            }
        }
        System.out.println("Could not find room");
        return null;
    }
}
