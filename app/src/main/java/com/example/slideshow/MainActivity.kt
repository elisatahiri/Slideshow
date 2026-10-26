
package com.example.slideshow

import android.app.Activity
import android.os.Bundle
import android.view.Gravity
import android.widget.*

class MainActivity : Activity() {

    private var current = 0

    private val pictures = intArrayOf(
        R.drawable.mountains,
        R.drawable.forest,
        R.drawable.ocean,
        R.drawable.flowers,
        R.drawable.waterfall
    )

    private val captions = arrayOf(
        "Picture 1: Mountains",
        "Picture 2: Forest",
        "Picture 3: Ocean",
        "Picture 4: Flowers",
        "Picture 5: Waterfall"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.gravity = Gravity.CENTER
        layout.setPadding(12, 12, 12, 12)

        val title = TextView(this)
        title.text = "My Slideshow"
        title.textSize = 24f
        title.gravity = Gravity.CENTER

        val image = ImageView(this)
        image.scaleType = ImageView.ScaleType.FIT_CENTER

        val caption = TextView(this)
        caption.textSize = 18f
        caption.gravity = Gravity.CENTER

        val buttons = LinearLayout(this)
        buttons.gravity = Gravity.CENTER

        val back = Button(this)
        back.text = "BACK"

        val next = Button(this)
        next.text = "NEXT"

        buttons.addView(back)
        buttons.addView(next)

        val input = EditText(this)
        input.hint = "Enter picture number 1-5"
        input.inputType = 2

        val go = Button(this)
        go.text = "GO TO PICTURE"

        layout.addView(title)
        layout.addView(image, LinearLayout.LayoutParams(-1, 180))
        layout.addView(caption)
        layout.addView(buttons)
        layout.addView(input)
        layout.addView(go)

        fun showPicture() {
            image.setImageResource(pictures[current])
            caption.text = captions[current]
        }

        back.setOnClickListener {
            current = (current - 1 + pictures.size) % pictures.size
            showPicture()
        }

        next.setOnClickListener {
            current = (current + 1) % pictures.size
            showPicture()
        }

        go.setOnClickListener {
            val number = input.text.toString().toIntOrNull()

            if (number != null && number in 1..pictures.size) {
                current = number - 1
                showPicture()
            } else {
                Toast.makeText(
                    this,
                    "Enter a number from 1 to ${pictures.size}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        showPicture()
        setContentView(layout)
    }
}
