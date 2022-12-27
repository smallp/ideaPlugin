package me.small.vision.window

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory
import me.small.vision.data.VisionInfo

class DBToolWindowFactory : ToolWindowFactory {
    private lateinit var info: VisionInfo
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        info.parse()
        val a = DBInfo(info)
        val cf = ContentFactory.getInstance()
        val content = cf.createContent(a.content, "Vision", false)
        toolWindow.contentManager.addContent(content)
    }

    override fun isApplicable(project: Project): Boolean {
        info = VisionInfo(project)
        return info.haveFile()
    }
}