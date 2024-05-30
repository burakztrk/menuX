package com.menu.x.menux.action

import com.intellij.openapi.actionSystem.AnActionEvent

class ReleaseAction : MaestroAction() {
    override fun actionPerformed(e: AnActionEvent) {
        onAction(e, ActionType.RELEASE)
    }

    override fun update(e: AnActionEvent) {
        e.presentation.isEnabledAndVisible = isCorrectFileType(e)
    }
}
