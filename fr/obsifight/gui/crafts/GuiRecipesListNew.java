package fr.obsifight.gui.crafts;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class GuiRecipesListNew {
   public static void GuiCraft_76646zc22d(GuiCraft var0, ItemStack var1) {
      if(var1.getItem() == (new ItemStack(Blocks.stone)).getItem()) {
         var0.GuiCraft_7957up5a5m = 1;
         var0.furnace = false;
         var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(2, new ItemStack(Blocks.cobblestone, 1));
         var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(8, var0.GuiCraft_sgkr6x0s4q());
      } else if(var1.getItem() == (new ItemStack(Blocks.planks)).getItem()) {
         var0.furnace = false;
         if(var1.getItemDamage() != 4) {
            if(var1.getItemDamage() != 5) {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(7, new ItemStack(Blocks.log, 1, var1.getItemDamage()));
            } else {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(7, new ItemStack(Blocks.log2, 1, 1));
            }
         } else {
            var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(7, new ItemStack(Blocks.log2, 1, 0));
         }
      } else {
         int var2;
         if(var1.getItem() == (new ItemStack(Blocks.lapis_block)).getItem()) {
            var0.furnace = false;

            for(var2 = 0; var2 < 9; ++var2) {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(var2, new ItemStack(Items.dye, 1, 4));
            }
         } else if(var1.getItem() == (new ItemStack(Blocks.dispenser)).getItem()) {
            var0.furnace = false;

            for(var2 = 0; var2 < 9; ++var2) {
               if(var2 != 4) {
                  if(var2 != 7) {
                     var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(var2, new ItemStack(Blocks.cobblestone, 1, var1.getItemDamageForDisplay()));
                  } else {
                     var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(var2, new ItemStack(Items.redstone, 1, var1.getItemDamageForDisplay()));
                  }
               } else {
                  var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(var2, new ItemStack(Items.bow, 1, var1.getItemDamageForDisplay()));
               }
            }
         } else if(var1.getItem() == (new ItemStack(Blocks.dropper)).getItem()) {
            var0.furnace = false;

            for(var2 = 0; var2 < 9; ++var2) {
               if(var2 != 4) {
                  if(var2 != 7) {
                     var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(var2, new ItemStack(Blocks.cobblestone, 1, var1.getItemDamageForDisplay()));
                  } else {
                     var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(var2, new ItemStack(Items.redstone, 1, var1.getItemDamageForDisplay()));
                  }
               }
            }
         } else if(var1.getItem() == (new ItemStack(Blocks.sandstone)).getItem()) {
            var0.furnace = false;
            if(var1.getIconIndex() == (new ItemStack(Blocks.sandstone, 1, 0)).getIconIndex()) {
               for(var2 = 0; var2 < 9; ++var2) {
                  if(var2 == 3 || var2 == 4 || var2 == 6 || var2 == 7) {
                     var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(var2, new ItemStack(Blocks.sand, 1));
                  }
               }
            }

            if(var1.getIconIndex() == (new ItemStack(Blocks.sandstone, 1, 1)).getIconIndex()) {
               for(var2 = 0; var2 < 9; ++var2) {
                  if(var2 == 4 || var2 == 7) {
                     var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(var2, new ItemStack(Blocks.stone_slab, 1, 1));
                  }
               }
            }

            if(var1.getIconIndex() == (new ItemStack(Blocks.sandstone, 1, 2)).getIconIndex()) {
               for(var2 = 0; var2 < 9; ++var2) {
                  if(var2 == 3 || var2 == 4 || var2 == 6 || var2 == 7) {
                     var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(var2, new ItemStack(Blocks.sandstone, 1, 2));
                  }
               }
            }
         } else if(var1.getItem() == (new ItemStack(Blocks.noteblock)).getItem()) {
            var0.furnace = false;

            for(var2 = 0; var2 < 9; ++var2) {
               if(var2 != 4) {
                  var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(var2, new ItemStack(Blocks.planks, 1));
               } else {
                  var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(var2, new ItemStack(Items.redstone, 1));
               }
            }
         } else if(var1.getItem() == (new ItemStack(Blocks.golden_rail)).getItem()) {
            var0.furnace = false;
            var0.contCrafts.GuiCraft_sgkr6x0s4q.GuiCraft_76646zc22d(0, new ItemStack(Blocks.golden_rail, 6));

            for(var2 = 0; var2 < 9; ++var2) {
               if(var2 != 0 && var2 != 2 && var2 != 3 && var2 != 5 && var2 != 6 && var2 != 8) {
                  if(var2 != 4) {
                     if(var2 == 7) {
                        var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(var2, new ItemStack(Items.redstone, 1));
                     }
                  } else {
                     var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(var2, new ItemStack(Items.stick, 1));
                  }
               } else {
                  var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(var2, new ItemStack(Items.gold_ingot, 1));
               }
            }
         } else if(var1.getItem() == (new ItemStack(Blocks.detector_rail)).getItem()) {
            var0.furnace = false;
            var0.contCrafts.GuiCraft_sgkr6x0s4q.GuiCraft_76646zc22d(0, new ItemStack(Blocks.detector_rail, 6));

            for(var2 = 0; var2 < 9; ++var2) {
               if(var2 != 0 && var2 != 2 && var2 != 3 && var2 != 5 && var2 != 6 && var2 != 8) {
                  if(var2 != 4) {
                     if(var2 == 7) {
                        var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(var2, new ItemStack(Items.redstone, 1));
                     }
                  } else {
                     var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(var2, new ItemStack(Blocks.stone_pressure_plate, 1));
                  }
               } else {
                  var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(var2, new ItemStack(Items.iron_ingot, 1));
               }
            }
         } else if(var1.getItem() == (new ItemStack(Blocks.activator_rail)).getItem()) {
            var0.furnace = false;
            var0.contCrafts.GuiCraft_sgkr6x0s4q.GuiCraft_76646zc22d(0, new ItemStack(Blocks.activator_rail, 6));

            for(var2 = 0; var2 < 9; ++var2) {
               if(var2 != 0 && var2 != 2 && var2 != 3 && var2 != 5 && var2 != 6 && var2 != 8) {
                  if(var2 != 4) {
                     if(var2 == 7 || var2 == 1) {
                        var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(var2, new ItemStack(Items.stick, 1));
                     }
                  } else {
                     var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(var2, new ItemStack(Blocks.redstone_torch, 1));
                  }
               } else {
                  var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(var2, new ItemStack(Items.iron_ingot, 1));
               }
            }
         } else if(var1.getItem() == (new ItemStack(Blocks.sticky_piston)).getItem()) {
            var0.furnace = false;

            for(var2 = 0; var2 < 9; ++var2) {
               if(var2 != 4) {
                  if(var2 == 7) {
                     var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(var2, new ItemStack(Blocks.piston, 1));
                  }
               } else {
                  var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(var2, new ItemStack(Items.slime_ball, 1));
               }
            }
         } else if(var1.getItem() == (new ItemStack(Blocks.piston)).getItem()) {
            var0.furnace = false;

            for(var2 = 0; var2 < 9; ++var2) {
               if(var2 == 3 || var2 == 5 || var2 == 6 || var2 == 8) {
                  var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(var2, new ItemStack(Blocks.cobblestone, 1));
               }

               if(var2 != 0 && var2 != 1 && var2 != 2) {
                  if(var2 != 4) {
                     if(var2 == 7) {
                        var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(var2, new ItemStack(Items.redstone, 1));
                     }
                  } else {
                     var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(var2, new ItemStack(Items.iron_ingot, 1));
                  }
               } else {
                  var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(var2, new ItemStack(Blocks.planks, 1));
               }
            }
         } else if(var1.getItem() == (new ItemStack(Blocks.wool)).getItem()) {
            var0.furnace = false;
            if(var1.getItemDamage() != 0) {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(4, new ItemStack(Blocks.wool, 1, var0.GuiCraft_z59bsdf26t()));
            } else {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(3, new ItemStack(Items.string, 1));
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(4, new ItemStack(Items.string, 1));
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(6, new ItemStack(Items.string, 1));
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(7, new ItemStack(Items.string, 1));
            }

            if(var1.getItemDamage() == 1) {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(5, new ItemStack(Items.dye, 1, 14));
            }

            if(var1.getItemDamage() == 2) {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(5, new ItemStack(Items.dye, 1, 13));
            }

            if(var1.getItemDamage() == 3) {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(5, new ItemStack(Items.dye, 1, 12));
            }

            if(var1.getItemDamage() == 4) {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(5, new ItemStack(Items.dye, 1, 11));
            }

            if(var1.getItemDamage() == 5) {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(5, new ItemStack(Items.dye, 1, 10));
            }

            if(var1.getItemDamage() == 6) {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(5, new ItemStack(Items.dye, 1, 9));
            }

            if(var1.getItemDamage() == 7) {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(5, new ItemStack(Items.dye, 1, 7));
            }

            if(var1.getItemDamage() == 8) {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(5, new ItemStack(Items.dye, 1, 8));
            }

            if(var1.getItemDamage() == 9) {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(5, new ItemStack(Items.dye, 1, 6));
            }

            if(var1.getItemDamage() == 10) {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(5, new ItemStack(Items.dye, 1, 5));
            }

            if(var1.getItemDamage() == 11) {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(5, new ItemStack(Items.dye, 1, 4));
            }

            if(var1.getItemDamage() == 12) {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(5, new ItemStack(Items.dye, 1, 3));
            }

            if(var1.getItemDamage() == 13) {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(5, new ItemStack(Items.dye, 1, 2));
            }

            if(var1.getItemDamage() == 14) {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(5, new ItemStack(Items.dye, 1, 1));
            }

            if(var1.getItemDamage() == 15) {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(5, new ItemStack(Items.dye, 1, 0));
            }
         } else if(var1.getItem() == (new ItemStack(Blocks.hardened_clay)).getItem()) {
            var0.furnace = false;
            if(var1.getItemDamage() == 0) {
               var0.GuiCraft_7957up5a5m = 1;
               var0.contCrafts.GuiCraft_sgkr6x0s4q.GuiCraft_76646zc22d(0, new ItemStack(var1.getItem(), 1));
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(2, new ItemStack(Blocks.clay, 1));
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(8, var0.GuiCraft_sgkr6x0s4q());
            }
         } else if(var1.getItem() == (new ItemStack(Blocks.stained_hardened_clay)).getItem()) {
            var0.furnace = false;
            if(var1.getItemDamage() == 0) {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(5, new ItemStack(Items.dye, 1, 15));
            }

            var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(4, new ItemStack(Blocks.hardened_clay, 1, var0.GuiCraft_z59bsdf26t()));
            if(var1.getItemDamage() == 1) {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(5, new ItemStack(Items.dye, 1, 14));
            }

            if(var1.getItemDamage() == 2) {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(5, new ItemStack(Items.dye, 1, 13));
            }

            if(var1.getItemDamage() == 3) {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(5, new ItemStack(Items.dye, 1, 12));
            }

            if(var1.getItemDamage() == 4) {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(5, new ItemStack(Items.dye, 1, 11));
            }

            if(var1.getItemDamage() == 5) {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(5, new ItemStack(Items.dye, 1, 10));
            }

            if(var1.getItemDamage() == 6) {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(5, new ItemStack(Items.dye, 1, 9));
            }

            if(var1.getItemDamage() == 7) {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(5, new ItemStack(Items.dye, 1, 7));
            }

            if(var1.getItemDamage() == 8) {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(5, new ItemStack(Items.dye, 1, 8));
            }

            if(var1.getItemDamage() == 9) {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(5, new ItemStack(Items.dye, 1, 6));
            }

            if(var1.getItemDamage() == 10) {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(5, new ItemStack(Items.dye, 1, 5));
            }

            if(var1.getItemDamage() == 11) {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(5, new ItemStack(Items.dye, 1, 4));
            }

            if(var1.getItemDamage() == 12) {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(5, new ItemStack(Items.dye, 1, 3));
            }

            if(var1.getItemDamage() == 13) {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(5, new ItemStack(Items.dye, 1, 2));
            }

            if(var1.getItemDamage() == 14) {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(5, new ItemStack(Items.dye, 1, 1));
            }

            if(var1.getItemDamage() == 15) {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(5, new ItemStack(Items.dye, 1, 0));
            }
         } else if(var1.getItem() == (new ItemStack(Blocks.stained_glass)).getItem()) {
            var0.furnace = false;
            var0.contCrafts.GuiCraft_sgkr6x0s4q.GuiCraft_76646zc22d(0, new ItemStack(var1.getItem(), 8, var1.getItemDamage()));
            if(var1.getItemDamage() == 0) {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(4, new ItemStack(Items.dye, 1, 15));
            }

            var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(0, new ItemStack(Blocks.glass, 1, var0.GuiCraft_z59bsdf26t()));
            var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(1, new ItemStack(Blocks.glass, 1, var0.GuiCraft_z59bsdf26t()));
            var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(2, new ItemStack(Blocks.glass, 1, var0.GuiCraft_z59bsdf26t()));
            var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(3, new ItemStack(Blocks.glass, 1, var0.GuiCraft_z59bsdf26t()));
            var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(5, new ItemStack(Blocks.glass, 1, var0.GuiCraft_z59bsdf26t()));
            var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(6, new ItemStack(Blocks.glass, 1, var0.GuiCraft_z59bsdf26t()));
            var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(7, new ItemStack(Blocks.glass, 1, var0.GuiCraft_z59bsdf26t()));
            var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(8, new ItemStack(Blocks.glass, 1, var0.GuiCraft_z59bsdf26t()));
            if(var1.getItemDamage() == 1) {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(4, new ItemStack(Items.dye, 1, 14));
            }

            if(var1.getItemDamage() == 2) {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(4, new ItemStack(Items.dye, 1, 13));
            }

            if(var1.getItemDamage() == 3) {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(4, new ItemStack(Items.dye, 1, 12));
            }

            if(var1.getItemDamage() == 4) {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(4, new ItemStack(Items.dye, 1, 11));
            }

            if(var1.getItemDamage() == 5) {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(4, new ItemStack(Items.dye, 1, 10));
            }

            if(var1.getItemDamage() == 6) {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(4, new ItemStack(Items.dye, 1, 9));
            }

            if(var1.getItemDamage() == 7) {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(4, new ItemStack(Items.dye, 1, 7));
            }

            if(var1.getItemDamage() == 8) {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(4, new ItemStack(Items.dye, 1, 8));
            }

            if(var1.getItemDamage() == 9) {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(4, new ItemStack(Items.dye, 1, 6));
            }

            if(var1.getItemDamage() == 10) {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(4, new ItemStack(Items.dye, 1, 5));
            }

            if(var1.getItemDamage() == 11) {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(4, new ItemStack(Items.dye, 1, 4));
            }

            if(var1.getItemDamage() == 12) {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(4, new ItemStack(Items.dye, 1, 3));
            }

            if(var1.getItemDamage() == 13) {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(4, new ItemStack(Items.dye, 1, 2));
            }

            if(var1.getItemDamage() == 14) {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(4, new ItemStack(Items.dye, 1, 1));
            }

            if(var1.getItemDamage() == 15) {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(4, new ItemStack(Items.dye, 1, 0));
            }
         } else if(var1.getItem() == (new ItemStack(Blocks.stained_glass_pane)).getItem()) {
            var0.furnace = false;
            var0.contCrafts.GuiCraft_sgkr6x0s4q.GuiCraft_76646zc22d(0, new ItemStack(var1.getItem(), 16, var0.GuiCraft_z59bsdf26t()));
            var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(3, new ItemStack(Blocks.stained_glass, 1, var0.GuiCraft_z59bsdf26t()));
            var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(4, new ItemStack(Blocks.stained_glass, 1, var0.GuiCraft_z59bsdf26t()));
            var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(5, new ItemStack(Blocks.stained_glass, 1, var0.GuiCraft_z59bsdf26t()));
            var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(6, new ItemStack(Blocks.stained_glass, 1, var0.GuiCraft_z59bsdf26t()));
            var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(7, new ItemStack(Blocks.stained_glass, 1, var0.GuiCraft_z59bsdf26t()));
            var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(8, new ItemStack(Blocks.stained_glass, 1, var0.GuiCraft_z59bsdf26t()));
            var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(4, new ItemStack(Blocks.stained_glass, 1, var0.GuiCraft_z59bsdf26t()));
         } else if(var1.getItem() == (new ItemStack(Blocks.carpet)).getItem()) {
            var0.furnace = false;
            var0.contCrafts.GuiCraft_sgkr6x0s4q.GuiCraft_76646zc22d(0, new ItemStack(var1.getItem(), 3, var0.GuiCraft_z59bsdf26t()));
            var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(6, new ItemStack(Blocks.wool, 1, var0.GuiCraft_z59bsdf26t()));
            var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(7, new ItemStack(Blocks.wool, 1, var0.GuiCraft_z59bsdf26t()));
         } else if(var1.getItem() == (new ItemStack(Blocks.gold_block)).getItem()) {
            var0.furnace = false;

            for(var2 = 0; var2 < 9; ++var2) {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(var2, new ItemStack(Items.gold_ingot, 1));
            }
         } else if(var1.getItem() == (new ItemStack(Blocks.hay_block)).getItem()) {
            var0.furnace = false;

            for(var2 = 0; var2 < 9; ++var2) {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(var2, new ItemStack(Items.wheat, 1));
            }
         } else if(var1.getItem() == (new ItemStack(Blocks.iron_block)).getItem()) {
            var0.furnace = false;

            for(var2 = 0; var2 < 9; ++var2) {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(var2, new ItemStack(Items.iron_ingot, 1));
            }
         } else if(var1.getItem() == (new ItemStack(Blocks.stone_slab)).getItem()) {
            var0.contCrafts.GuiCraft_sgkr6x0s4q.GuiCraft_76646zc22d(0, new ItemStack(var1.getItem(), 6, var1.getItemDamage()));
            var0.furnace = false;
            if(var1.getItemDamage() != 0) {
               if(var1.getItemDamage() != 1) {
                  if(var1.getItemDamage() != 3) {
                     if(var1.getItemDamage() != 4) {
                        if(var1.getItemDamage() != 6) {
                           if(var1.getItemDamage() != 7) {
                              var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(6, new ItemStack(Blocks.stonebrick, 1));
                              var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(7, new ItemStack(Blocks.stonebrick, 1));
                              var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(8, new ItemStack(Blocks.stonebrick, 1));
                           } else {
                              var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(6, new ItemStack(Blocks.quartz_block, 1));
                              var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(7, new ItemStack(Blocks.quartz_block, 1));
                              var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(8, new ItemStack(Blocks.quartz_block, 1));
                           }
                        } else {
                           var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(6, new ItemStack(Blocks.nether_brick, 1));
                           var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(7, new ItemStack(Blocks.nether_brick, 1));
                           var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(8, new ItemStack(Blocks.nether_brick, 1));
                        }
                     } else {
                        var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(6, new ItemStack(Blocks.brick_block, 1));
                        var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(7, new ItemStack(Blocks.brick_block, 1));
                        var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(8, new ItemStack(Blocks.brick_block, 1));
                     }
                  } else {
                     var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(6, new ItemStack(Blocks.cobblestone, 1));
                     var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(7, new ItemStack(Blocks.cobblestone, 1));
                     var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(8, new ItemStack(Blocks.cobblestone, 1));
                  }
               } else {
                  var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(6, new ItemStack(Blocks.sandstone, 1, 0));
                  var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(7, new ItemStack(Blocks.sandstone, 1, 0));
                  var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(8, new ItemStack(Blocks.sandstone, 1, 0));
               }
            } else {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(6, new ItemStack(Blocks.stone, 1, 0));
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(7, new ItemStack(Blocks.stone, 1, 0));
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(8, new ItemStack(Blocks.stone, 1, 0));
            }
         } else if(var1.getItem() == (new ItemStack(Blocks.brick_block)).getItem()) {
            var0.furnace = false;
            if(var1.getItemDamage() == 0) {
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(3, new ItemStack(Items.brick, 1));
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(4, new ItemStack(Items.brick, 1));
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(6, new ItemStack(Items.brick, 1));
               var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(7, new ItemStack(Items.brick, 1));
            }
         } else if(var1.getItem() == (new ItemStack(Blocks.tnt)).getItem()) {
            var0.furnace = false;

            for(var2 = 0; var2 < 9; ++var2) {
               if(var2 == 0 || var2 == 2 || var2 == 4 || var2 == 6 || var2 == 8) {
                  var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(var2, new ItemStack(Items.gunpowder, 1));
               }

               if(var2 == 1 || var2 == 3 || var2 == 5 || var2 == 7) {
                  var0.contCrafts.GuiCraft_z59bsdf26t.GuiCraft_76646zc22d(var2, new ItemStack(Blocks.sand, 1));
               }
            }
         }
      }
   }
}
