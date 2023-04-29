package product;

import constant.CoffeeType;

public class Coffee {
    private CoffeeType type;
    private double price;

    public Coffee(CoffeeType type) {
        this.type = type;
        switch (type) {
            case ESPRESSO:
                price = 2.0;
                break;
            case LATTE:
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
