group = "me.small"
version = "1.1.5"

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
    intellijPlatform{
        local("/Applications/IntelliJ IDEA CE.app")
        instrumentationTools()
    }
}
intellijPlatform{
    buildSearchableOptions=false
    pluginConfiguration{
        ideaVersion{
            sinceBuild="251"
        }
    }
}
kotlin{
    jvmToolchain(21)
}