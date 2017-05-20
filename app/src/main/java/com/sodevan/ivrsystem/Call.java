package com.sodevan.ivrsystem;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;

import java.util.Locale;

public class Call extends AppCompatActivity {
    TextToSpeech t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        t1=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i!=TextToSpeech.ERROR)
                    t1.setLanguage(Locale.UK);
            }
        });
        t1.speak("hey ronak",TextToSpeech.QUEUE_FLUSH, null);
    }
}
