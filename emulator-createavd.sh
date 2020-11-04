#!/bin/bash

# Download an ARM system image to create an ARM emulator.
sdkmanager "system-images;android-16;default;armeabi-v7a"

# Create an ARM AVD emulator, with a 100 MB SD card storage space. Echo "no"
# because it will ask if you want to use a custom hardware profile, and you don't.
# https://medium.com/@AndreSand/android-emulator-on-docker-container-f20c49b129ef
echo "no" | avdmanager create avd \
    -n Android_4.1_API_16 \
    -k "system-images;android-16;default;armeabi-v7a" \
    -c 100M \
    --force

# Launch the emulator in the background
$ANDROID_HOME/emulator/emulator -avd Android_4.1_API_16 -no-skin -no-audio -no-window -no-boot-anim -gpu off &

# Note: You will have to add a suitable time delay, to wait for the emulator to launch.
