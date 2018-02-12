package reifnsk.minimap;

import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;

public class WaypointEntity extends Entity {
   private final Minecraft mc;
   private ArrayList<Object> unloadedEntity;

   public WaypointEntity(Minecraft mc) {
      super(mc.theWorld);
      this.mc = mc;
      this.setSize(0.0F, 0.0F);
      this.ignoreFrustumCheck = true;
      this.onUpdate();
   }

   public void onUpdate() {
      this.setPosition(this.mc.thePlayer.posX, this.mc.thePlayer.posY, this.mc.thePlayer.posZ);
   }

   protected void entityInit() {}

   public boolean isInRangeToRenderDist(double d) {
      return true;
   }

   protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {}

   protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {}
}
