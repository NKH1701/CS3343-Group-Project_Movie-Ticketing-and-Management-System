package test.product_test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import release.exception.ExProductNotFound;
import release.product.Drink;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Test for Drink class in package Product
 */
public class DrinkTest {
    private final List<Drink> testdrinkList = new ArrayList<>();

    /**
     * Set up the test drink list
     */
    @BeforeEach
    void setup() {
        testdrinkList.add(new Drink("Drink 1", 10, "10ml"));
        testdrinkList.add(new Drink("Drink 2", 20, "20L"));
    }

    /**
     * Clear the test drink list and list in class Drink after each test
     */
    @AfterEach
    void clear() throws NoSuchFieldException, IllegalAccessException {
        Field field = Drink.class.getDeclaredField("allDrink");
        field.setAccessible(true);
        Object fieldValue = field.get(Drink.class);
        if (fieldValue instanceof List){
            ((List<?>) fieldValue).clear();
        }
        testdrinkList.clear();
    }

    /**
     * Test get name of the drink
     */
    @Test
    void testGetName() {
        String name = testdrinkList.getFirst().getName();
        Assertions.assertEquals("Drink 1", name);
    }

    /**
     * Test set name of the drink
     */
    @Test
    void testGetPortion() {
        String portion = testdrinkList.getFirst().getPortion();
        Assertions.assertEquals("10ml", portion);
    }

    /**
     * Test set portion of the drink
     */
    @Test
    void testSetPortion() {
        testdrinkList.getFirst().setPortion("20ml");
        String portion = testdrinkList.getFirst().getPortion();
        Assertions.assertEquals("20ml", portion);
    }

    /**
     * Test get price of the drink
     */
    @Test
    void testGetPrice() {
        double price = testdrinkList.getFirst().getPrice();
        Assertions.assertEquals(10, price);
    }

    /**
     * Test get all drinks
     */
    @Test
    void testGetAllDrinks() {
        List<Drink> allDrinks = Drink.getAllDrinks();
        Assertions.assertEquals(allDrinks, testdrinkList);
    }

    @Test
    void testToString() {
        Drink drink = testdrinkList.getFirst();
        String expected = "Name: Drink 1, Portion: 10ml, Price: 10.0";
        Assertions.assertEquals(expected, drink.toString());
    }

    /**
     * Test search drink
     */
    @Test
    void testSearchDrink() throws ExProductNotFound {
        Drink drink = testdrinkList.getFirst();
        Drink actualDrink = Drink.searchDrink("Drink 1");
        Assertions.assertEquals(drink, actualDrink);
    }

    /**
     * Test search drink that does not exist
     */
    @Test
    void testSearchDrinkNotFound() {
        Exception exception = Assertions.assertThrows(ExProductNotFound.class,
                () -> Drink.searchDrink("Drink not exist"));
        Assertions.assertEquals("[Exception] Drink Drink not exist not found", exception.getMessage());
    }

    /**
     * Test equals method
     */
    @Test
    void testEquals() {
        Drink drink1 = testdrinkList.getFirst();
        Drink drink2 = new Drink("Drink 1", 10, "10ml");
        Assertions.assertTrue(drink1.equals(drink2));
        Assertions.assertEquals(drink1.hashCode(), drink2.hashCode());
    }

    /**
     * Test equals method with different name of drink
     */
    @Test
    void testEquals_DifferentName() {
        Drink drink1 = testdrinkList.getFirst();
        Drink drink2 = new Drink("Drink 2", 10, "10ml");
        Assertions.assertFalse(drink1.equals(drink2));
        Assertions.assertNotEquals(drink1.hashCode(), drink2.hashCode());
    }

    /**
     * Test equals method with different price of drink
     */
    @Test
    void testEquals_DifferentPrice() {
        Drink drink1 = testdrinkList.getFirst();
        Drink drink2 = new Drink("Drink 1", 20, "10ml");
        Assertions.assertFalse(drink1.equals(drink2));
        Assertions.assertNotEquals(drink1.hashCode(), drink2.hashCode());
    }

    /**
     * Test equals method with different portion of drink
     */
    @Test
    void testEquals_DifferentPortion() {
        Drink drink1 = testdrinkList.getFirst();
        Drink drink2 = new Drink("Drink 1", 10, "20ml");
        Assertions.assertFalse(drink1.equals(drink2));
        Assertions.assertNotEquals(drink1.hashCode(), drink2.hashCode());
    }

    /**
     * Test equals method with null
     */
    @Test
    void testEquals_Null() {
        Drink drink1 = testdrinkList.getFirst();
        Assertions.assertFalse(drink1.equals(null));
        Assertions.assertNotEquals(0, drink1.hashCode());
    }

    /**
     * Test equals method with different class
     */
    @Test
    void testEquals_DifferentClass() {
        Drink drink1 = testdrinkList.getFirst();
        Object obj = new Object();
        Assertions.assertFalse(drink1.equals(obj));
        Assertions.assertNotEquals(drink1.hashCode(), obj.hashCode());
    }

    /**
     * Test equals method on the same instance of drink
     */
    @Test
    void testEquals_DrinkEqualsItself() {
        Drink drink1 = testdrinkList.getFirst();
        Assertions.assertTrue(drink1.equals(drink1));
    }

}

