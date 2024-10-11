package TestProduct;

import Product.Snack;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SnackTest {
    Snack snack1;
    Snack snack2;

    @BeforeEach
    public void setUp() {
        snack1 = new Snack("fist snack", 10, "10g");
        snack2 = new Snack("second snack", 20, "20g");
    }

    @Test
    public void testEqualsTwoSameSnacks() {
        Assertions.assertTrue(snack1.equals(snack1));
        Assertions.assertEquals(snack1.hashCode(), snack1.hashCode());
    }

    @Test
    public void testEqualsTwoDifferentSnacks() {
        Assertions.assertFalse(snack1.equals(snack2));
        Assertions.assertNotEquals(snack1.hashCode(), snack2.hashCode());
    }

    @Test
    public void testEqualsTwoDifferentSnacksWithSameName() {
        Snack snack3 = new Snack("fist snack", 20, "20g");
        Assertions.assertFalse(snack1.equals(snack3));
        Assertions.assertNotEquals(snack1.hashCode(), snack3.hashCode());
    }

    @Test
    public void testEqualsTwoDifferentSnacksWithSamePrice() {
        Snack snack3 = new Snack("second snack", 10, "20g");
        Assertions.assertFalse(snack1.equals(snack3));
        Assertions.assertNotEquals(snack1.hashCode(), snack3.hashCode());
    }

    @Test
    public void testEqualsTwoDifferentSnacksWithSamePortion() {
        Snack snack3 = new Snack("second snack", 20, "10g");
        Assertions.assertFalse(snack1.equals(snack3));
        Assertions.assertNotEquals(snack1.hashCode(), snack3.hashCode());
    }

    @Test
    public void testEqualsNull() {
        Assertions.assertFalse(snack1.equals(null));
        Assertions.assertNotEquals(snack1.hashCode(), 0);
    }
    
    @Test
    public void testEqualsDifferentClass() {
        Object obj = new Object();
        Assertions.assertFalse(snack1.equals(obj));
        Assertions.assertNotEquals(snack1.hashCode(), obj.hashCode());
    }

    @Test
    public void testGetName() {
        Assertions.assertEquals("fist snack", snack1.getName());
    }

    @Test
    public void testGetPortion() {
        Assertions.assertEquals("10g", snack1.getPortion());
    }

    @Test
    public void testSetPortion() {
        String portionSet = snack1.setPortion("20g");
        Assertions.assertEquals("20g", portionSet);
    }

    @Test
    public void testGetPrice() {
        Assertions.assertEquals(10, snack1.getPrice());
    }

    @Test
    public void testSetPrice() {
        double priceSet = snack1.setPrice(20);
        Assertions.assertEquals(20, priceSet);
    }

    @Test
    public void testGetAll() {
        List<Snack> aLlSnacks = Snack.getALlSnacks();
        Assertions.assertEquals(aLlSnacks.getFirst(), snack1);
        Assertions.assertEquals(aLlSnacks.get(1), snack2);
    }
}
