import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.multiplatform.library)
    alias(libs.plugins.kotlinx.binary.compatibility.validator)
    alias(libs.plugins.dokka)
    alias(libs.plugins.kover)
    `maven-publish`
    signing
}

kotlin {

    // FORCES CHECK OF PUBLIC API DECLARATIONS
    // DON'T FORGET TO RUN `./gradlew apiDump`
    explicitApi()

    jvm {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }

    js {
        binaries.executable()
        browser {
            commonWebpackConfig {
                cssSupport {
                    enabled.set(true)
                }
            }
            testTask {
                useKarma {
                    useChromeHeadless()
                }
            }
        }
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        binaries.executable()
        compilerOptions { freeCompilerArgs.add("-Xwasm-attach-js-exception") }
    }

    listOf(
        iosX64(), // mobile
        iosArm64(), // mobile
        iosSimulatorArm64(), // mobile
        macosArm64(), // desktop
        tvosArm64(), // tv
        tvosSimulatorArm64(), // tv
        watchosArm32(), // watch
        watchosArm64(), // watch
        watchosDeviceArm64(), // watch
        watchosSimulatorArm64(), // watch
    ).forEach {
        it.binaries.framework {
            baseName = "basic-logging"
            isStatic = true
        }
    }

    linuxX64 { binaries.staticLib { baseName = "basic-logging" } }

    mingwX64 { binaries.staticLib { baseName = "basic-logging" } }

    //https://kotlinlang.org/docs/native-objc-interop.html#export-of-kdoc-comments-to-generated-objective-c-headers
    targets.withType<KotlinNativeTarget> {
        compilations["main"].compileTaskProvider.configure{
            compilerOptions {
                freeCompilerArgs.add("-Xexport-kdoc")
            }
        }
    }

    compilerOptions { freeCompilerArgs.add("-Xexpect-actual-classes") }

    android {
        namespace = "app.lexilabs.basic.logging"
        compileSdk = libs.versions.build.sdk.compile.get().toInt()
        minSdk = libs.versions.build.sdk.min.get().toInt()
        withJava()

        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_26)
        }
    }
}