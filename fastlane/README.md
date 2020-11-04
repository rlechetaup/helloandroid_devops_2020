fastlane documentation
================
# Installation

Make sure you have the latest version of the Xcode command line tools installed:

```
xcode-select --install
```

Install _fastlane_ using
```
[sudo] gem install fastlane -NV
```
or alternatively using `brew install fastlane`

# Available Actions
## Android
### android incrementVersionNumber
```
fastlane android incrementVersionNumber
```
Increment Version Code
### android lint
```
fastlane android lint
```
Lint
### android test
```
fastlane android test
```
Test
### android build
```
fastlane android build
```
Compilar o apk - release
### android firebase
```
fastlane android firebase
```
Deploy to Firebase Distribution
### android app_center
```
fastlane android app_center
```
AppCenter
### android internal
```
fastlane android internal
```
Deploy a new version to the Google Play
### android all
```
fastlane android all
```
Firebase & Google

----

This README.md is auto-generated and will be re-generated every time [fastlane](https://fastlane.tools) is run.
More information about fastlane can be found on [fastlane.tools](https://fastlane.tools).
The documentation of fastlane can be found on [docs.fastlane.tools](https://docs.fastlane.tools).
