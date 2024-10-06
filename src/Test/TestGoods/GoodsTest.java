package TestGoods;

import Goods.Goods;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class GoodsTest {

    @Test
    void testGetName() {
        Goods goods = new Goods("Goods 1", 10);
        Assertions.assertEquals("Goods 1", goods.getName());
    }

    @Test
    void testGetPrice() {
        Goods goods = new Goods("Goods 1", 10);
        Assertions.assertEquals(10, goods.getPrice());
    }

    @Test
    void testSetPrice() {
        Goods goods = new Goods("Goods 1", 10);
        Assertions.assertEquals(20, goods.setPrice(20));
    }

    /*
     * Test equals method with two same goods
     */
    @Test
    void testEqualsTwoSameGoods() {
        Goods goods1 = new Goods("Goods 1", 10);
        Goods goods2 = new Goods("Goods 1", 10);
        Assertions.assertTrue(goods1.equals(goods2));
    }

    /*
     * Test equals method with two different goods
     */
    @Test
    void testEqualsTwoDifferentGoods() {
        Goods goods1 = new Goods("Goods 1", 10);
        Goods goods2 = new Goods("Goods 2", 20);
        Assertions.assertFalse(goods1.equals(goods2));
    }

    /*
     * Test equals method with two different goods with same name
     */
    @Test
    void testEqualsTwoDifferentGoodsWithSameName() {
        Goods goods1 = new Goods("Goods 1", 10);
        Goods goods2 = new Goods("Goods 1", 20);
        Assertions.assertFalse(goods1.equals(goods2));
    }

    /*
     * Test equals method with two different goods with same price
     */
    @Test
    void testEqualsTwoDifferentGoodsWithSamePrice() {
        Goods goods1 = new Goods("Goods 1", 10);
        Goods goods2 = new Goods("Goods 2", 10);
        Assertions.assertFalse(goods1.equals(goods2));
    }

    /*
     * Test equals method that goods equals itself
     */
    @Test
    void testEqualsGoodsEqualsItself() {
        Goods goods1 = new Goods("Goods 1", 10);
        Assertions.assertTrue(goods1.equals(goods1));
    }

    /*
     * Test equals method with null
     */
    @Test
    void testEqualsNull() {
        Goods goods1 = new Goods("Goods 1", 10);
        Assertions.assertFalse(goods1.equals(null));
    }

    /*
     * Test equals method with different class
     */
    @Test
    void testEqualsDifferentClass() {
        Goods goods1 = new Goods("Goods 1", 10);
        Object obj = new Object();
        Assertions.assertFalse(goods1.equals(obj));
    }

    /*
     * Test hashCode method with two same goods
     */
    @Test
    void testHashCode() {
        Goods goods1 = new Goods("Goods 1", 10);
        Goods goods2 = new Goods("Goods 1", 10);
        Assertions.assertEquals(goods1.hashCode(), goods2.hashCode());
    }
    
}
