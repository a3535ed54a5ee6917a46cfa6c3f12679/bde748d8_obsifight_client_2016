package reifnsk.minimap;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;

class BlockAccess implements IBlockAccess {
   Block block;
   TileEntity blockTileEntity;
   int lightBrightnessForSkyBlocks;
   int blockMetadata;
   WorldChunkManager worldChunkManager;
   int worldHeight = 256;

   public Block getBlock(int i, int j, int k) {
      return this.block;
   }

   public TileEntity getTileEntity(int i, int j, int k) {
      return this.blockTileEntity;
   }

   public int getLightBrightnessForSkyBlocks(int i, int j, int k, int l) {
      return this.lightBrightnessForSkyBlocks;
   }

   public int getBlockMetadata(int i, int j, int k) {
      return this.blockMetadata;
   }

   public boolean isAirBlock(int var1, int var2, int var3) {
      return this.block.getLightOpacity() == 0;
   }

   public BiomeGenBase getBiomeGenForCoords(int i, int j) {
      return null;
   }

   public int getHeight() {
      return this.worldHeight;
   }

   public boolean extendedLevelsInChunkCache() {
      return false;
   }

   public int isBlockProvidingPowerTo(int var1, int var2, int var3, int var4) {
      return 0;
   }
}
