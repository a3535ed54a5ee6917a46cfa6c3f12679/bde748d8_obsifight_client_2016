package net.minecraft.client.renderer.texture;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import javax.imageio.ImageIO;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.StitcherException;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.data.AnimationMetadataSection;
import net.minecraft.client.resources.data.TextureMetadataSection;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import optifine.Config;
import optifine.ConnectedTextures;
import optifine.CustomItems;
import optifine.Reflector;
import optifine.ReflectorForge;
import optifine.TextureUtils;
import optifine.WrUpdates;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TextureMap extends AbstractTexture implements ITickableTextureObject, IIconRegister {
   private static final Logger logger = LogManager.getLogger();
   public static final ResourceLocation locationBlocksTexture = new ResourceLocation("textures/atlas/blocks.png");
   public static final ResourceLocation locationItemsTexture = new ResourceLocation("textures/atlas/items.png");
   private final List listAnimatedSprites;
   private final Map mapRegisteredSprites;
   private final Map mapUploadedSprites;
   public final int textureType;
   public final String basePath;
   private int field_147636_j;
   private int field_147637_k;
   private final TextureAtlasSprite missingImage;
   public static TextureMap textureMapBlocks = null;
   public static TextureMap textureMapItems = null;
   private TextureAtlasSprite[] iconGrid;
   private int iconGridSize;
   private int iconGridCountX;
   private int iconGridCountY;
   private double iconGridSizeU;
   private double iconGridSizeV;
   private static final boolean ENABLE_SKIP = Boolean.parseBoolean(System.getProperty("fml.skipFirstTextureLoad", "true"));
   private boolean skipFirst;
   private static final String __OBFID = "CL_00001058";
   private static final String __OBFID = "CL_00001058";

   public TextureMap(int par1, String par2Str) {
      this(par1, par2Str, false);
   }

   public TextureMap(int par1, String par2Str, boolean skipFirst) {
      this.listAnimatedSprites = Lists.newArrayList();
      this.mapRegisteredSprites = Maps.newHashMap();
      this.mapUploadedSprites = Maps.newHashMap();
      this.field_147637_k = 1;
      this.missingImage = new TextureAtlasSprite("missingno");
      this.iconGrid = null;
      this.iconGridSize = -1;
      this.iconGridCountX = -1;
      this.iconGridCountY = -1;
      this.iconGridSizeU = -1.0D;
      this.iconGridSizeV = -1.0D;
      this.skipFirst = false;
      this.textureType = par1;
      this.basePath = par2Str;
      if(this.textureType == 0) {
         textureMapBlocks = this;
      }

      if(this.textureType == 1) {
         textureMapItems = this;
      }

      this.registerIcons();
      this.skipFirst = skipFirst && ENABLE_SKIP;
   }

   private void initMissingImage() {
      int[] var1;
      if((float)this.field_147637_k > 1.0F) {
         boolean var51 = true;
         boolean var3 = true;
         boolean var4 = true;
         this.missingImage.setIconWidth(32);
         this.missingImage.setIconHeight(32);
         var1 = new int[1024];
         System.arraycopy(TextureUtil.missingTextureData, 0, var1, 0, TextureUtil.missingTextureData.length);
         TextureUtil.func_147948_a(var1, 16, 16, 8);
      } else {
         var1 = TextureUtil.missingTextureData;
         this.missingImage.setIconWidth(16);
         this.missingImage.setIconHeight(16);
      }

      int[][] var511 = new int[this.field_147636_j + 1][];
      var511[0] = var1;
      this.missingImage.setFramesTextureData(Lists.newArrayList(new int[][][]{var511}));
      this.missingImage.setIndexInMap(0);
   }

   public void loadTexture(IResourceManager par1ResourceManager) throws IOException {
      this.initMissingImage();
      this.func_147631_c();
      this.loadTextureAtlas(par1ResourceManager);
   }

   public void loadTextureAtlas(IResourceManager par1ResourceManager) {
      Config.dbg("Loading texture map: " + this.basePath);
      WrUpdates.finishCurrentUpdate();
      this.registerIcons();
      int var2 = Minecraft.getGLMaximumTextureSize();
      Stitcher var3 = new Stitcher(var2, var2, true, 0, this.field_147636_j);
      this.mapUploadedSprites.clear();
      this.listAnimatedSprites.clear();
      int var4 = Integer.MAX_VALUE;
      Reflector.callVoid(Reflector.ForgeHooksClient_onTextureStitchedPre, new Object[]{this});
      Iterator var5 = this.mapRegisteredSprites.entrySet().iterator();

      TextureAtlasSprite var8;
      while(var5.hasNext() && !this.skipFirst) {
         Entry var241 = (Entry)var5.next();
         ResourceLocation var251 = new ResourceLocation((String)var241.getKey());
         var8 = (TextureAtlasSprite)var241.getValue();
         ResourceLocation sheetWidth2 = this.func_147634_a(var251, 0);
         if(var8.hasCustomLoader(par1ResourceManager, var251)) {
            if(!var8.load(par1ResourceManager, var251)) {
               var4 = Math.min(var4, Math.min(var8.getIconWidth(), var8.getIconHeight()));
               var3.addSprite(var8);
            }

            Config.dbg("Custom loader: " + var8);
         } else {
            try {
               IResource sheetHeight1 = par1ResourceManager.getResource(sheetWidth2);
               BufferedImage[] debugImage2 = new BufferedImage[1 + this.field_147636_j];
               debugImage2[0] = ImageIO.read(sheetHeight1.getInputStream());
               TextureMetadataSection var262 = (TextureMetadataSection)sheetHeight1.getMetadata("texture");
               if(var262 != null) {
                  List var282 = var262.func_148535_c();
                  int var301;
                  if(!var282.isEmpty()) {
                     int var21 = debugImage2[0].getWidth();
                     var301 = debugImage2[0].getHeight();
                     if(MathHelper.roundUpToPowerOfTwo(var21) != var21 || MathHelper.roundUpToPowerOfTwo(var301) != var301) {
                        throw new RuntimeException("Unable to load extra miplevels, source-texture is not power of two");
                     }
                  }

                  Iterator var212 = var282.iterator();

                  while(var212.hasNext()) {
                     var301 = ((Integer)var212.next()).intValue();
                     if(var301 > 0 && var301 < debugImage2.length - 1 && debugImage2[var301] == null) {
                        ResourceLocation var321 = this.func_147634_a(var251, var301);

                        try {
                           debugImage2[var301] = ImageIO.read(par1ResourceManager.getResource(var321).getInputStream());
                        } catch (IOException var20) {
                           logger.error("Unable to load miplevel {} from: {}", new Object[]{Integer.valueOf(var301), var321, var20});
                        }
                     }
                  }
               }

               AnimationMetadataSection var2821 = (AnimationMetadataSection)sheetHeight1.getMetadata("animation");
               var8.func_147964_a(debugImage2, var2821, (float)this.field_147637_k > 1.0F);
            } catch (RuntimeException var22) {
               logger.error("Unable to parse metadata from " + sheetWidth2, var22);
               ReflectorForge.FMLClientHandler_trackBrokenTexture(sheetWidth2, var22.getMessage());
               continue;
            } catch (IOException var23) {
               logger.error("Using missing texture, unable to load " + sheetWidth2 + ", " + var23.getClass().getName());
               ReflectorForge.FMLClientHandler_trackMissingTexture(sheetWidth2);
               continue;
            }

            var4 = Math.min(var4, Math.min(var8.getIconWidth(), var8.getIconHeight()));
            var3.addSprite(var8);
         }
      }

      int var2411 = MathHelper.calculateLogBaseTwo(var4);
      if(var2411 < 0) {
         var2411 = 0;
      }

      if(var2411 < this.field_147636_j) {
         logger.info("{}: dropping miplevel from {} to {}, because of minTexel: {}", new Object[]{this.basePath, Integer.valueOf(this.field_147636_j), Integer.valueOf(var2411), Integer.valueOf(var4)});
         this.field_147636_j = var2411;
      }

      Iterator var2511 = this.mapRegisteredSprites.values().iterator();

      while(var2511.hasNext() && !this.skipFirst) {
         final TextureAtlasSprite sheetWidth21 = (TextureAtlasSprite)var2511.next();

         try {
            sheetWidth21.func_147963_d(this.field_147636_j);
         } catch (Throwable var19) {
            CrashReport debugImage21 = CrashReport.makeCrashReport(var19, "Applying mipmap");
            CrashReportCategory var2621 = debugImage21.makeCategory("Sprite being mipmapped");
            var2621.addCrashSectionCallable("Sprite name", new Callable() {
               private static final String __OBFID = "CL_00001059";
               private static final String __OBFID = "CL_00001059";

               public String call1() {
                  return sheetWidth21.getIconName();
               }
               public Object call() throws Exception {
                  return this.call1();
               }
            });
            var2621.addCrashSectionCallable("Sprite size", new Callable() {
               private static final String __OBFID = "CL_00001060";
               private static final String __OBFID = "CL_00001060";

               public String call1() {
                  return sheetWidth21.getIconWidth() + " x " + sheetWidth21.getIconHeight();
               }
               public Object call() throws Exception {
                  return this.call1();
               }
            });
            var2621.addCrashSectionCallable("Sprite frames", new Callable() {
               private static final String __OBFID = "CL_00001061";
               private static final String __OBFID = "CL_00001061";

               public String call1() {
                  return sheetWidth21.getFrameCount() + " frames";
               }
               public Object call() throws Exception {
                  return this.call1();
               }
            });
            var2621.addCrashSection("Mipmap levels", Integer.valueOf(this.field_147636_j));
            throw new ReportedException(debugImage21);
         }
      }

      this.missingImage.func_147963_d(this.field_147636_j);
      var3.addSprite(this.missingImage);
      this.skipFirst = false;

      try {
         var3.doStitch();
      } catch (StitcherException var18) {
         throw var18;
      }

      Config.dbg("Texture size: " + this.basePath + ", " + var3.getCurrentWidth() + "x" + var3.getCurrentHeight());
      int sheetWidth22 = var3.getCurrentWidth();
      int sheetHeight11 = var3.getCurrentHeight();
      BufferedImage debugImage22 = null;
      if(System.getProperty("saveTextureMap", "false").equalsIgnoreCase("true")) {
         debugImage22 = this.makeDebugImage(sheetWidth22, sheetHeight11);
      }

      logger.info("Created: {}x{} {}-atlas", new Object[]{Integer.valueOf(var3.getCurrentWidth()), Integer.valueOf(var3.getCurrentHeight()), this.basePath});
      TextureUtil.func_147946_a(this.getGlTextureId(), this.field_147636_j, var3.getCurrentWidth(), var3.getCurrentHeight(), (float)this.field_147637_k);
      HashMap var2622 = Maps.newHashMap(this.mapRegisteredSprites);
      Iterator var2822 = var3.getStichSlots().iterator();

      while(var2822.hasNext()) {
         var8 = (TextureAtlasSprite)var2822.next();
         String var3011 = var8.getIconName();
         var2622.remove(var3011);
         this.mapUploadedSprites.put(var3011, var8);

         try {
            TextureUtil.func_147955_a(var8.func_147965_a(0), var8.getIconWidth(), var8.getIconHeight(), var8.getOriginX(), var8.getOriginY(), false, false);
            if(debugImage22 != null) {
               this.addDebugSprite(var8, debugImage22);
            }
         } catch (Throwable var211) {
            CrashReport var3211 = CrashReport.makeCrashReport(var211, "Stitching texture atlas");
            CrashReportCategory var33 = var3211.makeCategory("Texture being stitched together");
            var33.addCrashSection("Atlas path", this.basePath);
            var33.addCrashSection("Sprite", var8);
            throw new ReportedException(var3211);
         }

         if(var8.hasAnimationMetadata()) {
            this.listAnimatedSprites.add(var8);
         } else {
            var8.clearFramesTextureData();
         }
      }

      var2822 = var2622.values().iterator();

      while(var2822.hasNext()) {
         var8 = (TextureAtlasSprite)var2822.next();
         var8.copyFrom(this.missingImage);
      }

      if(debugImage22 != null) {
         this.writeDebugImage(debugImage22, "debug_" + this.basePath.replace('/', '_') + ".png");
      }

      Reflector.callVoid(Reflector.ForgeHooksClient_onTextureStitchedPost, new Object[]{this});
   }

   private ResourceLocation func_147634_a(ResourceLocation p_147634_1_, int p_147634_2_) {
      return this.isAbsoluteLocation(p_147634_1_)?(p_147634_2_ == 0?new ResourceLocation(p_147634_1_.getResourceDomain(), p_147634_1_.getResourcePath() + ".png"):new ResourceLocation(p_147634_1_.getResourceDomain(), p_147634_1_.getResourcePath() + "mipmap" + p_147634_2_ + ".png")):(p_147634_2_ == 0?new ResourceLocation(p_147634_1_.getResourceDomain(), String.format("%s/%s%s", new Object[]{this.basePath, p_147634_1_.getResourcePath(), ".png"})):new ResourceLocation(p_147634_1_.getResourceDomain(), String.format("%s/mipmaps/%s.%d%s", new Object[]{this.basePath, p_147634_1_.getResourcePath(), Integer.valueOf(p_147634_2_), ".png"})));
   }

   private void registerIcons() {
      this.mapRegisteredSprites.clear();
      Iterator var1;
      if(this.textureType == 0) {
         var1 = Block.blockRegistry.iterator();

         while(var1.hasNext()) {
            Block var31 = (Block)var1.next();
            if(var31.getMaterial() != Material.air) {
               var31.registerBlockIcons(this);
            }
         }

         Minecraft.getMinecraft().renderGlobal.registerDestroyBlockIcons(this);
         RenderManager.instance.updateIcons(this);
         ConnectedTextures.updateIcons(this);
      }

      if(this.textureType == 1) {
         CustomItems.updateIcons(this);
      }

      var1 = Item.itemRegistry.iterator();

      while(var1.hasNext()) {
         Item var311 = (Item)var1.next();
         if(var311 != null && var311.getSpriteNumber() == this.textureType) {
            var311.registerIcons(this);
         }
      }
   }

   public TextureAtlasSprite getAtlasSprite(String par1Str) {
      TextureAtlasSprite var2 = (TextureAtlasSprite)this.mapUploadedSprites.get(par1Str);
      if(var2 == null) {
         var2 = this.missingImage;
      }

      return var2;
   }

   public void updateAnimations() {
      TextureUtil.bindTexture(this.getGlTextureId());
      Iterator var1 = this.listAnimatedSprites.iterator();

      while(var1.hasNext()) {
         TextureAtlasSprite var2 = (TextureAtlasSprite)var1.next();
         if(this.textureType == 0) {
            if(!this.isTerrainAnimationActive(var2)) {
               continue;
            }
         } else if(this.textureType == 1 && !this.isItemAnimationActive(var2)) {
            continue;
         }

         var2.updateAnimation();
      }
   }

   private boolean isItemAnimationActive(TextureAtlasSprite ts) {
      return ts != TextureUtils.iconClock && ts != TextureUtils.iconCompass?Config.isAnimatedItems():true;
   }

   public IIcon registerIcon(String par1Str) {
      if(par1Str == null) {
         throw new IllegalArgumentException("Name cannot be null!");
      } else if(par1Str.indexOf(92) != -1 && !this.isAbsoluteLocationPath(par1Str)) {
         throw new IllegalArgumentException("Name cannot contain slashes!");
      } else {
         Object var2 = (TextureAtlasSprite)this.mapRegisteredSprites.get(par1Str);
         if(var2 == null && this.textureType == 1 && Reflector.ModLoader_getCustomAnimationLogic.exists()) {
            var2 = Reflector.call(Reflector.ModLoader_getCustomAnimationLogic, new Object[]{par1Str});
         }

         if(var2 == null) {
            if(this.textureType == 1) {
               if("clock".equals(par1Str)) {
                  var2 = new TextureClock(par1Str);
               } else if("compass".equals(par1Str)) {
                  var2 = new TextureCompass(par1Str);
               } else {
                  var2 = new TextureAtlasSprite(par1Str);
               }
            } else {
               var2 = new TextureAtlasSprite(par1Str);
            }

            this.mapRegisteredSprites.put(par1Str, var2);
            if(var2 instanceof TextureAtlasSprite) {
               TextureAtlasSprite tas = (TextureAtlasSprite)var2;
               tas.setIndexInMap(this.mapRegisteredSprites.size());
            }
         }

         return (IIcon)var2;
      }
   }

   public int getTextureType() {
      return this.textureType;
   }

   public void tick() {
      this.updateAnimations();
   }

   public void func_147633_a(int p_147633_1_) {
      this.field_147636_j = p_147633_1_;
   }

   public void func_147632_b(int p_147632_1_) {
      this.field_147637_k = p_147632_1_;
   }

   public TextureAtlasSprite getTextureExtry(String name) {
      return (TextureAtlasSprite)this.mapRegisteredSprites.get(name);
   }

   public boolean setTextureEntry(String name, TextureAtlasSprite entry) {
      if(!this.mapRegisteredSprites.containsKey(name)) {
         this.mapRegisteredSprites.put(name, entry);
         entry.setIndexInMap(this.mapRegisteredSprites.size());
         return true;
      } else {
         return false;
      }
   }

   private boolean isAbsoluteLocation(ResourceLocation loc) {
      String path = loc.getResourcePath();
      return this.isAbsoluteLocationPath(path);
   }

   private boolean isAbsoluteLocationPath(String resPath) {
      String path = resPath.toLowerCase();
      return path.startsWith("mcpatcher/") || path.startsWith("optifine/");
   }

   public TextureAtlasSprite getIconSafe(String name) {
      return (TextureAtlasSprite)this.mapRegisteredSprites.get(name);
   }

   private int getStandardTileSize(Collection icons) {
      int[] sizeCounts = new int[16];
      Iterator mostUsedPo2 = icons.iterator();

      int value;
      int count;
      int var9;
      while(mostUsedPo2.hasNext()) {
         TextureAtlasSprite var8 = (TextureAtlasSprite)mostUsedPo2.next();
         if(var8 != null) {
            value = TextureUtils.getPowerOfTwo(var8.getWidth());
            count = TextureUtils.getPowerOfTwo(var8.getHeight());
            var9 = Math.max(value, count);
            if(var9 < sizeCounts.length) {
               ++sizeCounts[var9];
            }
         }
      }

      int var81 = 4;
      var9 = 0;

      for(value = 0; value < sizeCounts.length; ++value) {
         count = sizeCounts[value];
         if(count > var9) {
            var81 = value;
            var9 = count;
         }
      }

      if(var81 < 4) {
         var81 = 4;
      }

      value = TextureUtils.twoToPower(var81);
      return value;
   }

   private void updateIconGrid(int sheetWidth, int sheetHeight) {
      this.iconGridCountX = -1;
      this.iconGridCountY = -1;
      this.iconGrid = null;
      if(this.iconGridSize > 0) {
         this.iconGridCountX = sheetWidth / this.iconGridSize;
         this.iconGridCountY = sheetHeight / this.iconGridSize;
         this.iconGrid = new TextureAtlasSprite[this.iconGridCountX * this.iconGridCountY];
         this.iconGridSizeU = 1.0D / (double)this.iconGridCountX;
         this.iconGridSizeV = 1.0D / (double)this.iconGridCountY;
         Iterator it = this.mapUploadedSprites.values().iterator();

         while(it.hasNext()) {
            TextureAtlasSprite ts = (TextureAtlasSprite)it.next();
            double deltaU = 0.5D / (double)sheetWidth;
            double deltaV = 0.5D / (double)sheetHeight;
            double uMin = (double)Math.min(ts.getMinU(), ts.getMaxU()) + deltaU;
            double vMin = (double)Math.min(ts.getMinV(), ts.getMaxV()) + deltaV;
            double uMax = (double)Math.max(ts.getMinU(), ts.getMaxU()) - deltaU;
            double vMax = (double)Math.max(ts.getMinV(), ts.getMaxV()) - deltaV;
            int iuMin = (int)(uMin / this.iconGridSizeU);
            int ivMin = (int)(vMin / this.iconGridSizeV);
            int iuMax = (int)(uMax / this.iconGridSizeU);
            int ivMax = (int)(vMax / this.iconGridSizeV);

            for(int iu = iuMin; iu <= iuMax; ++iu) {
               if(iu >= 0 && iu < this.iconGridCountX) {
                  for(int iv = ivMin; iv <= ivMax; ++iv) {
                     if(iv >= 0 && iv < this.iconGridCountX) {
                        int index = iv * this.iconGridCountX + iu;
                        this.iconGrid[index] = ts;
                     } else {
                        Config.warn("Invalid grid V: " + iv + ", icon: " + ts.getIconName());
                     }
                  }
               } else {
                  Config.warn("Invalid grid U: " + iu + ", icon: " + ts.getIconName());
               }
            }
         }
      }
   }

   public TextureAtlasSprite getIconByUV(double u, double v) {
      if(this.iconGrid == null) {
         return null;
      } else {
         int iu = (int)(u / this.iconGridSizeU);
         int iv = (int)(v / this.iconGridSizeV);
         int index = iv * this.iconGridCountX + iu;
         return index >= 0 && index <= this.iconGrid.length?this.iconGrid[index]:null;
      }
   }

   public TextureAtlasSprite getMissingSprite() {
      return this.missingImage;
   }

   public int getMaxTextureIndex() {
      return this.mapRegisteredSprites.size();
   }

   private boolean isTerrainAnimationActive(TextureAtlasSprite ts) {
      return ts != TextureUtils.iconWaterStill && ts != TextureUtils.iconWaterFlow?(ts != TextureUtils.iconLavaStill && ts != TextureUtils.iconLavaFlow?(ts != TextureUtils.iconFireLayer0 && ts != TextureUtils.iconFireLayer1?(ts == TextureUtils.iconPortal?Config.isAnimatedPortal():Config.isAnimatedTerrain()):Config.isAnimatedFire()):Config.isAnimatedLava()):Config.isAnimatedWater();
   }

   public void loadTextureSafe(IResourceManager rm) {
      try {
         this.loadTexture(rm);
      } catch (IOException var3) {
         Config.warn("Error loading texture map: " + this.basePath);
         var3.printStackTrace();
      }
   }

   private BufferedImage makeDebugImage(int sheetWidth, int sheetHeight) {
      BufferedImage image = new BufferedImage(sheetWidth, sheetHeight, 2);
      Graphics2D g = image.createGraphics();
      g.setPaint(new Color(255, 255, 0));
      g.fillRect(0, 0, image.getWidth(), image.getHeight());
      return image;
   }

   private void addDebugSprite(TextureAtlasSprite ts, BufferedImage image) {
      if(ts.getFrameCount() < 1) {
         Config.warn("Debug sprite has no data: " + ts.getIconName());
      } else {
         int[] data = ts.func_147965_a(0)[0];
         image.setRGB(ts.getOriginX(), ts.getOriginY(), ts.getIconWidth(), ts.getIconHeight(), data, 0, ts.getIconWidth());
      }
   }

   private void writeDebugImage(BufferedImage image, String pngPath) {
      try {
         ImageIO.write(image, "png", new File(Config.getMinecraft().mcDataDir, pngPath));
      } catch (Exception var4) {
         var4.printStackTrace();
      }
   }
}
