public class Item {
    //fields
    String name;
    String description;
    Container location;

    public Item(String name, String description, Container location) {
        this.name = name;
        this.description = description;
        this.location = location;
    }

    //print
    public String toString() {
        return "Item is " + name + ". " + description + ". " + location;
    }
}
