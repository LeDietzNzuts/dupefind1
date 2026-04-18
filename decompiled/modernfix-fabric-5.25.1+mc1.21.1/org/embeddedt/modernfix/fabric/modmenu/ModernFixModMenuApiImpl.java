package org.embeddedt.modernfix.fabric.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import org.embeddedt.modernfix.screen.ModernFixConfigScreen;

public class ModernFixModMenuApiImpl implements ModMenuApi {
   public ConfigScreenFactory<ModernFixConfigScreen> getModConfigScreenFactory() {
      return ModernFixConfigScreen::new;
   }
}
