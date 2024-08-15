package me.greenworld.vpdmoney.common


import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.Date

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("visibleGone")
    fun setVisibility(view: View, value: Boolean?) {
        value?.let {
            if (value)
                view.visibility = View.VISIBLE
            else
                view.visibility = View.GONE
        }
    }

    @JvmStatic
    @BindingAdapter("visibleGone")
    fun setVisibility(view: View, value: Event<Boolean>?) {
        value?.let {
            if (value.peekContent())
                view.visibility = View.VISIBLE
            else
                view.visibility = View.GONE
        }
    }

    @JvmStatic
    @BindingAdapter("visibleInvisible")
    fun setVisible(view: View, value: Boolean?) {
        value?.let {
            if (value)
                view.visibility = View.VISIBLE
            else
                view.visibility = View.INVISIBLE
        }
    }


    @JvmStatic
    @BindingAdapter("error")
    fun setError(view: View, errorMessage: Event<Int>?) {
        if (errorMessage != null && !errorMessage.hasBeenHandled) {
            val error = view.context.getString(errorMessage.peekContent())

            when (view) {
                is TextInputLayout -> view.error = error
                is TextInputEditText -> view.error = error
                else -> Snackbar.make(view, error, Snackbar.LENGTH_LONG).show()
            }
        } else {
            when (view) {
                // TextInputLayout error doesn't clear automatically unlike TextInputEditText
                is TextInputLayout -> view.error = null
            }
        }
    }


    @JvmStatic
    @BindingAdapter("transactionLimitError")
    fun setTransactionLimit(textInputLayout: TextInputLayout, errorMessage: Event<String>?) {
        if (errorMessage != null && !errorMessage.hasBeenHandled) {
            textInputLayout.error = errorMessage.peekContent()
        } else {
            textInputLayout.error = null
        }
    }

    @JvmStatic
    @BindingAdapter("drawable")
    fun drawableRes(imageView: ImageView, res: Drawable?) {
        res?.let {
            imageView.setImageDrawable(it)
        }
    }

    @JvmStatic
    @BindingAdapter("setBitmap")
    fun setBitmapResource(imageView: ImageView, bitmap: Bitmap?) {
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap)
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        }
    }

}
