package inventory.Inteface;

public interface InventoryInterface {
    public void register(Observer o);

    public void unregister(Observer o);

    public void notifyObserver();
}