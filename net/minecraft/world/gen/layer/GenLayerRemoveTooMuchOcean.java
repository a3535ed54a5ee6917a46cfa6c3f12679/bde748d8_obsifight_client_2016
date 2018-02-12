package net.minecraft.world.gen.layer;

public class GenLayerRemoveTooMuchOcean extends GenLayer {
   private static final String __OBFID = "CL_00000564";

   public GenLayerRemoveTooMuchOcean(long p_i45480_1_, GenLayer p_i45480_3_) {
      super(p_i45480_1_);
      this.parent = p_i45480_3_;
   }

   public int[] getInts(int p_75904_1_, int p_75904_2_, int p_75904_3_, int p_75904_4_) {
      int var5 = p_75904_1_ - 1;
      int var6 = p_75904_2_ - 1;
      int var7 = p_75904_3_ + 2;
      int var8 = p_75904_4_ + 2;
      int[] var9 = this.parent.getInts(var5, var6, var7, var8);
      int[] var10 = IntCache.getIntCache(p_75904_3_ * p_75904_4_);

      for(int var11 = 0; var11 < p_75904_4_; ++var11) {
         for(int var12 = 0; var12 < p_75904_3_; ++var12) {
            int var13 = var9[var12 + 1 + (var11 + 1 - 1) * (p_75904_3_ + 2)];
            int var14 = var9[var12 + 1 + 1 + (var11 + 1) * (p_75904_3_ + 2)];
            int var15 = var9[var12 + 1 - 1 + (var11 + 1) * (p_75904_3_ + 2)];
            int var16 = var9[var12 + 1 + (var11 + 1 + 1) * (p_75904_3_ + 2)];
            int var17 = var9[var12 + 1 + (var11 + 1) * var7];
            var10[var12 + var11 * p_75904_3_] = var17;
            this.initChunkSeed((long)(var12 + p_75904_1_), (long)(var11 + p_75904_2_));
            if(var17 == 0 && var13 == 0 && var14 == 0 && var15 == 0 && var16 == 0 && this.nextInt(2) == 0) {
               var10[var12 + var11 * p_75904_3_] = 1;
            }
         }
      }

      return var10;
   }
}
