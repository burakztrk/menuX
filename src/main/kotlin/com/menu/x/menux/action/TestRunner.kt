package com.menu.x.menux.action

import com.intellij.notification.NotificationType
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.options.ShowSettingsUtil
import com.menu.x.menux.util.CommandBuilder
import com.menu.x.menux.config.PanelConfigState
import com.menu.x.menux.config.PanelConfigurable
import com.menu.x.menux.util.*

interface TestRunner {
    fun onAction(e: AnActionEvent, actionType: ActionType)
}

class MaestroTestRunnerImpl(private val extension: String) : TestRunner {
    override fun onAction(e: AnActionEvent, actionType: ActionType) {
        val project = e.project ?: return

        runCatching {
            ConfigUtils.checkConfigs()
        }.onFailure {
            project.notify(it.message.orEmpty(), NotificationType.ERROR)
            ShowSettingsUtil.getInstance().editConfigurable(project, PanelConfigurable())
            return
        }

        val fileInfos = project.filterSelectedFiles(extension)
        runCatching {
            val file = FileUtils.checkFileSize(fileInfos)
            val appId = if (actionType == ActionType.DEBUG) PanelConfigState.instance.debugAppId else PanelConfigState.instance.prodAppId
            val command = CommandBuilder()
                    .command("maestro")
                    .subCommand("test")
                    .optionWithArg("e", "APP_ID=$appId")
                    .argument(file.path)
                    .build()

            project.runCommand(command, file.name)
            project.notify("Build Started!\n${file.name}")
        }.onFailure {
            project.notify(it.message.orEmpty(), NotificationType.ERROR)
        }
    }
}