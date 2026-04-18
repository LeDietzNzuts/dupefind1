package com.magistuarmory.util;

import java.util.Objects;

public class DualKey<K1, K2> {
   private final K1 key1;
   private final K2 key2;

   public DualKey(K1 key1, K2 key2) {
      this.key1 = key1;
      this.key2 = key2;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         DualKey key = (DualKey)o;
         return !Objects.equals(this.key1, key.key1) ? false : Objects.equals(this.key2, key.key2);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      int result = this.key1 != null ? this.key1.hashCode() : 0;
      return 31 * result + (this.key2 != null ? this.key2.hashCode() : 0);
   }

   @Override
   public String toString() {
      return "[" + this.key1 + ", " + this.key2 + "]";
   }
}
