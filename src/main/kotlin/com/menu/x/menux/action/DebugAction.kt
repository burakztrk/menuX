package com.menu.x.menux.action

import com.intellij.openapi.actionSystem.AnActionEvent

class DebugAction : MaestroAction() {
    override fun actionPerformed(e: AnActionEvent) {
        onAction(e, ActionType.DEBUG)
    }

    override fun update(e: AnActionEvent) {
        e.presentation.isEnabledAndVisible = isCorrectFileType(e)
    }
}