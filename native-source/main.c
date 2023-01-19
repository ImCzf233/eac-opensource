#include "jni.h"
#include "minhook.h"

//一群ENSE脑残天天抄别人的东西 别人没说啥
//然后又来个脑瘫抄你们的 你们ENSE脑残就急了是吧
//你们这几个ENSE脑残又要伸张正义了是把

/*
    **************************************************************************
    * ByteCode Resolve By DENATIVE (VER:2022/12/14 - Code By SeaShore)
    **************************************************************************
    * Method: EAC/coremod/EACDetector.Monitor()I
    * Sniffer: TestV1@Dynamic
    * Semi ByteCode !!NOT FULL!! :
    * ALOAD 0
    * INVOKEVIRTUAL java/lang/Class ()Ljava/lang/Class;
    * *** NATIVE CODE HERE ***
    * ALOAD 0
    * GETFIELD EAC/coremod/EACDetector.VL I
    * SIPUSH 7933
    * PUTFIELD EAC/coremod/EACDetector.VL I
    * SIPUSH 1001
    * IRETURN
    **************************************************************************
*/
jint JNIEXPORT JNICALL Java_EAC_coremod_EACDetector_Monitor(JNIEnv *env, jobject instance) {
  struct _jobject *clazz;
  struct _jfieldID *vl_id;

  clazz = (*env)->GetObjectClass(env, instance);
  vl_id = (*env)->GetFieldID(env, clazz, "VL", "I");
  //哥们在这手动给你还原字节码 一一对应
  //ALOAD0
  //GETFIELD EAC/coremod/EACDetector.VL I
  (*env)->GetIntField(env, instance, vl_id);
  //SIPUSH 7933
  //PUTFIELD EAC/coremod/EACDetector.VL I
  (*env)->SetIntField(env, instance, vl_id, 7933);
  //SIPUSH 1001
  //IRETURN
  return (jint)1001;
}

/*
    **************************************************************************
    * ByteCode Resolve By DENATIVE (VER:2022/12/14 - Code By SeaShore)
    **************************************************************************
    * Method: EAC/coremod/EACDetector.FileMonitor()I
    * Sniffer: TestV1@Dynamic
    * Semi ByteCode !!NOT FULL!! :
    * ICONST_1
    * IRETURN
    **************************************************************************
*/
jboolean JNIEXPORT JNICALL Java_EAC_coremod_EACDetector_FileMonitor(JNIEnv *env, jobject instance, jstring arg0){
  return (jboolean)1; //傻逼空壳，用来吓唬你的，实际上还得靠mac反作弊保护
}

/*
    **************************************************************************
    * ByteCode Resolve By DENATIVE (VER:2022/12/14 - Code By SeaShore)
    **************************************************************************
    * Method: EAC/coremod/EACDetector.protectVL()I
    * Sniffer: TestV1@Dynamic
    * Semi ByteCode !!NOT FULL!! :
    * ICONST_0
    * IRETURN
    **************************************************************************
*/
jint JNIEXPORT JNICALL Java_EAC_coremod_EACDetector_protectVL(JNIEnv *env, jobject instance){
  return (jint)0; //傻逼空壳
}

//fileMonitor 压根就不存在 傻逼玩意

typedef jclass(*Java_java_lang_ClassLoader_defineClass0)(JNIEnv* env, jobject instance, jstring name, jbyteArray data, jint offset, jint length, jobject pd);

typedef jclass(*Java_java_lang_ClassLoader_defineClass1)(JNIEnv* env, jobject instance, jstring name, jbyteArray data, jint offset, jint length, jobject pd, jstring source);

typedef jclass(*Java_java_lang_ClassLoader_defineClass2)(JNIEnv* env, jobject instance, jstring name, jobject data, jint offset, jint length, jobject pd, jstring source);

Java_java_lang_ClassLoader_defineClass0 original_d0 = NULL;
Java_java_lang_ClassLoader_defineClass1 original_d1 = NULL;
Java_java_lang_ClassLoader_defineClass2 original_d2 = NULL;

//逆的不好 你可以帮帮我找到丢失的EAC代码吗？
void* check(JNIEnv *env, jstring name){

    jclass strClazz = (*env)->FindClass(env, "java/lang/String");
    jmethodID str_getBytes = (*env)->GetMethodID(env, strClazz, "getBytes", "(Ljava/lang/String;)[B");
    jbyteArray ba = (jbyteArray)(*env)->CallObjectMethod(env,name,str_getBytes,(*env)->NewStringUTF(env, "utf-8"));
    jsize len = (*env)->GetArrayLength(env, ba);
    jbyte* str_bytes = (*env)->GetByteArrayElements(env, ba, 0);

    void* result = 0;
    if(len <= 0){
         result = 0;
    } else {
        result = malloc(__CFADD__(result,1)?-1:result+1);
         memcpy(result,str_bytes,len);
        *((jbyte*)result + str_bytes) = 0;
    }

    (*env)->ReleaseByteArrayElements(env, ba, str_bytes, 0);
    return result;
}

jclass JNICALL dc0(JNIEnv* env, jobject instance, jstring name, jbyteArray data, jint offset, jint length, jobject pd){
    if(!check(env, name)){
        exit(0);
    }
    return original_d0(env,instance,name,data,offset,length,pd);
}
jclass JNICALL dc1(JNIEnv* env, jobject instance, jstring name, jbyteArray data, jint offset, jint length, jobject pd, jstring source){
    if(!check(env, name)){
        exit(0);
    }
    return original_d1(env,instance,name,data,offset,length,pd,source);
}
jclass JNICALL dc2(JNIEnv* env, jobject instance, jstring name, jobject data, jint offset, jint length, jobject pd, jstring source){
    if(!check(env, name)){
        exit(0);
    }
    return original_d2(env,instance,name,data,offset,length,pd,source);
}

//这个方法是hook 我就不在这里用工具尝试分析为字节码了
//我用minhook给你写一个
//Loser Stop Coding shit
void JNIEXPORT JNICALL Java_EAC_coremod_EACDetector_Loserdontinject(JNIEnv *env, jobject instance){

    //对不起 主播不会C++ 主播是C++ 低级低手 使用printf
    printf("Start EAC");
    printf("EAC BY Hillo");

    //初始化 MH
    if (MH_Initialize() == MH_OK){
        printf("Don't Cheat Babe by Hillo:5");fflush(stdout);
    } 

    //上钩子
    if(MH_CreateHookApi(L"java.dll", "Java_java_lang_ClassLoader_defineClass0",(void *)&dc0, &original_d0) == MH_OK){
         printf("Don't Cheat Babe by Hillo:0");fflush(stdout);
    }
    if(MH_CreateHookApi(L"java.dll", "Java_java_lang_ClassLoader_defineClass1",(void *)&dc1, &original_d1) == MH_OK){
        printf("Don't Cheat Babe by Hillo:1");fflush(stdout);
    }
    if(MH_CreateHookApi(L"java.dll", "Java_java_lang_ClassLoader_defineClass2",(void *)&dc2, &original_d2) == MH_OK){
        printf("Don't Cheat Babe by Hillo:2");fflush(stdout);
    }

    //启用钩子
    if(MH_EnableHook(MH_ALL_HOOKS) != MH_OK){
        printf("Don't Cheat Babe by Hillo:3");fflush(stdout);
    }
    printf("EAC LOADED!");fflush(stdout);
}

