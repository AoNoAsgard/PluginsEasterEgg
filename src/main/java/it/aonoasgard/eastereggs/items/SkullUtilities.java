package it.aonoasgard.eastereggs.items;

import it.aonoasgard.eastereggs.ioutils.JsonIstructions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.json.simple.JSONArray;

import java.util.*;

public class SkullUtilities {
    public List<ItemStack> teste;

    public SkullUtilities(JsonIstructions fileTeste){
        teste = new ArrayList<ItemStack>();
        JSONArray jarray = fileTeste.getArray("Usernames");
        for (int i=0;i<jarray.size();i++){
            String nome =  jarray.get(i).toString();
            Bukkit.getLogger().info(nome);
            ItemStack playerhead = new ItemStack(Material.PLAYER_HEAD ,1 );
            SkullMeta skull =(SkullMeta) playerhead.getItemMeta();
            skull.setOwningPlayer(Bukkit.getPlayer(nome));
            playerhead.setItemMeta(skull);
            ItemMeta im = playerhead.getItemMeta();
            if(nome.equals("AoNoAsgard")){
                im.setDisplayName(ChatColor.GOLD+"Token Di AoNoAsgard");
                ArrayList lore = new ArrayList<String>();
                lore.add(ChatColor.WHITE+"Conservami");
                lore.add(ChatColor.WHITE+"ho un utilizzo");
                im.setLore(lore);
            }
            playerhead.setItemMeta(im);
            teste.add(playerhead);
        }

    }

}
