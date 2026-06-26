
/**
 * Write a description of class GroceryList here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.*;
public class GroceryList
{

    public static final int MAX = 50;

    static String[] items = new String[MAX];
    static boolean[] checked = new boolean[MAX];
    static int count = 0;

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        int choice = 0;

        while (choice != 5) {

            showMenu();
            choice = getChoice();

            if (choice == 1) {
                addItem();
            }
            else if (choice == 2) {
                removeItem();
            }
            else if (choice == 3) {
                checkItem();
            }
            else if (choice == 4) {
                printList();
            }
            else if (choice == 5) {
                exitProgram();
            }
            else {
                System.out.println("Invalid choice.");
            }
        }

        input.close();
    }

    // Show menu
    public static void showMenu() {

        System.out.println("\n===== Grocery List =====");
        System.out.println("1. Add Item");
        System.out.println("2. Remove Item (by number)");
        System.out.println("3. Check Off Item (by number)");
        System.out.println("4. Print List");
        System.out.println("5. Exit");
        System.out.print("Enter choice: ");
    }

    // Get menu choice
    public static int getChoice() {

        while (!input.hasNextInt()) {
            input.next();
            System.out.print("Enter a number (1-5): ");
        }

        return input.nextInt();
    }

    // Add item
    public static void addItem() {

        input.nextLine(); // clear buffer

        if (count >= MAX) {
            System.out.println("List is full.");
            return;
        }

        System.out.print("Enter item name: ");
        String name = input.nextLine();

        if (findItem(name) != -1) {
            System.out.println("Item already exists.");
            return;
        }

        items[count] = name;
        checked[count] = false;
        count++;

        System.out.println("Item added.");
    }

    // Remove item by number
    public static void removeItem() {

        if (count == 0) {
            System.out.println("List is empty.");
            return;
        }

        printList();

        System.out.print("Enter item number to remove: ");

        if (!input.hasNextInt()) {
            input.next();
            System.out.println("Invalid input.");
            return;
        }

        int num = input.nextInt();

        if (num < 1 || num > count) {
            System.out.println("Invalid number.");
            return;
        }

        removeAt(num - 1);

        System.out.println("Item removed.");
    }

    // Check item by number
    public static void checkItem() {

        if (count == 0) {
            System.out.println("List is empty.");
            return;
        }

        printList();

        System.out.print("Enter item number to check off: ");

        if (!input.hasNextInt()) {
            input.next();
            System.out.println("Invalid input.");
            return;
        }

        int num = input.nextInt();

        if (num < 1 || num > count) {
            System.out.println("Invalid number.");
            return;
        }

        checked[num - 1] = true;

        System.out.println("Item checked off.");
    }

    // Print list
    public static void printList() {

        if (count == 0) {
            System.out.println("List is empty.");
            return;
        }

        System.out.println("\n===== Your List =====");

        for (int i = 0; i < count; i++) {

            String mark;

            if (checked[i]) {
                mark = "x";
            }
            else {
                mark = "-";
            }

            System.out.println((i + 1) + ". [" + mark + "] " + items[i]);
        }
    }

    // Exit
    public static void exitProgram() {

        System.out.println("\nFinal List:");
        printList();

        System.out.println("\nGoodbye!");
    }

    // Find item (for duplicate checking)
    public static int findItem(String name) {

        for (int i = 0; i < count; i++) {

            if (items[i].equalsIgnoreCase(name)) {
                return i;
            }
        }

        return -1;
    }

    // Remove at index
    public static void removeAt(int index) {

        for (int i = index; i < count - 1; i++) {

            items[i] = items[i + 1];
            checked[i] = checked[i + 1];
        }

        count--;
    }
}