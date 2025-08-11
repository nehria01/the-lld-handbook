package VendingMachine.repository;

import VendingMachine.enums.Coin;
import VendingMachine.model.Product;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<String, Product> productMap;
    private Map<String, Integer> productIdToCountMap;
    private Map<Coin, Integer> coinCountMap;

    private Inventory() {
        productMap = new HashMap<>();
        productIdToCountMap = new HashMap<>();
        coinCountMap = new HashMap<>();
    }

    public static Inventory instance = null;

    public static Inventory getInstance() {
        if(instance == null) {
            synchronized (Inventory.class) {
                if(instance == null)
                    instance = new Inventory();
            }
        }
        return instance;
    }

    public Product getProduct(String productId) {
        return productMap.get(productId);
    }

    public int getProductCount(String productId) {
        return productIdToCountMap.getOrDefault(productId, 0);
    }

    public void addProduct(Product product, int count) {
        productMap.put(product.productCode, product);
        productIdToCountMap.put(product.productCode, count);
    }

    public void incrementProductCount(String productId, int count) {
        int currentCount = productIdToCountMap.getOrDefault(productId, 0);
        productIdToCountMap.put(productId, currentCount+count);
    }

    public void decrementProductCount(String productId, int count) {
        int currentCount = productIdToCountMap.getOrDefault(productId, 0);
        productIdToCountMap.put(productId, currentCount-count);
    }

    public boolean productSoldOut(String productId) {
        return productIdToCountMap.getOrDefault(productId,0) == 0;
    }

    public int[] getDenominations() {
        //need to convert the Coin to denomination
        return coinCountMap.keySet().stream()
                .sorted((c1, c2) -> Integer.compare(c1.getVal(), c2.getVal()))
                .mapToInt(Coin::getVal)
                .toArray();
    }

    public int[] getCoinCount() {
        return coinCountMap.keySet().stream()
                .sorted((c1, c2) -> Integer.compare(c1.getVal(), c2.getVal()))
                .mapToInt(coin -> coinCountMap.getOrDefault(coin, 0))
                .toArray();
    }

    public void addCoin(Coin coin) {
        int count = coinCountMap.getOrDefault(coin,0);
        coinCountMap.put(coin, count+1);
    }

    public void removeCoin(Coin coin, int count) {
        int currentCount = coinCountMap.getOrDefault(coin,0);
        coinCountMap.put(coin, currentCount-count);
    }
}
