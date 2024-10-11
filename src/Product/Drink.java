package Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Drink class that implements Product interface
 */
public class Drink implements Product {
    private final String name;
    private double price;
    private String portion;
    private static final List<Drink> allDrink = new ArrayList<>();

    /**
     * Constructor for Drink class, add the drink to the list of all drinks constructed
     * @param name  name of the drink
     * @param price  price of the drink
     * @param portion  portion of the drink
     */
    public Drink(String name, double price, String portion) {
        this.name = name;
        this.price = price;
        this.portion = portion;
        allDrink.add(this);
    }
    
    /**
     * get all drinks constructed in a list
     * @return the list of all drinks constructed
     */
    public static List<Drink> getALlDrinks() {
        return allDrink;
    }
    
    /**
     * get the name of the drink
     * @return the name of the drink
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * get the portion of the drink
     * @return the portion of the drink
     */
    @Override
    public String getPortion() {
        return portion;
    }

    /**
     * set the portion of the drink, and return the portion set
     * @param portion : portion of the drink
     * @return the portion of the drink set
     */
    @Override
    public String setPortion(String portion) {
        this.portion = portion;
        return getPortion();
    }

    /**
     * get the price of the drink
     * @return the price of the drink
     */
    @Override
    public double getPrice() {
        return price;
    }

    /**
     * set the price of the drink, and return the price set
     * @param price  price of the drink
     * @return the price of the drink set
     */
    @Override
    public double setPrice(double price) {
        this.price = price;
        return getPrice();
    }

    /**
     * check if the drink is equal to another object
     * @param o  object to compare
     * @return true if the drink is equal to the object, false otherwise
     */
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

    /**
     * get the hash code of the drink
     * @return hash code of the drink
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, price, portion);
    }
}
