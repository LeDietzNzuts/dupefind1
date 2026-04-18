package net.p3pp3rf1y.sophisticatedcore.api;

import java.util.function.UnaryOperator;
import net.minecraft.class_1937;
import net.minecraft.class_5819;
import net.p3pp3rf1y.sophisticatedcore.renderdata.IUpgradeRenderData;
import org.joml.Vector3f;

public interface IUpgradeRenderer<T extends IUpgradeRenderData> {
   void render(class_1937 var1, class_5819 var2, UnaryOperator<Vector3f> var3, T var4);
}
