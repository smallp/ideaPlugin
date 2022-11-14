package me.small.ideplugin.window

import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.fileEditor.ex.FileEditorManagerEx
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import me.small.ideplugin.setting.UrlState
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import javax.swing.JButton
import javax.swing.JPanel
import javax.swing.JTree
import javax.swing.tree.DefaultMutableTreeNode
import javax.swing.tree.DefaultTreeModel

class DBInfo(private val toolWindow: ToolWindow) : MouseListener {
    private var panel1: JPanel? = null
    private var dbList: JTree? = null
    private var refresh: JButton? = null
    private val set: UrlState = UrlState.getInstance(toolWindow.project)

    val content get() = panel1

    init {
        refresh?.addActionListener {
            set.updateDb {
                drawTree()
            }
            drawTree(true)
        }
        dbList!!.addMouseListener(this)
        drawTree()
    }

    private fun drawTree(clear: Boolean = false) {
        val data = set.dbInfo
        val res = DefaultMutableTreeNode("root")
        if (!clear) {
            val sorted = data.keys.toSortedSet()
            sorted.forEach { k ->
                val v = data[k]!!
                val item = DefaultMutableTreeNode(k)
                v.forEach {
                    item.add(DefaultMutableTreeNode(it))
                }
                res.add(item)
            }
        }
        dbList!!.model = DefaultTreeModel(res, false)
    }

    override fun mouseClicked(e: MouseEvent) {
        if (e.clickCount == 2) {
            val res = dbList!!.getPathForLocation(e.x, e.y)?.path ?: return
            if (res.size != 3) return
            var table = res[1].toString()
            var col = res[2].toString()
            table = table.split(" ")[0].uppercase()
            col = col.split(" ")[0].uppercase()

            val project: Project = toolWindow.project
            val editor: Editor = FileEditorManagerEx.getInstance(project).selectedTextEditor ?: return
            val document: Document = editor.document
            val start = editor.caretModel.primaryCaret.selectionStart
            WriteCommandAction.runWriteCommandAction(
                project
            ) {
                document.insertString(start, "T_${table}.${col},")
            }
        }
    }

    override fun mousePressed(e: MouseEvent?) {
    }

    override fun mouseReleased(e: MouseEvent?) {
    }

    override fun mouseEntered(e: MouseEvent?) {
    }

    override fun mouseExited(e: MouseEvent?) {
    }
}