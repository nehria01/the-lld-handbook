package VendingMachine.service;

import VendingMachine.enums.Coin;
import VendingMachine.model.Product;
import VendingMachine.repository.Inventory;

import java.util.Map;

public class InventoryService {
    private static InventoryService instance;
    private Inventory inventory;
    private InventoryService() {
        this.inventory = Inventory.getInstance();
    }

    public static InventoryService getInstance() {
        if(instance == null) {
            synchronized (InventoryService.class) {
                if(instance == null)
                    instance = new InventoryService();
            }
        }
        return instance;
    }

    public void addProduct(Product product , int count) {
        inventory.addProduct(product, count);
    }

    public boolean productAvailable(String productId, int count) {
        return inventory.getProductCount(productId) >= count;
    }

    public int getProductPrice(String productId) {
        return inventory.getProduct(productId).productCost;
    }

    public void reduceStock(String productId) {
        inventory.decrementProductCount(productId,1);
    }
    public int[] getDenominations() {
        return inventory.getDenominations();
    }

    public int[] getCoinsCount() {
        return inventory.getCoinCount();
    }

    public void reduceCoin(Map<Coin,Integer> changeMap) {
        for(var x : changeMap.entrySet())
            inventory.removeCoin(x.getKey(), x.getValue());
    }

    public void addCoin(Coin coin) {
        inventory.addCoin(coin);
    }
}
