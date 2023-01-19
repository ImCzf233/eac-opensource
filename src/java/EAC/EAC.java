package EAC;

import net.minecraftforge.fml.common.*;
import net.minecraftforge.common.*;
import sun.misc.*;
import EAC.coremod.*;
import java.lang.reflect.*;
import net.minecraftforge.fml.common.event.*;
import sun.reflect.*;

@Mod(modid = "eac", version = "1.7.1", acceptedMinecraftVersions = "[1.8.9]")
public class EAC
{
    public static final String MODID = "eac";
    public static final String VERSION = "1.7.1";
    public static boolean init;
    public static boolean debug;
    
    @Mod.EventHandler
    public void pre(final FMLInitializationEvent event) {
        if (EAC.init) {
            return;
        }
        EAC.init = true;
        final Running runner = new Running();
        MinecraftForge.EVENT_BUS.register((Object)runner);
        EACCore.detector.FileMonitor("");
        try {
            final Field a2 = Unsafe.class.getDeclaredField("theUnsafe");
            a2.setAccessible(true);
            ((Unsafe)a2.get(null)).putObject(System.class, 116L, new ESManager());
            a2.setAccessible(false);
        }
        catch (NoSuchFieldException eM) {
            throw new RuntimeException(eM);
        }
        catch (IllegalAccessException eM2) {
            throw new RuntimeException(eM2);
        }
    }
    
    @Mod.EventHandler
    public void post(final FMLPostInitializationEvent event) {
        if (!EACCore.tick) {
            Reflection.registerMethodsToFilter(ClassLoader.class, "defineClass");
            EACCore.tick = true;
        }
    }
    
    static {
        EAC.init = false;
        EAC.debug = false;
    }
}
