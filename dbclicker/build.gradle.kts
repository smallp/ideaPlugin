group = "me.small"
version = "1.1.1"

plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.9.20"
    id("org.jetbrains.intellij.platform") version "2.1.0"
}

repositories {
    maven("https://maven.aliyun.com/repository/public")
    maven("https://maven.aliyun.com/repository/spring")
    maven("https://www.jetbrains.com/intellij-repository/releases")
    intellijPlatform {
        localPlatformArtifacts()
        intellijDependencies()
    }
}
dependencies {
    implementation("org.postgresql:postgresql:42.7.3")
    implementation("mysql:mysql-connector-java:8.0.33")
    intellijPlatform{
        local("/Applications/IntelliJ IDEA CE.app")
        instrumentationTools()
    }
}
intellijPlatform{
    pluginConfiguration{
        ideaVersion{
            sinceBuild="241"
        }
    }
}
kotlin{
    jvmToolchain(21)
}