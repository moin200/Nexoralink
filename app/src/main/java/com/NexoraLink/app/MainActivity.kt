package com.nexoralink.app

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import java.security.SecureRandom

class MainActivity : Activity() {
    private val random = SecureRandom()
    private lateinit var pinText: TextView
    private lateinit var statusText: TextView
    private lateinit var consentButton: Button
    private var consentGiven = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navy = Color.rgb(14, 25, 48)
        val white = Color.WHITE
        val page = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_HORIZONTAL
            setPadding(28, 42, 28, 28)
            setBackgroundColor(navy)
        }

        fun label(value: String, size: Float, color: Int, bold: Boolean = false) =
            TextView(this).apply {
                text = value
                textSize = size
                setTextColor(color)
                gravity = Gravity.CENTER
                if (bold) setTypeface(null, android.graphics.Typeface.BOLD)
                setPadding(8, 12, 8, 12)
            }

        fun addButton(title: String, action: () -> Unit): Button {
            val button = Button(this).apply {
                text = title
                setOnClickListener { action() }
            }
            page.addView(button, LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { topMargin = 8; bottomMargin = 8 })
            return button
        }

        page.addView(label("NEXORALINK", 28f, white, true))
        page.addView(label("REMOTE SUPPORT • CONNECT WITH CONSENT", 12f, Color.LTGRAY))

        pinText = label("------", 34f, Color.rgb(130, 190, 255), true)
        page.addView(pinText)

        addButton("Generate demo PIN") {
            val pin = (100000 + random.nextInt(900000)).toString()
            pinText.text = pin
            statusText.text = "Demo PIN created. No device is connected."
            consentGiven = false
            consentButton.text = "Accept demo request"
        }

        val pinInput = EditText(this).apply {
            hint = "Enter partner's 6-digit PIN"
            setHintTextColor(Color.GRAY)
            setTextColor(white)
            setSingleLine(true)
            inputType = android.text.InputType.TYPE_CLASS_NUMBER
        }
        page.addView(pinInput, LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply { topMargin = 8 })

        addButton("Prepare connection request") {
            val value = pinInput.text.toString()
            if (value.length != 6 || !value.all(Char::isDigit)) {
                Toast.makeText(this, "Enter a valid 6-digit PIN.", Toast.LENGTH_SHORT).show()
            } else {
                statusText.text = "Demo request prepared for PIN $value. No network connection is made."
                consentGiven = false
                consentButton.text = "Accept demo request"
            }
        }

        consentButton = addButton("Accept demo request") {
            consentGiven = !consentGiven
            consentButton.text = if (consentGiven) "Revoke demo consent" else "Accept demo request"
            statusText.text = if (consentGiven)
                "Demo consent recorded locally. Remote access is not implemented."
            else
                "Demo consent revoked. No remote access is active."
        }

        statusText = label(
            "Starter prototype only. It does not stream screens, control another phone, access camera/mic/notifications, or transfer files.",
            14f, Color.rgb(220, 228, 240)
        )
        page.addView(statusText)
        page.addView(label("The other device owner must approve every sensitive session.", 12f, Color.LTGRAY))
        setContentView(page)
    }
}
