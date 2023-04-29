import java.util.*;

class Order {
    private List<Coffee> coffees;

    public Order() {
        coffees = new ArrayList<>();
    }
    public List<Coffee> getCoffees() {
        return coffees;
    }

    public void addCoffee(Coffee coffee) {
        coffees.add(coffee);
    }

    public double getTotal() {
        double total = 0.0;
        for (Coffee coffee : coffees) {
            total += coffee.getPrice();
        }
        return total;
    }
}
 class Coffee {
    private CoffeeType type;
    private double price;

    public Coffee(CoffeeType type) {
        this.type = type;
        switch (type) {
            case ESPRESSO:
                price = 2.0;
                break;
            case LATTE:
                price = 3.0;
                break;
            case CAPPUCCINO:
                price = 3.0;
                break;
            case AMERICANO:
                price = 2.5;
                break;
            default:
                throw new IllegalArgumentException("Invalid coffee type.");
        }
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return type.toString() + " ($" + price + ")";
    }
}



 class Inventory {
    private Map<CoffeeType, Integer> stock;

    public Inventory() {
        stock = new EnumMap<>(CoffeeType.class);
        for (CoffeeType type : CoffeeType.values()) {
            stock.put(type, 10);
        }
    }

    public boolean checkStock(CoffeeType type, int quantity) {
        return stock.get(type) >= quantity;
    }

    public void removeStock(CoffeeType type, int quantity) throws OutOfStockException {
        int currentStock = stock.get(type);
        if (currentStock >= quantity) {
            stock.put(type, currentStock - quantity);
        } else {
            throw new OutOfStockException(type + " is out of stock.");
        }
    }
}
 enum CoffeeType {
    ESPRESSO,
    LATTE,
    CAPPUCCINO,
    AMERICANO
}
 class OutOfStockException extends Exception {
    public OutOfStockException(String message) {
        super(message);
    }
}


 class CoffeeCafe {
    private Inventory inventory;

    public CoffeeCafe() {
        inventory = new Inventory();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        Order order = new Order();

        while (true) {
            System.out.println("Menu:");
            for (CoffeeType type : CoffeeType.values()) {
                System.out.println(type + ": " + type);
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



public class MainApp {
    public static void main(String[] args)
    {
        new CoffeeCafe().start();
    }
}



