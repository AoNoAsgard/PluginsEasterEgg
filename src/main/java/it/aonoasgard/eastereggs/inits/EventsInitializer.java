package it.aonoasgard.eastereggs.inits;

import it.aonoasgard.eastereggs.Eastereggs;
import it.aonoasgard.eastereggs.events.TesteDropListener;

public class EventsInitializer {
    public static void initialize(Eastereggs plugin){
        plugin.getServer().getPluginManager().registerEvents(new TesteDropListener(),plugin);
}
}
