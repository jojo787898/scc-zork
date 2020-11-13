/*
 *	GameMap
 *	Only use for serialization, not for game state
 */

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.util.*;
import java.lang.reflect.Type;

public class GameMap {

    private Set<Room> rooms;

    // Default constructor
    public GameMap(){
        rooms = new HashSet<>();
    }

    // Read
    public GameMap(String path) throws FileNotFoundException {
	readFromFile(path);
    }

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

    public String toString(){
        String out = "Rooms: \n------------\n";
        for (Room room : rooms) {
            out += room.getName() + ": " + room.getDescription() + "\n" + "Contains: \n";
	    out += room.toString();
	    out += "----------\n";
        }
        return out;
    }

    public Room nameToRoom(String name){
        for (Room room : rooms) {
            if (room.getName().equals(name)){
                return room;
            }
        }
        System.out.println("Could not find room " + name);
        return null;
    }

    public void Save() throws IOException {
	try {
            Gson g = new Gson();
	    FileWriter writer = new FileWriter("json_out.txt");
	    String json = g.toJson(this.rooms);

	    writer.write(json);
	    writer.close();
	} catch(Exception e) {
            System.err.printf("GameMap: error writing to \n");
	}
    }

    public void addRoom(Room room) {
        this.rooms.add(room);
    }
}
