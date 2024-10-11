package Product;

public interface Product {
    String getName();
    String getPortion();
    String setPortion(String portion);
    double getPrice();
    double setPrice(double price);
    boolean equals(Object o);
    int hashCode();
}

