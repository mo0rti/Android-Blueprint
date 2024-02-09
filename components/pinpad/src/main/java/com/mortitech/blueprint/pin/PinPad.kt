package com.mortitech.blueprint.pin

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.mortitech.blueprint.pin.databinding.PinPadBinding

class PinPad : LinearLayout {

    private companion object {
        const val DEFAULT_PIN_LENGTH = 6
    }

    private lateinit var binding: PinPadBinding
    private var mPinLength: Int = DEFAULT_PIN_LENGTH
    private var mEventListener: PinPadEventListener? = null

    private var mPinCode: String = ""
    val pinCode: String
        get() = mPinCode

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        initView(context, attrs, defStyle)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView(context, attrs, 0)
    }

    private fun initView(context: Context, attrs: AttributeSet, defStyle: Int) {
        binding = PinPadBinding.inflate(
            LayoutInflater.from(context), this, true
        )

        context
            .theme
            .obtainStyledAttributes(attrs, R.styleable.PinPad, defStyle, 0)
            .apply {
                try {
                    if (hasValue(R.styleable.PinPad_pinLength)) {
                        mPinLength = getInt(R.styleable.PinPad_pinLength, DEFAULT_PIN_LENGTH)
                    }

                    if (hasValue(R.styleable.PinPad_hasBiometric)
                        && (getBoolean(R.styleable.PinPad_hasBiometric, true))) {
                        binding.vAbnTechBtnEmptySpace.visibility = GONE
                        binding.btnAbnTechPinBio.visibility = VISIBLE
                    } else {
                        binding.vAbnTechBtnEmptySpace.visibility = VISIBLE
                        binding.btnAbnTechPinBio.visibility = GONE
                    }

                    if (hasValue(R.styleable.PinPad_android_enabled)) {
                        isEnabled = getBoolean(R.styleable.PinPad_hasBiometric, true)
                    }
                } finally {
                    recycle()
                }
            }

        binding.btnAbnTechPin0.setOnClickListener { addToPinCode(0) }
        binding.btnAbnTechPin1.setOnClickListener { addToPinCode(1) }
        binding.btnAbnTechPin2.setOnClickListener { addToPinCode(2) }
        binding.btnAbnTechPin3.setOnClickListener { addToPinCode(3) }
        binding.btnAbnTechPin4.setOnClickListener { addToPinCode(4) }
        binding.btnAbnTechPin5.setOnClickListener { addToPinCode(5) }
        binding.btnAbnTechPin6.setOnClickListener { addToPinCode(6) }
        binding.btnAbnTechPin7.setOnClickListener { addToPinCode(7) }
        binding.btnAbnTechPin8.setOnClickListener { addToPinCode(8) }
        binding.btnAbnTechPin9.setOnClickListener { addToPinCode(9) }
        binding.btnAbnTechPinDel.setOnClickListener { removeLastDigitFromPinCode() }
    }

    private fun addToPinCode(number: Int) {
        if (mPinCode.length < mPinLength) {
            mPinCode += number.toString()
            mEventListener?.onChange(mPinCode)

            if (mPinCode.length == mPinLength) {
                mEventListener?.onFinish(mPinCode)
            }
        }
    }

    private fun removeLastDigitFromPinCode() {
        if (mPinCode.isNotEmpty()) {
            mPinCode = mPinCode.dropLast(1)
            mEventListener?.onChange(mPinCode)
        }
    }

    fun setOnEventListener(listener: PinPadEventListener) {
        mEventListener = listener
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        binding.btnAbnTechPin0.isEnabled = enabled
        binding.btnAbnTechPin1.isEnabled = enabled
        binding.btnAbnTechPin2.isEnabled = enabled
        binding.btnAbnTechPin3.isEnabled = enabled
        binding.btnAbnTechPin4.isEnabled = enabled
        binding.btnAbnTechPin5.isEnabled = enabled
        binding.btnAbnTechPin6.isEnabled = enabled
        binding.btnAbnTechPin7.isEnabled = enabled
        binding.btnAbnTechPin8.isEnabled = enabled
        binding.btnAbnTechPin9.isEnabled = enabled
        binding.btnAbnTechPinDel.isEnabled = enabled
        binding.btnAbnTechPinBio.isEnabled = enabled
    }

    /**
     * Call this method when you want clear the pin code to sync the PinView and PinPad
     */
    fun resetPinCode() {
        mPinCode = ""
    }
}

interface PinPadEventListener {
    /**
     * Listener method invoked when the PIN changed (either a new digit added or an old one removed)
     *
     * @param pinCode - new pin
     */
    fun onChange(pinCode: String)
    /**
     * Listener method invoked when the PIN is complete
     *
     * @param pinCode - new pin
     */
    fun onFinish(pinCode: String)
}
