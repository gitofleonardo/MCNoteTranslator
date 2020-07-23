package cn.huangchengxi.notetranslator

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private var player:MediaPlayer?=null
    private val players=ArrayList<MediaPlayer>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }
    private fun init(){
        /**
         * low notes
         */
        findViewById<Button>(R.id.l1).setOnClickListener {
            player= MediaPlayer.create(this,R.raw.cs)
            player!!.setOnCompletionListener {
                players.remove(it)
            }
            players.add(player!!)
            player!!.start()
        }
        findViewById<Button>(R.id.l2).setOnClickListener {
            player= MediaPlayer.create(this,R.raw.ds)
            player!!.setOnCompletionListener {
                players.remove(it)
            }
            players.add(player!!)
            player!!.start()
        }
        findViewById<Button>(R.id.l3).setOnClickListener {
            player= MediaPlayer.create(this,R.raw.es)
            player!!.setOnCompletionListener {
                players.remove(it)
            }
            players.add(player!!)
            player!!.start()
        }
        findViewById<Button>(R.id.l4).setOnClickListener {
            player= MediaPlayer.create(this,R.raw.fs)
            player!!.setOnCompletionListener {
                players.remove(it)
            }
            players.add(player!!)
            player!!.start()
        }
        findViewById<Button>(R.id.l5).setOnClickListener {
            player= MediaPlayer.create(this,R.raw.gs)
            player!!.setOnCompletionListener {
                players.remove(it)
            }
            players.add(player!!)
            player!!.start()
        }
        findViewById<Button>(R.id.l6).setOnClickListener {
            player= MediaPlayer.create(this,R.raw.as_)
            player!!.setOnCompletionListener {
                players.remove(it)
            }
            players.add(player!!)
            player!!.start()
        }
        findViewById<Button>(R.id.l7).setOnClickListener {
            player= MediaPlayer.create(this,R.raw.bs)
            player!!.setOnCompletionListener {
                players.remove(it)
            }
            players.add(player!!)
            player!!.start()
        }
        findViewById<Button>(R.id.upl1).setOnClickListener {
            player= MediaPlayer.create(this,R.raw.css)
            player!!.setOnCompletionListener {
                players.remove(it)
            }
            players.add(player!!)
            player!!.start()
        }
        findViewById<Button>(R.id.upl2).setOnClickListener {
            player= MediaPlayer.create(this,R.raw.dss)
            player!!.setOnCompletionListener {
                players.remove(it)
            }
            players.add(player!!)
            player!!.start()
        }
        findViewById<Button>(R.id.upl4).setOnClickListener {
            player= MediaPlayer.create(this,R.raw.fss)
            player!!.setOnCompletionListener {
                players.remove(it)
            }
            players.add(player!!)
            player!!.start()
        }
        findViewById<Button>(R.id.upl5).setOnClickListener {
            player= MediaPlayer.create(this,R.raw.gss)
            player!!.setOnCompletionListener {
                players.remove(it)
            }
            players.add(player!!)
            player!!.start()
        }
        findViewById<Button>(R.id.upl6).setOnClickListener {
            player= MediaPlayer.create(this,R.raw.ass)
            player!!.setOnCompletionListener {
                players.remove(it)
            }
            players.add(player!!)
            player!!.start()
        }
        /**
         * middle notes
         */
        findViewById<Button>(R.id.m1).setOnClickListener {
            player= MediaPlayer.create(this,R.raw.cm_)
            player!!.setOnCompletionListener {
                players.remove(it)
            }
            players.add(player!!)
            player!!.start()
        }
        findViewById<Button>(R.id.m2).setOnClickListener {
            player= MediaPlayer.create(this,R.raw.dm_)
            player!!.setOnCompletionListener {
                players.remove(it)
            }
            players.add(player!!)
            player!!.start()
        }
        findViewById<Button>(R.id.m3).setOnClickListener {
            player= MediaPlayer.create(this,R.raw.em_)
            player!!.setOnCompletionListener {
                players.remove(it)
            }
            players.add(player!!)
            player!!.start()
        }
        findViewById<Button>(R.id.m4).setOnClickListener {
            player= MediaPlayer.create(this,R.raw.fm_)
            player!!.setOnCompletionListener {
                players.remove(it)
            }
            players.add(player!!)
            player!!.start()
        }
        findViewById<Button>(R.id.m5).setOnClickListener {
            player= MediaPlayer.create(this,R.raw.gm_)
            player!!.setOnCompletionListener {
                players.remove(it)
            }
            players.add(player!!)
            player!!.start()
        }
        findViewById<Button>(R.id.m6).setOnClickListener {
            player= MediaPlayer.create(this,R.raw.am_)
            player!!.setOnCompletionListener {
                players.remove(it)
            }
            players.add(player!!)
            player!!.start()
        }
        findViewById<Button>(R.id.m7).setOnClickListener {
            player= MediaPlayer.create(this,R.raw.bm_)
            player!!.setOnCompletionListener {
                players.remove(it)
            }
            players.add(player!!)
            player!!.start()
        }
        findViewById<Button>(R.id.upm1).setOnClickListener {
            player= MediaPlayer.create(this,R.raw.csm_)
            player!!.setOnCompletionListener {
                players.remove(it)
            }
            players.add(player!!)
            player!!.start()
        }
        findViewById<Button>(R.id.upm2).setOnClickListener {
            player= MediaPlayer.create(this,R.raw.dsm_)
            player!!.setOnCompletionListener {
                players.remove(it)
            }
            players.add(player!!)
            player!!.start()
        }
        findViewById<Button>(R.id.upm4).setOnClickListener {
            player= MediaPlayer.create(this,R.raw.fsm_)
            player!!.setOnCompletionListener {
                players.remove(it)
            }
            players.add(player!!)
            player!!.start()
        }
        findViewById<Button>(R.id.upm5).setOnClickListener {
            player= MediaPlayer.create(this,R.raw.gsm_)
            player!!.setOnCompletionListener {
                players.remove(it)
            }
            players.add(player!!)
            player!!.start()
        }
        findViewById<Button>(R.id.upm6).setOnClickListener {
            player= MediaPlayer.create(this,R.raw.asm_)
            player!!.setOnCompletionListener {
                players.remove(it)
            }
            players.add(player!!)
            player!!.start()
        }
        /**
         * high notes
         */
        findViewById<Button>(R.id.h1).setOnClickListener {
            player= MediaPlayer.create(this,R.raw.ch)
            player!!.setOnCompletionListener {
                players.remove(it)
            }
            players.add(player!!)
            player!!.start()
        }
        findViewById<Button>(R.id.h2).setOnClickListener {
            player= MediaPlayer.create(this,R.raw.dh)
            player!!.setOnCompletionListener {
                players.remove(it)
            }
            players.add(player!!)
            player!!.start()
        }
        findViewById<Button>(R.id.h3).setOnClickListener {
            player= MediaPlayer.create(this,R.raw.eh)
            player!!.setOnCompletionListener {
                players.remove(it)
            }
            players.add(player!!)
            player!!.start()
        }
        findViewById<Button>(R.id.h4).setOnClickListener {
            player= MediaPlayer.create(this,R.raw.fh)
            player!!.setOnCompletionListener {
                players.remove(it)
            }
            players.add(player!!)
            player!!.start()
        }
        findViewById<Button>(R.id.h5).setOnClickListener {
            player= MediaPlayer.create(this,R.raw.gh)
            player!!.setOnCompletionListener {
                players.remove(it)
            }
            players.add(player!!)
            player!!.start()
        }
        findViewById<Button>(R.id.h6).setOnClickListener {
            player= MediaPlayer.create(this,R.raw.ah)
            player!!.setOnCompletionListener {
                players.remove(it)
            }
            players.add(player!!)
            player!!.start()
        }
        findViewById<Button>(R.id.h7).setOnClickListener {
            player= MediaPlayer.create(this,R.raw.bh)
            player!!.setOnCompletionListener {
                players.remove(it)
            }
            players.add(player!!)
            player!!.start()
        }
        findViewById<Button>(R.id.uph1).setOnClickListener {
            player= MediaPlayer.create(this,R.raw.csh)
            player!!.setOnCompletionListener {
                players.remove(it)
            }
            players.add(player!!)
            player!!.start()
        }
        findViewById<Button>(R.id.uph2).setOnClickListener {
            player= MediaPlayer.create(this,R.raw.dsh)
            player!!.setOnCompletionListener {
                players.remove(it)
            }
            players.add(player!!)
            player!!.start()
        }
        findViewById<Button>(R.id.uph4).setOnClickListener {
            player= MediaPlayer.create(this,R.raw.fsh)
            player!!.setOnCompletionListener {
                players.remove(it)
            }
            players.add(player!!)
            player!!.start()
        }
        findViewById<Button>(R.id.uph5).setOnClickListener {
            player= MediaPlayer.create(this,R.raw.gsh)
            player!!.setOnCompletionListener {
                players.remove(it)
            }
            players.add(player!!)
            player!!.start()
        }
        findViewById<Button>(R.id.uph6).setOnClickListener {
            player= MediaPlayer.create(this,R.raw.ash)
            player!!.setOnCompletionListener {
                players.remove(it)
            }
            players.add(player!!)
            player!!.start()
        }
    }

    override fun onDestroy() {
        for (player in players){
            player.stop()
            player.release()
        }
        super.onDestroy()
    }
}