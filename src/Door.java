public class Door {

    String description;
    Boolean locked;
    Boolean open;
    Room[] rooms;

    public Door(String description, Boolean locked, Boolean open, Room[] rooms){
        this.description = description;
        this.locked = locked;
        this.open = open;
        this.rooms = rooms;
    }
}
