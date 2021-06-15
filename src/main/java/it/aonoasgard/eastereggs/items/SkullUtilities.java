package it.aonoasgard.eastereggs.items;

import it.aonoasgard.eastereggs.ioutils.JsonIstructions;
import it.aonoasgard.eastereggs.models.TestaBase;
import it.aonoasgard.eastereggs.models.TestaCompleta;
import it.aonoasgard.eastereggs.requests.HeadRequest;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.*;

public class SkullUtilities {
    public List<TestaCompleta> teste;

    public SkullUtilities(JsonIstructions fileTeste){
        teste = new ArrayList<TestaCompleta>();
        JSONObject jobj = fileTeste.getObject("ConfigTeste");
        JSONArray jarray = (JSONArray) jobj.get("Teste");


        for (int i=0;i<jarray.size();i++){
            JSONObject confTesta = (JSONObject) jarray.get(i);
            String username = confTesta.get("username").toString();
            Bukkit.getLogger().info(username);
            ItemStack playerhead = new ItemStack(Material.PLAYER_HEAD ,1 );
            SkullMeta skull =(SkullMeta) playerhead.getItemMeta();
            skull.setOwningPlayer(Bukkit.getOfflinePlayer(HeadRequest.getUUID(username)));
            playerhead.setItemMeta(skull);
            ItemMeta im = playerhead.getItemMeta();
            String titolo = null;
            try{
                titolo = confTesta.get("titolo").toString();
                im.setDisplayName(titolo);
            }catch(Exception exc){
                im.setDisplayName(username);
                titolo = username;
            }
            ArrayList<String> lore = new ArrayList<String>();
            try{
                lore = (ArrayList) confTesta.get("lore");
                im.setLore(lore);
            }catch (Exception exc){

            }

            playerhead.setItemMeta(im);

            Boolean droppable = null;
            try {
               droppable = (Boolean) confTesta.get("droppable");
            }catch (Exception exc){
                droppable = false;
            }

            int droprate ;

            try {
                droprate = Integer.parseInt(confTesta.get("droprate").toString())  ;
            }catch (Exception exc){
                Bukkit.getLogger().warning("droprate Exception");
                droprate = -1;
            }

            TestaCompleta testa = new TestaCompleta(username,titolo,lore ,droppable, droprate);
            testa.setItem(playerhead);

            teste.add(testa);

        }

        /**                     OLD
        teste = new ArrayList<ItemStack>();
        JSONArray jarray = fileTeste.getArray("Usernames");
        for (int i=0;i<jarray.size();i++){
            String nome =  jarray.get(i).toString();
            Bukkit.getLogger().info(nome);
            ItemStack playerhead = new ItemStack(Material.PLAYER_HEAD ,1 );
            SkullMeta skull =(SkullMeta) playerhead.getItemMeta();
            skull.setOwningPlayer(Bukkit.getPlayer(nome));
            try {
                Bukkit.getLogger().info(  Bukkit.getPlayer(nome).getClass().getName());

            }catch (NullPointerException exc){
                Bukkit.getLogger().info("NullPointerException nel getPlayer");
            }
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
        }**/



    }

}
