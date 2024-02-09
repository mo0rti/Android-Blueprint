package com.mortitech.blueprint.pin

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.MotionEvent
import androidx.constraintlayout.widget.ConstraintLayout
import com.mortitech.blueprint.pin.databinding.PinPadButtonBinding

class PinPadButton : ConstraintLayout {

    private lateinit var binding: PinPadButtonBinding

    private var mOnClickListener: OnClickListener? = null

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        initView(context, attrs, defStyle)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView(context, attrs, 0)
    }

    private fun initView(context: Context, attrs: AttributeSet, defStyle: Int) {
        binding = PinPadButtonBinding.inflate(LayoutInflater.from(context), this, true)

        context
            .theme
            .obtainStyledAttributes(attrs, R.styleable.PinPadButton, defStyle, 0)
            .apply {
                try {
                    if (hasValue(R.styleable.PinPadButton_android_drawable)) {
                        binding.icAbnTechPinPadButton.setImageDrawable(getDrawable(R.styleable.PinPadButton_android_drawable))
                    }

                    if (hasValue(R.styleable.PinPadButton_android_text)) {
                        binding.tvAbnTechPinPadButton.text = getText(R.styleable.PinPadButton_android_text)
                    } else {
                        binding.tvAbnTechPinPadButton.visibility = GONE
                    }

                    if (hasValue(R.styleable.PinPad_android_enabled)) {
                        isEnabled = getBoolean(R.styleable.PinPad_android_enabled, true)
                    }
                } finally {
                    recycle()
                }
            }
    }

    override fun setEnabled(enabled: Boolean) {
        binding.root.apply {
            isEnabled = enabled
            isClickable = enabled
        }
    }

    override fun setOnClickListener(listener: OnClickListener?) {
        mOnClickListener = listener
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_UP) {
            mOnClickListener?.onClick(this)
        }
        return super.dispatchTouchEvent(event)
    }

    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        if (event.action == KeyEvent.ACTION_UP && (event.keyCode == KeyEvent.KEYCODE_DPAD_CENTER || event.keyCode == KeyEvent.KEYCODE_ENTER)) {
            mOnClickListener?.onClick(this)
        }
        return super.dispatchKeyEvent(event)
    }
}
