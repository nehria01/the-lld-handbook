package VendingMachineSystem.inventory;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<String, Product> productMap;
    private Map<String, Integer> productStockMap;
    int totalItemsInStock;

    public Inventory() {
        productMap = new HashMap<>();
        productStockMap = new HashMap<>();
        totalItemsInStock = 0;
    }

    public synchronized void addProduct(Product product, int quantity) {
        String productCode = product.getCode();
        productMap.putIfAbsent(productCode, product);
        productStockMap.put(productCode, productStockMap.getOrDefault(productCode, 0)+quantity);
        totalItemsInStock += quantity;
    }

    public Product getProduct(String itemCode) {
        return productMap.get(itemCode);
    }
    public synchronized void removeProduct(Product product) {
        String productCode = product.getCode();
        if (!productStockMap.containsKey(productCode)) return;
        int quantity = productStockMap.get(productCode);
        if(quantity < 0 ) return;
        productMap.remove(productCode);
        productStockMap.remove(productCode);
        totalItemsInStock -= quantity;
    }

    public synchronized boolean reserveStock(String productCode, int quantity) {
        int currentStock = productStockMap.getOrDefault(productCode, 0);
        if (currentStock >= quantity) {
            productStockMap.put(productCode, currentStock - quantity);
            totalItemsInStock -= quantity;
            return true;
        }
        return false;
    }

    public synchronized boolean soldOut() {
        return this.totalItemsInStock == 0;
    }
}
