package it.aonoasgard.eastereggs.events;

import it.aonoasgard.eastereggs.items.SkullUtilities;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Random;

public class TesteDropListener implements Listener {

    @EventHandler
    public void onMobDeath(EntityDeathEvent event){
        LivingEntity entity = event.getEntity();
        Random rand = new Random();
        Bukkit.getLogger().info("Mob Ucciso");
        if (entity instanceof Monster){
            Bukkit.getLogger().info("Mostro Ucciso");
            if(rand.nextInt(100)==50) {
                Bukkit.getLogger().info("Fortuna");
                List<ItemStack> teste = SkullUtilities.teste;
                int nteste = teste.size();
                if (nteste < 1) {
                    Bukkit.getLogger().info("minore di 1");
                    return;
                }
                entity.getLocation().getWorld().dropItem(entity.getLocation(), teste.get(rand.nextInt(nteste)).clone());
            }
        }
    }
}
