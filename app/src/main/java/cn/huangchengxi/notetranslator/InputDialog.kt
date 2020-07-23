package cn.huangchengxi.notetranslator

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.widget.Button
import android.widget.EditText

class InputDialog(context: Context): Dialog(context,R.style.Theme_AppCompat_Light_Dialog_Alert){
    private val textView by lazy { findViewById<EditText>(R.id.duration) }
    private val confirmBtn by lazy { findViewById<Button>(R.id.confirm_btn) }
    private val cancelbtn by lazy { findViewById<Button>(R.id.cancel_btn) }

    private var onFinishedInputListener:((String)->Unit)?=null
    private var defaultVal="0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_input)
        setTitle("设置单节时长")
        confirmBtn.setOnClickListener {
            val txt=textView.text.toString()
            if (txt.matches(Regex("^[0-9]+$"))){
                dismiss(true)
            }else{
                textView.error = SpannableStringBuilder("Number format error!")
            }
        }
        cancelbtn.setOnClickListener {
            dismiss(false)
        }
        textView.text=SpannableStringBuilder(defaultVal)
    }
    fun setInputText(txt:String){
        defaultVal=txt
    }
    fun setOnFinishedInputListener(listener:(String)->Unit){
        onFinishedInputListener=listener
    }
    fun dismiss(success:Boolean){
        if (success){
            onFinishedInputListener?.invoke(textView.text.toString())
            dismiss()
        }else{
            dismiss()
        }
    }
}