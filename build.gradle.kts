
buildscript {
    repositories {
        maven("https://maven.aliyun.com/repository/central")
        maven("https://maven.aliyun.com/repository/spring")
        maven("https://maven.jinuo.me/repository/jinuo/")
    }
}
allprojects {
    repositories {
        maven("https://maven.aliyun.com/repository/public")
        maven("https://maven.aliyun.com/repository/spring")
        maven("https://maven.jinuo.me/repository/jinuo/")
    }
}