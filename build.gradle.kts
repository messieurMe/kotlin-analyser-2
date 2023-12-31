import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.ir.backend.js.compile

plugins {
    kotlin("jvm") version "1.8.21"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

kotlin { }
dependencies {
    testImplementation(kotlin("test"))
    implementation("com.github.drieks.antlr-kotlin:antlr-kotlin-runtime:ce9944fa0c")
    // please look at https://jitpack.io/#drieks/antlr-kotlin to find the latest version
//    api("com.github.kotlinx.ast:grammar-kotlin-parser-antlr-kotlin:0.1.0")
//    implementation("com.github.drieks.antlr-kotlin:antlr-kotlin-runtime:ce9944fa0c")
    api("com.github.kotlinx.ast:grammar-kotlin-parser-antlr-kotlin:0.1.0")
    // please look at https://jitpack.io/#drieks/antlr-kotlin to find the latest version
    implementation("com.github.kotlinx.ast:grammar-kotlin-parser-antlr-kotlin:0.1.0")
    // please look at https://jitpack.io/#drieks/antlr-kotlin to find the latest version
//    implementation("com.github.kotlinx.ast:grammar-kotlin-parser-antlr-java:0.1.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

