@echo off
"D:\\ProgramData\\AndroidSDK\\ndk\\27.2.12479018\\ndk-build.cmd" ^
  "NDK_PROJECT_PATH=null" ^
  "APP_BUILD_SCRIPT=D:\\AndroidStudioProjects\\Coder\\terminal-emulator\\src\\main\\jni\\Android.mk" ^
  "APP_ABI=armeabi-v7a" ^
  "NDK_ALL_ABIS=armeabi-v7a" ^
  "NDK_DEBUG=1" ^
  "APP_PLATFORM=android-28" ^
  "NDK_OUT=D:\\AndroidStudioProjects\\Coder\\terminal-emulator\\build\\intermediates\\cxx\\Debug\\4o4uo3o6/obj" ^
  "NDK_LIBS_OUT=D:\\AndroidStudioProjects\\Coder\\terminal-emulator\\build\\intermediates\\cxx\\Debug\\4o4uo3o6/lib" ^
  "APP_CFLAGS+=-std=c11" ^
  "APP_CFLAGS+=-Wall" ^
  "APP_CFLAGS+=-Wextra" ^
  "APP_CFLAGS+=-Werror" ^
  "APP_CFLAGS+=-Os" ^
  "APP_CFLAGS+=-fno-stack-protector" ^
  "APP_CFLAGS+=-Wl,--gc-sections" ^
  termux
