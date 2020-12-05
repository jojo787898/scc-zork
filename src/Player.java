/*
 *	Player
 */
import java.util.*;

public class Player {
    private Container inventory;
    private Room location;
    private int health;

    public Player(Room location) {
        this.inventory = new Container("Inventory", "Your Item", new ArrayList<>());
        this.location = location;
        this.health = 3;
    }

    public void take(Container container, Item item) {
        inventory.addItem(item);
        container.removeItem(item);
    }

    public void drop(Container container, Item item) {
        container.addItem(item);
        inventory.removeItem(item);
    }

    public void examine(Item item) {
        System.out.println(item.toString());
    }

    public Room getRoom() {
        return this.location;
    }

    public void move(Room next) {
        this.location = next;
    }

    public void printInventory() {
        for (Item item : inventory.getItems()) {
            examine(item);
        }
    }

    public Container getInventory() {
        return this.inventory;
    }
}