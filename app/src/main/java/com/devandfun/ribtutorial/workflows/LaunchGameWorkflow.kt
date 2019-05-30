package com.devandfun.ribtutorial.workflows

import android.content.Intent
import com.devandfun.ribtutorial.root.RootActionableItem
import com.devandfun.ribtutorial.root.RootWorkflow
import com.uber.rib.workflow.core.Step
import com.devandfun.ribtutorial.root.RootWorkflowModel


class LaunchGameWorkflow(deepLinkIntent: Intent) :
    RootWorkflow<Step.NoValue, LaunchGameWorkflow.LaunchGameDeepLinkModel>(deepLinkIntent) {


    override fun getSteps(
        rootActionableItem: RootActionableItem,
        deepLinkModel: LaunchGameDeepLinkModel
    ): Step<Step.NoValue, *> {
        return rootActionableItem.login(deepLinkModel.playerOneName, deepLinkModel.playerTwoName)
    }

    override fun parseDeepLinkIntent(deepLinkIntent: Intent): LaunchGameDeepLinkModel {
        val uri = deepLinkIntent.data
        val playerOne = uri?.getQueryParameter("playerOne")
        val playerTwo = uri?.getQueryParameter("playerTwo")
        return LaunchGameDeepLinkModel(playerOne, playerTwo)
    }

    data class LaunchGameDeepLinkModel(val playerOneName: String?, val playerTwoName: String?) : RootWorkflowModel
}