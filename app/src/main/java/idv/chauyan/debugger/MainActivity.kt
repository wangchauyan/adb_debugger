package idv.chauyan.debugger

import android.graphics.Color
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

                // update text size
                it.getStringExtra("textSize")?.let { size ->
                    debugTextView?.textSize = size.toFloat()
                }

                // update text color
                it.getStringExtra("textColor")?.let { color ->
                    val (r, g, b) = color.split(",").map(String::toInt)
                    val realColor = Color.argb(255, r, g, b)
                    debugTextView?.setTextColor(realColor)
                }

                // update message
                it.getStringExtra("testMsg")?.let { message ->
                    debugTextView?.text = message
                }

                // update rotation
                it.getStringExtra("rotation")?.let { rotation ->
                    debugTextView?.rotation = rotation.toFloat()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        receiver?.unRegisterSelf()
    }
}
