package com.example.dan.bam;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;

import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.io.File;
import java.io.IOException;
import java.net.URI;


public class MainActivity extends Activity  {

    private ImageButton btn1, btn2, btn3;
    private Button btnRecord;

    private String FILE;

    private MediaPlayer play = null;
    private MediaRecorder record = null;
    private static String mFileName = null;
    private boolean isPlayPressed = false;

    private boolean isButtonPressed = false;





    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FILE = Environment.getExternalStorageDirectory() + "/temp1Record.3gpp";

        btn1 = (ImageButton) findViewById(R.id.btn1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play = MediaPlayer.create(MainActivity.this, Uri.parse(FILE));
                play.start();

                /*if (isPlayPressed == false) {
                    try {
                        isPlayPressed = true;
                        startPlayback();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    isPlayPressed = false;
                    stopPlayback();
                }*/
                play.setOnCompletionListener(new MediaPlayer.OnCompletionListener()

                {
                @Override
                public void onCompletion (MediaPlayer mp){
                play.release();
                }
                }

                );
            }
    });





        btn1.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View pView, MotionEvent pEvent) {
                pView.onTouchEvent(pEvent);
                // We're only interested in when the button is released.
                if (pEvent.getAction() == MotionEvent.ACTION_UP) {
                    // We're only interested in anything if our speak button is currently pressed.
                    if (isButtonPressed) {
                        stopRecord();
                        isButtonPressed = false;
                    }
                }
                return false;
            }
        });


        btn1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                isButtonPressed = true;

                //if (btnRecord.getText().toString().equals("Record")) {
                try {
                    startRecord();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //} else if (btnRecord.getText().toString().equals("Stop Record")) {

                return true;
            }
        });



    }


    public void startRecord() throws Exception
    {
        if(record != null)
        {
            record.release();
        }

        File fileOut = new File(FILE);

        if(fileOut != null)
        {
            fileOut.delete();
        }

        record = new MediaRecorder();
        record.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
        record.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        record.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        record.setOutputFile(FILE);
        record.prepare();
        record.start();


    }

    public void stopRecord()
    {
        record.stop();
        record.release();

    }

    public void startPlayback() throws Exception
    {
        play = new MediaPlayer();
        play.setDataSource(FILE);
        play.prepare();
        play.start();

        play.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                play.release();
            }
        });

    }

    public void stopPlayback()
    {
        play.stop();

    }




}
