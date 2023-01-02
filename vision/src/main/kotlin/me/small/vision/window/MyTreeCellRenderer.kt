package me.small.vision.window

import me.small.vision.data.InfoPO
import java.awt.BorderLayout
import java.awt.Component
import javax.swing.ImageIcon
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JTree
import javax.swing.tree.DefaultMutableTreeNode
import javax.swing.tree.DefaultTreeCellRenderer
import javax.swing.tree.TreeCellRenderer

class MyTreeCellRenderer : JComponent(), TreeCellRenderer {
    private var father: DefaultTreeCellRenderer = DefaultTreeCellRenderer()

    init {
        father.leafIcon = ImageIcon(this.javaClass.classLoader.getResource("leaf.png"))
        layout = BorderLayout()
        add(JLabel("time"), BorderLayout.EAST)
        add(JLabel("info"), BorderLayout.WEST)
    }

    override fun getTreeCellRendererComponent(
        tree: JTree,
        value: Any,
        selected: Boolean,
        expanded: Boolean,
        leaf: Boolean,
        row: Int,
        hasFocus: Boolean
    ): Component {
        value as DefaultMutableTreeNode
        remove(1)
        if (value.userObject is InfoPO) {
            val data = value.userObject!! as InfoPO
            (components[0] as JLabel).text = data.time
            add(
                father.getTreeCellRendererComponent(
                    tree,
                    "${data.name} ${data.version}",
                    selected,
                    expanded,
                    leaf,
                    row,
                    hasFocus
                ), BorderLayout.WEST
            )
        } else {
            (components[0] as JLabel).text = ""
            add(
                father.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus),
                BorderLayout.WEST
            )
        }
        return this
    }
}