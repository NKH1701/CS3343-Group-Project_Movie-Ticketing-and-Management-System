package TestGoods;

import Goods.Drink;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class DrinkTest {
    private final List<Drink> testdrinkList = new ArrayList<>();

    @BeforeEach
    void setup() {
        testdrinkList.add(new Drink("Drink 1", 10));
        testdrinkList.add(new Drink("Drink 2", 20));
    }

    @AfterEach
    void clear() {
        testdrinkList.clear();
    }

    @Test
    void testGetALl() {
        List<Drink> drinkList = Drink.getALlDrinks();
        for (int i = 0; i < testdrinkList.size(); i++) {
            Assertions.assertEquals(testdrinkList.get(i), drinkList.get(i));
        }

    }
}

