package me.small.ideplugin.window

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory
import me.small.ideplugin.setting.UrlState

class DBToolWindowFactory : ToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val a = DBInfo(toolWindow)
        val cf = ContentFactory.getInstance()
        val content = cf.createContent(a.content, "Hello", false)
        toolWindow.contentManager.addContent(content)
    }

    override suspend fun isApplicableAsync(project: Project): Boolean {
        val setting = UrlState.getInstance(project)
        return setting.url.isNotEmpty()
    }
}