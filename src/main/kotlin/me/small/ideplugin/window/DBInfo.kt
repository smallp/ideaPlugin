package me.small.ideplugin.window

import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.fileEditor.ex.FileEditorManagerEx
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import me.small.ideplugin.setting.UrlState
import javax.swing.JButton
import javax.swing.JPanel
import javax.swing.JTree

class DBInfo(private val toolWindow: ToolWindow) {
    private var panel1: JPanel? = null
    private var dbList: JTree? = null
    private var refresh: JButton? = null

    val content get() = panel1

    init {
        refresh?.addActionListener {
            val project: Project = toolWindow.project
            val editor: Editor = FileEditorManagerEx.getInstance(project).selectedTextEditor ?: return@addActionListener
            val document: Document = editor.document
            val start = editor.caretModel.primaryCaret.selectionStart
            val setting = UrlState.getInstance()
            WriteCommandAction.runWriteCommandAction(
                project
            ) {
                document.insertString(start, setting.name)
            }
        }
    }
}