package dmitriiserdun.gmail.com.particleframe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import dmitriiserdun.gmail.com.particleframe.custom.MySurfaceView;

public class MainActivity extends AppCompatActivity  implements View.OnTouchListener {
    MySurfaceView mySurfaceView;
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
        button.setOnTouchListener(this);
//        Button button1 = findViewById(R.id.btn_click_f);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("thread_log","start Intent");


                Log.d("thread_log","end Intent");

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
        Log.d("thread_log", String.valueOf(button.isFocusable()));

        super.onResume();
        mySurfaceView.resume();

    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {


        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: // нажатие
                Log.d("thread_log", "DOWN CLICK");

                break;
            case MotionEvent.ACTION_MOVE: // движение
                break;
            case MotionEvent.ACTION_UP: // отпускание
            case MotionEvent.ACTION_CANCEL:
                Intent intent = new Intent(getBaseContext(), SampleActivity.class);
                startActivity(intent);
                Log.d("thread_log", "UP CLICK");

                break;
        }
        return true;
    }
}
