/*
 *	GameMap
 *	Tracks the provides functions to print current room (and connected),
 *	interact with items in room, 
 */

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.util.*;
import java.lang.reflect.Type;

public class GameMap {

    /* Members */
    private Set<Room> rooms; // TODO use map (name -> room) key/value pairings

    /* Constructors */
    public GameMap() {
        this.rooms = new HashSet<>();
    }

    public GameMap(String path) throws FileNotFoundException {
        readFromFile(path);
    }

    /* serializers*/
    // Have to do some funky shtuff to serialize a Set of rooms directly
    public void readFromFile(String path) {
        try {
            Gson g = new Gson();
            Type datasetListType = new TypeToken<Set<Room>>(){}.getType();
            String json = "";
            Scanner scan = new Scanner(new File(path));
            while(scan.hasNextLine()) {
                json += scan.nextLine();
            }
            scan.close();
            this.rooms = g.fromJson(json, datasetListType);
        } catch(Exception e) {
            System.err.printf("GameMap: error reading in from %s\n", path);
        }
    }

    // default  to json_out.txt, can change name if like
    public void save() throws IOException {
	    save("json_out.txt");
    }
    
    public void save(String path) throws IOException {
        try {
            Gson g = new Gson();
            FileWriter writer = new FileWriter(path);
            String json = g.toJson(this.rooms);

            writer.write(json);
            writer.close();
        } catch(Exception e) {
            System.err.printf("GameMap: error writing to %s\n", path);
        }
    }

    /* Edit state functions */
    public void addRoom(Room room) {
        this.rooms.add(room);
    }

    // @param:	room name to change into
    // @return:	new room, null if failed
    //public Room changeRoom(String room_name)
    //public Room changeRoom(String room_name, Container player_inv)

    /* getters, setters, toString */
    // TODO ugly fix it
    public String toString() {
        String out = "Rooms: \n------------\n";
        for (Room room : rooms) {
            out += room.getName() + ": " + room.getDescription() + "\n" + "Contains: \n";
            out += room.toString();
            out += "----------\n";
        }
        return out;
    }
}
