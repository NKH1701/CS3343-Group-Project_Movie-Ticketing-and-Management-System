package release.product;


import java.util.Map;

/**
 * Interface for all products
 */
public interface Product {
    /**
     * get the name of the product
     *
     * @return the name of the product
     */
    String getName();


    /**
     * get the price of the product
     *
     * @return the price of the product
     */
    double getPrice();

    /**
     * format the product map into a string
     * @param productMap the product map to format
     * @return the formatted string, empty string if the product map is empty or null
     */
    static String formatProductMap(Map<Product, Integer> productMap) {
        final int lineSeparator = 73;
        StringBuilder results = new StringBuilder();
        if (productMap != null && !productMap.isEmpty()) {
            int productCnt = 0;
            results.append("\n").append(String.format("%4s%-32s", " ", "Snacks/Drinks"))
                    .append(String.format("%-16s", "Portion"))
                    .append(String.format("%-15s", "Quantity")).append(String.format("%-6s", "Price"))
                    .append("\n").append("-".repeat(lineSeparator)).append("\n");
            for (Map.Entry<Product, Integer> entry : productMap.entrySet()) {
                Product product = entry.getKey();
                results.append(String.format("%2d) ", ++productCnt))
                        .append(String.format("%-30s%2s", product.getName(), " "));
                if (product instanceof ProductWithPortion) {
                    results.append(String.format("%-16s", ((ProductWithPortion) product).getPortion()));
                } else {
                    results.append(String.format("%-16s", "N/A"));
                }
                results.append(String.format("%5d%10s", entry.getValue(), " "))
                        .append(String.format("$%-4.1f", product.getPrice())).append("\n");
            }
            results.append("-".repeat(lineSeparator)).append("\n");
        }
        return results.toString();
    }


}

