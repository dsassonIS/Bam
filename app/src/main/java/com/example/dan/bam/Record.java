package com.example.dan.bam;

/**
 * Created by Dan on 09/08/2015.
 */

/*
 * The application needs to have the permission to write to external storage
 * if the output file is written to the external storage, and also the
 * permission to record audio. These permissions must be set in the
 * application's AndroidManifest.xml file, with something like:
 *
 * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
 * <uses-permission android:name="android.permission.RECORD_AUDIO" />
 *
 */


        import android.app.Activity;
        import android.widget.LinearLayout;
        import android.os.Bundle;
        import android.os.Environment;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.content.Context;
        import android.util.Log;
        import android.media.MediaRecorder;
        import android.media.MediaPlayer;

        import java.io.IOException;


public class Record extends Activity
{

}