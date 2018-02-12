package net.minecraft.client.gui;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import fr.obsifight.gui.crafts.GuiCraft;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.minecraft.client.gui.stream.GuiTwitchUserMode;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.client.C14PacketTabComplete;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import tv.twitch.chat.ChatUserInfo;

public class GuiChat extends GuiScreen implements GuiYesNoCallback {
   private static final Set field_152175_f = Sets.newHashSet(new String[]{"http", "https"});
   private static final Logger logger = LogManager.getLogger();
   private String field_146410_g = "";
   private int field_146416_h = -1;
   private boolean field_146417_i;
   private boolean field_146414_r;
   private int field_146413_s;
   private List field_146412_t = new ArrayList();
   private URI field_146411_u;
   protected GuiTextField field_146415_a;
   private String field_146409_v = "";
   private static final String __OBFID = "CL_00000682";
   private static final String __OBFID = "CL_00000682";

   public GuiChat() {}

   public GuiChat(String p_i1024_1_) {
      this.field_146409_v = p_i1024_1_;
   }

   public void initGui() {
      Keyboard.enableRepeatEvents(true);
      this.field_146416_h = mc.ingameGUI.getChatGUI().func_146238_c().size();
      this.field_146415_a = new GuiTextField(this.fontRendererObj, 4, this.height - 12, this.width - 4, 12);
      this.field_146415_a.func_146203_f(100);
      this.field_146415_a.func_146185_a(false);
      this.field_146415_a.setFocused(true);
      this.field_146415_a.setText(this.field_146409_v);
      this.field_146415_a.func_146205_d(false);
   }

   protected void actionPerformed(GuiButton par1GuiButton) {
      if(par1GuiButton.id == 9) {
         mc.displayGuiScreen(new GuiCraft(mc.thePlayer));
      }

      if(par1GuiButton.id == 5) {
         mc.renderHUD = false;
         mc.displayGuiScreen((GuiScreen)null);
      }

      if(par1GuiButton.id == 6) {
         mc.renderHUD = true;
         mc.displayGuiScreen((GuiScreen)null);
      }

      if(par1GuiButton.id == 7) {
         mc.percent = 2;
         mc.displayGuiScreen((GuiScreen)null);
      }

      if(par1GuiButton.id == 8) {
         mc.percent = 1;
         mc.displayGuiScreen((GuiScreen)null);
      }
   }

   public void onGuiClosed() {
      Keyboard.enableRepeatEvents(false);
      mc.ingameGUI.getChatGUI().resetScroll();
   }

   public void updateScreen() {
      this.field_146415_a.updateCursorCounter();
   }

   protected void keyTyped(char p_73869_1_, int p_73869_2_) {
      this.field_146414_r = false;
      if(p_73869_2_ == 15) {
         this.func_146404_p_();
      } else {
         this.field_146417_i = false;
      }

      if(p_73869_2_ == 1) {
         mc.displayGuiScreen((GuiScreen)null);
      } else if(p_73869_2_ != 28 && p_73869_2_ != 156) {
         if(p_73869_2_ == 200) {
            this.func_146402_a(-1);
         } else if(p_73869_2_ == 208) {
            this.func_146402_a(1);
         } else if(p_73869_2_ == 201) {
            mc.ingameGUI.getChatGUI().func_146229_b(mc.ingameGUI.getChatGUI().func_146232_i() - 1);
         } else if(p_73869_2_ == 209) {
            mc.ingameGUI.getChatGUI().func_146229_b(-mc.ingameGUI.getChatGUI().func_146232_i() + 1);
         } else {
            this.field_146415_a.textboxKeyTyped(p_73869_1_, p_73869_2_);
         }
      } else {
         String var3 = this.field_146415_a.getText().trim();
         if(var3.length() > 0) {
            this.func_146403_a(var3);
         }

         mc.displayGuiScreen((GuiScreen)null);
      }
   }

   public void func_146403_a(String p_146403_1_) {
      mc.ingameGUI.getChatGUI().func_146239_a(p_146403_1_);
      mc.thePlayer.sendChatMessage(p_146403_1_);
   }

   public void handleMouseInput() {
      super.handleMouseInput();
      int var1 = Mouse.getEventDWheel();
      if(var1 != 0) {
         if(var1 > 1) {
            var1 = 1;
         }

         if(var1 < -1) {
            var1 = -1;
         }

         if(!isShiftKeyDown()) {
            var1 *= 7;
         }

         mc.ingameGUI.getChatGUI().func_146229_b(var1);
      }
   }

   protected void mouseClicked(int p_73864_1_, int p_73864_2_, int p_73864_3_) {
      if(p_73864_3_ == 0 && mc.gameSettings.chatLinks) {
         IChatComponent var4 = mc.ingameGUI.getChatGUI().func_146236_a(Mouse.getX(), Mouse.getY());
         if(var4 != null) {
            ClickEvent var5 = var4.getChatStyle().getChatClickEvent();
            if(var5 != null) {
               if(isShiftKeyDown()) {
                  this.field_146415_a.func_146191_b(var4.getUnformattedTextForChat());
               } else {
                  URI var6;
                  if(var5.getAction() == ClickEvent.Action.OPEN_URL) {
                     try {
                        var6 = new URI(var5.getValue());
                        if(!field_152175_f.contains(var6.getScheme().toLowerCase())) {
                           throw new URISyntaxException(var5.getValue(), "Unsupported protocol: " + var6.getScheme().toLowerCase());
                        }

                        if(mc.gameSettings.chatLinksPrompt) {
                           this.field_146411_u = var6;
                           this.confirmClicked(true, 0);
                        } else {
                           this.func_146407_a(var6);
                        }
                     } catch (URISyntaxException var81) {
                        logger.error("Can\'t open url for " + var5, var81);
                     }
                  } else if(var5.getAction() == ClickEvent.Action.OPEN_FILE) {
                     var6 = (new File(var5.getValue())).toURI();
                     this.func_146407_a(var6);
                  } else if(var5.getAction() == ClickEvent.Action.SUGGEST_COMMAND) {
                     this.field_146415_a.setText(var5.getValue());
                  } else if(var5.getAction() == ClickEvent.Action.RUN_COMMAND) {
                     this.func_146403_a(var5.getValue());
                  } else if(var5.getAction() == ClickEvent.Action.TWITCH_USER_INFO) {
                     ChatUserInfo var8 = mc.func_152346_Z().func_152926_a(var5.getValue());
                     if(var8 != null) {
                        mc.displayGuiScreen(new GuiTwitchUserMode(mc.func_152346_Z(), var8));
                     } else {
                        logger.error("Tried to handle twitch user but couldn\'t find them!");
                     }
                  } else {
                     logger.error("Don\'t know how to handle " + var5);
                  }
               }

               return;
            }
         }
      }

      this.field_146415_a.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
      super.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
   }

   public void confirmClicked(boolean p_73878_1_, int p_73878_2_) {
      if(p_73878_2_ == 0) {
         if(p_73878_1_) {
            this.func_146407_a(this.field_146411_u);
         }

         this.field_146411_u = null;
         mc.displayGuiScreen(this);
      }
   }

   private void func_146407_a(URI p_146407_1_) {
      try {
         Class var4 = Class.forName("java.awt.Desktop");
         Object var3 = var4.getMethod("getDesktop", new Class[0]).invoke((Object)null, new Object[0]);
         var4.getMethod("browse", new Class[]{URI.class}).invoke(var3, new Object[]{p_146407_1_});
      } catch (Throwable var41) {
         logger.error("Couldn\'t open link", var41);
      }
   }

   public void func_146404_p_() {
      String var3;
      if(this.field_146417_i) {
         this.field_146415_a.func_146175_b(this.field_146415_a.func_146197_a(-1, this.field_146415_a.func_146198_h(), false) - this.field_146415_a.func_146198_h());
         if(this.field_146413_s >= this.field_146412_t.size()) {
            this.field_146413_s = 0;
         }
      } else {
         int var4 = this.field_146415_a.func_146197_a(-1, this.field_146415_a.func_146198_h(), false);
         this.field_146412_t.clear();
         this.field_146413_s = 0;
         String var5 = this.field_146415_a.getText().substring(var4).toLowerCase();
         var3 = this.field_146415_a.getText().substring(0, this.field_146415_a.func_146198_h());
         this.func_146405_a(var3, var5);
         if(this.field_146412_t.isEmpty()) {
            return;
         }

         this.field_146417_i = true;
         this.field_146415_a.func_146175_b(var4 - this.field_146415_a.func_146198_h());
      }

      if(this.field_146412_t.size() > 1) {
         StringBuilder var41 = new StringBuilder();

         for(Iterator var51 = this.field_146412_t.iterator(); var51.hasNext(); var41.append(var3)) {
            var3 = (String)var51.next();
            if(var41.length() > 0) {
               var41.append(", ");
            }
         }

         mc.ingameGUI.getChatGUI().func_146234_a(new ChatComponentText(var41.toString()), 1);
      }

      this.field_146415_a.func_146191_b((String)this.field_146412_t.get(this.field_146413_s++));
   }

   private void func_146405_a(String p_146405_1_, String p_146405_2_) {
      if(p_146405_1_.length() >= 1) {
         mc.thePlayer.sendQueue.addToSendQueue(new C14PacketTabComplete(p_146405_1_));
         this.field_146414_r = true;
      }
   }

   public void func_146402_a(int p_146402_1_) {
      int var2 = this.field_146416_h + p_146402_1_;
      int var3 = mc.ingameGUI.getChatGUI().func_146238_c().size();
      if(var2 < 0) {
         var2 = 0;
      }

      if(var2 > var3) {
         var2 = var3;
      }

      if(var2 != this.field_146416_h) {
         if(var2 == var3) {
            this.field_146416_h = var3;
            this.field_146415_a.setText(this.field_146410_g);
         } else {
            if(this.field_146416_h == var3) {
               this.field_146410_g = this.field_146415_a.getText();
            }

            this.field_146415_a.setText((String)mc.ingameGUI.getChatGUI().func_146238_c().get(var2));
            this.field_146416_h = var2;
         }
      }
   }

   public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
      drawRect(2, this.height - 14, this.width - 2, this.height - 2, Integer.MIN_VALUE);
      this.field_146415_a.drawTextBox();
      IChatComponent var4 = mc.ingameGUI.getChatGUI().func_146236_a(Mouse.getX(), Mouse.getY());
      if(var4 != null && var4.getChatStyle().getChatHoverEvent() != null) {
         HoverEvent var5 = var4.getChatStyle().getChatHoverEvent();
         if(var5.getAction() == HoverEvent.Action.SHOW_ITEM) {
            ItemStack var12 = null;

            try {
               NBTBase var13 = JsonToNBT.func_150315_a(var5.getValue().getUnformattedText());
               if(var13 != null && var13 instanceof NBTTagCompound) {
                  var12 = ItemStack.loadItemStackFromNBT((NBTTagCompound)var13);
               }
            } catch (NBTException var11) {
               ;
            }

            if(var12 != null) {
               this.func_146285_a(var12, p_73863_1_, p_73863_2_);
            } else {
               this.func_146279_a(EnumChatFormatting.RED + "Invalid Item!", p_73863_1_, p_73863_2_);
            }
         } else if(var5.getAction() == HoverEvent.Action.SHOW_TEXT) {
            this.func_146283_a(Splitter.on("\n").splitToList(var5.getValue().getFormattedText()), p_73863_1_, p_73863_2_);
         } else if(var5.getAction() == HoverEvent.Action.SHOW_ACHIEVEMENT) {
            StatBase var121 = StatList.func_151177_a(var5.getValue().getUnformattedText());
            if(var121 != null) {
               IChatComponent var131 = var121.func_150951_e();
               ChatComponentTranslation var8 = new ChatComponentTranslation("stats.tooltip.type." + (var121.isAchievement()?"achievement":"statistic"), new Object[0]);
               var8.getChatStyle().setItalic(Boolean.valueOf(true));
               String var9 = var121 instanceof Achievement?((Achievement)var121).getDescription():null;
               ArrayList var10 = Lists.newArrayList(new String[]{var131.getFormattedText(), var8.getFormattedText()});
               if(var9 != null) {
                  var10.addAll(this.fontRendererObj.listFormattedStringToWidth(var9, 150));
               }

               this.func_146283_a(var10, p_73863_1_, p_73863_2_);
            } else {
               this.func_146279_a(EnumChatFormatting.RED + "Invalid statistic/achievement!", p_73863_1_, p_73863_2_);
            }
         }

         GL11.glDisable(GL11.GL_LIGHTING);
      }

      super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
   }

   public void func_146406_a(String[] p_146406_1_) {
      if(this.field_146414_r) {
         this.field_146417_i = false;
         this.field_146412_t.clear();
         String[] var2 = p_146406_1_;
         int var3 = p_146406_1_.length;

         String var7;
         for(int var6 = 0; var6 < var3; ++var6) {
            var7 = var2[var6];
            if(var7.length() > 0) {
               this.field_146412_t.add(var7);
            }
         }

         String var61 = this.field_146415_a.getText().substring(this.field_146415_a.func_146197_a(-1, this.field_146415_a.func_146198_h(), false));
         var7 = StringUtils.getCommonPrefix(p_146406_1_);
         if(var7.length() > 0 && !var61.equalsIgnoreCase(var7)) {
            this.field_146415_a.func_146175_b(this.field_146415_a.func_146197_a(-1, this.field_146415_a.func_146198_h(), false) - this.field_146415_a.func_146198_h());
            this.field_146415_a.func_146191_b(var7);
         } else if(this.field_146412_t.size() > 0) {
            this.field_146417_i = true;
            this.func_146404_p_();
         }
      }
   }

   public boolean doesGuiPauseGame() {
      return false;
   }
}
