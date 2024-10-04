package TestGoods;

import Goods.Snack;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SnackTest {
    Snack snack1;
    Snack snack2;

    @BeforeEach
    public void setUp() {
        snack1 = new Snack("fist snack", 10);
        snack2 = new Snack("second snack", 20);
    }

    @Test
    public void testGetAll() {
        List<Snack> aLlSnacks = Snack.getALlSnacks();
        Assertions.assertEquals(aLlSnacks.getFirst(), snack1);
        Assertions.assertEquals(aLlSnacks.get(1), snack2);
    }
}
