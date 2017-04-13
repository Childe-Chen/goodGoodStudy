#include <stdio.h>
#include "com_cxd_jni_TestJNI.h"

JNIEXPORT void JNICALL Java_com_cxd_jni_TestJNI_sayHello(JNIEnv *env, jobject object){
    printf("Hello JNI\n");
}