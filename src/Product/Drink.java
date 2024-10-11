package Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Drink implements Product {
    private final String name;
    private double price;
    private String portion;
    private static final List<Drink> allDrink = new ArrayList<>();

    public Drink(String name, double price, String portion) {
        this.name = name;
        this.price = price;
        this.portion = portion;
        allDrink.add(this);
    }

    public static List<Drink> getALlDrinks() {
        return allDrink;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPortion() {
        return portion;
    }

    @Override
    public String setPortion(String portion) {
        this.portion = portion;
        return getPortion();
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public double setPrice(double price) {
        this.price = price;
        return getPrice();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Drink drink = (Drink) o;
        return Double.compare(price, drink.price) == 0 && Objects.equals(name,
                drink.name) && Objects.equals(portion, drink.portion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, portion);
    }
}
