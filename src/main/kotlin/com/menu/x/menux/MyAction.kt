package com.menu.x.menux

import com.intellij.ide.projectView.ProjectView
import com.intellij.ide.projectView.impl.nodes.AbstractPsiBasedNode
import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.jediterm.terminal.TerminalColor
import com.jediterm.terminal.TextStyle
import org.jetbrains.plugins.terminal.TerminalToolWindowManager

class MyAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val fileInfos = project.getSelectedYamlFiles()

        runCatching {
            val file = checkFiles(fileInfos)
            project.runMaestroCommand(file)
            project.notify("Build Started!\n${file.name}")
        }.onFailure {
            project.notify(it.message.orEmpty(), NotificationType.ERROR)
        }
    }

    @Throws(IllegalArgumentException::class)
    private fun checkFiles(files: List<FileInfo>): FileInfo {
        when (files.size) {
            0 -> throw IllegalArgumentException("Select A Yaml File")
            1 -> return files.first()
            else -> throw IllegalArgumentException("Select Only 1 Yaml File")
        }
    }

    private fun Project.getSelectedYamlFiles(): List<FileInfo> {
        return ProjectView
                .getInstance(this)
                .currentProjectViewPane
                .selectedUserObjects
                .filterIsInstance(AbstractPsiBasedNode::class.java)
                .filter { it.virtualFile?.extension == "yaml" }
                .mapNotNull { it.virtualFile?.toFileInfo() }
    }

    private fun Project.runMaestroCommand(file: FileInfo) {
        val command = CommandBuilder()
                //.raw("maestro test -e APP_ID=com.trendyol.go.stage ${file.path}")
                .command("maestro")
                .subCommand("test")
                .optionWithArg("e", "APP_ID=com.trendyol.go.stage")
                .argument(file.path)
                .build()
        runCommand(command)
    }

    private fun Project.runCommand(command: String) {
        val terminalView = TerminalToolWindowManager.getInstance(this)
        terminalView.terminalWidgets.forEach {
            it.ttyConnector?.close()
        }

        runCatching {
            terminalView.createLocalShellWidget(basePath, "name").apply {
                terminal.styleState.setDefaultStyle(TextStyle(TerminalColor.WHITE, TerminalColor.BLACK))
                executeCommand(command)
            }
        }.onFailure {
            it.printStackTrace()
        }
    }

    private fun Project.notify(text: String, type: NotificationType = NotificationType.IDE_UPDATE) {
        NotificationGroupManager.getInstance()
                .getNotificationGroup("Custom Notification Group")
                .createNotification(text, type)
                .notify(this)
    }

    private fun VirtualFile.toFileInfo() = FileInfo(
            name = name,
            path = path,
            extension = extension
    )

    override fun getActionUpdateThread(): ActionUpdateThread {
        return super.getActionUpdateThread()
    }

    override fun update(e: AnActionEvent) {
        super.update(e)
    }
}