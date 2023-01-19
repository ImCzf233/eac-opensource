package EAC.coremod;

import java.io.*;
import javax.swing.*;
import java.awt.*;
import net.minecraft.client.*;
import net.minecraftforge.fml.common.*;
import sun.misc.*;
import java.lang.reflect.*;

public class EACDetector
{
    public static String filePath;
    public int VL;
    public static int VARVL;
    public static File dll;
    public static File ffile;
    public static boolean is64;
    
    public EACDetector() {
        this.VL = 1;
    }
    
    public native void Loserdontinject();
    
    public native int protectVL();
    
    public native int Monitor();

    //空壳检测，哈哈
    public native boolean FileMonitor(final String p0);

    // 没调用
    public native boolean FileCheck(final String p0);

    //三个垃圾方法不知道干啥的
    public static void on1(final Object a) {
    }
    
    public static void on2(final Object b) {
    }
    
    public static void on3(final Object c) {
    }
    
    public static void runNative() {
        final EACDetector detector = new EACDetector();
        detector.Loserdontinject();
        detector.protectVL();
    }
    
    public static void writeFile(final InputStream in, final String file) {
        try {
            final FileOutputStream out = new FileOutputStream(file);
            final byte[] buf = new byte[1024];
            try {
                int i;
                while ((i = in.read(buf)) != -1) {
                    out.write(buf, 0, i);
                }
            }
            finally {
                in.close();
                out.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static int runCmd(final String cmd) {
        System.out.println(cmd);
        int exitValue = 0;
        try {
            final Runtime runtime = Runtime.getRuntime();
            final Process process = runtime.exec(cmd);
            exitValue = process.waitFor();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return exitValue;
    }
    
    public static void start() {
        if (!EACDetector.dll.exists()) {
            final int yes = JOptionPane.showConfirmDialog(null, "检测到DLL写入失败 请重启游戏！", "EAC", 0);
            return;
        }
        final int yes = JOptionPane.showConfirmDialog(null, "检测到VC++环境不存在 按下确定启动安装程序 请安装后自行重启游戏！", "EAC ENV-SYS", 0);
        if (yes == 0) {
            writeFile(EACDetector.class.getResourceAsStream("/dev/ac/eac/vc_redist.x64.exe"), EACDetector.ffile.getAbsolutePath() + File.separator + "vc_redist.x64.exe");
            writeFile(EACDetector.class.getResourceAsStream("/dev/ac/eac/vc_redist.x86.exe"), EACDetector.ffile.getAbsolutePath() + File.separator + "vc_redist.x86.exe");
            if (EACDetector.is64) {
                int a = runCmd("cmd.exe /c " + EACDetector.ffile.getAbsolutePath() + File.separator + "vc_redist.x64.exe");
                a = runCmd("cmd.exe /c " + EACDetector.ffile.getAbsolutePath() + File.separator + "vc_redist.x86.exe /q /norestar");
            }
            else {
                runCmd("cmd.exe /c " + EACDetector.ffile.getAbsolutePath() + File.separator + "vc_redist.x86.exe");
            }
        }
    }
    
    static {
        EACDetector.filePath = null;
        EACDetector.VARVL = 1;
        EACDetector.dll = null;
        EACDetector.ffile = new File("");
        EACDetector.is64 = false;
        try {
            final String arch = System.getProperty("sun.arch.data.model");
            System.out.println(arch + "x Java");
            InputStream in = null;
            if (arch.contains("64")) {
                in = EACDetector.class.getResourceAsStream("/dev/ac/eac/EAC.dll");
                EACDetector.is64 = true;
            }
            else {
                in = EACDetector.class.getResourceAsStream("/dev/ac/eac/EAC x86.dll");
            }
            EACDetector.dll = File.createTempFile("temp", ".dll", new File(EACDetector.ffile.getAbsolutePath() + File.separator));
            final FileOutputStream out = new FileOutputStream(EACDetector.dll);
            final byte[] buf = new byte[1024];
            try {
                int i;
                while ((i = in.read(buf)) != -1) {
                    out.write(buf, 0, i);
                }
            }
            finally {
                in.close();
                out.close();
            }
            System.load(EACDetector.dll.getAbsolutePath());
        }
        catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
            start();
            try {
                System.load(EACDetector.dll.getAbsolutePath());
            }
            catch (Throwable exxx) {
                Minecraft.getMinecraft().shutdown();
                exxx.printStackTrace();
                System.err.println("load jni error!");
                FMLCommonHandler.instance().exitJava(0, true);
                Minecraft.getMinecraft().shutdown();
                try {
                    final Field F = Unsafe.class.getDeclaredField("theUnsafe");
                    F.setAccessible(true);
                    ((Unsafe)F.get(null)).putAddress(114514L, 1919810L);
                    ((Unsafe)F.get(null)).putAddress(114514L, 19810L);
                    ((Unsafe)F.get(null)).putAddress(114514L, 1919810L);
                    ((Unsafe)F.get(null)).putAddress(1114L, 191810L);
                    ((Unsafe)F.get(null)).putAddress(114514L, 1919810L);
                    ((Unsafe)F.get(null)).putAddress(11414L, 1919810L);
                    ((Unsafe)F.get(null)).putAddress(114514L, 119810L);
                    ((Unsafe)F.get(null)).putAddress(11414L, 1919810L);
                    ((Unsafe)F.get(null)).putAddress(114514L, 19810L);
                    ((Unsafe)F.get(null)).putAddress(114514L, 191810L);
                    ((Unsafe)F.get(null)).putAddress(11454L, 1919810L);
                    ((Unsafe)F.get(null)).putAddress(1114L, 191810L);
                }
                catch (NoSuchFieldException e2) {
                    try {
                        new Object().getClass().getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass();
                    }
                    catch (NoSuchFieldException nosadtion) {
                        try {
                            final Field F2 = Unsafe.class.getDeclaredField("theUnsafe");
                            F2.setAccessible(true);
                            ((Unsafe)F2.get(null)).putAddress(114514L, 19810L);
                            ((Unsafe)F2.get(null)).putAddress(114514L, 1919810L);
                            ((Unsafe)F2.get(null)).putAddress(114514L, 19810L);
                            ((Unsafe)F2.get(null)).putAddress(114514L, 19810L);
                            ((Unsafe)F2.get(null)).putAddress(1514L, 1919810L);
                            ((Unsafe)F2.get(null)).putAddress(114514L, 19110L);
                            ((Unsafe)F2.get(null)).putAddress(1114L, 1919810L);
                            ((Unsafe)F2.get(null)).putAddress(114514L, 19110L);
                            ((Unsafe)F2.get(null)).putAddress(1144L, 1919810L);
                            ((Unsafe)F2.get(null)).putAddress(114514L, 91810L);
                            ((Unsafe)F2.get(null)).putAddress(1114L, 19810L);
                            ((Unsafe)F2.get(null)).putAddress(1514L, 1919810L);
                            ((Unsafe)F2.get(null)).setMemory(14514L, 191810L, new Byte(null));
                            ((Unsafe)F2.get(null)).setMemory(14514L, 191810L, new Byte(null));
                            ((Unsafe)F2.get(null)).putAddress(114514L, 1919810L);
                            ((Unsafe)F2.get(null)).setMemory(14514L, 191810L, new Byte(null));
                            ((Unsafe)F2.get(null)).putAddress(11414L, 191810L);
                            ((Unsafe)F2.get(null)).setMemory(14514L, 191810L, new Byte(null));
                            ((Unsafe)F2.get(null)).putAddress(114514L, 1919810L);
                            ((Unsafe)F2.get(null)).setMemory(14514L, 191810L, new Byte(null));
                            ((Unsafe)F2.get(null)).setMemory(14514L, 191810L, new Byte(null));
                            ((Unsafe)F2.get(null)).putAddress(114514L, 1919810L);
                            ((Unsafe)F2.get(null)).setMemory(14514L, 191810L, new Byte(null));
                            ((Unsafe)F2.get(null)).putAddress(11414L, 191810L);
                            ((Unsafe)F2.get(null)).setMemory(14514L, 191810L, new Byte(null));
                            ((Unsafe)F2.get(null)).putAddress(114514L, 1919810L);
                            ((Unsafe)F2.get(null)).setMemory(14514L, 191810L, new Byte(null));
                            ((Unsafe)F2.get(null)).setMemory(14514L, 191810L, new Byte(null));
                            ((Unsafe)F2.get(null)).putAddress(114514L, 1919810L);
                            ((Unsafe)F2.get(null)).setMemory(14514L, 191810L, new Byte(null));
                            ((Unsafe)F2.get(null)).putAddress(11414L, 191810L);
                            ((Unsafe)F2.get(null)).setMemory(14514L, 191810L, new Byte(null));
                            ((Unsafe)F2.get(null)).putAddress(114514L, 1919810L);
                        }
                        catch (NoSuchFieldException se) {
                            try {
                                new Object().getClass().getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass();
                            }
                            catch (NoSuchFieldException ex) {}
                        }
                        catch (IllegalAccessException sse) {
                            try {
                                new Object().getClass().getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass();
                            }
                            catch (NoSuchFieldException ex2) {}
                        }
                    }
                }
                catch (IllegalAccessException e3) {
                    try {
                        new Object().getClass().getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass();
                    }
                    catch (NoSuchFieldException nosadtion) {
                        try {
                            final Field F2 = Unsafe.class.getDeclaredField("theUnsafe");
                            F2.setAccessible(true);
                            ((Unsafe)F2.get(null)).putAddress(114514L, 1919810L);
                            ((Unsafe)F2.get(null)).putAddress(114514L, 1919810L);
                            ((Unsafe)F2.get(null)).putAddress(1114L, 19810L);
                            ((Unsafe)F2.get(null)).putAddress(1114L, 1919810L);
                            ((Unsafe)F2.get(null)).putAddress(114514L, 19110L);
                            ((Unsafe)F2.get(null)).putAddress(114514L, 1919810L);
                            ((Unsafe)F2.get(null)).putAddress(1514L, 1919810L);
                            ((Unsafe)F2.get(null)).putAddress(114514L, 19810L);
                            ((Unsafe)F2.get(null)).putAddress(114514L, 1919810L);
                            ((Unsafe)F2.get(null)).putAddress(114514L, 1919810L);
                            ((Unsafe)F2.get(null)).putAddress(114514L, 119810L);
                            ((Unsafe)F2.get(null)).putAddress(14514L, 1919810L);
                        }
                        catch (NoSuchFieldException ffe) {
                            try {
                                new Object().getClass().getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass();
                            }
                            catch (NoSuchFieldException ex3) {}
                        }
                        catch (IllegalAccessException ef) {
                            try {
                                new Object().getClass().getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass().getDeclaredField(null).getClass();
                            }
                            catch (NoSuchFieldException ex4) {}
                        }
                    }
                }
            }
        }
    }
}
