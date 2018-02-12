package reifnsk.minimap;

import java.awt.Desktop;
import java.awt.Point;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.net.SocketAddress;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.ChatLine;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.NetworkManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.EmptyChunk;
import org.lwjgl.Sys;
import org.lwjgl.opengl.GL11;

public class ReiMinimap implements Runnable, IResourceManagerReloadListener {
   public static final boolean DEBUG_BUILD = false;
   public static final int MC_VERSION_INT = 33621762;
   public static final String MC_1710 = "1.7.10";
   public static final String MC_172 = "1.7.2";
   public static final String MC_162 = "1.6.2";
   public static final String MC_161 = "1.6.1";
   public static final String MC_152 = "1.5.2";
   public static final String MC_151 = "1.5.1";
   public static final String MC_150 = "1.5";
   public static final String MC_147 = "1.4.7";
   public static final String MC_146 = "1.4.6";
   public static final String MC_145 = "1.4.5";
   public static final String MC_144 = "1.4.4";
   public static final String MC_142 = "1.4.2";
   public static final String MC_141 = "1.4.1";
   public static final String MC_132 = "1.3.2";
   public static final String MC_131P = "1.3.1";
   public static final String MC_125 = "1.2.5";
   public static final String MC_124 = "1.2.4";
   public static final String MC_123 = "1.2.3";
   public static final String MC_110 = "1.1";
   public static final String MC_100 = "1.0.0";
   public static final String MC_B19P5 = "Beta 1.9pre5";
   public static final String MC_B19P4 = "Beta 1.9pre4";
   public static final String MC_B181 = "Beta 1.8.1";
   public static final String MC_B180 = "Beta 1.8";
   public static final String MC_B173 = "Beta 1.7.3";
   public static final String MC_B166 = "Beta 1.6.6";
   public static final String MC_B151 = "Beta 1.5_01";
   public static final int MOD_VERSION_INT = 197635;
   public static final String MOD_VERSION = "v1";
   public static final String MC_VERSION = "1.7.2";
   public static final String version = String.format("%s [%s]", new Object[]{"v1", "1.7.2"});
   public static final boolean SUPPORT_HEIGHT_MOD = true;
   public static final boolean SUPPORT_NEW_LIGHTING = true;
   public static final boolean SUPPORT_SWAMPLAND_BIOME_COLOR = true;
   public static final boolean CHANGE_SUNRISE_DIRECTION = true;
   public boolean useModloader;
   private static final double renderZ = 1.0D;
   private static final boolean noiseAdded = false;
   private static final float noiseAlpha = 0.1F;
   static final File directory = new File(Minecraft.getMinecraft().mcDataDir, "mods" + File.separatorChar + "rei_minimap");
   private float[] lightBrightnessTable = this.generateLightBrightnessTable(0.125F);
   private static final int[] updateFrequencys = new int[]{2, 5, 10, 20, 40};
   public static final ReiMinimap instance = new ReiMinimap();
   private static final int TEXTURE_SIZE = 256;
   private int updateCount;
   private static BiomeGenBase[] bgbList;
   Minecraft theMinecraft;
   MinecraftServer server;
   private Tessellator tessellator;
   private World theWorld;
   private EntityPlayer thePlayer;
   private double playerPosX;
   private double playerPosY;
   private double playerPosZ;
   private float playerRotationYaw;
   private float playerRotationPitch;
   private GuiIngame ingameGUI;
   private ScaledResolution scaledResolution;
   private String errorString;
   private boolean multiplayer;
   private SocketAddress currentServer;
   private String currentLevelName;
   private int currentDimension;
   private int scWidth;
   private int scHeight;
   private GLTextureBufferedImage texture;
   final Thread mcThread;
   private Thread workerThread;
   private Lock lock;
   private Condition condition;
   private StripCounter stripCounter;
   private int stripCountMax1;
   private int stripCountMax2;
   private GuiScreen guiScreen;
   private int posX;
   private int posY;
   private double posYd;
   private int posZ;
   private int chunkCoordX;
   private int chunkCoordZ;
   private float sin;
   private float cos;
   private int lastX;
   private int lastY;
   private int lastZ;
   private int skylightSubtracted;
   private boolean isUpdateImage;
   private boolean isCompleteImage;
   private boolean enable;
   private boolean showMenuKey;
   private boolean filtering;
   private int mapPosition;
   private int textureView;
   private float mapOpacity;
   private float largeMapOpacity;
   private boolean largeMapLabel;
   private int lightmap;
   private int lightType;
   private boolean undulate;
   private boolean transparency;
   private boolean environmentColor;
   private boolean omitHeightCalc;
   private int updateFrequencySetting;
   private boolean threading;
   private int threadPriority;
   private boolean preloadedChunks;
   private boolean hideSnow;
   private boolean showChunkGrid;
   private boolean showSlimeChunk;
   private boolean heightmap;
   private boolean showCoordinate;
   private int fontScale;
   private int mapScale;
   private int largeMapScale;
   private int coordinateType;
   private boolean visibleWaypoints;
   private boolean useStencil;
   private boolean notchDirection;
   private boolean roundmap;
   private boolean fullmap;
   private boolean forceUpdate;
   private boolean marker;
   private boolean markerLabel;
   private boolean markerIcon;
   private boolean markerDistance;
   private long currentTimeMillis;
   private long currentTime;
   private long previousTime;
   private int renderType;
   private TreeMap<Integer, List<Waypoint>> wayPtsMap;
   private List<Waypoint> wayPts;
   private int waypointDimension;
   private static final double[] ZOOM_LIST;
   private int defaultZoom;
   private int flagZoom;
   private int largeZoom;
   private double targetZoom;
   private double currentZoom;
   private float zoomVisible;
   private int grassColor;
   private int foliageColor;
   private int foliageColorPine;
   private int foliageColorBirch;
   private int tfOakColor;
   private int tfCanopyColor;
   private int tfMangroveColor;
   private int worldHeight;
   private int[] temperatureColor;
   private int[] humidityColor;
   private HashMap<Integer, String> dimensionName;
   private HashMap<Integer, Double> dimensionScale;
   private boolean chatWelcomed;
   private List<ChatLine> chatLineList;
   private ChatLine chatLineLast;
   private long chatTime;
   private boolean configEntitiesRadar;
   private boolean configEntityPlayer;
   private boolean configEntityAnimal;
   private boolean configEntityMob;
   private boolean configEntitySquid;
   private boolean configEntitySlime;
   private boolean configEntityLiving;
   private boolean configEntityLightning;
   private boolean configEntityDirection;
   private boolean allowCavemap;
   private boolean allowEntitiesRadar;
   private boolean allowEntityPlayer;
   private boolean allowEntityAnimal;
   private boolean allowEntityMob;
   private boolean allowEntitySquid;
   private boolean allowEntitySlime;
   private boolean allowEntityLiving;
   private boolean visibleEntitiesRadar;
   private boolean visibleEntityPlayer;
   private boolean visibleEntityAnimal;
   private boolean visibleEntityMob;
   private boolean visibleEntitySquid;
   private boolean visibleEntitySlime;
   private boolean visibleEntityLiving;
   private long seed;
   private long ticksExisted;
   private static final int ENTITY_PLAYER_TYPE = 0;
   private static final int ENTITY_MOB_TYPE = 1;
   private static final int ENTITY_ANIMAL_TYPE = 2;
   private static final int ENTITY_SQUID_TYPE = 3;
   private static final int ENTITY_SLIME_TYPE = 4;
   private static final int ENTITY_LIVING_TYPE = 5;
   private static final int ENTITY_INVASION_MOB_TYPE = 6;
   private List<EntityLivingBase>[] visibleEntities;
   private int[] visibleEntityColor;
   private List<Entity> weatherEffects;
   private static final Class entityIMWaveAttackerClass;
   private boolean autoUpdateCheck;
   private int updateCheckFlag;
   private URL updateCheckURL;
   private float renderPartialTicks;
   boolean updateTexture;
   long ntime;
   int count;
   static float[] temp;
   private float[] lightmapRed;
   private float[] lightmapGreen;
   private float[] lightmapBlue;

   boolean getAllowCavemap() {
      return this.allowCavemap;
   }

   boolean getAllowEntitiesRadar() {
      return this.allowEntitiesRadar;
   }

   private ReiMinimap() {
      this.tessellator = Tessellator.instance;
      this.texture = GLTextureBufferedImage.create(256, 256);
      this.lock = new ReentrantLock();
      this.condition = this.lock.newCondition();
      this.stripCounter = new StripCounter(289);
      this.stripCountMax1 = 0;
      this.stripCountMax2 = 0;
      this.enable = true;
      this.showMenuKey = true;
      this.filtering = true;
      this.mapPosition = 2;
      this.textureView = 0;
      this.mapOpacity = 1.0F;
      this.largeMapOpacity = 1.0F;
      this.largeMapLabel = false;
      this.lightmap = 0;
      this.lightType = 0;
      this.undulate = true;
      this.transparency = true;
      this.environmentColor = true;
      this.omitHeightCalc = true;
      this.updateFrequencySetting = 2;
      this.threading = false;
      this.threadPriority = 1;
      this.preloadedChunks = false;
      this.hideSnow = false;
      this.showChunkGrid = false;
      this.showSlimeChunk = false;
      this.heightmap = true;
      this.showCoordinate = true;
      this.fontScale = 1;
      this.mapScale = 1;
      this.largeMapScale = 1;
      this.coordinateType = 1;
      this.visibleWaypoints = true;
      this.useStencil = false;
      this.notchDirection = true;
      this.roundmap = false;
      this.fullmap = false;
      this.marker = true;
      this.markerLabel = true;
      this.markerIcon = true;
      this.markerDistance = true;
      this.renderType = 0;
      this.wayPtsMap = new TreeMap();
      this.wayPts = new ArrayList();
      this.defaultZoom = 1;
      this.flagZoom = 1;
      this.largeZoom = 0;
      this.targetZoom = 1.0D;
      this.currentZoom = 1.0D;
      this.tfOakColor = 4764952;
      this.tfCanopyColor = 6330464;
      this.tfMangroveColor = 8431445;
      this.worldHeight = 255;
      this.dimensionName = new HashMap();
      this.dimensionScale = new HashMap();
      this.dimensionName.put(Integer.valueOf(0), "Overworld");
      this.dimensionScale.put(Integer.valueOf(0), Double.valueOf(1.0D));
      this.dimensionName.put(Integer.valueOf(-1), "Nether");
      this.dimensionScale.put(Integer.valueOf(-1), Double.valueOf(8.0D));
      this.dimensionName.put(Integer.valueOf(1), "The Ender");
      this.dimensionScale.put(Integer.valueOf(1), Double.valueOf(1.0D));
      this.chatTime = 0L;
      this.configEntitiesRadar = false;
      this.configEntityPlayer = true;
      this.configEntityAnimal = true;
      this.configEntityMob = true;
      this.configEntitySquid = true;
      this.configEntitySlime = true;
      this.configEntityLiving = true;
      this.configEntityLightning = true;
      this.configEntityDirection = true;
      this.visibleEntities = new ArrayList[7];
      this.visibleEntityColor = new int[]{-16711681, -65536, -1, -16760704, -10444704, -12533632, -8388416};
      this.weatherEffects = new ArrayList(256);
      this.autoUpdateCheck = false;
      this.updateCheckFlag = 0;

      try {
         this.updateCheckURL = new URL("http://dl.dropbox.com/u/34787499/minecraft/version.txt");
      } catch (Exception var2) {
         ;
      }

      this.ntime = 0L;
      this.count = 0;
      this.lightmapRed = new float[256];
      this.lightmapGreen = new float[256];
      this.lightmapBlue = new float[256];
      if(!directory.exists()) {
         directory.mkdirs();
      }

      if(!directory.isDirectory()) {
         this.errorString = "[Rei\'s Minimap] ERROR: Failed to create the rei_minimap folder.";
         error(this.errorString);
      }

      this.loadOptions();
      this.mcThread = Thread.currentThread();

      for(int i = 0; i < this.visibleEntities.length; ++i) {
         this.visibleEntities[i] = new ArrayList();
      }
   }

   public void onTickInGame(float f, Minecraft mc) {
      try {
         this.renderPartialTicks = f;
         this.currentTimeMillis = System.currentTimeMillis();
         GL11.glPushAttrib(1048575);
         GL11.glPushClientAttrib(-1);
         GL11.glPushMatrix();

         label2460: {
            try {
               if(mc == null) {
                  return;
               }

               if(this.errorString != null) {
                  this.scaledResolution = new ScaledResolution(this.theMinecraft, mc.displayWidth, mc.displayHeight);
                  mc.fontRenderer.drawStringWithShadow(this.errorString, this.scaledResolution.getScaledWidth() - mc.fontRenderer.getStringWidth(this.errorString) - 2, 2, -65536);
                  return;
               }

               Field[] var52;
               int displayHeight;
               int elapseTime;
               int var57;
               int var68;
               if(this.theMinecraft == null) {
                  ((IReloadableResourceManager)Minecraft.getMinecraft().getResourceManager()).registerReloadListener(this);
                  this.theMinecraft = mc;
                  this.ingameGUI = this.theMinecraft.ingameGUI;

                  Field var67;
                  try {
                     var68 = 0;
                     var52 = GuiNewChat.class.getDeclaredFields();
                     displayHeight = var52.length;

                     for(elapseTime = 0; elapseTime < displayHeight; ++elapseTime) {
                        var67 = var52[elapseTime];
                        if(var67.getType() == List.class && var68++ == 1) {
                           var67.setAccessible(true);
                           this.chatLineList = (List)var67.get(this.ingameGUI.getChatGUI());
                           break;
                        }
                     }
                  } catch (Exception var40) {
                     ;
                  }

                  this.chatLineList = (List)(this.chatLineList == null?new ArrayList():this.chatLineList);

                  try {
                     Field[] var44 = RenderManager.class.getDeclaredFields();
                     var57 = var44.length;

                     for(displayHeight = 0; displayHeight < var57; ++displayHeight) {
                        var67 = var44[displayHeight];
                        if(var67.getType() == Map.class) {
                           WaypointEntityRender y = new WaypointEntityRender(mc);
                           y.setRenderManager(RenderManager.instance);
                           var67.setAccessible(true);
                           ((Map)var67.get(RenderManager.instance)).put(WaypointEntity.class, y);
                           break;
                        }
                     }
                  } catch (Exception var39) {
                     var39.printStackTrace();
                  }
               }

               this.thePlayer = this.theMinecraft.thePlayer;
               this.playerPosX = this.thePlayer.prevPosX + (this.thePlayer.posX - this.thePlayer.prevPosX) * (double)f;
               this.playerPosY = this.thePlayer.prevPosY + (this.thePlayer.posY - this.thePlayer.prevPosY) * (double)f;
               this.playerPosZ = this.thePlayer.prevPosZ + (this.thePlayer.posZ - this.thePlayer.prevPosZ) * (double)f;
               this.playerRotationYaw = this.thePlayer.prevRotationYaw + (this.thePlayer.rotationYaw - this.thePlayer.prevRotationYaw) * f;
               this.playerRotationPitch = this.thePlayer.prevRotationPitch + (this.thePlayer.rotationPitch - this.thePlayer.prevRotationPitch) * f;
               int scale;
               Matcher var77;
               char[] var85;
               char var86;
               int var46;
               if(this.theWorld != this.theMinecraft.theWorld) {
                  this.updateCount = 0;
                  this.isUpdateImage = false;
                  this.texture.unregister();
                  this.theWorld = this.theMinecraft.theWorld;
                  this.theWorld.addWeatherEffect(new WaypointEntity(this.theMinecraft));
                  this.multiplayer = this.theMinecraft.getIntegratedServer() == null;
                  Arrays.fill(this.texture.data, (byte)0);
                  if(this.theWorld != null) {
                     this.worldHeight = this.theWorld.getHeight() - 1;
                     ChunkData.init();
                     String var79;
                     boolean var61;
                     if(this.multiplayer) {
                        this.seed = 0L;
                        var52 = null;
                        SocketAddress var82 = getRemoteSocketAddress(this.thePlayer);
                        if(var82 == null) {
                           throw new MinimapException("SMP ADDRESS ACQUISITION FAILURE");
                        }

                        var61 = this.currentServer != var82;
                        if(var61) {
                           String var45 = var82.toString().replaceAll("[\r\n]", "");
                           var77 = Pattern.compile("(.*)/(.*):([0-9]+)").matcher(var45);
                           if(!var77.matches()) {
                              String var59 = var82.toString().replaceAll("[a-z]", "a").replaceAll("[A-Z]", "A").replaceAll("[0-9]", "*");
                              throw new MinimapException("SMP ADDRESS FORMAT EXCEPTION: " + var59);
                           }

                           var79 = var77.group(1);
                           if(var79.isEmpty()) {
                              var79 = var77.group(2);
                           }

                           if(!var77.group(3).equals("25565")) {
                              var79 = var79 + "[" + var77.group(3) + "]";
                           }

                           var85 = ChatAllowedCharacters.allowedCharacters;
                           var46 = var85.length;

                           for(scale = 0; scale < var46; ++scale) {
                              var86 = var85[scale];
                              var79 = var79.replace(var86, '_');
                           }

                           this.currentLevelName = var79;
                           this.currentServer = var82;
                        }
                     } else {
                        var79 = this.theMinecraft.getIntegratedServer().getWorldName();
                        if(var79 == null) {
                           throw new MinimapException("WORLD_NAME ACQUISITION FAILURE");
                        }

                        char[] var50 = ChatAllowedCharacters.allowedCharacters;
                        elapseTime = var50.length;

                        for(var68 = 0; var68 < elapseTime; ++var68) {
                           char var56 = var50[var68];
                           var79 = var79.replace(var56, '_');
                        }

                        var61 = !var79.equals(this.currentLevelName) || this.currentServer != null;
                        if(var61) {
                           this.currentLevelName = var79;
                           var61 = true;
                        }

                        this.currentServer = null;
                     }

                     if(var61) {
                        this.chatTime = System.currentTimeMillis();
                        this.chatWelcomed = !this.multiplayer;
                        this.allowEntitiesRadar = !this.multiplayer;
                        this.allowEntityPlayer = !this.multiplayer;
                        this.allowEntityAnimal = !this.multiplayer;
                        this.allowEntityMob = !this.multiplayer;
                        this.allowEntitySlime = !this.multiplayer;
                        this.allowEntitySquid = !this.multiplayer;
                        this.allowEntityLiving = !this.multiplayer;
                        this.loadWaypoints();
                     }
                  }

                  this.stripCounter.reset();
                  this.currentDimension = -2147483647;
               }

               if(this.currentDimension != this.thePlayer.dimension) {
                  this.currentDimension = this.thePlayer.dimension;
                  this.waypointDimension = this.currentDimension;
                  this.wayPts = (List)this.wayPtsMap.get(Integer.valueOf(this.waypointDimension));
                  if(this.wayPts == null) {
                     this.wayPts = new ArrayList();
                     this.wayPtsMap.put(Integer.valueOf(this.waypointDimension), this.wayPts);
                  }
               }

               int var47;
               if(!this.chatWelcomed && System.currentTimeMillis() < this.chatTime + 10000L) {
                  Iterator var48 = this.chatLineList.iterator();

                  while(var48.hasNext()) {
                     ChatLine var51 = (ChatLine)var48.next();
                     if(var51 == null || this.chatLineLast == var51) {
                        break;
                     }

                     var77 = Pattern.compile("\u00a70\u00a70((?:\u00a7[1-9a-d])+)\u00a7e\u00a7f").matcher(var51.func_151461_a().getUnformattedTextForChat());

                     while(var77.find()) {
                        this.chatWelcomed = true;
                        var85 = var77.group(1).toCharArray();
                        var68 = var85.length;

                        for(var47 = 0; var47 < var68; ++var47) {
                           var86 = var85[var47];
                           switch(var86) {
                           case 50:
                              this.allowEntityPlayer = true;
                              break;
                           case 51:
                              this.allowEntityAnimal = true;
                              break;
                           case 52:
                              this.allowEntityMob = true;
                              break;
                           case 53:
                              this.allowEntitySlime = true;
                              break;
                           case 54:
                              this.allowEntitySquid = true;
                              break;
                           case 55:
                              this.allowEntityLiving = true;
                           }
                        }
                     }
                  }

                  this.chatLineLast = this.chatLineList.isEmpty()?null:(ChatLine)this.chatLineList.get(0);
                  if(this.chatWelcomed) {
                     this.allowEntitiesRadar = this.allowEntityPlayer || this.allowEntityAnimal || this.allowEntityMob || this.allowEntitySlime || this.allowEntitySquid || this.allowEntityLiving;
                     if(this.allowCavemap) {
                        this.chatInfo("\u00a7E[Rei\'s Minimap] enabled: cavemapping.");
                     }

                     if(this.allowEntitiesRadar) {
                        StringBuilder var521 = new StringBuilder("\u00a7E[Rei\'s Minimap] enabled: entities radar (");
                        if(this.allowEntityPlayer) {
                           var521.append("Player, ");
                        }

                        if(this.allowEntityAnimal) {
                           var521.append("Animal, ");
                        }

                        if(this.allowEntityMob) {
                           var521.append("Mob, ");
                        }

                        if(this.allowEntitySlime) {
                           var521.append("Slime, ");
                        }

                        if(this.allowEntitySquid) {
                           var521.append("Squid, ");
                        }

                        if(this.allowEntityLiving) {
                           var521.append("Living, ");
                        }

                        var521.setLength(var521.length() - 2);
                        var521.append(")");
                        this.chatInfo(var521.toString());
                     }
                  }
               } else {
                  this.chatWelcomed = true;
               }

               this.visibleEntitiesRadar = this.allowEntitiesRadar && this.configEntitiesRadar;
               this.visibleEntityPlayer = this.allowEntityPlayer && this.configEntityPlayer;
               this.visibleEntityAnimal = this.allowEntityAnimal && this.configEntityAnimal;
               this.visibleEntityMob = this.allowEntityMob && this.configEntityMob;
               this.visibleEntitySlime = this.allowEntitySlime && this.configEntitySlime;
               this.visibleEntitySquid = this.allowEntitySquid && this.configEntitySquid;
               this.visibleEntityLiving = this.allowEntityLiving && this.configEntityLiving;
               Object var49 = this.thePlayer.ridingEntity == null?this.thePlayer:this.thePlayer.ridingEntity;
               if((long)((Entity)var49).ticksExisted != this.ticksExisted) {
                  if(this.updateTexture) {
                     this.updateTexture = false;
                     ChunkData.updateTexture();
                  }

                  ++this.updateCount;
                  this.ticksExisted = (long)this.thePlayer.ticksExisted;

                  for(var57 = -8; var57 <= 8; ++var57) {
                     for(displayHeight = -8; displayHeight <= 8; ++displayHeight) {
                        ChunkData var53 = ChunkData.createChunkData(this.thePlayer.chunkCoordX + displayHeight, this.thePlayer.chunkCoordZ + var57);
                        if(var53 != null) {
                           var53.updateChunk(this.preloadedChunks);
                        }
                     }
                  }

                  List[] var54 = this.visibleEntities;
                  displayHeight = var54.length;

                  for(elapseTime = 0; elapseTime < displayHeight; ++elapseTime) {
                     List var571 = var54[elapseTime];
                     var571.clear();
                  }

                  this.weatherEffects.clear();
                  if(this.visibleEntitiesRadar) {
                     Iterator var58 = this.theWorld.loadedEntityList.iterator();

                     while(var58.hasNext()) {
                        Entity var60 = (Entity)var58.next();
                        elapseTime = this.getVisibleEntityType(var60);
                        if(elapseTime != -1) {
                           this.visibleEntities[elapseTime].add((EntityLivingBase)var60);
                        }
                     }

                     this.weatherEffects.addAll(this.theWorld.weatherEffects);
                  }
               }

               var57 = this.theMinecraft.displayWidth;
               displayHeight = this.theMinecraft.displayHeight;
               this.scaledResolution = new ScaledResolution(this.theMinecraft, var57, displayHeight);
               GL11.glScaled(1.0D / (double)this.scaledResolution.getScaleFactor(), 1.0D / (double)this.scaledResolution.getScaleFactor(), 1.0D);
               this.scWidth = mc.displayWidth;
               this.scHeight = mc.displayHeight;
               KeyInput.update();
               if(mc.currentScreen == null) {
                  if(!this.fullmap) {
                     this.targetZoom = ZOOM_LIST[this.flagZoom];
                  } else {
                     this.targetZoom = ZOOM_LIST[this.largeZoom];
                  }

                  if(KeyInput.TOGGLE_WAYPOINTS_DIMENSION.isKeyPush()) {
                     if(this.theMinecraft.gameSettings.keyBindSneak.getIsKeyPressed()) {
                        this.prevDimension();
                     } else {
                        this.nextDimension();
                     }
                  }

                  if(KeyInput.TOGGLE_WAYPOINTS_VISIBLE.isKeyPush()) {
                     this.visibleWaypoints = !this.visibleWaypoints;
                  }

                  if(KeyInput.TOGGLE_WAYPOINTS_MARKER.isKeyPush()) {
                     this.marker = !this.marker;
                  }

                  if(KeyInput.SET_WAYPOINT.isKeyPushUp()) {
                     this.waypointDimension = this.currentDimension;
                     this.wayPts = (List)this.wayPtsMap.get(Integer.valueOf(this.waypointDimension));
                     if(this.wayPts == null) {
                        this.wayPts = new ArrayList();
                        this.wayPtsMap.put(Integer.valueOf(this.waypointDimension), this.wayPts);
                     }

                     mc.displayGuiScreen(new GuiWaypointEditorScreen(mc, (Waypoint)null));
                  }

                  if(KeyInput.WAYPOINT_LIST.isKeyPushUp()) {
                     mc.displayGuiScreen(new GuiWaypointScreen((GuiScreen)null));
                  }

                  if(KeyInput.MENU_KEY.isKeyPush()) {
                     mc.displayGuiScreen(new GuiOptionScreen());
                  }
               } else if(this.fullmap) {
                  this.currentZoom = this.targetZoom = ZOOM_LIST[this.flagZoom];
                  this.fullmap = false;
                  this.forceUpdate = true;
                  this.stripCounter.reset();
               }

               this.guiScreen = this.theMinecraft.currentScreen;
               if(this.enable && checkGuiScreen(mc.currentScreen) && BlockDataPack.defaultBlockColor != null) {
                  if(this.threading) {
                     if(this.workerThread == null || !this.workerThread.isAlive()) {
                        this.workerThread = new Thread(this);
                        this.workerThread.setPriority(3 + this.threadPriority);
                        this.workerThread.setDaemon(true);
                        this.workerThread.start();
                     }
                  } else {
                     this.mapCalc(true);
                  }

                  if(this.lock.tryLock()) {
                     try {
                        if(this.isUpdateImage) {
                           this.isUpdateImage = false;
                           this.texture.setMinFilter(this.filtering);
                           this.texture.setMagFilter(this.filtering);
                           this.texture.setClampTexture(true);
                           this.texture.register();
                        }

                        this.condition.signal();
                     } finally {
                        this.lock.unlock();
                     }
                  }

                  this.currentTime = System.nanoTime();
                  double var55 = (double)(this.currentTime - this.previousTime) * 1.0E-9D;
                  this.zoomVisible = (float)((double)this.zoomVisible - var55);
                  if(this.currentZoom != this.targetZoom) {
                     double var611 = Math.max(0.0D, Math.min(1.0D, var55 * 4.0D));
                     this.currentZoom += (this.targetZoom - this.currentZoom) * var611;
                     if(Math.abs(this.currentZoom - this.targetZoom) < 5.0E-4D) {
                        this.currentZoom = this.targetZoom;
                     }

                     this.zoomVisible = 3.0F;
                  }

                  this.previousTime = this.currentTime;
                  if(this.texture.getId() != 0) {
                     scale = this.fontScale == 0?this.scaledResolution.getScaleFactor() + 1 >> 1:this.fontScale;
                     boolean var62;
                     boolean var63;
                     int var10000;
                     switch(this.mapPosition) {
                     case 0:
                        var63 = true;
                        var62 = true;
                        break label2460;
                     case 1:
                        var63 = true;
                        var46 = this.scHeight - 37;
                        var10000 = var46 - scale * ((this.showMenuKey | this.showCoordinate?2:0) + (this.showMenuKey?9:0) + (this.showCoordinate?18:0)) / this.scaledResolution.getScaleFactor();
                        break label2460;
                     case 2:
                     default:
                        var47 = this.scWidth - 37;
                        var62 = true;
                        break label2460;
                     case 3:
                        var47 = this.scWidth - 37;
                        var46 = this.scHeight - 37;
                        var10000 = var46 - scale * ((this.showMenuKey | this.showCoordinate?2:0) + (this.showMenuKey?9:0) + (this.showCoordinate?18:0)) / this.scaledResolution.getScaleFactor();
                     }
                  }
                  break label2460;
               }
            } catch (RuntimeException var41) {
               var41.printStackTrace();
               this.errorString = "[Rei\'s Minimap] ERROR: " + var41.getMessage();
               error("mainloop runtime exception", var41);
               break label2460;
            } finally {
               GL11.glPopMatrix();
               GL11.glPopClientAttrib();
               GL11.glPopAttrib();
            }

            return;
         }

         if(this.count != 0) {
            this.theMinecraft.fontRenderer.drawStringWithShadow(String.format("%12d", new Object[]{Long.valueOf(this.ntime / (long)this.count)}), 2, 12, -1);
         }

         Thread.yield();
      } finally {
         ;
      }
   }

   public void run() {
      if(this.theMinecraft != null) {
         Thread currentThread = Thread.currentThread();

         while(true) {
            while(!this.enable || currentThread != this.workerThread || !this.threading) {
               try {
                  Thread.sleep(1000L);
               } catch (InterruptedException var20) {
                  return;
               }

               this.lock.lock();

               label211: {
                  try {
                     this.condition.await();
                     break label211;
                  } catch (InterruptedException var24) {
                     ;
                  } finally {
                     this.lock.unlock();
                  }

                  return;
               }

               if(currentThread != this.workerThread) {
                  return;
               }
            }

            try {
               if(this.renderType == 0) {
                  Thread.sleep((long)(updateFrequencys[updateFrequencys.length - this.updateFrequencySetting - 1] * 2));
               } else {
                  Thread.sleep((long)(updateFrequencys[updateFrequencys.length - this.updateFrequencySetting - 1] * 6));
               }
            } catch (InterruptedException var19) {
               return;
            }

            this.lock.lock();

            try {
               this.mapCalc(false);
               if(this.isCompleteImage || this.isUpdateImage) {
                  this.condition.await();
               }
               continue;
            } catch (InterruptedException var21) {
               ;
            } catch (Exception var22) {
               var22.printStackTrace();
               this.errorString = "[Rei\'s Minimap] ERROR: " + var22.getMessage();
               error("mainloop runtime exception", var22);
               continue;
            } finally {
               this.lock.unlock();
            }

            return;
         }
      }
   }

   private void startDrawingQuads() {
      this.tessellator.startDrawingQuads();
   }

   private void draw() {
      this.tessellator.draw();
   }

   private void addVertexWithUV(double x, double y, double z, double u, double v) {
      this.tessellator.addVertexWithUV(x, y, z, u, v);
   }

   private void mapCalc(boolean strip) {
      if(this.theWorld != null && this.thePlayer != null) {
         Thread thread = Thread.currentThread();
         double d;
         if(this.stripCounter.count() == 0) {
            this.posX = MathHelper.floor_double(this.playerPosX);
            this.posY = MathHelper.floor_double(this.playerPosY);
            this.posYd = this.playerPosY;
            this.posZ = MathHelper.floor_double(this.playerPosZ);
            this.chunkCoordX = this.thePlayer.chunkCoordX;
            this.chunkCoordZ = this.thePlayer.chunkCoordZ;
            this.skylightSubtracted = this.calculateSkylightSubtracted(this.theWorld.getWorldTime(), 0.0F);
            if(this.lightType == 0) {
               switch(this.lightmap) {
               case 0:
                  this.updateLightmap(this.theWorld.getWorldTime(), 0.0F);
                  break;
               case 1:
                  this.updateLightmap(6000L, 0.0F);
                  break;
               case 2:
                  this.updateLightmap(18000L, 0.0F);
                  break;
               case 3:
                  this.updateLightmap(6000L, 0.0F);
               }
            }

            d = Math.toRadians(this.roundmap && !this.fullmap?(double)(45.0F - this.playerRotationYaw):(double)(this.notchDirection?225:-45));
            this.sin = (float)Math.sin(d);
            this.cos = (float)Math.cos(d);
            this.grassColor = ColorizerGrass.getGrassColor(0.5D, 1.0D);
            this.foliageColor = ColorizerFoliage.getFoliageColor(0.5D, 1.0D);
            this.foliageColorPine = ColorizerFoliage.getFoliageColorPine();
            this.foliageColorBirch = ColorizerFoliage.getFoliageColorBirch();
         }

         if(this.fullmap) {
            this.stripCountMax1 = 289;
            this.stripCountMax2 = 289;
         } else if(this.currentZoom < this.targetZoom) {
            d = Math.ceil(4.0D / this.currentZoom) * 2.0D + 1.0D;
            this.stripCountMax1 = (int)(d * d);
            d = Math.ceil(4.0D / this.targetZoom) * 2.0D + 1.0D;
            this.stripCountMax2 = (int)(d * d);
         } else {
            d = Math.ceil(4.0D / this.targetZoom) * 2.0D + 1.0D;
            this.stripCountMax1 = this.stripCountMax2 = (int)(d * d);
         }

         if(this.renderType == 1) {
            if(!this.forceUpdate && strip) {
               this.biomeCalcStrip(thread);
            } else {
               this.biomeCalc(thread);
            }
         } else if(this.renderType == 2) {
            if(!this.forceUpdate && strip) {
               this.caveCalcStrip();
            } else {
               this.caveCalc();
            }
         } else if(!this.forceUpdate && strip) {
            this.surfaceCalcStrip(thread);
         } else {
            this.surfaceCalc(thread);
         }

         if(this.isCompleteImage) {
            this.forceUpdate = false;
            this.isCompleteImage = false;
            this.stripCounter.reset();
            this.lastX = this.posX;
            this.lastY = this.posY;
            this.lastZ = this.posZ;
         }
      }
   }

   private void surfaceCalc(Thread thread) {
      int limit = Math.max(this.stripCountMax1, this.stripCountMax2);

      while(this.stripCounter.count() < limit) {
         Point point = this.stripCounter.next();
         ChunkData chunkData = ChunkData.getChunkData(this.chunkCoordX + point.x, this.chunkCoordZ + point.y);
         this.surfaceCalc(chunkData, thread);
      }

      this.isUpdateImage = this.stripCounter.count() >= this.stripCountMax1;
      this.isCompleteImage = this.isUpdateImage && this.stripCounter.count() >= this.stripCountMax2;
   }

   private void surfaceCalcStrip(Thread thread) {
      int limit = Math.max(this.stripCountMax1, this.stripCountMax2);
      int limit2 = updateFrequencys[this.updateFrequencySetting];

      for(int i = 0; i < limit2 && this.stripCounter.count() < limit; ++i) {
         Point point = this.stripCounter.next();
         ChunkData chunkData = ChunkData.getChunkData(this.chunkCoordX + point.x, this.chunkCoordZ + point.y);
         this.surfaceCalc(chunkData, thread);
      }

      this.isUpdateImage = this.stripCounter.count() >= this.stripCountMax1;
      this.isCompleteImage = this.isUpdateImage && this.stripCounter.count() >= this.stripCountMax2;
   }

   private void surfaceCalc(ChunkData chunkData, Thread thread) {
      if(chunkData != null) {
         Chunk chunk = chunkData.getChunk();
         if(chunk != null && !(chunk instanceof EmptyChunk)) {
            int offsetX = 128 + chunk.xPosition * 16 - this.posX;
            int offsetZ = 128 + chunk.zPosition * 16 - this.posZ;
            boolean slime = this.showSlimeChunk && this.currentDimension == 0 && chunkData.slime;
            PixelColor pixel = new PixelColor(this.transparency);
            ChunkData chunkMinusX = null;
            ChunkData chunkPlusX = null;
            ChunkData chunkMinusZ = null;
            ChunkData chunkPlusZ = null;
            ChunkData cmx = null;
            ChunkData cpx = null;
            ChunkData cmz = null;
            ChunkData cpz = null;
            if(this.undulate) {
               chunkMinusZ = ChunkData.getChunkData(chunk.xPosition, chunk.zPosition - 1);
               chunkPlusZ = ChunkData.getChunkData(chunk.xPosition, chunk.zPosition + 1);
               chunkMinusX = ChunkData.getChunkData(chunk.xPosition - 1, chunk.zPosition);
               chunkPlusX = ChunkData.getChunkData(chunk.xPosition + 1, chunk.zPosition);
            }

            for(int z = 0; z < 16; ++z) {
               int zCoord = offsetZ + z;
               if(zCoord >= 0) {
                  if(zCoord >= 256) {
                     break;
                  }

                  if(this.undulate) {
                     cmz = z == 0?chunkMinusZ:chunkData;
                     cpz = z == 15?chunkPlusZ:chunkData;
                  }

                  for(int x = 0; x < 16; ++x) {
                     int xCoord = offsetX + x;
                     if(xCoord >= 0) {
                        if(xCoord >= 256) {
                           break;
                        }

                        pixel.clear();
                        int height = !this.omitHeightCalc && !this.heightmap && !this.undulate?this.worldHeight:Math.min(this.worldHeight, chunk.getHeightValue(x, z));
                        int y = this.omitHeightCalc?Math.min(this.worldHeight, height + 1):this.worldHeight;
                        chunkData.setHeightValue(x, z, (float)height);
                        if(y < 0) {
                           if(this.transparency) {
                              this.texture.setRGB(xCoord, zCoord, 16711935);
                           } else {
                              this.texture.setRGB(xCoord, zCoord, -16777216);
                           }
                        } else {
                           this.surfaceCalc(chunkData, x, y, z, pixel, BlockType.AIR, thread);
                           float factor;
                           float blue;
                           if(this.heightmap) {
                              factor = this.undulate?0.15F:0.6F;
                              double var29 = (double)height - this.posYd;
                              blue = (float)Math.log10(Math.abs(var29) * 0.125D + 1.0D) * factor;
                              if(var29 >= 0.0D) {
                                 pixel.red += blue * (1.0F - pixel.red);
                                 pixel.green += blue * (1.0F - pixel.green);
                                 pixel.blue += blue * (1.0F - pixel.blue);
                              } else {
                                 blue = Math.abs(blue);
                                 pixel.red -= blue * pixel.red;
                                 pixel.green -= blue * pixel.green;
                                 pixel.blue -= blue * pixel.blue;
                              }
                           }

                           factor = 1.0F;
                           if(this.undulate) {
                              cmx = x == 0?chunkMinusX:chunkData;
                              cpx = x == 15?chunkPlusX:chunkData;
                              float var27 = cmx == null?0.0F:cmx.getHeightValue(x - 1 & 15, z);
                              float var30 = cpx == null?0.0F:cpx.getHeightValue(x + 1 & 15, z);
                              blue = cmz == null?0.0F:cmz.getHeightValue(x, z - 1 & 15);
                              float var28 = cpz == null?0.0F:cpz.getHeightValue(x, z + 1 & 15);
                              factor += Math.max(-4.0F, Math.min(3.0F, (var27 - var30) * this.sin + (blue - var28) * this.cos)) * 0.14142136F * 0.8F;
                           }

                           if(slime) {
                              pixel.red = (float)((double)pixel.red * 1.2D);
                              pixel.green = (float)((double)pixel.green * 0.5D);
                              pixel.blue = (float)((double)pixel.blue * 0.5D);
                           }

                           if(this.showChunkGrid && (x == 0 || z == 0)) {
                              pixel.red = (float)((double)pixel.red * 0.7D);
                              pixel.green = (float)((double)pixel.green * 0.7D);
                              pixel.blue = (float)((double)pixel.blue * 0.7D);
                           }

                           byte var281 = ftob(pixel.red * factor);
                           byte var291 = ftob(pixel.green * factor);
                           byte var301 = ftob(pixel.blue * factor);
                           if(this.transparency) {
                              this.texture.setRGBA(xCoord, zCoord, var281, var291, var301, ftob(pixel.alpha));
                           } else {
                              this.texture.setRGB(xCoord, zCoord, var281, var291, var301);
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private void biomeCalc(Thread thread) {
      int limit = Math.max(this.stripCountMax1, this.stripCountMax2);

      while(this.stripCounter.count() < limit) {
         Point point = this.stripCounter.next();
         ChunkData chunkData = ChunkData.getChunkData(this.chunkCoordX + point.x, this.chunkCoordZ + point.y);
         this.biomeCalc(chunkData, thread);
      }

      this.isUpdateImage = this.stripCounter.count() >= this.stripCountMax1;
      this.isCompleteImage = this.isUpdateImage && this.stripCounter.count() >= this.stripCountMax2;
   }

   private void biomeCalcStrip(Thread thread) {
      int limit = Math.max(this.stripCountMax1, this.stripCountMax2);
      int limit2 = updateFrequencys[this.updateFrequencySetting];

      for(int i = 0; i < limit2 && this.stripCounter.count() < limit; ++i) {
         Point point = this.stripCounter.next();
         ChunkData chunkData = ChunkData.getChunkData(this.chunkCoordX + point.x, this.chunkCoordZ + point.y);
         this.biomeCalc(chunkData, thread);
      }

      this.isUpdateImage = this.stripCounter.count() >= this.stripCountMax1;
      this.isCompleteImage = this.isUpdateImage && this.stripCounter.count() >= this.stripCountMax2;
   }

   private void biomeCalc(ChunkData chunkData, Thread thread) {
      if(chunkData != null) {
         int offsetX = 128 + chunkData.xPosition * 16 - this.posX;
         int offsetZ = 128 + chunkData.zPosition * 16 - this.posZ;

         for(int z = 0; z < 16; ++z) {
            int zCoord = z + offsetZ;
            if(zCoord >= 0) {
               if(zCoord >= 256) {
                  break;
               }

               for(int x = 0; x < 16; ++x) {
                  int xCoord = x + offsetX;
                  if(xCoord >= 0) {
                     if(xCoord >= 256) {
                        break;
                     }

                     BiomeGenBase bgb = chunkData.biomes[z << 4 | x];
                     int color = bgb != null?bgb.color:BiomeGenBase.plains.color;
                     byte r = (byte)(color >> 16);
                     byte g = (byte)(color >> 8);
                     byte b = (byte)(color >> 0);
                     this.texture.setRGB(xCoord, zCoord, r, g, b);
                  }
               }
            }
         }
      }
   }

   private void temperatureCalc(Thread thread) {
      int limit = Math.max(this.stripCountMax1, this.stripCountMax2);

      while(this.stripCounter.count() < limit) {
         Point point = this.stripCounter.next();
         ChunkData chunkData = ChunkData.getChunkData(this.chunkCoordX + point.x, this.chunkCoordZ + point.y);
         this.temperatureCalc(chunkData, thread);
      }

      this.isUpdateImage = this.stripCounter.count() >= this.stripCountMax1;
      this.isCompleteImage = this.isUpdateImage && this.stripCounter.count() >= this.stripCountMax2;
   }

   private void temperatureCalcStrip(Thread thread) {
      int limit = Math.max(this.stripCountMax1, this.stripCountMax2);
      int limit2 = updateFrequencys[this.updateFrequencySetting];

      for(int i = 0; i < limit2 && this.stripCounter.count() < limit; ++i) {
         Point point = this.stripCounter.next();
         ChunkData chunkData = ChunkData.getChunkData(this.chunkCoordX + point.x, this.chunkCoordZ + point.y);
         this.temperatureCalc(chunkData, thread);
      }

      this.isUpdateImage = this.stripCounter.count() >= this.stripCountMax1;
      this.isCompleteImage = this.isUpdateImage && this.stripCounter.count() >= this.stripCountMax2;
   }

   private void temperatureCalc(ChunkData chunkData, Thread thread) {
      if(chunkData != null) {
         int offsetX = 128 + chunkData.xPosition * 16 - this.posX;
         int offsetZ = 128 + chunkData.zPosition * 16 - this.posZ;

         for(int z = 0; z < 16; ++z) {
            int zCoord = z + offsetZ;
            if(zCoord >= 0) {
               if(zCoord >= 256) {
                  break;
               }

               for(int x = 0; x < 16; ++x) {
                  int xCoord = x + offsetX;
                  if(xCoord >= 0) {
                     if(xCoord >= 256) {
                        break;
                     }

                     float temperature = chunkData.biomes[z << 4 | x].temperature;
                     int rgb = (int)(temperature * 255.0F);
                     this.texture.setRGB(xCoord, zCoord, this.temperatureColor[rgb]);
                  }
               }
            }
         }
      }
   }

   private void humidityCalc(Thread thread) {
      int limit = Math.max(this.stripCountMax1, this.stripCountMax2);

      while(this.stripCounter.count() < limit) {
         Point point = this.stripCounter.next();
         ChunkData chunkData = ChunkData.getChunkData(this.chunkCoordX + point.x, this.chunkCoordZ + point.y);
         this.humidityCalc(chunkData, thread);
      }

      this.isUpdateImage = this.stripCounter.count() >= this.stripCountMax1;
      this.isCompleteImage = this.isUpdateImage && this.stripCounter.count() >= this.stripCountMax2;
   }

   private void humidityCalcStrip(Thread thread) {
      int limit = Math.max(this.stripCountMax1, this.stripCountMax2);
      int limit2 = updateFrequencys[this.updateFrequencySetting];

      for(int i = 0; i < limit2 && this.stripCounter.count() < limit; ++i) {
         Point point = this.stripCounter.next();
         ChunkData chunkData = ChunkData.getChunkData(this.chunkCoordX + point.x, this.chunkCoordZ + point.y);
         this.humidityCalc(chunkData, thread);
      }

      this.isUpdateImage = this.stripCounter.count() >= this.stripCountMax1;
      this.isCompleteImage = this.isUpdateImage && this.stripCounter.count() >= this.stripCountMax2;
   }

   private void humidityCalc(ChunkData chunkData, Thread thread) {
      if(chunkData != null) {
         int offsetX = 128 + chunkData.xPosition * 16 - this.posX;
         int offsetZ = 128 + chunkData.zPosition * 16 - this.posZ;

         for(int z = 0; z < 16; ++z) {
            int zCoord = z + offsetZ;
            if(zCoord >= 0) {
               if(zCoord >= 256) {
                  break;
               }

               for(int x = 0; x < 16; ++x) {
                  int xCoord = x + offsetX;
                  if(xCoord >= 0) {
                     if(xCoord >= 256) {
                        break;
                     }

                     float humidity = chunkData.biomes[z << 4 | x].rainfall;
                     int rgb = (int)(humidity * 255.0F);
                     this.texture.setRGB(xCoord, zCoord, this.humidityColor[rgb]);
                  }
               }
            }
         }
      }
   }

   private static final byte ftob(float f) {
      return (byte)Math.max(0, Math.min(255, (int)(f * 255.0F)));
   }

   private void surfaceCalc(ChunkData chunkData, int x, int y, int z, PixelColor pixel, BlockType tintType, Thread thread) {
      Chunk chunk = chunkData.getChunk();
      Block block = chunk.func_150810_a(x, y, z);
      int blockID = Block.getIdFromBlock(block);
      if(blockID != 0 && (!this.hideSnow || blockID != 78)) {
         int metadata = chunk.getBlockMetadata(x, y, z);
         BlockColor color = BlockDataPack.defaultBlockColor[blockID << 4 | metadata];
         if(color == null) {
            if(y > 0) {
               this.surfaceCalc(chunkData, x, y - 1, z, pixel, BlockType.AIR, thread);
            }
         } else {
            if(this.transparency) {
               if(color.alpha < 1.0F && y > 0) {
                  this.surfaceCalc(chunkData, x, y - 1, z, pixel, color.type, thread);
                  if(color.alpha == 0.0F) {
                     return;
                  }
               }
            } else if(color.alpha == 0.0F && y > 0) {
               this.surfaceCalc(chunkData, x, y - 1, z, pixel, color.type, thread);
               return;
            }

            int argb;
            float g;
            float r;
            float b;
            int lightValue1;
            if(this.lightType == 0) {
               boolean lightBrightness1 = true;
               switch(this.lightmap) {
               case 3:
                  lightValue1 = 15;
                  break;
               default:
                  this.lightmap = 0;
               case 0:
               case 1:
               case 2:
                  lightValue1 = y < this.worldHeight?chunk.getSavedLightValue(EnumSkyBlock.Sky, x, y + 1, z):15;
               }

               int lightBrightness = Math.max(block.getLightValue(), chunk.getSavedLightValue(EnumSkyBlock.Block, x, Math.min(this.worldHeight, y + 1), z));
               argb = lightValue1 << 4 | lightBrightness;
               r = this.lightmapRed[argb];
               g = this.lightmapGreen[argb];
               b = this.lightmapBlue[argb];
               if(color.type.water && tintType.water) {
                  return;
               }

               int argb1;
               if(this.environmentColor) {
                  switch(ReiMinimap.NamelessClass1095983968.$SwitchMap$reifnsk$minimap$BlockType[color.type.ordinal()]) {
                  case 1:
                     argb1 = chunkData.smoothGrassColors[z << 4 | x];
                     pixel.composite(color.alpha, argb1, r * color.red, g * color.green, b * color.blue);
                     return;
                  case 2:
                     argb1 = chunkData.grassColors[z << 4 | x];
                     pixel.composite(color.alpha, argb1, r * color.red, g * color.green, b * color.blue);
                     return;
                  case 3:
                     argb1 = chunkData.smoothFoliageColors[z << 4 | x];
                     pixel.composite(color.alpha, argb1, r * color.red, g * color.green, b * color.blue);
                     return;
                  case 4:
                     argb1 = chunkData.smoothWaterColors[z << 4 | x];
                     pixel.composite(color.alpha, argb1, r * color.red, g * color.green, b * color.blue);
                     return;
                  }
               } else {
                  switch(ReiMinimap.NamelessClass1095983968.$SwitchMap$reifnsk$minimap$BlockType[color.type.ordinal()]) {
                  case 1:
                     pixel.composite(color.alpha, this.grassColor, r * color.red, g * color.green, b * color.blue);
                     return;
                  case 2:
                     pixel.composite(color.alpha, this.grassColor, r * color.red * 0.9F, g * color.green * 0.9F, b * color.blue * 0.9F);
                     return;
                  case 3:
                     pixel.composite(color.alpha, this.foliageColor, r * color.red, g * color.green, b * color.blue);
                     return;
                  }
               }

               if(color.type == BlockType.FOLIAGE_PINE) {
                  pixel.composite(color.alpha, this.foliageColorPine, r * color.red, g * color.green, b * color.blue);
                  return;
               }

               if(color.type == BlockType.FOLIAGE_BIRCH) {
                  pixel.composite(color.alpha, this.foliageColorBirch, r * color.red, g * color.green, b * color.blue);
                  return;
               }

               if(color.type == BlockType.GLASS && tintType == BlockType.GLASS) {
                  return;
               }

               argb1 = block.getRenderColor(metadata);
               if(argb1 == 16777215) {
                  pixel.composite(color.alpha, color.red * r, color.green * g, color.blue * b);
               } else {
                  pixel.composite(color.alpha, argb1, color.red * r, color.green * g, color.blue * b);
               }
            } else {
               switch(this.lightmap) {
               case 1:
                  lightValue1 = y < this.worldHeight?chunk.getBlockLightValue(x, y + 1, z, 0):15;
                  break;
               case 2:
                  lightValue1 = y < this.worldHeight?chunk.getBlockLightValue(x, y + 1, z, 11):4;
                  break;
               case 3:
                  lightValue1 = 15;
                  break;
               default:
                  this.lightmap = 0;
               case 0:
                  lightValue1 = y < this.worldHeight?chunk.getBlockLightValue(x, y + 1, z, this.skylightSubtracted):15 - this.skylightSubtracted;
               }

               float lightBrightness11 = this.lightBrightnessTable[lightValue1];
               if(color.type.water && tintType.water) {
                  return;
               }

               if(this.environmentColor) {
                  switch(ReiMinimap.NamelessClass1095983968.$SwitchMap$reifnsk$minimap$BlockType[color.type.ordinal()]) {
                  case 1:
                     argb = chunkData.smoothGrassColors[z << 4 | x];
                     pixel.composite(color.alpha, argb, lightBrightness11 * 0.6F);
                     return;
                  case 2:
                     argb = chunkData.smoothGrassColors[z << 4 | x];
                     pixel.composite(color.alpha, argb, lightBrightness11 * 0.5F);
                     return;
                  case 3:
                     argb = chunkData.smoothFoliageColors[z << 4 | x];
                     pixel.composite(color.alpha, argb, lightBrightness11 * 0.5F);
                     return;
                  case 4:
                     argb = chunkData.smoothWaterColors[z << 4 | x];
                     r = (float)(argb >> 16 & 255) * 0.003921569F;
                     g = (float)(argb >> 8 & 255) * 0.003921569F;
                     b = (float)(argb >> 0 & 255) * 0.003921569F;
                     pixel.composite(color.alpha, color.red * r, color.green * g, color.blue * b, lightBrightness11);
                     return;
                  }
               } else {
                  switch(ReiMinimap.NamelessClass1095983968.$SwitchMap$reifnsk$minimap$BlockType[color.type.ordinal()]) {
                  case 1:
                     pixel.composite(color.alpha, this.grassColor, lightBrightness11 * color.red, lightBrightness11 * color.green, lightBrightness11 * color.blue);
                     return;
                  case 2:
                     pixel.composite(color.alpha, this.grassColor, lightBrightness11 * color.red * 0.9F, lightBrightness11 * color.green * 0.9F, lightBrightness11 * color.blue * 0.9F);
                     return;
                  case 3:
                     pixel.composite(color.alpha, this.foliageColor, lightBrightness11 * color.red, lightBrightness11 * color.green, lightBrightness11 * color.blue);
                     return;
                  }
               }

               if(color.type == BlockType.FOLIAGE_PINE) {
                  pixel.composite(color.alpha, this.foliageColorPine, lightBrightness11 * color.red, lightBrightness11 * color.green, lightBrightness11 * color.blue);
                  return;
               }

               if(color.type == BlockType.FOLIAGE_BIRCH) {
                  pixel.composite(color.alpha, this.foliageColorBirch, lightBrightness11 * color.red, lightBrightness11 * color.green, lightBrightness11 * color.blue);
                  return;
               }

               if(color.type == BlockType.GLASS && tintType == BlockType.GLASS) {
                  return;
               }

               argb = block.getRenderColor(metadata);
               if(argb == 16777215) {
                  pixel.composite(color.alpha, color.red, color.green, color.blue, lightBrightness11);
               } else {
                  pixel.composite(color.alpha, argb, color.red * lightBrightness11, color.green * lightBrightness11, color.blue * lightBrightness11);
               }
            }
         }
      } else if(y > 0) {
         this.surfaceCalc(chunkData, x, y - 1, z, pixel, BlockType.AIR, thread);
      }
   }

   private void caveCalc() {
      int limit = Math.max(this.stripCountMax1, this.stripCountMax2);

      while(this.stripCounter.count() < limit) {
         Point point = this.stripCounter.next();
         ChunkData chunkData = ChunkData.getChunkData(this.chunkCoordX + point.x, this.chunkCoordZ + point.y);
         this.caveCalc(chunkData);
      }

      this.isUpdateImage = this.stripCounter.count() >= this.stripCountMax1;
      this.isCompleteImage = this.isUpdateImage && this.stripCounter.count() >= this.stripCountMax2;
   }

   private void caveCalcStrip() {
      int limit = Math.max(this.stripCountMax1, this.stripCountMax2);
      int limit2 = updateFrequencys[this.updateFrequencySetting];

      for(int i = 0; i < limit2 && this.stripCounter.count() < limit; ++i) {
         Point point = this.stripCounter.next();
         ChunkData chunkData = ChunkData.getChunkData(this.chunkCoordX + point.x, this.chunkCoordZ + point.y);
         this.caveCalc(chunkData);
      }

      this.isUpdateImage = this.stripCounter.count() >= this.stripCountMax1;
      this.isCompleteImage = this.isUpdateImage && this.stripCounter.count() >= this.stripCountMax2;
   }

   private void caveCalc(ChunkData chunkData) {
      if(chunkData != null) {
         Chunk chunk = chunkData.getChunk();
         if(chunk != null && !(chunk instanceof EmptyChunk)) {
            int offsetX = 128 + chunk.xPosition * 16 - this.posX;
            int offsetZ = 128 + chunk.zPosition * 16 - this.posZ;

            for(int z = 0; z < 16; ++z) {
               int zCoord = offsetZ + z;
               if(zCoord >= 0) {
                  if(zCoord >= 256) {
                     break;
                  }

                  for(int x = 0; x < 16; ++x) {
                     int xCoord = offsetX + x;
                     if(xCoord >= 0) {
                        if(xCoord >= 256) {
                           break;
                        }

                        float f;
                        f = 0.0F;
                        int y;
                        int _y;
                        label94:
                        switch(this.currentDimension) {
                        case -1:
                           y = 0;

                           while(true) {
                              if(y >= temp.length) {
                                 break label94;
                              }

                              _y = this.posY - y;
                              if(_y >= 0 && _y <= this.worldHeight && chunk.func_150808_b(x, _y, z) == 0 && chunk.getBlockLightValue(x, _y, z, 12) != 0) {
                                 f += temp[y];
                              }

                              _y = this.posY + y + 1;
                              if(_y >= 0 && _y <= this.worldHeight && chunk.func_150808_b(x, _y, z) == 0 && chunk.getBlockLightValue(x, _y, z, 12) != 0) {
                                 f += temp[y];
                              }

                              ++y;
                           }
                        case 0:
                           for(y = 0; y < temp.length; ++y) {
                              _y = this.posY - y;
                              if(_y > this.worldHeight || _y >= 0 && chunk.func_150808_b(x, _y, z) == 0 && chunk.getBlockLightValue(x, _y, z, 12) != 0) {
                                 f += temp[y];
                              }

                              _y = this.posY + y + 1;
                              if(_y > this.worldHeight || _y >= 0 && chunk.func_150808_b(x, _y, z) == 0 && chunk.getBlockLightValue(x, _y, z, 12) != 0) {
                                 f += temp[y];
                              }
                           }
                        case 1:
                        }

                        f = 0.8F - f;
                        this.texture.setRGB(xCoord, zCoord, ftob(0.0F), ftob(f), ftob(0.0F));
                     }
                  }
               }
            }
         }
      }
   }

   private void renderRoundMap() {
      int mapscale = 1;
      if(this.mapScale == 0) {
         mapscale = this.scaledResolution.getScaleFactor();
      } else if(this.mapScale == 1) {
         while(this.scWidth >= (mapscale + 1) * 320 && this.scHeight >= (mapscale + 1) * 240) {
            ++mapscale;
         }
      } else {
         mapscale = this.mapScale - 1;
      }

      int fscale = this.fontScale - 1;
      if(this.fontScale == 0) {
         fscale = this.scaledResolution.getScaleFactor() + 1 >> 1;
      } else if(this.fontScale == 1) {
         fscale = mapscale + 1 >> 1;
      }

      int centerX = (this.mapPosition & 2) == 0?37 * mapscale:this.scWidth - 37 * mapscale;
      int centerY = (this.mapPosition & 1) == 0?37 * mapscale:this.scHeight - 37 * mapscale;
      if((this.mapPosition & 1) == 1) {
         centerY -= ((this.showMenuKey | this.showCoordinate?2:0) + (this.showMenuKey?9:0) + (this.showCoordinate?18:0)) * fscale;
      }

      GL11.glTranslated((double)centerX, (double)centerY, 0.0D);
      GL11.glScalef((float)mapscale, (float)mapscale, 1.0F);
      GL11.glDisable(GL11.GL_BLEND);
      GL11.glColorMask(false, false, false, false);
      GL11.glEnable(GL11.GL_DEPTH_TEST);
      if(this.useStencil) {
         GL11.glAlphaFunc(GL11.GL_LEQUAL, 0.1F);
         GL11.glClearStencil(0);
         GL11.glClear(1024);
         GL11.glEnable(GL11.GL_STENCIL_TEST);
         GL11.glStencilFunc(GL11.GL_ALWAYS, 1, -1);
         GL11.glStencilOp(GL11.GL_KEEP, GL11.GL_REPLACE, GL11.GL_REPLACE);
         GL11.glDepthMask(false);
      } else {
         GL11.glAlphaFunc(GL11.GL_GREATER, 0.0F);
         GL11.glDepthMask(true);
      }

      GL11.glPushMatrix();
      GL11.glRotatef(90.0F - this.playerRotationYaw, 0.0F, 0.0F, 1.0F);
      GLTexture.ROUND_MAP_MASK.bind();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.drawCenteringRectangle(0.0D, 0.0D, 1.01D, 64.0D, 64.0D);
      if(this.useStencil) {
         GL11.glStencilOp(GL11.GL_KEEP, GL11.GL_KEEP, GL11.GL_KEEP);
         GL11.glStencilFunc(GL11.GL_EQUAL, 1, -1);
      }

      GL11.glEnable(GL11.GL_BLEND);
      GL11.glAlphaFunc(GL11.GL_GREATER, 0.0F);
      GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
      GL11.glColorMask(true, true, true, true);
      double a = 0.25D / this.currentZoom;
      double slideX = (this.playerPosX - (double)this.lastX) * 0.00390625D;
      double slideY = (this.playerPosZ - (double)this.lastZ) * 0.00390625D;
      GL11.glColor4f(1.0F, 1.0F, 1.0F, this.mapOpacity);
      this.texture.bind();
      this.startDrawingQuads();
      this.addVertexWithUV(-32.0D, 32.0D, 1.0D, 0.5D + a + slideX, 0.5D + a + slideY);
      this.addVertexWithUV(32.0D, 32.0D, 1.0D, 0.5D + a + slideX, 0.5D - a + slideY);
      this.addVertexWithUV(32.0D, -32.0D, 1.0D, 0.5D - a + slideX, 0.5D - a + slideY);
      this.addVertexWithUV(-32.0D, -32.0D, 1.0D, 0.5D - a + slideX, 0.5D + a + slideY);
      this.draw();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glPopMatrix();
      double s;
      int str;
      double tx;
      float tx1;
      Iterator var51;
      double var52;
      double var59;
      float var60;
      if(this.visibleEntitiesRadar) {
         s = this.useStencil?34.0D:29.0D;
         (this.configEntityDirection?GLTexture.ENTITY2:GLTexture.ENTITY).bind();

         for(int var54 = this.visibleEntities.length - 1; var54 >= 0; --var54) {
            int pt = this.visibleEntityColor[var54];
            List var57 = this.visibleEntities[var54];
            Iterator var56 = var57.iterator();

            while(var56.hasNext()) {
               EntityLivingBase var58 = (EntityLivingBase)var56.next();
               str = var58.isEntityAlive()?pt:(pt & 16579836) >> 2 | -16777216;
               double var68 = var58.prevPosX + (var58.posX - var58.prevPosX) * (double)this.renderPartialTicks;
               double var62 = var58.prevPosZ + (var58.posZ - var58.prevPosZ) * (double)this.renderPartialTicks;
               double var69 = this.playerPosX - var68;
               double var70 = this.playerPosZ - var62;
               float locate = (float)Math.toDegrees(Math.atan2(var69, var70));
               double distance = Math.sqrt(var69 * var69 + var70 * var70) * this.currentZoom * 0.5D;

               try {
                  GL11.glPushMatrix();
                  if(distance < s) {
                     float r = (float)(str >> 16 & 255) * 0.003921569F;
                     float g = (float)(str >> 8 & 255) * 0.003921569F;
                     float b = (float)(str & 255) * 0.003921569F;
                     float alpha1 = (float)Math.max(0.20000000298023224D, 1.0D - Math.abs(this.playerPosY - var58.posY) * 0.04D);
                     float mul = (float)Math.min(1.0D, Math.max(0.5D, 1.0D - (this.thePlayer.boundingBox.minY - var58.boundingBox.minY) * 0.1D));
                     r *= mul;
                     g *= mul;
                     b *= mul;
                     GL11.glColor4f(r, g, b, alpha1);
                     GL11.glRotatef(-locate - this.playerRotationYaw + 180.0F, 0.0F, 0.0F, 1.0F);
                     GL11.glTranslated(0.0D, -distance, 0.0D);
                     GL11.glRotatef(-(-locate - this.playerRotationYaw + 180.0F), 0.0F, 0.0F, 1.0F);
                     if(this.configEntityDirection) {
                        float entityRotationYaw = var58.prevRotationYaw + (var58.rotationYaw - var58.prevRotationYaw) * this.renderPartialTicks;
                        GL11.glRotatef(entityRotationYaw - this.playerRotationYaw, 0.0F, 0.0F, 1.0F);
                     }

                     this.drawCenteringRectangle(0.0D, 0.0D, 1.0D, 8.0D, 8.0D);
                  }
               } finally {
                  GL11.glPopMatrix();
               }
            }
         }

         if(this.configEntityLightning) {
            var51 = this.weatherEffects.iterator();

            while(var51.hasNext()) {
               Entity var601 = (Entity)var51.next();
               if(var601 instanceof EntityLightningBolt) {
                  var52 = this.playerPosX - var601.posX;
                  var59 = this.playerPosZ - var601.posZ;
                  var60 = (float)Math.toDegrees(Math.atan2(var52, var59));
                  tx = Math.sqrt(var52 * var52 + var59 * var59) * this.currentZoom * 0.5D;

                  try {
                     GL11.glPushMatrix();
                     if(tx < s) {
                        tx1 = (float)Math.max(0.20000000298023224D, 1.0D - Math.abs(this.playerPosY - var601.posY) * 0.04D);
                        GL11.glColor4f(1.0F, 1.0F, 1.0F, tx1);
                        GL11.glRotatef(-var60 - this.playerRotationYaw + 180.0F, 0.0F, 0.0F, 1.0F);
                        GL11.glTranslated(0.0D, -tx, 0.0D);
                        GL11.glRotatef(-(-var60 - this.playerRotationYaw + 180.0F), 0.0F, 0.0F, 1.0F);
                        GLTexture.LIGHTNING.bind();
                        this.drawCenteringRectangle(0.0D, 0.0D, 1.0D, 8.0D, 8.0D);
                     }
                  } finally {
                     GL11.glPopMatrix();
                  }
               }
            }
         }
      }

      if(this.useStencil) {
         GL11.glDisable(GL11.GL_STENCIL_TEST);
      }

      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glDisable(GL11.GL_DEPTH_TEST);
      GL11.glDepthMask(false);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, this.mapOpacity);
      GLTexture.ROUND_MAP.bind();
      this.drawCenteringRectangle(0.0D, 0.0D, 1.0D, 64.0D, 64.0D);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      if(this.visibleWaypoints) {
         s = this.getVisibleDimensionScale();
         var51 = this.wayPts.iterator();

         while(var51.hasNext()) {
            Waypoint var611 = (Waypoint)var51.next();
            if(var611.enable) {
               var52 = this.playerPosX - (double)var611.x * s - 0.5D;
               var59 = this.playerPosZ - (double)var611.z * s - 0.5D;
               var60 = (float)Math.toDegrees(Math.atan2(var52, var59));
               tx = Math.sqrt(var52 * var52 + var59 * var59) * this.currentZoom * 0.5D;

               try {
                  GL11.glPushMatrix();
                  if(tx < 31.0D) {
                     GL11.glColor4f(var611.red, var611.green, var611.blue, (float)Math.min(1.0D, Math.max(0.4D, (tx - 1.0D) * 0.5D)));
                     Waypoint.FILE[var611.type].bind();
                     GL11.glRotatef(-var60 - this.playerRotationYaw + 180.0F, 0.0F, 0.0F, 1.0F);
                     GL11.glTranslated(0.0D, -tx, 0.0D);
                     GL11.glRotatef(-(-var60 - this.playerRotationYaw + 180.0F), 0.0F, 0.0F, 1.0F);
                     this.drawCenteringRectangle(0.0D, 0.0D, 1.0D, 8.0D, 8.0D);
                  } else {
                     GL11.glColor3f(var611.red, var611.green, var611.blue);
                     Waypoint.MARKER[var611.type].bind();
                     GL11.glRotatef(-var60 - this.playerRotationYaw + 180.0F, 0.0F, 0.0F, 1.0F);
                     GL11.glTranslated(0.0D, -34.0D, 0.0D);
                     this.drawCenteringRectangle(0.0D, 0.0D, 1.0D, 8.0D, 8.0D);
                  }
               } finally {
                  GL11.glPopMatrix();
               }
            }
         }
      }

      GL11.glColor3f(1.0F, 1.0F, 1.0F);
      s = Math.sin(Math.toRadians((double)this.playerRotationYaw)) * 28.0D;
      double var621 = Math.cos(Math.toRadians((double)this.playerRotationYaw)) * 28.0D;
      if(this.notchDirection) {
         GLTexture.W.bind();
         this.drawCenteringRectangle(var621, -s, 1.0D, 8.0D, 8.0D);
         GLTexture.S.bind();
         this.drawCenteringRectangle(-s, -var621, 1.0D, 8.0D, 8.0D);
         GLTexture.E.bind();
         this.drawCenteringRectangle(-var621, s, 1.0D, 8.0D, 8.0D);
         GLTexture.N.bind();
         this.drawCenteringRectangle(s, var621, 1.0D, 8.0D, 8.0D);
      } else {
         GLTexture.N.bind();
         this.drawCenteringRectangle(var621, -s, 1.0D, 8.0D, 8.0D);
         GLTexture.W.bind();
         this.drawCenteringRectangle(-s, -var621, 1.0D, 8.0D, 8.0D);
         GLTexture.S.bind();
         this.drawCenteringRectangle(-var621, s, 1.0D, 8.0D, 8.0D);
         GLTexture.E.bind();
         this.drawCenteringRectangle(s, var621, 1.0D, 8.0D, 8.0D);
      }

      GL11.glScaled(1.0D / (double)mapscale, 1.0D / (double)mapscale, 1.0D);
      FontRenderer var63 = this.theMinecraft.fontRenderer;
      int var641 = (int)(this.zoomVisible * 255.0F);
      int var64;
      String var65;
      int var66;
      int var671;
      if(var641 > 0) {
         var65 = String.format("%2.2fx", new Object[]{Double.valueOf(this.currentZoom)});
         str = var63.getStringWidth(var65);
         if(var641 > 255) {
            var641 = 255;
         }

         var671 = 30 * mapscale - str * fscale;
         var64 = 30 * mapscale - 8 * fscale;
         GL11.glTranslatef((float)var671, (float)var64, 0.0F);
         GL11.glScalef((float)fscale, (float)fscale, 1.0F);
         var66 = var641 << 24 | 16777215;
         var63.drawStringWithShadow(var65, 0, 0, var66);
         GL11.glScaled(1.0D / (double)fscale, 1.0D / (double)fscale, 1.0D);
         GL11.glTranslatef((float)(-var671), (float)(-var64), 0.0F);
      }

      if(this.visibleWaypoints && this.currentDimension != this.waypointDimension) {
         GL11.glPushMatrix();
         var65 = this.getDimensionName(this.waypointDimension);
         float var681 = (float)var63.getStringWidth(var65) * 0.5F * (float)fscale;
         var60 = (float)(37 * mapscale) < var681?(float)(37 * mapscale) - var681:0.0F;
         if((this.mapPosition & 2) == 0) {
            var60 = -var60;
         }

         GL11.glTranslated((double)(var60 - var681), (double)(-30 * mapscale), 0.0D);
         GL11.glScaled((double)fscale, (double)fscale, 1.0D);
         var63.drawStringWithShadow(var65, 0, 0, 16777215);
         GL11.glPopMatrix();
      }

      var671 = 32 * mapscale;
      String var61;
      float var691;
      if(this.showCoordinate) {
         String var67;
         if(this.coordinateType == 0) {
            var64 = MathHelper.floor_double(this.playerPosX);
            var66 = MathHelper.floor_double(this.thePlayer.boundingBox.minY);
            int var701 = MathHelper.floor_double(this.playerPosZ);
            var61 = String.format("%+d, %+d", new Object[]{Integer.valueOf(var64), Integer.valueOf(var701)});
            var67 = Integer.toString(var66);
         } else {
            var61 = String.format("%+1.2f, %+1.2f", new Object[]{Double.valueOf(this.playerPosX), Double.valueOf(this.playerPosZ)});
            var67 = String.format("%1.2f (%d)", new Object[]{Double.valueOf(this.playerPosY), Integer.valueOf((int)this.thePlayer.boundingBox.minY)});
         }

         var691 = (float)var63.getStringWidth(var61) * 0.5F * (float)fscale;
         float var71 = (float)var63.getStringWidth(var67) * 0.5F * (float)fscale;
         tx1 = (float)(37 * mapscale) < var691?(float)(37 * mapscale) - var691:0.0F;
         if((this.mapPosition & 2) == 0) {
            tx1 = -tx1;
         }

         GL11.glTranslatef(tx1 - var691, (float)var671, 0.0F);
         GL11.glScalef((float)fscale, (float)fscale, 1.0F);
         var63.drawStringWithShadow(var61, 0, 2, 16777215);
         GL11.glScaled(1.0D / (double)fscale, 1.0D / (double)fscale, 1.0D);
         GL11.glTranslatef(var691 - var71, 0.0F, 0.0F);
         GL11.glScalef((float)fscale, (float)fscale, 1.0F);
         var63.drawStringWithShadow(var67, 0, 11, 16777215);
         GL11.glScaled(1.0D / (double)fscale, 1.0D / (double)fscale, 1.0D);
         GL11.glTranslatef(var71 - tx1, (float)(-var671), 0.0F);
         var671 += 18 * fscale;
      }

      if(this.showMenuKey) {
         var61 = String.format("Menu: %s key", new Object[]{KeyInput.MENU_KEY.getKeyName()});
         var60 = (float)this.theMinecraft.fontRenderer.getStringWidth(var61) * 0.5F * (float)fscale;
         var691 = (float)(32 * mapscale) - var60;
         if((this.mapPosition & 2) == 0 && (float)(32 * mapscale) < var60) {
            var691 = (float)(-32 * mapscale) + var60;
         }

         GL11.glTranslatef(var691 - var60, (float)var671, 0.0F);
         GL11.glScalef((float)fscale, (float)fscale, 1.0F);
         var63.drawStringWithShadow(var61, 0, 2, 16777215);
         GL11.glScaled(1.0D / (double)fscale, 1.0D / (double)fscale, 1.0D);
         GL11.glTranslatef(var60 - var691, (float)(-var671), 0.0F);
      }

      GL11.glDepthMask(true);
      GL11.glEnable(GL11.GL_DEPTH_TEST);
   }

   public void setOption(EnumOption option, EnumOptionValue value) {
      this.lock.lock();

      try {
         label222:
         switch(ReiMinimap.NamelessClass1095983968.$SwitchMap$reifnsk$minimap$EnumOption[option.ordinal()]) {
         case 1:
            this.enable = EnumOptionValue.bool(value);
            break;
         case 2:
            this.showMenuKey = EnumOptionValue.bool(value);
            break;
         case 3:
            this.useStencil = value == EnumOptionValue.STENCIL;
            break;
         case 4:
            this.notchDirection = true;
            break;
         case 5:
            this.roundmap = value == EnumOptionValue.ROUND;
            break;
         case 6:
            this.textureView = Math.max(0, option.getValue(value));
            switch(this.textureView) {
            case 0:
               GLTexture.setPack("/reifnsk/minimap/");
               break label222;
            case 1:
               GLTexture.setPack("/reifnsk/minimap/zantextures/");
            default:
               break label222;
            }
         case 7:
            this.mapPosition = Math.max(0, option.getValue(value));
            break;
         case 8:
            this.mapScale = option.getValue(value);
            break;
         case 9:
            switch(ReiMinimap.NamelessClass1095983968.$SwitchMap$reifnsk$minimap$EnumOptionValue[value.ordinal()]) {
            case 1:
            default:
               this.mapOpacity = 1.0F;
               break label222;
            case 2:
               this.mapOpacity = 0.75F;
               break label222;
            case 3:
               this.mapOpacity = 0.5F;
               break label222;
            case 4:
               this.mapOpacity = 0.25F;
               break label222;
            }
         case 10:
            this.largeMapScale = option.getValue(value);
            break;
         case 11:
            switch(ReiMinimap.NamelessClass1095983968.$SwitchMap$reifnsk$minimap$EnumOptionValue[value.ordinal()]) {
            case 1:
            default:
               this.largeMapOpacity = 1.0F;
               break label222;
            case 2:
               this.largeMapOpacity = 0.75F;
               break label222;
            case 3:
               this.largeMapOpacity = 0.5F;
               break label222;
            case 4:
               this.largeMapOpacity = 0.25F;
               break label222;
            }
         case 12:
            this.largeMapLabel = EnumOptionValue.bool(value);
            break;
         case 13:
            this.filtering = EnumOptionValue.bool(value);
            break;
         case 14:
            this.coordinateType = Math.max(0, option.getValue(value));
            this.showCoordinate = value != EnumOptionValue.DISABLE;
            break;
         case 15:
            this.fontScale = Math.max(0, option.getValue(value));
            break;
         case 16:
            this.updateFrequencySetting = Math.max(0, option.getValue(value));
            break;
         case 17:
            this.threading = EnumOptionValue.bool(value);
            break;
         case 18:
            this.threadPriority = Math.max(0, option.getValue(value));
            if(this.workerThread != null && this.workerThread.isAlive()) {
               this.workerThread.setPriority(3 + this.threadPriority);
            }
            break;
         case 19:
            this.preloadedChunks = EnumOptionValue.bool(value);
            break;
         case 20:
            this.lightmap = Math.max(0, option.getValue(value));
            break;
         case 21:
            this.lightType = Math.max(0, option.getValue(value));
            break;
         case 22:
            this.undulate = EnumOptionValue.bool(value);
            break;
         case 23:
            this.heightmap = EnumOptionValue.bool(value);
            break;
         case 24:
            this.transparency = EnumOptionValue.bool(value);
            break;
         case 25:
            this.environmentColor = EnumOptionValue.bool(value);
            break;
         case 26:
            this.omitHeightCalc = EnumOptionValue.bool(value);
            break;
         case 27:
            this.hideSnow = EnumOptionValue.bool(value);
            break;
         case 28:
            this.showChunkGrid = EnumOptionValue.bool(value);
            break;
         case 29:
            this.showSlimeChunk = EnumOptionValue.bool(value);
            break;
         case 30:
            this.renderType = Math.max(0, option.getValue(value));
            break;
         case 31:
            this.configEntitiesRadar = EnumOptionValue.bool(value);
            break;
         case 32:
            this.theMinecraft.displayGuiScreen(new GuiOptionScreen(1));
            break;
         case 33:
            this.theMinecraft.displayGuiScreen(new GuiOptionScreen(2));
            break;
         case 34:
            this.theMinecraft.displayGuiScreen(new GuiOptionScreen(5));
            break;
         case 35:
            this.theMinecraft.displayGuiScreen(new GuiOptionScreen(3));
            break;
         case 36:
            try {
               Desktop.getDesktop().browse(new URI("http://www.minecraftforum.net/index.php?showtopic=482147"));
            } catch (Exception var9) {
               error("Open Forum(en)", var9);
            }
            break;
         case 37:
            try {
               Desktop.getDesktop().browse(new URI("http://forum.minecraftuser.jp/viewtopic.php?f=13&t=153"));
            } catch (Exception var8) {
               var8.printStackTrace();
               error("Open Forum(jp)", var8);
            }
         case 38:
         default:
            break;
         case 39:
            this.configEntityPlayer = EnumOptionValue.bool(value);
            break;
         case 40:
            this.configEntityAnimal = EnumOptionValue.bool(value);
            break;
         case 41:
            this.configEntityMob = EnumOptionValue.bool(value);
            break;
         case 42:
            this.configEntitySlime = EnumOptionValue.bool(value);
            break;
         case 43:
            this.configEntitySquid = EnumOptionValue.bool(value);
            break;
         case 44:
            this.configEntityLiving = EnumOptionValue.bool(value);
            break;
         case 45:
            this.configEntityLightning = EnumOptionValue.bool(value);
            break;
         case 46:
            this.configEntityDirection = EnumOptionValue.bool(value);
            break;
         case 47:
            this.theMinecraft.displayGuiScreen(new GuiOptionScreen(4));
            break;
         case 48:
            this.marker = EnumOptionValue.bool(value);
            break;
         case 49:
            this.markerIcon = EnumOptionValue.bool(value);
            break;
         case 50:
            this.markerLabel = EnumOptionValue.bool(value);
            break;
         case 51:
            this.markerDistance = EnumOptionValue.bool(value);
            break;
         case 52:
            this.defaultZoom = Math.max(0, option.getValue(value));
         }

         this.forceUpdate = true;
         this.stripCounter.reset();
         if(this.threading) {
            this.mapCalc(false);
            if(this.isCompleteImage) {
               this.texture.register();
            }
         }
      } finally {
         this.lock.unlock();
      }
   }

   public EnumOptionValue getOption(EnumOption option) {
      switch(ReiMinimap.NamelessClass1095983968.$SwitchMap$reifnsk$minimap$EnumOption[option.ordinal()]) {
      case 1:
         return EnumOptionValue.bool(this.enable);
      case 2:
         return EnumOptionValue.bool(this.showMenuKey);
      case 3:
         return this.useStencil?EnumOptionValue.STENCIL:EnumOptionValue.DEPTH;
      case 4:
         return this.notchDirection?EnumOptionValue.NORTH:EnumOptionValue.EAST;
      case 5:
         return this.roundmap?EnumOptionValue.ROUND:EnumOptionValue.ROUND;
      case 6:
         return option.getValue(this.textureView);
      case 7:
         return option.getValue(this.mapPosition);
      case 8:
         return option.getValue(this.mapScale);
      case 9:
         return this.mapOpacity == 0.25F?EnumOptionValue.PERCENT25:(this.mapOpacity == 0.5F?EnumOptionValue.PERCENT50:(this.mapOpacity == 0.75F?EnumOptionValue.PERCENT75:EnumOptionValue.PERCENT100));
      case 10:
         return option.getValue(this.largeMapScale);
      case 11:
         return this.largeMapOpacity == 0.25F?EnumOptionValue.PERCENT25:(this.largeMapOpacity == 0.5F?EnumOptionValue.PERCENT50:(this.largeMapOpacity == 0.75F?EnumOptionValue.PERCENT75:EnumOptionValue.PERCENT100));
      case 12:
         return EnumOptionValue.bool(this.largeMapLabel);
      case 13:
         return EnumOptionValue.bool(this.filtering);
      case 14:
         return option.getValue(this.coordinateType);
      case 15:
         return option.getValue(this.fontScale);
      case 16:
         return option.getValue(this.updateFrequencySetting);
      case 17:
         return EnumOptionValue.bool(this.threading);
      case 18:
         return option.getValue(this.threadPriority);
      case 19:
         return EnumOptionValue.bool(this.preloadedChunks);
      case 20:
         return option.getValue(this.lightmap);
      case 21:
         return option.getValue(this.lightType);
      case 22:
         return EnumOptionValue.bool(this.undulate);
      case 23:
         return EnumOptionValue.bool(this.heightmap);
      case 24:
         return EnumOptionValue.bool(this.transparency);
      case 25:
         return EnumOptionValue.bool(this.environmentColor);
      case 26:
         return EnumOptionValue.bool(this.omitHeightCalc);
      case 27:
         return EnumOptionValue.bool(this.hideSnow);
      case 28:
         return EnumOptionValue.bool(this.showChunkGrid);
      case 29:
         return EnumOptionValue.bool(this.showSlimeChunk);
      case 30:
         return option.getValue(this.renderType);
      case 31:
         return EnumOptionValue.bool(this.configEntitiesRadar);
      case 32:
      case 33:
      case 34:
      case 35:
      case 36:
      case 37:
      case 38:
      case 47:
      default:
         return option.getValue(0);
      case 39:
         return EnumOptionValue.bool(this.configEntityPlayer);
      case 40:
         return EnumOptionValue.bool(this.configEntityAnimal);
      case 41:
         return EnumOptionValue.bool(this.configEntityMob);
      case 42:
         return EnumOptionValue.bool(this.configEntitySlime);
      case 43:
         return EnumOptionValue.bool(this.configEntitySquid);
      case 44:
         return EnumOptionValue.bool(this.configEntityLiving);
      case 45:
         return EnumOptionValue.bool(this.configEntityLightning);
      case 46:
         return EnumOptionValue.bool(this.configEntityDirection);
      case 48:
         return EnumOptionValue.bool(this.marker);
      case 49:
         return EnumOptionValue.bool(this.markerIcon);
      case 50:
         return EnumOptionValue.bool(this.markerLabel);
      case 51:
         return EnumOptionValue.bool(this.markerDistance);
      case 52:
         return option.getValue(this.defaultZoom);
      case 53:
         return EnumOptionValue.bool(this.autoUpdateCheck);
      case 54:
         return option.getValue(this.updateCheckFlag);
      }
   }

   void saveOptions() {
      File file = new File(directory, "option.txt");

      try {
         PrintWriter var7 = new PrintWriter(file, "UTF-8");
         EnumOption[] arr$ = EnumOption.values();
         int len$ = arr$.length;

         for(int i$ = 0; i$ < len$; ++i$) {
            EnumOption var10000 = arr$[i$];
         }

         var7.flush();
         var7.close();
      } catch (Exception var71) {
         var71.printStackTrace();
      }
   }

   private void loadOptions() {
      File file = new File(directory, "option.txt");
      if(file.exists()) {
         boolean error = false;

         try {
            Scanner var6 = new Scanner(file, "UTF-8");

            while(var6.hasNextLine()) {
               try {
                  String[] var5 = var6.nextLine().split(":");
                  this.setOption(EnumOption.valueOf(toUpperCase(var5[0].trim())), EnumOptionValue.valueOf(toUpperCase(var5[1].trim())));
               } catch (Exception var51) {
                  System.err.println(var51.getMessage());
                  error = true;
               }
            }

            var6.close();
         } catch (Exception var61) {
            var61.printStackTrace();
         }

         if(error) {
            this.saveOptions();
         }

         this.flagZoom = this.defaultZoom;
      }
   }

   public List<Waypoint> getWaypoints() {
      return this.wayPts;
   }

   void saveWaypoints() {
      File waypointFile = new File(directory, this.currentLevelName + ".DIM" + this.waypointDimension + ".points");
      if(waypointFile.isDirectory()) {
         this.chatInfo("\u00a7E[Rei\'s Minimap] Error Saving Waypoints");
         error("[Rei\'s Minimap] Error Saving Waypoints: (" + waypointFile + ") is directory.");
      } else {
         try {
            PrintWriter var5 = new PrintWriter(waypointFile, "UTF-8");
            Iterator i$ = this.wayPts.iterator();

            while(i$.hasNext()) {
               Waypoint pt = (Waypoint)i$.next();
               var5.println(pt);
            }

            var5.flush();
            var5.close();
         } catch (Exception var51) {
            this.chatInfo("\u00a7E[Rei\'s Minimap] Error Saving Waypoints");
            error("Error Saving Waypoints", var51);
         }
      }
   }

   void loadWaypoints() {
      this.wayPts = null;
      this.wayPtsMap.clear();
      Pattern pattern = Pattern.compile(Pattern.quote(this.currentLevelName) + "\\.DIM(-?[0-9])\\.points");
      int load = 0;
      int dim = 0;
      String[] arr$ = directory.list();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         String file = arr$[i$];
         Matcher m = pattern.matcher(file);
         if(m.matches()) {
            ++dim;
            int dimension = Integer.parseInt(m.group(1));
            ArrayList list = new ArrayList();
            Scanner in = null;

            try {
               in = new Scanner(new File(directory, file), "UTF-8");

               while(in.hasNextLine()) {
                  Waypoint e = Waypoint.load(in.nextLine());
                  if(e != null) {
                     list.add(e);
                     ++load;
                  }
               }
            } catch (Exception var16) {
               ;
            } finally {
               if(in != null) {
                  in.close();
               }
            }

            this.wayPtsMap.put(Integer.valueOf(dimension), list);
            if(dimension == this.currentDimension) {
               this.wayPts = list;
            }
         }
      }

      if(this.wayPts == null) {
         this.wayPts = new ArrayList();
      }

      if(load != 0) {
         this.chatInfo("\u00a7E[Rei\'s Minimap] " + load + " Waypoints loaded for " + this.currentLevelName);
      }
   }

   void chatInfo(String s) {
      this.ingameGUI.getChatGUI().func_146227_a(new ChatComponentText(s));
   }

   private float[] generateLightBrightnessTable(float f) {
      float[] result = new float[16];

      for(int i = 0; i <= 15; ++i) {
         float f1 = 1.0F - (float)i / 15.0F;
         result[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * (1.0F - f) + f;
      }

      return result;
   }

   private int calculateSkylightSubtracted(long time, float k) {
      float f1 = this.calculateCelestialAngle(time) + k;
      float f2 = Math.max(0.0F, Math.min(1.0F, 1.0F - (MathHelper.cos(f1 * (float)Math.PI * 2.0F) * 2.0F + 0.5F)));
      f2 = 1.0F - f2;
      f2 = (float)((double)f2 * (1.0D - (double)(this.theWorld.getRainStrength(1.0F) * 5.0F) / 16.0D));
      f2 = (float)((double)f2 * (1.0D - (double)(this.theWorld.getWeightedThunderStrength(1.0F) * 5.0F) / 16.0D));
      f2 = 1.0F - f2;
      return (int)(f2 * 11.0F);
   }

   private void updateLightmap(long time, float k) {
      float _f = this.func_35464_b(time, k);

      for(int i = 0; i < 256; ++i) {
         float f = _f * 0.95F + 0.05F;
         float sky = this.theWorld.provider.lightBrightnessTable[i / 16] * f;
         float block = this.theWorld.provider.lightBrightnessTable[i % 16] * 1.55F;
         if(this.theWorld.lastLightningBolt > 0) {
            sky = this.theWorld.provider.lightBrightnessTable[i / 16];
         }

         float skyR = sky * (_f * 0.65F + 0.35F);
         float skyG = sky * (_f * 0.65F + 0.35F);
         float blockG = block * ((block * 0.6F + 0.4F) * 0.6F + 0.4F);
         float blockB = block * (block * block * 0.6F + 0.4F);
         float red = skyR + block;
         float green = skyG + blockG;
         float blue = sky + blockB;
         red = Math.min(1.0F, red * 0.96F + 0.03F);
         green = Math.min(1.0F, green * 0.96F + 0.03F);
         blue = Math.min(1.0F, blue * 0.96F + 0.03F);
         float f12 = this.theMinecraft.gameSettings.gammaSetting;
         float f13 = 1.0F - red;
         float f14 = 1.0F - green;
         float f15 = 1.0F - blue;
         f13 = 1.0F - f13 * f13 * f13 * f13;
         f14 = 1.0F - f14 * f14 * f14 * f14;
         f15 = 1.0F - f15 * f15 * f15 * f15;
         red = red * (1.0F - f12) + f13 * f12;
         green = green * (1.0F - f12) + f14 * f12;
         blue = blue * (1.0F - f12) + f15 * f12;
         this.lightmapRed[i] = Math.max(0.0F, Math.min(1.0F, red * 0.96F + 0.03F));
         this.lightmapGreen[i] = Math.max(0.0F, Math.min(1.0F, green * 0.96F + 0.03F));
         this.lightmapBlue[i] = Math.max(0.0F, Math.min(1.0F, blue * 0.96F + 0.03F));
      }
   }

   private float func_35464_b(long time, float k) {
      float f1 = this.calculateCelestialAngle(time) + k;
      float f2 = Math.max(0.0F, Math.min(1.0F, 1.0F - (MathHelper.cos(f1 * (float)Math.PI * 2.0F) * 2.0F + 0.2F)));
      f2 = 1.0F - f2;
      f2 *= 1.0F - this.theWorld.getRainStrength(1.0F) * 5.0F * 0.0625F;
      f2 *= 1.0F - this.theWorld.getWeightedThunderStrength(1.0F) * 5.0F * 0.0625F;
      return f2 * 0.8F + 0.2F;
   }

   private float calculateCelestialAngle(long time) {
      int i = (int)(time % 24000L);
      float f1 = (float)(i + 1) * 4.1666666E-5F - 0.25F;
      if(f1 < 0.0F) {
         ++f1;
      } else if(f1 > 1.0F) {
         --f1;
      }

      float f2 = f1;
      f1 = 1.0F - (float)((Math.cos((double)f1 * Math.PI) + 1.0D) * 0.5D);
      f1 = f2 + (f1 - f2) * 0.33333334F;
      return f1;
   }

   private void drawCenteringRectangle(double centerX, double centerY, double z, double w, double h) {
      w *= 0.5D;
      h *= 0.5D;
      this.startDrawingQuads();
      this.addVertexWithUV(centerX - w, centerY + h, z, 0.0D, 1.0D);
      this.addVertexWithUV(centerX + w, centerY + h, z, 1.0D, 1.0D);
      this.addVertexWithUV(centerX + w, centerY - h, z, 1.0D, 0.0D);
      this.addVertexWithUV(centerX - w, centerY - h, z, 0.0D, 0.0D);
      this.draw();
   }

   public static String capitalize(String src) {
      if(src == null) {
         return null;
      } else {
         boolean title = true;
         char[] cs = src.toCharArray();
         int i = 0;

         for(int j = cs.length; i < j; ++i) {
            char c = cs[i];
            if(c == 95) {
               c = 32;
            }

            cs[i] = title?Character.toTitleCase(c):Character.toLowerCase(c);
            title = Character.isWhitespace(c);
         }

         return new String(cs);
      }
   }

   public static String toUpperCase(String src) {
      return src == null?null:src.replace(' ', '_').toUpperCase(Locale.ENGLISH);
   }

   private static boolean checkGuiScreen(GuiScreen gui) {
      return gui == null || gui instanceof GuiScreenInterface || gui instanceof GuiChat || gui instanceof GuiGameOver;
   }

   String getDimensionName(int dim) {
      String name = (String)this.dimensionName.get(Integer.valueOf(dim));
      return name == null?"DIM:" + dim:name;
   }

   int getWaypointDimension() {
      return this.waypointDimension;
   }

   int getCurrentDimension() {
      return this.currentDimension;
   }

   private double getDimensionScale(int dim) {
      Double d = (Double)this.dimensionScale.get(Integer.valueOf(dim));
      return d == null?1.0D:d.doubleValue();
   }

   double getVisibleDimensionScale() {
      return this.getDimensionScale(this.waypointDimension) / this.getDimensionScale(this.currentDimension);
   }

   void prevDimension() {
      Entry entry = this.wayPtsMap.lowerEntry(Integer.valueOf(this.waypointDimension));
      if(entry == null) {
         entry = this.wayPtsMap.lowerEntry(Integer.valueOf(Integer.MAX_VALUE));
      }

      if(entry != null) {
         this.waypointDimension = ((Integer)entry.getKey()).intValue();
         this.wayPts = (List)entry.getValue();
      }
   }

   void nextDimension() {
      Entry entry = this.wayPtsMap.higherEntry(Integer.valueOf(this.waypointDimension));
      if(entry == null) {
         entry = this.wayPtsMap.higherEntry(Integer.valueOf(Integer.MIN_VALUE));
      }

      if(entry != null) {
         this.waypointDimension = ((Integer)entry.getKey()).intValue();
         this.wayPts = (List)entry.getValue();
      }
   }

   private static SocketAddress getRemoteSocketAddress(EntityPlayer player) {
      NetHandlerPlayClient netClientHandler = ((EntityClientPlayerMP)player).sendQueue;
      NetworkManager networkManager = netClientHandler.getNetworkManager();
      return networkManager == null?null:networkManager.getSocketAddress();
   }

   private static final void error(String str, Exception e) {
      File file = new File(directory, "error.txt");
      PrintWriter out = null;

      try {
         FileOutputStream ex = new FileOutputStream(file, true);
         out = new PrintWriter(new OutputStreamWriter(ex, "UTF-8"));
         information(out);
         out.println(str);
         e.printStackTrace(out);
         out.println();
         out.flush();
      } catch (Exception var8) {
         ;
      } finally {
         if(out != null) {
            out.close();
         }
      }
   }

   private static final void error(String str) {
      File file = new File(directory, "error.txt");
      PrintWriter out = null;

      try {
         FileOutputStream ex = new FileOutputStream(file, true);
         out = new PrintWriter(new OutputStreamWriter(ex, "UTF-8"));
         information(out);
         out.println(str);
         out.println();
         out.flush();
      } catch (Exception var7) {
         ;
      } finally {
         if(out != null) {
            out.close();
         }
      }
   }

   private static final void information(PrintWriter out) {
      out.printf("--- %1$tF %1$tT %1$tZ ---%n", new Object[]{Long.valueOf(System.currentTimeMillis())});
      out.printf("Rei\'s Minimap %s [%s]%n", new Object[]{"v1", "1.7.2"});
      out.printf("OS: %s (%s) version %s%n", new Object[]{System.getProperty("os.name"), System.getProperty("os.arch"), System.getProperty("os.version")});
      out.printf("Java: %s, %s%n", new Object[]{System.getProperty("java.version"), System.getProperty("java.vendor")});
      out.printf("VM: %s (%s), %s%n", new Object[]{System.getProperty("java.vm.name"), System.getProperty("java.vm.info"), System.getProperty("java.vm.vendor")});
      out.printf("LWJGL: %s%n", new Object[]{Sys.getVersion()});
      out.printf("OpenGL: %s version %s, %s%n", new Object[]{GL11.glGetString(GL11.GL_RENDERER), GL11.glGetString(GL11.GL_VERSION), GL11.glGetString(GL11.GL_VENDOR)});
   }

   boolean isMinecraftThread() {
      return Thread.currentThread() == this.mcThread;
   }

   static final int version(int i, int major, int minor, int revision) {
      return (i & 255) << 24 | (major & 255) << 16 | (minor & 255) << 8 | (revision & 255) << 0;
   }

   int getWorldHeight() {
      return this.worldHeight;
   }

   private static void close(InputStream in) {
      if(in != null) {
         try {
            in.close();
         } catch (IOException var2) {
            var2.printStackTrace();
         }
      }
   }

   private int getEntityColor(Entity entity) {
      return entity == this.thePlayer?0:(entity instanceof EntityPlayer?(this.visibleEntityPlayer?-16711681:0):(entity instanceof EntitySquid?(this.visibleEntitySquid?-16760704:0):(entity instanceof EntityAnimal?(this.visibleEntityAnimal?-1:0):(entity instanceof EntitySlime?(this.visibleEntitySlime?-10444704:0):(!(entity instanceof EntityMob) && !(entity instanceof EntityGhast)?(entity instanceof EntityLivingBase?(this.visibleEntityLiving?-12533632:0):0):(this.visibleEntityMob?-65536:0))))));
   }

   private int getVisibleEntityType(Entity entity) {
      return entity instanceof EntityLivingBase?(entity == this.thePlayer?-1:(entity instanceof EntityPlayer?(this.visibleEntityPlayer?0:-1):(entity instanceof EntitySquid?(this.visibleEntitySquid?3:-1):(entity instanceof EntityAnimal?(this.visibleEntityAnimal?2:-1):(entity instanceof EntitySlime?(this.visibleEntitySlime?4:-1):(!(entity instanceof EntityMob) && !(entity instanceof EntityGhast)?(entityIMWaveAttackerClass != null && entityIMWaveAttackerClass.isAssignableFrom(entity.getClass())?(this.visibleEntityMob?6:-1):(this.visibleEntityLiving?5:-1)):(this.visibleEntityMob?1:-1))))))):-1;
   }

   boolean getMarker() {
      return this.marker & (this.markerIcon | this.markerLabel | this.markerDistance);
   }

   boolean getMarkerIcon() {
      return this.markerIcon;
   }

   boolean getMarkerLabel() {
      return this.markerLabel;
   }

   boolean getMarkerDistance() {
      return this.markerDistance;
   }

   int getUpdateCount() {
      return this.updateCount;
   }

   Minecraft getMinecraft() {
      return this.theMinecraft;
   }

   World getWorld() {
      return this.theWorld;
   }

   public void onResourceManagerReload(IResourceManager resourceManager) {
      BlockDataPack.calcTexture();
   }

   static {
      LinkedList f = new LinkedList();
      BiomeGenBase[] i = BiomeGenBase.getBiomeGenArray();
      int e = i.length;

      for(int var9 = 0; var9 < e; ++var9) {
         BiomeGenBase var8 = i[var9];
         if(var8 != null) {
            f.add(var8);
         }
      }

      bgbList = (BiomeGenBase[])((BiomeGenBase[])f.toArray(new BiomeGenBase[0]));
      InputStream var111 = GuiIngame.class.getResourceAsStream(GuiIngame.class.getSimpleName() + ".class");
      int var10;
      if(var111 != null) {
         try {
            ByteArrayOutputStream var12 = new ByteArrayOutputStream();
            byte[] var11 = new byte[4096];

            while(true) {
               var10 = var111.read(var11);
               if(var10 == -1) {
                  var111.close();
                  String var15 = (new String(var12.toByteArray(), "UTF-8")).toLowerCase(Locale.ENGLISH);
                  if(var15.indexOf("\u00a70\u00a70") != -1 && var15.indexOf("\u00a7e\u00a7f") != -1) {
                     instance.errorString = "serious error";
                     instance.texture.unregister();
                     instance.texture = null;
                  }
                  break;
               }

               var12.write(var11, 0, var10);
            }
         } catch (Exception var101) {
            ;
         }
      }

      ZOOM_LIST = new double[]{0.5D, 1.0D, 1.5D, 2.0D, 4.0D, 8.0D};
      Class var13 = null;

      try {
         if(var13 == null) {
            var13 = Class.forName("invmod.entity.EntityIMMob");
         }
      } catch (ClassNotFoundException var91) {
         ;
      }

      try {
         if(var13 == null) {
            var13 = Class.forName("invmod.EntityIMWaveAttacker");
         }
      } catch (ClassNotFoundException var81) {
         ;
      }

      entityIMWaveAttackerClass = var13;
      temp = new float[10];
      float var14 = 0.0F;

      for(var10 = 0; var10 < temp.length; ++var10) {
         temp[var10] = (float)(1.0D / Math.sqrt((double)(var10 + 1)));
         var14 += temp[var10];
      }

      var14 = 0.3F / var14;

      for(var10 = 0; var10 < temp.length; ++var10) {
         temp[var10] *= var14;
      }

      var14 = 0.0F;

      for(var10 = 0; var10 < 10; ++var10) {
         var14 += temp[var10];
      }
   }

   static class NamelessClass1095983968 {
      static final int[] $SwitchMap$reifnsk$minimap$BlockType;
      static final int[] $SwitchMap$reifnsk$minimap$EnumOptionValue;
      static final int[] $SwitchMap$reifnsk$minimap$EnumOption = new int[EnumOption.values().length];

      static {
         try {
            $SwitchMap$reifnsk$minimap$EnumOption[EnumOption.MINIMAP.ordinal()] = 1;
         } catch (NoSuchFieldError var17) {
            ;
         }

         try {
            $SwitchMap$reifnsk$minimap$EnumOption[EnumOption.TEXTURE.ordinal()] = 6;
         } catch (NoSuchFieldError var16) {
            ;
         }

         try {
            $SwitchMap$reifnsk$minimap$EnumOption[EnumOption.ENG_FORUM.ordinal()] = 36;
         } catch (NoSuchFieldError var15) {
            ;
         }

         try {
            $SwitchMap$reifnsk$minimap$EnumOption[EnumOption.JP_FORUM.ordinal()] = 37;
         } catch (NoSuchFieldError var14) {
            ;
         }

         try {
            $SwitchMap$reifnsk$minimap$EnumOption[EnumOption.MARKER_OPTION.ordinal()] = 47;
         } catch (NoSuchFieldError var13) {
            ;
         }

         try {
            $SwitchMap$reifnsk$minimap$EnumOption[EnumOption.MARKER.ordinal()] = 48;
         } catch (NoSuchFieldError var12) {
            ;
         }

         try {
            $SwitchMap$reifnsk$minimap$EnumOption[EnumOption.MARKER_ICON.ordinal()] = 49;
         } catch (NoSuchFieldError var11) {
            ;
         }

         try {
            $SwitchMap$reifnsk$minimap$EnumOption[EnumOption.MARKER_LABEL.ordinal()] = 50;
         } catch (NoSuchFieldError var10) {
            ;
         }

         try {
            $SwitchMap$reifnsk$minimap$EnumOption[EnumOption.MARKER_DISTANCE.ordinal()] = 51;
         } catch (NoSuchFieldError var9) {
            ;
         }

         $SwitchMap$reifnsk$minimap$EnumOptionValue = new int[EnumOptionValue.values().length];

         try {
            $SwitchMap$reifnsk$minimap$EnumOptionValue[EnumOptionValue.PERCENT100.ordinal()] = 1;
         } catch (NoSuchFieldError var8) {
            ;
         }

         try {
            $SwitchMap$reifnsk$minimap$EnumOptionValue[EnumOptionValue.PERCENT75.ordinal()] = 2;
         } catch (NoSuchFieldError var7) {
            ;
         }

         try {
            $SwitchMap$reifnsk$minimap$EnumOptionValue[EnumOptionValue.PERCENT50.ordinal()] = 3;
         } catch (NoSuchFieldError var6) {
            ;
         }

         try {
            $SwitchMap$reifnsk$minimap$EnumOptionValue[EnumOptionValue.PERCENT25.ordinal()] = 4;
         } catch (NoSuchFieldError var5) {
            ;
         }

         $SwitchMap$reifnsk$minimap$BlockType = new int[BlockType.values().length];

         try {
            $SwitchMap$reifnsk$minimap$BlockType[BlockType.GRASS.ordinal()] = 1;
         } catch (NoSuchFieldError var4) {
            ;
         }

         try {
            $SwitchMap$reifnsk$minimap$BlockType[BlockType.SIMPLE_GRASS.ordinal()] = 2;
         } catch (NoSuchFieldError var3) {
            ;
         }

         try {
            $SwitchMap$reifnsk$minimap$BlockType[BlockType.FOLIAGE.ordinal()] = 3;
         } catch (NoSuchFieldError var2) {
            ;
         }

         try {
            $SwitchMap$reifnsk$minimap$BlockType[BlockType.WATER.ordinal()] = 4;
         } catch (NoSuchFieldError var1) {
            ;
         }
      }
   }
}
