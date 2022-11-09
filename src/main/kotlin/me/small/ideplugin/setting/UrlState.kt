package me.small.ideplugin.setting

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil

@State(
    name = "me.small.ideplugin.setting.UrlState",
    storages = [Storage("SmallDBClicker.xml")]
)
class UrlState: PersistentStateComponent<UrlState> {
    var name:String="your name"

    companion object{
        fun getInstance():UrlState{
            return ApplicationManager.getApplication().getService(UrlState::class.java)
        }
    }

    override fun getState(): UrlState {
        return this
    }

    override fun loadState(state: UrlState) {
        XmlSerializerUtil.copyBean(state, this)
    }
}