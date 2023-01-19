package EAC.coremod;

import java.util.*;
import java.security.*;
import net.minecraftforge.fml.common.*;
import net.minecraft.client.*;

public class ESManager extends SecurityManager
{
    private static ArrayList<String> allowed;
    
    @Override
    public void checkPermission(final Permission a) {
        this.Recheck(a);
    }
    
    private void Recheck(final Permission a) {
        final int x = EACCore.detector.protectVL();
        if (x != 114514) {
            System.out.println("4");
            FMLCommonHandler.instance().exitJava(999, true);
            Minecraft.getMinecraft().shutdown();
        }
    }
    
    static {
        ESManager.allowed = new ArrayList<String>();
    }
}
