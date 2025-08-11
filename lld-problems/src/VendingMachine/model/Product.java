package VendingMachine.model;

import java.util.concurrent.atomic.AtomicInteger;

public class Product {
    private static AtomicInteger PRODUCT_ID_GENERATOR = new AtomicInteger(1);

    public int id;
    public String productName;
    public String productCode;
    public int productCost;

    public Product(String productName, String productCode, int productCost) {
        this.id = PRODUCT_ID_GENERATOR.getAndIncrement();
        this.productName = productName;
        this.productCode = productCode;
        this.productCost = productCost;
    }
}
