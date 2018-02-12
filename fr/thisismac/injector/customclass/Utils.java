package fr.thisismac.injector.customclass;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import org.apache.commons.io.FileUtils;

public class Utils {
   public static String readURL(String url) {
      String text = "";

      try {
         HttpURLConnection con = (HttpURLConnection)(new URL(url)).openConnection();
         con.setConnectTimeout(500);
         con.setReadTimeout(500);
         text = (new BufferedReader(new InputStreamReader(con.getInputStream()))).readLine();
      } catch (Exception var3) {
         ;
      }

      return text;
   }

   public static String parseDamageToColoredText(ItemStack item) {
      int pourcentage = (int)((float)(item.getMaxDamage() - item.getItemDamage()) / (float)item.getMaxDamage() * 100.0F);
      return pourcentage <= 25?EnumChatFormatting.RED.toString() + pourcentage + "%":(pourcentage > 25 && pourcentage <= 50?EnumChatFormatting.GOLD.toString() + pourcentage + "%":(pourcentage > 50 && pourcentage <= 75?EnumChatFormatting.YELLOW.toString() + pourcentage + "%":(pourcentage > 75?EnumChatFormatting.GREEN.toString() + pourcentage + "%":EnumChatFormatting.GREEN.toString() + pourcentage + "%")));
   }

   public static void cleanCache() {
      File dir = new File(Minecraft.getMinecraft().fileAssets, "skins");
      if(dir.exists()) {
         try {
            FileUtils.deleteDirectory(dir);
         } catch (IOException var2) {
            var2.printStackTrace();
         }
      }
   }
}
