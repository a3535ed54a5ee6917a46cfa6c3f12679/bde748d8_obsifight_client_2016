package fr.thisismac.injector.customclass.commands;

import java.awt.Desktop;
import java.net.URI;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class CommandSite extends CommandBase {
   public String getCommandName() {
      return "site";
   }

   public int getRequiredPermissionLevel() {
      return 0;
   }

   public String getCommandUsage(ICommandSender p_71518_1_) {
      return "commands.site.usage";
   }

   public void processCommand(ICommandSender p_71515_1_, String[] p_71515_2_) {
      try {
         URI var7 = new URI("https://obsifight.net/");
         Desktop.getDesktop().browse(var7);
      } catch (Exception var4) {
         var4.printStackTrace();
      }
   }
}
