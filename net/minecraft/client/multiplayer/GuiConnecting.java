package net.minecraft.client.multiplayer;

import io.netty.util.concurrent.GenericFutureListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.network.NetHandlerLoginClient;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.network.login.client.C00PacketLoginStart;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.Charsets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GuiConnecting extends GuiScreen {
   private static final AtomicInteger field_146372_a = new AtomicInteger(0);
   private static final Logger logger = LogManager.getLogger();
   private NetworkManager field_146371_g;
   private boolean field_146373_h;
   private final GuiScreen field_146374_i;
   public int cooldownTimer;
   public boolean canLogin;
   public boolean isLogging = true;
   public String host;
   public int port;
   public String splashText = "Vous \u00eates sur ObsiFight !";
   private static final ResourceLocation splashTexts = new ResourceLocation("texts/splashesLoading.txt");
   private static final String __OBFID = "CL_00000685";
   private static final String __OBFID = "CL_00000685";

   public GuiConnecting(GuiScreen p_i1181_1_, Minecraft p_i1181_2_, ServerData p_i1181_3_) {
      mc = p_i1181_2_;
      this.field_146374_i = p_i1181_1_;
      ServerAddress var4 = ServerAddress.func_78860_a(p_i1181_3_.serverIP);
      p_i1181_2_.loadWorld((WorldClient)null);
      p_i1181_2_.setServerData(p_i1181_3_);
      this.host = var4.getIP();
      this.port = var4.getPort();
   }

   public GuiConnecting(GuiScreen p_i1182_1_, Minecraft p_i1182_2_, String p_i1182_3_, int p_i1182_4_) {
      mc = p_i1182_2_;
      this.field_146374_i = p_i1182_1_;
      p_i1182_2_.loadWorld((WorldClient)null);
      this.host = p_i1182_3_;
      this.port = p_i1182_4_;
   }

   private void func_146367_a(final String p_146367_1_, final int p_146367_2_) {
      (new Thread("Server Connector #" + field_146372_a.incrementAndGet()) {
         private static final String __OBFID = "CL_00000686";
         private static final String __OBFID = "CL_00000686";

         public void run() {
            InetAddress var1 = null;

            GuiConnecting var10000;
            try {
               if(GuiConnecting.this.field_146373_h) {
                  return;
               }

               var1 = InetAddress.getByName(p_146367_1_);
               GuiConnecting.this.field_146371_g = NetworkManager.provideLanClient(var1, p_146367_2_);
               NetworkManager var7 = GuiConnecting.this.field_146371_g;
               NetHandlerLoginClient var10001 = new NetHandlerLoginClient;
               NetworkManager var10003 = GuiConnecting.this.field_146371_g;
               GuiConnecting var10004 = GuiConnecting.this;
               var10001.<init>(var10003, GuiConnecting.mc, GuiConnecting.this.field_146374_i);
               var7.setNetHandler(var10001);
               GuiConnecting.this.field_146371_g.scheduleOutboundPacket(new C00Handshake(18, p_146367_1_, p_146367_2_, EnumConnectionState.LOGIN), new GenericFutureListener[0]);
               var7 = GuiConnecting.this.field_146371_g;
               C00PacketLoginStart var8 = new C00PacketLoginStart(GuiConnecting.mc.getSession().func_148256_e());
               GuiConnecting var9 = GuiConnecting.this;
               var7.scheduleOutboundPacket(var8, new GenericFutureListener[0]);
            } catch (UnknownHostException var5) {
               if(GuiConnecting.this.field_146373_h) {
                  return;
               }

               GuiConnecting.logger.error("Couldn\'t connect to server", var5);
               var10000 = GuiConnecting.this;
               GuiConnecting.mc.displayGuiScreen(new GuiDisconnected(GuiConnecting.this.field_146374_i, "connect.failed", new ChatComponentTranslation("disconnect.genericReason", new Object[]{"Unknown host"})));
            } catch (Exception var6) {
               if(GuiConnecting.this.field_146373_h) {
                  return;
               }

               GuiConnecting.logger.error("Couldn\'t connect to server", var6);
               String var3 = var6.toString();
               if(var1 != null) {
                  String var4 = var1.toString() + ":" + p_146367_2_;
                  var3 = var3.replaceAll(var4, "");
               }

               var10000 = GuiConnecting.this;
               GuiConnecting.mc.displayGuiScreen(new GuiDisconnected(GuiConnecting.this.field_146374_i, "connect.failed", new ChatComponentTranslation("disconnect.genericReason", new Object[]{var3})));
            }
         }
      }).start();
   }

   public void updateScreen() {
      this.cooldownTimer += 2;
      if(this.field_146371_g != null) {
         if(this.field_146371_g.isChannelOpen()) {
            this.field_146371_g.processReceivedPackets();
         } else if(this.field_146371_g.getExitMessage() != null) {
            this.field_146371_g.getNetHandler().onDisconnect(this.field_146371_g.getExitMessage());
         }
      }
   }

   protected void keyTyped(char p_73869_1_, int p_73869_2_) {}

   public void initGui() {
      this.buttonList.clear();
      this.buttonList.add(new GuiButton(0, this.width / 2 + 120, this.height - 36, 60, 20, I18n.format("Annuler", new Object[0])));
      this.splashText = "";
      BufferedReader var1 = null;
      Random rand = new Random();

      try {
         ArrayList var11 = new ArrayList();
         var1 = new BufferedReader(new InputStreamReader(Minecraft.getMinecraft().getResourceManager().getResource(splashTexts).getInputStream(), Charsets.UTF_8));

         String var3;
         while((var3 = var1.readLine()) != null) {
            var3 = var3.trim();
            if(!var3.isEmpty()) {
               var11.add(var3);
            }
         }

         if(!var11.isEmpty()) {
            do {
               this.splashText = (String)var11.get(rand.nextInt(var11.size()));
            } while(this.splashText.hashCode() == 125780783);
         }
      } catch (IOException var13) {
         ;
      } finally {
         if(var1 != null) {
            try {
               var1.close();
            } catch (IOException var12) {
               ;
            }
         }
      }
   }

   protected void actionPerformed(GuiButton p_146284_1_) {
      if(p_146284_1_.id == 0) {
         this.field_146373_h = true;
         if(this.field_146371_g != null) {
            this.field_146371_g.closeChannel(new ChatComponentText("Aborted"));
         }

         mc.displayGuiScreen(this.field_146374_i);
      }
   }

   public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
      this.drawDefaultBackground();
      mc.getTextureManager().bindTexture(new ResourceLocation("textures/gui/title/chargement.png"));
      Tessellator var9 = Tessellator.instance;
      var9.startDrawingQuads();
      var9.addVertexWithUV(0.0D, 0.0D, (double)this.zLevel, 0.0D, 0.0D);
      var9.addVertexWithUV(0.0D, (double)this.height, (double)this.zLevel, 0.0D, 1.0D);
      var9.addVertexWithUV((double)this.width, (double)this.height, (double)this.zLevel, 1.0D, 1.0D);
      var9.addVertexWithUV((double)this.width, 0.0D, (double)this.zLevel, 1.0D, 0.0D);
      var9.draw();
      this.drawHorizontalLine(0, this.width, this.height - 47, -16777216);
      this.drawHorizontalLine(0, this.width, this.height - 46, -9211021);
      this.drawString(mc.fontRenderer, "\u00a7kiii \u00a76Chargement, veuillez patienter \u00a7f\u00a7kiii ", this.width / 2 - mc.fontRenderer.getStringWidth("Chargement, veuillez patienter") / 2, this.height / 2 - 100, 16777215);
      drawRect(0, this.height - 45, this.width, this.height, -13421773);
      this.drawGradientRect(0, this.height - 45, this.width, this.height, 0, Integer.MIN_VALUE);
      this.drawString(mc.fontRenderer, this.splashText, this.width / 2 - mc.fontRenderer.getStringWidth(this.splashText) / 2 - 100, this.height / 2 - 30, 16777215);
      if(this.cooldownTimer < 100) {
         this.drawCenteredString(mc.fontRenderer, "Connexion en cours ...", this.width / 2, this.height - 43, 16777215);
      } else if(this.cooldownTimer >= 100) {
         if(this.canLogin) {
            this.drawCenteredString(mc.fontRenderer, "Authentification...", this.width / 2, this.height - 43, 16777215);
         } else {
            this.drawCenteredString(mc.fontRenderer, "Une erreur s\'est produite...", this.width / 2, this.height - 43, 16777215);
         }
      }

      if(this.cooldownTimer >= 95 && this.isLogging) {
         this.func_146367_a(this.host, this.port);
         this.canLogin = true;
         this.isLogging = false;
      }

      if(this.cooldownTimer >= 100) {
         drawRect(this.width / 2 - 96, this.height - 32, this.width / 2 + 96, this.height - 22, -41054848);
         drawRect(this.width / 2 - 5, this.height - 31, this.width / 2 + 95, this.height - 23, -268435456);
         drawRect(this.width / 2 - 95, this.height - 31, this.width / 2 + 95, this.height - 23, -267739136);
         mc.fontRenderer.drawString("100 %", this.width / 2 - mc.fontRenderer.getStringWidth("100 %") / 2, this.height - 31, -1);
      } else {
         drawRect(this.width / 2 - 96, this.height - 32, this.width / 2 + 96, this.height - 22, -41054848);
         drawRect(this.width / 2 - 95, this.height - 31, this.width / 2 + 95, this.height - 23, -268435456);
         drawRect(this.width / 2 - 95, this.height - 31, this.width / 2 - 95 + this.cooldownTimer * 2 - 8, this.height - 23, -267739136);
         mc.fontRenderer.drawString(this.cooldownTimer + " %", this.width / 2 - mc.fontRenderer.getStringWidth(this.cooldownTimer + " %") / 2, this.height - 31, -1);
      }

      super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
   }
}
