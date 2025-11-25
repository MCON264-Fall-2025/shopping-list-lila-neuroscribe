package exercises.shopping_app;

import edu.touro.mcon264.apps.collections.ArrayBasedList;
import edu.touro.mcon264.apps.collections.ListInterface;

import java.util.Iterator;
import java.util.Scanner;

/**
 * Creates a ListInterface<ShoppingItem> instance.
 * Has a main method that runs a console application.
 * Provides a simple text-based menu to:
 * Add items (in sorted order).
 * View the current list.
 * “Shop” the next item on the list.
 * Exit the program.
 */

public class ShoppingListApp {
    ListInterface<ShoppingItem> shoppingList;

    ShoppingListApp(ListInterface<ShoppingItem> list) {
        this.shoppingList = list;
    }
    /**
     * Inserts the given item into the list so that the list remains sorted
     * by aisle and then item name.
     */
    public static void insertSorted(ListInterface<ShoppingItem> list, ShoppingItem item) {
            if (list.isEmpty()) {
                list.add(0, item);
                return;
            }
            for (int i = 0; i < list.size(); i++) {
                 ShoppingItem currentItem = list.get(i);
                 if (item.compareTo(currentItem) < 0) {
                     list.add(i, item);
                     return;
                 }
             }
            list.add(list.size(), item);
    }

    /**
     * Returns the "next" item to shop and removes it from the list.
     * If the list is empty, returns null.
     */
    public static ShoppingItem shopNext(ListInterface<ShoppingItem> list) {
        if (list.isEmpty()) {
            return null;
        }
        return list.remove(0);
    }

    static void printMenu() {
        System.out.println("Shopping List Menu:");
        System.out.println("1. Add item");
        System.out.println("2. View shopping list");
        System.out.println("3. Shop next item");
        System.out.println("0. Exit");
    }
    static void printList(ListInterface<ShoppingItem> list) {
        if (list.isEmpty()) {
            System.out.println("Shopping list is empty.");
            return;
        }
        Iterator<ShoppingItem> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println("- " + iterator.next());
        }
    }

    public static void main(String[] args) {
        ListInterface<ShoppingItem> shoppingList = new ArrayBasedList<>();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.print("Enter aisle: ");
                    String aisle = scanner.nextLine().trim();
                    System.out.print("Enter item name: ");
                    String name = scanner.nextLine().trim();
                    ShoppingItem newItem = new ShoppingItem(aisle, name);
                    insertSorted(shoppingList, newItem);
                    System.out.println("Added: " + newItem);
                    break;
                case "2":
                    System.out.println("Current shopping list:");
                    printList(shoppingList);
                    break;
                case "3":
                    ShoppingItem next = shopNext(shoppingList);
                    if (next == null) {
                        System.out.println("All done! No items left to shop.");
                    } else {
                        System.out.println("Next item to buy: " + next);
                    }
                    break;
                case "0":
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Unknown option. Please try again.");
            }
            System.out.println();
        }
    }

}
