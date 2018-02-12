package reifnsk.minimap;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import javax.imageio.ImageIO;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockDynamicLiquid;
import net.minecraft.block.BlockEndPortalFrame;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockIce;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPistonExtension;
import net.minecraft.block.BlockRedstoneTorch;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.BlockStaticLiquid;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

public class BlockDataPack {
   private static final int renderStandardBlock = 0;
   private static final int renderCrossedSquares = 1;
   private static final int renderBlockTorch = 2;
   private static final int renderBlockFire = 3;
   private static final int renderBlockFluids = 4;
   private static final int renderBlockRedstoneWire = 5;
   private static final int renderBlockCrops = 6;
   private static final int renderBlockDoor = 7;
   private static final int renderBlockLadder = 8;
   private static final int renderBlockMinecartTrack = 9;
   private static final int renderBlockStairs = 10;
   private static final int renderBlockFence = 11;
   private static final int renderBlockLever = 12;
   private static final int renderBlockCactus = 13;
   private static final int renderBlockBed = 14;
   private static final int renderBlockRepeater = 15;
   private static final int renderPistonBase = 16;
   private static final int renderPistonExtension = 17;
   private static final int renderBlockPane = 18;
   private static final int renderBlockStem = 19;
   private static final int renderBlockVine = 20;
   private static final int renderBlockFenceGate = 21;
   private static final int renderBlockChest = 22;
   private static final int renderBlockLilyPad = 23;
   private static final int renderBlockCauldron = 24;
   private static final int renderBlockBrewingStand = 25;
   private static final int renderBlockEndPortalFrame = 26;
   private static final int renderBlockDragonEgg = 27;
   private static final int renderBlockCocoa = 28;
   private static final int renderBlockTripWireSource = 29;
   private static final int renderBlockTripWire = 30;
   private static final int renderBlockLog = 31;
   private static final int renderBlockWall = 32;
   private static final int renderBlockFlowerpot = 33;
   private static final int renderBlockBeacon = 34;
   private static final int renderBlockAnvil = 35;
   private static final int renderBlockRepeater2 = 36;
   private static final int renderBlockComparator = 37;
   private static final int renderBlockHopper = 38;
   private static final int renderBlockModLoader = -1;
   protected static final int BLOCK_NUM = 4096;
   protected static final int BLOCK_META_BITS = 4;
   protected static final int BLOCK_META = 16;
   protected static final int BLOCK_META_MASK = 15;
   protected static final int BLOCK_COLOR_NUM = 65536;
   protected static BlockData[] blockData;
   protected static float[] height = new float[65536];
   protected static BlockData[] blockColorData = new BlockData[65536];
   public static BlockColor[] defaultBlockColor = null;
   private static AtomicReference<Thread> referenceThread = new AtomicReference();

   static synchronized void calcTexture() {
      Thread thread = new Thread() {
         public void run() {
            BlockDataPack.defaultBlockColor = BlockDataPack.calcTextureColor();
         }
      };
      thread.setDaemon(true);
      thread.setPriority(1);
      referenceThread.set(thread);
      thread.start();
   }

   private static BlockColor[] calcTextureColor() {
      BlockDynamicLiquid waterMoving = (BlockDynamicLiquid)Block.getBlockFromName("flowing_water");
      BlockStaticLiquid waterStill = (BlockStaticLiquid)Block.getBlockFromName("water");
      Thread currentThread = Thread.currentThread();
      IResourceManager rm = Minecraft.getMinecraft().getResourceManager();
      BlockColor[] result = defaultBlockColor != null?(BlockColor[])((BlockColor[])Arrays.copyOf(defaultBlockColor, 65536)):new BlockColor[65536];
      boolean skipTexture = false;
      String textureName = null;
      BufferedImage image = null;
      int[] splitImage = null;
      boolean w = false;
      boolean h = false;
      int sw = 0;
      int sh = 0;
      HashMap map = new HashMap();
      BlockData[] i = blockData;
      int len$ = i.length;

      int var32;
      for(var32 = 0; var32 < len$; ++var32) {
         BlockData bd = i[var32];
         if(referenceThread.get() != currentThread) {
            break;
         }

         int base3;
         int b;
         if(!bd.textureName.equals(textureName)) {
            textureName = bd.textureName;
            String var34 = fixDomain("textures/blocks/", textureName) + ".png";

            try {
               ResourceLocation base2 = new ResourceLocation(var34);
               IResource r = rm.getResource(base2);
               image = ImageIO.read(r.getInputStream());
               skipTexture = false;
               base3 = image.getWidth();
               b = image.getHeight();
               sw = base3;
               sh = base3;
               splitImage = calcColorArrays(image, bd.renderPass, (List)null);
            } catch (IOException var31) {
               skipTexture = true;
               continue;
            }
         } else if(skipTexture) {
            continue;
         }

         BlockColor var321 = null;
         int g;
         int meta;
         int var35;
         int var33;
         BlockType var37;
         int var331;
         int var341;
         switch(bd.renderType) {
         case 0:
         default:
            var37 = bd.extend instanceof BlockType?(BlockType)bd.extend:BlockType.NORMAL;
            var35 = calcColorInt(splitImage, sw, sh, bd.minX, bd.minZ, bd.maxX, bd.maxZ);
            var321 = new BlockColor(var35, var37);
            break;
         case 1:
            var33 = calcColorInt(splitImage, sw, sh, bd.minX, bd.minZ, bd.maxX, bd.maxZ);
            if((var33 & -16777216) != 0) {
               var35 = Math.max(var33 >>> 24, 48) << 24;
               var33 = var33 & 16777215 | var35;
               var321 = new BlockColor(var33, bd.extend instanceof BlockType?(BlockType)bd.extend:BlockType.NORMAL);
            }
            break;
         case 2:
            b = calcColorInt(splitImage, sw, sh, 0.4375F, 0.4375F, 0.5625F, 0.5625F);
            meta = calcColorInt(splitImage, sw, sh, 0.375F, 0.375F, 0.625F, 0.625F);
            var33 = b >> 24 & 255;
            var35 = meta >> 24 & 255;
            var331 = var33 + var35;
            if(var331 != 0) {
               base3 = ((b >> 16 & 255) * var33 + (meta >> 16 & 255) * var35) / var331;
               var341 = ((b >> 8 & 255) * var33 + (meta >> 8 & 255) * var35) / var331;
               g = ((b >> 0 & 255) * var33 + (meta >> 0 & 255) * var35) / var331;
               var321 = new BlockColor(Integer.MIN_VALUE | base3 << 16 | var341 << 8 | g, BlockType.NORMAL);
            } else {
               b = calcColorInt(splitImage, sw, sh, 0.25F, 0.25F, 0.75F, 0.75F);
               meta = calcColorInt(splitImage, sw, sh, 0.0F, 0.0F, 1.0F, 1.0F);
               var33 = b >> 24 & 255;
               var35 = meta >> 24 & 255;
               var331 = var33 + var35;
               if(var331 != 0) {
                  base3 = ((b >> 16 & 255) * var33 + (meta >> 16 & 255) * var35) / var331;
                  var341 = ((b >> 8 & 255) * var33 + (meta >> 8 & 255) * var35) / var331;
                  g = ((b >> 0 & 255) * var33 + (meta >> 0 & 255) * var35) / var331;
                  var321 = new BlockColor(Integer.MIN_VALUE | base3 << 16 | var341 << 8 | g, BlockType.NORMAL);
               }
            }
            break;
         case 3:
            var321 = new BlockColor(calcColorInt(splitImage, sw, sh, bd.minX, bd.minZ, bd.maxX, bd.maxZ), BlockType.NORMAL);
            break;
         case 4:
            var37 = bd.extend != waterStill && bd.extend != waterMoving?BlockType.NORMAL:BlockType.WATER;
            var321 = new BlockColor(calcColorInt(splitImage, sw, sh, bd.minX, bd.minZ, bd.maxX, bd.maxZ), var37);
            break;
         case 5:
            var33 = bd.extend instanceof Integer?((Integer)bd.extend).intValue():0;
            float var36 = (float)var33 / 15.0F;
            var331 = calcColorInt(splitImage, sw, sh, bd.minX, bd.minZ, bd.maxX, bd.maxZ);
            if((var331 & -16777216) != 0) {
               base3 = Math.max(var331 >> 24 & 255, 108);
               var341 = (int)((float)(var331 >> 16 & 255) * Math.max(0.3F, var36 * 0.6F + 0.4F));
               g = (int)((float)(var331 >> 8 & 255) * Math.max(0.0F, var36 * var36 * 0.7F - 0.5F));
               var321 = new BlockColor(base3 << 24 | var341 << 16 | g << 8, BlockType.NORMAL);
            }
            break;
         case 6:
            var33 = calcColorInt(splitImage, sw, sh, bd.minX, bd.minZ, bd.maxX, bd.maxZ);
            if((var33 & -16777216) != 0) {
               var35 = Math.max(var33 >>> 24, 32) << 24;
               var321 = new BlockColor(var33 & 16777215 | var35, BlockType.NORMAL);
            }
            break;
         case 7:
            var321 = new BlockColor(calcColorInt(splitImage, sw, sh, bd.minX, bd.minZ, bd.maxX, bd.maxZ), BlockType.NORMAL);
            break;
         case 8:
            var33 = calcColorInt(splitImage, sw, sh, bd.minX, bd.minZ, bd.maxX, bd.maxZ);
            if((var33 & -16777216) != 0) {
               var35 = Math.min(var33 >>> 24, 40) << 24;
               var321 = new BlockColor(var33 & 16777215 | var35, BlockType.NORMAL);
            }
            break;
         case 9:
            var321 = new BlockColor(calcColorInt(splitImage, sw, sh, bd.minX, bd.minZ, bd.maxX, bd.maxZ), BlockType.NORMAL);
            break;
         case 10:
            var321 = new BlockColor(calcColorInt(splitImage, sw, sh, bd.minX, bd.minZ, bd.maxX, bd.maxZ), BlockType.NORMAL);
            break;
         case 11:
            var33 = calcColorInt(splitImage, sw, sh, bd.minX, bd.minZ, bd.maxX, bd.maxZ);
            if((var33 & -16777216) != 0) {
               var35 = Math.min(var33 >>> 24, 96) << 24;
               var321 = new BlockColor(var33 & 16777215 | var35, BlockType.NORMAL);
            }
            break;
         case 12:
            var321 = new BlockColor(calcColorInt(splitImage, sw, sh, bd.minX, bd.minZ, bd.maxX, bd.maxZ), BlockType.NORMAL);
            break;
         case 13:
            var321 = new BlockColor(calcColorInt(splitImage, sw, sh, bd.minX, bd.minZ, bd.maxX, bd.maxZ), BlockType.NORMAL);
            break;
         case 14:
            var321 = new BlockColor(calcColorInt(splitImage, sw, sh, bd.minX, bd.minZ, bd.maxX, bd.maxZ), BlockType.NORMAL);
            break;
         case 15:
            var321 = new BlockColor(calcColorInt(splitImage, sw, sh, bd.minX, bd.minZ, bd.maxX, bd.maxZ), BlockType.NORMAL);
            break;
         case 16:
            if(bd.extend instanceof Integer) {
               var33 = ((Integer)bd.extend).intValue();
               if(var33 >= 10 && var33 <= 13) {
                  var321 = new BlockColor(calcColorInt(splitImage, sw, sh, 0.0F, 0.25F, 1.0F, 1.0F), BlockType.NORMAL);
                  break;
               }
            }

            var321 = new BlockColor(calcColorInt(splitImage, sw, sh, bd.minX, bd.minZ, bd.maxX, bd.maxZ), BlockType.NORMAL);
            break;
         case 17:
            if(bd.extend instanceof Integer) {
               var33 = ((Integer)bd.extend).intValue();
               if((var33 & 7) == 0 || (var33 & 7) == 1) {
                  var321 = new BlockColor(calcColorInt(splitImage, sw, sh, bd.minX, bd.minZ, bd.maxX, bd.maxZ), BlockType.NORMAL);
                  break;
               }
            }

            var321 = new BlockColor(calcColorInt(splitImage, sw, sh, 0.0F, 0.0F, 1.0F, 0.25F) & 16777215 | Integer.MIN_VALUE, BlockType.NORMAL);
            break;
         case 18:
            var33 = calcColorInt(splitImage, sw, sh, bd.minX, bd.minZ, bd.maxX, bd.maxZ);
            if((var33 & -16777216) != 0) {
               var35 = Math.min(var33 >>> 24, 40) << 24;
               var321 = new BlockColor(var33 & 16777215 | var35, BlockType.NORMAL);
            }
            break;
         case 19:
            var33 = bd.extend instanceof Integer?((Integer)bd.extend).intValue():0;
            var35 = calcColorInt(splitImage, sw, sh, 0.0F, 0.0F, 1.0F, bd.maxY);
            if((var35 & -16777216) != 0) {
               var331 = Math.max(48, var35 >> 24 & 255);
               base3 = (var35 >> 16 & 255) * var33 * 32 / 255;
               var341 = (var35 >> 8 & 255) * (255 - var33 * 8) / 255;
               g = (var35 >> 0 & 255) * var33 * 4 / 255;
               var321 = new BlockColor(var331 << 24 | base3 << 16 | var341 << 8 | g << 0, BlockType.NORMAL);
            }
            break;
         case 20:
            var33 = calcColorInt(splitImage, sw, sh, 0.0F, 0.0F, 1.0F, 1.0F);
            if((var33 & -16777216) != 0) {
               var35 = Math.min(var33 >>> 24, 32) << 24;
               var321 = new BlockColor(var33 & 16777215 | var35, BlockType.SIMPLE_FOLIAGE);
            }
            break;
         case 21:
            var33 = calcColorInt(splitImage, sw, sh, 0.0F, 0.0F, 1.0F, 1.0F);
            if((var33 & -16777216) != 0) {
               var35 = Math.min(var33 >>> 24, 128) << 24;
               var321 = new BlockColor(var33 & 16777215 | var35, BlockType.NORMAL);
            }
            break;
         case 22:
            var321 = new BlockColor(calcColorInt(splitImage, sw, sh, bd.minX, bd.minZ, bd.maxX, bd.maxZ), BlockType.NORMAL);
            break;
         case 23:
            var33 = calcColorInt(splitImage, sw, sh, bd.minX, bd.minZ, bd.maxX, bd.maxZ);
            if((var33 & -16777216) != 0) {
               var35 = var33 & -16777216;
               var331 = (var33 >> 16 & 255) * 32 / 255;
               base3 = (var33 >> 8 & 255) * 128 / 255;
               var341 = (var33 >> 0 & 255) * 48 / 255;
               var321 = new BlockColor(var35 | var331 << 16 | base3 << 8 | var341 << 0, BlockType.NORMAL);
            }
            break;
         case 24:
            var33 = calcColorInt(splitImage, sw, sh, bd.minX, bd.minZ, bd.maxX, bd.maxZ);
            var331 = calcColorInt(splitImage, sw, sh, bd.minX, bd.minZ, bd.maxX, bd.maxZ);
            if(bd.extend instanceof Integer) {
               base3 = ((Integer)bd.extend).intValue();
               if(base3 > 0) {
                  var341 = ((var331 >> 16 & 255) * 102 + 5508) / 255;
                  g = ((var331 >> 8 & 255) * 102 + 9027) / 255;
                  b = ((var331 >> 0 & 255) * 102 + 39015) / 255;
                  var331 = -16777216 | var341 << 16 | g << 8 | b << 0;
               }
            }

            base3 = var33 >> 24;
            var341 = 255 - base3;
            g = ((var331 >> 16 & 255) * var341 + (var33 >> 16 & 255) * base3) / 255;
            b = ((var331 >> 8 & 255) * var341 + (var33 >> 8 & 255) * base3) / 255;
            meta = ((var331 >> 0 & 255) * var341 + (var33 >> 0 & 255) * base3) / 255;
            var321 = new BlockColor(-16777216 | g << 16 | b << 8 | meta << 0, BlockType.NORMAL);
            break;
         case 25:
            var35 = calcColorInt(splitImage, sw, sh, 0.5625F, 0.3125F, 0.9375F, 0.6875F);
            var331 = calcColorInt(splitImage, sw, sh, 0.125F, 0.0625F, 0.5F, 0.4375F);
            base3 = calcColorInt(splitImage, sw, sh, 0.125F, 0.5625F, 0.5F, 0.9375F);
            var341 = (var35 >> 16 & 255) + (var331 >> 16 & 255) + (base3 >> 16 & 255);
            g = (var35 >> 8 & 255) + (var331 >> 8 & 255) + (base3 >> 8 & 255);
            b = (var35 >> 0 & 255) + (var331 >> 0 & 255) + (base3 >> 0 & 255);
            meta = bd.extend instanceof Integer?((Integer)bd.extend).intValue():0;
            int stand1 = calcColorInt(splitImage, sw, sh, 0.5F, 0.0F, 1.0F, 1.0F);
            int stand2 = calcColorInt(splitImage, sw, sh, 0.0F, 0.0F, 0.5F, 1.0F);
            switch(meta) {
            case 0:
               var341 += (stand1 >> 16 & 255) * 3;
               g += (stand1 >> 8 & 255) * 3;
               b += (stand1 >> 0 & 255) * 3;
               break;
            case 1:
            case 2:
            case 4:
               var341 += (stand1 >> 16 & 255) * 2 + (stand2 >> 16 & 255);
               g += (stand1 >> 8 & 255) * 2 + (stand2 >> 8 & 255);
               b += (stand1 >> 0 & 255) * 2 + (stand2 >> 0 & 255);
               break;
            case 3:
            case 5:
            case 6:
               var341 += (stand1 >> 16 & 255) + (stand2 >> 16 & 255) * 2;
               g += (stand1 >> 8 & 255) + (stand2 >> 8 & 255) * 2;
               b += (stand1 >> 0 & 255) + (stand2 >> 0 & 255) * 2;
               break;
            case 7:
               var341 += (stand2 >> 16 & 255) * 3;
               g += (stand2 >> 8 & 255) * 3;
               b += (stand2 >> 0 & 255) * 3;
            }

            var341 /= 6;
            g /= 6;
            b /= 6;
            var321 = new BlockColor(Integer.MIN_VALUE | var341 << 16 | g << 8 | b << 0, BlockType.NORMAL);
         }

         map.put(bd, var321);
      }

      for(var32 = 0; var32 < 65536; ++var32) {
         result[var32] = (BlockColor)map.get(blockColorData[var32]);
      }

      referenceThread.compareAndSet(currentThread, (Object)null);
      ReiMinimap.instance.updateTexture = true;
      return result;
   }

   private static int[] calcColorArrays(BufferedImage image, int renderPass, List<Integer> list) {
      boolean alpha = renderPass == 1;
      int w = image.getWidth();
      int h = image.getHeight();
      int sz = w * w;
      int[] result = new int[sz];
      if(w == h) {
         image.getRGB(0, 0, w, h, result, 0, w);
         return result;
      } else {
         int[] rgbArray = image.getRGB(0, 0, w, h, new int[w * h], 0, w);
         int[] factor = new int[h / w];
         int num = 0;
         int rSum;
         int gSum;
         int bSum;
         if(list == null) {
            Arrays.fill(factor, 1);
            num = factor.length;
         } else {
            Iterator var20 = list.iterator();

            while(var20.hasNext()) {
               Integer var21 = (Integer)var20.next();
               if(var21 != null) {
                  rSum = var21.intValue();
                  gSum = rSum >>> 16;
                  bSum = rSum & 65535;
                  if(gSum < factor.length) {
                     factor[gSum] += bSum;
                     num += bSum;
                  }
               }
            }
         }

         for(int var201 = 0; var201 < sz; ++var201) {
            int var211 = 0;
            rSum = 0;
            gSum = 0;
            bSum = 0;

            int r;
            int a;
            for(a = 0; a < factor.length; ++a) {
               r = rgbArray[a * sz + var201];
               var211 += (r >> 24 & 255) * factor[a];
               rSum += (r >> 16 & 255) * factor[a];
               gSum += (r >> 8 & 255) * factor[a];
               bSum += (r >> 0 & 255) * factor[a];
            }

            a = clamp(var211 / num, 0, 255);
            r = clamp(rSum / num, 0, 255);
            int g = clamp(gSum / num, 0, 255);
            int b = clamp(bSum / num, 0, 255);
            if(!alpha) {
               a = a <= 25?0:255;
            }

            result[var201] = a << 24 | r << 16 | g << 8 | b << 0;
         }

         return result;
      }
   }

   private static int clamp(int i, int min, int max) {
      return i < min?min:(i > max?max:i);
   }

   private static int calcColorInt(int[] image, int w, int h, float minX, float minZ, float maxX, float maxZ) {
      if(minX != maxX && minZ != maxZ) {
         int startX = (int)Math.floor((double)((float)w * Math.max(0.0F, minX < maxX?minX:maxX)));
         int startY = (int)Math.floor((double)((float)h * Math.max(0.0F, minZ < maxZ?minZ:maxZ)));
         int endX = (int)Math.floor((double)((float)w * Math.min(1.0F, minX < maxX?maxX:minX)));
         int endY = (int)Math.floor((double)((float)h * Math.min(1.0F, minZ < maxZ?maxZ:minZ)));
         long a = 0L;
         long r = 0L;
         long g = 0L;
         long b = 0L;

         for(int var23 = startY; var23 < endY; ++var23) {
            for(int x = startX; x < endX; ++x) {
               int argb = image[var23 * w + x];
               int _a = argb >> 24 & 255;
               a += (long)_a;
               r += (long)((argb >> 16 & 255) * _a);
               g += (long)((argb >> 8 & 255) * _a);
               b += (long)((argb >> 0 & 255) * _a);
            }
         }

         if(a == 0L) {
            return 16711935;
         } else {
            double var231 = 1.0D / (double)a;
            a /= (long)image.length;
            r = (long)Math.min(255, Math.max(0, (int)((double)r * var231)));
            g = (long)Math.min(255, Math.max(0, (int)((double)g * var231)));
            b = (long)Math.min(255, Math.max(0, (int)((double)b * var231)));
            return (int)(a << 24 | r << 16 | g << 8 | b);
         }
      } else {
         return 16711935;
      }
   }

   private static String getBlockTexture(Block block) {
      for(Class clazz = block.getClass(); clazz != null; clazz = clazz.getSuperclass()) {
         Method[] arr$ = clazz.getMethods();
         int len$ = arr$.length;

         for(int i$ = 0; i$ < len$; ++i$) {
            Method m = arr$[i$];
            if(m.getReturnType() == String.class && m.getParameterTypes().length == 0 && m.getName().equals("getTextureFile")) {
               try {
                  return (String)m.invoke(block, new Object[0]);
               } catch (Exception var7) {
                  return null;
               }
            }
         }
      }

      return null;
   }

   protected static final int calcPointer(int id, int meta) {
      assert id >= 0 && id < 4096;

      assert meta >= 0 && meta < 16;

      return id << 4 | meta;
   }

   private static boolean isPlasmaCraftFluidBlock(Block block) {
      assert block != null;

      String className = block.getClass().getName();
      return className.equals("Plasmacraft.BlockCausticStationary") || className.equals("Plasmacraft.BlockCausticFlowing");
   }

   private static String fixDomain(String base, String complex) {
      int idx = complex.indexOf(58);
      if(idx == -1) {
         return base + complex;
      } else {
         String name = complex.substring(idx + 1, complex.length());
         if(idx > 1) {
            String domain = complex.substring(0, idx);
            return domain + ':' + base + name;
         } else {
            return base + name;
         }
      }
   }

   static {
      HashMap blockDataMap = new HashMap();
      BlockAccess blockAccess = new BlockAccess();
      BlockAir air = (BlockAir)Block.getBlockFromName("air");
      BlockLeaves leaves = (BlockLeaves)Block.getBlockFromName("leaves");
      BlockPistonExtension pistonExtension = (BlockPistonExtension)Block.getBlockFromName("piston_head");
      BlockCrops crops = (BlockCrops)Block.getBlockFromName("wheat");
      BlockGrass grass = (BlockGrass)Block.getBlockFromName("grass");
      BlockTallGrass tallGrass = (BlockTallGrass)Block.getBlockFromName("tallgrass");
      BlockIce ice = (BlockIce)Block.getBlockFromName("ice");
      leaves.func_150122_b(true);
      boolean var38 = false;
      boolean var40 = false;

      try {
         var40 = true;
         var38 = true;
         int var53 = 0;

         while(true) {
            if(var53 >= 4096) {
               var38 = false;
               var40 = false;
               break;
            }

            Block block = Block.getBlockById(var53);
            if(block != null && block != air) {
               blockAccess.block = block;
               int renderType = block.getRenderType();
               int renderPass = block.getRenderBlockPass();

               try {
                  for(int e1 = 0; e1 < 16; ++e1) {
                     Object extend = null;
                     int exmeta = e1;
                     if(block == pistonExtension) {
                        exmeta = (e1 & 7) >= 6?108:e1;
                     }

                     int ptr;
                     if(block == crops && e1 >= 8) {
                        ptr = calcPointer(var53, e1);
                        blockColorData[ptr] = blockColorData[ptr & -1];
                     } else {
                        blockAccess.blockMetadata = e1;
                        ptr = calcPointer(var53, e1);

                        try {
                           block.setBlockBoundsBasedOnState(blockAccess, 0, 0, 0);
                        } catch (Exception var531) {
                           ;
                        }

                        height[ptr] = (float)block.getBlockBoundsMaxY();
                        boolean redstoneTorch = block instanceof BlockRedstoneTorch;
                        IIcon icon = null;

                        try {
                           icon = block.func_149735_b(redstoneTorch?0:1, exmeta);
                        } catch (Exception var52) {
                           ;
                        }

                        if(block instanceof BlockRedstoneWire) {
                           icon = BlockRedstoneWire.func_150173_e("redstoneDust_cross");
                        } else if(block instanceof BlockDoor) {
                           icon = block.getIcon(blockAccess, 0, 0, 0, 0);
                        }

                        if(icon != null) {
                           String textureName = icon.getIconName();
                           if(textureName != null) {
                              if(block == grass) {
                                 extend = BlockType.GRASS;
                              } else if(block == leaves) {
                                 switch(e1 & 3) {
                                 case 0:
                                 case 3:
                                 default:
                                    extend = BlockType.FOLIAGE;
                                    break;
                                 case 1:
                                    extend = BlockType.FOLIAGE_PINE;
                                    break;
                                 case 2:
                                    extend = BlockType.FOLIAGE_BIRCH;
                                 }
                              } else if(block == tallGrass && e1 != 0) {
                                 extend = BlockType.SIMPLE_GRASS;
                              } else if(block == ice) {
                                 extend = BlockType.ICE;
                              }

                              float minX = (float)block.getBlockBoundsMinX();
                              float minY = (float)block.getBlockBoundsMinY();
                              float minZ = (float)block.getBlockBoundsMinZ();
                              float maxX = (float)block.getBlockBoundsMaxX();
                              float maxY = (float)block.getBlockBoundsMaxY();
                              float maxZ = (float)block.getBlockBoundsMaxZ();
                              switch(renderType) {
                              case -1:
                                 extend = block;
                              case 0:
                              case 1:
                              case 2:
                              case 3:
                              case 6:
                              case 7:
                              case 8:
                              case 9:
                              case 11:
                              case 12:
                              case 13:
                              case 14:
                              case 15:
                              case 18:
                              case 20:
                              case 21:
                              case 22:
                              case 23:
                              default:
                                 break;
                              case 4:
                                 height[ptr] = Math.max(0.0F, 1.0F - (float)(e1 + 1) / 9.0F);
                                 extend = block;
                                 break;
                              case 5:
                                 extend = Integer.valueOf(e1);
                                 break;
                              case 10:
                                 height[ptr] = (e1 & 4) == 0?0.75F:1.0F;
                                 break;
                              case 16:
                                 extend = Integer.valueOf(e1);
                                 break;
                              case 17:
                                 extend = Integer.valueOf(e1);
                                 break;
                              case 19:
                                 extend = Integer.valueOf(Math.min(7, e1));
                                 break;
                              case 24:
                                 height[ptr] = (float)(2656 + 432 * Math.min(3, e1)) / 256.0F;
                                 extend = Integer.valueOf(Math.min(3, e1));
                                 break;
                              case 25:
                                 height[ptr] = 0.2F;
                                 extend = Integer.valueOf(e1 & 7);
                                 break;
                              case 26:
                                 boolean var54 = BlockEndPortalFrame.func_150020_b(e1);
                                 if(var54) {
                                    height[ptr] = 0.859375F;
                                 }

                                 extend = Boolean.valueOf(var54);
                              }

                              BlockData var58 = new BlockData(renderType, renderPass, textureName, minX, minY, minZ, maxX, maxY, maxZ, extend);
                              BlockData bd = (BlockData)blockDataMap.get(var58);
                              if(bd == null) {
                                 bd = var58;
                                 blockDataMap.put(var58, var58);
                              }

                              blockColorData[ptr] = bd;
                           }
                        }
                     }
                  }
               } catch (ArrayIndexOutOfBoundsException var541) {
                  ;
               } finally {
                  ;
               }
            }

            ++var53;
         }
      } finally {
         if(var40) {
            if(var38) {
               try {
                  BlockLeaves e2 = (BlockLeaves)Block.getBlockFromName("leaves");
                  e2.func_150122_b(Minecraft.getMinecraft().gameSettings.fancyGraphics);
               } catch (Exception var49) {
                  ;
               }
            }
         }
      }

      BlockLeaves var57;
      if(var38) {
         try {
            var57 = (BlockLeaves)Block.getBlockFromName("leaves");
            var57.func_150122_b(Minecraft.getMinecraft().gameSettings.fancyGraphics);
         } catch (Exception var51) {
            ;
         }
      }

      try {
         var57 = (BlockLeaves)Block.getBlockFromName("leaves");
         var57.func_150122_b(Minecraft.getMinecraft().gameSettings.fancyGraphics);
      } catch (Exception var50) {
         ;
      }

      blockData = (BlockData[])((BlockData[])blockDataMap.keySet().toArray(new BlockData[blockDataMap.size()]));
      Arrays.sort(blockData);
   }
}
