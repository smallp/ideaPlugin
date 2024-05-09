group = "me.small"
version = "1.1.2"

plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.9.20"
    id("org.jetbrains.intellij.platform") version "2.0.0-beta1"
}

repositories {
    maven("https://maven.aliyun.com/repository/public")
    maven("https://maven.aliyun.com/repository/spring")
    intellijPlatform {
        defaultRepositories()
    }
}
dependencies {
    intellijPlatform{
        intellijIdeaCommunity("2024.1")
        instrumentationTools()
    }
}
intellijPlatform{
    buildSearchableOptions=false
    pluginConfiguration{
        ideaVersion{
            sinceBuild="223"
            untilBuild="241.*"
        }
    }
}
tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }
}
