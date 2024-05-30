package com.menu.x.menux.action

import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction

abstract class MaestroAction(
        maestroPerformer: TestRunner = MaestroTestRunnerImpl(TEST_FILE_EXT),
        fileFilter: FileFilterImpl = FileFilterImpl(TEST_FILE_EXT)
) : TestRunner by maestroPerformer,
        FileFilter by fileFilter,
        AnAction() {

    override fun getActionUpdateThread(): ActionUpdateThread {
        return super.getActionUpdateThread()
    }

    companion object {
        private const val TEST_FILE_EXT = "yaml"
    }
}
