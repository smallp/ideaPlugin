subprojects {
    repositories {
        maven("https://maven.aliyun.com/repository/central")
        maven("https://maven.aliyun.com/repository/spring")
    }
    buildscript {
        repositories {
            maven("https://maven.aliyun.com/repository/gradle-plugin")
            maven("https://maven.aliyun.com/repository/central")
            maven("https://maven.aliyun.com/repository/spring")
            maven("https://maven.jinuo.me/repository/jinuo/")
        }
    }
}
