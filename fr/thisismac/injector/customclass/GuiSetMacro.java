package fr.thisismac.injector.customclass;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringTranslate;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class GuiSetMacro extends GuiScreen {
   private static GuiTextField macro1;
   private static GuiTextField macro2;
   private static GuiTextField macro3;
   private static GuiTextField macro4;
   public static String m1;
   public static String m2;
   public static String m3;
   public static String m4;
   private int xSize = 230;
   private int ySize = 162;
   public String macro11;
   public String macro21;
   public String macro31;
   public String macro41;
   protected String screenTitle;
   protected GuiScreen guiScreen;
   private World world;

   public GuiSetMacro(Minecraft minecraft, World theWorld) {
      mc = minecraft;
      this.world = theWorld;
      this.xSize = 256;
      this.ySize = 256;
   }

   public void loadMacro() {
      try {
         if(!mc.gameSettings.optionsFile.exists()) {
            return;
         }

         BufferedReader var9 = new BufferedReader(new FileReader(mc.gameSettings.optionsFile));
         String var2 = "";

         while((var2 = var9.readLine()) != null) {
            try {
               String[] var8 = var2.split(":");
               if(var8[0].equals("macro1")) {
                  this.macro11 = var8.length > 1?var8[1]:var8.length + "";
               }

               if(var8[0].equals("macro2")) {
                  this.macro21 = var8.length > 1?var8[1]:var8.length + "";
               }

               if(var8[0].equals("macro3")) {
                  this.macro31 = var8.length > 1?var8[1]:var8.length + "";
               }

               if(var8[0].equals("macro4")) {
                  this.macro41 = var8.length > 1?var8[1]:var8.length + "";
               }
            } catch (Exception var4) {
               Minecraft.logger.warn("Skipping bad option: " + var2);
            }
         }
      } catch (Exception var5) {
         Minecraft.logger.error("Failed to load options", var5);
      }
   }

   public void initGui() {
      StringTranslate stringtranslate = StringTranslate.getInstance();
      int i = this.height / 4 + 100;
      int j = this.height / 4 + 140;

      try {
         m1 = CustomSettings.getSetting("macro1");
         m2 = CustomSettings.getSetting("macro2");
         m3 = CustomSettings.getSetting("macro3");
         m4 = CustomSettings.getSetting("macro4");
      } catch (IOException var5) {
         var5.printStackTrace();
      }

      macro3 = new GuiTextField(this.fontRendererObj, this.width / 3 + 75, this.height / 4 + 50, 200, 10);
      macro3.setText(m3);
      macro3.func_146189_e(true);
      macro1 = new GuiTextField(this.fontRendererObj, this.width / 3 + 75, this.height / 4, 200, 10);
      macro1.setText(m1);
      macro1.func_146189_e(true);
      macro2 = new GuiTextField(this.fontRendererObj, this.width / 3 + 75, this.height / 4 + 25, 200, 10);
      macro2.setText(m2);
      macro2.func_146189_e(true);
      macro4 = new GuiTextField(this.fontRendererObj, this.width / 3 + 75, this.height / 4 + 75, 200, 10);
      macro4.setText(m4);
      macro4.func_146189_e(true);
      this.buttonList.add(new GuiButton(1, this.width / 2, this.height / 4 + 100, 150, 20, "Termin\u00e9"));
   }

   public void drawScreen(int i, int j, float f) {
      GL11.glDisable(GL11.GL_LIGHTING);
      GL11.glDisable(GL11.GL_DEPTH_TEST);
      GL11.glColor3f(1.0F, 1.0F, 1.0F);
      this.drawDefaultBackground();
      macro1.drawTextBox();
      macro2.drawTextBox();
      macro3.drawTextBox();
      macro4.drawTextBox();
      this.drawCenteredString(this.fontRendererObj, EnumChatFormatting.AQUA + "- Menu Macro -", this.width / 2, this.height / 64, 16777215);
      this.drawCenteredString(this.fontRendererObj, EnumChatFormatting.RED + "" + EnumChatFormatting.BOLD + EnumChatFormatting.UNDERLINE + "/!\\ " + EnumChatFormatting.GOLD + "" + EnumChatFormatting.BOLD + EnumChatFormatting.UNDERLINE + "Ne pas mettre de \'/\' en d\u00e9but de commande." + EnumChatFormatting.RED + "" + EnumChatFormatting.BOLD + EnumChatFormatting.UNDERLINE + " /!\\", this.width / 2, this.height / 20, 16777215);
      this.drawCenteredString(this.fontRendererObj, EnumChatFormatting.AQUA + "> Macro 1: ", this.width / 3 + 40, this.height / 4, 16777215);
      this.drawCenteredString(this.fontRendererObj, EnumChatFormatting.AQUA + "> Macro 2: ", this.width / 3 + 40, this.height / 4 + 25, 16777215);
      this.drawCenteredString(this.fontRendererObj, EnumChatFormatting.AQUA + "> Macro 3: ", this.width / 3 + 40, this.height / 4 + 50, 16777215);
      this.drawCenteredString(this.fontRendererObj, EnumChatFormatting.AQUA + "> Macro 4: ", this.width / 3 + 40, this.height / 4 + 75, 16777215);
      super.drawScreen(i, j, f);
      GL11.glEnable(GL11.GL_LIGHTING);
      GL11.glEnable(GL11.GL_DEPTH_TEST);
   }

   protected void drawBackgroundImage() {
      int displayX = (this.width - this.xSize) / 2;
      int displayY = (this.height - this.ySize) / 2;
      mc.getTextureManager().bindTexture(new ResourceLocation("textures/custom/social/da.png"));
      this.drawTexturedModalRect(displayX, displayY, 0, 0, this.xSize, this.ySize);
   }

   protected void actionPerformed(GuiButton e) {
      if(e.enabled) {
         if(e.id == 1) {
            String newMacro4;
            if(macro1.getText() != "") {
               newMacro4 = macro1.getText();
               CustomSettings.macro1 = newMacro4;
               mc.gameSettings.saveOptions();
               Minecraft.getMinecraft().displayGuiScreen((GuiScreen)null);
            }

            if(macro2.getText() != "") {
               newMacro4 = macro2.getText();
               CustomSettings.macro2 = newMacro4;
               mc.gameSettings.saveOptions();
               Minecraft.getMinecraft().displayGuiScreen((GuiScreen)null);
            }

            if(macro3.getText() != "") {
               newMacro4 = macro3.getText();
               CustomSettings.macro3 = newMacro4;
               mc.gameSettings.saveOptions();
               Minecraft.getMinecraft().displayGuiScreen((GuiScreen)null);
            }

            if(macro4.getText() != "") {
               newMacro4 = macro4.getText();
               CustomSettings.macro4 = newMacro4;
               mc.gameSettings.saveOptions();
               Minecraft.getMinecraft().displayGuiScreen((GuiScreen)null);
            }
         }
      }
   }

   protected void mouseClicked(int mouseX, int mouseY, int button) {
      boolean fmacro1 = macro1.isFocused();
      boolean fmacro2 = macro2.isFocused();
      boolean fmacro3 = macro3.isFocused();
      boolean fmacro4 = macro4.isFocused();
      super.mouseClicked(mouseX, mouseY, button);
      macro1.mouseClicked(mouseX, mouseY, button);
      macro2.mouseClicked(mouseX, mouseY, button);
      macro3.mouseClicked(mouseX, mouseY, button);
      macro4.mouseClicked(mouseX, mouseY, button);
   }

   protected void keyTyped(char c, int val) {
      if(!macro1.isFocused() && !macro2.isFocused() && !macro3.isFocused() && !macro4.isFocused()) {
         super.keyTyped(c, val);
      } else {
         macro1.textboxKeyTyped(c, val);
         macro2.textboxKeyTyped(c, val);
         macro3.textboxKeyTyped(c, val);
         macro4.textboxKeyTyped(c, val);
      }
   }
}
