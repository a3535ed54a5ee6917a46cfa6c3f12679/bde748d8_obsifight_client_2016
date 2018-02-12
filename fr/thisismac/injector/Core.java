package fr.thisismac.injector;

import fr.thisismac.injector.bettersprintmod.ClientModManager;
import fr.thisismac.injector.bettersprintmod.ClientSettings;
import fr.thisismac.injector.bettersprintmod.GuiSprint;
import fr.thisismac.injector.customclass.CustomAuthService;
import fr.thisismac.injector.customclass.CustomCraft;
import fr.thisismac.injector.customclass.CustomEnchantNoBack;
import fr.thisismac.injector.customclass.CustomSessionService;
import fr.thisismac.injector.customclass.CustomSettings;
import fr.thisismac.injector.customclass.CustomSoundThread;
import fr.thisismac.injector.customclass.CustomStuff;
import fr.thisismac.injector.customclass.CustomThread;
import fr.thisismac.injector.customclass.GuiCheatDetected;
import fr.thisismac.injector.customclass.HttpReq;
import fr.thisismac.injector.customclass.NetworkTrigger;
import fr.thisismac.injector.customclass.TextureManager;
import fr.thisismac.injector.customclass.Utils;
import java.awt.Frame;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.Proxy;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import java.util.Map.Entry;
import javax.swing.JOptionPane;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionHelper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

public class Core {
   public static String macro1 = null;
   public static String macro2 = null;
   public static String macro3 = null;
   public static String macro4 = null;
   private static Core INSTANCE;
   private static Minecraft game;
   private static int tick = 0;
   private Map<Core.EnumStuff, CustomStuff> stuffs = new TreeMap();
   private Map<String, Item> items = new TreeMap();
   private Map<String, Block> blocks = new TreeMap();
   private Map<String, Method> network_trigger = new HashMap();
   public CustomSoundThread soundThread = new CustomSoundThread();
   public CustomThread customThread;
   private static EntityOtherPlayerMP fakePlayer;
   public static boolean act = false;
   public static int nbt = 0;
   public static int leftClickDelayTimer;
   private static int sec;
   private static int yolo;
   private static int lastYolo;
   private static int detected = 0;
   private static long lastTime = 0L;
   public static final String SERVER_NAME = "Obsifight";
   public final String SERVER_IP = "bungee.obsifight.fr";
   public final int SERVER_PORT = 25565;
   public final boolean NERF_DAMAGE = true;
   public final boolean NERF_STRENGTH = true;
   public static short firstAvailableItemID = 450;
   public static short firstAvailableBlockID = 176;
   public static byte fistArmorAvailableID = 5;
   public static CustomAuthService authService;

   public static Core get() {
      return INSTANCE;
   }

   public Core() {
      INSTANCE = this;
      this.log(Level.INFO, "Loading ..");
      game = Minecraft.getMinecraft();
      new ClientSettings(game);
      Utils.cleanCache();

      try {
         Class noback = Class.forName("com.mojang.authlib.yggdrasil.YggdrasilMinecraftSessionService");
         setFinalStatic(noback.getDeclaredField("JOIN_URL"), new URL("http://auth.obsifight.fr/join"));
         setFinalStatic(noback.getDeclaredField("CHECK_URL"), new URL("http://auth.obsifight.fr/hasJoined"));
         setFinalStatic(noback.getDeclaredField("BASE_URL"), "http://auth.obsifight.fr/");
         setFinalStatic(noback.getDeclaredField("WHITELISTED_DOMAINS"), new String[]{".obsifight.fr"});
      } catch (Exception var9) {
         var9.printStackTrace();
      }

      try {
         HttpReq var10000 = HttpReq.http;
         HttpReq.sendGet();
      } catch (Exception var8) {
         var8.printStackTrace();
      }

      authService = new CustomAuthService(Proxy.NO_PROXY, UUID.randomUUID().toString());
      game.field_152355_az = new CustomSessionService(authService);
      this.log(Level.INFO, "Building ..");
      PotionHelper.potionRequirements.put(Integer.valueOf(Potion.digSpeed.getId()), "0 & 1 & 2 & !3 & !8 & !9 & !10 & !11 & 0+6");
      PotionHelper.potionAmplifiers.put(Integer.valueOf(Potion.digSpeed.getId()), "5");
      PotionHelper.potionRequirements.put(Integer.valueOf(Potion.jump.getId()), "0 & 1 & !2 & 3");
      Potion var10 = (new Potion(24, true, 5123226)).setPotionName("potion.noback").setIconIndex(5, 1).setEffectiveness(1.5D);
      new CustomEnchantNoBack(42, 15);
      PotionHelper.potionRequirements.put(Integer.valueOf(var10.getId()), "0 & 1 & 2 & 3");
      PotionHelper.potionAmplifiers.put(Integer.valueOf(var10.getId()), "5");
      PotionHelper.potionRequirements.put(Integer.valueOf(Potion.featherFalling.getId()), "!0 & !1 & !2 & !3 & !4 & 5 +5-6-7");
      PotionHelper.potionAmplifiers.put(Integer.valueOf(Potion.featherFalling.getId()), "10");
      this.blocks.put("chest_garnet", Blocks.chest_garnet);
      this.blocks.put("chest_amethyst", Blocks.chest_amethyst);
      this.blocks.put("chest_titanium", Blocks.chest_titanium);
      this.blocks.put("chest_xenotium", Blocks.chest_xenotium);

      try {
         this.network_trigger.put("[CombatTag] Vous etes", NetworkTrigger.class.getMethod("onCombatMessage", new Class[]{String.class}));
         this.network_trigger.put("[CombatTag] Vous n\'etes", NetworkTrigger.class.getMethod("onNoCombatMessage", new Class[]{String.class}));
         this.network_trigger.put(" ~ ", NetworkTrigger.class.getMethod("onTerritoryMessage", new Class[]{String.class}));
         this.network_trigger.put("[TG] ", NetworkTrigger.class.getMethod("onBroadcastMessage", new Class[]{String.class}));
         this.network_trigger.put("[STREAM-ON]", NetworkTrigger.class.getMethod("onStreamEnable", new Class[]{String.class}));
         this.network_trigger.put("[STREAM-OFF]", NetworkTrigger.class.getMethod("onStreamDisable", new Class[]{String.class}));
         this.network_trigger.put("[STREAM-UP]", NetworkTrigger.class.getMethod("onStreamSound", new Class[]{String.class}));
         this.network_trigger.put("[OBSISHOP]", NetworkTrigger.class.getMethod("onObsiShop", new Class[]{String.class}));
         this.network_trigger.put("[RE]", NetworkTrigger.class.getMethod("onShaders", new Class[]{String.class}));
      } catch (SecurityException var6) {
         var6.printStackTrace();
      } catch (NoSuchMethodException var7) {
         var7.printStackTrace();
      }

      this.log(Level.INFO, "Injecting ..");
      Iterator e = this.items.entrySet().iterator();

      while(e.hasNext()) {
         Entry item = (Entry)e.next();
         Item.itemRegistry.addObject(firstAvailableItemID++, (String)item.getKey(), item.getValue());
      }

      this.customize();
      this.log(Level.INFO, "Successfuly injected " + this.stuffs.size() + " stuffs, " + this.items.size() + " items, and " + this.blocks.size() + " blocks");
   }

   public void customize() {
      Block block = Block.getBlockFromName("obsidian").setResistance(23.0F);
      block = Block.getBlockFromName("enchanting_table").setResistance(23.0F);
      block = Block.getBlockFromName("ender_chest").setResistance(23.0F);
      block = Block.getBlockFromName("anvil").setResistance(23.0F);
      FurnaceRecipes.smelting().func_151393_a(Blocks.xenotium_ore, new ItemStack(Items.xenotium, 1, 0), 0.5F);
      FurnaceRecipes.smelting().func_151396_a(Items.canabis, new ItemStack(Items.canabis_seche), 1.2F);
      FurnaceRecipes.smelting().func_151393_a(Blocks.quartz_block, new ItemStack(Blocks.quartz_noir), 0.6F);
      CustomCraft.register();
   }

   public void tick() {
      if(ClientModManager.keyBindOptionsMenu.getIsKeyPressed()) {
         game.displayGuiScreen(new GuiSprint(game.currentScreen));
      }

      try {
         macro1 = "/" + CustomSettings.getSetting("macro1");
         macro2 = "/" + CustomSettings.getSetting("macro2");
         macro3 = "/" + CustomSettings.getSetting("macro3");
         macro4 = "/" + CustomSettings.getSetting("macro4");
      } catch (IOException var2) {
         var2.printStackTrace();
      }

      if(NetworkTrigger.timeToRender > 0) {
         if(NetworkTrigger.timeToRender == 0) {
            NetworkTrigger.textToRender = null;
         } else {
            --NetworkTrigger.timeToRender;
         }
      }

      if(leftClickDelayTimer > 0) {
         --leftClickDelayTimer;
      }

      if(tick == 20) {
         tick = 0;
         this.tickSecond();
         this.jsaispas();
      } else {
         ++tick;
      }
   }

   public void jsaispas() {
      if(game.currentScreen instanceof GuiCheatDetected) {
         if(sec == 5) {
            act = true;
            sec = 0;
         } else {
            ++sec;
         }
      }
   }

   public void tickSecond() {
      if(!game.isGamePaused) {
         if(lastTime == System.currentTimeMillis() / 1000L) {
            ++detected;
            yolo = (int)(System.currentTimeMillis() / 1000L - (long)yolo);
            if(yolo == lastYolo) {
               JOptionPane.showMessageDialog(new Frame(), "Le cheat c\'est mal ...", "Obsifight", 1);
               System.exit(0);
            }

            yolo = lastYolo;
         }

         lastTime = System.currentTimeMillis() / 1000L;
      }

      if(sec == 60) {
         sec = 0;
         this.tickMinute();
      } else {
         ++sec;
      }
   }

   public void tickMinute() {
      if(detected > 7) {
         new JOptionPane();
         JOptionPane.showMessageDialog(new Frame(), "Le cheat c\'est mal ...", "Obsifight", 1);
         System.exit(0);
         Minecraft.getMinecraft().thePlayer.sendChatMessage("\u00a7cD\u00e9connect\u00e9 pour SpeedHack.");
      }

      detected = 0;
   }

   public void postInit() {
      game.mcResourceManager.registerReloadListener(new TextureManager());
      game.getSoundHandler().setSoundLevel(SoundCategory.RADIO, 1.0F);
      (new Thread(this.soundThread)).start();
      this.customThread = new CustomThread(game.getSession().getUsername());
      (new Thread(this.customThread)).start();
   }

   public void log(Level lvl, String str) {
      LogManager.getLogger().log(lvl, "[ModInjector] " + str);
   }

   public Map<Core.EnumStuff, CustomStuff> getStuffs() {
      return this.stuffs;
   }

   public CustomStuff getStuff(Core.EnumStuff stuff) {
      return (CustomStuff)this.stuffs.get(stuff);
   }

   public Map<String, Method> getNetworkTrigger() {
      return this.network_trigger;
   }

   public Map<String, Block> getBlocks() {
      return this.blocks;
   }

   public Map<String, Item> getItems() {
      return this.items;
   }

   private static void setFinalStatic(Field field, Object newValue) throws Exception {
      field.setAccessible(true);
      Field modifiersField = Field.class.getDeclaredField("modifiers");
      modifiersField.setAccessible(true);
      modifiersField.setInt(field, field.getModifiers() & -17);
      field.set((Object)null, newValue);
   }

   public static enum EnumStuff {
      GARNET("GARNET", 0),
      AMETHYST("AMETHYST", 1),
      TITANIUM("TITANIUM", 2),
      OBSIDIUM("OBSIDIUM", 3),
      XENOTIUM("XENOTIUM", 4);

      private static final Core.EnumStuff[] $VALUES = new Core.EnumStuff[]{GARNET, AMETHYST, TITANIUM, OBSIDIUM, XENOTIUM};

      private EnumStuff(String var1, int var2) {}
   }
}
