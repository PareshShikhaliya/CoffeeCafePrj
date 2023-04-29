package management;

import constant.CoffeeType;
import exception.OutOfStockException;
import inventory.Inventory;
import order.Order;
import product.Coffee;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class CoffeeCafe {
    private Inventory inventory;

    public CoffeeCafe() throws FileNotFoundException {
        inventory = new Inventory("C:\\Users\\pares\\IdeaProjects\\CoffeeCafe\\resource\\inventory.txt");
    }

    public void openCafe() {
        Scanner scanner = new Scanner(System.in);
        Order order = new Order();

        while (true) {
            System.out.println("Menu:");
            int numberOfTypeOfCoffee =1;
            for (CoffeeType type : CoffeeType.values()) {
                System.out.println(numberOfTypeOfCoffee++ + ": " + type);
            }

            System.out.println("Enter the type of coffee you would like to order:");
            String input = scanner.nextLine().toUpperCase();
            CoffeeType type;
            try {
                type = CoffeeType.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid coffee type. Please try again.");
                continue;
            }

            System.out.println("Enter the quantity:");
            int quantity = scanner.nextInt();
            scanner.nextLine();

            if (!inventory.checkStock(type, quantity)) {
                System.out.println("Sorry, we do not have enough " + type + " in stock.");
                continue;
            }

            for (int i = 0; i < quantity; i++) {
                order.addCoffee(new Coffee(type));
                try {
                    inventory.removeStock(type, 1);
                } catch (OutOfStockException e) {
                    System.out.println(e.getMessage());
                    break;
                }
            }

            System.out.println("Order summary:");
            for (Coffee coffee : order.getCoffees()) {
                System.out.println(coffee.toString());
            }
            System.out.println("Total: $" + order.getTotal());

            System.out.println("Would you like to place another order? (y/n)");
            input = scanner.nextLine().toLowerCase();
            if (input.equals("n")) {
                break;
            }
        }

        System.out.println("Thank you for visiting our coffee shop!");
    }
}
