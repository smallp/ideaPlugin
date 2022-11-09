package me.small.ideplugin.setting

import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBTextField
import com.intellij.util.ui.FormBuilder
import javax.swing.JComponent
import javax.swing.JPanel


class UrlComponent {
    private val _panel: JPanel
    private val _name: JBTextField=JBTextField()
    var name:String
        get() {
            return _name.text
        }
        set(value) {
            _name.text=value
        }

    val panel:JComponent
        get() =_panel

    init {
        _panel= FormBuilder.createFormBuilder()
            .addLabeledComponent(JBLabel("Enter user name: "), _name, 1, false)
            .addComponentFillVertically(JPanel(), 0)
            .panel
    }

    fun getPreferredFocusedComponent():JComponent{
        return _name
    }
}