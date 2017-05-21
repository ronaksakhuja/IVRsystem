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
    TextToSpeech tts;
    String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        rel= (RelativeLayout) findViewById(R.id.rel);

        tts=new TextToSpeech(Call.this, new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int status) {
                // TODO Auto-generated method stub
                if(status == TextToSpeech.SUCCESS){
                    int result=tts.setLanguage(Locale.US);
                    if(result==TextToSpeech.LANG_MISSING_DATA ||
                            result==TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.e("error", "This Language is not supported");
                    }
                    else{
                        text="Good Morning. Please Tell your Request!";
                        tts.speak(text,TextToSpeech.QUEUE_FLUSH,null);

                    }
                }
                else
                    Log.e("error", "Initilization Failed!");
            }
        });



        final AIConfiguration config = new AIConfiguration(CLIENT_ACCESS_TOKEN,
                AIConfiguration.SupportedLanguages.English,
                AIConfiguration.RecognitionEngine.System);


        final AIService aiService = AIService.getService(getApplicationContext(), config);
        ai.api.AIListener mylistner=new AIListener() {
            @Override
            public void onResult(AIResponse result) {
                Log.d("TAG","rechd");
                String value= String.valueOf(result.getResult().getFulfillment().getSpeech());
                Log.d("TAG",value);
                if(value.equals("talk")){
                    text="Okay, Redirecting your call to our customer care representative.";
                }
                else if(value.equals("Balance")){
                    text="Your balance is Rs.250";
                }
                else if(value.equals("offers")){
                    text="You get full talktime in rs.250";
                }
                else if(value.equals("internet")){
                    text="Our internet offers are, 1 gb data for 250 rupees, 2 gb data for 300 rupees";
                }
                tts.speak(text,TextToSpeech.QUEUE_FLUSH,null);

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
