group = "me.small"
version = "1.1.1"

plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.7.20"
    id("org.jetbrains.intellij") version "1.11.0"
}

dependencies {
//    implementation("org.jetbrains:marketplace-zip-signer:0.1.8")
}


// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    version.set("2023.1")
    type.set("IC") // Target IDE Platform
    plugins.set(listOf(/* Plugin Dependencies */))
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
    buildSearchableOptions {
        enabled = false
    }
    jarSearchableOptions {
        enabled = false
    }


    patchPluginXml {
        sinceBuild.set("222")
        untilBuild.set("232.*")
    }

//    signPlugin {
//        certificateChainFile.set(File(System.getenv("CERTIFICATE_CHAIN")))
//        privateKeyFile.set(File(System.getenv("PRIVATE_KEY")))
//        cliPath.set("/usr/local/lib/marketplace-zip-signer-cli.jar")
//    }
//
//    publishPlugin {
//        token.set(System.getenv("PUBLISH_TOKEN"))
//    }
}
