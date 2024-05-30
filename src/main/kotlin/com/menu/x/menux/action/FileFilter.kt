
package com.menu.x.menux.action

import com.intellij.openapi.actionSystem.AnActionEvent
import com.menu.x.menux.util.FileUtils
import com.menu.x.menux.util.filterSelectedFiles

interface FileFilter {
    fun isCorrectFileType(e: AnActionEvent) : Boolean
}

class FileFilterImpl(private val extension: String): FileFilter {
    override fun isCorrectFileType(e: AnActionEvent): Boolean {
        val project = e.project ?: return false
        val fileInfos = project.filterSelectedFiles(extension)

        val file = runCatching { FileUtils.checkFileSize(fileInfos) }.getOrNull()
        return file != null
    }
}