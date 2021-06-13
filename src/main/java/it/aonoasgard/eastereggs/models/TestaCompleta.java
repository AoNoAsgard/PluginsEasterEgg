package it.aonoasgard.eastereggs.models;

import org.bukkit.inventory.ItemStack;
import org.json.simple.JSONArray;

import java.util.ArrayList;

public class TestaCompleta extends TestaBase{
    private ItemStack item;

    public TestaCompleta(String username, String titolo, ArrayList<String> lore, Boolean droppable){
        super(username, titolo, lore, droppable);
    }
    public TestaCompleta(String username, String titolo, ArrayList<String> lore, Boolean droppable,int droprate){
        super(username, titolo, lore, droppable, droprate);
    }
    public TestaCompleta(String username, String titolo, ArrayList<String> lore,  int droprate){
        super(username, titolo, lore, droprate);
    }
    public TestaCompleta(TestaBase testaBase){
        super(testaBase.getUsername(), testaBase.getTitolo(), testaBase.getLore(), testaBase.getDroppable() ,testaBase.getDroprate());
    }

    public TestaCompleta(TestaBase testaBase, ItemStack itemStack){
        super(testaBase.getUsername(), testaBase.getTitolo(), testaBase.getLore(), testaBase.getDroppable() ,testaBase.getDroprate());
        this.item = itemStack;
    }

    public ItemStack getItem() {
        return item;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }
}
