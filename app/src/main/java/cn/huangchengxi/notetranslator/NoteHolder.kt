package cn.huangchengxi.notetranslator

import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteHolder(view:View):RecyclerView.ViewHolder(view) {
    val note by lazy { view.findViewById<TextView>(R.id.note) }
    val count by lazy { view.findViewById<TextView>(R.id.note_count) }
    val container by lazy { view.findViewById<FrameLayout>(R.id.item_container) }
    val clap by lazy { view.findViewById<TextView>(R.id.note_clap) }
}