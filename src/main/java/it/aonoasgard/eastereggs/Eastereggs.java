package it.aonoasgard.eastereggs;

import it.aonoasgard.eastereggs.inits.EventsInitializer;
import it.aonoasgard.eastereggs.inits.JsonInits;
import it.aonoasgard.eastereggs.ioutils.JsonIstructions;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Eastereggs extends JavaPlugin {
    public JsonIstructions fileTeste ;

    @Override
    public void onEnable() {
        fileTeste = new JsonIstructions(new File("plugins/EasterEggsAoNoAsgard/Teste.json"));
        JsonInits.initialize(fileTeste);
        EventsInitializer.initialize(this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
