package cn.huangchengxi.notetranslator

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.Button
import androidx.annotation.IdRes
import androidx.annotation.RawRes

class NoteView(@IdRes val btn:Int, @RawRes val note:Int,context: Context) {
    private val view=(context as Activity).findViewById<Button>(btn)
    val text=view.text.toString()
    private var onClickListener:((View)->Unit)?=null

    fun setOnClickListener(listener:((View)->Unit)?){
        view.setOnClickListener {
            listener?.invoke(it)
        }
    }
}