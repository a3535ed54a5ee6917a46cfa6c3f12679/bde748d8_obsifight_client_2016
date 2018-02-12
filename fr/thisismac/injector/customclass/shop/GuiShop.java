package fr.thisismac.injector.customclass.shop;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class GuiShop extends GuiScreen {
   private static ResourceLocation image = new ResourceLocation("textures/custom/achat.png");
   private int guiTop;
   private int guiLeft;
   private int middleScreenX;
   private int middleScreenY;
   private static GuiTextField quant;
   private static GuiTextField prix;
   private static GuiTextField montant;
   private static GuiTextField pseudo;
   private static GuiTextField pf;
   public static boolean flag_inv = false;
   public static boolean flag_money = false;
   private RenderItem itemRenderer = new RenderItem();
   private int currentSlot = -1;
   private List<ItemStack> inv = new ArrayList();
   private String error = null;
   private long timeError = 0L;
   private long periodeError = 5000L;
   private int xSize = 256;
   private int ySize = 256;
   public String i;
   public String buttonList;
   public String optionsBackground;
   public static String p;
   public String id;
   public String meta;
   GuiTextField text;
   public ItemStack stack;
   GuiButton width;

   public GuiShop(Minecraft minecraft, String item, String nombre, String balance, String prix) {
      mc = minecraft;
      this.xSize = 256;
      this.ySize = 256;
      this.i = item;
      String[] met = this.i.split(":");
      this.id = met[0];
      this.meta = met[1];
      this.buttonList = nombre;
      this.optionsBackground = balance;
      p = prix;
   }

   public void initGui() {
      super.initGui();
      int i = this.height / 4 + 100;
      int j = this.height / 4 + 140;
      Keyboard.enableRepeatEvents(true);
      quant = new GuiTextField(this.fontRendererObj, this.width / 2 - 100, this.height / 2 + 20, 70, 15);
      this.initTextField(quant, "1");
      quant.func_146189_e(true);
      prix = new GuiTextField(this.fontRendererObj, this.width / 2 + 30, this.height / 2 + 20, 70, 15);
      this.initTextField(prix, p);
      prix.func_146189_e(true);
      pseudo = new GuiTextField(this.fontRendererObj, this.width / 2 + 40, this.height / 2 + 81, 70, 15);
      this.initTextField(pseudo, "A qui ?");
      pseudo.func_146189_e(true);
      montant = new GuiTextField(this.fontRendererObj, this.width / 2 + 20, this.height / 2 + 60, 70, 15);
      this.initTextField(montant, "Combien ?");
      montant.func_146189_e(true);
      this.buttonList.add(this.width = new GuiButton(0, this.width / 2 + 20, this.height / 2 + 75, 100, 20, "Acheter"));
   }

   private void initTextField(GuiTextField obj, String text) {
      obj.setText(text);
   }

   public void onGuiClosed() {
      super.onGuiClosed();
      Keyboard.enableRepeatEvents(false);
   }

   public void updateScreen() {
      super.updateScreen();
      updatePrix(Double.parseDouble(p));
      if(this.error != null && System.currentTimeMillis() - this.timeError > this.periodeError) {
         this.error = null;
      }
   }

   protected void mouseClicked(int mouseX, int mouseY, int button) {
      boolean pFocus = quant.isFocused();
      boolean tFocus = prix.isFocused();
      boolean fpseudo = pseudo.isFocused();
      boolean fmontant = montant.isFocused();
      super.mouseClicked(mouseX, mouseY, button);
      quant.mouseClicked(mouseX, mouseY, button);
      prix.mouseClicked(mouseX, mouseY, button);
      if(!pFocus && quant.isFocused() && quant.getText().equals("0")) {
         quant.setText("");
      }

      if(!quant.isFocused() && quant.getText().equals("")) {
         quant.setText("1");
      }

      if(!tFocus && prix.isFocused() && prix.getText().equals("0")) {
         prix.setText("");
      }

      if(!prix.isFocused() && prix.getText().equals("")) {
         prix.setText("0");
      }

      if(quant.isFocused() && quant.equals("")) {
         quant.setText("1");
      }

      if(!fmontant && pseudo.isFocused() && montant.getText().equals("0")) {
         montant.setText("Combien ?");
      }

      if(!montant.isFocused() && montant.getText().equals("")) {
         montant.setText("Combien ?");
      }

      if(montant.isFocused()) {
         montant.setText("0");
      }
   }

   protected void keyTyped(char c, int val) {
      if(!quant.isFocused() && !prix.isFocused() && !montant.isFocused() && !pseudo.isFocused()) {
         super.keyTyped(c, val);
      } else {
         pseudo.textboxKeyTyped(c, val);
         if(c >= 48 && c <= 57 || c == 46 || val == 14) {
            quant.textboxKeyTyped(c, val);
            montant.textboxKeyTyped(c, val);
         }
      }
   }

   public void drawScreen(int i, int j, float f) {
      int displayX = (this.width - this.xSize) / 2;
      int displayY = (this.height - this.ySize) / 2;
      GL11.glDisable(GL11.GL_LIGHTING);
      GL11.glDisable(GL11.GL_DEPTH_TEST);
      GL11.glColor3f(1.0F, 1.0F, 1.0F);
      this.drawDefaultBackground();
      this.drawBackgroundImage();
      quant.drawTextBox();
      prix.drawTextBox();
      super.drawScreen(i, j, f);
      GL11.glEnable(GL11.GL_LIGHTING);
      GL11.glEnable(GL11.GL_DEPTH_TEST);
   }

   private int getStringWidth(String str) {
      return this.fontRendererObj.getStringWidth(str);
   }

   private int getInt(GuiTextField tf) {
      int value = 0;

      try {
         value = Integer.parseInt(tf.getText());
      } catch (NumberFormatException var4) {
         var4.printStackTrace();
      }

      return value;
   }

   protected void drawBackgroundImage() {
      int displayX = (this.width - this.xSize) / 2;
      int displayY = (this.height - this.ySize) / 2;
      mc.getTextureManager().bindTexture(new ResourceLocation("textures/custom/achat.png"));
      this.drawTexturedModalRect(displayX, displayY, 0, 0, 256, 256);
      String nom = "";
      ItemStack item = new ItemStack(Item.getItemById(Integer.valueOf(this.id).intValue()), 1, Short.valueOf(this.meta).shortValue());
      if(item != null) {
         this.stack = item;
         nom = item.getDisplayName();
      }

      this.drawString(this.fontRendererObj, nom.replace("tile.", ""), this.width / 2 - 8, this.height / 2 - 88, 1111111);
      this.drawString(this.fontRendererObj, "$" + p + "/Unit\u00e9", this.width / 2 - 6, this.height / 2 - 62, 1111111);
      Keyboard.enableRepeatEvents(true);
      this.text = new GuiTextField(this.fontRendererObj, this.width / 2 - 110, this.height / 2 + 30, 89, 25);
      this.text.setText("0");
      this.text.func_146189_e(true);
      this.text.func_146205_d(false);
      this.text.setFocused(true);
      this.text.func_146185_a(true);
      this.text.func_146193_g(111111);
      this.drawString(this.fontRendererObj, this.optionsBackground + "$", this.width / 2 - 80, this.height / 2 + 80, 1111111);
      int qq = quant.getText().equals("")?1:Integer.parseInt(quant.getText());
      double aa = Double.parseDouble(this.optionsBackground);
      double zz = Double.parseDouble(prix.getText());
      this.updateButton(qq, aa, zz);
   }

   private void renderItem(ItemStack stack, int p_73832_2_, int p_73832_3_, float p_73832_4_) {
      if(stack != null) {
         float var6 = (float)stack.animationsToGo - p_73832_4_;
         if(var6 > 0.0F) {
            GL11.glPushMatrix();
            float var7 = 1.0F + var6 / 5.0F;
            GL11.glTranslatef((float)(p_73832_2_ + 8), (float)(p_73832_3_ + 12), 0.0F);
            GL11.glScalef(1.0F / var7, (var7 + 1.0F) / 2.0F, 1.0F);
            GL11.glTranslatef((float)(-(p_73832_2_ + 8)), (float)(-(p_73832_3_ + 12)), 0.0F);
         }

         stack.addEnchantment(Enchantment.aquaAffinity, 0);
         this.itemRenderer.renderItemAndEffectIntoGUI(mc.fontRenderer, mc.getTextureManager(), stack, p_73832_2_, p_73832_3_);
         if(var6 > 0.0F) {
            GL11.glPopMatrix();
         }
      }
   }

   synchronized void updateButton(final int amount, final double bal, final double prix) {
      System.err.println("prix: " + prix + ", bal:" + bal);
      Thread t = new Thread() {
         public void run() {
            if(Util.canTakeThisAmount(amount) == -1) {
               GuiShop.flag_inv = true;
            }

            if(Util.canTakeThisAmount(amount) > 0) {
               GuiShop.flag_inv = false;
            }

            if((int)prix > (int)bal) {
               GuiShop.flag_money = false;
            } else {
               GuiShop.flag_money = true;
            }

            System.err.println("Money: " + GuiShop.flag_money);
            System.err.println("Inv: " + GuiShop.flag_inv + " === " + Util.canTakeThisAmount(amount));
            if(GuiShop.flag_money && GuiShop.flag_inv) {
               GuiShop.this.width.enabled = true;
            } else {
               GuiShop.this.width.enabled = false;
            }
         }
      };
      t.start();
   }

   static synchronized void updatePrix(final double nb) {
      Thread thread = new Thread() {
         public void run() {
            String xda = GuiShop.quant.getText().equals("")?"1":GuiShop.quant.getText();
            int nombre_item = Integer.parseInt(xda);
            double pFinal = (double)Math.round((double)nombre_item * nb);
            if(pFinal > 9999999.0D) {
               GuiShop.prix.setText("Trop haut.");
               GuiShop.quant.setText("0");
            }

            GuiShop.prix.setText(pFinal + "");
         }
      };
      thread.start();
   }

   public void actionPerformed(GuiButton e) {
      if(e.id == 0) {
         String fPrix = prix.getText();
         String nombre = quant.getText();
         String balance = this.optionsBackground;
         if(quant.getText() == "0") {
            return;
         }

         if(Double.parseDouble(fPrix) > Double.parseDouble(balance.replace(" ", ""))) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(ChatFormatting.RED + "Vous n\'avez pas assez d\'argent."));
            return;
         }

         Minecraft.getMinecraft().thePlayer.sendChatMessage("/obsishop confirm " + nombre);
         Minecraft.getMinecraft().displayGuiScreen((GuiScreen)null);
      }

      if(e.id == 1) {
         if(montant.getText().startsWith("0")) {
            return;
         }

         if(montant.getText().equals("Combien ?") || montant.getText().equals("0")) {
            return;
         }

         if(pseudo.getText().equals("A qui ?") || pseudo.getText().equals("0")) {
            return;
         }

         if(Integer.parseInt(this.optionsBackground) <= Integer.parseInt(montant.getText())) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(ChatFormatting.RED + "Vous n\'avez pas assez d\'argent."));
            return;
         }

         Minecraft.getMinecraft().thePlayer.sendChatMessage("/pay " + pseudo.getText() + " " + montant.getText());
      }
   }
}
