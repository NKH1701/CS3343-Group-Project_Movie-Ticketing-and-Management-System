package test.product_test;

import org.junit.jupiter.api.AfterEach;
import release.product.Snack;
import release.exception.ExProductNotFound;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Test for Snack class in package Product
 */
public class SnackTest {
    Snack snack1;
    Snack snack2;

    /**
     * Set up the test snacks before each test
     */
    @BeforeEach
    public void setUp() {
        snack1 = new Snack("fist snack", 10, "10g");
        snack2 = new Snack("second snack", 20, "20g");
    }
    
    /**
     * Clear the list in class Snack after each test
     */
    @AfterEach
    void clear() throws NoSuchFieldException, IllegalAccessException {
        Field field = Snack.class.getDeclaredField("allSnacks");
        field.setAccessible(true);
        Object fieldValue = field.get(Snack.class);
        if (fieldValue instanceof List){
            ((List<?>) fieldValue).clear();
        }
    }

    /**
     * Test equals method with the same instance of snack
     */
    @Test
    public void testEquals_SameSnacksInstance() {
        Assertions.assertTrue(snack1.equals(snack1));
        Assertions.assertEquals(snack1.hashCode(), snack1.hashCode());
    }

    /**
     * Test equals method with two same snacks
     */
    @Test
    public void testEquals_TwoSameSnacks() {
        Snack snack3 = new Snack("fist snack", 10, "10g");
        Assertions.assertTrue(snack1.equals(snack3));
        Assertions.assertEquals(snack1.hashCode(), snack3.hashCode());
    }
    
    /**
     * Test equals method with two different snacks
     */
    @Test
    public void testEquals_TwoDifferentSnacks() {
        Assertions.assertFalse(snack1.equals(snack2));
        Assertions.assertNotEquals(snack1.hashCode(), snack2.hashCode());
    }

    /**
     * Test equals method with two different snacks with the same price
     */
    @Test
    public void testEquals_TwoDifferentSnacksWithSamePrice() {
        Snack snack3 = new Snack("second snack", 10, "20g");
        Assertions.assertFalse(snack1.equals(snack3));
        Assertions.assertNotEquals(snack1.hashCode(), snack3.hashCode());
    }

    /**
     * Test equals method with two different snacks with the same portion
     */
    @Test
    public void testEquals_TwoDifferentSnacksWithSamePortion() {
        Snack snack3 = new Snack("second snack", 20, "10g");
        Assertions.assertFalse(snack2.equals(snack3));
        Assertions.assertNotEquals(snack2.hashCode(), snack3.hashCode());
    }

    /**
     * Test equals method with null
     */
    @Test
    public void testEquals_Null() {
        Assertions.assertFalse(snack1.equals(null));
        Assertions.assertNotEquals(0, snack1.hashCode());
    }
    
    /**
     * Test equals method with different class
     */
    @Test
    public void testEquals_DifferentClass() {
        Object obj = new Object();
        Assertions.assertFalse(snack1.equals(obj));
        Assertions.assertNotEquals(snack1.hashCode(), obj.hashCode());
    }

    /**
     * Test getName method
     */
    @Test
    public void testGetName() {
        Assertions.assertEquals("fist snack", snack1.getName());
    }

    /**
     * Test getPortion method
     */
    @Test
    public void testGetPortion() {
        Assertions.assertEquals("10g", snack1.getPortion());
    }

    /**
     * Test setPortion method
     */
    @Test
    public void testSetPortion() {
        String portionSet = snack1.setPortion("20g");
        Assertions.assertEquals("20g", portionSet);
    }

    /**
     * Test getPrice method
     */
    @Test
    public void testGetPrice() {
        Assertions.assertEquals(10, snack1.getPrice());
    }
    

    /**
     * Test getAllSnacks method
     */
    @Test
    public void testGetAll() {
        List<Snack> allSnacks = Snack.getAllSnacks();
        Assertions.assertEquals(List.of(snack1, snack2), allSnacks);
    }
    
    /**
     * Test searchProduct method
     */
    @Test
    public void testSearchSnack() throws ExProductNotFound {
        Snack acutalSnack = Snack.searchSnack("fist snack");
        Assertions.assertEquals(snack1, acutalSnack);
    }
    
    /**
     * Test searchSnack method with snack that does not exist
     */
    @Test
    public void testSearchSnackNotFound() {
        Exception exception = Assertions.assertThrows(ExProductNotFound.class, () -> Snack.searchSnack("snack not exist"));
        Assertions.assertEquals("[Exception] Snack snack not exist not found", exception.getMessage());
    }
    
    /**
     * Test toString method
     */
    @Test
    public void testToString() {
        String expected = "Name: fist snack, Portion: 10g, Price: 10.0";
        Assertions.assertEquals(expected, snack1.toString());
    }
    
@Test
    public void testFormattedToString() {
        String expected = String.format("%-20s%2s%-5s%2s$%-4.1f%1s", "fist snack", " ", "10g", " ", 10.0," ");
        Assertions.assertEquals(expected, snack1.formattedToString());
    }
}
