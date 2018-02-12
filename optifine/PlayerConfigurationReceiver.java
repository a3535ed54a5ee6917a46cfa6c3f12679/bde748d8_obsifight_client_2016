package optifine;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class PlayerConfigurationReceiver implements IFileDownloadListener {
   private String player = null;

   public PlayerConfigurationReceiver(String player) {
      this.player = player;
   }

   public void fileDownloadFinished(String url, byte[] bytes, Throwable exception) {
      if(bytes != null) {
         try {
            String var9 = new String(bytes, "ASCII");
            JsonParser jp = new JsonParser();
            JsonElement je = jp.parse(var9);
            PlayerConfigurationParser pcp = new PlayerConfigurationParser(this.player);
            PlayerConfiguration pc = pcp.parsePlayerConfiguration(je);
            if(pc != null) {
               pc.setInitialized(true);
               PlayerConfigurations.setPlayerConfiguration(this.player, pc);
            }
         } catch (Exception var91) {
            Config.dbg("Error parsing configuration: " + url + ", " + var91.getClass().getName() + ": " + var91.getMessage());
         }
      }
   }
}
