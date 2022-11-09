package me.small.ideplugin

import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import me.small.ideplugin.setting.UrlState

class SmallTest : AnAction() {

    override fun update(e: AnActionEvent) {
        val a=e.getData(CommonDataKeys.EDITOR)
        e.presentation.isEnabledAndVisible=a!=null && a.document.isWritable
    }

    override fun getActionUpdateThread(): ActionUpdateThread = ActionUpdateThread.BGT

    override fun actionPerformed(e: AnActionEvent) {
        val editor: Editor = e.getRequiredData(CommonDataKeys.EDITOR)
        val project: Project = e.getRequiredData(CommonDataKeys.PROJECT)
        val document: Document = editor.document
        val start=editor.caretModel.primaryCaret.selectionStart
        val setting=UrlState.getInstance()
        WriteCommandAction.runWriteCommandAction(
            project
        ) {
            document.insertString(start,setting.name)
        }
    }
}