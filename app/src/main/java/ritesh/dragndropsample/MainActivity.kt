package ritesh.dragndropsample

import android.content.ClipData
import android.content.ClipData.Item
import android.content.ClipDescription
import android.os.Bundle
import android.view.DragEvent
import android.view.View
import android.view.View.OnDragListener
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var nameList: TextView
    lateinit var clipData: ClipData
    private val mimeType = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameList = findViewById(R.id.nameList)
        findViewById<LinearLayout>(R.id.topLayout).setOnDragListener(dragListener)
        findViewById<LinearLayout>(R.id.bottomLayout).setOnDragListener(dragListener)


        findViewById<ImageView>(R.id.dragView1).setOnLongClickListener {
            val clipText = "Red"
            val item = Item(clipText)

            if (::clipData.isInitialized) {
                clipData.addItem(item)
            } else {
                clipData = ClipData(clipText, mimeType, item)
            }
            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(clipData, dragShadowBuilder, it, 0)
            true
        }

        findViewById<ImageView>(R.id.dragView2).setOnLongClickListener {
            val clipText = "Green"
            val item = Item(clipText)
            if (::clipData.isInitialized) {
                clipData.addItem(item)
            } else {
                clipData = ClipData(clipText, mimeType, item)
            }
            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(clipData, dragShadowBuilder, it, 0)
            true
        }
        findViewById<ImageView>(R.id.dragView3).setOnLongClickListener {
            val clipText = "Blue"
            val item = Item(clipText)
            if (::clipData.isInitialized) {
                clipData.addItem(item)
            } else {
                clipData = ClipData(clipText, mimeType, item)
            }
            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(clipData, dragShadowBuilder, it, 0)
            true

        }
        findViewById<ImageView>(R.id.dragView4).setOnLongClickListener {
            val clipText = "Purple"
            val item = Item(clipText)
            if (::clipData.isInitialized) {
                clipData.addItem(item)
            } else {
                clipData = ClipData(clipText, mimeType, item)
            }

            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(clipData, dragShadowBuilder, it, 0)

            true
        }
    }

    private val dragListener = OnDragListener { view, event ->
        when (event.action) {
            DragEvent.ACTION_DRAG_STARTED -> {
                event.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)
            }
            DragEvent.ACTION_DRAG_ENTERED,
            DragEvent.ACTION_DRAG_EXITED,
            DragEvent.ACTION_DRAG_ENDED -> {
                view.invalidate()
                true
            }
            DragEvent.ACTION_DROP -> {
                val files = java.lang.StringBuilder()
                for (index in 0 until event.clipData.itemCount) {
                    val item = event.clipData.getItemAt(index)
                    files.append("\n").append(item.text)
                }
                nameList.text = files.toString()

                // Toast.makeText(this, dragData, Toast.LENGTH_SHORT).show()
                true
            }
            DragEvent.ACTION_DRAG_LOCATION -> true
            else -> {
                false
            }
        }
    }
}