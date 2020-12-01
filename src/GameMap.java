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
    private Map<String, Room> rooms;

    /* Constructors */
    public GameMap() {
        this.rooms = new HashMap<>();
    }

    public GameMap(String path) throws FileNotFoundException {
        readFromFile(path);
    }

    /* serializers*/
    // Have to do some funky shtuff to serialize a Map of rooms directly
    public void readFromFile(String path) {
        try {
            Gson g = new Gson();
            Type datasetListType = new TypeToken<Map<String, Room>>(){}.getType();
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
        this.rooms.put(room.getName(), room);
    }

    // @param:	room name to change into
    // @return:	new room, (same if can't change), no error checking
    public Room changeRoom(String start_room, String dest_room) {
	    if(canChangeRoom(start_room, dest_room)) {
		return rooms.get(dest_room);
	    } else {
		return rooms.get(start_room);
	    }
    }
    public Room changeRoom(String start_room, String dest_room, Container player_inv) {
	    if(canChangeRoom(start_room, dest_room)) { // already unlocked
		return rooms.get(dest_room);
	    } else if(!rooms.get(start_room).is_connected(dest_room)) { // not connected
		return rooms.get(start_room);
	    } else if(rooms.get(dest_room).canAccess(player_inv)) { // check if have valid items and unlock for later use
		rooms.get(dest_room).unlock_room();
		return rooms.get(dest_room);    	
	    } else { //cannot access
	        return rooms.get(start_room);
	    }
    }

    // Helpter to changeRoom
    public boolean canChangeRoom(String start_room, String dest_room) {
	    if(!rooms.get(start_room).is_connected(dest_room)) { // not connected
		return false;
	    } else if(rooms.get(dest_room).canAccess()) { // is connected and can access
		return true;
	    } else { // cannot access dest
		    return false;
	    }
    }

    /* getters, setters, toString */
    // TODO ugly fix it
    public String toString() {
        String out = "Rooms: \n------------\n";
        for (String room_name : rooms.keySet()) {
            out += room_name + ": " + rooms.get(room_name).getDescription() + "\n" + "Contains: \n";
            out += rooms.get(room_name).toString();
            out += "----------\n";
        }
        return out;
    }

    public Map<String, Room> getRooms() {
        return this.rooms;
    }
}
