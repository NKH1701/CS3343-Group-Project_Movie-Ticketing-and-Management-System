package Goods;

import java.util.ArrayList;
import java.util.List;

public class Snack extends Goods {
    private static final List<Snack> allSnacks = new ArrayList<>();
    public Snack(String name, double price) {
        super(name, price);
        allSnacks.add(this);
    }
//    @Override
    public static List<Snack> getALlSnacks() {
        return allSnacks;
    }
    
}
