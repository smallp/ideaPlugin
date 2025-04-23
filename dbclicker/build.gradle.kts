group = "me.small"
version = "1.1.2"

plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "2.1.0"
    id("org.jetbrains.intellij.platform") version "2.5.0"
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
            sinceBuild="251"
        }
    }
}
kotlin{
    jvmToolchain(21)
}