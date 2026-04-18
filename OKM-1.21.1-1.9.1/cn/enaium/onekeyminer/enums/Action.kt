package cn.enaium.onekeyminer.enums

import kotlin.enums.EnumEntries

public enum class Action {
   ADD,
   REMOVE
   @JvmStatic
   fun getEntries(): EnumEntries<Action> {
      return $ENTRIES;
   }
}
