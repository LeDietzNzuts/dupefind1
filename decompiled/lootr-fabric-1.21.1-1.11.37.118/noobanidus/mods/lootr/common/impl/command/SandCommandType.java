package noobanidus.mods.lootr.common.impl.command;

import com.google.auto.service.AutoService;
import net.minecraft.class_2248;
import noobanidus.mods.lootr.common.api.command.ILootrCommandExtension;
import noobanidus.mods.lootr.common.api.registry.LootrRegistry;

@AutoService(ILootrCommandExtension.class)
public class SandCommandType implements ILootrCommandExtension {
   @Override
   public class_2248 getBlock() {
      return LootrRegistry.getSuspiciousSandBlock();
   }

   @Override
   public String getId() {
      return "sand";
   }
}
