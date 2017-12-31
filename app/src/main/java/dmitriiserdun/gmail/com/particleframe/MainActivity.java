package dmitriiserdun.gmail.com.particleframe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import dmitriiserdun.gmail.com.particleframe.realization.ParticleView;

public class MainActivity extends AppCompatActivity  {
    ParticleView mySurfaceView;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mySurfaceView = findViewById(R.id.sview);
        mySurfaceView.setClickable(false);
        mySurfaceView.setFocusable(false);

       button = findViewById(R.id.btn_click);
        button.setFocusable(true);
        button.requestFocus();
//        Button button1 = findViewById(R.id.btn_click_f);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), SampleActivity.class);
                startActivity(intent);

            }
        });

//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("thread_log","click");
//
//            }
//        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        mySurfaceView.pause();

    }

    @Override
    protected void onResume() {

        super.onResume();
        mySurfaceView.resume();

    }

}
