package fr.thisismac.injector.customclass;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import net.minecraft.client.Minecraft;

public class HttpReq {
   public static final String USER_AGENT = "Mozilla/5.0";
   public static HttpReq http = new HttpReq();

   public static void sendGet() throws Exception {
      try {
         InetAddress ip = InetAddress.getLocalHost();
         NetworkInterface e = NetworkInterface.getByInetAddress(ip);
         byte[] ma = e.getHardwareAddress();
         StringBuilder sb = new StringBuilder();

         for(int url = 0; url < ma.length; ++url) {
            sb.append(String.format("%02X%s", new Object[]{Byte.valueOf(ma[url]), url < ma.length - 1?"-":""}));
         }

         String var11 = "http://auth.obsifight.fr/mac_adress/" + Minecraft.getMinecraft().session.getUsername() + "/" + sb.toString();
         URL obj = new URL(var11);
         HttpURLConnection con = (HttpURLConnection)obj.openConnection();
         con.setRequestMethod("GET");
         con.setRequestProperty("User-Agent", "Mozilla/5.0");
         int responseCode = con.getResponseCode();
         BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
         in.close();
      } catch (UnknownHostException var9) {
         var9.printStackTrace();
      } catch (SocketException var10) {
         var10.printStackTrace();
      }
   }
}
