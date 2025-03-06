package me.small.vision.data

import com.intellij.openapi.project.Project
import java.io.File
import java.util.regex.Pattern

class VisionInfo(private val project: Project) {
    val parsedData = HashMap<String, HashMap<String, InfoPO>>()
    private val visionConfig: Pattern = Pattern.compile(".?visionRefresh.+")
    fun haveFile(): Boolean {
        val fold = File(project.basePath, "gradle/catalogs")
        return fold.exists()
    }

    fun parse() {
        parsedData.clear()
        val version = Pattern.compile("cur is (.+?)\\. (.+)")
        val info = Pattern.compile("(.+?)= \\{ module =.*require = \"(.+?)\"")

        val fold = File(project.basePath, "gradle/catalogs")
        fold.listFiles()?.forEach { file ->
            if (!file.name.endsWith(".toml")) return@forEach
            val filename = file.name.split(".")[0]
            val data = HashMap<String, InfoPO>()
            var name = ""
            var readme = ""
            var versions = ""
            var v = ""
            var nowV = ""
            var time = ""
            file.readLines().let { t ->
                val size = t.size
                t.subList(5, size)
            }.forEach {
                when {
                    it.isBlank() -> {
                        if (name.isNotEmpty()) {
                            data[name] = InfoPO(nowV, v, readme, versions.split(","), name, time)
                            name = ""
                        }
                    }

                    it.startsWith("# README") -> {
                        readme = it.substring(10)
                        if (readme == "null") readme = ""
                    }

                    it.startsWith("# Versions") -> {
                        val res = version.matcher(it)
                        res.find()
                        v = res.group(1)
                        versions = res.group(2)
                    }

                    it.startsWith("# Update Time") -> {
                        val res = it.split(":")
                        time = res.subList(1, res.size).joinToString(":").trim()
                    }

                    it.startsWith("#") -> {}
                    else -> {
                        val res = info.matcher(it)
                        res.find()
                        name = res.group(1).trim()
                        nowV = res.group(2)
                    }
                }
            }
            parsedData[filename] = data
        }
    }

    fun isUpdate(): Boolean {
        val config = File(project.basePath, "gradle.properties")
        if (!config.exists()) return true
        val line = getConfig(config.readText()) ?: return true
        if (line.startsWith("#")) return true
        val c = line.split("=")[1].trim()
        return c != "false"
    }

    private fun getConfig(file: String): String? {
        val match = visionConfig.matcher(file)
        return if (match.find()) {
            match.group()
        } else null
    }
}