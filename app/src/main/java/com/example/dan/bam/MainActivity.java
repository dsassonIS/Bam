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
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;


public class MainActivity extends Activity {

    // Constructor - create recordable buttons
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ArrayList<RecordableButton> recordables = new ArrayList<RecordableButton>();

        for (int i = 1; i < 7; i++) {
            recordables.add(new RecordableButton(i));
        }
    }


    public class RecordableButton {
        private ImageButton btn;

        private String FILE;

        //private MediaPlayer play = null;
        private MediaRecorder record = null;
        private String mFileName = null;
        private boolean isPlayPressed = false;

        private boolean isButtonPressed = false;

        public RecordableButton(int id) {
            switch (id) {
                case 1:
                    btn = (ImageButton) findViewById(R.id.btn1);
                    break;

                case 2:
                    btn = (ImageButton) findViewById(R.id.btn2);
                    break;

                case 3:
                    btn = (ImageButton) findViewById(R.id.btn3);
                    break;

                case 4:
                    btn = (ImageButton) findViewById(R.id.btn4);
                    break;

                case 5:
                    btn = (ImageButton) findViewById(R.id.btn5);
                    break;

                case 6:
                    btn = (ImageButton) findViewById(R.id.btn6);
                    break;

                default:
            }

            FILE = Environment.getExternalStorageDirectory() + "/btn" + id + ".3gpp";

            btn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    try {
                        startPlayback();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });


            btn.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View pView, MotionEvent pEvent) {
                    pView.onTouchEvent(pEvent);
                    // We're only interested in when the button is released.
                    if (pEvent.getAction() == MotionEvent.ACTION_UP) {
                        // We're only interested in anything if our speak button is currently pressed.
                        if (isButtonPressed) {
                            try {
                                stopRecord();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            isButtonPressed = false;
                        }
                    }
                    return false;
                }
            });


            btn.setOnLongClickListener(new View.OnLongClickListener() {
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

        public void startRecord() throws Exception {

            if (record != null) {
                record.release();
            }

            File fileOut = new File(FILE);

            if (fileOut != null) {
                fileOut.delete();
            }

            record = new MediaRecorder();
            record.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
            record.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            record.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
            record.setOutputFile(FILE);
            record.prepare();
            record.start();
            Log.e("Dan", "recording started");


        }

        public void stopRecord() throws IOException {
            record.stop();
            record.release();


            Log.d("Dan", "recording stopped");

        }

        public void startPlayback() throws Exception {
//            play = new MediaPlayer();
//            play.setDataSource(FILE);

            MediaPlayer play = new MediaPlayer();

            play = MediaPlayer.create(MainActivity.this, Uri.parse(FILE));
            play.setDataSource(FILE);

            if (play != null) {

                play.setOnCompletionListener(new MediaPlayer.OnCompletionListener()

                                             {
                                                 @Override
                                                 public void onCompletion(MediaPlayer mp) {
                                                     mp.release();
                                                     Log.d("Dan", "play stopped");
                                                     System.out.println("play stopeed");

                                                 }
                                             }
                );

                play.prepare();
                play.start();

                Log.d("Dan", "play started");
                System.out.println("play started");


            }


        }

        public void stopPlayback() {
            //play.stop();
           // Log.d("Dan", "play stopped");
        }

    }


}



