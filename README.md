This is a Kotlin Multiplatform Library project targeting Android, iOS. Library module name is OrbitSDK

* `/androidApp` contains Android application with dependency on OrbitSDK module.

* `/OrbitSDK` is for code that will be shared across your Multiplatform applications.
  It contains several sub-folders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications with dependency on OrbitSDK module.