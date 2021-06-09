package it.aonoasgard.eastereggs.models;

import org.bukkit.inventory.ItemStack;
import org.json.simple.JSONArray;

import java.util.List;

public class TestaBase {
    private String username;
    private String titolo;
    private JSONArray lore;
    private Boolean droppable;
    private int droprate;


    public TestaBase(String username, String titolo, JSONArray lore, Boolean droppable){
        this.username = username;
        this.titolo = titolo;
        this.lore = lore;
        this.droppable = droppable;
        this.droprate = -1;
    }
    public TestaBase(String username, String titolo, JSONArray lore, Boolean droppable,int droprate){
        this.username = username;
        this.titolo = titolo;
        this.lore = lore;
        this.droppable = droppable;
        this.droprate = droprate;
    }

    public TestaBase(String username, String titolo, JSONArray lore,  int droprate){
        this.username = username;
        this.titolo = titolo;
        this.lore = lore;
        this.droppable = true;
        this.droprate = droprate;
    }

    public String getTitolo() {
        return titolo;
    }

    public JSONArray getLore() {
        return lore;
    }

    public String getUsername() {
        return username;
    }
    public Boolean getDroppable() {
        return droppable;
    }
    public int getDroprate() {
        return droprate;
    }
}
