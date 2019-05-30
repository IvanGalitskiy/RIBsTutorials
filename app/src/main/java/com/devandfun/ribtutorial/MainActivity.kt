package com.devandfun.ribtutorial

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import com.devandfun.ribtutorial.root.RootBuilder
import com.devandfun.ribtutorial.root.RootInteractor
import com.devandfun.ribtutorial.workflows.WorkflowFactory
import com.uber.autodispose.SingleScoper
import com.uber.rib.core.Optional
import com.uber.rib.core.RibActivity
import com.uber.rib.core.ViewRouter
import io.reactivex.functions.Consumer


class MainActivity : RibActivity() {
    lateinit var rootInteractor: RootInteractor
    override fun createRouter(parentViewGroup: ViewGroup): ViewRouter<*, *, *> {
        val rootBuilder = RootBuilder(object : RootBuilder.ParentComponent {
        })
        val router = rootBuilder.build(parentViewGroup)
        rootInteractor = router.interactor
        return router
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (intent != null) {
            handleDeepLink(intent)
        }
    }

    private fun handleDeepLink(intent: Intent) {
        val rootWorkflow = WorkflowFactory().getWorkflow(intent)
        if (rootWorkflow.deepLinkModel.playerOneName != null &&
            rootWorkflow.deepLinkModel.playerTwoName != null
        ) {
            rootWorkflow
                .createSingle(rootInteractor)
                .to(SingleScoper<Optional<*>>(this))
                .subscribe(
                    Consumer<Optional<*>> {
                    })
        }
    }
}
