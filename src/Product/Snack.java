package Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Snack implements Product {
    private String name;
    private double price;
    private String portion;
    private static final List<Snack> allSnacks = new ArrayList<>();

    public Snack(String name, double price, String portion) {
        this.name = name;
        this.price = price;
        this.portion = portion;
        allSnacks.add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Snack snack = (Snack) o;
        return Double.compare(price, snack.price) == 0 && Objects.equals(name,
                snack.name) && Objects.equals(portion, snack.portion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, portion);
    }

    //    @Override
    public static List<Snack> getALlSnacks() {
        return allSnacks;
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
}
