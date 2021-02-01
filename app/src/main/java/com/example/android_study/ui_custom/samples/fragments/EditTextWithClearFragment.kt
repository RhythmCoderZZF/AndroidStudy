package com.example.android_study.ui_custom.samples.fragments

import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_UP
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.Fragment
import com.example.android_study.R

class EditTextWithClearFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edit_text_with_clear, container, false)
    }

}

class EditTextWithClear @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = android.R.attr.editTextStyle
) : AppCompatEditText(context, attrs, defStyleAttr) {

    private var iconDrawable: Drawable? = null

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.EditTextWithClear, defStyleAttr, 0).apply {
            try {
                iconDrawable = getDrawable(R.styleable.EditTextWithClear_clearDrawable)
            }finally {
                recycle()
            }
        }

    }

    override fun onTextChanged(text: CharSequence?, start: Int, lengthBefore: Int, lengthAfter: Int) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
        toggleClear()
    }

    override fun onFocusChanged(focused: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect)
        toggleClear()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let { ev ->
            iconDrawable?.let { drawable ->
                if (ev.action == ACTION_UP
                        && ev.x > width - drawable.intrinsicWidth - paddingEnd - 5
                        && ev.x < width - paddingEnd + 5
                        && ev.y > (height - drawable.intrinsicHeight) / 2f - 5
                        && ev.y < (height + drawable.intrinsicHeight) / 2f + 5
                ) {
                    performClick()//注意ACTION_UP return前要调用performClick()响应点击事件
                    text?.clear()
                    toggleClear()
                }
            }
        }
        return super.onTouchEvent(event)
    }

    private fun toggleClear() {
        var show = if (!isFocused || text.isNullOrEmpty()) null else iconDrawable
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, show, null)//设置drawable的位置及bounds
    }
}