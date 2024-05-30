package com.menu.x.menux.util

import com.intellij.openapi.project.Project
import com.jediterm.terminal.TerminalColor
import com.jediterm.terminal.TextStyle
import org.jetbrains.plugins.terminal.TerminalToolWindowManager

fun Project.runCommand(command: String, fileName: String) {
    val terminalView = TerminalToolWindowManager.getInstance(this)
    terminalView.terminalWidgets.forEach {
        it.ttyConnector?.close()
    }

    runCatching {
        terminalView.createLocalShellWidget(basePath, fileName).apply {
            terminal.styleState.setDefaultStyle(TextStyle(TerminalColor.WHITE, TerminalColor.BLACK))
            executeCommand(command)
        }
    }.onFailure {
        it.printStackTrace()
    }
}