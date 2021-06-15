package it.aonoasgard.eastereggs.events;

import it.aonoasgard.eastereggs.inits.JsonInits;
import it.aonoasgard.eastereggs.items.SkullUtilities;
import it.aonoasgard.eastereggs.models.TestaCompleta;
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
        if (entity instanceof Monster){

            List<TestaCompleta> teste = JsonInits.su.teste;
            for(TestaCompleta testa: teste){
                if(testa.getDroppable()){
                    int r= rand.nextInt(1000);
                    //Bukkit.getLogger().info(String.valueOf(r)+"  -  "+ String.valueOf(testa.getDroprate()));
                    if(r<=testa.getDroprate()) {
                        //Bukkit.getLogger().info("Fortuna");

                        entity.getLocation().getWorld().dropItem(entity.getLocation(), testa.getItem().clone());
                }
            }
                //entity.getLocation().getWorld().dropItem(entity.getLocation(), teste.get(rand.nextInt(nteste)).clone());
            }
        }
    }
}
