package EAC.coremod;

import net.minecraftforge.fml.relauncher.*;
import java.util.*;
import net.minecraft.launchwrapper.*;

public class CoreMod implements IFMLLoadingPlugin
{
    private static boolean isObfuscatedEnvironment;
    private static boolean loaded;
    
    public CoreMod() {
        Load();
    }
    
    public String[] getASMTransformerClass() {
        return new String[] { "" };
    }
    
    public String getModContainerClass() {
        return null;
    }
    
    public String getSetupClass() {
        return null;
    }
    
    public void injectData(final Map<String, Object> data) {
        CoreMod.isObfuscatedEnvironment = (boolean) data.get("runtimeDeobfuscationEnabled");
    }
    
    public String getAccessTransformerClass() {
        return null;
    }
    
    public static void Load() {
        if (!CoreMod.loaded) {
            final EACDetector _detector = new EACDetector();
            _detector.Loserdontinject();
            CoreMod.loaded = true;
            EACCore.detector = _detector;
            EACDetector.VARVL = 0;
            Launch.classLoader.registerTransformer(EACCore.class.getName());
            System.out.println("Loaded");
        }
    }
    
    static {
        CoreMod.isObfuscatedEnvironment = false;
        CoreMod.loaded = false;
    }
}
