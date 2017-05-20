package com.sodevan.ivrsystem;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.Locale;

import ai.api.AIListener;
import ai.api.android.AIConfiguration;
import ai.api.android.AIService;
import ai.api.model.AIError;
import ai.api.model.AIResponse;

public class Call extends AppCompatActivity {
    TextToSpeech t1;
    final String CLIENT_ACCESS_TOKEN="0f962c1104ea4663bb5fa1ca2bb105ad";
    RelativeLayout rel;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        rel= (RelativeLayout) findViewById(R.id.rel);
        t1=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i!=TextToSpeech.ERROR)
                    t1.setLanguage(Locale.UK);
            }
        });
        t1.speak("hey ronak",TextToSpeech.QUEUE_FLUSH, null);
        final AIConfiguration config = new AIConfiguration(CLIENT_ACCESS_TOKEN,
                AIConfiguration.SupportedLanguages.English,
                AIConfiguration.RecognitionEngine.System);


        final AIService aiService = AIService.getService(getApplicationContext(), config);
        ai.api.AIListener mylistner=new AIListener() {
            @Override
            public void onResult(AIResponse result) {
                Log.d("TAG","rechd");
                Log.d("TAG", String.valueOf(result.getResult().getFulfillment().getSpeech()));
            }

            @Override
            public void onError(AIError error) {

            }

            @Override
            public void onAudioLevel(float level) {

            }

            @Override
            public void onListeningStarted() {

            }

            @Override
            public void onListeningCanceled() {

            }

            @Override
            public void onListeningFinished() {

            }
        };
        aiService.setListener(mylistner);
        rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i==0) {
                    aiService.startListening();
                    Log.d("TAG", "started");
                    i=1;
                }
                else if(i==1) {
                    aiService.stopListening();
                    Log.d("TAG", "stopped");
                    i=0;
                }
            }
        });


    }
}
