package optifine;

import java.util.ArrayList;
import java.util.Properties;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.BiomeGenBase;

public class ConnectedProperties {
   public String name = null;
   public String basePath = null;
   public int[] matchBlocks = null;
   public String[] matchTiles = null;
   public int method = 0;
   public String[] tiles = null;
   public int connect = 0;
   public int faces = 63;
   public int[] metadatas = null;
   public BiomeGenBase[] biomes = null;
   public int minHeight = 0;
   public int maxHeight = 1024;
   public int renderPass = 0;
   public boolean innerSeams = false;
   public int width = 0;
   public int height = 0;
   public int[] weights = null;
   public int symmetry = 1;
   public int[] sumWeights = null;
   public int sumAllWeights = 1;
   public IIcon[] matchTileIcons = null;
   public IIcon[] tileIcons = null;
   public static final int METHOD_NONE = 0;
   public static final int METHOD_CTM = 1;
   public static final int METHOD_HORIZONTAL = 2;
   public static final int METHOD_TOP = 3;
   public static final int METHOD_RANDOM = 4;
   public static final int METHOD_REPEAT = 5;
   public static final int METHOD_VERTICAL = 6;
   public static final int METHOD_FIXED = 7;
   public static final int METHOD_HORIZONTAL_VERTICAL = 8;
   public static final int METHOD_VERTICAL_HORIZONTAL = 9;
   public static final int CONNECT_NONE = 0;
   public static final int CONNECT_BLOCK = 1;
   public static final int CONNECT_TILE = 2;
   public static final int CONNECT_MATERIAL = 3;
   public static final int CONNECT_UNKNOWN = 128;
   public static final int FACE_BOTTOM = 1;
   public static final int FACE_TOP = 2;
   public static final int FACE_EAST = 4;
   public static final int FACE_WEST = 8;
   public static final int FACE_NORTH = 16;
   public static final int FACE_SOUTH = 32;
   public static final int FACE_SIDES = 60;
   public static final int FACE_ALL = 63;
   public static final int FACE_UNKNOWN = 128;
   public static final int SYMMETRY_NONE = 1;
   public static final int SYMMETRY_OPPOSITE = 2;
   public static final int SYMMETRY_ALL = 6;
   public static final int SYMMETRY_UNKNOWN = 128;

   public ConnectedProperties(Properties props, String path) {
      this.name = parseName(path);
      this.basePath = parseBasePath(path);
      this.matchBlocks = parseBlockIds(props.getProperty("matchBlocks"));
      this.matchTiles = this.parseMatchTiles(props.getProperty("matchTiles"));
      this.method = parseMethod(props.getProperty("method"));
      this.tiles = this.parseTileNames(props.getProperty("tiles"));
      this.connect = parseConnect(props.getProperty("connect"));
      this.faces = parseFaces(props.getProperty("faces"));
      this.metadatas = parseInts(props.getProperty("metadata"));
      this.biomes = parseBiomes(props.getProperty("biomes"));
      this.minHeight = parseInt(props.getProperty("minHeight"), -1);
      this.maxHeight = parseInt(props.getProperty("maxHeight"), 1024);
      this.renderPass = parseInt(props.getProperty("renderPass"));
      this.innerSeams = parseBoolean(props.getProperty("innerSeams"));
      this.width = parseInt(props.getProperty("width"));
      this.height = parseInt(props.getProperty("height"));
      this.weights = parseInts(props.getProperty("weights"));
      this.symmetry = parseSymmetry(props.getProperty("symmetry"));
   }

   private String[] parseMatchTiles(String str) {
      if(str == null) {
         return null;
      } else {
         String[] names = Config.tokenize(str, " ");

         for(int i = 0; i < names.length; ++i) {
            String iconName = names[i];
            if(iconName.endsWith(".png")) {
               iconName = iconName.substring(0, iconName.length() - 4);
            }

            iconName = TextureUtils.fixResourcePath(iconName, this.basePath);
            names[i] = iconName;
         }

         return names;
      }
   }

   private static String parseName(String path) {
      String str = path;
      int pos = path.lastIndexOf(47);
      if(pos >= 0) {
         str = path.substring(pos + 1);
      }

      int pos2 = str.lastIndexOf(46);
      if(pos2 >= 0) {
         str = str.substring(0, pos2);
      }

      return str;
   }

   private static String parseBasePath(String path) {
      int pos = path.lastIndexOf(47);
      return pos < 0?"":path.substring(0, pos);
   }

   private static BiomeGenBase[] parseBiomes(String str) {
      if(str == null) {
         return null;
      } else {
         String[] biomeNames = Config.tokenize(str, " ");
         ArrayList list = new ArrayList();

         for(int var6 = 0; var6 < biomeNames.length; ++var6) {
            String biomeName = biomeNames[var6];
            BiomeGenBase biome = findBiome(biomeName);
            if(biome == null) {
               Config.warn("Biome not found: " + biomeName);
            } else {
               list.add(biome);
            }
         }

         BiomeGenBase[] var61 = (BiomeGenBase[])((BiomeGenBase[])((BiomeGenBase[])list.toArray(new BiomeGenBase[list.size()])));
         return var61;
      }
   }

   private static BiomeGenBase findBiome(String biomeName) {
      biomeName = biomeName.toLowerCase();
      BiomeGenBase[] biomeList = BiomeGenBase.getBiomeGenArray();

      for(int i = 0; i < biomeList.length; ++i) {
         BiomeGenBase biome = biomeList[i];
         if(biome != null) {
            String name = biome.biomeName.replace(" ", "").toLowerCase();
            if(name.equals(biomeName)) {
               return biome;
            }
         }
      }

      return null;
   }

   private String[] parseTileNames(String str) {
      if(str == null) {
         return null;
      } else {
         ArrayList list = new ArrayList();
         String[] iconStrs = Config.tokenize(str, " ,");

         label66:
         for(int var10 = 0; var10 < iconStrs.length; ++var10) {
            String var11 = iconStrs[var10];
            if(var11.contains("-")) {
               String[] var12 = Config.tokenize(var11, "-");
               if(var12.length == 2) {
                  int var13 = Config.parseInt(var12[0], -1);
                  int max = Config.parseInt(var12[1], -1);
                  if(var13 >= 0 && max >= 0) {
                     if(var13 <= max) {
                        int n = var13;

                        while(true) {
                           if(n > max) {
                              continue label66;
                           }

                           list.add(String.valueOf(n));
                           ++n;
                        }
                     } else {
                        Config.warn("Invalid interval: " + var11 + ", when parsing: " + str);
                        continue;
                     }
                  }
               }
            }

            list.add(var11);
         }

         String[] var101 = (String[])((String[])((String[])list.toArray(new String[list.size()])));

         for(int var111 = 0; var111 < var101.length; ++var111) {
            String var121 = var101[var111];
            var121 = TextureUtils.fixResourcePath(var121, this.basePath);
            if(!var121.startsWith(this.basePath) && !var121.startsWith("textures/") && !var121.startsWith("mcpatcher/")) {
               var121 = this.basePath + "/" + var121;
            }

            if(var121.endsWith(".png")) {
               var121 = var121.substring(0, var121.length() - 4);
            }

            String var131 = "textures/blocks/";
            if(var121.startsWith(var131)) {
               var121 = var121.substring(var131.length());
            }

            if(var121.startsWith("/")) {
               var121 = var121.substring(1);
            }

            var101[var111] = var121;
         }

         return var101;
      }
   }

   private static int parseInt(String str) {
      if(str == null) {
         return -1;
      } else {
         int num = Config.parseInt(str, -1);
         if(num < 0) {
            Config.warn("Invalid number: " + str);
         }

         return num;
      }
   }

   private static int parseInt(String str, int defVal) {
      if(str == null) {
         return defVal;
      } else {
         int num = Config.parseInt(str, -1);
         if(num < 0) {
            Config.warn("Invalid number: " + str);
            return defVal;
         } else {
            return num;
         }
      }
   }

   private static boolean parseBoolean(String str) {
      return str == null?false:str.toLowerCase().equals("true");
   }

   private static int parseSymmetry(String str) {
      if(str == null) {
         return 1;
      } else if(str.equals("opposite")) {
         return 2;
      } else if(str.equals("all")) {
         return 6;
      } else {
         Config.warn("Unknown symmetry: " + str);
         return 1;
      }
   }

   private static int parseFaces(String str) {
      if(str == null) {
         return 63;
      } else {
         String[] faceStrs = Config.tokenize(str, " ,");
         int facesMask = 0;

         for(int i = 0; i < faceStrs.length; ++i) {
            String faceStr = faceStrs[i];
            int faceMask = parseFace(faceStr);
            facesMask |= faceMask;
         }

         return facesMask;
      }
   }

   private static int parseFace(String str) {
      str = str.toLowerCase();
      if(str.equals("bottom")) {
         return 1;
      } else if(str.equals("top")) {
         return 2;
      } else if(str.equals("north")) {
         return 4;
      } else if(str.equals("south")) {
         return 8;
      } else if(str.equals("east")) {
         return 32;
      } else if(str.equals("west")) {
         return 16;
      } else if(str.equals("sides")) {
         return 60;
      } else if(str.equals("all")) {
         return 63;
      } else {
         Config.warn("Unknown face: " + str);
         return 128;
      }
   }

   private static int parseConnect(String str) {
      if(str == null) {
         return 0;
      } else if(str.equals("block")) {
         return 1;
      } else if(str.equals("tile")) {
         return 2;
      } else if(str.equals("material")) {
         return 3;
      } else {
         Config.warn("Unknown connect: " + str);
         return 128;
      }
   }

   private static int[] parseInts(String str) {
      if(str == null) {
         return null;
      } else {
         ArrayList list = new ArrayList();
         String[] intStrs = Config.tokenize(str, " ,");

         for(int var9 = 0; var9 < intStrs.length; ++var9) {
            String var10 = intStrs[var9];
            if(var10.contains("-")) {
               String[] var11 = Config.tokenize(var10, "-");
               if(var11.length != 2) {
                  Config.warn("Invalid interval: " + var10 + ", when parsing: " + str);
               } else {
                  int min = Config.parseInt(var11[0], -1);
                  int max = Config.parseInt(var11[1], -1);
                  if(min >= 0 && max >= 0 && min <= max) {
                     for(int n = min; n <= max; ++n) {
                        list.add(Integer.valueOf(n));
                     }
                  } else {
                     Config.warn("Invalid interval: " + var10 + ", when parsing: " + str);
                  }
               }
            } else {
               int var111 = Config.parseInt(var10, -1);
               if(var111 < 0) {
                  Config.warn("Invalid number: " + var10 + ", when parsing: " + str);
               } else {
                  list.add(Integer.valueOf(var111));
               }
            }
         }

         int[] var91 = new int[list.size()];

         for(int var101 = 0; var101 < var91.length; ++var101) {
            var91[var101] = ((Integer)list.get(var101)).intValue();
         }

         return var91;
      }
   }

   private static int[] parseBlockIds(String str) {
      if(str == null) {
         return null;
      } else {
         ArrayList list = new ArrayList();
         String[] intStrs = Config.tokenize(str, " ,");

         for(int var9 = 0; var9 < intStrs.length; ++var9) {
            String var10 = intStrs[var9];
            if(var10.contains("-")) {
               String[] var11 = Config.tokenize(var10, "-");
               if(var11.length != 2) {
                  Config.warn("Invalid interval: " + var10 + ", when parsing: " + str);
               } else {
                  int min = parseBlockId(var11[0]);
                  int max = parseBlockId(var11[1]);
                  if(min >= 0 && max >= 0 && min <= max) {
                     for(int n = min; n <= max; ++n) {
                        list.add(Integer.valueOf(n));
                     }
                  } else {
                     Config.warn("Invalid interval: " + var10 + ", when parsing: " + str);
                  }
               }
            } else {
               int var111 = parseBlockId(var10);
               if(var111 < 0) {
                  Config.warn("Invalid block ID: " + var10 + ", when parsing: " + str);
               } else {
                  list.add(Integer.valueOf(var111));
               }
            }
         }

         int[] var91 = new int[list.size()];

         for(int var101 = 0; var101 < var91.length; ++var101) {
            var91[var101] = ((Integer)list.get(var101)).intValue();
         }

         return var91;
      }
   }

   private static int parseBlockId(String blockStr) {
      int val = Config.parseInt(blockStr, -1);
      if(val >= 0) {
         return val;
      } else {
         Block block = Block.getBlockFromName(blockStr);
         return block != null?Block.getIdFromBlock(block):-1;
      }
   }

   private static int parseMethod(String str) {
      if(str == null) {
         return 1;
      } else if(!str.equals("ctm") && !str.equals("glass")) {
         if(!str.equals("horizontal") && !str.equals("bookshelf")) {
            if(str.equals("vertical")) {
               return 6;
            } else if(str.equals("top")) {
               return 3;
            } else if(str.equals("random")) {
               return 4;
            } else if(str.equals("repeat")) {
               return 5;
            } else if(str.equals("fixed")) {
               return 7;
            } else if(!str.equals("horizontal+vertical") && !str.equals("h+v")) {
               if(!str.equals("vertical+horizontal") && !str.equals("v+h")) {
                  Config.warn("Unknown method: " + str);
                  return 0;
               } else {
                  return 9;
               }
            } else {
               return 8;
            }
         } else {
            return 2;
         }
      } else {
         return 1;
      }
   }

   public boolean isValid(String path) {
      if(this.name != null && this.name.length() > 0) {
         if(this.basePath == null) {
            Config.warn("No base path found: " + path);
            return false;
         } else {
            if(this.matchBlocks == null) {
               this.matchBlocks = this.detectMatchBlocks();
            }

            if(this.matchTiles == null && this.matchBlocks == null) {
               this.matchTiles = this.detectMatchTiles();
            }

            if(this.matchBlocks == null && this.matchTiles == null) {
               Config.warn("No matchBlocks or matchTiles specified: " + path);
               return false;
            } else if(this.method == 0) {
               Config.warn("No method: " + path);
               return false;
            } else if(this.tiles != null && this.tiles.length > 0) {
               if(this.connect == 0) {
                  this.connect = this.detectConnect();
               }

               if(this.connect == 128) {
                  Config.warn("Invalid connect in: " + path);
                  return false;
               } else if(this.renderPass > 0) {
                  Config.warn("Render pass not supported: " + this.renderPass);
                  return false;
               } else if((this.faces & 128) != 0) {
                  Config.warn("Invalid faces in: " + path);
                  return false;
               } else if((this.symmetry & 128) != 0) {
                  Config.warn("Invalid symmetry in: " + path);
                  return false;
               } else {
                  switch(this.method) {
                  case 1:
                     return this.isValidCtm(path);
                  case 2:
                     return this.isValidHorizontal(path);
                  case 3:
                     return this.isValidTop(path);
                  case 4:
                     return this.isValidRandom(path);
                  case 5:
                     return this.isValidRepeat(path);
                  case 6:
                     return this.isValidVertical(path);
                  case 7:
                     return this.isValidFixed(path);
                  case 8:
                     return this.isValidHorizontalVertical(path);
                  case 9:
                     return this.isValidVerticalHorizontal(path);
                  default:
                     Config.warn("Unknown method: " + path);
                     return false;
                  }
               }
            } else {
               Config.warn("No tiles specified: " + path);
               return false;
            }
         }
      } else {
         Config.warn("No name found: " + path);
         return false;
      }
   }

   private int detectConnect() {
      return this.matchBlocks != null?1:(this.matchTiles != null?2:128);
   }

   private int[] detectMatchBlocks() {
      if(!this.name.startsWith("block")) {
         return null;
      } else {
         int startPos = "block".length();

         int pos;
         for(pos = startPos; pos < this.name.length(); ++pos) {
            char var5 = this.name.charAt(pos);
            if(var5 < 48 || var5 > 57) {
               break;
            }
         }

         if(pos == startPos) {
            return null;
         } else {
            String var51 = this.name.substring(startPos, pos);
            int id = Config.parseInt(var51, -1);
            return id < 0?null:new int[]{id};
         }
      }
   }

   private String[] detectMatchTiles() {
      IIcon icon = getIcon(this.name);
      return icon == null?null:new String[]{this.name};
   }

   private static IIcon getIcon(String iconName) {
      return TextureMap.textureMapBlocks.getIconSafe(iconName);
   }

   private boolean isValidCtm(String path) {
      if(this.tiles == null) {
         this.tiles = this.parseTileNames("0-11 16-27 32-43 48-58");
      }

      if(this.tiles.length < 47) {
         Config.warn("Invalid tiles, must be at least 47: " + path);
         return false;
      } else {
         return true;
      }
   }

   private boolean isValidHorizontal(String path) {
      if(this.tiles == null) {
         this.tiles = this.parseTileNames("12-15");
      }

      if(this.tiles.length != 4) {
         Config.warn("Invalid tiles, must be exactly 4: " + path);
         return false;
      } else {
         return true;
      }
   }

   private boolean isValidVertical(String path) {
      if(this.tiles == null) {
         Config.warn("No tiles defined for vertical: " + path);
         return false;
      } else if(this.tiles.length != 4) {
         Config.warn("Invalid tiles, must be exactly 4: " + path);
         return false;
      } else {
         return true;
      }
   }

   private boolean isValidHorizontalVertical(String path) {
      if(this.tiles == null) {
         Config.warn("No tiles defined for horizontal+vertical: " + path);
         return false;
      } else if(this.tiles.length != 7) {
         Config.warn("Invalid tiles, must be exactly 7: " + path);
         return false;
      } else {
         return true;
      }
   }

   private boolean isValidVerticalHorizontal(String path) {
      if(this.tiles == null) {
         Config.warn("No tiles defined for vertical+horizontal: " + path);
         return false;
      } else if(this.tiles.length != 7) {
         Config.warn("Invalid tiles, must be exactly 7: " + path);
         return false;
      } else {
         return true;
      }
   }

   private boolean isValidRandom(String path) {
      if(this.tiles != null && this.tiles.length > 0) {
         if(this.weights != null) {
            int[] sum;
            if(this.weights.length > this.tiles.length) {
               Config.warn("More weights defined than tiles, trimming weights: " + path);
               sum = new int[this.tiles.length];
               System.arraycopy(this.weights, 0, sum, 0, sum.length);
               this.weights = sum;
            }

            int i;
            int var5;
            if(this.weights.length < this.tiles.length) {
               Config.warn("Less weights defined than tiles, expanding weights: " + path);
               sum = new int[this.tiles.length];
               System.arraycopy(this.weights, 0, sum, 0, this.weights.length);
               i = this.getAverage(this.weights);

               for(var5 = this.weights.length; var5 < sum.length; ++var5) {
                  sum[var5] = i;
               }

               this.weights = sum;
            }

            this.sumWeights = new int[this.weights.length];
            var5 = 0;

            for(i = 0; i < this.weights.length; ++i) {
               var5 += this.weights[i];
               this.sumWeights[i] = var5;
            }

            this.sumAllWeights = var5;
            if(this.sumAllWeights <= 0) {
               Config.warn("Invalid sum of all weights: " + var5);
               this.sumAllWeights = 1;
            }
         }

         return true;
      } else {
         Config.warn("Tiles not defined: " + path);
         return false;
      }
   }

   private int getAverage(int[] vals) {
      if(vals.length <= 0) {
         return 0;
      } else {
         int sum = 0;

         int avg;
         for(avg = 0; avg < vals.length; ++avg) {
            int val = vals[avg];
            sum += val;
         }

         avg = sum / vals.length;
         return avg;
      }
   }

   private boolean isValidRepeat(String path) {
      if(this.tiles == null) {
         Config.warn("Tiles not defined: " + path);
         return false;
      } else if(this.width > 0 && this.width <= 16) {
         if(this.height > 0 && this.height <= 16) {
            if(this.tiles.length != this.width * this.height) {
               Config.warn("Number of tiles does not equal width x height: " + path);
               return false;
            } else {
               return true;
            }
         } else {
            Config.warn("Invalid height: " + path);
            return false;
         }
      } else {
         Config.warn("Invalid width: " + path);
         return false;
      }
   }

   private boolean isValidFixed(String path) {
      if(this.tiles == null) {
         Config.warn("Tiles not defined: " + path);
         return false;
      } else if(this.tiles.length != 1) {
         Config.warn("Number of tiles should be 1 for method: fixed.");
         return false;
      } else {
         return true;
      }
   }

   private boolean isValidTop(String path) {
      if(this.tiles == null) {
         this.tiles = this.parseTileNames("66");
      }

      if(this.tiles.length != 1) {
         Config.warn("Invalid tiles, must be exactly 1: " + path);
         return false;
      } else {
         return true;
      }
   }

   public void updateIcons(TextureMap textureMap) {
      if(this.matchTiles != null) {
         this.matchTileIcons = registerIcons(this.matchTiles, textureMap);
      }

      if(this.tiles != null) {
         this.tileIcons = registerIcons(this.tiles, textureMap);
      }
   }

   private static IIcon[] registerIcons(String[] tileNames, TextureMap textureMap) {
      if(tileNames == null) {
         return null;
      } else {
         ArrayList iconList = new ArrayList();

         for(int var10 = 0; var10 < tileNames.length; ++var10) {
            String iconName = tileNames[var10];
            String fullName = iconName;
            if(!iconName.contains("/")) {
               fullName = "textures/blocks/" + iconName;
            }

            String fileName = fullName + ".png";
            ResourceLocation loc = new ResourceLocation(fileName);
            boolean exists = Config.hasResource(loc);
            if(!exists) {
               Config.warn("File not found: " + fileName);
            }

            IIcon icon = textureMap.registerIcon(iconName);
            iconList.add(icon);
         }

         IIcon[] var101 = (IIcon[])((IIcon[])((IIcon[])iconList.toArray(new IIcon[iconList.size()])));
         return var101;
      }
   }

   public String toString() {
      return "CTM name: " + this.name + ", basePath: " + this.basePath + ", matchBlocks: " + Config.arrayToString(this.matchBlocks) + ", matchTiles: " + Config.arrayToString((Object[])this.matchTiles);
   }
}
