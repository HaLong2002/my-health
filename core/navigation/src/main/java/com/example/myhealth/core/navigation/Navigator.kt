package com.example.myhealth.core.navigation

import androidx.navigation3.runtime.NavKey

class Navigator(
    val state: NavigationState
) {
    fun navigate(key: NavKey) {
        when(key) {
            state.currentTopLevelKey -> clearSubStack()
            in state.topLevelKeys -> goToTopLevel(key)
            else -> goToKey(key)
        }
    }

    fun goBack() {
        when(state.currentKey) {
            state.startKey -> error("You can not go back from the start route")
            state.currentTopLevelKey -> state.topLevelStack.removeLastOrNull()
            else -> state.currentSubStack.removeLastOrNull()
        }
    }

    /**
     * Go to a non-top level key.
     */
    fun goToKey(key: NavKey) {
        state.currentSubStack.apply {
            remove(key)
            add(key)
        }
    }

    fun goToTopLevel(key: NavKey) {
        state.topLevelStack.apply {
            if (key == state.startKey) {
                clear()
            } else {
                remove(key)
            }
            add(key)
        }
    }

    /**
    * Clearing all but the root key in the current substack.
    */
    fun clearSubStack() {
        state.currentSubStack.run {
            if(size > 1) subList(1, size).clear()
        }
    }
}