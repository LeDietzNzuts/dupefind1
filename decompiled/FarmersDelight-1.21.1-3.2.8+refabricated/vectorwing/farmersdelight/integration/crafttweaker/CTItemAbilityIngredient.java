package vectorwing.farmersdelight.integration.crafttweaker;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.ingredient.condition.IngredientConditions;
import com.blamejared.crafttweaker.api.ingredient.transformer.IngredientTransformers;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.item.MCItemStackMutable;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import net.minecraft.class_1799;
import net.minecraft.class_1856;
import org.openzen.zencode.java.ZenCodeType.Name;
import vectorwing.farmersdelight.common.crafting.ingredient.ItemAbilityIngredient;
import vectorwing.farmersdelight.refabricated.ItemAbility;

@Document("mods/FarmersDelight/ItemAbilityIngredient")
@ZenRegister
@Name("mods.farmersdelight.ItemAbilityIngredient")
public class CTItemAbilityIngredient implements IIngredient {
   public static final String PREFIX = "toolingredient";
   private final ItemAbilityIngredient ingredient;
   private final IngredientConditions conditions = new IngredientConditions();
   private final IngredientTransformers transformers = new IngredientTransformers();

   public CTItemAbilityIngredient(ItemAbility itemAbility) {
      this(new ItemAbilityIngredient(itemAbility));
   }

   public CTItemAbilityIngredient(ItemAbilityIngredient ingredient) {
      this.ingredient = ingredient;
   }

   public boolean matches(IItemStack stack) {
      return this.ingredient.test(stack.getInternal());
   }

   public class_1856 asVanillaIngredient() {
      return this.ingredient.toVanilla();
   }

   public String getCommandString() {
      return "<toolingredient:" + this.ingredient.getItemAbility().name() + ">";
   }

   public IItemStack[] getItems() {
      class_1799[] stacks = (class_1799[])this.ingredient.getMatchingStacks().toArray();
      IItemStack[] out = new IItemStack[stacks.length];

      for (int i = 0; i < stacks.length; i++) {
         out[i] = new MCItemStackMutable(stacks[i]);
      }

      return out;
   }

   public IngredientTransformers transformers() {
      return this.transformers;
   }

   public IngredientConditions conditions() {
      return this.conditions;
   }
}
