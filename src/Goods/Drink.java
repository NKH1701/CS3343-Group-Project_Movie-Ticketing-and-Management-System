package Goods;

import java.util.ArrayList;
import java.util.List;

public class Drink extends Goods {
    private static final List<Drink> allDrink = new ArrayList<>();
    public Drink(String name, double price) {
        super(name, price);
        allDrink.add(this);
    }

    public static List<Drink> getALlDrinks() {
        return allDrink;
    }
}
