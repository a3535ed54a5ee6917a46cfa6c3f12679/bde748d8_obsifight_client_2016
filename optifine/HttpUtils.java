package optifine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.Minecraft;

public class HttpUtils {
   public static final String SERVER_URL = "http://s.optifine.net";
   public static final String POST_URL = "http://optifine.net";

   public static byte[] get(String urlStr) throws IOException {
      HttpURLConnection conn = null;

      try {
         URL url = new URL(urlStr);
         conn = (HttpURLConnection)url.openConnection(Minecraft.getMinecraft().getProxy());
         conn.setDoInput(true);
         conn.setDoOutput(false);
         conn.connect();
         if(conn.getResponseCode() / 100 != 2) {
            if(conn.getErrorStream() != null) {
               Config.readAll(conn.getErrorStream());
            }

            throw new IOException("HTTP response: " + conn.getResponseCode());
         } else {
            InputStream in = conn.getInputStream();
            byte[] bytes = new byte[conn.getContentLength()];
            int pos = 0;

            do {
               int len1 = in.read(bytes, pos, bytes.length - pos);
               if(len1 < 0) {
                  throw new IOException("Input stream closed: " + urlStr);
               }

               pos += len1;
            } while(pos < bytes.length);

            byte[] var7 = bytes;
            return var7;
         }
      } finally {
         if(conn != null) {
            conn.disconnect();
         }
      }
   }

   public static String post(String urlStr, Map headers, byte[] content) throws IOException {
      HttpURLConnection conn = null;

      try {
         URL url = new URL(urlStr);
         conn = (HttpURLConnection)url.openConnection(Minecraft.getMinecraft().getProxy());
         conn.setRequestMethod("POST");
         if(headers != null) {
            Set os1 = headers.keySet();
            Iterator in1 = os1.iterator();

            while(in1.hasNext()) {
               String isr1 = (String)in1.next();
               String br1 = "" + headers.get(isr1);
               conn.setRequestProperty(isr1, br1);
            }
         }

         conn.setRequestProperty("Content-Type", "text/plain");
         conn.setRequestProperty("Content-Length", "" + content.length);
         conn.setRequestProperty("Content-Language", "en-US");
         conn.setUseCaches(false);
         conn.setDoInput(true);
         conn.setDoOutput(true);
         OutputStream os11 = conn.getOutputStream();
         os11.write(content);
         os11.flush();
         os11.close();
         InputStream in11 = conn.getInputStream();
         InputStreamReader isr11 = new InputStreamReader(in11, "ASCII");
         BufferedReader br11 = new BufferedReader(isr11);
         StringBuffer sb = new StringBuffer();

         String line;
         while((line = br11.readLine()) != null) {
            sb.append(line);
            sb.append('\r');
         }

         br11.close();
         String var11 = sb.toString();
         String var12 = var11;
         return var12;
      } finally {
         if(conn != null) {
            conn.disconnect();
         }
      }
   }
}
