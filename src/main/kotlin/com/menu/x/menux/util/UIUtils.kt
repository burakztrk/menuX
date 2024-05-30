package com.menu.x.menux.util

import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project

fun Project.notify(text: String, type: NotificationType = NotificationType.IDE_UPDATE) {
    NotificationGroupManager.getInstance()
            .getNotificationGroup("Custom Notification Group")
            .createNotification(text, type)
            .notify(this)
}