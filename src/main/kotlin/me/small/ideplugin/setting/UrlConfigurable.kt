package me.small.ideplugin.setting

import com.intellij.openapi.options.Configurable
import javax.swing.JComponent

class UrlConfigurable: Configurable {
    var component:UrlComponent?=null

    override fun createComponent(): JComponent {
        val ori=UrlState.getInstance()
        component= UrlComponent()
        component!!.name=ori.name
        return component!!.panel
    }

    override fun isModified(): Boolean {
        val ori=UrlState.getInstance()
        return ori.name != component!!.name
    }

    override fun apply() {
        val ori=UrlState.getInstance()
        ori.name=component!!.name
    }

    override fun getDisplayName(): String {
        return "DbClicker: Set My DB Info"
    }

    override fun getPreferredFocusedComponent(): JComponent {
        return component!!.getPreferredFocusedComponent()
    }

    override fun reset() {
        val ori=UrlState.getInstance()
        component!!.name=ori.name
    }

    override fun disposeUIResources() {
        component=null
    }
}