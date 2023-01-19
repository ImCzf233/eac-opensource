package EAC.coremod;

import net.minecraft.launchwrapper.*;
import net.minecraftforge.fml.common.*;
import net.minecraft.client.*;
import net.minecraftforge.fml.common.eventhandler.*;
import sun.reflect.*;

public class EACCore implements IClassTransformer
{
    public static EACDetector detector;
    public static boolean tick;
    
    public byte[] transform(final String name, final String transformedName, final byte[] basicClass) {
        final int x = EACCore.detector.protectVL();
        if (x != 114514) {
            FMLCommonHandler.instance().exitJava(999, true);
            Minecraft.getMinecraft().shutdown();
        }
        if (name.startsWith("it.fktcod.ktykshrk")) {
            System.out.println("5");
            FMLCommonHandler.instance().exitJava(999, true);
            Minecraft.getMinecraft().shutdown();
            return null;
        }
        if (name.startsWith("net.minecraftforge.fml.common.FMLCommonHandler")) {
            try {
                Reflection.registerFieldsToFilter(EventBus.class, "listeners");
            }
            catch (Throwable t) {}
            return basicClass;
        }
        if (System.getSecurityManager() != null) {
            return basicClass;
        }
        System.out.println("3");
        FMLCommonHandler.instance().exitJava(999, true);
        Minecraft.getMinecraft().shutdown();
        return null;
    }
}
