public class Door {

    String description;
    Boolean locked;
    Boolean open;
    Room room1;
    Room room2;

    public Door(String description, Boolean locked, Boolean open, Room room1, Room room2){
        this.description = description;
        this.locked = locked;
        this.open = open;
        this.room1 = room1;
        this.room2 = room2;
    }
}
