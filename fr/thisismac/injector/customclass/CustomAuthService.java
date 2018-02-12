package fr.thisismac.injector.customclass;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.exceptions.AuthenticationUnavailableException;
import com.mojang.authlib.exceptions.InvalidCredentialsException;
import com.mojang.authlib.exceptions.UserMigratedException;
import com.mojang.authlib.properties.PropertyMap;
import com.mojang.authlib.properties.PropertyMap.Serializer;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.response.ProfileSearchResultsResponse;
import com.mojang.authlib.yggdrasil.response.Response;
import com.mojang.util.UUIDTypeAdapter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.Proxy;
import java.net.URL;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;

public class CustomAuthService extends YggdrasilAuthenticationService {
   public final Gson gson;

   public CustomAuthService(Proxy proxy, String clientToken) {
      super(proxy, clientToken);
      GsonBuilder builder = new GsonBuilder();
      builder.registerTypeAdapter(GameProfile.class, new CustomAuthService.GameProfileSerializer());
      builder.registerTypeAdapter(PropertyMap.class, new Serializer());
      builder.registerTypeAdapter(UUID.class, new UUIDTypeAdapter());
      builder.registerTypeAdapter(ProfileSearchResultsResponse.class, new com.mojang.authlib.yggdrasil.response.ProfileSearchResultsResponse.Serializer());
      this.gson = builder.create();
   }

   public <T extends Response> T makeRequest(URL url, Object input, Class<T> classOfT) throws AuthenticationException {
      try {
         String e = input == null?this.performGetRequest(url):this.performPostRequest(url, this.gson.toJson(input), "application/json");
         Response result = (Response)this.gson.fromJson(e, classOfT);
         if(result == null) {
            return null;
         } else if(StringUtils.isNotBlank(result.getError())) {
            if("UserMigratedException".equals(result.getCause())) {
               throw new UserMigratedException(result.getErrorMessage());
            } else if(result.getError().equals("ForbiddenOperationException")) {
               throw new InvalidCredentialsException(result.getErrorMessage());
            } else {
               throw new AuthenticationException(result.getErrorMessage());
            }
         } else {
            return result;
         }
      } catch (IOException var6) {
         throw new AuthenticationUnavailableException("Cannot contact authentication server", var6);
      } catch (IllegalStateException var7) {
         throw new AuthenticationUnavailableException("Cannot contact authentication server", var7);
      } catch (JsonParseException var8) {
         throw new AuthenticationUnavailableException("Cannot contact authentication server", var8);
      }
   }

   public static class GameProfileSerializer implements JsonSerializer<GameProfile>, JsonDeserializer<GameProfile> {
      public GameProfile deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
         JsonObject object = (JsonObject)json;
         UUID id = object.has("id")?(UUID)context.deserialize(object.get("id"), UUID.class):null;
         String name = object.has("name")?object.getAsJsonPrimitive("name").getAsString():null;
         return new GameProfile(id, name);
      }

      public JsonElement serialize(GameProfile src, Type typeOfSrc, JsonSerializationContext context) {
         JsonObject result = new JsonObject();
         if(src.getId() != null) {
            result.add("id", context.serialize(src.getId()));
         }

         if(src.getName() != null) {
            result.addProperty("name", src.getName());
         }

         return result;
      }

      public JsonElement serialize(Object var1, Type var2, JsonSerializationContext var3) {
         return this.serialize((GameProfile)var1, var2, var3);
      }

      public Object deserialize(JsonElement var1, Type var2, JsonDeserializationContext var3) throws JsonParseException {
         return this.deserialize(var1, var2, var3);
      }
   }
}