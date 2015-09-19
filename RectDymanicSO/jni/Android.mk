LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_LDLIBS := -L$(SYSROOT)/usr/lib -llog
LOCAL_MODULE    := rectdymanicassets
LOCAL_SRC_FILES := rectdymanic.cpp \

include $(BUILD_SHARED_LIBRARY)
