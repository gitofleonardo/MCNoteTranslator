package cn.huangchengxi.notetranslator

import android.graphics.Color
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.fastjson.JSONObject

class NoteAdapter(private val notes:ArrayList<Clap>):RecyclerView.Adapter<NoteHolder> (){
    private val mapper=JSONObject.parseObject(mapperDict)
    private var containerListener:((Int)->Unit)?=null
    private val colors= arrayOf(
        Color.rgb(255,152,0),
        Color.rgb(33,150,243),
        Color.rgb(76,175,80),
        Color.rgb(244,67,54)
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.view_note_item,parent,false)
        return NoteHolder(view)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val note=notes[position]
        val cnt=if (note.clapName=="Space") "Space" else mapper.getString(note.clapName)
        holder.note.text=SpannableStringBuilder(note.clapName)
        if (cnt!=null){
            holder.count.text=SpannableStringBuilder(cnt)
        }else{
            holder.count.text=SpannableStringBuilder("OutOfRange")
        }
        holder.container.setOnClickListener {
            containerListener?.invoke(position)
        }
        holder.clap.text=SpannableStringBuilder(note.clapId.toString())
        holder.container.setBackgroundColor(colors[note.clapId%4])
    }
    fun setContainerListener(listener:((Int)->Unit)?){
        this.containerListener=listener
    }
    fun onItemDismiss(position:Int){
        notes.removeAt(position)
        notifyItemRemoved(position)
    }
}