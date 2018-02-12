package fr.thisismac.injector.customclass;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.HttpAuthenticationService;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilMinecraftSessionService;
import com.mojang.authlib.yggdrasil.response.MinecraftProfilePropertiesResponse;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomSessionService extends YggdrasilMinecraftSessionService {
   private static final String[] WHITELISTED_DOMAINS = new String[]{".obsifight.fr"};
   private static final Logger LOGGER = LogManager.getLogger();
   private static final String BASE_URL = "http://auth.obsifight.fr/";
   private static final URL JOIN_URL = HttpAuthenticationService.constantURL("http://auth.obsifight.fr/join");
   private static final URL CHECK_URL = HttpAuthenticationService.constantURL("http://auth.obsifight.fr/hasJoined");

   public CustomSessionService(YggdrasilAuthenticationService arg0) {
      super(arg0);
   }

   public Map<Type, MinecraftProfileTexture> getTextures(GameProfile profile, boolean requireSecure) {
      HashMap textures = new HashMap();
      boolean cape = false;
      boolean skin = false;

      try {
         HttpURLConnection e = (HttpURLConnection)(new URL("http://skins.obsifight.fr/skins/" + profile.getName() + ".png")).openConnection();
         e.setReadTimeout(2000);
         e.setConnectTimeout(2000);
         if(e.getResponseCode() == 200) {
            skin = true;
         }

         e.disconnect();
         e = (HttpURLConnection)(new URL("http://skins.obsifight.fr/capes/" + profile.getName() + "_cape.png")).openConnection();
         e.setReadTimeout(2000);
         e.setConnectTimeout(2000);
         if(e.getResponseCode() == 200) {
            cape = true;
         }
      } catch (MalformedURLException var7) {
         var7.printStackTrace();
      } catch (IOException var8) {
         var8.printStackTrace();
      }

      if(skin) {
         textures.put(Type.SKIN, new MinecraftProfileTexture("http://skins.obsifight.fr/skins/" + profile.getName() + ".png", (Map)null));
      }

      if(cape) {
         textures.put(Type.CAPE, new MinecraftProfileTexture("http://skins.obsifight.fr/capes/" + profile.getName() + "_cape.png", (Map)null));
      }

      return textures;
   }

   protected GameProfile fillGameProfile(GameProfile profile, boolean requireSecure) {
      try {
         URL e = HttpAuthenticationService.constantURL("http://auth.obsifight.fr/" + "profile/" + profile.getId());
         e = HttpAuthenticationService.concatenateURL(e, "unsigned=" + !requireSecure);
         MinecraftProfilePropertiesResponse response = (MinecraftProfilePropertiesResponse)((CustomAuthService)this.getAuthenticationService()).makeRequest(e, (Object)null, MinecraftProfilePropertiesResponse.class);
         if(response == null) {
            LOGGER.debug("Couldn\'t fetch profile properties for " + profile + " as the profile does not exist");
            return profile;
         } else {
            GameProfile result = new GameProfile(response.getId(), response.getName());
            result.getProperties().putAll(response.getProperties());
            profile.getProperties().putAll(response.getProperties());
            LOGGER.debug("Successfully fetched profile properties for " + profile.getName());
            return result;
         }
      } catch (Exception var6) {
         LOGGER.warn("Couldn\'t look up profile properties for " + profile, var6);
         return profile;
      }
   }
}
