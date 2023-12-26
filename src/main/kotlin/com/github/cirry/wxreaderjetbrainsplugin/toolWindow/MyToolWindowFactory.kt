package com.github.cirry.wxreaderjetbrainsplugin.toolWindow

import com.github.cirry.wxreaderjetbrainsplugin.MyBundle
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBPanel
import com.intellij.ui.content.ContentFactory
import com.intellij.ui.jcef.JBCefApp
import com.intellij.ui.jcef.JBCefBrowser
import com.intellij.ui.jcef.executeJavaScriptAsync
import java.awt.*
import javax.swing.*


class MyToolWindowFactory : ToolWindowFactory {

    init {

    }

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val myToolWindow = MyToolWindow(toolWindow)
        val content = ContentFactory.getInstance().createContent(myToolWindow.getContent(), null, false)
        toolWindow.contentManager.addContent(content)
    }

    override fun shouldBeAvailable(project: Project) = true

    class MyToolWindow(toolWindow: ToolWindow) {
        fun getContent() = JBPanel<JBPanel<*>>().apply {
            val noSupportLabel = JBLabel(MyBundle.message("noSupport"))
            if (JBCefApp.isSupported()) {
                val jbcef = JBCefBrowser()
                add(jbcef.component, BorderLayout.CENTER)
                jbcef.loadURL("https://weread.qq.com/")
            } else {
                add(noSupportLabel)
            }
        }
    }
}
