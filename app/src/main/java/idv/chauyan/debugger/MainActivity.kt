package idv.chauyan.debugger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var receiver: Debugger? = null
    private var debugTextView: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        debugTextView = debuggerMessage

        receiver = Debugger(this) { intent ->
            intent?.let {
                println(it.getStringExtra("testMsg"))
                val message = it.getStringExtra("testMsg")
                debugTextView?.text = message
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        receiver?.unRegisterSelf()
    }
}
