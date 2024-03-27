import java.util.Properties

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.swiftPackage)
    `maven-publish`
}

val orbitGroup = "com.campbell"
val orbitArtifact = "orbit"
val orbitVersion = "0.0.2"

var gitHubUser = ""
var gitHubKey = ""
val securePropertiesPath = "../secure.properties"

if(file(securePropertiesPath).exists()){
    val properties = readProperties(file(securePropertiesPath))
    gitHubUser = properties.getProperty("github_user")
    gitHubKey = properties.getProperty("github_key")
}

fun readProperties(propertiesFile: File) = Properties().apply {
    propertiesFile.inputStream().use { fis ->
        load(fis)
    }
}

group=orbitGroup
version=orbitVersion

publishing{
    publications {
        register<MavenPublication>("orbitVariants") {
            groupId = orbitGroup
            artifactId = orbitArtifact
            version = orbitVersion
            afterEvaluate {
                from(components["orbitVariants"])
            }
        }
    }
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/idevdroidapps/OrbitSDK")
            credentials {
                username = gitHubUser
                password = gitHubKey
            }
        }
    }
}

kotlin {
    androidTarget {
        publishAllLibraryVariants()
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "OrbitSDK"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            //put your multiplatform dependencies here
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }

    multiplatformSwiftPackage {
        swiftToolsVersion("5.3")
        targetPlatforms {
            iOS { v("16") }
        }
        outputDirectory(File("../"))
    }
}

android {
    namespace = "${orbitGroup}.${orbitArtifact}"
    compileSdk = 34
    defaultConfig {
        minSdk = 28
        aarMetadata{
            minCompileSdk = 28
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }

        getByName("debug") {
            isMinifyEnabled = false
        }
        create("staging") {
            initWith(getByName("debug"))
        }
    }
    flavorDimensions += "modes"
    productFlavors {
        create("full") {
            dimension = "modes"
        }
        create("preview") {
            dimension = "modes"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    publishing {
        multipleVariants("orbitVariants") {
            includeBuildTypeValues("debug", "release","staging")
            includeFlavorDimensionAndValues(
                dimension = "modes",
                values = arrayOf("full", "preview")
            )
        }
    }
}
