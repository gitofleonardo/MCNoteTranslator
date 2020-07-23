package cn.huangchengxi.notetranslator

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RawRes
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.Exception
import java.util.concurrent.ConcurrentLinkedQueue
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private var player:MediaPlayer?=null
    private val players=ConcurrentLinkedQueue<MediaPlayer>()
    private val notes=ArrayList<Clap>()
    private val adapter=NoteAdapter(notes)
    private val layoutManager by lazy { LinearLayoutManager(this) }
    private val noteList by lazy { findViewById<RecyclerView>(R.id.notes_recycler_view) }
    private val noteViews=ArrayList<NoteView>()
    private var playThread:Thread?=null
    private val toolbar by lazy { findViewById<Toolbar>(R.id.toolbar) }
    private val clapText by lazy { findViewById<TextView>(R.id.clap) }
    private val nextClapBtn by lazy { findViewById<Button>(R.id.next_clap) }
    private val insertSpace by lazy { findViewById<Button>(R.id.space_key) }
    private var currentClap = P14
    private var currentNoteClap=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar()
        setupList()
        init()
        initToolWidget()
    }
    private fun initToolWidget(){
        nextClapBtn.setOnClickListener {
            currentNoteClap++
        }
        insertSpace.setOnClickListener {
            notes.add(Clap(currentNoteClap,"Space"))
            adapter.notifyItemInserted(notes.size-1)
            noteList.smoothScrollToPosition(notes.size-1)
        }
    }
    private fun setupToolbar(){
        setSupportActionBar(toolbar)
        supportActionBar?.title=SpannableStringBuilder("")
        clapText.text=SpannableStringBuilder("1/4拍")
    }
    @RawRes
    private fun findRawRes(note:String):Int{
        for (noteView in noteViews){
            if (noteView.text==note){
                return noteView.note
            }
        }
        return R.raw.cs
    }
    private fun setupList(){
        adapter.setContainerListener {
            val note=notes[it]
            val res=findRawRes(note.clapName)
            val player=MediaPlayer.create(this,res)
            player.setOnCompletionListener {    p->
                players.remove(p)
            }
            players.add(player)
            player.start()
        }
        noteList.adapter=adapter
        noteList.layoutManager=layoutManager
        val touchHelper=ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                val swipeFlag=ItemTouchHelper.LEFT.or(ItemTouchHelper.RIGHT)
                return makeMovementFlags(0,swipeFlag)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.onItemDismiss(viewHolder.adapterPosition)
                if (notes[notes.size-1].clapId<currentNoteClap){
                    currentNoteClap=notes[notes.size-1].clapId
                }
            }

        })
        touchHelper.attachToRecyclerView(noteList)
    }
    private fun init(){
        noteViews.add(NoteView(R.id.l1,R.raw.cs,this))
        noteViews.add(NoteView(R.id.l2,R.raw.ds,this))
        noteViews.add(NoteView(R.id.l3,R.raw.es,this))
        noteViews.add(NoteView(R.id.l4,R.raw.fs,this))
        noteViews.add(NoteView(R.id.l5,R.raw.gs,this))
        noteViews.add(NoteView(R.id.l6,R.raw.as_,this))
        noteViews.add(NoteView(R.id.l7,R.raw.bs,this))
        noteViews.add(NoteView(R.id.m1,R.raw.cm_,this))
        noteViews.add(NoteView(R.id.m2,R.raw.dm_,this))
        noteViews.add(NoteView(R.id.m3,R.raw.em_,this))
        noteViews.add(NoteView(R.id.m4,R.raw.fm_,this))
        noteViews.add(NoteView(R.id.m5,R.raw.gm_,this))
        noteViews.add(NoteView(R.id.m6,R.raw.am_,this))
        noteViews.add(NoteView(R.id.m7,R.raw.bm_,this))
        noteViews.add(NoteView(R.id.h1,R.raw.ch,this))
        noteViews.add(NoteView(R.id.h2,R.raw.dh,this))
        noteViews.add(NoteView(R.id.h3,R.raw.eh,this))
        noteViews.add(NoteView(R.id.h4,R.raw.fh,this))
        noteViews.add(NoteView(R.id.h5,R.raw.gh,this))
        noteViews.add(NoteView(R.id.h6,R.raw.ah,this))
        noteViews.add(NoteView(R.id.h7,R.raw.bh,this))

        noteViews.add(NoteView(R.id.upl1,R.raw.css,this))
        noteViews.add(NoteView(R.id.upl2,R.raw.dss,this))
        noteViews.add(NoteView(R.id.upl4,R.raw.fss,this))
        noteViews.add(NoteView(R.id.upl5,R.raw.gss,this))
        noteViews.add(NoteView(R.id.upl6,R.raw.ass,this))
        noteViews.add(NoteView(R.id.upm1,R.raw.csm_,this))
        noteViews.add(NoteView(R.id.upm2,R.raw.dsm_,this))
        noteViews.add(NoteView(R.id.upm4,R.raw.fsm_,this))
        noteViews.add(NoteView(R.id.upm5,R.raw.gsm_,this))
        noteViews.add(NoteView(R.id.upm6,R.raw.asm_,this))
        noteViews.add(NoteView(R.id.uph1,R.raw.csh,this))
        noteViews.add(NoteView(R.id.uph2,R.raw.dsh,this))
        noteViews.add(NoteView(R.id.uph4,R.raw.fsh,this))
        noteViews.add(NoteView(R.id.uph5,R.raw.gsh,this))
        noteViews.add(NoteView(R.id.uph6,R.raw.ash,this))

        /**
         * low notes
         */

        findViewById<Button>(R.id.l1).setOnClickListener {
            setupPlayers(it,R.raw.cs)
        }
        findViewById<Button>(R.id.l2).setOnClickListener {
            setupPlayers(it,R.raw.ds)
        }
        findViewById<Button>(R.id.l3).setOnClickListener {
            setupPlayers(it,R.raw.es)
        }
        findViewById<Button>(R.id.l4).setOnClickListener {
            setupPlayers(it,R.raw.fs)
        }
        findViewById<Button>(R.id.l5).setOnClickListener {
            setupPlayers(it,R.raw.gs)
        }
        findViewById<Button>(R.id.l6).setOnClickListener {
            setupPlayers(it,R.raw.as_)
        }
        findViewById<Button>(R.id.l7).setOnClickListener {
            setupPlayers(it,R.raw.bs)
        }
        findViewById<Button>(R.id.upl1).setOnClickListener {
            setupPlayers(it,R.raw.css)
        }
        findViewById<Button>(R.id.upl2).setOnClickListener {
            setupPlayers(it,R.raw.dss)
        }
        findViewById<Button>(R.id.upl4).setOnClickListener {
            setupPlayers(it,R.raw.fss)
        }
        findViewById<Button>(R.id.upl5).setOnClickListener {
            setupPlayers(it,R.raw.gss)
        }
        findViewById<Button>(R.id.upl6).setOnClickListener {
            setupPlayers(it,R.raw.ass)
        }
        /**
         * middle notes
         */
        findViewById<Button>(R.id.m1).setOnClickListener {
            setupPlayers(it,R.raw.cm_)
        }
        findViewById<Button>(R.id.m2).setOnClickListener {
            setupPlayers(it,R.raw.dm_)
        }
        findViewById<Button>(R.id.m3).setOnClickListener {
            setupPlayers(it,R.raw.em_)
        }
        findViewById<Button>(R.id.m4).setOnClickListener {
            setupPlayers(it,R.raw.fm_)
        }
        findViewById<Button>(R.id.m5).setOnClickListener {
            setupPlayers(it,R.raw.gm_)
        }
        findViewById<Button>(R.id.m6).setOnClickListener {
            setupPlayers(it,R.raw.am_)
        }
        findViewById<Button>(R.id.m7).setOnClickListener {
            setupPlayers(it,R.raw.bm_)
        }
        findViewById<Button>(R.id.upm1).setOnClickListener {
            setupPlayers(it,R.raw.csm_)
        }
        findViewById<Button>(R.id.upm2).setOnClickListener {
            setupPlayers(it,R.raw.dsm_)
        }
        findViewById<Button>(R.id.upm4).setOnClickListener {
            setupPlayers(it,R.raw.fsm_)
        }
        findViewById<Button>(R.id.upm5).setOnClickListener {
            setupPlayers(it,R.raw.gsm_)
        }
        findViewById<Button>(R.id.upm6).setOnClickListener {
            setupPlayers(it,R.raw.asm_)
        }
        /**
         * high notes
         */
        findViewById<Button>(R.id.h1).setOnClickListener {
            setupPlayers(it,R.raw.ch)
        }
        findViewById<Button>(R.id.h2).setOnClickListener {
            setupPlayers(it,R.raw.dh)
        }
        findViewById<Button>(R.id.h3).setOnClickListener {
            setupPlayers(it,R.raw.eh)
        }
        findViewById<Button>(R.id.h4).setOnClickListener {
            setupPlayers(it,R.raw.fh)
        }
        findViewById<Button>(R.id.h5).setOnClickListener {
            setupPlayers(it,R.raw.gh)
        }
        findViewById<Button>(R.id.h6).setOnClickListener {
            setupPlayers(it,R.raw.ah)
        }
        findViewById<Button>(R.id.h7).setOnClickListener {
            setupPlayers(it,R.raw.bh)
        }
        findViewById<Button>(R.id.uph1).setOnClickListener {
            setupPlayers(it,R.raw.csh)
        }
        findViewById<Button>(R.id.uph2).setOnClickListener {
            setupPlayers(it,R.raw.dsh)
        }
        findViewById<Button>(R.id.uph4).setOnClickListener {
            setupPlayers(it,R.raw.fsh)
        }
        findViewById<Button>(R.id.uph5).setOnClickListener {
            setupPlayers(it,R.raw.gsh)
        }
        findViewById<Button>(R.id.uph6).setOnClickListener {
            setupPlayers(it,R.raw.ash)
        }
    }
    private fun setupPlayers(view: View,@RawRes audio:Int){
        player= MediaPlayer.create(this,audio)
        player!!.setOnCompletionListener {
            players.remove(it)
        }
        players.add(player!!)
        player!!.start()

        notes.add(Clap(currentNoteClap,(view as Button).text.toString()))
        adapter.notifyItemInserted(notes.size-1)
        noteList.smoothScrollToPosition(notes.size-1)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main,menu)
        return true
    }
    private fun calculateSleepTime():Int{
        val clapTime= CLAP_TIME
        return when (currentClap){
            P14->{
                ((clapTime.toFloat()*0.75f)/2.0f).toInt()
            }
            P24->{
                ((clapTime.toFloat()*0.5f)/3.0f).toInt()
            }
            P34->{
                ((clapTime.toFloat()*0.25f)/4.0f).toInt()
            }
            P44->{
                0
            }
            else -> 0
        }
    }
    private fun calculateSingleClapTime():Int{
        val clapTime= CLAP_TIME
        return when (currentClap){
            P14->{
                ((clapTime.toFloat()*0.75f)).toInt()
            }
            P24->{
                ((clapTime.toFloat()*0.5f)).toInt()
            }
            P34->{
                ((clapTime.toFloat()*0.25f)).toInt()
            }
            P44->{
                clapTime
            }
            else -> clapTime
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.play_menu->{
                if (notes.size<=0) return true

                playThread=thread(start = true) {
                    try {
                        val clapTime=calculateSingleClapTime()
                        val sleepTime=calculateSleepTime()
                        val current=ArrayList<Clap>()
                        current.addAll(notes)
                        val song=ArrayList<ArrayList<Clap>>()
                        var currentA: ArrayList<Clap>?=null
                        var cc=-1
                        for (clap in notes){
                            if (clap.clapId==cc){
                                currentA!!.add(clap)
                            }else{
                                currentA=ArrayList()
                                song.add(currentA)
                                currentA.add(clap)
                                cc=clap.clapId
                            }
                        }
                        for (s in song){
                            Thread.sleep(sleepTime.toLong())
                            for (clap in s){
                                if (clap.clapName=="Space"){
                                    Thread.sleep(clapTime.toLong())
                                    continue
                                }
                                val res=findRawRes(clap.clapName)
                                player=MediaPlayer.create(this,res)
                                players.add(player!!)
                                player!!.start()
                                val sleept=clapTime/s.size
                                Thread.sleep(sleept.toLong())
                            }
                        }
                    }catch (e:Exception){
                        e.printStackTrace()
                    }
                }
            }
            R.id.pause_menu->{
                playThread?.interrupt()
            }
            R.id.clear_all->{
                adapter.notifyItemRangeRemoved(0,notes.size)
                notes.clear()
            }
            R.id.p14->{
                clapText.text=SpannableStringBuilder("1/4拍")
                currentClap= P14
            }
            R.id.p24->{
                clapText.text=SpannableStringBuilder("2/4拍")
                currentClap= P24
            }
            R.id.p34->{
                clapText.text=SpannableStringBuilder("3/4拍")
                currentClap= P34
            }
            R.id.p44->{
                clapText.text=SpannableStringBuilder("4/4拍")
                currentClap=P44
            }
            R.id.duration->{
                val dialog=InputDialog(this)
                dialog.setOnFinishedInputListener {
                    CLAP_TIME=it.toInt()
                }
                dialog.setInputText(CLAP_TIME.toString())
                dialog.show()
            }
        }
        return true
    }
    override fun onDestroy() {
        for (player in players){
            player.stop()
            player.release()
        }
        playThread?.interrupt()
        super.onDestroy()
    }
    companion object{
        private val P14=0
        private val P24=1
        private val P34=2
        private val P44=3
        private var CLAP_TIME=2000
    }
}