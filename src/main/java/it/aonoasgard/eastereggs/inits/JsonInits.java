package it.aonoasgard.eastereggs.inits;

import it.aonoasgard.eastereggs.ioutils.JsonIstructions;
import it.aonoasgard.eastereggs.items.SkullUtilities;
import org.bukkit.Bukkit;
import org.json.simple.JSONArray;

public class JsonInits {
    public static SkullUtilities su;

    public static void initialize(JsonIstructions fileTeste){
        /**
        JSONArray array = fileTeste.getArray("Usernames");
        fileTeste.setArray("Usernames",array);
         **/
        fileTeste.initialize();
        Bukkit.getLogger().info( ( (Boolean) fileTeste.save()).toString());
        //su = new SkullUtilities(fileTeste);
    }
}
