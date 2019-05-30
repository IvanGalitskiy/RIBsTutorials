package com.devandfun.ribtutorial.workflows

import android.content.Intent
import com.devandfun.ribtutorial.root.RootWorkflow

class WorkflowFactory {
    fun getWorkflow(intent: Intent): LaunchGameWorkflow {
        return LaunchGameWorkflow(intent)
    }
}