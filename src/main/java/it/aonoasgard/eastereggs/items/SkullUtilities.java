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
    public static List<ItemStack> teste;

    public SkullUtilities(JsonIstructions fileTeste){
        JSONArray jarray = fileTeste.getArray("Usernames");
        for (int i=0;i<jarray.size();i++){
            String nome =  jarray.get(i).toString();
            Bukkit.getLogger().info(nome);
            ItemStack playerhead = new ItemStack(Material.PLAYER_HEAD,1 );
            Bukkit.getLogger().info("itemstack");
            SkullMeta skull =(SkullMeta) playerhead.getItemMeta();
            Bukkit.getLogger().info("skullMeta");
            skull.setOwningPlayer(Bukkit.getPlayer(nome));
            Bukkit.getLogger().info("setowningplayer");
            playerhead.setItemMeta(skull);
            Bukkit.getLogger().info("setMetaskull");
            ItemMeta im = playerhead.getItemMeta();
            Bukkit.getLogger().info("getMeta");
            if(nome.equals("AoNoAsgard")){
                im.setDisplayName(ChatColor.GOLD+"Token Di AoNoAsgard");
                Bukkit.getLogger().info("displayname");
                ArrayList lore = new ArrayList<String>();
                Bukkit.getLogger().info("create lore");
                lore.add(ChatColor.WHITE+"Conservami");
                lore.add(ChatColor.WHITE+"ho un utilizzo");
                Bukkit.getLogger().info("lore");
                im.setLore(lore);
                Bukkit.getLogger().info("seLore");
            }
            playerhead.setItemMeta(im);
            Bukkit.getLogger().info("setim");
            teste.add(playerhead);
            Bukkit.getLogger().info("add");
        }

    }

}
