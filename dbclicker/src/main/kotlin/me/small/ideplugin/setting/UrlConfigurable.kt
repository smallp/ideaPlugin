package me.small.ideplugin.setting

import com.intellij.openapi.options.Configurable
import com.intellij.openapi.project.Project
import javax.swing.JComponent

class UrlConfigurable(private val poj: Project) : Configurable {
    var component: UrlComponent? = null

    override fun createComponent(): JComponent {
        val ori = UrlState.getInstance(poj)
        component = UrlComponent()
        component!!.name = ori.user
        component!!.pwd = ori.pwd
        component!!.url = ori.url
        return component!!.panel
    }

    override fun isModified(): Boolean {
        val ori = UrlState.getInstance(poj)
        return ori.user != component!!.name || ori.pwd != component!!.pwd || ori.url != component!!.url
    }

    override fun apply() {
        val ori = UrlState.getInstance(poj)
        ori.user = component!!.name
        ori.pwd = component!!.pwd
        ori.url = component!!.url
    }

    override fun getDisplayName(): String {
        return "DbClicker"
    }

    override fun getPreferredFocusedComponent(): JComponent {
        return component!!.getPreferredFocusedComponent()
    }

    override fun reset() {
        val ori = UrlState.getInstance(poj)
        component!!.name = ori.user
        component!!.pwd = ori.pwd
        component!!.url = ori.url
    }

    override fun disposeUIResources() {
        component = null
    }
}