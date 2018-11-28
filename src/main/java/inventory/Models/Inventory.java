package inventory.Models;

import inventory.controls.InventoryObserver;
import inventory.controls.InventoryUpdater;

import java.util.ArrayList;

public class Inventory {
  private  ArrayList<Item> items=new ArrayList<>();
  private boolean duplicates=true;
    private InventoryUpdater inv=new InventoryUpdater();
    private ArrayList<InventoryObserver> collection=new ArrayList<>();//InventoryObserver(inv);
    private int maxSize=16;
    public void setMaxSize(int maxSize)
    {
        this.maxSize=maxSize;
    }
    public boolean isMaxSize(int size)
    {
        return size>=maxSize;
    }

    public Inventory(ArrayList<Item> items, boolean duplicates) {

        this.items = items;
        for(Item it:items)
        {
            collection.add(new InventoryObserver(inv));
            inv.setItem(it);
        }
        this.duplicates = duplicates;
    }

    public boolean isDuplicates() {
        return duplicates;
    }

    public void setDuplicates(boolean duplicates) {
        this.duplicates = duplicates;
    }

    public Inventory() {
    }

    public Inventory(ArrayList<Item> items) {
        this.items = items;
    }
    public final int getSize()
    {
        return items.size();
    }

    public ArrayList<Item> getItems() {
        return items;
    }
    public void addItem(Item it)
    {
        boolean exists=false;
        if(duplicates)
            items.add(it);
        else{
            for(Item i:items)
            {
                if(i.getName().equalsIgnoreCase(it.getName()))
                {
                    exists=true;
                }
            }
            if(!exists){
                items.add(it);
                collection.add(new InventoryObserver(inv));
                inv.setItem(it);
            }
        }
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}
