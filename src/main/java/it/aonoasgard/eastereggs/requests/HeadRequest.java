package it.aonoasgard.eastereggs.requests;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.UUID;

public class HeadRequest {

    static private JsonParser parser = new JsonParser();
    static private String API_PROFILE_LINK = "https://api.mojang.com/users/profiles/minecraft/";

    public static UUID getUUID(String username){
        String json = getContent(API_PROFILE_LINK + username);
        Bukkit.getLogger().info(json);
        JsonObject o = parser.parse(json).getAsJsonObject();
        String id = o.get("id").getAsString();
        Bukkit.getLogger().info(id);
        UUID uuid = new UUID(
                new BigInteger(id.substring(0, 16), 16).longValue(),
                new BigInteger(id.substring(16), 16).longValue());

        return uuid;
    }

    public static String getContent(String link){
        try {
            URL url = new URL(link);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), Charset.forName("UTF-8")));

            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                return inputLine;
            }
            br.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
