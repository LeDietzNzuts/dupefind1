package net.p3pp3rf1y.sophisticatedcore.upgrades.stack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.class_1792;
import net.minecraft.class_1802;
import net.minecraft.class_2960;
import net.minecraft.class_7923;
import net.neoforged.neoforge.common.ModConfigSpec.Builder;
import net.neoforged.neoforge.common.ModConfigSpec.ConfigValue;
import net.p3pp3rf1y.sophisticatedcore.Config;
import net.p3pp3rf1y.sophisticatedcore.SophisticatedCore;
import net.p3pp3rf1y.sophisticatedcore.util.RegistryHelper;

public class StackUpgradeConfig {
   private static final String REGISTRY_NAME_MATCHER = "([a-z0-9_.-]+:[a-z0-9_/.-]+)";
   private final ConfigValue<List<String>> nonStackableItemsList;
   @Nullable
   private Set<class_1792> nonStackableItems = null;

   public StackUpgradeConfig(Builder builder) {
      builder.comment("Stack Upgrade Settings").push("stackUpgrade");
      this.nonStackableItemsList = builder.comment(
            "List of items that are not supposed to stack in storage even when stack upgrade is inserted. Item registry names are expected here."
         )
         .define("nonStackableItems", this::getDefaultNonStackableList, itemNames -> {
            List<String> registryNames = (List<String>)itemNames;
            return registryNames != null && registryNames.stream().allMatch(itemName -> itemName.matches("([a-z0-9_.-]+:[a-z0-9_/.-]+)"));
         });
      builder.pop();
   }

   private List<String> getDefaultNonStackableList() {
      List<String> ret = new ArrayList<>();
      ret.add(RegistryHelper.getItemKey(class_1802.field_27023).toString());
      ret.add(RegistryHelper.getItemKey(class_1802.field_8545).toString());
      ret.add(RegistryHelper.getItemKey(class_1802.field_8722).toString());
      ret.add(RegistryHelper.getItemKey(class_1802.field_8380).toString());
      ret.add(RegistryHelper.getItemKey(class_1802.field_8050).toString());
      ret.add(RegistryHelper.getItemKey(class_1802.field_8829).toString());
      ret.add(RegistryHelper.getItemKey(class_1802.field_8271).toString());
      ret.add(RegistryHelper.getItemKey(class_1802.field_8548).toString());
      ret.add(RegistryHelper.getItemKey(class_1802.field_8520).toString());
      ret.add(RegistryHelper.getItemKey(class_1802.field_8627).toString());
      ret.add(RegistryHelper.getItemKey(class_1802.field_8451).toString());
      ret.add(RegistryHelper.getItemKey(class_1802.field_8213).toString());
      ret.add(RegistryHelper.getItemKey(class_1802.field_8816).toString());
      ret.add(RegistryHelper.getItemKey(class_1802.field_8350).toString());
      ret.add(RegistryHelper.getItemKey(class_1802.field_8584).toString());
      ret.add(RegistryHelper.getItemKey(class_1802.field_8461).toString());
      ret.add(RegistryHelper.getItemKey(class_1802.field_8676).toString());
      ret.add(RegistryHelper.getItemKey(class_1802.field_8268).toString());
      return ret;
   }

   public boolean canStackItem(class_1792 item) {
      if (!Config.COMMON_SPEC.isLoaded()) {
         return true;
      } else {
         if (this.nonStackableItems == null) {
            this.nonStackableItems = new HashSet<>();
            ((List)this.nonStackableItemsList.get()).forEach(name -> {
               class_2960 registryName = class_2960.method_60654(name);
               if (class_7923.field_41178.method_10250(registryName)) {
                  this.nonStackableItems.add((class_1792)class_7923.field_41178.method_10223(registryName));
               } else {
                  SophisticatedCore.LOGGER.error("Item {} is set to not be affected by stack upgrade in config, but it does not exist in item registry", name);
               }
            });
         }

         return !this.nonStackableItems.contains(item);
      }
   }

   public void clearNonStackableItems() {
      this.nonStackableItems = null;
   }
}
