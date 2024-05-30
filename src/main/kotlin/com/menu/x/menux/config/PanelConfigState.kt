package com.menu.x.menux.config

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage

@State(name = "PanelConfigState", storages = [(Storage("PanelConfigPlugin.xml"))])
class PanelConfigState : PersistentStateComponent<PanelConfigState> {
    var debugAppId: String = ""
    var prodAppId: String = ""

    override fun getState(): PanelConfigState? = this

    override fun loadState(state: PanelConfigState) {
        this.debugAppId = state.debugAppId
        this.prodAppId = state.prodAppId
    }

    companion object {
        val instance: PanelConfigState
            get() = com.intellij.openapi.application.ApplicationManager.getApplication().getService(PanelConfigState::class.java)
    }
}
