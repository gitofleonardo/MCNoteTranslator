package cn.huangchengxi.notetranslator

import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.fastjson.JSONObject

class NoteAdapter(private val notes:ArrayList<String>):RecyclerView.Adapter<NoteHolder> (){
    private val mapper=JSONObject.parseObject(mapperDict)
    private var containerListener:((Int)->Unit)?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.view_note_item,parent,false)
        return NoteHolder(view)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val note=notes[position]
        val cnt=mapper.getString(note)
        holder.note.text=SpannableStringBuilder(note)
        if (cnt!=null){
            holder.count.text=SpannableStringBuilder(cnt)
        }else{
            holder.count.text=SpannableStringBuilder("OutOfRange")
        }
        holder.container.setOnClickListener {
            containerListener?.invoke(position)
        }
    }
    fun setContainerListener(listener:((Int)->Unit)?){
        this.containerListener=listener
    }
}