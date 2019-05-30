package com.devandfun.ribtutorial.root

import com.devandfun.ribtutorial.root.logged_in.LoggedInActionableItem
import com.uber.rib.workflow.core.ActionableItem
import com.uber.rib.workflow.core.Step

interface RootActionableItem :ActionableItem{
    fun login(firstPlayer:String?, secondPlayer:String?): Step<Step.NoValue, LoggedInActionableItem>
}