package net.minecraft.client.gui;

import com.mojang.realmsclient.gui.ChatFormatting;
import fr.thisismac.injector.bettersprintmod.CustomMovementInput;
import fr.thisismac.injector.customclass.GuiGUIMessage;
import fr.thisismac.injector.customclass.NetworkTrigger;
import java.awt.Color;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFlintAndSteel;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.Direction;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.FoodStats;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.chunk.Chunk;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import reifnsk.minimap.ReiMinimap;

public class GuiIngame extends Gui {
   private static final ResourceLocation vignetteTexPath = new ResourceLocation("textures/misc/vignette.png");
   private static final ResourceLocation widgetsTexPath = new ResourceLocation("textures/gui/widgets.png");
   private static final ResourceLocation pumpkinBlurTexPath = new ResourceLocation("textures/misc/pumpkinblur.png");
   private static final ResourceLocation inventory = new ResourceLocation("textures/gui/container/inventory.png");
   private static final ResourceLocation inventoryPath = new ResourceLocation("textures/gui/container/inventory.png");
   private static final RenderItem itemRenderer = new RenderItem();
   public static Boolean toggleArmures = Boolean.valueOf(true);
   public static Boolean toggleEffets = Boolean.valueOf(true);
   public static Boolean toggleCompass = Boolean.valueOf(true);
   private final Random rand = new Random();
   private final Minecraft mc;
   private ArrayList<GuiGUIMessage> messages = new ArrayList();
   public int kills;
   float currentHealth;
   private final GuiNewChat persistantChatGUI;
   private final GuiStreamIndicator field_152127_m;
   private int updateCounter;
   private String recordPlaying = "";
   private int recordPlayingUpFor;
   private boolean recordIsPlaying;
   public float prevVignetteBrightness = 1.0F;
   private int remainingHighlightTicks;
   private ItemStack highlightingItemStack;
   private static final String __OBFID = "CL_00000661";
   private static final boolean REI_MINIMAP;
   private static final String __OBFID = "CL_00000661";

   public GuiIngame(Minecraft p_i1036_1_) {
      this.mc = p_i1036_1_;
      this.persistantChatGUI = new GuiNewChat(p_i1036_1_);
      this.field_152127_m = new GuiStreamIndicator(this.mc);
      this.clearMessages();
      this.kills = 0;
   }

   public void displayMessage(GuiGUIMessage message) {
      Iterator i$ = this.messages.iterator();

      while(i$.hasNext()) {
         GuiGUIMessage msg = (GuiGUIMessage)i$.next();
         if(msg.id.equalsIgnoreCase(message.id)) {
            msg.elapsedTimer = message.elapsedTimer;
            msg.displayTimer = message.displayTimer;
            msg.message = message.message;
            msg.rotation = message.rotation;
            msg.scale = message.scale;
            msg.shadow = message.shadow;
            msg.stack = message.stack;
            msg.x = message.x;
            msg.y = message.y;
            msg.xFinal = message.xFinal;
            msg.yFinal = message.yFinal;
            msg.renderPass = message.renderPass;
            msg.renderPassed = 0;
            msg.anchor = message.anchor;
            return;
         }
      }

      this.messages.add(message);
   }

   public void clearMessages() {
      this.messages.clear();
   }

   public void renderGameOverlay(float p_73830_1_, boolean p_73830_2_, int p_73830_3_, int p_73830_4_) {
      ScaledResolution var5 = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
      int var6 = var5.getScaledWidth();
      int var7 = var5.getScaledHeight();
      FontRenderer var8 = this.mc.fontRenderer;
      this.mc.entityRenderer.setupOverlayRendering();
      GL11.glEnable(GL11.GL_BLEND);
      if(Minecraft.isFancyGraphicsEnabled()) {
         this.renderVignette(this.mc.thePlayer.getBrightness(p_73830_1_), var6, var7);
      } else {
         OpenGlHelper.glBlendFunc(770, 771, 1, 0);
      }

      ItemStack var9 = this.mc.thePlayer.inventory.armorItemInSlot(3);
      if(this.mc.gameSettings.thirdPersonView == 0 && var9 != null && var9.getItem() == Item.getItemFromBlock(Blocks.pumpkin)) {
         this.renderPumpkinBlur(var6, var7);
      }

      if(!this.mc.thePlayer.isPotionActive(Potion.confusion)) {
         float var12 = this.mc.thePlayer.prevTimeInPortal + (this.mc.thePlayer.timeInPortal - this.mc.thePlayer.prevTimeInPortal) * p_73830_1_;
         if(var12 > 0.0F) {
            this.func_130015_b(var12, var6, var7);
         }
      }

      int var13;
      int var371;
      int var391;
      if(!this.mc.playerController.enableEverythingIsScrewedUpMode()) {
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         this.mc.getTextureManager().bindTexture(widgetsTexPath);
         InventoryPlayer var381 = this.mc.thePlayer.inventory;
         this.zLevel = -90.0F;
         this.drawTexturedModalRect(var6 / 2 - 91, var7 - 22, 0, 0, 182, 22);
         this.drawTexturedModalRect(var6 / 2 - 91 - 1 + var381.currentItem * 20, var7 - 22 - 1, 0, 22, 24, 22);
         this.mc.getTextureManager().bindTexture(icons);
         GL11.glEnable(GL11.GL_BLEND);
         OpenGlHelper.glBlendFunc(775, 769, 1, 0);
         this.drawTexturedModalRect(var6 / 2 - 7, var7 / 2 - 7, 0, 0, 16, 16);
         OpenGlHelper.glBlendFunc(770, 771, 1, 0);
         this.mc.mcProfiler.startSection("bossHealth");
         this.renderBossHealth();
         this.mc.mcProfiler.endSection();
         if(this.mc.playerController.shouldDrawHUD()) {
            this.func_110327_a(var6, var7);
         }

         this.mc.mcProfiler.startSection("actionBar");
         GL11.glEnable(GL12.GL_RESCALE_NORMAL);
         RenderHelper.enableGUIStandardItemLighting();

         for(var371 = 0; var371 < 9; ++var371) {
            var391 = var6 / 2 - 90 + var371 * 20 + 2;
            var13 = var7 - 16 - 3;
            this.renderInventorySlot(var371, var391, var13, p_73830_1_);
         }

         this.renderAFHUD(p_73830_1_);
         RenderHelper.disableStandardItemLighting();
         GL11.glDisable(GL12.GL_RESCALE_NORMAL);
         this.mc.mcProfiler.endSection();
         GL11.glDisable(GL11.GL_BLEND);
      }

      int var40;
      if(this.mc.thePlayer.getSleepTimer() > 0) {
         this.mc.mcProfiler.startSection("sleep");
         GL11.glDisable(GL11.GL_DEPTH_TEST);
         GL11.glDisable(GL11.GL_ALPHA_TEST);
         var40 = this.mc.thePlayer.getSleepTimer();
         float var15 = (float)var40 / 100.0F;
         if(var15 > 1.0F) {
            var15 = 1.0F - (float)(var40 - 100) / 10.0F;
         }

         var391 = (int)(220.0F * var15) << 24 | 1052704;
         drawRect(0, 0, var6, var7, var391);
         GL11.glEnable(GL11.GL_ALPHA_TEST);
         GL11.glEnable(GL11.GL_DEPTH_TEST);
         this.mc.mcProfiler.endSection();
      }

      var40 = 16777215;
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      var371 = var6 / 2 - 91;
      int var16;
      int var17;
      float var34;
      short var38;
      int var39;
      int var411;
      if(this.mc.thePlayer.isRidingHorse()) {
         this.mc.mcProfiler.startSection("jumpBar");
         this.mc.getTextureManager().bindTexture(Gui.icons);
         var34 = this.mc.thePlayer.getHorseJumpPower();
         var38 = 182;
         var39 = (int)(var34 * (float)(var38 + 1));
         var411 = var7 - 32 + 3;
         this.drawTexturedModalRect(var371, var411, 0, 84, var38, 5);
         if(var39 > 0) {
            this.drawTexturedModalRect(var371, var411, 0, 89, var39, 5);
         }

         this.mc.mcProfiler.endSection();
      } else if(this.mc.playerController.gameIsSurvivalOrAdventure()) {
         this.mc.mcProfiler.startSection("expBar");
         this.mc.getTextureManager().bindTexture(Gui.icons);
         var391 = this.mc.thePlayer.xpBarCap();
         if(var391 > 0) {
            var38 = 182;
            var39 = (int)(this.mc.thePlayer.experience * (float)(var38 + 1));
            var411 = var7 - 32 + 3;
            this.drawTexturedModalRect(var371, var411, 0, 64, var38, 5);
            if(var39 > 0) {
               this.drawTexturedModalRect(var371, var411, 0, 69, var39, 5);
            }
         }

         this.mc.mcProfiler.endSection();
         if(this.mc.thePlayer.experienceLevel > 0) {
            this.mc.mcProfiler.startSection("expLevel");
            boolean var401 = false;
            var39 = var401?16777215:8453920;
            String var23 = "" + this.mc.thePlayer.experienceLevel;
            var16 = (var6 - var8.getStringWidth(var23)) / 2;
            var17 = var7 - 31 - 4;
            boolean var50 = false;
            var8.drawString(var23, var16 + 1, var17, 0);
            var8.drawString(var23, var16 - 1, var17, 0);
            var8.drawString(var23, var16, var17 + 1, 0);
            var8.drawString(var23, var16, var17 - 1, 0);
            var8.drawString(var23, var16, var17, var39);
            this.mc.mcProfiler.endSection();
         }
      }

      if(this.mc.gameSettings.heldItemTooltips) {
         this.mc.mcProfiler.startSection("toolHighlight");
         if(this.remainingHighlightTicks > 0 && this.highlightingItemStack != null) {
            String var42 = this.highlightingItemStack.getDisplayName();
            var13 = (var6 - var8.getStringWidth(var42)) / 2;
            var39 = var7 - 59;
            if(!this.mc.playerController.shouldDrawHUD()) {
               var39 += 14;
            }

            var411 = (int)((float)this.remainingHighlightTicks * 256.0F / 10.0F);
            if(var411 > 255) {
               var411 = 255;
            }

            if(var411 > 0) {
               GL11.glPushMatrix();
               GL11.glEnable(GL11.GL_BLEND);
               OpenGlHelper.glBlendFunc(770, 771, 1, 0);
               var8.drawStringWithShadow(var42, var13, var39, 16777215 + (var411 << 24));
               GL11.glDisable(GL11.GL_BLEND);
               GL11.glPopMatrix();
            }
         }

         this.mc.mcProfiler.endSection();
      }

      if(REI_MINIMAP && !ReiMinimap.instance.useModloader) {
         ReiMinimap.instance.onTickInGame(p_73830_1_, this.mc);
      }

      int var27;
      int var28;
      int var41;
      int var421;
      String lascaled;
      int var431;
      String var44;
      Minecraft var10000;
      if(this.mc.gameSettings.showDebugInfo) {
         this.mc.mcProfiler.startSection("debug");
         GL11.glPushMatrix();
         var10000 = this.mc;
         int var43 = Minecraft.debugFPS;
         lascaled = "";
         if(var43 < 30) {
            lascaled = "\u00a7c";
         }

         if(var43 > 30 && var43 < 120) {
            lascaled = "\u00a7a";
         }

         if(var43 > 120) {
            lascaled = "\u00a79";
         }

         String xx = "\u00a76ObsiFight 1.7.10 \u00a74(\u00a76FPS: \u00a7a" + var43 + "\u00a76, \u00a7a" + WorldRenderer.chunksUpdated + " \u00a76chunk updated\u00a74)";
         var8.drawStringWithShadow(xx, 2, 2, 16777215);
         var8.drawStringWithShadow("\u00a7c" + this.mc.getEntityDebug(), 2, 53, 16777215);
         long yy = Runtime.getRuntime().maxMemory();
         long aa = Runtime.getRuntime().totalMemory();
         long xdd = Runtime.getRuntime().freeMemory();
         long var30 = aa - xdd;
         var44 = "\u00a76Used memory: " + var30 * 100L / yy + "% (" + var30 / 1024L / 1024L + "MB) of " + yy / 1024L / 1024L + "MB";
         var41 = 14737632;
         this.drawString(var8, var44, var6 - var8.getStringWidth(var44) - 2, 2, 14737632);
         var44 = "\u00a76Allocated memory: " + aa * 100L / yy + "% (" + aa / 1024L / 1024L + "MB)";
         this.drawString(var8, var44, var6 - var8.getStringWidth(var44) - 2, 12, 14737632);
         var421 = MathHelper.floor_double(this.mc.thePlayer.posX);
         var431 = MathHelper.floor_double(this.mc.thePlayer.posY);
         var27 = MathHelper.floor_double(this.mc.thePlayer.posZ);
         if(GameSettings.affichage == 0) {
            this.drawString(var8, String.format("\u00a74[\u00a76X: %.5f (%d)\u00a74]", new Object[]{Double.valueOf(this.mc.thePlayer.posX), Integer.valueOf(var421), Integer.valueOf(var421 >> 4), Integer.valueOf(var421 & 15)}), 2, 22, 14737632);
            this.drawString(var8, String.format("\u00a74[\u00a76Y: %.3f (feet pos, %.3f eyes pos)\u00a74]", new Object[]{Double.valueOf(this.mc.thePlayer.boundingBox.minY), Double.valueOf(this.mc.thePlayer.posY)}), 2, 32, 14737632);
            this.drawString(var8, String.format("\u00a74[\u00a76Z: %.5f (%d)\u00a74]", new Object[]{Double.valueOf(this.mc.thePlayer.posZ), Integer.valueOf(var27), Integer.valueOf(var27 >> 4), Integer.valueOf(var27 & 15)}), 2, 42, 14737632);
         } else if(GameSettings.affichage == 1) {
            this.drawString(var8, String.format("\u00a74[\u00a7cCach\u00e9\u00a74]", new Object[]{Double.valueOf(this.mc.thePlayer.posX), Integer.valueOf(var421), Integer.valueOf(var421 >> 4), Integer.valueOf(var421 & 15)}), 2, 22, 14737632);
            this.drawString(var8, String.format("\u00a74[\u00a76Y: %.3f (feet pos, %.3f eyes pos)\u00a74]", new Object[]{Double.valueOf(this.mc.thePlayer.boundingBox.minY), Double.valueOf(this.mc.thePlayer.posY)}), 2, 32, 14737632);
            this.drawString(var8, String.format("\u00a74[\u00a7cCach\u00e9\u00a74]", new Object[]{Double.valueOf(this.mc.thePlayer.posZ), Integer.valueOf(var27), Integer.valueOf(var27 >> 4), Integer.valueOf(var27 & 15)}), 2, 42, 14737632);
         } else if(GameSettings.affichage == 2) {
            this.drawString(var8, String.format("\u00a74[\u00a7cCach\u00e9\u00a74]", new Object[]{Double.valueOf(this.mc.thePlayer.posX), Integer.valueOf(var421), Integer.valueOf(var421 >> 4), Integer.valueOf(var421 & 15)}), 2, 22, 14737632);
            this.drawString(var8, String.format("\u00a74[\u00a7cCach\u00e9\u00a74]", new Object[]{Double.valueOf(this.mc.thePlayer.boundingBox.minY), Double.valueOf(this.mc.thePlayer.posY)}), 2, 32, 14737632);
            this.drawString(var8, String.format("\u00a74[\u00a7cCach\u00e9\u00a74]", new Object[]{Double.valueOf(this.mc.thePlayer.posZ), Integer.valueOf(var27), Integer.valueOf(var27 >> 4), Integer.valueOf(var27 & 15)}), 2, 42, 14737632);
         }

         var28 = MathHelper.floor_double((double)(this.mc.thePlayer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
         this.drawString(var8, "\u00a76Direction: " + Direction.directions[var28].replace("SOUTH", "SUD").replace("NORTH", "NORD").replace("EAST", "EST"), 2, 88, 14737632);
         if(this.mc.theWorld != null && this.mc.theWorld.blockExists(var421, var431, var27)) {
            if(GameSettings.affichage != 3) {
               Chunk var29 = this.mc.theWorld.getChunkFromBlockCoords(var421, var27);
               this.drawString(var8, "\u00a76Lumi\u00e8re: " + var29.getSavedLightValue(EnumSkyBlock.Block, var421 & 15, var431, var27 & 15), 2, 65, 14737632);
            }

            if(this.mc.entityRenderer != null && this.mc.entityRenderer.isShaderActive()) {
               this.drawString(var8, String.format("shader: %s", new Object[]{this.mc.entityRenderer.getShaderGroup().getShaderGroupName()}), 2, 112, 14737632);
            }

            GL11.glPopMatrix();
            this.mc.mcProfiler.endSection();
         }
      }

      if(this.recordPlayingUpFor > 0) {
         this.mc.mcProfiler.startSection("overlayMessage");
         var34 = (float)this.recordPlayingUpFor - p_73830_1_;
         var13 = (int)(var34 * 255.0F / 20.0F);
         if(var13 > 255) {
            var13 = 255;
         }

         if(var13 > 8) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)(var6 / 2), (float)(var7 - 68), 0.0F);
            GL11.glEnable(GL11.GL_BLEND);
            OpenGlHelper.glBlendFunc(770, 771, 1, 0);
            var39 = 16777215;
            if(this.recordIsPlaying) {
               var39 = Color.HSBtoRGB(var34 / 50.0F, 0.7F, 0.6F) & 16777215;
            }

            var8.drawString(this.recordPlaying, -var8.getStringWidth(this.recordPlaying) / 2, -4, var39 + (var13 << 24 & -16777216));
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glPopMatrix();
         }

         this.mc.mcProfiler.endSection();
      }

      ScoreObjective var45 = this.mc.theWorld.getScoreboard().func_96539_a(1);
      if(var45 != null) {
         this.func_96136_a(var45, var7, var6, var8);
      }

      GL11.glEnable(GL11.GL_BLEND);
      OpenGlHelper.glBlendFunc(770, 771, 1, 0);
      GL11.glDisable(GL11.GL_ALPHA_TEST);
      GL11.glPushMatrix();
      GL11.glTranslatef(0.0F, (float)(var7 - 48), 0.0F);
      this.mc.mcProfiler.startSection("chat");
      this.persistantChatGUI.func_146230_a(this.updateCounter);
      this.mc.mcProfiler.endSection();
      GL11.glPopMatrix();
      var45 = this.mc.theWorld.getScoreboard().func_96539_a(0);
      int var51;
      if(this.mc.gameSettings.keyBindPlayerList.getIsKeyPressed() && (!this.mc.isIntegratedServerRunning() || this.mc.thePlayer.sendQueue.playerInfoList.size() > 1 || var45 != null)) {
         this.mc.mcProfiler.startSection("playerList");
         NetHandlerPlayClient var46 = this.mc.thePlayer.sendQueue;
         List var49 = var46.playerInfoList;
         var411 = var46.currentServerMaxPlayers;
         var16 = var411;

         for(var17 = 1; var16 > 20; var16 = (var411 + var17 - 1) / var17) {
            ++var17;
         }

         var51 = 300 / var17;
         if(var51 > 150) {
            var51 = 150;
         }

         int xd = (var6 - var17 * var51) / 2;
         byte var53 = 10;
         drawRect(xd - 1, var53 - 1, xd + var51 * var17, var53 + 9 * var16, Integer.MIN_VALUE);

         for(var41 = 0; var41 < var411; ++var41) {
            var421 = xd + var41 % var17 * var51;
            var431 = var53 + var41 / var17 * 9;
            drawRect(var421, var431, var421 + var51 - 1, var431 + 8, 553648127);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glEnable(GL11.GL_ALPHA_TEST);
            if(var41 < var49.size()) {
               GuiPlayerInfo bb = (GuiPlayerInfo)var49.get(var41);
               ScorePlayerTeam var56 = this.mc.theWorld.getScoreboard().getPlayersTeam(bb.name);
               var44 = ScorePlayerTeam.formatPlayerName(var56, bb.name);
               var8.drawStringWithShadow(var44, var421, var431, 16777215);
               if(var45 != null) {
                  var27 = var421 + var8.getStringWidth(var44) + 5;
                  var28 = var421 + var51 - 12 - 5;
                  if(var28 - var27 > 5) {
                     Score xddd = var45.getScoreboard().func_96529_a(bb.name, var45);
                     String var59 = EnumChatFormatting.YELLOW + "" + xddd.getScorePoints();
                     var8.drawStringWithShadow(var59, var28 - var8.getStringWidth(var59), var431, 16777215);
                  }
               }
            }
         }
      }

      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glDisable(GL11.GL_LIGHTING);
      GL11.glEnable(GL11.GL_ALPHA_TEST);
      this.drawBetterSprintStatus(0, var7 - 10);
      if(NetworkTrigger.textToRender != null && NetworkTrigger.timeToRender > 0) {
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         int var47 = NetworkTrigger.timeToRender < 10?NetworkTrigger.timeToRender * 25:255;
         this.mc.fontRenderer.drawString(NetworkTrigger.textToRender, var6 / 2 - this.mc.fontRenderer.getStringWidth(NetworkTrigger.textToRender) / 2, 32, 16777215 | (var47 & 255) << 24);
      }

      if(NetworkTrigger.timeInCombat) {
         lascaled = EnumChatFormatting.RED + "EN COMBAT";
         this.mc.fontRenderer.drawString(lascaled, var6 / 4 * 3 + 60 - this.mc.fontRenderer.getStringWidth(lascaled), var7 - 10, 16777215);
      }

      ScaledResolution var48 = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
      int var501 = var48.getScaledWidth();
      var51 = var48.getScaledHeight();
      if(GameSettings.showArmor) {
         if(GameSettings.armorPos == 0) {
            this.showHUD(var8, 2, 20);
         }

         if(GameSettings.armorPos == 1) {
            this.showHUD(var8, var501 - 50, 10);
         }

         if(GameSettings.armorPos == 2) {
            this.showHUD(var8, var501 - 50, var51 - 90);
         }
      }

      if(GameSettings.showEffect) {
         if(GameSettings.effectPos == 0) {
            this.renderEffect(var8, 2, 100);
         }

         if(GameSettings.effectPos == 1) {
            this.renderEffect(var8, var501 - 45, 80);
         }
      }

      if(!this.mc.gameSettings.showDebugInfo) {
         if(NetworkTrigger.currentTerritory != null) {
            String var52 = ChatFormatting.GOLD + " - " + ChatFormatting.RESET + NetworkTrigger.currentTerritory;
            this.mc.fontRenderer.drawString(var52, 46, 2, 16777215);
         }

         this.renderFPS(var8, 2, 2);
         if(GameSettings.facingPos == 0) {
            this.renderFacing(var8, var501, 2);
         }

         if(GameSettings.facingPos == 1) {
            this.renderFacing(var8, var501 / 2, 2);
         }

         if(GameSettings.facingPos == 2) {
            this.renderFacing(var8, 30, 14);
         }
      }

      var10000 = this.mc;
      if(Minecraft.vote) {
         new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
         int var54 = var5.getScaledWidth();
         int var55 = var5.getScaledHeight();
         int var57 = var54 / 2 - 90 + 180;
         int var58 = var55 - 16;
         var8.drawString("Vous pouvez voter", var54 - 98, var55 - 10, 15790080);
      }
   }

   private void renderFacing(FontRenderer var8, int x, int y) {
      ScaledResolution var5 = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
      int var6 = var5.getScaledWidth();
      int var7 = var5.getScaledHeight();
      int var28 = MathHelper.floor_double((double)(this.mc.thePlayer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
      String direction = Direction.directions[var28].replace("SOUTH", "SUD").replace("NORTH", "NORD").replace("EAST", "EST").replace("WEST", "OUEST");
      if(GameSettings.showfacing) {
         this.drawString(var8, "\u00a7a" + direction, x - var8.getStringWidth(direction), y, 14737632);
      }
   }

   private void renderFPS(FontRenderer var8, int x, int y) {
      Minecraft var10000 = this.mc;
      int fps = Minecraft.debugFPS;
      var8.drawStringWithShadow("\u00a76ObsiFight", x, y, 0);
      var8.drawStringWithShadow(GameSettings.showfps?"\u00a76 " + fps + " FPS":"", var8.getStringWidth(String.valueOf(fps)) - var8.getStringWidth(String.valueOf(fps)), 15, 0);
   }

   private void renderCords(FontRenderer var8) {
      ScaledResolution var5 = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
      int var6 = var5.getScaledWidth();
      int var7 = var5.getScaledHeight();
      String x = "X: " + Math.round(this.mc.thePlayer.posX);
      String y = "Y: " + Math.round(this.mc.thePlayer.posY);
      String z = "Z: " + Math.round(this.mc.thePlayer.posZ);
      if(GameSettings.showcoords) {
         this.drawString(var8, x, var6 - 50, 2, 14737632);
         this.drawString(var8, y, var6 - 50, 10, 14737632);
         this.drawString(var8, z, var6 - 50, 18, 14737632);
      }
   }

   private void renderAFHUD(float p_73830_1_) {
      GL11.glPushMatrix();
      ScaledResolution var5 = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
      int var6 = var5.getScaledWidth();
      int var7 = var5.getScaledHeight();
      GL11.glDisable(GL11.GL_LIGHTING);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      int var12 = var6 - 16;
      int var18 = var7 - 3;
      Iterator var22 = this.messages.iterator();

      while(var22.hasNext()) {
         GuiGUIMessage var25 = (GuiGUIMessage)var22.next();
         if(var25.displayTimer > 1) {
            if(var25.xFinal != -999 || var25.yFinal != -999) {
               if(var25.renderPass == var25.renderPassed) {
                  if(var25.xFinal != -999) {
                     if(var25.x > var25.xFinal) {
                        --var25.x;
                     } else if(var25.x < var25.xFinal) {
                        ++var25.x;
                     }
                  }

                  if(var25.yFinal != -999) {
                     if(var25.y > var25.yFinal) {
                        --var25.y;
                     } else if(var25.y < var25.yFinal) {
                        ++var25.y;
                     }
                  }

                  if(var25.renderPass > 0) {
                     var25.renderPassed = 0;
                  }
               } else {
                  ++var25.renderPassed;
               }
            }

            float var28 = (float)var25.displayTimer - p_73830_1_;
            int var121 = (int)(var28 * 256.0F / 20.0F);
            float var30 = (float)var25.elapsedTimer;
            int var31 = (int)(var30 * 256.0F / 20.0F);
            if(var31 < 255) {
               var121 = var31;
            }

            if(var121 > 255) {
               var121 = 255;
            }

            if(var121 < 0) {
               var121 = 0;
            }

            if(var121 > 0) {
               GL11.glPushMatrix();
               if(var25.anchor == GuiGUIMessage.Anchor.DEFAULT) {
                  GL11.glTranslatef((float)(var6 / 2), 30.0F, 0.0F);
               }

               if(var25.anchor == GuiGUIMessage.Anchor.TOP) {
                  GL11.glTranslatef((float)(var6 / 2), 0.0F, 0.0F);
               } else if(var25.anchor == GuiGUIMessage.Anchor.TOPLEFT) {
                  GL11.glTranslatef((float)this.mc.fontRenderer.getStringWidth(var25.message) / 2.0F, 0.0F, 0.0F);
               } else if(var25.anchor == GuiGUIMessage.Anchor.TOPRIGHT) {
                  GL11.glTranslatef((float)var6 - (float)(this.mc.fontRenderer.getStringWidth(var25.message) / 2), 0.0F, 0.0F);
               } else if(var25.anchor == GuiGUIMessage.Anchor.LEFT) {
                  GL11.glTranslatef((float)this.mc.fontRenderer.getStringWidth(var25.message) / 2.0F, (float)(var7 / 2), 0.0F);
               } else if(var25.anchor == GuiGUIMessage.Anchor.RIGHT) {
                  GL11.glTranslatef((float)var6 - (float)(this.mc.fontRenderer.getStringWidth(var25.message) / 2), (float)(var7 / 2), 0.0F);
               } else if(var25.anchor == GuiGUIMessage.Anchor.BOT) {
                  GL11.glTranslatef((float)(var6 / 2), (float)(var7 - 60), 0.0F);
               } else if(var25.anchor == GuiGUIMessage.Anchor.BOTLEFT) {
                  GL11.glTranslatef((float)(this.mc.fontRenderer.getStringWidth(var25.message) / 2), (float)(var7 - 30), 0.0F);
               } else if(var25.anchor == GuiGUIMessage.Anchor.BOTRIGHT) {
                  GL11.glTranslatef((float)var6 - (float)(this.mc.fontRenderer.getStringWidth(var25.message) / 2), (float)(var7 - 30), 0.0F);
               } else if(var25.anchor == GuiGUIMessage.Anchor.CENTER) {
                  GL11.glTranslatef((float)(var6 / 2), (float)(var7 / 2), 0.0F);
               }

               if(var25.spin == GuiGUIMessage.ROTATION.RIGHT) {
                  var25.rotation += 2.0F;
               } else if(var25.spin == GuiGUIMessage.ROTATION.LEFT) {
                  --var25.rotation;
               } else if(var25.spin == GuiGUIMessage.ROTATION.CUSTOMLEFT) {
                  var25.rotation -= var25.spinValue;
               } else if(var25.spin == GuiGUIMessage.ROTATION.CUSTOMRIGHT) {
                  var25.rotation += var25.spinValue;
               }

               GL11.glScalef(var25.scale, var25.scale, var25.scale);
               GL11.glRotatef(var25.rotation, 0.0F, 0.0F, 1.0F);
               var18 = 16777215;

               try {
                  if(var25.stack != null) {
                     this.renderItem(var25.stack, -this.mc.fontRenderer.getStringWidth(var25.message) / 2 + var25.x - 20, var25.y - 5, p_73830_1_);
                  }
               } catch (Exception var15) {
                  var15.printStackTrace();
               }

               GL11.glDisable(GL11.GL_LIGHTING);
               GL11.glEnable(GL11.GL_BLEND);
               GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
               if(var25.shadow) {
                  this.mc.fontRenderer.drawStringWithShadow(var25.message, -this.mc.fontRenderer.getStringWidth(var25.message) / 2 + var25.x, var25.y, var18 + (var121 << 24));
               } else {
                  this.mc.fontRenderer.drawString(var25.message, -this.mc.fontRenderer.getStringWidth(var25.message) / 2 + var25.x, var25.y, var18 + (var121 << 24));
               }

               GL11.glDisable(GL11.GL_BLEND);
               GL11.glPopMatrix();
            }
         }
      }

      GL11.glPopMatrix();
   }

   public void func_152126_a(float p_152126_1_, float p_152126_2_) {
      this.field_152127_m.func_152437_a((int)(p_152126_1_ - 10.0F), 10);
   }

   private void func_96136_a(ScoreObjective p_96136_1_, int p_96136_2_, int p_96136_3_, FontRenderer p_96136_4_) {
      Scoreboard var5 = p_96136_1_.getScoreboard();
      Collection var6 = var5.func_96534_i(p_96136_1_);
      if(var6.size() <= 15) {
         int var7 = p_96136_4_.getStringWidth(p_96136_1_.getDisplayName());

         String var11;
         for(Iterator var221 = var6.iterator(); var221.hasNext(); var7 = Math.max(var7, p_96136_4_.getStringWidth(var11))) {
            Score var231 = (Score)var221.next();
            ScorePlayerTeam var241 = var5.getPlayersTeam(var231.getPlayerName());
            var11 = ScorePlayerTeam.formatPlayerName(var241, var231.getPlayerName()) + ": " + EnumChatFormatting.RED + var231.getScorePoints();
         }

         int var22 = var6.size() * p_96136_4_.FONT_HEIGHT;
         int var23 = p_96136_2_ / 2 + var22 / 3;
         byte var24 = 3;
         int var25 = p_96136_3_ - var7 - var24;
         int var12 = 0;
         Iterator var13 = var6.iterator();

         while(var13.hasNext()) {
            Score var14 = (Score)var13.next();
            ++var12;
            ScorePlayerTeam var15 = var5.getPlayersTeam(var14.getPlayerName());
            String var16 = ScorePlayerTeam.formatPlayerName(var15, var14.getPlayerName());
            String var17 = EnumChatFormatting.RED + "" + var14.getScorePoints();
            int var19 = var23 - var12 * p_96136_4_.FONT_HEIGHT;
            int var20 = p_96136_3_ - var24 + 2;
            drawRect(var25 - 2, var19, var20, var19 + p_96136_4_.FONT_HEIGHT, 1342177280);
            p_96136_4_.drawString(var16, var25, var19, 553648127);
            p_96136_4_.drawString(var17, var20 - p_96136_4_.getStringWidth(var17), var19, 553648127);
            if(var12 == var6.size()) {
               String var21 = p_96136_1_.getDisplayName();
               drawRect(var25 - 2, var19 - p_96136_4_.FONT_HEIGHT - 1, var20, var19 - 1, 1610612736);
               drawRect(var25 - 2, var19 - 1, var20, var19, 1342177280);
               p_96136_4_.drawString(var21, var25 + var7 / 2 - p_96136_4_.getStringWidth(var21) / 2, var19 - p_96136_4_.FONT_HEIGHT, 553648127);
            }
         }
      }
   }

   private void func_110327_a(int p_110327_1_, int p_110327_2_) {
      boolean var3 = this.mc.thePlayer.hurtResistantTime / 3 % 2 == 1;
      if(this.mc.thePlayer.hurtResistantTime < 10) {
         var3 = false;
      }

      int var4 = MathHelper.ceiling_float_int(this.mc.thePlayer.getHealth());
      int var5 = MathHelper.ceiling_float_int(this.mc.thePlayer.prevHealth);
      this.rand.setSeed((long)(this.updateCounter * 312871));
      boolean var6 = false;
      FoodStats var7 = this.mc.thePlayer.getFoodStats();
      int var8 = var7.getFoodLevel();
      int var9 = var7.getPrevFoodLevel();
      IAttributeInstance var10 = this.mc.thePlayer.getEntityAttribute(SharedMonsterAttributes.maxHealth);
      int var11 = p_110327_1_ / 2 - 91;
      int var12 = p_110327_1_ / 2 + 91;
      int var13 = p_110327_2_ - 39;
      float var14 = (float)var10.getAttributeValue();
      float var15 = this.mc.thePlayer.getAbsorptionAmount();
      int var16 = MathHelper.ceiling_float_int((var14 + var15) / 2.0F / 10.0F);
      int var17 = Math.max(10 - (var16 - 2), 3);
      int var18 = var13 - (var16 - 1) * var17 - 10;
      float var19 = var15;
      int var20 = this.mc.thePlayer.getTotalArmorValue();
      int var21 = -1;
      if(this.mc.thePlayer.isPotionActive(Potion.regeneration)) {
         var21 = this.updateCounter % MathHelper.ceiling_float_int(var14 + 5.0F);
      }

      int var25;
      int var26;
      if(GameSettings.protectarmor_value == 0) {
         this.mc.mcProfiler.startSection("armor");

         for(var25 = 0; var25 < 10; ++var25) {
            if(var20 > 0) {
               var26 = var11 + var25 * 8;
               if(var25 * 2 + 1 < var20) {
                  this.drawTexturedModalRect(var26, var18, 34, 9, 9, 9);
               }

               if(var25 * 2 + 1 == var20) {
                  this.drawTexturedModalRect(var26, var18, 25, 9, 9, 9);
               }

               if(var25 * 2 + 1 > var20) {
                  this.drawTexturedModalRect(var26, var18, 16, 9, 9, 9);
               }
            }
         }

         if(var20 > 20) {
            for(var25 = 0; var25 < 10; ++var25) {
               if(var20 > 0) {
                  var26 = var11 + var25 * 8;
                  if(var25 * 2 + 1 + 20 < var20) {
                     this.drawTexturedModalRect(var26, var18 - 9, 142, 9, 9, 9);
                  }

                  if(var25 * 2 + 1 + 20 == var20) {
                     this.drawTexturedModalRect(var26, var18 - 9, 133, 9, 9, 9);
                  }

                  if(var25 * 2 + 1 + 20 > var20) {
                     this.drawTexturedModalRect(var26, var18 - 9, 124, 9, 9, 9);
                  }
               }
            }
         }
      }

      if(GameSettings.protectarmor_value == 1) {
         this.mc.mcProfiler.startSection("armor");

         for(var25 = 0; var25 < 10; ++var25) {
            if(var20 > 0) {
               var26 = var11 + var25 * 8;
               if(var25 * 2 + 1 < var20) {
                  this.drawTexturedModalRect(var26, var18, 34, 9, 9, 9);
               }

               if(var25 * 2 + 1 == var20) {
                  this.drawTexturedModalRect(var26, var18, 25, 9, 9, 9);
               }

               if(var25 * 2 + 1 > var20) {
                  this.drawTexturedModalRect(var26, var18, 16, 9, 9, 9);
               }
            }
         }

         if(var20 > 20) {
            for(var25 = 0; var25 < 10; ++var25) {
               if(var20 > 0) {
                  var26 = var11 + var25 * 8;
                  if(var25 * 2 + 1 + 20 < var20) {
                     this.drawTexturedModalRect(var26, var18, 142, 9, 9, 9);
                  }

                  if(var25 * 2 + 1 + 20 == var20) {
                     this.drawTexturedModalRect(var26, var18, 133, 9, 4, 9);
                  }
               }
            }
         }
      }

      if(GameSettings.protectarmor_value == 2) {
         this.mc.mcProfiler.startSection("armor");

         for(var25 = 0; var25 < 10; ++var25) {
            if(var20 > 0) {
               var26 = var11 + var25 * 8;
               if(var25 * 2 + 1 < var20) {
                  this.drawTexturedModalRect(var26, var18, 34, 9, 9, 9);
               }

               if(var25 * 2 + 1 == var20) {
                  this.drawTexturedModalRect(var26, var18, 25, 9, 9, 9);
               }

               if(var25 * 2 + 1 > var20) {
                  this.drawTexturedModalRect(var26, var18, 16, 9, 9, 9);
               }
            }
         }

         if(var20 > 20) {
            for(var25 = 0; var25 < 10; ++var25) {
               if(var20 > 0) {
                  var26 = var11 + var25 * 8;
                  if(var25 * 2 + 1 + 20 < var20) {
                     this.drawTexturedModalRect(var26 + 80, var18, 142, 9, 9, 9);
                  }

                  if(var25 * 2 + 1 + 20 == var20) {
                     this.drawTexturedModalRect(var26 + 80, var18, 133, 9, 9, 9);
                  }
               }
            }
         }
      }

      if(GameSettings.protectarmor_value == 3) {
         this.mc.mcProfiler.startSection("armor");
         if(var20 > 20) {
            for(var25 = 0; var25 < 10; ++var25) {
               if(var20 > 0) {
                  var26 = var11 + var25 * 8;
                  if(var25 * 2 + 1 + 20 < var20) {
                     this.drawTexturedModalRect(var26, var18, 142, 9, 9, 9);
                  }

                  if(var25 * 2 + 1 + 20 == var20) {
                     this.drawTexturedModalRect(var26, var18, 133, 9, 9, 9);
                  }

                  if(var25 * 2 + 1 + 20 > var20) {
                     this.drawTexturedModalRect(var26, var18, 124, 9, 9, 9);
                  }
               }
            }
         } else {
            for(var25 = 0; var25 < 10; ++var25) {
               if(var20 > 0) {
                  var26 = var11 + var25 * 8;
                  if(var25 * 2 + 1 < var20) {
                     this.drawTexturedModalRect(var26, var18, 34, 9, 9, 9);
                  }

                  if(var25 * 2 + 1 == var20) {
                     this.drawTexturedModalRect(var26, var18, 25, 9, 9, 9);
                  }

                  if(var25 * 2 + 1 > var20) {
                     this.drawTexturedModalRect(var26, var18, 16, 9, 9, 9);
                  }
               }
            }
         }
      }

      this.mc.mcProfiler.endStartSection("health");

      int var27;
      int var23;
      byte var40;
      for(int var38 = MathHelper.ceiling_float_int((var14 + var15) / 2.0F) - 1; var38 >= 0; --var38) {
         var23 = 16;
         if(this.mc.thePlayer.isPotionActive(Potion.poison)) {
            var23 += 36;
         } else if(this.mc.thePlayer.isPotionActive(Potion.wither)) {
            var23 += 72;
         }

         byte var391 = 0;
         if(var3) {
            var391 = 1;
         }

         var25 = MathHelper.ceiling_float_int((float)(var38 + 1) / 10.0F) - 1;
         var26 = var11 + var38 % 10 * 8;
         var27 = var13 - var25 * var17;
         if(var4 <= 4) {
            var27 += this.rand.nextInt(2);
         }

         if(var38 == var21) {
            var27 -= 2;
         }

         var40 = 0;
         if(this.mc.theWorld.getWorldInfo().isHardcoreModeEnabled()) {
            var40 = 5;
         }

         this.drawTexturedModalRect(var26, var27, 16 + var391 * 9, 9 * var40, 9, 9);
         if(var3) {
            if(var38 * 2 + 1 < var5) {
               this.drawTexturedModalRect(var26, var27, var23 + 54, 9 * var40, 9, 9);
            }

            if(var38 * 2 + 1 == var5) {
               this.drawTexturedModalRect(var26, var27, var23 + 63, 9 * var40, 9, 9);
            }
         }

         if(var19 > 0.0F) {
            if(var19 == var15 && var15 % 2.0F == 1.0F) {
               this.drawTexturedModalRect(var26, var27, var23 + 153, 9 * var40, 9, 9);
            } else {
               this.drawTexturedModalRect(var26, var27, var23 + 144, 9 * var40, 9, 9);
            }

            var19 -= 2.0F;
         } else {
            if(var38 * 2 + 1 < var4) {
               this.drawTexturedModalRect(var26, var27, var23 + 36, 9 * var40, 9, 9);
            }

            if(var38 * 2 + 1 == var4) {
               this.drawTexturedModalRect(var26, var27, var23 + 45, 9 * var40, 9, 9);
            }
         }
      }

      Entity var36 = this.mc.thePlayer.ridingEntity;
      int var371;
      if(var36 == null) {
         this.mc.mcProfiler.endStartSection("food");

         for(var23 = 0; var23 < 10; ++var23) {
            var371 = var13;
            var25 = 16;
            var40 = 0;
            if(this.mc.thePlayer.isPotionActive(Potion.hunger)) {
               var25 += 36;
               var40 = 13;
            }

            if(this.mc.thePlayer.getFoodStats().getSaturationLevel() <= 0.0F && this.updateCounter % (var8 * 3 + 1) == 0) {
               var371 = var13 + (this.rand.nextInt(3) - 1);
            }

            if(var6) {
               var40 = 1;
            }

            var27 = var12 - var23 * 8 - 9;
            this.drawTexturedModalRect(var27, var371, 16 + var40 * 9, 27, 9, 9);
            if(var6) {
               if(var23 * 2 + 1 < var9) {
                  this.drawTexturedModalRect(var27, var371, var25 + 54, 27, 9, 9);
               }

               if(var23 * 2 + 1 == var9) {
                  this.drawTexturedModalRect(var27, var371, var25 + 63, 27, 9, 9);
               }
            }

            if(var23 * 2 + 1 < var8) {
               this.drawTexturedModalRect(var27, var371, var25 + 36, 27, 9, 9);
            }

            if(var23 * 2 + 1 == var8) {
               this.drawTexturedModalRect(var27, var371, var25 + 45, 27, 9, 9);
            }
         }
      } else if(var36 instanceof EntityLivingBase) {
         this.mc.mcProfiler.endStartSection("mountHealth");
         EntityLivingBase var381 = (EntityLivingBase)var36;
         var371 = (int)Math.ceil((double)var381.getHealth());
         float var37 = var381.getMaxHealth();
         var26 = (int)(var37 + 0.5F) / 2;
         if(var26 > 30) {
            var26 = 30;
         }

         var27 = var13;

         for(int var39 = 0; var26 > 0; var39 += 20) {
            int var29 = Math.min(var26, 10);
            var26 -= var29;

            for(int var30 = 0; var30 < var29; ++var30) {
               byte var31 = 52;
               byte var32 = 0;
               if(var6) {
                  var32 = 1;
               }

               int var33 = var12 - var30 * 8 - 9;
               this.drawTexturedModalRect(var33, var27, var31 + var32 * 9, 9, 9, 9);
               if(var30 * 2 + 1 + var39 < var371) {
                  this.drawTexturedModalRect(var33, var27, var31 + 36, 9, 9, 9);
               }

               if(var30 * 2 + 1 + var39 == var371) {
                  this.drawTexturedModalRect(var33, var27, var31 + 45, 9, 9, 9);
               }
            }

            var27 -= 10;
         }
      }

      this.mc.mcProfiler.endStartSection("air");
      if(this.mc.thePlayer.isInsideOfMaterial(Material.water)) {
         var23 = this.mc.thePlayer.getAir();
         var371 = MathHelper.ceiling_double_int((double)(var23 - 2) * 10.0D / 300.0D);
         var25 = MathHelper.ceiling_double_int((double)var23 * 10.0D / 300.0D) - var371;

         for(var26 = 0; var26 < var371 + var25; ++var26) {
            if(var26 < var371) {
               this.drawTexturedModalRect(var12 - var26 * 8 - 9, var18, 16, 18, 9, 9);
            } else {
               this.drawTexturedModalRect(var12 - var26 * 8 - 9, var18, 25, 18, 9, 9);
            }
         }
      }

      this.mc.mcProfiler.endSection();
   }

   private void renderBossHealth() {
      if(BossStatus.bossName != null && BossStatus.statusBarTime > 0) {
         --BossStatus.statusBarTime;
         FontRenderer var1 = this.mc.fontRenderer;
         ScaledResolution var2 = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
         int var3 = var2.getScaledWidth();
         short var4 = 182;
         int var5 = var3 / 2 - var4 / 2;
         int var6 = (int)(BossStatus.healthScale * (float)(var4 + 1));
         byte var7 = 12;
         this.drawTexturedModalRect(var5, var7, 0, 74, var4, 5);
         this.drawTexturedModalRect(var5, var7, 0, 74, var4, 5);
         if(var6 > 0) {
            this.drawTexturedModalRect(var5, var7, 0, 79, var6, 5);
         }

         String var8 = BossStatus.bossName;
         var1.drawStringWithShadow(var8, var3 / 2 - var1.getStringWidth(var8) / 2, var7 - 10, 16777215);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         this.mc.getTextureManager().bindTexture(icons);
      }
   }

   private void renderPumpkinBlur(int p_73836_1_, int p_73836_2_) {
      GL11.glDisable(GL11.GL_DEPTH_TEST);
      GL11.glDepthMask(false);
      OpenGlHelper.glBlendFunc(770, 771, 1, 0);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glDisable(GL11.GL_ALPHA_TEST);
      this.mc.getTextureManager().bindTexture(pumpkinBlurTexPath);
      Tessellator var3 = Tessellator.instance;
      var3.startDrawingQuads();
      var3.addVertexWithUV(0.0D, (double)p_73836_2_, -90.0D, 0.0D, 1.0D);
      var3.addVertexWithUV((double)p_73836_1_, (double)p_73836_2_, -90.0D, 1.0D, 1.0D);
      var3.addVertexWithUV((double)p_73836_1_, 0.0D, -90.0D, 1.0D, 0.0D);
      var3.addVertexWithUV(0.0D, 0.0D, -90.0D, 0.0D, 0.0D);
      var3.draw();
      GL11.glDepthMask(true);
      GL11.glEnable(GL11.GL_DEPTH_TEST);
      GL11.glEnable(GL11.GL_ALPHA_TEST);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
   }

   private void renderVignette(float p_73829_1_, int p_73829_2_, int p_73829_3_) {
      p_73829_1_ = 1.0F - p_73829_1_;
      if(p_73829_1_ < 0.0F) {
         p_73829_1_ = 0.0F;
      }

      if(p_73829_1_ > 1.0F) {
         p_73829_1_ = 1.0F;
      }

      this.prevVignetteBrightness = (float)((double)this.prevVignetteBrightness + (double)(p_73829_1_ - this.prevVignetteBrightness) * 0.01D);
      GL11.glDisable(GL11.GL_DEPTH_TEST);
      GL11.glDepthMask(false);
      OpenGlHelper.glBlendFunc(0, 769, 1, 0);
      GL11.glColor4f(this.prevVignetteBrightness, this.prevVignetteBrightness, this.prevVignetteBrightness, 1.0F);
      this.mc.getTextureManager().bindTexture(vignetteTexPath);
      Tessellator var4 = Tessellator.instance;
      var4.startDrawingQuads();
      var4.addVertexWithUV(0.0D, (double)p_73829_3_, -90.0D, 0.0D, 1.0D);
      var4.addVertexWithUV((double)p_73829_2_, (double)p_73829_3_, -90.0D, 1.0D, 1.0D);
      var4.addVertexWithUV((double)p_73829_2_, 0.0D, -90.0D, 1.0D, 0.0D);
      var4.addVertexWithUV(0.0D, 0.0D, -90.0D, 0.0D, 0.0D);
      var4.draw();
      GL11.glDepthMask(true);
      GL11.glEnable(GL11.GL_DEPTH_TEST);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      OpenGlHelper.glBlendFunc(770, 771, 1, 0);
   }

   private void func_130015_b(float p_130015_1_, int p_130015_2_, int p_130015_3_) {
      if(p_130015_1_ < 1.0F) {
         p_130015_1_ *= p_130015_1_;
         p_130015_1_ *= p_130015_1_;
         p_130015_1_ = p_130015_1_ * 0.8F + 0.2F;
      }

      GL11.glDisable(GL11.GL_ALPHA_TEST);
      GL11.glDisable(GL11.GL_DEPTH_TEST);
      GL11.glDepthMask(false);
      OpenGlHelper.glBlendFunc(770, 771, 1, 0);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, p_130015_1_);
      IIcon var4 = Blocks.portal.getBlockTextureFromSide(1);
      this.mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
      float var5 = var4.getMinU();
      float var6 = var4.getMinV();
      float var7 = var4.getMaxU();
      float var8 = var4.getMaxV();
      Tessellator var9 = Tessellator.instance;
      var9.startDrawingQuads();
      var9.addVertexWithUV(0.0D, (double)p_130015_3_, -90.0D, (double)var5, (double)var8);
      var9.addVertexWithUV((double)p_130015_2_, (double)p_130015_3_, -90.0D, (double)var7, (double)var8);
      var9.addVertexWithUV((double)p_130015_2_, 0.0D, -90.0D, (double)var7, (double)var6);
      var9.addVertexWithUV(0.0D, 0.0D, -90.0D, (double)var5, (double)var6);
      var9.draw();
      GL11.glDepthMask(true);
      GL11.glEnable(GL11.GL_DEPTH_TEST);
      GL11.glEnable(GL11.GL_ALPHA_TEST);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
   }

   private void renderInventorySlot(int p_73832_1_, int p_73832_2_, int p_73832_3_, float p_73832_4_) {
      ItemStack var5 = this.mc.thePlayer.inventory.mainInventory[p_73832_1_];
      if(var5 != null) {
         float var6 = (float)var5.animationsToGo - p_73832_4_;
         if(var6 > 0.0F) {
            GL11.glPushMatrix();
            float var7 = 1.0F + var6 / 5.0F;
            GL11.glTranslatef((float)(p_73832_2_ + 8), (float)(p_73832_3_ + 12), 0.0F);
            GL11.glScalef(1.0F / var7, (var7 + 1.0F) / 2.0F, 1.0F);
            GL11.glTranslatef((float)(-(p_73832_2_ + 8)), (float)(-(p_73832_3_ + 12)), 0.0F);
         }

         itemRenderer.renderItemAndEffectIntoGUI(this.mc.fontRenderer, this.mc.getTextureManager(), var5, p_73832_2_, p_73832_3_);
         if(var6 > 0.0F) {
            GL11.glPopMatrix();
         }

         itemRenderer.renderItemOverlayIntoGUI(this.mc.fontRenderer, this.mc.getTextureManager(), var5, p_73832_2_, p_73832_3_);
      }
   }

   private void renderArmorSlot(int p_73832_1_, int p_73832_2_, int p_73832_3_, float p_73832_4_) {
      ItemStack var5 = this.mc.thePlayer.inventory.armorItemInSlot(p_73832_1_);
      if(var5 != null) {
         float var6 = (float)var5.animationsToGo - p_73832_4_;
         if(var6 > 0.0F) {
            GL11.glPushMatrix();
            float var7 = 1.0F + var6 / 5.0F;
            GL11.glTranslatef((float)(p_73832_2_ + 8), (float)(p_73832_3_ + 12), 0.0F);
            GL11.glScalef(1.0F / var7, (var7 + 1.0F) / 2.0F, 1.0F);
            GL11.glTranslatef((float)(-(p_73832_2_ + 8)), (float)(-(p_73832_3_ + 12)), 0.0F);
         }

         itemRenderer.renderItemAndEffectIntoGUI(this.mc.fontRenderer, this.mc.getTextureManager(), var5, p_73832_2_, p_73832_3_);
         if(var6 > 0.0F) {
            GL11.glPopMatrix();
         }
      }
   }

   private void renderMainSlot(int p_73832_2_, int p_73832_3_, float p_73832_4_) {
      ItemStack var5 = this.mc.thePlayer.getHeldItem();
      if(var5 != null && var5.isItemDamaged()) {
         float var6 = (float)var5.animationsToGo - p_73832_4_;
         if(var6 > 0.0F) {
            GL11.glPushMatrix();
            float var7 = 1.0F + var6 / 5.0F;
            GL11.glTranslatef((float)(p_73832_2_ + 8), (float)(p_73832_3_ + 12), 0.0F);
            GL11.glScalef(1.0F / var7, (var7 + 1.0F) / 2.0F, 1.0F);
            GL11.glTranslatef((float)(-(p_73832_2_ + 8)), (float)(-(p_73832_3_ + 12)), 0.0F);
         }

         if(var6 > 0.0F) {
            GL11.glPopMatrix();
         }
      }
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

         itemRenderer.renderItemAndEffectIntoGUI(this.mc.fontRenderer, this.mc.getTextureManager(), stack, p_73832_2_, p_73832_3_);
         if(var6 > 0.0F) {
            GL11.glPopMatrix();
         }
      }
   }

   public void updateTick() {
      if(this.recordPlayingUpFor > 0) {
         --this.recordPlayingUpFor;
      }

      ArrayList toRemove = new ArrayList();
      Iterator var1 = this.messages.iterator();

      while(var1.hasNext()) {
         GuiGUIMessage var4 = (GuiGUIMessage)var1.next();
         if(var4.displayTimer > 0) {
            --var4.displayTimer;
            ++var4.elapsedTimer;
         } else {
            toRemove.add(var4);
         }
      }

      this.messages.removeAll(toRemove);
      ++this.updateCounter;
      this.field_152127_m.func_152439_a();
      if(this.mc.thePlayer != null) {
         ItemStack var41 = this.mc.thePlayer.inventory.getCurrentItem();
         if(var41 == null) {
            this.remainingHighlightTicks = 0;
         } else if(this.highlightingItemStack != null && var41.getItem() == this.highlightingItemStack.getItem() && ItemStack.areItemStackTagsEqual(var41, this.highlightingItemStack) && (var41.isItemStackDamageable() || var41.getItemDamage() == this.highlightingItemStack.getItemDamage())) {
            if(this.remainingHighlightTicks > 0) {
               --this.remainingHighlightTicks;
            }
         } else {
            this.remainingHighlightTicks = 40;
         }

         this.highlightingItemStack = var41;
      }
   }

   public void setRecordPlayingMessage(String p_73833_1_) {
      this.func_110326_a(I18n.format("record.nowPlaying", new Object[]{p_73833_1_}), true);
   }

   public void func_110326_a(String p_110326_1_, boolean p_110326_2_) {
      this.recordPlaying = p_110326_1_;
      this.recordPlayingUpFor = 60;
      this.recordIsPlaying = p_110326_2_;
   }

   public GuiNewChat getChatGUI() {
      return this.persistantChatGUI;
   }

   public int getUpdateCounter() {
      return this.updateCounter;
   }

   public void drawPotionStatus(int x, int y) {
      if(!this.mc.thePlayer.getActivePotionEffects().isEmpty()) {
         GL11.glDisable(GL11.GL_LIGHTING);

         for(Iterator var6 = this.mc.thePlayer.getActivePotionEffects().iterator(); var6.hasNext(); y += 20) {
            PotionEffect var7 = (PotionEffect)var6.next();
            Potion var8 = Potion.potionTypes[var7.getPotionID()];
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.mc.getTextureManager().bindTexture(inventoryPath);
            if(var8.hasStatusIcon()) {
               GL11.glPushMatrix();
               GL11.glScalef(0.85F, 0.85F, 1.0F);
               int x1 = var8.getStatusIconIndex();
               this.drawTexturedModalRect((int)((float)(x + 1) / 0.85F), (int)((float)(y - 12) / 0.85F), 0 + x1 % 8 * 18, 198 + x1 / 8 * 18, 18, 18);
               GL11.glPopMatrix();
            }

            String var81 = I18n.format(var8.getName(), new Object[0]);
            if(var7.getAmplifier() == 1) {
               var81 = var81 + " II";
            } else if(var7.getAmplifier() == 2) {
               var81 = var81 + " III";
            } else if(var7.getAmplifier() == 3) {
               var81 = var81 + " IV";
            }

            for(int pot = 25; pot > 80; ++pot) {
               if(var8.getId() != pot) {
                  this.mc.fontRenderer.drawString(var81, x + 18, y - 13, var8.getLiquidColor());
                  this.mc.fontRenderer.drawString(EnumChatFormatting.WHITE + Potion.getDurationString(var7), x + 18, y - 3, 8355711);
               }
            }
         }
      }
   }

   private void drawBetterSprintStatus(int x, int y) {
      if(CustomMovementInput.sprintToggle) {
         this.mc.fontRenderer.drawString(ChatFormatting.BLUE + "[" + ChatFormatting.AQUA + "Sprint" + ChatFormatting.BLUE + "]", x, y, 16777215);
      } else if(CustomMovementInput.sneakToggle) {
         this.mc.fontRenderer.drawString(ChatFormatting.DARK_RED + "[" + ChatFormatting.RED + "Sneak" + ChatFormatting.DARK_RED + "]", x, y, 16777215);
      }
   }

   private void renderEffect(FontRenderer var8, int x, int y) {
      int j = 0;
      if(!this.mc.thePlayer.getActivePotionEffects().isEmpty()) {
         for(Iterator var12 = this.mc.thePlayer.getActivePotionEffects().iterator(); var12.hasNext(); ++j) {
            PotionEffect var15 = (PotionEffect)var12.next();
            Potion var14 = Potion.potionTypes[var15.getPotionID()];
            this.mc.getTextureManager().bindTexture(inventoryPath);
            if(var14.hasStatusIcon()) {
               int var13 = var14.getStatusIconIndex();
               this.drawTexturedModalRect(x, y + 18 * j, 0 + var13 % 8 * 18, 198 + var13 / 8 * 18, 18, 18);
            }

            String var10 = Potion.getDurationString(var15);
            if(var14.hasStatusIcon()) {
               int stringPotion = var14.getStatusIconIndex();
               var8.drawString(var10, x + 20, y + 5 + 18 * j, 16777215);
            }
         }
      }
   }

   private void showHUD(FontRenderer var8, int x, int y) {
      ItemStack casque = this.mc.thePlayer.inventory.armorItemInSlot(3);
      ItemStack plastron = this.mc.thePlayer.inventory.armorItemInSlot(2);
      ItemStack pantalon = this.mc.thePlayer.inventory.armorItemInSlot(1);
      ItemStack bottes = this.mc.thePlayer.inventory.armorItemInSlot(0);
      ItemStack main = this.mc.thePlayer.inventory.getCurrentItem();
      int j = 0;
      if(!this.mc.gameSettings.showDebugInfo) {
         int var6;
         String var7;
         String thePotion;
         ScaledResolution var5;
         int aa;
         int bb;
         int var12;
         int var13;
         ItemStack is;
         double percent;
         DecimalFormatSymbols dfs;
         DecimalFormat df;
         int color;
         int i;
         if(casque != null) {
            thePotion = "";
            var5 = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
            aa = var5.getScaledWidth();
            bb = var5.getScaledHeight();
            var12 = x * 2 - y * 2;
            var13 = y - 16 - 72;
            if(GameSettings.armorstyle) {
               var6 = this.mc.thePlayer.inventory.armorItemInSlot(3).getMaxDamage() - this.mc.thePlayer.inventory.armorItemInSlot(3).getItemDamageForDisplay();
               var7 = "";
               if(var6 <= 100) {
                  var7 = "\u00a7c";
               }

               if(var6 > 100) {
                  var7 = "\u00a7a";
               }

               thePotion = var7 + var6;
            }

            if(!GameSettings.armorstyle) {
               dfs = new DecimalFormatSymbols();
               dfs.setDecimalSeparator('.');
               df = new DecimalFormat("#.##", dfs);

               for(i = 0; i < 4; ++i) {
                  is = this.mc.thePlayer.inventory.armorItemInSlot(3);
                  percent = 100.0D * (double)(is.getMaxDamage() - is.getItemDamage()) / (double)is.getMaxDamage();
                  color = percent < 25.0D?-65536:(percent < 50.0D?-26368:-16711936);
                  var8.drawString(df.format(percent) + "%", x + 20, y + 5, color);
               }
            }

            var8.drawString(thePotion, x + 20, y + 4 + 20 * j, 16777215);
            itemRenderer.renderItemIntoGUI(var8, this.mc.getTextureManager(), casque, x, y + 18 * j);
            GL11.glDisable(GL11.GL_LIGHTING);
            ++j;
         }

         ItemStack var25;
         double var26;
         DecimalFormatSymbols var27;
         DecimalFormat var28;
         int var29;
         int var30;
         if(plastron != null) {
            thePotion = "";
            if(GameSettings.armorstyle) {
               var6 = this.mc.thePlayer.inventory.armorItemInSlot(2).getMaxDamage() - this.mc.thePlayer.inventory.armorItemInSlot(2).getItemDamageForDisplay();
               var7 = "";
               if(var6 <= 100) {
                  var7 = "\u00a7c";
               }

               if(var6 > 100) {
                  var7 = "\u00a7a";
               }

               thePotion = var7 + var6;
            }

            if(!GameSettings.armorstyle) {
               var27 = new DecimalFormatSymbols();
               var27.setDecimalSeparator('.');
               var28 = new DecimalFormat("#.##", var27);

               for(var30 = 0; var30 < 4; ++var30) {
                  var25 = this.mc.thePlayer.inventory.armorItemInSlot(2);
                  var26 = 100.0D * (double)(var25.getMaxDamage() - var25.getItemDamage()) / (double)var25.getMaxDamage();
                  var29 = var26 < 25.0D?-65536:(var26 < 50.0D?-26368:-16711936);
                  var8.drawString(var28.format(var26) + "%", x + 20, y + 23 * j, var29);
               }
            }

            var8.drawString(thePotion, x + 20, y + 23 * j, 16777215);
            itemRenderer.renderItemIntoGUI(var8, this.mc.getTextureManager(), plastron, x, y + 18 * j);
            GL11.glDisable(GL11.GL_LIGHTING);
            ++j;
         }

         if(pantalon != null) {
            thePotion = "";
            if(GameSettings.armorstyle) {
               var6 = this.mc.thePlayer.inventory.armorItemInSlot(1).getMaxDamage() - this.mc.thePlayer.inventory.armorItemInSlot(1).getItemDamageForDisplay();
               var7 = "";
               if(var6 <= 100) {
                  var7 = "\u00a7c";
               }

               if(var6 > 100) {
                  var7 = "\u00a7a";
               }

               thePotion = var7 + var6;
            }

            if(!GameSettings.armorstyle) {
               var27 = new DecimalFormatSymbols();
               var27.setDecimalSeparator('.');
               var28 = new DecimalFormat("#.##", var27);

               for(var30 = 0; var30 < 4; ++var30) {
                  var25 = this.mc.thePlayer.inventory.armorItemInSlot(1);
                  var26 = 100.0D * (double)(var25.getMaxDamage() - var25.getItemDamage()) / (double)var25.getMaxDamage();
                  var29 = var26 < 25.0D?-65536:(var26 < 50.0D?-26368:-16711936);
                  var8.drawString(var28.format(var26) + "%", x + 20, y + 20 * j, var29);
               }
            }

            var8.drawString(thePotion, x + 20, y - 5 + 23 * j, 16777215);
            itemRenderer.renderItemIntoGUI(var8, this.mc.getTextureManager(), pantalon, x, y + 18 * j);
            GL11.glDisable(GL11.GL_LIGHTING);
            ++j;
         }

         if(bottes != null) {
            thePotion = "";
            if(GameSettings.armorstyle) {
               var6 = this.mc.thePlayer.inventory.armorItemInSlot(0).getMaxDamage() - this.mc.thePlayer.inventory.armorItemInSlot(0).getItemDamageForDisplay();
               var7 = "";
               if(var6 <= 100) {
                  var7 = "\u00a7c";
               }

               if(var6 > 100) {
                  var7 = "\u00a7a";
               }

               thePotion = var7 + var6;
            }

            if(!GameSettings.armorstyle) {
               var27 = new DecimalFormatSymbols();
               var27.setDecimalSeparator('.');
               var28 = new DecimalFormat("#.##", var27);

               for(var30 = 0; var30 < 4; ++var30) {
                  var25 = this.mc.thePlayer.inventory.armorItemInSlot(0);
                  var26 = 100.0D * (double)(var25.getMaxDamage() - var25.getItemDamage()) / (double)var25.getMaxDamage();
                  var29 = var26 < 25.0D?-65536:(var26 < 50.0D?-26368:-16711936);
                  var8.drawString(var28.format(var26) + "%", x + 20, y + 19 * j, var29);
               }
            }

            var8.drawString(thePotion, x + 20, y - 11 + 23 * j, 16777215);
            itemRenderer.renderItemIntoGUI(var8, this.mc.getTextureManager(), bottes, x, y + 18 * j);
            GL11.glDisable(GL11.GL_LIGHTING);
            ++j;
         }

         if(main != null && (main.getItem() instanceof ItemSword || main.getItem() instanceof ItemBow || main.getItem() instanceof ItemTool || main.getItem() instanceof ItemFlintAndSteel || main.getItem() instanceof ItemShears)) {
            if(GameSettings.armorstyle) {
               var6 = this.mc.thePlayer.inventory.getCurrentItem().getMaxDamage() - this.mc.thePlayer.inventory.getCurrentItem().getItemDamageForDisplay();
               var7 = "";
               if(var6 <= 100) {
                  var7 = "\u00a7c";
               }

               var5 = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
               aa = var5.getScaledWidth();
               bb = var5.getScaledHeight();
               var12 = aa / 2 - 90 + 180 + 6;
               var13 = bb - 16;
               thePotion = var7 + var6;
               var8.drawString(thePotion, var12, var13, 16777215);
               GL11.glDisable(GL11.GL_LIGHTING);
               ++j;
            }

            if(!GameSettings.armorstyle) {
               var5 = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
               aa = var5.getScaledWidth();
               bb = var5.getScaledHeight();
               var12 = aa / 2 - 90 + 180 + 6;
               var13 = bb - 16;
               dfs = new DecimalFormatSymbols();
               dfs.setDecimalSeparator('.');
               df = new DecimalFormat("#.##", dfs);

               for(i = 0; i < 4; ++i) {
                  is = this.mc.thePlayer.inventory.getCurrentItem();
                  percent = 100.0D * (double)(is.getMaxDamage() - is.getItemDamage()) / (double)is.getMaxDamage();
                  color = percent < 25.0D?-65536:(percent < 50.0D?-26368:-16711936);
                  var8.drawString(df.format(percent) + "%", var12, var13, color);
               }
            }
         }
      }
   }

   static {
      boolean b = false;

      try {
         Class.forName("reifnsk.minimap.ReiMinimap");
         b = true;
      } catch (Exception var2) {
         ;
      }

      REI_MINIMAP = b;
   }
}
