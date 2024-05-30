package com.menu.x.menux.config

import com.intellij.openapi.options.Configurable
import com.intellij.ui.dsl.builder.panel
import javax.swing.*


class PanelConfigurable : Configurable {
    private var debugAppIdTextField: JTextField? = null
    private var prodAppIdTextField: JTextField? = null
    override fun createComponent(): JComponent {
        return panel {
            row("APPID Config"){}
            row("Debug:") {
                textField().apply {
                    debugAppIdTextField = this.component
                }
                label("* Debug package name")
            }

            row("Release:") {
                textField().apply {
                    prodAppIdTextField = this.component
                }
                label("* Release package name")
            }
        }
    }

    override fun isModified(): Boolean {
        val savedDebugAppId = PanelConfigState.instance.debugAppId
        val currentDebugAppId = debugAppIdTextField?.text
        val isDebugModified = savedDebugAppId != currentDebugAppId

        val savedProdAppId = PanelConfigState.instance.prodAppId
        val currentProdAppId = prodAppIdTextField?.text
        val isProdModified = savedProdAppId != currentProdAppId
        return isDebugModified || isProdModified
    }

    override fun apply() {
        PanelConfigState.instance.debugAppId = debugAppIdTextField?.text.orEmpty()
        PanelConfigState.instance.prodAppId = prodAppIdTextField?.text.orEmpty()
    }

    override fun reset() {
        debugAppIdTextField?.text = PanelConfigState.instance.debugAppId
        prodAppIdTextField?.text = PanelConfigState.instance.prodAppId
    }

    override fun getDisplayName(): String = "Config Panel MenuX"

    override fun disposeUIResources() {
        debugAppIdTextField = null
        prodAppIdTextField = null
    }
}
