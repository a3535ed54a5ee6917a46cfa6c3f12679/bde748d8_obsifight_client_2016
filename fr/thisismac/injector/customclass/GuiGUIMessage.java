package fr.thisismac.injector.customclass;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GuiGUIMessage {
   public String message = "";
   public boolean shadow = true;
   public String id = "default";
   public GuiGUIMessage.Anchor anchor;
   public int displayTimer;
   public int elapsedTimer;
   public int y;
   public int yFinal;
   public int x;
   public int xFinal;
   public int renderPass;
   public int renderPassed;
   public float scale;
   public float rotation;
   public ItemStack stack;
   public GuiGUIMessage.ROTATION spin;
   public float spinValue;

   public GuiGUIMessage() {
      this.anchor = GuiGUIMessage.Anchor.DEFAULT;
      this.displayTimer = 90;
      this.elapsedTimer = 0;
      this.y = 0;
      this.yFinal = -999;
      this.x = 0;
      this.xFinal = -999;
      this.renderPass = 0;
      this.renderPassed = 0;
      this.scale = 1.0F;
      this.rotation = 0.0F;
      this.stack = null;
      this.spin = GuiGUIMessage.ROTATION.DEFAULT;
      this.spinValue = 0.0F;
   }

   public void setShadow(boolean shadow) {
      this.shadow = shadow;
   }

   public void setMessage(String message) {
      message = message.replace('&', '\u00a7');
      this.message = message;
   }

   public void setId(String id) {
      this.id = id;
   }

   public void setDisplayTimer(int timer) {
      this.displayTimer = timer;
   }

   public void setAnchor(GuiGUIMessage.Anchor anchor) {
      this.anchor = anchor;
   }

   public void setSpin(GuiGUIMessage.ROTATION spin) {
      this.spin = spin;
   }

   public void setSpinValue(float f) {
      this.spinValue = f;
   }

   public void setX(int x) {
      this.x = x;
   }

   public void setYFinal(int y) {
      this.yFinal = y;
   }

   public void setXFinal(int x) {
      this.xFinal = x;
   }

   public void setY(int y) {
      this.y = y;
   }

   public void setRenderPass(int number) {
      this.renderPass = number;
   }

   public void setScale(float scale) {
      this.scale = scale;
   }

   public void setRotation(float rotation) {
      this.rotation = rotation;
   }

   public void setStack(int id) {
      Item item = Item.getItemById(id);
      Block block = Block.getBlockById(id);
      if(item != null) {
         this.stack = new ItemStack(item);
      } else if(block != null) {
         this.stack = new ItemStack(block);
      }
   }

   public static enum Anchor {
      DEFAULT("DEFAULT", 0, "DEFAULT", 0),
      LEFT("LEFT", 1, "LEFT", 1),
      RIGHT("RIGHT", 2, "RIGHT", 2),
      TOP("TOP", 3, "TOP", 3),
      BOT("BOT", 4, "BOT", 4),
      TOPLEFT("TOPLEFT", 5, "TOPLEFT", 5),
      TOPRIGHT("TOPRIGHT", 6, "TOPRIGHT", 6),
      BOTLEFT("BOTLEFT", 7, "BOTLEFT", 7),
      BOTRIGHT("BOTRIGHT", 8, "BOTRIGHT", 8),
      CENTER("CENTER", 9, "CENTER", 9);
      private static final GuiGUIMessage.Anchor[] $VALUES = new GuiGUIMessage.Anchor[]{DEFAULT, LEFT, RIGHT, TOP, BOT, TOPLEFT, TOPRIGHT, BOTLEFT, BOTRIGHT, CENTER};

      private static final GuiGUIMessage.Anchor[] $VALUES$ = new GuiGUIMessage.Anchor[]{DEFAULT, LEFT, RIGHT, TOP, BOT, TOPLEFT, TOPRIGHT, BOTLEFT, BOTRIGHT, CENTER};

      private Anchor(String var1, int var2, String p_i46435_1_, int p_i46435_2_) {}
   }

   public static enum ROTATION {
      LEFT("LEFT", 0, "LEFT", 0),
      RIGHT("RIGHT", 1, "RIGHT", 1),
      CUSTOMLEFT("CUSTOMLEFT", 2, "CUSTOMLEFT", 2),
      CUSTOMRIGHT("CUSTOMRIGHT", 3, "CUSTOMRIGHT", 3),
      DEFAULT("DEFAULT", 4, "DEFAULT", 4);
      private static final GuiGUIMessage.ROTATION[] $VALUES = new GuiGUIMessage.ROTATION[]{LEFT, RIGHT, CUSTOMLEFT, CUSTOMRIGHT, DEFAULT};

      private static final GuiGUIMessage.ROTATION[] $VALUES$ = new GuiGUIMessage.ROTATION[]{LEFT, RIGHT, CUSTOMLEFT, CUSTOMRIGHT, DEFAULT};

      private ROTATION(String var1, int var2, String p_i46435_1_, int p_i46435_2_) {}
   }
}
