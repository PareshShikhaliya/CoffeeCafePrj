package inventory;

import constant.CoffeeType;
import exception.OutOfStockException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.EnumMap;
import java.util.Map;
import java.util.Scanner;

public class Inventory {
    private Map<CoffeeType, Integer> stock;

    public Inventory(String fileName) throws FileNotFoundException {
        stock = new EnumMap<>(CoffeeType.class);
        Scanner scanner = new Scanner(new File(fileName));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] tokens = line.split(",");
            CoffeeType type = CoffeeType.valueOf(tokens[0]);
            int quantity = Integer.parseInt(tokens[1]);
            stock.put(type, quantity);
        }
        scanner.close();
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
