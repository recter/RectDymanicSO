#include "rectdymanic.h"
#include <string.h>
#include <jni.h>
#include <android/log.h>

jstring JNICALL Java_com_rect_dymanic_MainActivity_CallDymanicFuncFormAssets(JNIEnv * env, jclass object, jint x, jint y)
{
	__android_log_print(ANDROID_LOG_INFO, "JNIMsg", "C JNI  ---- > x =  %d,y = %d",x,y);
    return env->NewStringUTF("I Am Form C JNI And I Am In Assets!");
}
