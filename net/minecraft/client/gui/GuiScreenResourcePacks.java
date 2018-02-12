package net.minecraft.client.gui;

import com.google.common.collect.Lists;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.ResourcePackListEntry;
import net.minecraft.client.resources.ResourcePackListEntryDefault;
import net.minecraft.client.resources.ResourcePackListEntryFound;
import net.minecraft.client.resources.ResourcePackRepository;
import net.minecraft.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.Sys;

public class GuiScreenResourcePacks extends GuiScreen {
   private static final Logger logger = LogManager.getLogger();
   private GuiScreen field_146965_f;
   private List field_146966_g;
   private List field_146969_h;
   private GuiResourcePackAvailable field_146970_i;
   private GuiResourcePackSelected field_146967_r;
   private static final String __OBFID = "CL_00000820";
   private static final String __OBFID = "CL_00000820";

   public GuiScreenResourcePacks(GuiScreen p_i45050_1_) {
      this.field_146965_f = p_i45050_1_;
   }

   public void initGui() {
      this.buttonList.add(new GuiOptionButton(2, this.width / 2 - 154, this.height - 48, I18n.format("resourcePack.openFolder", new Object[0])));
      this.buttonList.add(new GuiOptionButton(1, this.width / 2 + 4, this.height - 48, I18n.format("gui.done", new Object[0])));
      this.field_146966_g = new ArrayList();
      this.field_146969_h = new ArrayList();
      ResourcePackRepository var1 = mc.getResourcePackRepository();
      var1.updateRepositoryEntriesAll();
      ArrayList var2 = Lists.newArrayList(var1.getRepositoryEntriesAll());
      var2.removeAll(var1.getRepositoryEntries());
      Iterator var3 = var2.iterator();

      ResourcePackRepository.Entry var4;
      while(var3.hasNext()) {
         var4 = (ResourcePackRepository.Entry)var3.next();
         this.field_146966_g.add(new ResourcePackListEntryFound(this, var4));
      }

      var3 = Lists.reverse(var1.getRepositoryEntries()).iterator();

      while(var3.hasNext()) {
         var4 = (ResourcePackRepository.Entry)var3.next();
         this.field_146969_h.add(new ResourcePackListEntryFound(this, var4));
      }

      this.field_146969_h.add(new ResourcePackListEntryDefault(this));
      this.field_146970_i = new GuiResourcePackAvailable(mc, 200, this.height, this.field_146966_g);
      this.field_146970_i.func_148140_g(this.width / 2 - 4 - 200);
      this.field_146970_i.func_148134_d(7, 8);
      this.field_146967_r = new GuiResourcePackSelected(mc, 200, this.height, this.field_146969_h);
      this.field_146967_r.func_148140_g(this.width / 2 + 4);
      this.field_146967_r.func_148134_d(7, 8);
   }

   public boolean func_146961_a(ResourcePackListEntry p_146961_1_) {
      return this.field_146969_h.contains(p_146961_1_);
   }

   public List func_146962_b(ResourcePackListEntry p_146962_1_) {
      return this.func_146961_a(p_146962_1_)?this.field_146969_h:this.field_146966_g;
   }

   public List func_146964_g() {
      return this.field_146966_g;
   }

   public List func_146963_h() {
      return this.field_146969_h;
   }

   protected void actionPerformed(GuiButton p_146284_1_) {
      if(p_146284_1_.enabled) {
         if(p_146284_1_.id == 2) {
            File var10 = mc.getResourcePackRepository().getDirResourcepacks();
            String var11 = var10.getAbsolutePath();
            if(Util.getOSType() == Util.EnumOS.OSX) {
               try {
                  logger.info(var11);
                  Runtime.getRuntime().exec(new String[]{"/usr/bin/open", var11});
                  return;
               } catch (IOException var9) {
                  logger.error("Couldn\'t open file", var9);
               }
            } else if(Util.getOSType() == Util.EnumOS.WINDOWS) {
               String var14 = String.format("cmd.exe /C start \"Open file\" \"%s\"", new Object[]{var11});

               try {
                  Runtime.getRuntime().exec(var14);
                  return;
               } catch (IOException var8) {
                  logger.error("Couldn\'t open file", var8);
               }
            }

            boolean var141 = false;

            try {
               Class var7 = Class.forName("java.awt.Desktop");
               Object var6 = var7.getMethod("getDesktop", new Class[0]).invoke((Object)null, new Object[0]);
               var7.getMethod("browse", new Class[]{URI.class}).invoke(var6, new Object[]{var10.toURI()});
            } catch (Throwable var71) {
               logger.error("Couldn\'t open link", var71);
               var141 = true;
            }

            if(var141) {
               logger.info("Opening via system class!");
               Sys.openURL("file://" + var11);
            }
         } else if(p_146284_1_.id == 1) {
            ArrayList var101 = Lists.newArrayList();
            Iterator var111 = this.field_146969_h.iterator();

            while(var111.hasNext()) {
               ResourcePackListEntry var142 = (ResourcePackListEntry)var111.next();
               if(var142 instanceof ResourcePackListEntryFound) {
                  var101.add(((ResourcePackListEntryFound)var142).func_148318_i());
               }
            }

            Collections.reverse(var101);
            mc.getResourcePackRepository().func_148527_a(var101);
            mc.gameSettings.resourcePacks.clear();
            var111 = var101.iterator();

            while(var111.hasNext()) {
               ResourcePackRepository.Entry var143 = (ResourcePackRepository.Entry)var111.next();
               mc.gameSettings.resourcePacks.add(var143.getResourcePackName());
            }

            mc.gameSettings.saveOptions();
            mc.refreshResources();
            mc.displayGuiScreen(this.field_146965_f);
         }
      }
   }

   protected void mouseClicked(int p_73864_1_, int p_73864_2_, int p_73864_3_) {
      super.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
      this.field_146970_i.func_148179_a(p_73864_1_, p_73864_2_, p_73864_3_);
      this.field_146967_r.func_148179_a(p_73864_1_, p_73864_2_, p_73864_3_);
   }

   protected void mouseMovedOrUp(int p_146286_1_, int p_146286_2_, int p_146286_3_) {
      super.mouseMovedOrUp(p_146286_1_, p_146286_2_, p_146286_3_);
   }

   public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
      this.func_146278_c(0);
      this.field_146970_i.func_148128_a(p_73863_1_, p_73863_2_, p_73863_3_);
      this.field_146967_r.func_148128_a(p_73863_1_, p_73863_2_, p_73863_3_);
      this.drawCenteredString(this.fontRendererObj, I18n.format("resourcePack.title", new Object[0]), this.width / 2, 16, 16777215);
      this.drawCenteredString(this.fontRendererObj, I18n.format("resourcePack.folderInfo", new Object[0]), this.width / 2 - 77, this.height - 26, 8421504);
      super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
   }
}
