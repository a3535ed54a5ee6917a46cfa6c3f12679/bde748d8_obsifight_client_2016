package fr.thisismac.injector.customclass;

import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class CustomThread extends GuiScreen implements Runnable {
   public Boolean canvote;
   public String playerName;

   public CustomThread(String player) {
      this.playerName = player;
      this.canvote = Boolean.valueOf(false);
   }

   public void run() {
      while(true) {
         CloseableHttpClient client = HttpClientBuilder.create().build();
         HttpGet post = new HttpGet("http://api.obsifight.net/user/" + this.playerName + "/vote/can");
         post.setConfig(RequestConfig.custom().setConnectTimeout(1000).setSocketTimeout(1000).build());

         try {
            HttpResponse e = client.execute(post);
            if(e.getEntity() != null) {
               String tmp = EntityUtils.toString(e.getEntity());
               boolean state = tmp.equals("1");
               Minecraft.vote = state;
            }
         } catch (ClientProtocolException var7) {
            ;
         } catch (IOException var8) {
            ;
         }

         try {
            Thread.sleep(60000L);
         } catch (InterruptedException var6) {
            var6.printStackTrace();
         }
      }
   }
}
