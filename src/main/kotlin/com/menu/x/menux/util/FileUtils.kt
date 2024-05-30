package com.menu.x.menux.util

import com.intellij.ide.projectView.ProjectView
import com.intellij.ide.projectView.impl.nodes.AbstractPsiBasedNode
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.menu.x.menux.FileInfo
import com.menu.x.menux.util.FileUtils.toFileInfo

object FileUtils {

    @Throws(IllegalArgumentException::class)
    fun checkFileSize(files: List<FileInfo>): FileInfo  {
        when (files.size) {
            0 -> throw IllegalArgumentException("Select A Yaml File")
            1 -> return files.first()
            else -> throw IllegalArgumentException("Select Only 1 Yaml File")
        }
    }

    fun VirtualFile.toFileInfo() = FileInfo(
            name = name,
            path = path,
            extension = extension
    )
}

fun Project.filterSelectedFiles(ext: String): List<FileInfo> {
    return ProjectView
            .getInstance(this)
            .currentProjectViewPane
            .selectedUserObjects
            .filterIsInstance(AbstractPsiBasedNode::class.java)
            .filter { it.virtualFile?.extension == ext }
            .mapNotNull { it.virtualFile?.toFileInfo() }
}