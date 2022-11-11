package me.small.ideplugin.setting

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil
import java.sql.DriverManager

@State(
    name = "me.small.ideplugin.setting.UrlState",
    storages = [Storage("SmallDBClicker.xml")]
)
class UrlState: PersistentStateComponent<UrlState> {
    var url: String = ""
    var user: String = ""
    var pwd: String = ""
    var dbInfo: HashMap<String, ArrayList<String>> = HashMap()

    companion object {
        fun getInstance(): UrlState {
            return ApplicationManager.getApplication().getService(UrlState::class.java)
        }
    }

    override fun getState(): UrlState {
        return this
    }

    override fun loadState(state: UrlState) {
        XmlSerializerUtil.copyBean(state, this)
        if (dbInfo.isEmpty()) {
            updateDb { }
        }
        println(dbInfo)
    }

    fun updateDb(callback: () -> Unit) {
        DriverManager.registerDriver(org.postgresql.Driver())
        Thread {
            try {
                val res: HashMap<String, ArrayList<String>> = HashMap()
                val con = DriverManager.getConnection(url, user, pwd)
                val info = con.metaData.getTables(null, null, null, arrayOf("TABLE"))
                while (info.next()) {
                    val table = info.getString(3)
                    if (table.startsWith("flywaylite")) continue
                    val tableName = info.getString(3) + " " + info.getString(5)
                    val column = con.metaData.getColumns(null, null, table, null)
                    val columns = ArrayList<String>()
                    while (column.next()) {
                        columns.add(column.getString(4) + "  " + column.getString(6))
                    }
                    res[tableName] = columns
                }
                dbInfo = res
                callback()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.start()
    }
}