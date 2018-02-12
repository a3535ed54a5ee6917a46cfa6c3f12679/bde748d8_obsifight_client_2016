package fr.thisismac.injector.customclass.craft;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.world.World;

public class WorkSurfaceCraftingManager {
   private static final WorkSurfaceCraftingManager instance = new WorkSurfaceCraftingManager();
   private List recipes = new ArrayList();

   public static final WorkSurfaceCraftingManager getInstance() {
      return instance;
   }

   private WorkSurfaceCraftingManager() {
      this.recipes = new ArrayList();
      this.addRecipe(new ItemStack(Items.admin_axe, 1), new Object[]{"S", "S", "S", "S", "S", 'S', Items.stick});
      Collections.sort(this.recipes, new WorkSurfaceRecipeSorter(this));
   }

   public WorkSurfaceShapedRecipes addRecipe(ItemStack par1ItemStack, Object ... par2ArrayOfObj) {
      String s = "";
      int i = 0;
      int j = 0;
      int k = 0;
      if(par2ArrayOfObj[i] instanceof String[]) {
         String[] hashmap = (String[])((String[])((String[])par2ArrayOfObj[i++]));

         for(int aitemstack = 0; aitemstack < hashmap.length; ++aitemstack) {
            String shapedrecipes = hashmap[aitemstack];
            ++k;
            j = shapedrecipes.length();
            s = s + shapedrecipes;
         }
      } else {
         while(par2ArrayOfObj[i] instanceof String) {
            String var11 = (String)par2ArrayOfObj[i++];
            ++k;
            j = var11.length();
            s = s + var11;
         }
      }

      HashMap var12;
      for(var12 = new HashMap(); i < par2ArrayOfObj.length; i += 2) {
         Character var13 = (Character)par2ArrayOfObj[i];
         ItemStack var15 = null;
         if(par2ArrayOfObj[i + 1] instanceof Item) {
            var15 = new ItemStack((Item)par2ArrayOfObj[i + 1]);
         } else if(par2ArrayOfObj[i + 1] instanceof Block) {
            var15 = new ItemStack((Block)par2ArrayOfObj[i + 1], 1, 32767);
         } else if(par2ArrayOfObj[i + 1] instanceof ItemStack) {
            var15 = (ItemStack)par2ArrayOfObj[i + 1];
         }

         var12.put(var13, var15);
      }

      ItemStack[] var14 = new ItemStack[j * k];

      for(int var16 = 0; var16 < j * k; ++var16) {
         char c0 = s.charAt(var16);
         if(var12.containsKey(Character.valueOf(c0))) {
            var14[var16] = ((ItemStack)var12.get(Character.valueOf(c0))).copy();
         } else {
            var14[var16] = null;
         }
      }

      WorkSurfaceShapedRecipes var17 = new WorkSurfaceShapedRecipes(j, k, var14, par1ItemStack);
      this.recipes.add(var17);
      return var17;
   }

   public void addShapelessRecipe(ItemStack par1ItemStack, Object ... par2ArrayOfObj) {
      ArrayList arraylist = new ArrayList();
      Object[] aobject = par2ArrayOfObj;
      int i = par2ArrayOfObj.length;

      for(int j = 0; j < i; ++j) {
         Object object1 = aobject[j];
         if(object1 instanceof ItemStack) {
            arraylist.add(((ItemStack)object1).copy());
         } else if(object1 instanceof Item) {
            arraylist.add(new ItemStack((Item)object1));
         } else {
            if(!(object1 instanceof Block)) {
               throw new RuntimeException("Invalid shapeless recipe!");
            }

            arraylist.add(new ItemStack((Block)object1));
         }
      }

      this.recipes.add(new ShapelessRecipes(par1ItemStack, arraylist));
   }

   public ItemStack findMatchingRecipe(InventoryCrafting par1InventoryCrafting, World par2World) {
      int i = 0;
      ItemStack itemstack = null;
      ItemStack itemstack1 = null;

      int j;
      for(j = 0; j < par1InventoryCrafting.getSizeInventory(); ++j) {
         ItemStack irecipe = par1InventoryCrafting.getStackInSlot(j);
         if(irecipe != null) {
            if(i == 0) {
               itemstack = irecipe;
            }

            if(i == 1) {
               itemstack1 = irecipe;
            }

            ++i;
         }
      }

      if(i == 2 && itemstack.getItem() == itemstack1.getItem() && itemstack.stackSize == 1 && itemstack1.stackSize == 1) {
         Item var13 = itemstack.getItem();
         int j1 = var13.getMaxDamage() - itemstack.getItemDamageForDisplay();
         int k = var13.getMaxDamage() - itemstack1.getItemDamageForDisplay();
         int l = j1 + k + var13.getMaxDamage() * 5 / 100;
         int i1 = var13.getMaxDamage() - l;
         if(i1 < 0) {
            i1 = 0;
         }

         return new ItemStack(itemstack.getItem(), 1, i1);
      } else {
         for(j = 0; j < this.recipes.size(); ++j) {
            IRecipe var12 = (IRecipe)this.recipes.get(j);
            if(var12.matches(par1InventoryCrafting, par2World)) {
               return var12.getCraftingResult(par1InventoryCrafting);
            }
         }

         return null;
      }
   }

   public List getRecipeList() {
      return this.recipes;
   }
}
