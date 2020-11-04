#!/bin/bash
#https://spin.atomicobject.com/2016/03/10/android-test-script/

#Start the emulator
$ANDROID_SDK_ROOT/tools/emulator -avd Nexus_5X_API_29  &
EMULATOR_PID=$!

# Wait for Android to finish booting
WAIT_CMD="$ANDROID_SDK_ROOT/platform-tools/adb wait-for-device shell getprop init.svc.bootanim"
until $WAIT_CMD | grep -m 1 stopped; do
  echo "Waiting..."
  sleep 1
done

# Unlock the Lock Screen
#$ANDROID_SDK_ROOT/platform-tools/adb shell input keyevent 82

# Clear and capture logcat
#$ANDROID_SDK_ROOT/platform-tools/adb logcat -c
#$ANDROID_SDK_ROOT/platform-tools/adb logcat > build/logcat.log &
#LOGCAT_PID=$!

# Run the tests
./gradlew connectedDebugAndroidTest -i

# Stop the background processes
#kill $LOGCAT_PID
kill $EMULATOR_PID