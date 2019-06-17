package idv.chauyan.debugger

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter

class Debugger(
    private val context: Context,
    intentAction: String = "fastReload",
    private val actionHandler: (Intent?) -> Unit
): BroadcastReceiver() {

    init {
        context.registerReceiver(this, IntentFilter(intentAction))
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        actionHandler(intent)
    }

    fun unRegisterSelf() {
        context.unregisterReceiver(this)
    }
}