package EAC.coremod;

import net.minecraftforge.fml.common.network.*;
import net.minecraftforge.fml.common.*;
import net.minecraft.client.*;
import io.netty.buffer.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Running
{
    @SubscribeEvent
    public void onJoinSever(final FMLNetworkEvent.ClientConnectedToServerEvent event) {
        if (event.isLocal) {
            return;
        }
        final int x = EACCore.detector.protectVL();
        if (x != 114514) {
            System.out.println("2");
            FMLCommonHandler.instance().exitJava(999, true);
            Minecraft.getMinecraft().shutdown();
        }
        try {
            ClassLoader.class.getDeclaredField("defineClass");
        }
        catch (NoSuchFieldException ex) {
            final PacketBuffer bufferx = new PacketBuffer(Unpooled.buffer());
            bufferx.writeString("online=" + Minecraft.getMinecraft().getSession().getPlayerID());
            event.manager.sendPacket((Packet)new C17PacketCustomPayload("eac|login", bufferx));
            return;
        }
        System.out.println("1");
        FMLCommonHandler.instance().exitJava(999, true);
        Minecraft.getMinecraft().shutdown();
    }
}
