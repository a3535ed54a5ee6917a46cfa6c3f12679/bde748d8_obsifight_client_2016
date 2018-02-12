package fr.obsifight.gui.crafts;

import java.util.Iterator;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class GuiCraft extends InventoryEffectRenderer {
   private static InventoryBasic GuiCraft_9r4y62p982 = new InventoryBasic("tmp", true, 45);
   private static int GuiCraft_4h40n7r1sn = CreativeTabs.tabBlock.getTabIndex();
   GuiButton GuiCraft_k4139752p4;
   GuiButton GuiCraft_50r7q6nib5;
   GuiButton GuiCraft_u99qx6ems4;
   GuiButton GuiCraft_nuua6ab78u;
   public GuiRecipesContainer contCrafts;
   private float GuiCraft_hpnl6k6595 = 0.0F;
   private boolean GuiCraft_13ohr344z5 = false;
   private int GuiCraft_eo2mw98c05;
   private boolean GuiCraft_6shc73dz6b;
   private GuiTextField GuiCraft_2fa7lq054s;
   private boolean GuiCraft_12h61v8k98 = false;
   public int GuiCraft_3ngkt2n947;
   public boolean furnace;
   public int GuiCraft_070830nbcq;
   public boolean GuiCraft_361t7b020o;
   public int GuiCraft_7vxp2u3242;
   public int GuiCraft_7957up5a5m;
   private int GuiCraft_792o5ppyh3;
   public int base;
   private EntityPlayer GuiCraft_6446q2m22x;

   public GuiCraft(EntityPlayer var1) {
      super(new GuiRecipesContainer(var1));
      var1.contCrafts = (GuiRecipesContainer)this.field_147002_h;
      this.field_146291_p = false;
      this.field_147000_g = 190;
      this.field_146999_f = 195;
      this.GuiCraft_6446q2m22x = var1;
      this.GuiCraft_eo2mw98c05 = 25;
      this.contCrafts = var1.contCrafts;
      this.GuiCraft_3ngkt2n947 = 0;
      this.furnace = true;
      this.GuiCraft_070830nbcq = 0;
      this.GuiCraft_361t7b020o = true;
      this.GuiCraft_7vxp2u3242 = 0;
      this.GuiCraft_7957up5a5m = 0;
      this.GuiCraft_792o5ppyh3 = 0;
      this.base = 0;
   }

   public void updateScreen() {
      if(!this.GuiCraft_6446q2m22x.isEntityAlive() || this.GuiCraft_6446q2m22x.isDead) {
         this.GuiCraft_6446q2m22x.closeScreen();
      }

      if(!this.furnace) {
         this.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_3cirau7j5p();
         this.furnace = true;
      }

      if(this.contCrafts.GuiCraft_sgkr6x0s4q.GuiCraft_9h444i05z9() != null) {
         if(this.GuiCraft_3ngkt2n947 >= 50) {
            this.GuiCraft_3ngkt2n947 = 0;
         }

         ++this.GuiCraft_3ngkt2n947;
         if(this.GuiCraft_7vxp2u3242 >= 425) {
            this.GuiCraft_7vxp2u3242 = 1;
         }

         ++this.GuiCraft_7vxp2u3242;
         if(this.GuiCraft_792o5ppyh3 >= 45) {
            this.GuiCraft_792o5ppyh3 = 0;
         }

         ++this.GuiCraft_792o5ppyh3;
         if(this.base >= 200) {
            this.base = 0;
         }

         ++this.base;
         this.GuiCraft_7957up5a5m = 0;
         ItemStack var1 = this.contCrafts.GuiCraft_sgkr6x0s4q.GuiCraft_9h444i05z9();
         GuiRecipesList.GuiCraft_76646zc22d(this, var1);
      }
   }

   protected void func_146984_a(Slot var1, int var2, int var3, int var4) {
      this.GuiCraft_12h61v8k98 = true;
      this.GuiCraft_361t7b020o = true;
      this.GuiCraft_7vxp2u3242 = 0;
      if(!this.furnace) {
         this.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_3cirau7j5p();
         this.furnace = true;
      }

      ItemStack var5;
      GuiRecipesInventory var6;
      if(var1 != null) {
         if(var1.inventory == GuiCraft_9r4y62p982) {
            var6 = this.contCrafts.GuiCraft_sgkr6x0s4q;
            var5 = var6.GuiCraft_0l2z6vr0w3();
            ItemStack var8 = var1.getStack();
            boolean var9;
            if(var8 != null && var5 == null) {
               var9 = false;
               if(!var9) {
                  var6.GuiCraft_g75m285sa8(ItemStack.copyItemStack(var8));
                  var5 = var6.GuiCraft_0l2z6vr0w3();
                  if(var4 == 2) {
                     var5.stackSize = var5.getMaxStackSize();
                  }
               }
            } else {
               var9 = false;
               if(!var9) {
                  var6.GuiCraft_g75m285sa8(ItemStack.copyItemStack(var8));
                  var5 = var6.GuiCraft_0l2z6vr0w3();
                  if(var4 == 3) {
                     var5.stackSize = var5.getMaxStackSize();
                  }
               }
            }
         }
      } else {
         var6 = this.contCrafts.GuiCraft_sgkr6x0s4q;
         if(var6.GuiCraft_0l2z6vr0w3() != null) {
            if(var3 == 0) {
               mc.playerController.sendPacketDropItem(var6.GuiCraft_0l2z6vr0w3());
               var6.GuiCraft_g75m285sa8((ItemStack)null);
            }

            if(var3 == 1) {
               var5 = var6.GuiCraft_0l2z6vr0w3().splitStack(1);
               mc.playerController.sendPacketDropItem(var5);
               if(var6.GuiCraft_0l2z6vr0w3().stackSize == 0) {
                  var6.GuiCraft_g75m285sa8((ItemStack)null);
               }
            }
         }
      }
   }

   public void initGui() {
      super.initGui();
      int var1 = (this.width - this.field_146999_f) / 2;
      int var2 = (this.height - this.field_147000_g) / 2;
      this.buttonList.clear();
      Keyboard.enableRepeatEvents(true);
      this.GuiCraft_2fa7lq054s = new GuiTextField(this.fontRendererObj, var1 + 80, var2 + 5, 89, this.fontRendererObj.FONT_HEIGHT + 1);
      this.GuiCraft_2fa7lq054s.func_146203_f(15);
      this.GuiCraft_2fa7lq054s.func_146185_a(false);
      this.GuiCraft_2fa7lq054s.func_146189_e(true);
      this.GuiCraft_2fa7lq054s.func_146193_g(16777215);
      this.GuiCraft_76646zc22d(CreativeTabs.tabAllSearch);
   }

   public void onGuiClosed() {
      super.onGuiClosed();
      Keyboard.enableRepeatEvents(false);
   }

   protected void keyTyped(char var1, int var2) {
      if(this.GuiCraft_12h61v8k98) {
         this.GuiCraft_12h61v8k98 = false;
         this.GuiCraft_2fa7lq054s.setText("");
      }

      if(this.GuiCraft_2fa7lq054s.textboxKeyTyped(var1, var2)) {
         this.GuiCraft_c39249l556();
      } else {
         super.keyTyped(var1, var2);
      }
   }

   private void GuiCraft_c39249l556() {
      GuiRecipesContainer var1 = (GuiRecipesContainer)this.field_147002_h;
      var1.GuiCraft_1eyc9rub2y.clear();
      Iterator var2 = Item.itemRegistry.iterator();

      while(var2.hasNext()) {
         Item var9 = (Item)var2.next();
         if(var9 != null && var9.getCreativeTab() != null) {
            var9.getSubItems(var9, (CreativeTabs)null, var1.GuiCraft_1eyc9rub2y);
         }
      }

      Iterator var91 = var1.GuiCraft_1eyc9rub2y.iterator();
      String var4 = this.GuiCraft_2fa7lq054s.getText().toLowerCase();

      while(var91.hasNext()) {
         ItemStack var5 = (ItemStack)var91.next();
         boolean var6 = false;
         Iterator var7 = var5.getTooltip(mc.thePlayer, mc.gameSettings.advancedItemTooltips).iterator();

         while(true) {
            if(var7.hasNext()) {
               String var8 = (String)var7.next();
               if(!var8.toLowerCase().contains(var4)) {
                  continue;
               }

               var6 = true;
            }

            if(!var6) {
               var91.remove();
            }
            break;
         }
      }

      this.GuiCraft_hpnl6k6595 = 0.0F;
      var1.GuiCraft_76646zc22d(0.0F);
   }

   protected void func_146979_b(int var1, int var2) {
      CreativeTabs var3 = CreativeTabs.tabAllSearch;
      if(var3.drawInForegroundOfTab()) {
         this.fontRendererObj.drawString("Recherche: ", 8, 6, 4210752);
      }
   }

   protected void mouseClicked(int var1, int var2, int var3) {
      super.mouseClicked(var1, var2, var3);
   }

   private boolean GuiCraft_3slwya46i3() {
      return GuiCraft_4h40n7r1sn != CreativeTabs.tabInventory.getTabIndex() && CreativeTabs.creativeTabArray[GuiCraft_4h40n7r1sn].shouldHidePlayerInventory() && ((GuiRecipesContainer)this.field_147002_h).GuiCraft_g75m285sa8();
   }

   private void GuiCraft_76646zc22d(CreativeTabs var1) {
      int var2 = GuiCraft_4h40n7r1sn;
      GuiCraft_4h40n7r1sn = var1.getTabIndex();
      GuiRecipesContainer var3 = (GuiRecipesContainer)this.field_147002_h;
      var3.GuiCraft_1eyc9rub2y.clear();
      var1.displayAllReleventItems(var3.GuiCraft_1eyc9rub2y);
      if(this.GuiCraft_2fa7lq054s != null) {
         if(var1 == CreativeTabs.tabAllSearch) {
            this.GuiCraft_2fa7lq054s.func_146189_e(true);
            this.GuiCraft_2fa7lq054s.func_146205_d(false);
            this.GuiCraft_2fa7lq054s.setFocused(true);
            this.GuiCraft_2fa7lq054s.setText("");
            this.GuiCraft_c39249l556();
         } else {
            this.GuiCraft_2fa7lq054s.func_146189_e(false);
            this.GuiCraft_2fa7lq054s.func_146205_d(true);
            this.GuiCraft_2fa7lq054s.setFocused(false);
         }
      }

      this.GuiCraft_hpnl6k6595 = 0.0F;
      var3.GuiCraft_76646zc22d(0.0F);
   }

   public void handleMouseInput() {
      super.handleMouseInput();
      int var1 = Mouse.getEventDWheel();
      if(var1 != 0 && this.GuiCraft_3slwya46i3()) {
         int var2 = ((GuiRecipesContainer)this.field_147002_h).GuiCraft_1eyc9rub2y.size() / 9 - 5 + 1;
         if(var1 > 0) {
            var1 = 1;
         }

         if(var1 < 0) {
            var1 = -1;
         }

         this.GuiCraft_hpnl6k6595 = (float)((double)this.GuiCraft_hpnl6k6595 - (double)var1 / (double)var2);
         if(this.GuiCraft_hpnl6k6595 < 0.0F) {
            this.GuiCraft_hpnl6k6595 = 0.0F;
         }

         if(this.GuiCraft_hpnl6k6595 > 0.78F) {
            this.GuiCraft_hpnl6k6595 = 0.78F;
         }

         ((GuiRecipesContainer)this.field_147002_h).GuiCraft_76646zc22d(this.GuiCraft_hpnl6k6595);
      }
   }

   public void drawScreen(int var1, int var2, float var3) {
      boolean var4 = Mouse.isButtonDown(0);
      int var5 = this.field_147003_i;
      int var6 = this.GuiCraft_eo2mw98c05;
      int var7 = var5 + 175;
      int var8 = var6 + 18;
      int var9 = var7 + 14;
      int var10 = var8 + 112;
      if(!this.GuiCraft_6shc73dz6b && var4 && var1 >= var7 && var2 >= var8 && var1 < var9 && var2 < var10) {
         this.GuiCraft_13ohr344z5 = this.GuiCraft_3slwya46i3();
      }

      if(!var4) {
         this.GuiCraft_13ohr344z5 = false;
      }

      this.GuiCraft_6shc73dz6b = var4;
      if(this.GuiCraft_13ohr344z5) {
         this.GuiCraft_hpnl6k6595 = ((float)(var2 - var8) - 7.5F) / ((float)(var10 - var8) - 15.0F);
         if(this.GuiCraft_hpnl6k6595 < 0.0F) {
            this.GuiCraft_hpnl6k6595 = 0.0F;
         }

         if((double)this.GuiCraft_hpnl6k6595 > 0.78D) {
            this.GuiCraft_hpnl6k6595 = 0.78F;
         }

         ((GuiRecipesContainer)this.field_147002_h).GuiCraft_76646zc22d(this.GuiCraft_hpnl6k6595);
      }

      super.drawScreen(var1, var2, var3);
      GL11.glEnable(GL11.GL_LIGHTING);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glEnable(GL11.GL_LIGHTING);
   }

   protected void func_146976_a(float var1, int var2, int var3) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      RenderHelper.enableGUIStandardItemLighting();
      ResourceLocation var4 = new ResourceLocation("textures/gui/container/creative_inventory/tabs.png");
      CreativeTabs var5 = CreativeTabs.tabAllSearch;
      ResourceLocation var6;
      if(this.GuiCraft_7957up5a5m == 1) {
         var6 = new ResourceLocation("textures/custom/craftsFour.png");
      } else {
         var6 = new ResourceLocation("textures/custom/crafts.png");
      }

      CreativeTabs var7 = CreativeTabs.tabAllSearch;
      CreativeTabs[] var8 = CreativeTabs.creativeTabArray;
      int var9 = var8.length;
      this.GuiCraft_q8o484xbkz(var7);
      this.field_147003_i = (this.width - this.field_146999_f) / 2;
      this.GuiCraft_eo2mw98c05 = (this.height - this.field_147000_g) / 2;
      mc.renderEngine.bindTexture(var6);
      this.drawTexturedModalRect(this.field_147003_i, this.GuiCraft_eo2mw98c05, 0, 0, this.field_146999_f, this.field_147000_g);
      int var11;
      if(this.GuiCraft_7957up5a5m != 0) {
         var11 = this.GuiCraft_792o5ppyh3 / 2;
         this.drawTexturedModalRect(this.field_147003_i + 93, this.GuiCraft_eo2mw98c05 + 139, this.field_146999_f, 15, var11 + 1, 167);
      }

      this.GuiCraft_2fa7lq054s.drawTextBox();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      var11 = this.field_147003_i + 175;
      var9 = this.GuiCraft_eo2mw98c05 + 18;
      int var10 = var9 + 112;
      mc.renderEngine.bindTexture(var4);
      if(var5.shouldHidePlayerInventory()) {
         this.drawTexturedModalRect(var11, var9 + (int)((float)(var10 - var9 - 17) * this.GuiCraft_hpnl6k6595), 232 + (this.GuiCraft_3slwya46i3()?0:12), 0, 12, 15);
      }

      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glEnable(GL11.GL_LIGHTING);
      this.GuiCraft_q8o484xbkz(var5);
   }

   protected boolean GuiCraft_76646zc22d(CreativeTabs var1, int var2, int var3) {
      int var4 = var1.getTabColumn();
      int var5 = 28 * var4;
      byte var6 = 0;
      if(var4 == 5) {
         var5 = this.field_146999_f - 28 + 2;
      } else if(var4 > 0) {
         var5 += var4;
      }

      int var7;
      if(var1.isTabInFirstRow()) {
         var7 = var6 - 32;
      } else {
         var7 = var6 + this.field_147000_g;
      }

      return var2 >= var5 && var2 <= var5 + 28 && var3 >= var7 && var3 <= var7 + 32;
   }

   protected boolean GuiCraft_q8o484xbkz(CreativeTabs var1, int var2, int var3) {
      int var4 = var1.getTabColumn();
      int var5 = 28 * var4;
      byte var6 = 0;
      if(var4 == 5) {
         var5 = this.field_146999_f - 28 + 2;
      } else if(var4 > 0) {
         var5 += var4;
      }

      int var7;
      if(var1.isTabInFirstRow()) {
         var7 = var6 - 32;
      } else {
         var7 = var6 + this.field_147000_g;
      }

      if(this.func_146978_c(var5 + 3, var7 + 3, 23, 27, var2, var3)) {
         this.func_146279_a(var1.getTranslatedTabLabel(), var2, var3);
         return true;
      } else {
         return false;
      }
   }

   protected void GuiCraft_q8o484xbkz(CreativeTabs var1) {
      GL11.glDisable(GL11.GL_LIGHTING);
      this.zLevel = 100.0F;
      itemRender.zLevel = 100.0F;
      GL11.glEnable(GL11.GL_LIGHTING);
      GL11.glEnable(GL12.GL_RESCALE_NORMAL);
      int var2 = (this.width - this.field_146999_f) / 2;
      int var3 = (this.height - this.field_147000_g) / 2;
      ItemStack var4 = new ItemStack(Blocks.crafting_table);
      if(this.GuiCraft_7957up5a5m == 1) {
         var4 = new ItemStack(Blocks.furnace);
         itemRender.renderItemIntoGUI(this.fontRendererObj, mc.renderEngine, var4, var2 + 26, var3 + 139);
         itemRender.renderItemOverlayIntoGUI(this.fontRendererObj, mc.renderEngine, var4, var2 + 26, var3 + 139);
      } else {
         itemRender.renderItemIntoGUI(this.fontRendererObj, mc.renderEngine, var4, var2 + 10, var3 + 139);
         itemRender.renderItemOverlayIntoGUI(this.fontRendererObj, mc.renderEngine, var4, var2 + 10, var3 + 139);
      }

      GL11.glDisable(GL11.GL_LIGHTING);
      itemRender.zLevel = 0.0F;
      this.zLevel = 0.0F;
   }

   protected void actionPerformed(GuiButton var1) {}

   public int GuiCraft_xah90ktn6d() {
      return GuiCraft_4h40n7r1sn;
   }

   public static InventoryBasic GuiCraft_1eyc9rub2y() {
      return GuiCraft_9r4y62p982;
   }

   public ItemStack GuiCraft_sgkr6x0s4q() {
      if(this.GuiCraft_4dzd6huseb() > 21) {
         this.GuiCraft_3ngkt2n947 = this.GuiCraft_7vxp2u3242 = 0;
      }

      ItemStack[] var1 = new ItemStack[]{new ItemStack(Items.coal), new ItemStack(Blocks.planks), new ItemStack(Blocks.planks), new ItemStack(Blocks.wooden_slab), new ItemStack(Blocks.sapling), new ItemStack(Items.wooden_sword), new ItemStack(Blocks.wooden_pressure_plate), new ItemStack(Items.stick), new ItemStack(Blocks.fence), new ItemStack(Blocks.oak_stairs), new ItemStack(Blocks.trapdoor), new ItemStack(Blocks.crafting_table), new ItemStack(Blocks.bookshelf), new ItemStack(Blocks.chest), new ItemStack(Blocks.jukebox), new ItemStack(Blocks.jukebox), new ItemStack(Blocks.brown_mushroom_block), new ItemStack(Blocks.red_mushroom_block), new ItemStack(Items.blaze_rod), new ItemStack(Items.lava_bucket)};
      return var1[this.GuiCraft_4dzd6huseb()];
   }

   public int GuiCraft_z59bsdf26t() {
      byte var1 = 0;
      if(this.GuiCraft_7vxp2u3242 <= 25) {
         var1 = 0;
      }

      if(this.GuiCraft_7vxp2u3242 > 25) {
         var1 = 1;
      }

      if(this.GuiCraft_7vxp2u3242 > 50) {
         var1 = 2;
      }

      if(this.GuiCraft_7vxp2u3242 > 75) {
         var1 = 3;
      }

      if(this.GuiCraft_7vxp2u3242 > 100) {
         var1 = 4;
      }

      if(this.GuiCraft_7vxp2u3242 > 125) {
         var1 = 5;
      }

      if(this.GuiCraft_7vxp2u3242 > 150) {
         var1 = 6;
      }

      if(this.GuiCraft_7vxp2u3242 > 175) {
         var1 = 7;
      }

      if(this.GuiCraft_7vxp2u3242 > 200) {
         var1 = 8;
      }

      if(this.GuiCraft_7vxp2u3242 > 225) {
         var1 = 9;
      }

      if(this.GuiCraft_7vxp2u3242 > 250) {
         var1 = 10;
      }

      if(this.GuiCraft_7vxp2u3242 > 275) {
         var1 = 11;
      }

      if(this.GuiCraft_7vxp2u3242 > 300) {
         var1 = 12;
      }

      if(this.GuiCraft_7vxp2u3242 > 325) {
         var1 = 13;
      }

      if(this.GuiCraft_7vxp2u3242 > 350) {
         var1 = 14;
      }

      if(this.GuiCraft_7vxp2u3242 > 400) {
         var1 = 15;
      }

      return var1;
   }

   private int GuiCraft_4dzd6huseb() {
      byte var1 = 0;
      if(this.GuiCraft_7vxp2u3242 <= 12) {
         var1 = 0;
      }

      if(this.GuiCraft_7vxp2u3242 > 12) {
         var1 = 1;
      }

      if(this.GuiCraft_7vxp2u3242 > 25) {
         var1 = 2;
      }

      if(this.GuiCraft_7vxp2u3242 > 37) {
         var1 = 3;
      }

      if(this.GuiCraft_7vxp2u3242 > 50) {
         var1 = 4;
      }

      if(this.GuiCraft_7vxp2u3242 > 62) {
         var1 = 5;
      }

      if(this.GuiCraft_7vxp2u3242 > 75) {
         var1 = 6;
      }

      if(this.GuiCraft_7vxp2u3242 > 87) {
         var1 = 7;
      }

      if(this.GuiCraft_7vxp2u3242 > 100) {
         var1 = 8;
      }

      if(this.GuiCraft_7vxp2u3242 > 112) {
         var1 = 9;
      }

      if(this.GuiCraft_7vxp2u3242 > 125) {
         var1 = 10;
      }

      if(this.GuiCraft_7vxp2u3242 > 137) {
         var1 = 11;
      }

      if(this.GuiCraft_7vxp2u3242 > 150) {
         var1 = 12;
      }

      if(this.GuiCraft_7vxp2u3242 > 162) {
         var1 = 13;
      }

      if(this.GuiCraft_7vxp2u3242 > 175) {
         var1 = 14;
      }

      if(this.GuiCraft_7vxp2u3242 > 200) {
         var1 = 15;
      }

      if(this.GuiCraft_7vxp2u3242 > 212) {
         var1 = 16;
      }

      if(this.GuiCraft_7vxp2u3242 > 225) {
         var1 = 17;
      }

      if(this.GuiCraft_7vxp2u3242 > 237) {
         var1 = 18;
      }

      if(this.GuiCraft_7vxp2u3242 > 250) {
         var1 = 19;
      }

      if(this.GuiCraft_7vxp2u3242 > 262) {
         var1 = 20;
      }

      if(this.GuiCraft_7vxp2u3242 > 275) {
         var1 = 21;
      }

      if(this.GuiCraft_7vxp2u3242 > 287) {
         var1 = 22;
      }

      if(this.GuiCraft_7vxp2u3242 > 300) {
         var1 = 23;
      }

      if(this.GuiCraft_7vxp2u3242 > 312) {
         var1 = 24;
      }

      if(this.GuiCraft_7vxp2u3242 > 125) {
         var1 = 25;
      }

      if(this.GuiCraft_7vxp2u3242 > 337) {
         var1 = 26;
      }

      if(this.GuiCraft_7vxp2u3242 > 350) {
         var1 = 27;
      }

      if(this.GuiCraft_7vxp2u3242 > 362) {
         var1 = 28;
      }

      if(this.GuiCraft_7vxp2u3242 > 375) {
         var1 = 29;
      }

      return var1;
   }
}
