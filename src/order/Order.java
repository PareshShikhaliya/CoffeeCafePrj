package order;

import product.Coffee;

import java.util.ArrayList;
import java.util.List;

public class Order {
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
