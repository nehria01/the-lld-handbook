package VendingMachineSystem;

import VendingMachineSystem.exception.VendingMachineException;
import VendingMachineSystem.inventory.Inventory;
import VendingMachineSystem.state.DispensingState;
import VendingMachineSystem.state.HasCoinState;
import VendingMachineSystem.state.IdleState;
import VendingMachineSystem.state.VendingMachineState;

import java.util.ArrayList;
import java.util.List;

public class VendingMachine {
    public volatile static VendingMachine instance = null;
    //STATE objects
    private VendingMachineState idleState;
    private VendingMachineState hasCoinState;
    private VendingMachineState dispensingState;
    private VendingMachineState soldOutState;
    private VendingMachineState currentState;
    
    private Inventory inventory;

    //User state
    private List<Coin> userCoinsList;
    private String selectedItemCode;
    
    public void setSelectedItemCode(String selectedItemCode) {
        this.selectedItemCode = selectedItemCode;
    }

    public int getSelectedProductQuantity() {
        return selectedProductQuantity;
    }

    public void setSelectedProductQuantity(int selectedProductQuantity) {
        this.selectedProductQuantity = selectedProductQuantity;
    }

    private int selectedProductQuantity;

    private VendingMachine() {
        idleState = new IdleState(this);
        hasCoinState = new HasCoinState(this);
        dispensingState = new DispensingState(this);
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
        userCoinsList.add(coin);
    }

    public List<Coin> getUserCoinsList() {
        return userCoinsList;
    }

    public void resetMachineState() {
        this.currentState = idleState;
        this.userCoinsList = new ArrayList<>();
        this.selectedItemCode = null;
    }

    public String getSelectedItemCode() {
        return selectedItemCode;
    }

}
