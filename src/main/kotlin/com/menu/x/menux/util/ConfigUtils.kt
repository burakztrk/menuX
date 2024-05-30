package com.menu.x.menux.util

import com.menu.x.menux.config.PanelConfigState

object ConfigUtils {
    fun checkConfigs() {
        if (PanelConfigState.instance.prodAppId.isEmpty() ||
                PanelConfigState.instance.debugAppId.isEmpty()) {
            throw IllegalArgumentException("Please Fill Config APPID")
        }
    }
}