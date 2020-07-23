package cn.huangchengxi.notetranslator

import android.view.View

class NoteViewHolder(private val noteViews:List<NoteView>) {
    private var onClickListener:((View,Int)->Unit)?=null

    fun setOnClickListener(listener:((View,Int)->Unit)?){
        onClickListener=listener

        for (noteView in noteViews){
            noteView.setOnClickListener {
                onClickListener?.invoke(it,noteView.btn)
            }
        }
    }
}