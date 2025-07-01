package VendingMachineSystem.machine;

import VendingMachineSystem.Coin;
import VendingMachineSystem.exception.VendingMachineException;
import VendingMachineSystem.inventory.Inventory;
import VendingMachineSystem.state.DispensingState;
import VendingMachineSystem.state.HasCoinState;
import VendingMachineSystem.state.ReadyState;
import VendingMachineSystem.state.VendingMachineState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendingMachine {
    public volatile static VendingMachine instance = null;
    //STATE objects
    private VendingMachineState readyState;
    private VendingMachineState hasCoinState;
    private VendingMachineState dispensingState;
    private VendingMachineState currentState;
    
    private Inventory inventory;

    //User state
    private List<Coin> userCoins;
    private String selectedItemCode;
    private Map<Coin, Integer> machineCoinInventory;

    private VendingMachine() {
        readyState = new ReadyState(this);
        hasCoinState = new HasCoinState(this);
        dispensingState = new DispensingState(this);
        currentState = readyState;
        userCoins = new ArrayList<>();
        machineCoinInventory = new HashMap<>();
        machineCoinInventory.put(Coin.TEN, 10);
        machineCoinInventory.put(Coin.TWENTY, 10);
        machineCoinInventory.put(Coin.ONE, 10);
    }

    public static VendingMachine getInstance() {
        if(instance == null) {
            synchronized (VendingMachine.class) {
               if(instance == null)
                   instance = new VendingMachine();
            }
        }
        return instance;
    }
    
    public VendingMachineState getHasCoinState() {
        return hasCoinState;
    }
    
    public VendingMachineState getDispensingState() {
        return dispensingState;
    }
    

    public VendingMachineState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(VendingMachineState currentState) {
        this.currentState = currentState;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void addUserCoin(Coin coin) {
        userCoins.add(coin);
    }

    public List<Coin> getUserCoins() {
        return userCoins;
    }

    public void resetMachineState() {
        this.currentState = readyState;
        this.userCoins = new ArrayList<>();
        this.selectedItemCode = null;
    }

    public String getSelectedItemCode() {
        return selectedItemCode;
    }

    public void setSelectedItemCode(String selectedItemCode) {
        this.selectedItemCode = selectedItemCode;
    }

    public void removeCoinsFromInventory(List<Coin> coins) {
        for(Coin coin : coins) {
            int currentCount = machineCoinInventory.getOrDefault(coin, 0);
            if(currentCount > 0)
                machineCoinInventory.put(coin, currentCount-1);
        }
    }

    public double getVendingMachineBalance() {
        double total = 0.0;
        for(Map.Entry<Coin, Integer> mapEntry : machineCoinInventory.entrySet()) {
            Coin coin = mapEntry.getKey();
            int count = mapEntry.getValue();
            total += coin.getValue() * count;
        }
        return total;
    }

    public void selectItem(String itemCode) throws VendingMachineException {
        this.getCurrentState().selectItem(itemCode);
    }

    public void insertCoin(Coin coin) throws VendingMachineException {
        this.getCurrentState().insertCoin(coin);

    }

    public void dispenseItem() throws VendingMachineException {
        this.setCurrentState(dispensingState);
        this.getCurrentState().dispenseItem();
        for(Coin coin : userCoins)
            machineCoinInventory.put(coin, machineCoinInventory.getOrDefault(coin, 0)+1);
    }

    public void cancelTransaction() throws VendingMachineException {
        this.getCurrentState().cancelTransaction();
    }
}
