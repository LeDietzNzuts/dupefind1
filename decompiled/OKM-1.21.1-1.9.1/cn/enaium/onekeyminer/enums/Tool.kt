package cn.enaium.onekeyminer.enums

import kotlin.enums.EnumEntries

public enum class Tool {
   AXE,
   HOE,
   PICKAXE,
   SHOVEL,
   SHEARS,
   ANY
   @JvmStatic
   fun getEntries(): EnumEntries<Tool> {
      return $ENTRIES;
   }
}
