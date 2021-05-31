package it.aonoasgard.eastereggs.ioutils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.HashMap;
import java.util.TreeMap;


public class JsonIstructions {
    private File file;
    private JSONObject json;
    private JSONParser parser = new JSONParser();
    private HashMap<String, Object> defaults = new HashMap<String, Object>();

    public JsonIstructions(File file){
        this.file = file;
        reload();
    }

    public void reload(){
        try {
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            if(!file.exists()){
                PrintWriter pw = new PrintWriter(file, "UTF-8");
                pw.print("{");
                pw.print("}");
                pw.flush();
                pw.close();
            }
            json = (JSONObject) parser.parse(new InputStreamReader(new FileInputStream(file),"UTF-8"));
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public String getRawData(String key) {
        return json.containsKey(key) ? json.get(key).toString()
                : (defaults.containsKey(key) ? defaults.get(key).toString() : key);
    }

    public void initialize(){
        defaults.put("MyString","Some String");

        defaults.put("MyNumber",1337);
        JSONObject myObject = new JSONObject();
        myObject.put("Test","test");
        myObject.put("Test2","test2");
        defaults.put("MyObject",myObject);

        JSONArray myArray = new JSONArray();
        myArray.add("Valore1");
        myArray.add("Valore2");
        defaults.put("MyArray",myArray);

    }

    public String getString(String key) {
        return ChatColor.translateAlternateColorCodes('&', getRawData(key));
    }

    public boolean getBoolean(String key){
        return Boolean.valueOf(getRawData(key));
    }

    public double getDouble(String key){
        try{
            return Double.parseDouble(getRawData(key));
        }catch(Exception ex){ }
        return -1;
    }

    public double getInteger(String key){
        try {
            return Integer.parseInt(getRawData(key));
        }catch(Exception ex){}
        return -1;
    }

    public JSONObject getObject(String key){
        return json.containsKey(key) ? (JSONObject) json.get(key) :
                (defaults.containsKey(key) ? (JSONObject) defaults.get(key) : new JSONObject());
    }

    public void setObject(String s ,JSONObject j){
        defaults.put(s,j);
    }

    public void setArray(String s ,JSONArray a){
        defaults.put(s,a);
    }

    public JSONArray getArray(String key){
        return json.containsKey(key) ? (JSONArray) json.get(key) :
                (defaults.containsKey(key) ? (JSONArray) defaults.get(key) : new JSONArray());
    }

    public boolean save(){
        try{
            JSONObject toSave = new JSONObject();

            for(String s : defaults.keySet()){
                Object o = defaults.get(s);
                if(o instanceof String){
                    toSave.put(s,getString(s));
                }else if (o instanceof Double) {
                    toSave.put(s, getDouble(s));
                } else if (o instanceof Integer) {
                    toSave.put(s, getInteger(s));
                } else if (o instanceof JSONObject) {
                    toSave.put(s, getObject(s));
                } else if (o instanceof JSONArray) {
                    toSave.put(s, getArray(s));
                }
            }

            TreeMap<String,Object> treeMap = new TreeMap<String, Object>(String.CASE_INSENSITIVE_ORDER);
            treeMap.putAll(toSave);

            Gson g = new GsonBuilder().setPrettyPrinting().create();
            String prettyJsonString = g.toJson(treeMap);

            FileWriter fw = new FileWriter(file);
            fw.write(prettyJsonString);
            fw.flush();
            fw.close();

            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }


    public static JSONObject locToJson(Location loc) {
        JSONObject jso = new JSONObject();
        jso.put("world", loc.getWorld().getName());
        jso.put("x", loc.getX());
        jso.put("y", loc.getY());
        jso.put("z", loc.getZ());
        jso.put("yaw", loc.getYaw());
        jso.put("pitch", loc.getPitch());
        return jso;
    }
    public static Location jsonToLoc(JSONObject obj) {
        return new Location(Bukkit.getWorld(obj.get("world").toString()),
                Double.parseDouble(obj.get("x").toString()),
                Double.parseDouble(obj.get("y").toString()),
                Double.parseDouble(obj.get("z").toString()),
                Float.parseFloat(obj.get("yaw").toString()),
                Float.parseFloat(obj.get("pitch").toString()));
    }

}