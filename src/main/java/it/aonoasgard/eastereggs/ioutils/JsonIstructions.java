package it.aonoasgard.eastereggs.ioutils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.aonoasgard.eastereggs.models.TestaBase;
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

        JSONObject myObject = new JSONObject();
        JSONArray myArray = new JSONArray();
        JSONArray lore = new JSONArray();

        lore.add(ChatColor.WHITE+"Conservami");
        lore.add(ChatColor.WHITE+"ho un utilizzo");

        myArray.add(testeToJson(new TestaBase("AoNoAsgard",ChatColor.GOLD+"Token Di AoNoAsgard",lore,10)));
        myObject.put("Teste",myArray);
        defaults.put("ConfigTeste",myObject);

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


    public static JSONObject testeToJson(TestaBase testa) {
        JSONObject jso = new JSONObject();
        jso.put("username", testa.getUsername());
        jso.put("titolo", testa.getTitolo());
        jso.put("lore", testa.getLore());
        jso.put("droppable", testa.getDroppable());
        jso.put("droprate", testa.getDroprate());
        return jso;
    }
    public static TestaBase jsonToTesta(JSONObject obj) {
        try {
            return new TestaBase(obj.get("username").toString(),
                    obj.get("titolo").toString(),
                    (JSONArray) obj.get("lore"),
                    (Boolean) obj.get("droppable"),
                    (Integer) obj.get("droprate"));
        }catch (Exception exc){
            exc.printStackTrace();
            return null;
        }
    }

}