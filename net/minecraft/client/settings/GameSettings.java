package net.minecraft.client.settings;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import fr.thisismac.injector.customclass.CustomSettings;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.stream.TwitchStream;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C15PacketClientSettings;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import optifine.ClearWater;
import optifine.Config;
import optifine.CustomColorizer;
import optifine.CustomSky;
import optifine.IWrUpdater;
import optifine.NaturalTextures;
import optifine.RandomMobs;
import optifine.Reflector;
import optifine.TextureUtils;
import optifine.WrUpdaterSmooth;
import optifine.WrUpdaterThreaded;
import optifine.WrUpdates;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public class GameSettings {
   private static final Logger logger = LogManager.getLogger();
   private static final Gson gson = new Gson();
   private static final ParameterizedType typeListString = new ParameterizedType() {
      private static final String __OBFID = "CL_00000651";
      private static final String __OBFID = "CL_00000651";

      public Type[] getActualTypeArguments() {
         return new Type[]{String.class};
      }
      public Type getRawType() {
         return List.class;
      }
      public Type getOwnerType() {
         return null;
      }
   };
   public static boolean showArmor;
   public static boolean showEffect;
   public static boolean showfps;
   public static boolean showcoords;
   public static boolean showfacing;
   public static boolean armorstyle;
   public static int armorPos;
   public static int effectPos;
   public static int facingPos;
   public static int affichage;
   public static int protectarmor_value;
   public static boolean showDeathMessage;
   private static final String[] GUISCALES = new String[]{"options.guiScale.auto", "options.guiScale.small", "options.guiScale.normal", "options.guiScale.large"};
   private static final String[] PARTICLES = new String[]{"options.particles.all", "options.particles.decreased", "options.particles.minimal"};
   private static final String[] AMBIENT_OCCLUSIONS = new String[]{"options.ao.off", "options.ao.min", "options.ao.max"};
   private static final String[] field_152391_aS = new String[]{"options.stream.compression.low", "options.stream.compression.medium", "options.stream.compression.high"};
   private static final String[] field_152392_aT = new String[]{"options.stream.chat.enabled.streaming", "options.stream.chat.enabled.always", "options.stream.chat.enabled.never"};
   private static final String[] field_152393_aU = new String[]{"options.stream.chat.userFilter.all", "options.stream.chat.userFilter.subs", "options.stream.chat.userFilter.mods"};
   private static final String[] field_152394_aV = new String[]{"options.stream.mic_toggle.mute", "options.stream.mic_toggle.talk"};
   public float mouseSensitivity = 0.5F;
   public boolean invertMouse;
   public int renderDistanceChunks = -1;
   public boolean viewBobbing = true;
   public boolean anaglyph;
   public boolean advancedOpengl;
   public boolean fboEnable = true;
   public int limitFramerate = 120;
   public boolean fancyGraphics = true;
   public int ambientOcclusion = 2;
   public int ofFogType = 1;
   public float ofFogStart = 0.8F;
   public int ofMipmapType = 0;
   public boolean ofLoadFar = false;
   public int ofPreloadedChunks = 0;
   public boolean ofOcclusionFancy = false;
   public boolean ofSmoothFps = false;
   public boolean ofSmoothWorld = Config.isSingleProcessor();
   public boolean ofLazyChunkLoading = Config.isSingleProcessor();
   public float ofAoLevel = 1.0F;
   public int ofAaLevel = 0;
   public int ofClouds = 0;
   public float ofCloudsHeight = 0.0F;
   public int ofTrees = 0;
   public int ofGrass = 0;
   public int ofRain = 0;
   public int ofWater = 0;
   public int ofDroppedItems = 0;
   public int ofBetterGrass = 3;
   public int ofAutoSaveTicks = 4000;
   public boolean ofLagometer = false;
   public boolean ofProfiler = false;
   public boolean ofShowFps = false;
   public boolean ofWeather = true;
   public boolean ofSky = true;
   public boolean ofStars = true;
   public boolean ofSunMoon = true;
   public int ofVignette = 0;
   public int ofChunkUpdates = 1;
   public int ofChunkLoading = 0;
   public boolean ofChunkUpdatesDynamic = false;
   public int ofTime = 0;
   public boolean ofClearWater = false;
   public boolean ofDepthFog = true;
   public boolean ofBetterSnow = false;
   public String ofFullscreenMode = "Default";
   public boolean ofSwampColors = true;
   public boolean ofRandomMobs = true;
   public boolean ofSmoothBiomes = true;
   public boolean ofCustomFonts = true;
   public boolean ofCustomColors = true;
   public boolean ofCustomSky = true;
   public boolean ofShowCapes = true;
   public int ofConnectedTextures = 2;
   public boolean ofNaturalTextures = false;
   public boolean ofFastMath = false;
   public boolean ofFastRender = false;
   public int ofTranslucentBlocks = 2;
   public int ofAnimatedWater = 0;
   public int ofAnimatedLava = 0;
   public boolean ofAnimatedFire = true;
   public boolean ofAnimatedPortal = true;
   public boolean ofAnimatedRedstone = true;
   public boolean ofAnimatedExplosion = true;
   public boolean ofAnimatedFlame = true;
   public boolean ofAnimatedSmoke = true;
   public boolean ofVoidParticles = true;
   public boolean ofWaterParticles = true;
   public boolean ofRainSplash = true;
   public boolean ofPortalParticles = true;
   public boolean ofPotionParticles = true;
   public boolean ofDrippingWaterLava = true;
   public boolean ofAnimatedTerrain = true;
   public boolean ofAnimatedItems = true;
   public boolean ofAnimatedTextures = true;
   public static final int DEFAULT = 0;
   public static final int FAST = 1;
   public static final int FANCY = 2;
   public static final int OFF = 3;
   public static final int ANIM_ON = 0;
   public static final int ANIM_GENERATED = 1;
   public static final int ANIM_OFF = 2;
   public static final int CL_DEFAULT = 0;
   public static final int CL_SMOOTH = 1;
   public static final int CL_THREADED = 2;
   public static final String DEFAULT_STR = "Default";
   public KeyBinding ofKeyBindZoom;
   private File optionsFileOF;
   public File optionsFileObsi;
   public boolean clouds = true;
   public List resourcePacks = new ArrayList();
   public EntityPlayer.EnumChatVisibility chatVisibility;
   public boolean chatColours;
   public boolean chatLinks;
   public boolean chatLinksPrompt;
   public float chatOpacity;
   public boolean snooperEnabled;
   public boolean fullScreen;
   public boolean enableVsync;
   public boolean hideServerAddress;
   public boolean advancedItemTooltips;
   public boolean pauseOnLostFocus;
   public boolean showCape;
   public boolean touchscreen;
   public int overrideWidth;
   public int overrideHeight;
   public boolean heldItemTooltips;
   public float chatScale;
   public float chatWidth;
   public float chatHeightUnfocused;
   public float chatHeightFocused;
   public boolean showInventoryAchievementHint;
   public int mipmapLevels;
   public int anisotropicFiltering;
   private Map mapSoundLevels;
   public float field_152400_J;
   public float field_152401_K;
   public float field_152402_L;
   public float field_152403_M;
   public float field_152404_N;
   public int field_152405_O;
   public boolean field_152406_P;
   public String field_152407_Q;
   public int field_152408_R;
   public int field_152409_S;
   public int field_152410_T;
   public KeyBinding keyBindForward;
   public KeyBinding keyBindLeft;
   public KeyBinding keyBindBack;
   public KeyBinding keyBindRight;
   public KeyBinding keyBindJump;
   public KeyBinding keyBindSneak;
   public KeyBinding keyBindInventory;
   public KeyBinding keyBindUseItem;
   public KeyBinding keyBindDrop;
   public KeyBinding keyBindAttack;
   public KeyBinding keyBindPickBlock;
   public KeyBinding keyBindSprint;
   public KeyBinding keyBindChat;
   public KeyBinding keyBindPlayerList;
   public KeyBinding keyBindCommand;
   public KeyBinding keyBindScreenshot;
   public KeyBinding keyBindTogglePerspective;
   public KeyBinding keyBindSmoothCamera;
   public KeyBinding field_152395_am;
   public KeyBinding field_152396_an;
   public KeyBinding field_152397_ao;
   public KeyBinding field_152398_ap;
   public KeyBinding field_152399_aq;
   public KeyBinding[] keyBindsHotbar;
   public KeyBinding[] keyBindings;
   protected Minecraft mc;
   public File optionsFile;
   public EnumDifficulty difficulty;
   public boolean hideGUI;
   public int thirdPersonView;
   public KeyBinding keymacro1;
   public KeyBinding keymacro2;
   public KeyBinding keymacro3;
   public KeyBinding keymacro4;
   public boolean showDebugInfo;
   public boolean showDebugProfilerChart;
   public String lastServer;
   public boolean noclip;
   public boolean smoothCamera;
   public boolean debugCamEnable;
   public float noclipRate;
   public float debugCamRate;
   public float fovSetting;
   public float gammaSetting;
   public float saturation;
   public int guiScale;
   public int particleSetting;
   public String language;
   public boolean forceUnicodeFont;
   private static final String __OBFID = "CL_00000650";
   private static final String __OBFID = "CL_00000650";

   public GameSettings(Minecraft par1Minecraft, File par2File) {
      this.chatVisibility = EntityPlayer.EnumChatVisibility.FULL;
      this.chatColours = true;
      this.chatLinks = true;
      this.chatLinksPrompt = true;
      this.chatOpacity = 1.0F;
      this.snooperEnabled = true;
      this.enableVsync = true;
      this.pauseOnLostFocus = true;
      this.showCape = true;
      this.heldItemTooltips = true;
      this.chatScale = 1.0F;
      this.chatWidth = 1.0F;
      this.chatHeightUnfocused = 0.44366196F;
      this.chatHeightFocused = 1.0F;
      this.showInventoryAchievementHint = true;
      this.mipmapLevels = 4;
      this.anisotropicFiltering = 1;
      this.mapSoundLevels = Maps.newEnumMap(SoundCategory.class);
      this.field_152400_J = 0.5F;
      this.field_152401_K = 1.0F;
      this.field_152402_L = 1.0F;
      this.field_152403_M = 0.5412844F;
      this.field_152404_N = 0.31690142F;
      this.field_152405_O = 1;
      this.field_152406_P = true;
      this.field_152407_Q = "";
      this.field_152408_R = 0;
      this.field_152409_S = 0;
      this.field_152410_T = 0;
      this.keyBindForward = new KeyBinding("key.forward", 17, "key.categories.movement");
      this.keyBindLeft = new KeyBinding("key.left", 30, "key.categories.movement");
      this.keyBindBack = new KeyBinding("key.back", 31, "key.categories.movement");
      this.keyBindRight = new KeyBinding("key.right", 32, "key.categories.movement");
      this.keyBindJump = new KeyBinding("key.jump", 57, "key.categories.movement");
      this.keyBindSneak = new KeyBinding("key.sneak", 42, "key.categories.movement");
      this.keyBindInventory = new KeyBinding("key.inventory", 18, "key.categories.inventory");
      this.keyBindUseItem = new KeyBinding("key.use", -99, "key.categories.gameplay");
      this.keyBindDrop = new KeyBinding("key.drop", 16, "key.categories.gameplay");
      this.keyBindAttack = new KeyBinding("key.attack", -100, "key.categories.gameplay");
      this.keyBindPickBlock = new KeyBinding("key.pickItem", -98, "key.categories.gameplay");
      this.keyBindSprint = new KeyBinding("key.sprint", 29, "key.categories.gameplay");
      this.keyBindChat = new KeyBinding("key.chat", 20, "key.categories.multiplayer");
      this.keyBindPlayerList = new KeyBinding("key.playerlist", 15, "key.categories.multiplayer");
      this.keyBindCommand = new KeyBinding("key.command", 53, "key.categories.multiplayer");
      this.keyBindScreenshot = new KeyBinding("key.screenshot", 60, "key.categories.misc");
      this.keyBindTogglePerspective = new KeyBinding("key.togglePerspective", 63, "key.categories.misc");
      this.keyBindSmoothCamera = new KeyBinding("key.smoothCamera", 0, "key.categories.misc");
      this.field_152395_am = new KeyBinding("key.fullscreen", 87, "key.categories.misc");
      this.field_152396_an = new KeyBinding("key.streamStartStop", 64, "key.categories.stream");
      this.field_152397_ao = new KeyBinding("key.streamPauseUnpause", 65, "key.categories.stream");
      this.field_152398_ap = new KeyBinding("key.streamCommercial", 0, "key.categories.stream");
      this.field_152399_aq = new KeyBinding("key.streamToggleMic", 0, "key.categories.stream");
      this.keyBindsHotbar = new KeyBinding[]{new KeyBinding("key.hotbar.1", 2, "key.categories.inventory"), new KeyBinding("key.hotbar.2", 3, "key.categories.inventory"), new KeyBinding("key.hotbar.3", 4, "key.categories.inventory"), new KeyBinding("key.hotbar.4", 5, "key.categories.inventory"), new KeyBinding("key.hotbar.5", 6, "key.categories.inventory"), new KeyBinding("key.hotbar.6", 7, "key.categories.inventory"), new KeyBinding("key.hotbar.7", 8, "key.categories.inventory"), new KeyBinding("key.hotbar.8", 9, "key.categories.inventory"), new KeyBinding("key.hotbar.9", 10, "key.categories.inventory")};
      this.keymacro1 = new KeyBinding("key.macro1", 0, "key.categories.multiplayer");
      this.keymacro2 = new KeyBinding("key.macro2", 0, "key.categories.multiplayer");
      this.keymacro3 = new KeyBinding("key.macro3", 0, "key.categories.multiplayer");
      this.keymacro4 = new KeyBinding("key.macro4", 0, "key.categories.multiplayer");
      this.keyBindings = (KeyBinding[])((KeyBinding[])((KeyBinding[])ArrayUtils.addAll(new KeyBinding[]{this.keyBindAttack, this.keymacro1, this.keymacro2, this.keymacro3, this.keymacro4, this.keyBindUseItem, this.keyBindForward, this.keyBindLeft, this.keyBindBack, this.keyBindRight, this.keyBindJump, this.keyBindSneak, this.keyBindDrop, this.keyBindInventory, this.keyBindChat, this.keyBindPlayerList, this.keyBindPickBlock, this.keyBindCommand, this.keyBindScreenshot, this.keyBindTogglePerspective, this.keyBindSmoothCamera, this.keyBindSprint, this.field_152396_an, this.field_152397_ao, this.field_152398_ap, this.field_152399_aq, this.field_152395_am}, this.keyBindsHotbar)));
      this.difficulty = EnumDifficulty.NORMAL;
      this.lastServer = "";
      this.noclipRate = 1.0F;
      this.debugCamRate = 1.0F;
      this.fovSetting = 70.0F;
      this.language = "en_US";
      this.forceUnicodeFont = false;
      this.mc = par1Minecraft;
      this.optionsFile = new File(par2File, "options.txt");
      this.optionsFileOF = new File(par2File, "optionsof.txt");
      this.limitFramerate = (int)GameSettings.Options.FRAMERATE_LIMIT.getValueMax();
      this.ofKeyBindZoom = new KeyBinding("Zoom", 29, "key.categories.misc");
      this.keyBindings = (KeyBinding[])((KeyBinding[])((KeyBinding[])ArrayUtils.addAll(this.keyBindings, new KeyBinding[]{this.ofKeyBindZoom})));
      GameSettings.Options.RENDER_DISTANCE.setValueMax(32.0F);
      this.renderDistanceChunks = par1Minecraft.isJava64bit()?12:8;
      this.loadOptions();
      Config.initGameSettings(this);
   }

   public GameSettings() {
      this.chatVisibility = EntityPlayer.EnumChatVisibility.FULL;
      this.chatColours = true;
      this.chatLinks = true;
      this.chatLinksPrompt = true;
      this.chatOpacity = 1.0F;
      this.snooperEnabled = true;
      this.enableVsync = true;
      this.pauseOnLostFocus = true;
      this.showCape = true;
      this.heldItemTooltips = true;
      this.chatScale = 1.0F;
      this.chatWidth = 1.0F;
      this.chatHeightUnfocused = 0.44366196F;
      this.chatHeightFocused = 1.0F;
      this.showInventoryAchievementHint = true;
      this.mipmapLevels = 4;
      this.anisotropicFiltering = 1;
      this.mapSoundLevels = Maps.newEnumMap(SoundCategory.class);
      this.field_152400_J = 0.5F;
      this.field_152401_K = 1.0F;
      this.field_152402_L = 1.0F;
      this.field_152403_M = 0.5412844F;
      this.field_152404_N = 0.31690142F;
      this.field_152405_O = 1;
      this.field_152406_P = true;
      this.field_152407_Q = "";
      this.field_152408_R = 0;
      this.field_152409_S = 0;
      this.field_152410_T = 0;
      this.keyBindForward = new KeyBinding("key.forward", 17, "key.categories.movement");
      this.keyBindLeft = new KeyBinding("key.left", 30, "key.categories.movement");
      this.keyBindBack = new KeyBinding("key.back", 31, "key.categories.movement");
      this.keyBindRight = new KeyBinding("key.right", 32, "key.categories.movement");
      this.keyBindJump = new KeyBinding("key.jump", 57, "key.categories.movement");
      this.keyBindSneak = new KeyBinding("key.sneak", 42, "key.categories.movement");
      this.keyBindInventory = new KeyBinding("key.inventory", 18, "key.categories.inventory");
      this.keyBindUseItem = new KeyBinding("key.use", -99, "key.categories.gameplay");
      this.keyBindDrop = new KeyBinding("key.drop", 16, "key.categories.gameplay");
      this.keyBindAttack = new KeyBinding("key.attack", -100, "key.categories.gameplay");
      this.keyBindPickBlock = new KeyBinding("key.pickItem", -98, "key.categories.gameplay");
      this.keyBindSprint = new KeyBinding("key.sprint", 29, "key.categories.gameplay");
      this.keyBindChat = new KeyBinding("key.chat", 20, "key.categories.multiplayer");
      this.keyBindPlayerList = new KeyBinding("key.playerlist", 15, "key.categories.multiplayer");
      this.keyBindCommand = new KeyBinding("key.command", 53, "key.categories.multiplayer");
      this.keyBindScreenshot = new KeyBinding("key.screenshot", 60, "key.categories.misc");
      this.keyBindTogglePerspective = new KeyBinding("key.togglePerspective", 63, "key.categories.misc");
      this.keyBindSmoothCamera = new KeyBinding("key.smoothCamera", 0, "key.categories.misc");
      this.field_152395_am = new KeyBinding("key.fullscreen", 87, "key.categories.misc");
      this.field_152396_an = new KeyBinding("key.streamStartStop", 64, "key.categories.stream");
      this.field_152397_ao = new KeyBinding("key.streamPauseUnpause", 65, "key.categories.stream");
      this.field_152398_ap = new KeyBinding("key.streamCommercial", 0, "key.categories.stream");
      this.field_152399_aq = new KeyBinding("key.streamToggleMic", 0, "key.categories.stream");
      this.keyBindsHotbar = new KeyBinding[]{new KeyBinding("key.hotbar.1", 2, "key.categories.inventory"), new KeyBinding("key.hotbar.2", 3, "key.categories.inventory"), new KeyBinding("key.hotbar.3", 4, "key.categories.inventory"), new KeyBinding("key.hotbar.4", 5, "key.categories.inventory"), new KeyBinding("key.hotbar.5", 6, "key.categories.inventory"), new KeyBinding("key.hotbar.6", 7, "key.categories.inventory"), new KeyBinding("key.hotbar.7", 8, "key.categories.inventory"), new KeyBinding("key.hotbar.8", 9, "key.categories.inventory"), new KeyBinding("key.hotbar.9", 10, "key.categories.inventory")};
      this.keyBindings = (KeyBinding[])((KeyBinding[])((KeyBinding[])ArrayUtils.addAll(new KeyBinding[]{this.keyBindAttack, this.keyBindUseItem, this.keyBindForward, this.keyBindLeft, this.keyBindBack, this.keyBindRight, this.keyBindJump, this.keyBindSneak, this.keyBindDrop, this.keyBindInventory, this.keyBindChat, this.keyBindPlayerList, this.keyBindPickBlock, this.keyBindCommand, this.keyBindScreenshot, this.keyBindTogglePerspective, this.keyBindSmoothCamera, this.keyBindSprint, this.field_152396_an, this.field_152397_ao, this.field_152398_ap, this.field_152399_aq, this.field_152395_am}, this.keyBindsHotbar)));
      this.difficulty = EnumDifficulty.NORMAL;
      this.lastServer = "";
      this.noclipRate = 1.0F;
      this.debugCamRate = 1.0F;
      this.fovSetting = 70.0F;
      this.language = "en_US";
      this.forceUnicodeFont = false;
      this.limitFramerate = (int)GameSettings.Options.FRAMERATE_LIMIT.getValueMax();
      this.keyBindings = (KeyBinding[])((KeyBinding[])((KeyBinding[])ArrayUtils.addAll(this.keyBindings, new KeyBinding[]{this.ofKeyBindZoom})));
   }

   public static String getKeyDisplayString(int par0) {
      return par0 < 0?I18n.format("key.mouseButton", new Object[]{Integer.valueOf(par0 + 101)}):Keyboard.getKeyName(par0);
   }

   public static boolean isKeyDown(KeyBinding par0KeyBinding) {
      return par0KeyBinding.getKeyCode() == 0?false:(par0KeyBinding.getKeyCode() < 0?Mouse.isButtonDown(par0KeyBinding.getKeyCode() + 100):Keyboard.isKeyDown(par0KeyBinding.getKeyCode()));
   }

   public void setKeyCodeSave(KeyBinding p_151440_1_, int p_151440_2_) {
      p_151440_1_.setKeyCode(p_151440_2_);
      this.saveOptions();
   }

   public void setOptionFloatValue(GameSettings.Options par1EnumOptions, float par2) {
      if(par1EnumOptions == GameSettings.Options.CLOUD_HEIGHT) {
         this.ofCloudsHeight = par2;
      }

      if(par1EnumOptions == GameSettings.Options.AO_LEVEL) {
         this.ofAoLevel = par2;
         this.mc.renderGlobal.loadRenderers();
      }

      if(par1EnumOptions == GameSettings.Options.SENSITIVITY) {
         this.mouseSensitivity = par2;
      }

      if(par1EnumOptions == GameSettings.Options.FOV) {
         this.fovSetting = par2;
      }

      if(par1EnumOptions == GameSettings.Options.GAMMA) {
         this.gammaSetting = par2;
      }

      if(par1EnumOptions == GameSettings.Options.FRAMERATE_LIMIT) {
         this.limitFramerate = (int)par2;
         this.enableVsync = false;
         if(this.limitFramerate <= 0) {
            this.limitFramerate = (int)GameSettings.Options.FRAMERATE_LIMIT.getValueMax();
            this.enableVsync = true;
         }

         this.updateVSync();
      }

      if(par1EnumOptions == GameSettings.Options.CHAT_OPACITY) {
         this.chatOpacity = par2;
         this.mc.ingameGUI.getChatGUI().func_146245_b();
      }

      if(par1EnumOptions == GameSettings.Options.CHAT_HEIGHT_FOCUSED) {
         this.chatHeightFocused = par2;
         this.mc.ingameGUI.getChatGUI().func_146245_b();
      }

      if(par1EnumOptions == GameSettings.Options.CHAT_HEIGHT_UNFOCUSED) {
         this.chatHeightUnfocused = par2;
         this.mc.ingameGUI.getChatGUI().func_146245_b();
      }

      if(par1EnumOptions == GameSettings.Options.CHAT_WIDTH) {
         this.chatWidth = par2;
         this.mc.ingameGUI.getChatGUI().func_146245_b();
      }

      if(par1EnumOptions == GameSettings.Options.CHAT_SCALE) {
         this.chatScale = par2;
         this.mc.ingameGUI.getChatGUI().func_146245_b();
      }

      int var3;
      if(par1EnumOptions == GameSettings.Options.ANISOTROPIC_FILTERING) {
         var3 = this.anisotropicFiltering;
         this.anisotropicFiltering = (int)par2;
         if((float)var3 != par2) {
            this.mc.getTextureMapBlocks().func_147632_b(this.anisotropicFiltering);
            this.mc.scheduleResourcesRefresh();
         }
      }

      if(par1EnumOptions == GameSettings.Options.MIPMAP_LEVELS) {
         var3 = this.mipmapLevels;
         this.mipmapLevels = (int)par2;
         if((float)var3 != par2) {
            this.mc.getTextureMapBlocks().func_147633_a(this.mipmapLevels);
            this.mc.scheduleResourcesRefresh();
         }
      }

      if(par1EnumOptions == GameSettings.Options.RENDER_DISTANCE) {
         this.renderDistanceChunks = (int)par2;
      }

      if(par1EnumOptions == GameSettings.Options.STREAM_BYTES_PER_PIXEL) {
         this.field_152400_J = par2;
      }

      if(par1EnumOptions == GameSettings.Options.STREAM_VOLUME_MIC) {
         this.field_152401_K = par2;
         this.mc.func_152346_Z().func_152915_s();
      }

      if(par1EnumOptions == GameSettings.Options.STREAM_VOLUME_SYSTEM) {
         this.field_152402_L = par2;
         this.mc.func_152346_Z().func_152915_s();
      }

      if(par1EnumOptions == GameSettings.Options.STREAM_KBPS) {
         this.field_152403_M = par2;
      }

      if(par1EnumOptions == GameSettings.Options.STREAM_FPS) {
         this.field_152404_N = par2;
      }
   }

   public void setOptionValue(GameSettings.Options par1EnumOptions, int par2) {
      if(par1EnumOptions == GameSettings.Options.FOG_FANCY) {
         switch(this.ofFogType) {
         case 1:
            this.ofFogType = 2;
            if(!Config.isFancyFogAvailable()) {
               this.ofFogType = 3;
            }
            break;
         case 2:
            this.ofFogType = 3;
            break;
         case 3:
            this.ofFogType = 1;
            break;
         default:
            this.ofFogType = 1;
         }
      }

      if(par1EnumOptions == GameSettings.Options.FOG_START) {
         this.ofFogStart += 0.2F;
         if(this.ofFogStart > 0.81F) {
            this.ofFogStart = 0.2F;
         }
      }

      if(par1EnumOptions == GameSettings.Options.MIPMAP_TYPE) {
         ++this.ofMipmapType;
         if(this.ofMipmapType > 3) {
            this.ofMipmapType = 0;
         }

         TextureUtils.refreshBlockTextures();
      }

      if(par1EnumOptions == GameSettings.Options.LOAD_FAR) {
         this.ofLoadFar = !this.ofLoadFar;
         this.mc.renderGlobal.loadRenderers();
      }

      if(par1EnumOptions == GameSettings.Options.PRELOADED_CHUNKS) {
         this.ofPreloadedChunks += 2;
         if(this.ofPreloadedChunks > 8) {
            this.ofPreloadedChunks = 0;
         }

         this.mc.renderGlobal.loadRenderers();
      }

      if(par1EnumOptions == GameSettings.Options.SMOOTH_FPS) {
         this.ofSmoothFps = !this.ofSmoothFps;
      }

      if(par1EnumOptions == GameSettings.Options.SMOOTH_WORLD) {
         this.ofSmoothWorld = !this.ofSmoothWorld;
         Config.updateAvailableProcessors();
         if(!Config.isSingleProcessor()) {
            this.ofSmoothWorld = false;
         }

         Config.updateThreadPriorities();
      }

      if(par1EnumOptions == GameSettings.Options.CLOUDS) {
         ++this.ofClouds;
         if(this.ofClouds > 3) {
            this.ofClouds = 0;
         }
      }

      if(par1EnumOptions == GameSettings.Options.TREES) {
         ++this.ofTrees;
         if(this.ofTrees > 2) {
            this.ofTrees = 0;
         }

         this.mc.renderGlobal.loadRenderers();
      }

      if(par1EnumOptions == GameSettings.Options.GRASS) {
         ++this.ofGrass;
         if(this.ofGrass > 2) {
            this.ofGrass = 0;
         }

         RenderBlocks.fancyGrass = Config.isGrassFancy();
         this.mc.renderGlobal.loadRenderers();
      }

      if(par1EnumOptions == GameSettings.Options.DROPPED_ITEMS) {
         ++this.ofDroppedItems;
         if(this.ofDroppedItems > 2) {
            this.ofDroppedItems = 0;
         }
      }

      if(par1EnumOptions == GameSettings.Options.RAIN) {
         ++this.ofRain;
         if(this.ofRain > 3) {
            this.ofRain = 0;
         }
      }

      if(par1EnumOptions == GameSettings.Options.WATER) {
         ++this.ofWater;
         if(this.ofWater > 2) {
            this.ofWater = 0;
         }
      }

      if(par1EnumOptions == GameSettings.Options.ANIMATED_WATER) {
         ++this.ofAnimatedWater;
         if(this.ofAnimatedWater > 2) {
            this.ofAnimatedWater = 0;
         }
      }

      if(par1EnumOptions == GameSettings.Options.ANIMATED_LAVA) {
         ++this.ofAnimatedLava;
         if(this.ofAnimatedLava > 2) {
            this.ofAnimatedLava = 0;
         }
      }

      if(par1EnumOptions == GameSettings.Options.ANIMATED_FIRE) {
         this.ofAnimatedFire = !this.ofAnimatedFire;
      }

      if(par1EnumOptions == GameSettings.Options.ANIMATED_PORTAL) {
         this.ofAnimatedPortal = !this.ofAnimatedPortal;
      }

      if(par1EnumOptions == GameSettings.Options.ANIMATED_REDSTONE) {
         this.ofAnimatedRedstone = !this.ofAnimatedRedstone;
      }

      if(par1EnumOptions == GameSettings.Options.ANIMATED_EXPLOSION) {
         this.ofAnimatedExplosion = !this.ofAnimatedExplosion;
      }

      if(par1EnumOptions == GameSettings.Options.ANIMATED_FLAME) {
         this.ofAnimatedFlame = !this.ofAnimatedFlame;
      }

      if(par1EnumOptions == GameSettings.Options.ANIMATED_SMOKE) {
         this.ofAnimatedSmoke = !this.ofAnimatedSmoke;
      }

      if(par1EnumOptions == GameSettings.Options.VOID_PARTICLES) {
         this.ofVoidParticles = !this.ofVoidParticles;
      }

      if(par1EnumOptions == GameSettings.Options.WATER_PARTICLES) {
         this.ofWaterParticles = !this.ofWaterParticles;
      }

      if(par1EnumOptions == GameSettings.Options.PORTAL_PARTICLES) {
         this.ofPortalParticles = !this.ofPortalParticles;
      }

      if(par1EnumOptions == GameSettings.Options.POTION_PARTICLES) {
         this.ofPotionParticles = !this.ofPotionParticles;
      }

      if(par1EnumOptions == GameSettings.Options.DRIPPING_WATER_LAVA) {
         this.ofDrippingWaterLava = !this.ofDrippingWaterLava;
      }

      if(par1EnumOptions == GameSettings.Options.ANIMATED_TERRAIN) {
         this.ofAnimatedTerrain = !this.ofAnimatedTerrain;
      }

      if(par1EnumOptions == GameSettings.Options.ANIMATED_TEXTURES) {
         this.ofAnimatedTextures = !this.ofAnimatedTextures;
      }

      if(par1EnumOptions == GameSettings.Options.ANIMATED_ITEMS) {
         this.ofAnimatedItems = !this.ofAnimatedItems;
      }

      if(par1EnumOptions == GameSettings.Options.RAIN_SPLASH) {
         this.ofRainSplash = !this.ofRainSplash;
      }

      if(par1EnumOptions == GameSettings.Options.LAGOMETER) {
         this.ofLagometer = !this.ofLagometer;
      }

      if(par1EnumOptions == GameSettings.Options.SHOW_FPS) {
         this.ofShowFps = !this.ofShowFps;
      }

      if(par1EnumOptions == GameSettings.Options.AUTOSAVE_TICKS) {
         this.ofAutoSaveTicks *= 10;
         if(this.ofAutoSaveTicks > 40000) {
            this.ofAutoSaveTicks = 40;
         }
      }

      if(par1EnumOptions == GameSettings.Options.BETTER_GRASS) {
         ++this.ofBetterGrass;
         if(this.ofBetterGrass > 3) {
            this.ofBetterGrass = 1;
         }

         this.mc.renderGlobal.loadRenderers();
      }

      if(par1EnumOptions == GameSettings.Options.CONNECTED_TEXTURES) {
         ++this.ofConnectedTextures;
         if(this.ofConnectedTextures > 3) {
            this.ofConnectedTextures = 1;
         }

         this.mc.renderGlobal.loadRenderers();
      }

      if(par1EnumOptions == GameSettings.Options.WEATHER) {
         this.ofWeather = !this.ofWeather;
      }

      if(par1EnumOptions == GameSettings.Options.SKY) {
         this.ofSky = !this.ofSky;
      }

      if(par1EnumOptions == GameSettings.Options.STARS) {
         this.ofStars = !this.ofStars;
      }

      if(par1EnumOptions == GameSettings.Options.SUN_MOON) {
         this.ofSunMoon = !this.ofSunMoon;
      }

      if(par1EnumOptions == GameSettings.Options.VIGNETTE) {
         ++this.ofVignette;
         if(this.ofVignette > 2) {
            this.ofVignette = 0;
         }
      }

      if(par1EnumOptions == GameSettings.Options.CHUNK_UPDATES) {
         ++this.ofChunkUpdates;
         if(this.ofChunkUpdates > 5) {
            this.ofChunkUpdates = 1;
         }
      }

      if(par1EnumOptions == GameSettings.Options.CHUNK_LOADING) {
         ++this.ofChunkLoading;
         if(this.ofChunkLoading > 2) {
            this.ofChunkLoading = 0;
         }

         this.updateChunkLoading();
      }

      if(par1EnumOptions == GameSettings.Options.CHUNK_UPDATES_DYNAMIC) {
         this.ofChunkUpdatesDynamic = !this.ofChunkUpdatesDynamic;
      }

      if(par1EnumOptions == GameSettings.Options.TIME) {
         ++this.ofTime;
         if(this.ofTime > 3) {
            this.ofTime = 0;
         }
      }

      if(par1EnumOptions == GameSettings.Options.CLEAR_WATER) {
         this.ofClearWater = !this.ofClearWater;
         this.updateWaterOpacity();
      }

      if(par1EnumOptions == GameSettings.Options.DEPTH_FOG) {
         this.ofDepthFog = !this.ofDepthFog;
      }

      if(par1EnumOptions == GameSettings.Options.AA_LEVEL) {
         this.ofAaLevel = 0;
      }

      if(par1EnumOptions == GameSettings.Options.PROFILER) {
         this.ofProfiler = !this.ofProfiler;
      }

      if(par1EnumOptions == GameSettings.Options.BETTER_SNOW) {
         this.ofBetterSnow = !this.ofBetterSnow;
         this.mc.renderGlobal.loadRenderers();
      }

      if(par1EnumOptions == GameSettings.Options.SWAMP_COLORS) {
         this.ofSwampColors = !this.ofSwampColors;
         CustomColorizer.updateUseDefaultColorMultiplier();
         this.mc.renderGlobal.loadRenderers();
      }

      if(par1EnumOptions == GameSettings.Options.RANDOM_MOBS) {
         this.ofRandomMobs = !this.ofRandomMobs;
         RandomMobs.resetTextures();
      }

      if(par1EnumOptions == GameSettings.Options.SMOOTH_BIOMES) {
         this.ofSmoothBiomes = !this.ofSmoothBiomes;
         CustomColorizer.updateUseDefaultColorMultiplier();
         this.mc.renderGlobal.loadRenderers();
      }

      if(par1EnumOptions == GameSettings.Options.CUSTOM_FONTS) {
         this.ofCustomFonts = !this.ofCustomFonts;
         this.mc.fontRenderer.onResourceManagerReload(Config.getResourceManager());
         this.mc.standardGalacticFontRenderer.onResourceManagerReload(Config.getResourceManager());
      }

      if(par1EnumOptions == GameSettings.Options.CUSTOM_COLORS) {
         this.ofCustomColors = !this.ofCustomColors;
         CustomColorizer.update();
         this.mc.renderGlobal.loadRenderers();
      }

      if(par1EnumOptions == GameSettings.Options.CUSTOM_SKY) {
         this.ofCustomSky = !this.ofCustomSky;
         CustomSky.update();
      }

      if(par1EnumOptions == GameSettings.Options.SHOW_CAPES) {
         this.ofShowCapes = !this.ofShowCapes;
         this.mc.renderGlobal.updateCapes();
      }

      if(par1EnumOptions == GameSettings.Options.NATURAL_TEXTURES) {
         this.ofNaturalTextures = !this.ofNaturalTextures;
         NaturalTextures.update();
         this.mc.renderGlobal.loadRenderers();
      }

      if(par1EnumOptions == GameSettings.Options.FAST_MATH) {
         this.ofFastMath = !this.ofFastMath;
         MathHelper.fastMath = this.ofFastMath;
      }

      if(par1EnumOptions == GameSettings.Options.FAST_RENDER) {
         this.ofFastRender = !this.ofFastRender;
         if(this.ofFastRender) {
            this.mc.entityRenderer.stopUseShader();
         }

         Config.updateFramebufferSize();
         this.mc.renderGlobal.loadRenderers();
      }

      if(par1EnumOptions == GameSettings.Options.TRANSLUCENT_BLOCKS) {
         if(this.ofTranslucentBlocks == 1) {
            this.ofTranslucentBlocks = 2;
         } else {
            this.ofTranslucentBlocks = 1;
         }

         this.mc.renderGlobal.loadRenderers();
      }

      if(par1EnumOptions == GameSettings.Options.LAZY_CHUNK_LOADING) {
         this.ofLazyChunkLoading = !this.ofLazyChunkLoading;
         Config.updateAvailableProcessors();
         if(!Config.isSingleProcessor()) {
            this.ofLazyChunkLoading = false;
         }

         this.mc.renderGlobal.loadRenderers();
      }

      if(par1EnumOptions == GameSettings.Options.FULLSCREEN_MODE) {
         List modeList = Arrays.asList(Config.getFullscreenModes());
         if(this.ofFullscreenMode.equals("Default")) {
            this.ofFullscreenMode = (String)modeList.get(0);
         } else {
            int index = modeList.indexOf(this.ofFullscreenMode);
            if(index < 0) {
               this.ofFullscreenMode = "Default";
            } else {
               ++index;
               if(index >= modeList.size()) {
                  this.ofFullscreenMode = "Default";
               } else {
                  this.ofFullscreenMode = (String)modeList.get(index);
               }
            }
         }
      }

      if(par1EnumOptions == GameSettings.Options.HELD_ITEM_TOOLTIPS) {
         this.heldItemTooltips = !this.heldItemTooltips;
      }

      if(par1EnumOptions == GameSettings.Options.INVERT_MOUSE) {
         this.invertMouse = !this.invertMouse;
      }

      if(par1EnumOptions == GameSettings.Options.GUI_SCALE) {
         this.guiScale = this.guiScale + par2 & 3;
      }

      if(par1EnumOptions == GameSettings.Options.PARTICLES) {
         this.particleSetting = (this.particleSetting + par2) % 3;
      }

      if(par1EnumOptions == GameSettings.Options.VIEW_BOBBING) {
         this.viewBobbing = !this.viewBobbing;
      }

      if(par1EnumOptions == GameSettings.Options.RENDER_CLOUDS) {
         this.clouds = !this.clouds;
      }

      if(par1EnumOptions == GameSettings.Options.FORCE_UNICODE_FONT) {
         this.forceUnicodeFont = !this.forceUnicodeFont;
         this.mc.fontRenderer.setUnicodeFlag(this.mc.getLanguageManager().isCurrentLocaleUnicode() || this.forceUnicodeFont);
      }

      if(par1EnumOptions == GameSettings.Options.ADVANCED_OPENGL) {
         if(!Config.isOcclusionAvailable()) {
            this.ofOcclusionFancy = false;
            this.advancedOpengl = false;
         } else if(!this.advancedOpengl) {
            this.advancedOpengl = true;
            this.ofOcclusionFancy = false;
         } else if(!this.ofOcclusionFancy) {
            this.ofOcclusionFancy = true;
         } else {
            this.ofOcclusionFancy = false;
            this.advancedOpengl = false;
         }

         this.mc.renderGlobal.setAllRenderersVisible();
      }

      if(par1EnumOptions == GameSettings.Options.FBO_ENABLE) {
         this.fboEnable = !this.fboEnable;
      }

      if(par1EnumOptions == GameSettings.Options.ANAGLYPH) {
         this.anaglyph = !this.anaglyph;
         this.mc.refreshResources();
      }

      if(par1EnumOptions == GameSettings.Options.DIFFICULTY) {
         this.difficulty = EnumDifficulty.getDifficultyEnum(this.difficulty.getDifficultyId() + par2 & 3);
      }

      if(par1EnumOptions == GameSettings.Options.GRAPHICS) {
         this.fancyGraphics = !this.fancyGraphics;
         this.mc.renderGlobal.loadRenderers();
      }

      if(par1EnumOptions == GameSettings.Options.AMBIENT_OCCLUSION) {
         this.ambientOcclusion = (this.ambientOcclusion + par2) % 3;
         this.mc.renderGlobal.loadRenderers();
      }

      if(par1EnumOptions == GameSettings.Options.CHAT_VISIBILITY) {
         this.chatVisibility = EntityPlayer.EnumChatVisibility.getEnumChatVisibility((this.chatVisibility.getChatVisibility() + par2) % 3);
      }

      if(par1EnumOptions == GameSettings.Options.STREAM_COMPRESSION) {
         this.field_152405_O = (this.field_152405_O + par2) % 3;
      }

      if(par1EnumOptions == GameSettings.Options.STREAM_SEND_METADATA) {
         this.field_152406_P = !this.field_152406_P;
      }

      if(par1EnumOptions == GameSettings.Options.STREAM_CHAT_ENABLED) {
         this.field_152408_R = (this.field_152408_R + par2) % 3;
      }

      if(par1EnumOptions == GameSettings.Options.STREAM_CHAT_USER_FILTER) {
         this.field_152409_S = (this.field_152409_S + par2) % 3;
      }

      if(par1EnumOptions == GameSettings.Options.STREAM_MIC_TOGGLE_BEHAVIOR) {
         this.field_152410_T = (this.field_152410_T + par2) % 2;
      }

      if(par1EnumOptions == GameSettings.Options.CHAT_COLOR) {
         this.chatColours = !this.chatColours;
      }

      if(par1EnumOptions == GameSettings.Options.CHAT_LINKS) {
         this.chatLinks = !this.chatLinks;
      }

      if(par1EnumOptions == GameSettings.Options.CHAT_LINKS_PROMPT) {
         this.chatLinksPrompt = !this.chatLinksPrompt;
      }

      if(par1EnumOptions == GameSettings.Options.SNOOPER_ENABLED) {
         this.snooperEnabled = !this.snooperEnabled;
      }

      if(par1EnumOptions == GameSettings.Options.SHOW_CAPE) {
         this.showCape = !this.showCape;
      }

      if(par1EnumOptions == GameSettings.Options.TOUCHSCREEN) {
         this.touchscreen = !this.touchscreen;
      }

      if(par1EnumOptions == GameSettings.Options.USE_FULLSCREEN) {
         this.fullScreen = !this.fullScreen;
         if(this.mc.isFullScreen() != this.fullScreen) {
            this.mc.toggleFullscreen();
         }
      }

      if(par1EnumOptions == GameSettings.Options.ENABLE_VSYNC) {
         this.enableVsync = !this.enableVsync;
         Display.setVSyncEnabled(this.enableVsync);
      }

      this.saveOptions();
   }

   public float getOptionFloatValue(GameSettings.Options par1EnumOptions) {
      return par1EnumOptions == GameSettings.Options.CLOUD_HEIGHT?this.ofCloudsHeight:(par1EnumOptions == GameSettings.Options.AO_LEVEL?this.ofAoLevel:(par1EnumOptions == GameSettings.Options.FRAMERATE_LIMIT?((float)this.limitFramerate == GameSettings.Options.FRAMERATE_LIMIT.getValueMax() && this.enableVsync?0.0F:(float)this.limitFramerate):(par1EnumOptions == GameSettings.Options.FOV?this.fovSetting:(par1EnumOptions == GameSettings.Options.GAMMA?this.gammaSetting:(par1EnumOptions == GameSettings.Options.SATURATION?this.saturation:(par1EnumOptions == GameSettings.Options.SENSITIVITY?this.mouseSensitivity:(par1EnumOptions == GameSettings.Options.CHAT_OPACITY?this.chatOpacity:(par1EnumOptions == GameSettings.Options.CHAT_HEIGHT_FOCUSED?this.chatHeightFocused:(par1EnumOptions == GameSettings.Options.CHAT_HEIGHT_UNFOCUSED?this.chatHeightUnfocused:(par1EnumOptions == GameSettings.Options.CHAT_SCALE?this.chatScale:(par1EnumOptions == GameSettings.Options.CHAT_WIDTH?this.chatWidth:(par1EnumOptions == GameSettings.Options.FRAMERATE_LIMIT?(float)this.limitFramerate:(par1EnumOptions == GameSettings.Options.ANISOTROPIC_FILTERING?(float)this.anisotropicFiltering:(par1EnumOptions == GameSettings.Options.MIPMAP_LEVELS?(float)this.mipmapLevels:(par1EnumOptions == GameSettings.Options.RENDER_DISTANCE?(float)this.renderDistanceChunks:(par1EnumOptions == GameSettings.Options.STREAM_BYTES_PER_PIXEL?this.field_152400_J:(par1EnumOptions == GameSettings.Options.STREAM_VOLUME_MIC?this.field_152401_K:(par1EnumOptions == GameSettings.Options.STREAM_VOLUME_SYSTEM?this.field_152402_L:(par1EnumOptions == GameSettings.Options.STREAM_KBPS?this.field_152403_M:(par1EnumOptions == GameSettings.Options.STREAM_FPS?this.field_152404_N:0.0F))))))))))))))))))));
   }

   public boolean getOptionOrdinalValue(GameSettings.Options par1EnumOptions) {
      switch(GameSettings.SwitchOptions.optionIds[par1EnumOptions.ordinal()]) {
      case 1:
         return this.invertMouse;
      case 2:
         return this.viewBobbing;
      case 3:
         return this.anaglyph;
      case 4:
         return this.advancedOpengl;
      case 5:
         return this.fboEnable;
      case 6:
         return this.clouds;
      case 7:
         return this.chatColours;
      case 8:
         return this.chatLinks;
      case 9:
         return this.chatLinksPrompt;
      case 10:
         return this.snooperEnabled;
      case 11:
         return this.fullScreen;
      case 12:
         return this.enableVsync;
      case 13:
         return this.showCape;
      case 14:
         return this.touchscreen;
      case 15:
         return this.field_152406_P;
      case 16:
         return this.forceUnicodeFont;
      default:
         return false;
      }
   }

   private static String getTranslation(String[] par0ArrayOfStr, int par1) {
      if(par1 < 0 || par1 >= par0ArrayOfStr.length) {
         par1 = 0;
      }

      return I18n.format(par0ArrayOfStr[par1], new Object[0]);
   }

   public String getKeyBinding(GameSettings.Options par1EnumOptions) {
      String var2 = I18n.format(par1EnumOptions.getEnumString(), new Object[0]) + ": ";
      if(var2 == null) {
         var2 = par1EnumOptions.getEnumString();
      }

      String var3;
      if(par1EnumOptions == GameSettings.Options.RENDER_DISTANCE) {
         int var321 = (int)this.getOptionFloatValue(par1EnumOptions);
         var3 = "Tiny";
         byte baseDist = 2;
         if(var321 >= 4) {
            var3 = "Short";
            baseDist = 4;
         }

         if(var321 >= 8) {
            var3 = "Normal";
            baseDist = 8;
         }

         if(var321 >= 16) {
            var3 = "Far";
            baseDist = 16;
         }

         if(var321 >= 32) {
            var3 = "Extreme";
            baseDist = 32;
         }

         int diff = this.renderDistanceChunks - baseDist;
         String descr = var3;
         if(diff > 0) {
            descr = var3 + "+";
         }

         return var2 + var321 + " " + descr + "";
      } else if(par1EnumOptions == GameSettings.Options.ADVANCED_OPENGL) {
         return !this.advancedOpengl?var2 + "OFF":(this.ofOcclusionFancy?var2 + "Fancy":var2 + "Fast");
      } else if(par1EnumOptions == GameSettings.Options.FOG_FANCY) {
         switch(this.ofFogType) {
         case 1:
            return var2 + "Fast";
         case 2:
            return var2 + "Fancy";
         case 3:
            return var2 + "OFF";
         default:
            return var2 + "OFF";
         }
      } else if(par1EnumOptions == GameSettings.Options.FOG_START) {
         return var2 + this.ofFogStart;
      } else if(par1EnumOptions == GameSettings.Options.MIPMAP_TYPE) {
         switch(this.ofMipmapType) {
         case 0:
            return var2 + "Nearest";
         case 1:
            return var2 + "Linear";
         case 2:
            return var2 + "Bilinear";
         case 3:
            return var2 + "Trilinear";
         default:
            return var2 + "Nearest";
         }
      } else if(par1EnumOptions == GameSettings.Options.LOAD_FAR) {
         return this.ofLoadFar?var2 + "ON":var2 + "OFF";
      } else if(par1EnumOptions == GameSettings.Options.PRELOADED_CHUNKS) {
         return this.ofPreloadedChunks == 0?var2 + "OFF":var2 + this.ofPreloadedChunks;
      } else if(par1EnumOptions == GameSettings.Options.SMOOTH_FPS) {
         return this.ofSmoothFps?var2 + "ON":var2 + "OFF";
      } else if(par1EnumOptions == GameSettings.Options.SMOOTH_WORLD) {
         return this.ofSmoothWorld?var2 + "ON":var2 + "OFF";
      } else if(par1EnumOptions == GameSettings.Options.CLOUDS) {
         switch(this.ofClouds) {
         case 1:
            return var2 + "Fast";
         case 2:
            return var2 + "Fancy";
         case 3:
            return var2 + "OFF";
         default:
            return var2 + "Default";
         }
      } else if(par1EnumOptions == GameSettings.Options.TREES) {
         switch(this.ofTrees) {
         case 1:
            return var2 + "Fast";
         case 2:
            return var2 + "Fancy";
         default:
            return var2 + "Default";
         }
      } else if(par1EnumOptions == GameSettings.Options.GRASS) {
         switch(this.ofGrass) {
         case 1:
            return var2 + "Fast";
         case 2:
            return var2 + "Fancy";
         default:
            return var2 + "Default";
         }
      } else if(par1EnumOptions == GameSettings.Options.DROPPED_ITEMS) {
         switch(this.ofDroppedItems) {
         case 1:
            return var2 + "Fast";
         case 2:
            return var2 + "Fancy";
         default:
            return var2 + "Default";
         }
      } else if(par1EnumOptions == GameSettings.Options.RAIN) {
         switch(this.ofRain) {
         case 1:
            return var2 + "Fast";
         case 2:
            return var2 + "Fancy";
         case 3:
            return var2 + "OFF";
         default:
            return var2 + "Default";
         }
      } else if(par1EnumOptions == GameSettings.Options.WATER) {
         switch(this.ofWater) {
         case 1:
            return var2 + "Fast";
         case 2:
            return var2 + "Fancy";
         case 3:
            return var2 + "OFF";
         default:
            return var2 + "Default";
         }
      } else if(par1EnumOptions == GameSettings.Options.ANIMATED_WATER) {
         switch(this.ofAnimatedWater) {
         case 1:
            return var2 + "Dynamic";
         case 2:
            return var2 + "OFF";
         default:
            return var2 + "ON";
         }
      } else if(par1EnumOptions == GameSettings.Options.ANIMATED_LAVA) {
         switch(this.ofAnimatedLava) {
         case 1:
            return var2 + "Dynamic";
         case 2:
            return var2 + "OFF";
         default:
            return var2 + "ON";
         }
      } else if(par1EnumOptions == GameSettings.Options.ANIMATED_FIRE) {
         return this.ofAnimatedFire?var2 + "ON":var2 + "OFF";
      } else if(par1EnumOptions == GameSettings.Options.ANIMATED_PORTAL) {
         return this.ofAnimatedPortal?var2 + "ON":var2 + "OFF";
      } else if(par1EnumOptions == GameSettings.Options.ANIMATED_REDSTONE) {
         return this.ofAnimatedRedstone?var2 + "ON":var2 + "OFF";
      } else if(par1EnumOptions == GameSettings.Options.ANIMATED_EXPLOSION) {
         return this.ofAnimatedExplosion?var2 + "ON":var2 + "OFF";
      } else if(par1EnumOptions == GameSettings.Options.ANIMATED_FLAME) {
         return this.ofAnimatedFlame?var2 + "ON":var2 + "OFF";
      } else if(par1EnumOptions == GameSettings.Options.ANIMATED_SMOKE) {
         return this.ofAnimatedSmoke?var2 + "ON":var2 + "OFF";
      } else if(par1EnumOptions == GameSettings.Options.VOID_PARTICLES) {
         return this.ofVoidParticles?var2 + "ON":var2 + "OFF";
      } else if(par1EnumOptions == GameSettings.Options.WATER_PARTICLES) {
         return this.ofWaterParticles?var2 + "ON":var2 + "OFF";
      } else if(par1EnumOptions == GameSettings.Options.PORTAL_PARTICLES) {
         return this.ofPortalParticles?var2 + "ON":var2 + "OFF";
      } else if(par1EnumOptions == GameSettings.Options.POTION_PARTICLES) {
         return this.ofPotionParticles?var2 + "ON":var2 + "OFF";
      } else if(par1EnumOptions == GameSettings.Options.DRIPPING_WATER_LAVA) {
         return this.ofDrippingWaterLava?var2 + "ON":var2 + "OFF";
      } else if(par1EnumOptions == GameSettings.Options.ANIMATED_TERRAIN) {
         return this.ofAnimatedTerrain?var2 + "ON":var2 + "OFF";
      } else if(par1EnumOptions == GameSettings.Options.ANIMATED_TEXTURES) {
         return this.ofAnimatedTextures?var2 + "ON":var2 + "OFF";
      } else if(par1EnumOptions == GameSettings.Options.ANIMATED_ITEMS) {
         return this.ofAnimatedItems?var2 + "ON":var2 + "OFF";
      } else if(par1EnumOptions == GameSettings.Options.RAIN_SPLASH) {
         return this.ofRainSplash?var2 + "ON":var2 + "OFF";
      } else if(par1EnumOptions == GameSettings.Options.LAGOMETER) {
         return this.ofLagometer?var2 + "ON":var2 + "OFF";
      } else if(par1EnumOptions == GameSettings.Options.SHOW_FPS) {
         return this.ofShowFps?var2 + "ON":var2 + "OFF";
      } else if(par1EnumOptions == GameSettings.Options.AUTOSAVE_TICKS) {
         return this.ofAutoSaveTicks <= 40?var2 + "Default (2s)":(this.ofAutoSaveTicks <= 400?var2 + "20s":(this.ofAutoSaveTicks <= 4000?var2 + "3min":var2 + "30min"));
      } else if(par1EnumOptions == GameSettings.Options.BETTER_GRASS) {
         switch(this.ofBetterGrass) {
         case 1:
            return var2 + "Fast";
         case 2:
            return var2 + "Fancy";
         default:
            return var2 + "OFF";
         }
      } else if(par1EnumOptions == GameSettings.Options.CONNECTED_TEXTURES) {
         switch(this.ofConnectedTextures) {
         case 1:
            return var2 + "Fast";
         case 2:
            return var2 + "Fancy";
         default:
            return var2 + "OFF";
         }
      } else if(par1EnumOptions == GameSettings.Options.WEATHER) {
         return this.ofWeather?var2 + "ON":var2 + "OFF";
      } else if(par1EnumOptions == GameSettings.Options.SKY) {
         return this.ofSky?var2 + "ON":var2 + "OFF";
      } else if(par1EnumOptions == GameSettings.Options.STARS) {
         return this.ofStars?var2 + "ON":var2 + "OFF";
      } else if(par1EnumOptions == GameSettings.Options.SUN_MOON) {
         return this.ofSunMoon?var2 + "ON":var2 + "OFF";
      } else if(par1EnumOptions == GameSettings.Options.VIGNETTE) {
         switch(this.ofVignette) {
         case 1:
            return var2 + "Fast";
         case 2:
            return var2 + "Fancy";
         default:
            return var2 + "Default";
         }
      } else if(par1EnumOptions == GameSettings.Options.CHUNK_UPDATES) {
         return var2 + this.ofChunkUpdates;
      } else if(par1EnumOptions == GameSettings.Options.CHUNK_LOADING) {
         return this.ofChunkLoading == 1?var2 + "Smooth":(this.ofChunkLoading == 2?var2 + "Multi-Core":var2 + "Default");
      } else if(par1EnumOptions == GameSettings.Options.CHUNK_UPDATES_DYNAMIC) {
         return this.ofChunkUpdatesDynamic?var2 + "ON":var2 + "OFF";
      } else if(par1EnumOptions == GameSettings.Options.TIME) {
         return this.ofTime == 1?var2 + "Day Only":(this.ofTime == 3?var2 + "Night Only":var2 + "Default");
      } else if(par1EnumOptions == GameSettings.Options.CLEAR_WATER) {
         return this.ofClearWater?var2 + "ON":var2 + "OFF";
      } else if(par1EnumOptions == GameSettings.Options.DEPTH_FOG) {
         return this.ofDepthFog?var2 + "ON":var2 + "OFF";
      } else if(par1EnumOptions == GameSettings.Options.AA_LEVEL) {
         return this.ofAaLevel == 0?var2 + "OFF":var2 + this.ofAaLevel;
      } else if(par1EnumOptions == GameSettings.Options.PROFILER) {
         return this.ofProfiler?var2 + "ON":var2 + "OFF";
      } else if(par1EnumOptions == GameSettings.Options.BETTER_SNOW) {
         return this.ofBetterSnow?var2 + "ON":var2 + "OFF";
      } else if(par1EnumOptions == GameSettings.Options.SWAMP_COLORS) {
         return this.ofSwampColors?var2 + "ON":var2 + "OFF";
      } else if(par1EnumOptions == GameSettings.Options.RANDOM_MOBS) {
         return this.ofRandomMobs?var2 + "ON":var2 + "OFF";
      } else if(par1EnumOptions == GameSettings.Options.SMOOTH_BIOMES) {
         return this.ofSmoothBiomes?var2 + "ON":var2 + "OFF";
      } else if(par1EnumOptions == GameSettings.Options.CUSTOM_FONTS) {
         return this.ofCustomFonts?var2 + "ON":var2 + "OFF";
      } else if(par1EnumOptions == GameSettings.Options.CUSTOM_COLORS) {
         return this.ofCustomColors?var2 + "ON":var2 + "OFF";
      } else if(par1EnumOptions == GameSettings.Options.CUSTOM_SKY) {
         return this.ofCustomSky?var2 + "ON":var2 + "OFF";
      } else if(par1EnumOptions == GameSettings.Options.SHOW_CAPES) {
         return this.ofShowCapes?var2 + "ON":var2 + "OFF";
      } else if(par1EnumOptions == GameSettings.Options.NATURAL_TEXTURES) {
         return this.ofNaturalTextures?var2 + "ON":var2 + "OFF";
      } else if(par1EnumOptions == GameSettings.Options.FAST_MATH) {
         return this.ofFastMath?var2 + "ON":var2 + "OFF";
      } else if(par1EnumOptions == GameSettings.Options.FAST_RENDER) {
         return this.ofFastRender?var2 + "ON":var2 + "OFF";
      } else if(par1EnumOptions == GameSettings.Options.TRANSLUCENT_BLOCKS) {
         return this.ofTranslucentBlocks == 1?var2 + "Fast":var2 + "Fancy";
      } else if(par1EnumOptions == GameSettings.Options.LAZY_CHUNK_LOADING) {
         return this.ofLazyChunkLoading?var2 + "ON":var2 + "OFF";
      } else if(par1EnumOptions == GameSettings.Options.FULLSCREEN_MODE) {
         return var2 + this.ofFullscreenMode;
      } else if(par1EnumOptions == GameSettings.Options.HELD_ITEM_TOOLTIPS) {
         return this.heldItemTooltips?var2 + "ON":var2 + "OFF";
      } else {
         float var32;
         if(par1EnumOptions == GameSettings.Options.FRAMERATE_LIMIT) {
            var32 = this.getOptionFloatValue(par1EnumOptions);
            return var32 == 0.0F?var2 + "VSync":(var32 == par1EnumOptions.valueMax?var2 + I18n.format("options.framerateLimit.max", new Object[0]):var2 + (int)var32 + " fps");
         } else if(par1EnumOptions.getEnumFloat()) {
            var32 = this.getOptionFloatValue(par1EnumOptions);
            float var32 = par1EnumOptions.normalizeValue(var32);
            return par1EnumOptions == GameSettings.Options.SENSITIVITY?(var32 == 0.0F?var2 + I18n.format("options.sensitivity.min", new Object[0]):(var32 == 1.0F?var2 + I18n.format("options.sensitivity.max", new Object[0]):var2 + (int)(var32 * 200.0F) + "%")):(par1EnumOptions == GameSettings.Options.FOV?(var32 == 70.0F?var2 + I18n.format("options.fov.min", new Object[0]):(var32 == 110.0F?var2 + I18n.format("options.fov.max", new Object[0]):var2 + (int)var32)):(par1EnumOptions == GameSettings.Options.FRAMERATE_LIMIT?(var32 == par1EnumOptions.valueMax?var2 + I18n.format("options.framerateLimit.max", new Object[0]):var2 + (int)var32 + " fps"):(par1EnumOptions == GameSettings.Options.GAMMA?(var32 == 0.0F?var2 + I18n.format("options.gamma.min", new Object[0]):(var32 == 1.0F?var2 + I18n.format("options.gamma.max", new Object[0]):var2 + "+" + (int)(var32 * 100.0F) + "%")):(par1EnumOptions == GameSettings.Options.SATURATION?var2 + (int)(var32 * 400.0F) + "%":(par1EnumOptions == GameSettings.Options.CHAT_OPACITY?var2 + (int)(var32 * 90.0F + 10.0F) + "%":(par1EnumOptions == GameSettings.Options.CHAT_HEIGHT_UNFOCUSED?var2 + GuiNewChat.func_146243_b(var32) + "px":(par1EnumOptions == GameSettings.Options.CHAT_HEIGHT_FOCUSED?var2 + GuiNewChat.func_146243_b(var32) + "px":(par1EnumOptions == GameSettings.Options.CHAT_WIDTH?var2 + GuiNewChat.func_146233_a(var32) + "px":(par1EnumOptions == GameSettings.Options.RENDER_DISTANCE?var2 + (int)var32 + " chunks":(par1EnumOptions == GameSettings.Options.ANISOTROPIC_FILTERING?(var32 == 1.0F?var2 + I18n.format("options.off", new Object[0]):var2 + (int)var32):(par1EnumOptions == GameSettings.Options.MIPMAP_LEVELS?(var32 == 0.0F?var2 + I18n.format("options.off", new Object[0]):var2 + (int)var32):(par1EnumOptions == GameSettings.Options.STREAM_FPS?var2 + TwitchStream.func_152948_a(var32) + " fps":(par1EnumOptions == GameSettings.Options.STREAM_KBPS?var2 + TwitchStream.func_152946_b(var32) + " Kbps":(par1EnumOptions == GameSettings.Options.STREAM_BYTES_PER_PIXEL?var2 + String.format("%.3f bpp", new Object[]{Float.valueOf(TwitchStream.func_152947_c(var32))}):(var32 == 0.0F?var2 + I18n.format("options.off", new Object[0]):var2 + (int)(var32 * 100.0F) + "%")))))))))))))));
         } else if(par1EnumOptions.getEnumBoolean()) {
            boolean var31 = this.getOptionOrdinalValue(par1EnumOptions);
            return var31?var2 + I18n.format("options.on", new Object[0]):var2 + I18n.format("options.off", new Object[0]);
         } else if(par1EnumOptions == GameSettings.Options.DIFFICULTY) {
            return var2 + I18n.format(this.difficulty.getDifficultyResourceKey(), new Object[0]);
         } else if(par1EnumOptions == GameSettings.Options.GUI_SCALE) {
            return var2 + getTranslation(GUISCALES, this.guiScale);
         } else if(par1EnumOptions == GameSettings.Options.CHAT_VISIBILITY) {
            return var2 + I18n.format(this.chatVisibility.getResourceKey(), new Object[0]);
         } else if(par1EnumOptions == GameSettings.Options.PARTICLES) {
            return var2 + getTranslation(PARTICLES, this.particleSetting);
         } else if(par1EnumOptions == GameSettings.Options.AMBIENT_OCCLUSION) {
            return var2 + getTranslation(AMBIENT_OCCLUSIONS, this.ambientOcclusion);
         } else if(par1EnumOptions == GameSettings.Options.STREAM_COMPRESSION) {
            return var2 + getTranslation(field_152391_aS, this.field_152405_O);
         } else if(par1EnumOptions == GameSettings.Options.STREAM_CHAT_ENABLED) {
            return var2 + getTranslation(field_152392_aT, this.field_152408_R);
         } else if(par1EnumOptions == GameSettings.Options.STREAM_CHAT_USER_FILTER) {
            return var2 + getTranslation(field_152393_aU, this.field_152409_S);
         } else if(par1EnumOptions == GameSettings.Options.STREAM_MIC_TOGGLE_BEHAVIOR) {
            return var2 + getTranslation(field_152394_aV, this.field_152410_T);
         } else if(par1EnumOptions == GameSettings.Options.GRAPHICS) {
            if(this.fancyGraphics) {
               return var2 + I18n.format("options.graphics.fancy", new Object[0]);
            } else {
               var3 = "options.graphics.fast";
               return var2 + I18n.format("options.graphics.fast", new Object[0]);
            }
         } else {
            return var2;
         }
      }
   }

   public void loadOptions() {
      try {
         if(!this.optionsFile.exists()) {
            return;
         }

         BufferedReader var101 = new BufferedReader(new FileReader(this.optionsFile));
         String var2 = "";
         this.mapSoundLevels.clear();

         while((var2 = var101.readLine()) != null) {
            try {
               String[] var91 = var2.split(":");
               if(var91[0].equals("mouseSensitivity")) {
                  this.mouseSensitivity = this.parseFloat(var91[1]);
               }

               if(var91[0].equals("invertYMouse")) {
                  this.invertMouse = var91[1].equals("true");
               }

               if(var91[0].equals("fov")) {
                  this.fovSetting = this.parseFloat(var91[1]);
               }

               if(var91[0].equals("gamma")) {
                  this.gammaSetting = this.parseFloat(var91[1]);
               }

               if(var91[0].equals("saturation")) {
                  this.saturation = this.parseFloat(var91[1]);
               }

               if(var91[0].equals("fov")) {
                  this.fovSetting = this.parseFloat(var91[1]) * 40.0F + 70.0F;
               }

               if(var91[0].equals("renderDistance")) {
                  this.renderDistanceChunks = Integer.parseInt(var91[1]);
               }

               if(var91[0].equals("guiScale")) {
                  this.guiScale = Integer.parseInt(var91[1]);
               }

               if(var91[0].equals("particles")) {
                  this.particleSetting = Integer.parseInt(var91[1]);
               }

               if(var91[0].equals("bobView")) {
                  this.viewBobbing = var91[1].equals("true");
               }

               if(var91[0].equals("anaglyph3d")) {
                  this.anaglyph = var91[1].equals("true");
               }

               if(var91[0].equals("advancedOpengl")) {
                  this.advancedOpengl = var91[1].equals("true");
               }

               if(var91[0].equals("maxFps")) {
                  this.limitFramerate = Integer.parseInt(var91[1]);
                  this.enableVsync = false;
                  if(this.limitFramerate <= 0) {
                     this.limitFramerate = (int)GameSettings.Options.FRAMERATE_LIMIT.getValueMax();
                     this.enableVsync = true;
                  }

                  this.updateVSync();
               }

               if(var91[0].equals("fboEnable")) {
                  this.fboEnable = var91[1].equals("true");
               }

               if(var91[0].equals("difficulty")) {
                  this.difficulty = EnumDifficulty.getDifficultyEnum(Integer.parseInt(var91[1]));
               }

               if(var91[0].equals("fancyGraphics")) {
                  this.fancyGraphics = var91[1].equals("true");
               }

               if(var91[0].equals("ao")) {
                  if(var91[1].equals("true")) {
                     this.ambientOcclusion = 2;
                  } else if(var91[1].equals("false")) {
                     this.ambientOcclusion = 0;
                  } else {
                     this.ambientOcclusion = Integer.parseInt(var91[1]);
                  }
               }

               if(var91[0].equals("clouds")) {
                  this.clouds = var91[1].equals("true");
               }

               if(var91[0].equals("resourcePacks")) {
                  this.resourcePacks = (List)gson.fromJson(var2.substring(var2.indexOf(58) + 1), typeListString);
                  if(this.resourcePacks == null) {
                     this.resourcePacks = new ArrayList();
                  }
               }

               if(var91[0].equals("lastServer") && var91.length >= 2) {
                  this.lastServer = var2.substring(var2.indexOf(58) + 1);
               }

               if(var91[0].equals("lang") && var91.length >= 2) {
                  this.language = var91[1];
               }

               if(var91[0].equals("chatVisibility")) {
                  this.chatVisibility = EntityPlayer.EnumChatVisibility.getEnumChatVisibility(Integer.parseInt(var91[1]));
               }

               if(var91[0].equals("chatColors")) {
                  this.chatColours = var91[1].equals("true");
               }

               if(var91[0].equals("chatLinks")) {
                  this.chatLinks = var91[1].equals("true");
               }

               if(var91[0].equals("chatLinksPrompt")) {
                  this.chatLinksPrompt = var91[1].equals("true");
               }

               if(var91[0].equals("chatOpacity")) {
                  this.chatOpacity = this.parseFloat(var91[1]);
               }

               if(var91[0].equals("snooperEnabled")) {
                  this.snooperEnabled = var91[1].equals("true");
               }

               if(var91[0].equals("fullscreen")) {
                  this.fullScreen = var91[1].equals("true");
               }

               if(var91[0].equals("enableVsync")) {
                  this.enableVsync = var91[1].equals("true");
                  this.updateVSync();
               }

               if(var91[0].equals("hideServerAddress")) {
                  this.hideServerAddress = var91[1].equals("true");
               }

               if(var91[0].equals("advancedItemTooltips")) {
                  this.advancedItemTooltips = var91[1].equals("true");
               }

               if(var91[0].equals("pauseOnLostFocus")) {
                  this.pauseOnLostFocus = var91[1].equals("true");
               }

               if(var91[0].equals("showCape")) {
                  this.showCape = var91[1].equals("true");
               }

               if(var91[0].equals("touchscreen")) {
                  this.touchscreen = var91[1].equals("true");
               }

               if(var91[0].equals("overrideHeight")) {
                  this.overrideHeight = Integer.parseInt(var91[1]);
               }

               if(var91[0].equals("overrideWidth")) {
                  this.overrideWidth = Integer.parseInt(var91[1]);
               }

               if(var91[0].equals("heldItemTooltips")) {
                  this.heldItemTooltips = var91[1].equals("true");
               }

               if(var91[0].equals("chatHeightFocused")) {
                  this.chatHeightFocused = this.parseFloat(var91[1]);
               }

               if(var91[0].equals("chatHeightUnfocused")) {
                  this.chatHeightUnfocused = this.parseFloat(var91[1]);
               }

               if(var91[0].equals("chatScale")) {
                  this.chatScale = this.parseFloat(var91[1]);
               }

               if(var91[0].equals("chatWidth")) {
                  this.chatWidth = this.parseFloat(var91[1]);
               }

               if(var91[0].equals("showInventoryAchievementHint")) {
                  this.showInventoryAchievementHint = var91[1].equals("true");
               }

               if(var91[0].equals("mipmapLevels")) {
                  this.mipmapLevels = Integer.parseInt(var91[1]);
               }

               if(var91[0].equals("anisotropicFiltering")) {
                  this.anisotropicFiltering = Integer.parseInt(var91[1]);
               }

               if(var91[0].equals("streamBytesPerPixel")) {
                  this.field_152400_J = this.parseFloat(var91[1]);
               }

               if(var91[0].equals("streamMicVolume")) {
                  this.field_152401_K = this.parseFloat(var91[1]);
               }

               if(var91[0].equals("streamSystemVolume")) {
                  this.field_152402_L = this.parseFloat(var91[1]);
               }

               if(var91[0].equals("streamKbps")) {
                  this.field_152403_M = this.parseFloat(var91[1]);
               }

               if(var91[0].equals("streamFps")) {
                  this.field_152404_N = this.parseFloat(var91[1]);
               }

               if(var91[0].equals("streamCompression")) {
                  this.field_152405_O = Integer.parseInt(var91[1]);
               }

               if(var91[0].equals("streamSendMetadata")) {
                  this.field_152406_P = var91[1].equals("true");
               }

               if(var91[0].equals("streamPreferredServer") && var91.length >= 2) {
                  this.field_152407_Q = var2.substring(var2.indexOf(58) + 1);
               }

               if(var91[0].equals("streamChatEnabled")) {
                  this.field_152408_R = Integer.parseInt(var91[1]);
               }

               if(var91[0].equals("streamChatUserFilter")) {
                  this.field_152409_S = Integer.parseInt(var91[1]);
               }

               if(var91[0].equals("streamMicToggleBehavior")) {
                  this.field_152410_T = Integer.parseInt(var91[1]);
               }

               if(var91[0].equals("forceUnicodeFont")) {
                  this.forceUnicodeFont = var91[1].equals("true");
               }

               if(var91[0].equals("showArmor")) {
                  showArmor = var91[1].equals("true");
               }

               if(var91[0].equals("showFps")) {
                  showfps = var91[1].equals("true");
               }

               if(var91[0].equals("showFacing")) {
                  showfacing = var91[1].equals("true");
               }

               if(var91[0].equals("showCoords")) {
                  showcoords = var91[1].equals("true");
               }

               if(var91[0].equals("armorStyle")) {
                  armorstyle = var91[1].equals("true");
               }

               if(var91[0].equals("showEffect")) {
                  showEffect = var91[1].equals("true");
               }

               if(var91[0].equals("armorPos")) {
                  armorPos = Integer.parseInt(var91[1]);
               }

               if(var91[0].equals("effectPos")) {
                  effectPos = Integer.parseInt(var91[1]);
               }

               if(var91[0].equals("facingPos")) {
                  facingPos = Integer.parseInt(var91[1]);
               }

               if(var91[0].equals("showDeathMessage")) {
                  showDeathMessage = var91[1].equals("true");
               } else if(var91[0].equals("macro1")) {
                  CustomSettings.macro1 = var91.length > 1?var91[1]:var91.length + "";
               } else if(var91[0].equals("macro2")) {
                  CustomSettings.macro2 = var91.length > 1?var91[1]:var91.length + "";
               } else if(var91[0].equals("macro3")) {
                  CustomSettings.macro3 = var91.length > 1?var91[1]:var91.length + "";
               } else if(var91[0].equals("macro4")) {
                  CustomSettings.macro4 = var91.length > 1?var91[1]:var91.length + "";
               }

               KeyBinding[] var4 = this.keyBindings;
               int var5 = var4.length;

               int var6;
               for(var6 = 0; var6 < var5; ++var6) {
                  KeyBinding var111 = var4[var6];
                  if(var91[0].equals("key_" + var111.getKeyDescription())) {
                     var111.setKeyCode(Integer.parseInt(var91[1]));
                  }
               }

               SoundCategory[] var111 = SoundCategory.values();
               var5 = var111.length;

               for(var6 = 0; var6 < var5; ++var6) {
                  SoundCategory var11 = var111[var6];
                  if(var91[0].equals("soundCategory_" + var11.getCategoryName())) {
                     this.mapSoundLevels.put(var11, Float.valueOf(this.parseFloat(var91[1])));
                  }
               }
            } catch (Exception var9) {
               logger.warn("Skipping bad option: " + var2);
               var9.printStackTrace();
            }
         }

         KeyBinding.resetKeyBindingArrayAndHash();
         var101.close();
      } catch (Exception var10) {
         logger.error("Failed to load options", var10);
      }

      this.loadOfOptions();
   }

   private float parseFloat(String par1Str) {
      return par1Str.equals("true")?1.0F:(par1Str.equals("false")?0.0F:Float.parseFloat(par1Str));
   }

   public void saveOptions() {
      if(Reflector.FMLClientHandler.exists()) {
         Object var71 = Reflector.call(Reflector.FMLClientHandler_instance, new Object[0]);
         if(var71 != null && Reflector.callBoolean(var71, Reflector.FMLClientHandler_isLoading, new Object[0])) {
            return;
         }
      }

      try {
         PrintWriter var81 = new PrintWriter(new FileWriter(this.optionsFile));
         var81.println("invertYMouse:" + this.invertMouse);
         var81.println("mouseSensitivity:" + this.mouseSensitivity);
         var81.println("fov:" + (this.fovSetting - 70.0F) / 40.0F);
         var81.println("gamma:" + this.gammaSetting);
         var81.println("saturation:" + this.saturation);
         var81.println("renderDistance:" + Config.limit(this.renderDistanceChunks, 2, 16));
         var81.println("guiScale:" + this.guiScale);
         var81.println("particles:" + this.particleSetting);
         var81.println("bobView:" + this.viewBobbing);
         var81.println("anaglyph3d:" + this.anaglyph);
         var81.println("advancedOpengl:" + this.advancedOpengl);
         var81.println("maxFps:" + this.limitFramerate);
         var81.println("fboEnable:" + this.fboEnable);
         var81.println("difficulty:" + this.difficulty.getDifficultyId());
         var81.println("fancyGraphics:" + this.fancyGraphics);
         var81.println("ao:" + this.ambientOcclusion);
         var81.println("clouds:" + this.clouds);
         var81.println("resourcePacks:" + gson.toJson(this.resourcePacks));
         var81.println("lastServer:" + this.lastServer);
         var81.println("lang:" + this.language);
         var81.println("chatVisibility:" + this.chatVisibility.getChatVisibility());
         var81.println("chatColors:" + this.chatColours);
         var81.println("chatLinks:" + this.chatLinks);
         var81.println("chatLinksPrompt:" + this.chatLinksPrompt);
         var81.println("chatOpacity:" + this.chatOpacity);
         var81.println("snooperEnabled:" + this.snooperEnabled);
         var81.println("fullscreen:" + this.fullScreen);
         var81.println("enableVsync:" + this.enableVsync);
         var81.println("hideServerAddress:" + this.hideServerAddress);
         var81.println("advancedItemTooltips:" + this.advancedItemTooltips);
         var81.println("pauseOnLostFocus:" + this.pauseOnLostFocus);
         var81.println("showCape:" + this.showCape);
         var81.println("touchscreen:" + this.touchscreen);
         var81.println("overrideWidth:" + this.overrideWidth);
         var81.println("overrideHeight:" + this.overrideHeight);
         var81.println("heldItemTooltips:" + this.heldItemTooltips);
         var81.println("chatHeightFocused:" + this.chatHeightFocused);
         var81.println("chatHeightUnfocused:" + this.chatHeightUnfocused);
         var81.println("chatScale:" + this.chatScale);
         var81.println("chatWidth:" + this.chatWidth);
         var81.println("showInventoryAchievementHint:" + this.showInventoryAchievementHint);
         var81.println("mipmapLevels:" + this.mipmapLevels);
         var81.println("anisotropicFiltering:" + this.anisotropicFiltering);
         var81.println("streamBytesPerPixel:" + this.field_152400_J);
         var81.println("streamMicVolume:" + this.field_152401_K);
         var81.println("streamSystemVolume:" + this.field_152402_L);
         var81.println("streamKbps:" + this.field_152403_M);
         var81.println("streamFps:" + this.field_152404_N);
         var81.println("streamCompression:" + this.field_152405_O);
         var81.println("streamSendMetadata:" + this.field_152406_P);
         var81.println("streamPreferredServer:" + this.field_152407_Q);
         var81.println("streamChatEnabled:" + this.field_152408_R);
         var81.println("streamChatUserFilter:" + this.field_152409_S);
         var81.println("streamMicToggleBehavior:" + this.field_152410_T);
         var81.println("forceUnicodeFont:" + this.forceUnicodeFont);
         var81.println("showArmor:" + showArmor);
         var81.println("showFps:" + showfps);
         var81.println("showFacing:" + showfacing);
         var81.println("showCoords:" + showcoords);
         var81.println("showEffect:" + showEffect);
         var81.println("armorStyle:" + armorstyle);
         var81.println("armorPos:" + armorPos);
         var81.println("effectPos:" + effectPos);
         var81.println("macro1:" + CustomSettings.macro1);
         var81.println("macro2:" + CustomSettings.macro2);
         var81.println("macro3:" + CustomSettings.macro3);
         var81.println("macro4:" + CustomSettings.macro4);
         var81.println("showDeathMessage:" + showDeathMessage);
         KeyBinding[] var2 = this.keyBindings;
         int var3 = var2.length;

         int var4;
         for(var4 = 0; var4 < var3; ++var4) {
            KeyBinding var9 = var2[var4];
            var81.println("key_" + var9.getKeyDescription() + ":" + var9.getKeyCode());
         }

         SoundCategory[] var91 = SoundCategory.values();
         var3 = var91.length;

         for(var4 = 0; var4 < var3; ++var4) {
            SoundCategory var8 = var91[var4];
            var81.println("soundCategory_" + var8.getCategoryName() + ":" + this.getSoundLevel(var8));
         }

         var81.close();
      } catch (Exception var7) {
         logger.error("Failed to save options", var7);
      }

      this.saveOfOptions();
      this.sendSettingsToServer();
   }

   public float getSoundLevel(SoundCategory p_151438_1_) {
      return this.mapSoundLevels.containsKey(p_151438_1_)?((Float)this.mapSoundLevels.get(p_151438_1_)).floatValue():1.0F;
   }

   public void setSoundLevel(SoundCategory p_151439_1_, float p_151439_2_) {
      this.mc.getSoundHandler().setSoundLevel(p_151439_1_, p_151439_2_);
      this.mapSoundLevels.put(p_151439_1_, Float.valueOf(p_151439_2_));
   }

   public void sendSettingsToServer() {
      if(this.mc.thePlayer != null) {
         this.mc.thePlayer.sendQueue.addToSendQueue(new C15PacketClientSettings(this.language, this.renderDistanceChunks, this.chatVisibility, this.chatColours, this.difficulty, this.showCape));
      }
   }

   public boolean shouldRenderClouds() {
      return this.renderDistanceChunks >= 4 && this.clouds;
   }

   public void loadOfOptions() {
      try {
         File var6 = this.optionsFileOF;
         if(!var6.exists()) {
            var6 = this.optionsFile;
         }

         if(!var6.exists()) {
            return;
         }

         BufferedReader bufferedreader = new BufferedReader(new FileReader(var6));
         String s = "";

         while((s = bufferedreader.readLine()) != null) {
            try {
               String[] var5 = s.split(":");
               if(var5[0].equals("ofRenderDistanceChunks") && var5.length >= 2) {
                  this.renderDistanceChunks = Integer.valueOf(var5[1]).intValue();
                  this.renderDistanceChunks = Config.limit(this.renderDistanceChunks, 2, 32);
               }

               if(var5[0].equals("ofFogType") && var5.length >= 2) {
                  this.ofFogType = Integer.valueOf(var5[1]).intValue();
                  this.ofFogType = Config.limit(this.ofFogType, 1, 3);
               }

               if(var5[0].equals("ofFogStart") && var5.length >= 2) {
                  this.ofFogStart = Float.valueOf(var5[1]).floatValue();
                  if(this.ofFogStart < 0.2F) {
                     this.ofFogStart = 0.2F;
                  }

                  if(this.ofFogStart > 0.81F) {
                     this.ofFogStart = 0.8F;
                  }
               }

               if(var5[0].equals("ofMipmapType") && var5.length >= 2) {
                  this.ofMipmapType = Integer.valueOf(var5[1]).intValue();
                  this.ofMipmapType = Config.limit(this.ofMipmapType, 0, 3);
               }

               if(var5[0].equals("ofLoadFar") && var5.length >= 2) {
                  this.ofLoadFar = Boolean.valueOf(var5[1]).booleanValue();
               }

               if(var5[0].equals("ofPreloadedChunks") && var5.length >= 2) {
                  this.ofPreloadedChunks = Integer.valueOf(var5[1]).intValue();
                  if(this.ofPreloadedChunks < 0) {
                     this.ofPreloadedChunks = 0;
                  }

                  if(this.ofPreloadedChunks > 8) {
                     this.ofPreloadedChunks = 8;
                  }
               }

               if(var5[0].equals("ofOcclusionFancy") && var5.length >= 2) {
                  this.ofOcclusionFancy = Boolean.valueOf(var5[1]).booleanValue();
               }

               if(var5[0].equals("ofSmoothFps") && var5.length >= 2) {
                  this.ofSmoothFps = Boolean.valueOf(var5[1]).booleanValue();
               }

               if(var5[0].equals("ofSmoothWorld") && var5.length >= 2) {
                  this.ofSmoothWorld = Boolean.valueOf(var5[1]).booleanValue();
               }

               if(var5[0].equals("ofAoLevel") && var5.length >= 2) {
                  this.ofAoLevel = Float.valueOf(var5[1]).floatValue();
                  this.ofAoLevel = Config.limit(this.ofAoLevel, 0.0F, 1.0F);
               }

               if(var5[0].equals("ofClouds") && var5.length >= 2) {
                  this.ofClouds = Integer.valueOf(var5[1]).intValue();
                  this.ofClouds = Config.limit(this.ofClouds, 0, 3);
               }

               if(var5[0].equals("ofCloudsHeight") && var5.length >= 2) {
                  this.ofCloudsHeight = Float.valueOf(var5[1]).floatValue();
                  this.ofCloudsHeight = Config.limit(this.ofCloudsHeight, 0.0F, 1.0F);
               }

               if(var5[0].equals("ofTrees") && var5.length >= 2) {
                  this.ofTrees = Integer.valueOf(var5[1]).intValue();
                  this.ofTrees = Config.limit(this.ofTrees, 0, 2);
               }

               if(var5[0].equals("ofGrass") && var5.length >= 2) {
                  this.ofGrass = Integer.valueOf(var5[1]).intValue();
                  this.ofGrass = Config.limit(this.ofGrass, 0, 2);
               }

               if(var5[0].equals("ofDroppedItems") && var5.length >= 2) {
                  this.ofDroppedItems = Integer.valueOf(var5[1]).intValue();
                  this.ofDroppedItems = Config.limit(this.ofDroppedItems, 0, 2);
               }

               if(var5[0].equals("ofRain") && var5.length >= 2) {
                  this.ofRain = Integer.valueOf(var5[1]).intValue();
                  this.ofRain = Config.limit(this.ofRain, 0, 3);
               }

               if(var5[0].equals("ofWater") && var5.length >= 2) {
                  this.ofWater = Integer.valueOf(var5[1]).intValue();
                  this.ofWater = Config.limit(this.ofWater, 0, 3);
               }

               if(var5[0].equals("ofAnimatedWater") && var5.length >= 2) {
                  this.ofAnimatedWater = Integer.valueOf(var5[1]).intValue();
                  this.ofAnimatedWater = Config.limit(this.ofAnimatedWater, 0, 2);
               }

               if(var5[0].equals("ofAnimatedLava") && var5.length >= 2) {
                  this.ofAnimatedLava = Integer.valueOf(var5[1]).intValue();
                  this.ofAnimatedLava = Config.limit(this.ofAnimatedLava, 0, 2);
               }

               if(var5[0].equals("ofAnimatedFire") && var5.length >= 2) {
                  this.ofAnimatedFire = Boolean.valueOf(var5[1]).booleanValue();
               }

               if(var5[0].equals("ofAnimatedPortal") && var5.length >= 2) {
                  this.ofAnimatedPortal = Boolean.valueOf(var5[1]).booleanValue();
               }

               if(var5[0].equals("ofAnimatedRedstone") && var5.length >= 2) {
                  this.ofAnimatedRedstone = Boolean.valueOf(var5[1]).booleanValue();
               }

               if(var5[0].equals("ofAnimatedExplosion") && var5.length >= 2) {
                  this.ofAnimatedExplosion = Boolean.valueOf(var5[1]).booleanValue();
               }

               if(var5[0].equals("ofAnimatedFlame") && var5.length >= 2) {
                  this.ofAnimatedFlame = Boolean.valueOf(var5[1]).booleanValue();
               }

               if(var5[0].equals("ofAnimatedSmoke") && var5.length >= 2) {
                  this.ofAnimatedSmoke = Boolean.valueOf(var5[1]).booleanValue();
               }

               if(var5[0].equals("ofVoidParticles") && var5.length >= 2) {
                  this.ofVoidParticles = Boolean.valueOf(var5[1]).booleanValue();
               }

               if(var5[0].equals("ofWaterParticles") && var5.length >= 2) {
                  this.ofWaterParticles = Boolean.valueOf(var5[1]).booleanValue();
               }

               if(var5[0].equals("ofPortalParticles") && var5.length >= 2) {
                  this.ofPortalParticles = Boolean.valueOf(var5[1]).booleanValue();
               }

               if(var5[0].equals("ofPotionParticles") && var5.length >= 2) {
                  this.ofPotionParticles = Boolean.valueOf(var5[1]).booleanValue();
               }

               if(var5[0].equals("ofDrippingWaterLava") && var5.length >= 2) {
                  this.ofDrippingWaterLava = Boolean.valueOf(var5[1]).booleanValue();
               }

               if(var5[0].equals("ofAnimatedTerrain") && var5.length >= 2) {
                  this.ofAnimatedTerrain = Boolean.valueOf(var5[1]).booleanValue();
               }

               if(var5[0].equals("ofAnimatedTextures") && var5.length >= 2) {
                  this.ofAnimatedTextures = Boolean.valueOf(var5[1]).booleanValue();
               }

               if(var5[0].equals("ofAnimatedItems") && var5.length >= 2) {
                  this.ofAnimatedItems = Boolean.valueOf(var5[1]).booleanValue();
               }

               if(var5[0].equals("ofRainSplash") && var5.length >= 2) {
                  this.ofRainSplash = Boolean.valueOf(var5[1]).booleanValue();
               }

               if(var5[0].equals("ofLagometer") && var5.length >= 2) {
                  this.ofLagometer = Boolean.valueOf(var5[1]).booleanValue();
               }

               if(var5[0].equals("ofShowFps") && var5.length >= 2) {
                  this.ofShowFps = Boolean.valueOf(var5[1]).booleanValue();
               }

               if(var5[0].equals("ofAutoSaveTicks") && var5.length >= 2) {
                  this.ofAutoSaveTicks = Integer.valueOf(var5[1]).intValue();
                  this.ofAutoSaveTicks = Config.limit(this.ofAutoSaveTicks, 40, 40000);
               }

               if(var5[0].equals("ofBetterGrass") && var5.length >= 2) {
                  this.ofBetterGrass = Integer.valueOf(var5[1]).intValue();
                  this.ofBetterGrass = Config.limit(this.ofBetterGrass, 1, 3);
               }

               if(var5[0].equals("ofConnectedTextures") && var5.length >= 2) {
                  this.ofConnectedTextures = Integer.valueOf(var5[1]).intValue();
                  this.ofConnectedTextures = Config.limit(this.ofConnectedTextures, 1, 3);
               }

               if(var5[0].equals("ofWeather") && var5.length >= 2) {
                  this.ofWeather = Boolean.valueOf(var5[1]).booleanValue();
               }

               if(var5[0].equals("ofSky") && var5.length >= 2) {
                  this.ofSky = Boolean.valueOf(var5[1]).booleanValue();
               }

               if(var5[0].equals("ofStars") && var5.length >= 2) {
                  this.ofStars = Boolean.valueOf(var5[1]).booleanValue();
               }

               if(var5[0].equals("ofSunMoon") && var5.length >= 2) {
                  this.ofSunMoon = Boolean.valueOf(var5[1]).booleanValue();
               }

               if(var5[0].equals("ofVignette") && var5.length >= 2) {
                  this.ofVignette = Integer.valueOf(var5[1]).intValue();
                  this.ofVignette = Config.limit(this.ofVignette, 0, 2);
               }

               if(var5[0].equals("ofChunkUpdates") && var5.length >= 2) {
                  this.ofChunkUpdates = Integer.valueOf(var5[1]).intValue();
                  this.ofChunkUpdates = Config.limit(this.ofChunkUpdates, 1, 5);
               }

               if(var5[0].equals("ofChunkLoading") && var5.length >= 2) {
                  this.ofChunkLoading = Integer.valueOf(var5[1]).intValue();
                  this.ofChunkLoading = Config.limit(this.ofChunkLoading, 0, 2);
                  this.updateChunkLoading();
               }

               if(var5[0].equals("ofChunkUpdatesDynamic") && var5.length >= 2) {
                  this.ofChunkUpdatesDynamic = Boolean.valueOf(var5[1]).booleanValue();
               }

               if(var5[0].equals("ofTime") && var5.length >= 2) {
                  this.ofTime = Integer.valueOf(var5[1]).intValue();
                  this.ofTime = Config.limit(this.ofTime, 0, 3);
               }

               if(var5[0].equals("ofClearWater") && var5.length >= 2) {
                  this.ofClearWater = Boolean.valueOf(var5[1]).booleanValue();
                  this.updateWaterOpacity();
               }

               if(var5[0].equals("ofDepthFog") && var5.length >= 2) {
                  this.ofDepthFog = Boolean.valueOf(var5[1]).booleanValue();
               }

               if(var5[0].equals("ofAaLevel") && var5.length >= 2) {
                  this.ofAaLevel = Integer.valueOf(var5[1]).intValue();
                  this.ofAaLevel = Config.limit(this.ofAaLevel, 0, 16);
               }

               if(var5[0].equals("ofProfiler") && var5.length >= 2) {
                  this.ofProfiler = Boolean.valueOf(var5[1]).booleanValue();
               }

               if(var5[0].equals("ofBetterSnow") && var5.length >= 2) {
                  this.ofBetterSnow = Boolean.valueOf(var5[1]).booleanValue();
               }

               if(var5[0].equals("ofSwampColors") && var5.length >= 2) {
                  this.ofSwampColors = Boolean.valueOf(var5[1]).booleanValue();
               }

               if(var5[0].equals("ofRandomMobs") && var5.length >= 2) {
                  this.ofRandomMobs = Boolean.valueOf(var5[1]).booleanValue();
               }

               if(var5[0].equals("ofSmoothBiomes") && var5.length >= 2) {
                  this.ofSmoothBiomes = Boolean.valueOf(var5[1]).booleanValue();
               }

               if(var5[0].equals("ofCustomFonts") && var5.length >= 2) {
                  this.ofCustomFonts = Boolean.valueOf(var5[1]).booleanValue();
               }

               if(var5[0].equals("ofCustomColors") && var5.length >= 2) {
                  this.ofCustomColors = Boolean.valueOf(var5[1]).booleanValue();
               }

               if(var5[0].equals("ofCustomSky") && var5.length >= 2) {
                  this.ofCustomSky = Boolean.valueOf(var5[1]).booleanValue();
               }

               if(var5[0].equals("ofShowCapes") && var5.length >= 2) {
                  this.ofShowCapes = Boolean.valueOf(var5[1]).booleanValue();
               }

               if(var5[0].equals("ofNaturalTextures") && var5.length >= 2) {
                  this.ofNaturalTextures = Boolean.valueOf(var5[1]).booleanValue();
               }

               if(var5[0].equals("ofLazyChunkLoading") && var5.length >= 2) {
                  this.ofLazyChunkLoading = Boolean.valueOf(var5[1]).booleanValue();
               }

               if(var5[0].equals("ofFullscreenMode") && var5.length >= 2) {
                  this.ofFullscreenMode = var5[1];
               }

               if(var5[0].equals("ofFastMath") && var5.length >= 2) {
                  this.ofFastMath = Boolean.valueOf(var5[1]).booleanValue();
                  MathHelper.fastMath = this.ofFastMath;
               }

               if(var5[0].equals("ofFastRender") && var5.length >= 2) {
                  this.ofFastRender = Boolean.valueOf(var5[1]).booleanValue();
               }

               if(var5[0].equals("ofTranslucentBlocks") && var5.length >= 2) {
                  this.ofTranslucentBlocks = Integer.valueOf(var5[1]).intValue();
                  this.ofTranslucentBlocks = Config.limit(this.ofTranslucentBlocks, 1, 2);
               }
            } catch (Exception var51) {
               Config.dbg("Skipping bad option: " + s);
               var51.printStackTrace();
            }
         }

         KeyBinding.resetKeyBindingArrayAndHash();
         bufferedreader.close();
      } catch (Exception var61) {
         Config.warn("Failed to load options");
         var61.printStackTrace();
      }
   }

   public void saveOfOptions() {
      try {
         PrintWriter var2 = new PrintWriter(new FileWriter(this.optionsFileOF));
         var2.println("ofRenderDistanceChunks:" + this.renderDistanceChunks);
         var2.println("ofFogType:" + this.ofFogType);
         var2.println("ofFogStart:" + this.ofFogStart);
         var2.println("ofMipmapType:" + this.ofMipmapType);
         var2.println("ofLoadFar:" + this.ofLoadFar);
         var2.println("ofPreloadedChunks:" + this.ofPreloadedChunks);
         var2.println("ofOcclusionFancy:" + this.ofOcclusionFancy);
         var2.println("ofSmoothFps:" + this.ofSmoothFps);
         var2.println("ofSmoothWorld:" + this.ofSmoothWorld);
         var2.println("ofAoLevel:" + this.ofAoLevel);
         var2.println("ofClouds:" + this.ofClouds);
         var2.println("ofCloudsHeight:" + this.ofCloudsHeight);
         var2.println("ofTrees:" + this.ofTrees);
         var2.println("ofGrass:" + this.ofGrass);
         var2.println("ofDroppedItems:" + this.ofDroppedItems);
         var2.println("ofRain:" + this.ofRain);
         var2.println("ofWater:" + this.ofWater);
         var2.println("ofAnimatedWater:" + this.ofAnimatedWater);
         var2.println("ofAnimatedLava:" + this.ofAnimatedLava);
         var2.println("ofAnimatedFire:" + this.ofAnimatedFire);
         var2.println("ofAnimatedPortal:" + this.ofAnimatedPortal);
         var2.println("ofAnimatedRedstone:" + this.ofAnimatedRedstone);
         var2.println("ofAnimatedExplosion:" + this.ofAnimatedExplosion);
         var2.println("ofAnimatedFlame:" + this.ofAnimatedFlame);
         var2.println("ofAnimatedSmoke:" + this.ofAnimatedSmoke);
         var2.println("ofVoidParticles:" + this.ofVoidParticles);
         var2.println("ofWaterParticles:" + this.ofWaterParticles);
         var2.println("ofPortalParticles:" + this.ofPortalParticles);
         var2.println("ofPotionParticles:" + this.ofPotionParticles);
         var2.println("ofDrippingWaterLava:" + this.ofDrippingWaterLava);
         var2.println("ofAnimatedTerrain:" + this.ofAnimatedTerrain);
         var2.println("ofAnimatedTextures:" + this.ofAnimatedTextures);
         var2.println("ofAnimatedItems:" + this.ofAnimatedItems);
         var2.println("ofRainSplash:" + this.ofRainSplash);
         var2.println("ofLagometer:" + this.ofLagometer);
         var2.println("ofShowFps:" + this.ofShowFps);
         var2.println("ofAutoSaveTicks:" + this.ofAutoSaveTicks);
         var2.println("ofBetterGrass:" + this.ofBetterGrass);
         var2.println("ofConnectedTextures:" + this.ofConnectedTextures);
         var2.println("ofWeather:" + this.ofWeather);
         var2.println("ofSky:" + this.ofSky);
         var2.println("ofStars:" + this.ofStars);
         var2.println("ofSunMoon:" + this.ofSunMoon);
         var2.println("ofVignette:" + this.ofVignette);
         var2.println("ofChunkUpdates:" + this.ofChunkUpdates);
         var2.println("ofChunkLoading:" + this.ofChunkLoading);
         var2.println("ofChunkUpdatesDynamic:" + this.ofChunkUpdatesDynamic);
         var2.println("ofTime:" + this.ofTime);
         var2.println("ofClearWater:" + this.ofClearWater);
         var2.println("ofDepthFog:" + this.ofDepthFog);
         var2.println("ofAaLevel:" + this.ofAaLevel);
         var2.println("ofProfiler:" + this.ofProfiler);
         var2.println("ofBetterSnow:" + this.ofBetterSnow);
         var2.println("ofSwampColors:" + this.ofSwampColors);
         var2.println("ofRandomMobs:" + this.ofRandomMobs);
         var2.println("ofSmoothBiomes:" + this.ofSmoothBiomes);
         var2.println("ofCustomFonts:" + this.ofCustomFonts);
         var2.println("ofCustomColors:" + this.ofCustomColors);
         var2.println("ofCustomSky:" + this.ofCustomSky);
         var2.println("ofShowCapes:" + this.ofShowCapes);
         var2.println("ofNaturalTextures:" + this.ofNaturalTextures);
         var2.println("ofLazyChunkLoading:" + this.ofLazyChunkLoading);
         var2.println("ofFullscreenMode:" + this.ofFullscreenMode);
         var2.println("ofFastMath:" + this.ofFastMath);
         var2.println("ofFastRender:" + this.ofFastRender);
         var2.println("ofTranslucentBlocks:" + this.ofTranslucentBlocks);
         var2.close();
      } catch (Exception var21) {
         Config.warn("Failed to save options");
         var21.printStackTrace();
      }
   }

   public void resetSettings() {
      this.renderDistanceChunks = 8;
      this.viewBobbing = true;
      this.anaglyph = false;
      this.advancedOpengl = false;
      this.limitFramerate = (int)GameSettings.Options.FRAMERATE_LIMIT.getValueMax();
      this.enableVsync = false;
      this.updateVSync();
      this.mipmapLevels = 4;
      this.anisotropicFiltering = 1;
      this.fancyGraphics = true;
      this.ambientOcclusion = 2;
      this.clouds = true;
      this.fovSetting = 70.0F;
      this.gammaSetting = 0.0F;
      this.guiScale = 0;
      this.particleSetting = 0;
      this.heldItemTooltips = true;
      this.ofFogType = 1;
      this.ofFogStart = 0.8F;
      this.ofMipmapType = 0;
      this.ofLoadFar = false;
      this.ofPreloadedChunks = 0;
      this.ofOcclusionFancy = false;
      this.ofSmoothFps = false;
      Config.updateAvailableProcessors();
      this.ofSmoothWorld = Config.isSingleProcessor();
      this.ofLazyChunkLoading = Config.isSingleProcessor();
      this.ofFastMath = false;
      this.ofFastRender = false;
      this.ofTranslucentBlocks = 2;
      this.ofAoLevel = 1.0F;
      this.ofAaLevel = 0;
      this.ofClouds = 0;
      this.ofCloudsHeight = 0.0F;
      this.ofTrees = 0;
      this.ofGrass = 0;
      this.ofRain = 0;
      this.ofWater = 0;
      this.ofBetterGrass = 3;
      this.ofAutoSaveTicks = 4000;
      this.ofLagometer = false;
      this.ofShowFps = false;
      this.ofProfiler = false;
      this.ofWeather = true;
      this.ofSky = true;
      this.ofStars = true;
      this.ofSunMoon = true;
      this.ofVignette = 0;
      this.ofChunkUpdates = 1;
      this.ofChunkLoading = 0;
      this.ofChunkUpdatesDynamic = false;
      this.ofTime = 0;
      this.ofClearWater = false;
      this.ofDepthFog = true;
      this.ofBetterSnow = false;
      this.ofFullscreenMode = "Default";
      this.ofSwampColors = true;
      this.ofRandomMobs = true;
      this.ofSmoothBiomes = true;
      this.ofCustomFonts = true;
      this.ofCustomColors = true;
      this.ofCustomSky = true;
      this.ofShowCapes = true;
      this.ofConnectedTextures = 2;
      this.ofNaturalTextures = false;
      this.ofAnimatedWater = 0;
      this.ofAnimatedLava = 0;
      this.ofAnimatedFire = true;
      this.ofAnimatedPortal = true;
      this.ofAnimatedRedstone = true;
      this.ofAnimatedExplosion = true;
      this.ofAnimatedFlame = true;
      this.ofAnimatedSmoke = true;
      this.ofVoidParticles = true;
      this.ofWaterParticles = true;
      this.ofRainSplash = true;
      this.ofPortalParticles = true;
      this.ofPotionParticles = true;
      this.ofDrippingWaterLava = true;
      this.ofAnimatedTerrain = true;
      this.ofAnimatedItems = true;
      this.ofAnimatedTextures = true;
      showArmor = false;
      showfps = false;
      showfacing = false;
      armorstyle = false;
      showcoords = false;
      this.mc.renderGlobal.updateCapes();
      this.updateWaterOpacity();
      this.mc.renderGlobal.setAllRenderersVisible();
      this.mc.refreshResources();
      this.saveOptions();
   }

   public void updateVSync() {
      Display.setVSyncEnabled(this.enableVsync);
   }

   private void updateWaterOpacity() {
      if(this.mc.isIntegratedServerRunning() && this.mc.getIntegratedServer() != null) {
         Config.waterOpacityChanged = true;
      }

      ClearWater.updateWaterOpacity(this, this.mc.theWorld);
   }

   public void updateChunkLoading() {
      switch(this.ofChunkLoading) {
      case 1:
         WrUpdates.setWrUpdater(new WrUpdaterSmooth());
         break;
      case 2:
         WrUpdates.setWrUpdater(new WrUpdaterThreaded());
         break;
      default:
         WrUpdates.setWrUpdater((IWrUpdater)null);
      }

      if(this.mc.renderGlobal != null) {
         this.mc.renderGlobal.loadRenderers();
      }
   }

   public void setAllAnimations(boolean flag) {
      int animVal = flag?0:2;
      this.ofAnimatedWater = animVal;
      this.ofAnimatedLava = animVal;
      this.ofAnimatedFire = flag;
      this.ofAnimatedPortal = flag;
      this.ofAnimatedRedstone = flag;
      this.ofAnimatedExplosion = flag;
      this.ofAnimatedFlame = flag;
      this.ofAnimatedSmoke = flag;
      this.ofVoidParticles = flag;
      this.ofWaterParticles = flag;
      this.ofRainSplash = flag;
      this.ofPortalParticles = flag;
      this.ofPotionParticles = flag;
      this.particleSetting = flag?0:2;
      this.ofDrippingWaterLava = flag;
      this.ofAnimatedTerrain = flag;
      this.ofAnimatedItems = flag;
      this.ofAnimatedTextures = flag;
   }

   public static enum Options {
      INVERT_MOUSE("INVERT_MOUSE", 0, "INVERT_MOUSE", 0, "INVERT_MOUSE", 0, "options.invertMouse", false, true),
      SENSITIVITY("SENSITIVITY", 1, "SENSITIVITY", 1, "SENSITIVITY", 1, "options.sensitivity", true, false),
      FOV("FOV", 2, "FOV", 2, "FOV", 2, "options.fov", true, false, 30.0F, 110.0F, 1.0F),
      GAMMA("GAMMA", 3, "GAMMA", 3, "GAMMA", 3, "options.gamma", true, false),
      SATURATION("SATURATION", 4, "SATURATION", 4, "SATURATION", 4, "options.saturation", true, false),
      RENDER_DISTANCE("RENDER_DISTANCE", 5, "RENDER_DISTANCE", 5, "RENDER_DISTANCE", 5, "options.renderDistance", true, false, 2.0F, 16.0F, 1.0F),
      VIEW_BOBBING("VIEW_BOBBING", 6, "VIEW_BOBBING", 6, "VIEW_BOBBING", 6, "options.viewBobbing", false, true),
      ANAGLYPH("ANAGLYPH", 7, "ANAGLYPH", 7, "ANAGLYPH", 7, "options.anaglyph", false, true),
      ADVANCED_OPENGL("ADVANCED_OPENGL", 8, "ADVANCED_OPENGL", 8, "ADVANCED_OPENGL", 8, "options.advancedOpengl", false, true),
      FRAMERATE_LIMIT("FRAMERATE_LIMIT", 9, "FRAMERATE_LIMIT", 9, "FRAMERATE_LIMIT", 9, "options.framerateLimit", true, false, 0.0F, 260.0F, 5.0F),
      FBO_ENABLE("FBO_ENABLE", 10, "FBO_ENABLE", 10, "FBO_ENABLE", 10, "options.fboEnable", false, true),
      DIFFICULTY("DIFFICULTY", 11, "DIFFICULTY", 11, "DIFFICULTY", 11, "options.difficulty", false, false),
      GRAPHICS("GRAPHICS", 12, "GRAPHICS", 12, "GRAPHICS", 12, "options.graphics", false, false),
      AMBIENT_OCCLUSION("AMBIENT_OCCLUSION", 13, "AMBIENT_OCCLUSION", 13, "AMBIENT_OCCLUSION", 13, "options.ao", false, false),
      GUI_SCALE("GUI_SCALE", 14, "GUI_SCALE", 14, "GUI_SCALE", 14, "options.guiScale", false, false),
      RENDER_CLOUDS("RENDER_CLOUDS", 15, "RENDER_CLOUDS", 15, "RENDER_CLOUDS", 15, "options.renderClouds", false, true),
      PARTICLES("PARTICLES", 16, "PARTICLES", 16, "PARTICLES", 16, "options.particles", false, false),
      CHAT_VISIBILITY("CHAT_VISIBILITY", 17, "CHAT_VISIBILITY", 17, "CHAT_VISIBILITY", 17, "options.chat.visibility", false, false),
      CHAT_COLOR("CHAT_COLOR", 18, "CHAT_COLOR", 18, "CHAT_COLOR", 18, "options.chat.color", false, true),
      CHAT_LINKS("CHAT_LINKS", 19, "CHAT_LINKS", 19, "CHAT_LINKS", 19, "options.chat.links", false, true),
      CHAT_OPACITY("CHAT_OPACITY", 20, "CHAT_OPACITY", 20, "CHAT_OPACITY", 20, "options.chat.opacity", true, false),
      CHAT_LINKS_PROMPT("CHAT_LINKS_PROMPT", 21, "CHAT_LINKS_PROMPT", 21, "CHAT_LINKS_PROMPT", 21, "options.chat.links.prompt", false, true),
      SNOOPER_ENABLED("SNOOPER_ENABLED", 22, "SNOOPER_ENABLED", 22, "SNOOPER_ENABLED", 22, "options.snooper", false, true),
      USE_FULLSCREEN("USE_FULLSCREEN", 23, "USE_FULLSCREEN", 23, "USE_FULLSCREEN", 23, "options.fullscreen", false, true),
      ENABLE_VSYNC("ENABLE_VSYNC", 24, "ENABLE_VSYNC", 24, "ENABLE_VSYNC", 24, "options.vsync", false, true),
      SHOW_CAPE("SHOW_CAPE", 25, "SHOW_CAPE", 25, "SHOW_CAPE", 25, "options.showCape", false, true),
      TOUCHSCREEN("TOUCHSCREEN", 26, "TOUCHSCREEN", 26, "TOUCHSCREEN", 26, "options.touchscreen", false, true),
      CHAT_SCALE("CHAT_SCALE", 27, "CHAT_SCALE", 27, "CHAT_SCALE", 27, "options.chat.scale", true, false),
      CHAT_WIDTH("CHAT_WIDTH", 28, "CHAT_WIDTH", 28, "CHAT_WIDTH", 28, "options.chat.width", true, false),
      CHAT_HEIGHT_FOCUSED("CHAT_HEIGHT_FOCUSED", 29, "CHAT_HEIGHT_FOCUSED", 29, "CHAT_HEIGHT_FOCUSED", 29, "options.chat.height.focused", true, false),
      CHAT_HEIGHT_UNFOCUSED("CHAT_HEIGHT_UNFOCUSED", 30, "CHAT_HEIGHT_UNFOCUSED", 30, "CHAT_HEIGHT_UNFOCUSED", 30, "options.chat.height.unfocused", true, false),
      MIPMAP_LEVELS("MIPMAP_LEVELS", 31, "MIPMAP_LEVELS", 31, "MIPMAP_LEVELS", 31, "options.mipmapLevels", true, false, 0.0F, 4.0F, 1.0F),
      ANISOTROPIC_FILTERING("ANISOTROPIC_FILTERING", 32, "ANISOTROPIC_FILTERING", 32, "ANISOTROPIC_FILTERING", 32, "options.anisotropicFiltering", true, false, 1.0F, 16.0F, 0.0F, (Object)null, (Object)null, null) {
         private static final String __OBFID = "CL_00000654";
         private static final String __OBFID = "CL_00000654";

         protected float snapToStep(float p_148264_1_) {
            return (float)MathHelper.roundUpToPowerOfTwo((int)p_148264_1_);
         }
      },
      FORCE_UNICODE_FONT("FORCE_UNICODE_FONT", 33, "FORCE_UNICODE_FONT", 33, "FORCE_UNICODE_FONT", 33, "options.forceUnicodeFont", false, true),
      STREAM_BYTES_PER_PIXEL("STREAM_BYTES_PER_PIXEL", 34, "STREAM_BYTES_PER_PIXEL", 34, "STREAM_BYTES_PER_PIXEL", 34, "options.stream.bytesPerPixel", true, false),
      STREAM_VOLUME_MIC("STREAM_VOLUME_MIC", 35, "STREAM_VOLUME_MIC", 35, "STREAM_VOLUME_MIC", 35, "options.stream.micVolumne", true, false),
      STREAM_VOLUME_SYSTEM("STREAM_VOLUME_SYSTEM", 36, "STREAM_VOLUME_SYSTEM", 36, "STREAM_VOLUME_SYSTEM", 36, "options.stream.systemVolume", true, false),
      STREAM_KBPS("STREAM_KBPS", 37, "STREAM_KBPS", 37, "STREAM_KBPS", 37, "options.stream.kbps", true, false),
      STREAM_FPS("STREAM_FPS", 38, "STREAM_FPS", 38, "STREAM_FPS", 38, "options.stream.fps", true, false),
      STREAM_COMPRESSION("STREAM_COMPRESSION", 39, "STREAM_COMPRESSION", 39, "STREAM_COMPRESSION", 39, "options.stream.compression", false, false),
      STREAM_SEND_METADATA("STREAM_SEND_METADATA", 40, "STREAM_SEND_METADATA", 40, "STREAM_SEND_METADATA", 40, "options.stream.sendMetadata", false, true),
      STREAM_CHAT_ENABLED("STREAM_CHAT_ENABLED", 41, "STREAM_CHAT_ENABLED", 41, "STREAM_CHAT_ENABLED", 41, "options.stream.chat.enabled", false, false),
      STREAM_CHAT_USER_FILTER("STREAM_CHAT_USER_FILTER", 42, "STREAM_CHAT_USER_FILTER", 42, "STREAM_CHAT_USER_FILTER", 42, "options.stream.chat.userFilter", false, false),
      STREAM_MIC_TOGGLE_BEHAVIOR("STREAM_MIC_TOGGLE_BEHAVIOR", 43, "STREAM_MIC_TOGGLE_BEHAVIOR", 43, "STREAM_MIC_TOGGLE_BEHAVIOR", 43, "options.stream.micToggleBehavior", false, false),
      FOG_FANCY("FOG_FANCY", 44, "FOG_FANCY", 44, "FOG", 999, "Fog", false, false),
      FOG_START("FOG_START", 45, "FOG_START", 45, "", 999, "Fog Start", false, false),
      MIPMAP_TYPE("MIPMAP_TYPE", 46, "MIPMAP_TYPE", 46, "", 999, "Mipmap Type", false, false),
      LOAD_FAR("LOAD_FAR", 47, "LOAD_FAR", 47, "", 999, "Load Far", false, false),
      PRELOADED_CHUNKS("PRELOADED_CHUNKS", 48, "PRELOADED_CHUNKS", 48, "", 999, "Preloaded Chunks", false, false),
      SMOOTH_FPS("SMOOTH_FPS", 49, "SMOOTH_FPS", 49, "", 999, "Smooth FPS", false, false),
      CLOUDS("CLOUDS", 50, "CLOUDS", 50, "", 999, "Clouds", false, false),
      CLOUD_HEIGHT("CLOUD_HEIGHT", 51, "CLOUD_HEIGHT", 51, "", 999, "Cloud Height", true, false),
      TREES("TREES", 52, "TREES", 52, "", 999, "Trees", false, false),
      GRASS("GRASS", 53, "GRASS", 53, "", 999, "Grass", false, false),
      RAIN("RAIN", 54, "RAIN", 54, "", 999, "Rain & Snow", false, false),
      WATER("WATER", 55, "WATER", 55, "", 999, "Water", false, false),
      ANIMATED_WATER("ANIMATED_WATER", 56, "ANIMATED_WATER", 56, "", 999, "Water Animated", false, false),
      ANIMATED_LAVA("ANIMATED_LAVA", 57, "ANIMATED_LAVA", 57, "", 999, "Lava Animated", false, false),
      ANIMATED_FIRE("ANIMATED_FIRE", 58, "ANIMATED_FIRE", 58, "", 999, "Fire Animated", false, false),
      ANIMATED_PORTAL("ANIMATED_PORTAL", 59, "ANIMATED_PORTAL", 59, "", 999, "Portal Animated", false, false),
      AO_LEVEL("AO_LEVEL", 60, "AO_LEVEL", 60, "", 999, "Smooth Lighting Level", true, false),
      LAGOMETER("LAGOMETER", 61, "LAGOMETER", 61, "", 999, "Lagometer", false, false),
      SHOW_FPS("SHOW_FPS", 62, "SHOW_FPS", 62, "", 999, "Show FPS", false, false),
      AUTOSAVE_TICKS("AUTOSAVE_TICKS", 63, "AUTOSAVE_TICKS", 63, "", 999, "Autosave", false, false),
      BETTER_GRASS("BETTER_GRASS", 64, "BETTER_GRASS", 64, "", 999, "Better Grass", false, false),
      ANIMATED_REDSTONE("ANIMATED_REDSTONE", 65, "ANIMATED_REDSTONE", 65, "", 999, "Redstone Animated", false, false),
      ANIMATED_EXPLOSION("ANIMATED_EXPLOSION", 66, "ANIMATED_EXPLOSION", 66, "", 999, "Explosion Animated", false, false),
      ANIMATED_FLAME("ANIMATED_FLAME", 67, "ANIMATED_FLAME", 67, "", 999, "Flame Animated", false, false),
      ANIMATED_SMOKE("ANIMATED_SMOKE", 68, "ANIMATED_SMOKE", 68, "", 999, "Smoke Animated", false, false),
      WEATHER("WEATHER", 69, "WEATHER", 69, "", 999, "Weather", false, false),
      SKY("SKY", 70, "SKY", 70, "", 999, "Sky", false, false),
      STARS("STARS", 71, "STARS", 71, "", 999, "Stars", false, false),
      SUN_MOON("SUN_MOON", 72, "SUN_MOON", 72, "", 999, "Sun & Moon", false, false),
      VIGNETTE("VIGNETTE", 73, "VIGNETTE", 73, "", 999, "Vignette", false, false),
      CHUNK_UPDATES("CHUNK_UPDATES", 74, "CHUNK_UPDATES", 74, "", 999, "Chunk Updates", false, false),
      CHUNK_UPDATES_DYNAMIC("CHUNK_UPDATES_DYNAMIC", 75, "CHUNK_UPDATES_DYNAMIC", 75, "", 999, "Dynamic Updates", false, false),
      TIME("TIME", 76, "TIME", 76, "", 999, "Time", false, false),
      CLEAR_WATER("CLEAR_WATER", 77, "CLEAR_WATER", 77, "", 999, "Clear Water", false, false),
      SMOOTH_WORLD("SMOOTH_WORLD", 78, "SMOOTH_WORLD", 78, "", 999, "Smooth World", false, false),
      DEPTH_FOG("DEPTH_FOG", 79, "DEPTH_FOG", 79, "", 999, "Depth Fog", false, false),
      VOID_PARTICLES("VOID_PARTICLES", 80, "VOID_PARTICLES", 80, "", 999, "Void Particles", false, false),
      WATER_PARTICLES("WATER_PARTICLES", 81, "WATER_PARTICLES", 81, "", 999, "Water Particles", false, false),
      RAIN_SPLASH("RAIN_SPLASH", 82, "RAIN_SPLASH", 82, "", 999, "Rain Splash", false, false),
      PORTAL_PARTICLES("PORTAL_PARTICLES", 83, "PORTAL_PARTICLES", 83, "", 999, "Portal Particles", false, false),
      POTION_PARTICLES("POTION_PARTICLES", 84, "POTION_PARTICLES", 84, "", 999, "Potion Particles", false, false),
      PROFILER("PROFILER", 85, "PROFILER", 85, "", 999, "Debug Profiler", false, false),
      DRIPPING_WATER_LAVA("DRIPPING_WATER_LAVA", 86, "DRIPPING_WATER_LAVA", 86, "", 999, "Dripping Water/Lava", false, false),
      BETTER_SNOW("BETTER_SNOW", 87, "BETTER_SNOW", 87, "", 999, "Better Snow", false, false),
      FULLSCREEN_MODE("FULLSCREEN_MODE", 88, "FULLSCREEN_MODE", 88, "", 999, "Fullscreen Mode", false, false),
      ANIMATED_TERRAIN("ANIMATED_TERRAIN", 89, "ANIMATED_TERRAIN", 89, "", 999, "Terrain Animated", false, false),
      ANIMATED_ITEMS("ANIMATED_ITEMS", 90, "ANIMATED_ITEMS", 90, "", 999, "Items Animated", false, false),
      SWAMP_COLORS("SWAMP_COLORS", 91, "SWAMP_COLORS", 91, "", 999, "Swamp Colors", false, false),
      RANDOM_MOBS("RANDOM_MOBS", 92, "RANDOM_MOBS", 92, "", 999, "Random Mobs", false, false),
      SMOOTH_BIOMES("SMOOTH_BIOMES", 93, "SMOOTH_BIOMES", 93, "", 999, "Smooth Biomes", false, false),
      CUSTOM_FONTS("CUSTOM_FONTS", 94, "CUSTOM_FONTS", 94, "", 999, "Custom Fonts", false, false),
      CUSTOM_COLORS("CUSTOM_COLORS", 95, "CUSTOM_COLORS", 95, "", 999, "Custom Colors", false, false),
      SHOW_CAPES("SHOW_CAPES", 96, "SHOW_CAPES", 96, "", 999, "Show Capes", false, false),
      CONNECTED_TEXTURES("CONNECTED_TEXTURES", 97, "CONNECTED_TEXTURES", 97, "", 999, "Connected Textures", false, false),
      AA_LEVEL("AA_LEVEL", 98, "AA_LEVEL", 98, "", 999, "Antialiasing", false, false),
      ANIMATED_TEXTURES("ANIMATED_TEXTURES", 99, "ANIMATED_TEXTURES", 99, "", 999, "Textures Animated", false, false),
      NATURAL_TEXTURES("NATURAL_TEXTURES", 100, "NATURAL_TEXTURES", 100, "", 999, "Natural Textures", false, false),
      CHUNK_LOADING("CHUNK_LOADING", 101, "CHUNK_LOADING", 101, "", 999, "Chunk Loading", false, false),
      HELD_ITEM_TOOLTIPS("HELD_ITEM_TOOLTIPS", 102, "HELD_ITEM_TOOLTIPS", 102, "", 999, "Held Item Tooltips", false, false),
      DROPPED_ITEMS("DROPPED_ITEMS", 103, "DROPPED_ITEMS", 103, "", 999, "Dropped Items", false, false),
      LAZY_CHUNK_LOADING("LAZY_CHUNK_LOADING", 104, "LAZY_CHUNK_LOADING", 104, "", 999, "Lazy Chunk Loading", false, false),
      CUSTOM_SKY("CUSTOM_SKY", 105, "CUSTOM_SKY", 105, "", 999, "Custom Sky", false, false),
      FAST_MATH("FAST_MATH", 106, "FAST_MATH", 106, "", 999, "Fast Math", false, false),
      FAST_RENDER("FAST_RENDER", 107, "FAST_RENDER", 107, "", 999, "Fast Render", false, false),
      TRANSLUCENT_BLOCKS("TRANSLUCENT_BLOCKS", 108, "TRANSLUCENT_BLOCKS", 108, "", 999, "Translucent Blocks", false, false);
      private final boolean enumFloat;
      private final boolean enumBoolean;
      private final String enumString;
      private final float valueStep;
      private float valueMin;
      private float valueMax;
      private static final GameSettings.Options[] $VALUES = new GameSettings.Options[]{INVERT_MOUSE, SENSITIVITY, FOV, GAMMA, SATURATION, RENDER_DISTANCE, VIEW_BOBBING, ANAGLYPH, ADVANCED_OPENGL, FRAMERATE_LIMIT, FBO_ENABLE, DIFFICULTY, GRAPHICS, AMBIENT_OCCLUSION, GUI_SCALE, RENDER_CLOUDS, PARTICLES, CHAT_VISIBILITY, CHAT_COLOR, CHAT_LINKS, CHAT_OPACITY, CHAT_LINKS_PROMPT, SNOOPER_ENABLED, USE_FULLSCREEN, ENABLE_VSYNC, SHOW_CAPE, TOUCHSCREEN, CHAT_SCALE, CHAT_WIDTH, CHAT_HEIGHT_FOCUSED, CHAT_HEIGHT_UNFOCUSED, MIPMAP_LEVELS, ANISOTROPIC_FILTERING, FORCE_UNICODE_FONT, STREAM_BYTES_PER_PIXEL, STREAM_VOLUME_MIC, STREAM_VOLUME_SYSTEM, STREAM_KBPS, STREAM_FPS, STREAM_COMPRESSION, STREAM_SEND_METADATA, STREAM_CHAT_ENABLED, STREAM_CHAT_USER_FILTER, STREAM_MIC_TOGGLE_BEHAVIOR};
      private static final String __OBFID = "CL_00000653";
      private static final GameSettings.Options[] $VALUES$ = new GameSettings.Options[]{INVERT_MOUSE, SENSITIVITY, FOV, GAMMA, SATURATION, RENDER_DISTANCE, VIEW_BOBBING, ANAGLYPH, ADVANCED_OPENGL, FRAMERATE_LIMIT, FBO_ENABLE, DIFFICULTY, GRAPHICS, AMBIENT_OCCLUSION, GUI_SCALE, RENDER_CLOUDS, PARTICLES, CHAT_VISIBILITY, CHAT_COLOR, CHAT_LINKS, CHAT_OPACITY, CHAT_LINKS_PROMPT, SNOOPER_ENABLED, USE_FULLSCREEN, ENABLE_VSYNC, SHOW_CAPE, TOUCHSCREEN, CHAT_SCALE, CHAT_WIDTH, CHAT_HEIGHT_FOCUSED, CHAT_HEIGHT_UNFOCUSED, MIPMAP_LEVELS, ANISOTROPIC_FILTERING, FORCE_UNICODE_FONT, STREAM_BYTES_PER_PIXEL, STREAM_VOLUME_MIC, STREAM_VOLUME_SYSTEM, STREAM_KBPS, STREAM_FPS, STREAM_COMPRESSION, STREAM_SEND_METADATA, STREAM_CHAT_ENABLED, STREAM_CHAT_USER_FILTER, STREAM_MIC_TOGGLE_BEHAVIOR, FOG_FANCY, FOG_START, MIPMAP_TYPE, LOAD_FAR, PRELOADED_CHUNKS, SMOOTH_FPS, CLOUDS, CLOUD_HEIGHT, TREES, GRASS, RAIN, WATER, ANIMATED_WATER, ANIMATED_LAVA, ANIMATED_FIRE, ANIMATED_PORTAL, AO_LEVEL, LAGOMETER, SHOW_FPS, AUTOSAVE_TICKS, BETTER_GRASS, ANIMATED_REDSTONE, ANIMATED_EXPLOSION, ANIMATED_FLAME, ANIMATED_SMOKE, WEATHER, SKY, STARS, SUN_MOON, VIGNETTE, CHUNK_UPDATES, CHUNK_UPDATES_DYNAMIC, TIME, CLEAR_WATER, SMOOTH_WORLD, DEPTH_FOG, VOID_PARTICLES, WATER_PARTICLES, RAIN_SPLASH, PORTAL_PARTICLES, POTION_PARTICLES, PROFILER, DRIPPING_WATER_LAVA, BETTER_SNOW, FULLSCREEN_MODE, ANIMATED_TERRAIN, ANIMATED_ITEMS, SWAMP_COLORS, RANDOM_MOBS, SMOOTH_BIOMES, CUSTOM_FONTS, CUSTOM_COLORS, SHOW_CAPES, CONNECTED_TEXTURES, AA_LEVEL, ANIMATED_TEXTURES, NATURAL_TEXTURES, CHUNK_LOADING, HELD_ITEM_TOOLTIPS, DROPPED_ITEMS, LAZY_CHUNK_LOADING, CUSTOM_SKY, FAST_MATH, FAST_RENDER, TRANSLUCENT_BLOCKS};

      private static final GameSettings.Options[] $VALUES$$ = new GameSettings.Options[]{INVERT_MOUSE, SENSITIVITY, FOV, GAMMA, SATURATION, RENDER_DISTANCE, VIEW_BOBBING, ANAGLYPH, ADVANCED_OPENGL, FRAMERATE_LIMIT, FBO_ENABLE, DIFFICULTY, GRAPHICS, AMBIENT_OCCLUSION, GUI_SCALE, RENDER_CLOUDS, PARTICLES, CHAT_VISIBILITY, CHAT_COLOR, CHAT_LINKS, CHAT_OPACITY, CHAT_LINKS_PROMPT, SNOOPER_ENABLED, USE_FULLSCREEN, ENABLE_VSYNC, SHOW_CAPE, TOUCHSCREEN, CHAT_SCALE, CHAT_WIDTH, CHAT_HEIGHT_FOCUSED, CHAT_HEIGHT_UNFOCUSED, MIPMAP_LEVELS, ANISOTROPIC_FILTERING, FORCE_UNICODE_FONT, STREAM_BYTES_PER_PIXEL, STREAM_VOLUME_MIC, STREAM_VOLUME_SYSTEM, STREAM_KBPS, STREAM_FPS, STREAM_COMPRESSION, STREAM_SEND_METADATA, STREAM_CHAT_ENABLED, STREAM_CHAT_USER_FILTER, STREAM_MIC_TOGGLE_BEHAVIOR, FOG_FANCY, FOG_START, MIPMAP_TYPE, LOAD_FAR, PRELOADED_CHUNKS, SMOOTH_FPS, CLOUDS, CLOUD_HEIGHT, TREES, GRASS, RAIN, WATER, ANIMATED_WATER, ANIMATED_LAVA, ANIMATED_FIRE, ANIMATED_PORTAL, AO_LEVEL, LAGOMETER, SHOW_FPS, AUTOSAVE_TICKS, BETTER_GRASS, ANIMATED_REDSTONE, ANIMATED_EXPLOSION, ANIMATED_FLAME, ANIMATED_SMOKE, WEATHER, SKY, STARS, SUN_MOON, VIGNETTE, CHUNK_UPDATES, CHUNK_UPDATES_DYNAMIC, TIME, CLEAR_WATER, SMOOTH_WORLD, DEPTH_FOG, VOID_PARTICLES, WATER_PARTICLES, RAIN_SPLASH, PORTAL_PARTICLES, POTION_PARTICLES, PROFILER, DRIPPING_WATER_LAVA, BETTER_SNOW, FULLSCREEN_MODE, ANIMATED_TERRAIN, ANIMATED_ITEMS, SWAMP_COLORS, RANDOM_MOBS, SMOOTH_BIOMES, CUSTOM_FONTS, CUSTOM_COLORS, SHOW_CAPES, CONNECTED_TEXTURES, AA_LEVEL, ANIMATED_TEXTURES, NATURAL_TEXTURES, CHUNK_LOADING, HELD_ITEM_TOOLTIPS, DROPPED_ITEMS, LAZY_CHUNK_LOADING, CUSTOM_SKY, FAST_MATH, FAST_RENDER, TRANSLUCENT_BLOCKS};
      private static final String __OBFID = "CL_00000653";

      public static GameSettings.Options getEnumOptions(int par0) {
         GameSettings.Options[] var1 = values();
         int var2 = var1.length;

         for(int var3 = 0; var3 < var2; ++var3) {
            GameSettings.Options var4 = var1[var3];
            if(var4.returnEnumOrdinal() == par0) {
               return var4;
            }
         }

         return null;
      }

      private Options(String p_i46401_1_, int p_i46401_2_, String p_i46401_1_1, int p_i46401_2_1, String par1Str, int par2, String par3Str, boolean par4, boolean par5) {
         this(p_i46401_1_, p_i46401_2_, p_i46401_1_1, p_i46401_2_1, par1Str, par2, par3Str, par4, par5, 0.0F, 1.0F, 0.0F);
      }

      private Options(String p_i46402_1_, int p_i46402_2_, String p_i46402_1_1, int p_i46402_2_1, String p_i45004_1_, int p_i45004_2_, String p_i45004_3_, boolean p_i45004_4_, boolean p_i45004_5_, float p_i45004_6_, float p_i45004_7_, float p_i45004_8_) {
         this.enumString = p_i45004_3_;
         this.enumFloat = p_i45004_4_;
         this.enumBoolean = p_i45004_5_;
         this.valueMin = p_i45004_6_;
         this.valueMax = p_i45004_7_;
         this.valueStep = p_i45004_8_;
      }

      public boolean getEnumFloat() {
         return this.enumFloat;
      }

      public boolean getEnumBoolean() {
         return this.enumBoolean;
      }

      public int returnEnumOrdinal() {
         return this.ordinal();
      }

      public String getEnumString() {
         return this.enumString;
      }

      public float getValueMax() {
         return this.valueMax;
      }

      public void setValueMax(float p_148263_1_) {
         this.valueMax = p_148263_1_;
      }

      public float normalizeValue(float p_148266_1_) {
         return MathHelper.clamp_float((this.snapToStepClamp(p_148266_1_) - this.valueMin) / (this.valueMax - this.valueMin), 0.0F, 1.0F);
      }

      public float denormalizeValue(float p_148262_1_) {
         return this.snapToStepClamp(this.valueMin + (this.valueMax - this.valueMin) * MathHelper.clamp_float(p_148262_1_, 0.0F, 1.0F));
      }

      public float snapToStepClamp(float p_148268_1_) {
         p_148268_1_ = this.snapToStep(p_148268_1_);
         return MathHelper.clamp_float(p_148268_1_, this.valueMin, this.valueMax);
      }

      protected float snapToStep(float p_148264_1_) {
         if(this.valueStep > 0.0F) {
            p_148264_1_ = this.valueStep * (float)Math.round(p_148264_1_ / this.valueStep);
         }

         return p_148264_1_;
      }

      private Options(String p_i46403_1_, int p_i46403_2_, String p_i46403_1_1, int p_i46403_2_1, String p_i45005_1_, int p_i45005_2_, String p_i45005_3_, boolean p_i45005_4_, boolean p_i45005_5_, float p_i45005_6_, float p_i45005_7_, float p_i45005_8_, Object p_i45005_9_) {
         this(p_i46403_1_, p_i46403_2_, p_i46403_1_1, p_i46403_2_1, p_i45005_1_, p_i45005_2_, p_i45005_3_, p_i45005_4_, p_i45005_5_, p_i45005_6_, p_i45005_7_, p_i45005_8_);
      }

      private Options(String p_i46404_1_, int p_i46404_2_, String x0, int x1, String x2, int x3, String x4, boolean x5, boolean x6, float x7, float x8, float x9, Object x10, Object x11) {
         this(p_i46404_1_, p_i46404_2_, x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, x10);
      }

      Options(String x0, int x1, String x2, int x3, String x4, int x5, String x6, boolean x7, boolean x8, float x9, float x10, float x11, Object x12, Object x13, Object x14) {
         this(x0, x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12, x13);
      }
   }

   static final class SwitchOptions {
      static final int[] optionIds = new int[GameSettings.Options.values().length];
      private static final String __OBFID = "CL_00000652";
      private static final String __OBFID = "CL_00000652";

      static {
         try {
            optionIds[GameSettings.Options.INVERT_MOUSE.ordinal()] = 1;
         } catch (NoSuchFieldError var16) {
            ;
         }

         try {
            optionIds[GameSettings.Options.VIEW_BOBBING.ordinal()] = 2;
         } catch (NoSuchFieldError var15) {
            ;
         }

         try {
            optionIds[GameSettings.Options.ANAGLYPH.ordinal()] = 3;
         } catch (NoSuchFieldError var14) {
            ;
         }

         try {
            optionIds[GameSettings.Options.ADVANCED_OPENGL.ordinal()] = 4;
         } catch (NoSuchFieldError var13) {
            ;
         }

         try {
            optionIds[GameSettings.Options.FBO_ENABLE.ordinal()] = 5;
         } catch (NoSuchFieldError var12) {
            ;
         }

         try {
            optionIds[GameSettings.Options.RENDER_CLOUDS.ordinal()] = 6;
         } catch (NoSuchFieldError var11) {
            ;
         }

         try {
            optionIds[GameSettings.Options.CHAT_COLOR.ordinal()] = 7;
         } catch (NoSuchFieldError var10) {
            ;
         }

         try {
            optionIds[GameSettings.Options.CHAT_LINKS.ordinal()] = 8;
         } catch (NoSuchFieldError var9) {
            ;
         }

         try {
            optionIds[GameSettings.Options.CHAT_LINKS_PROMPT.ordinal()] = 9;
         } catch (NoSuchFieldError var8) {
            ;
         }

         try {
            optionIds[GameSettings.Options.SNOOPER_ENABLED.ordinal()] = 10;
         } catch (NoSuchFieldError var7) {
            ;
         }

         try {
            optionIds[GameSettings.Options.USE_FULLSCREEN.ordinal()] = 11;
         } catch (NoSuchFieldError var6) {
            ;
         }

         try {
            optionIds[GameSettings.Options.ENABLE_VSYNC.ordinal()] = 12;
         } catch (NoSuchFieldError var5) {
            ;
         }

         try {
            optionIds[GameSettings.Options.SHOW_CAPE.ordinal()] = 13;
         } catch (NoSuchFieldError var4) {
            ;
         }

         try {
            optionIds[GameSettings.Options.TOUCHSCREEN.ordinal()] = 14;
         } catch (NoSuchFieldError var3) {
            ;
         }

         try {
            optionIds[GameSettings.Options.STREAM_SEND_METADATA.ordinal()] = 15;
         } catch (NoSuchFieldError var2) {
            ;
         }

         try {
            optionIds[GameSettings.Options.FORCE_UNICODE_FONT.ordinal()] = 16;
         } catch (NoSuchFieldError var1) {
            ;
         }
      }
   }
}
