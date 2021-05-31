package it.aonoasgard.eastereggs;

import it.aonoasgard.eastereggs.inits.EventsInitializer;
import it.aonoasgard.eastereggs.inits.JsonInits;
import it.aonoasgard.eastereggs.ioutils.JsonIstructions;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Eastereggs extends JavaPlugin {
    public JsonIstructions FileTeste ;

    @Override
    public void onEnable() {
        FileTeste = new JsonIstructions(new File("plugins/EasterEggsAoNoAsgard/Teste.json"));
        JsonInits.initialize(FileTeste);
        EventsInitializer.initialize(this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
