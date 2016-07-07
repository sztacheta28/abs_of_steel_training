package pl.edu.uksw.absofsteeltraining;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrainingActivity extends AppCompatActivity{
    public final static String EXTRA_MESSAGE = "pl.edu.uksw.absofsteeltraining.MESSAGE";

    @BindView(R.id.toolbar_actionbar) Toolbar toolbar;
    @BindView(R.id.nrSeries) TextView nrSeriesTV;
    @BindView(R.id.nameOfExcercise) TextView nameOfExcerciseTV;
    @BindView(R.id.attempts) TextView attemptsTV;
    @BindView(R.id.excerciseImg) ImageView excerciseImageView;

    Thread thread;

    public static Context ctx;

    int level;

    boolean stop = false;

    enum ExcerciseType {
        ATTEMPTS, TIME, NONE
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        level = intent.getIntExtra(EXTRA_MESSAGE, 1);

        ctx = this;

        thread = new Thread(){
            int excercise = 0;
            int attempts = 0;
            int waitTime = 5000;
            int series = 0;
            int imgId = getResources().getIdentifier("exercise1", "drawable", getPackageName());
            int maxSeries = 0;
            boolean newExcercise = true;
            boolean imgVisible = false;
            ExcerciseType type = ExcerciseType.NONE;
            String excerciseName = "WAITING...";

            MediaPlayer mp1 = MediaPlayer.create(TrainingActivity.ctx, R.raw.knob);
            MediaPlayer mp2 = MediaPlayer.create(TrainingActivity.ctx, R.raw.arpeggio);

            @Override
            public void run(){
                if(level == 1){
                    maxSeries = 2;
                }else if(level == 2){
                    maxSeries = 4;
                }else if(level == 3){
                    maxSeries = 6;
                }else if(level == 4){
                    maxSeries = 8;
                }

                while(!stop) {
                    try {
                        if(newExcercise) {
                            newExcercise = false;

                            mp2.start();
                            synchronized (this) {
                                Thread.sleep(3000);
                            }
                        }

                        synchronized (this) {
                            Thread.sleep(waitTime);
                        }
                    } catch (InterruptedException ex) {}

                    mp1.start();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            nrSeriesTV.setText(String.valueOf(series) + " SERIES");

                            if (imgVisible) {
                                excerciseImageView.setVisibility(View.VISIBLE);
                                excerciseImageView.setImageResource(imgId);
                            } else {
                                excerciseImageView.setVisibility(View.GONE);
                            }

                            if (type == ExcerciseType.ATTEMPTS) {
                                attemptsTV.setText(String.valueOf(attempts) + " ATTEMPTS");
                            } else if (type == ExcerciseType.TIME) {
                                attemptsTV.setText(String.valueOf(attempts) + " SECONDS");
                            } else {
                                attemptsTV.setText("");
                            }

                            nameOfExcerciseTV.setText(excerciseName);
                        }
                    });

                    --attempts;

                    if (attempts <= 0) {
                        ++excercise;
                        newExcercise = true;

                        switch (excercise) {
                            case 1:
                                if(series == maxSeries){
                                    stop = true;
                                }
                                imgVisible = true;
                                ++series;
                                excerciseName = "SIT-UPS";
                                attempts = 10;
                                waitTime = 3000;
                                type = ExcerciseType.ATTEMPTS;
                                imgId = getResources().getIdentifier("exercise1", "drawable", getPackageName());
                                break;
                            case 2:
                                excerciseName = "FLUTTER KICKS";
                                attempts = 12;
                                waitTime = 3000;
                                type = ExcerciseType.ATTEMPTS;
                                imgId = getResources().getIdentifier("exercise2", "drawable", getPackageName());
                                break;
                            case 3:
                                excerciseName = "LEG RAISES";
                                attempts = 8;
                                waitTime = 3000;
                                type = ExcerciseType.ATTEMPTS;
                                imgId = getResources().getIdentifier("exercise3", "drawable", getPackageName());
                                break;
                            case 4:
                                excerciseName = "CYCLING CRUNCHES";
                                attempts = 10;
                                waitTime = 3000;
                                type = ExcerciseType.ATTEMPTS;
                                imgId = getResources().getIdentifier("exercise4", "drawable", getPackageName());
                                break;
                            case 5:
                                excerciseName = "KNEE CRUNCHES";
                                attempts = 10;
                                waitTime = 3000;
                                type = ExcerciseType.ATTEMPTS;
                                imgId = getResources().getIdentifier("exercise5", "drawable", getPackageName());
                                break;
                            case 6:
                                excerciseName = "LEG PULL-INS";
                                attempts = 8;
                                waitTime = 3000;
                                type = ExcerciseType.ATTEMPTS;
                                imgId = getResources().getIdentifier("exercise6", "drawable", getPackageName());
                                break;
                            case 7:
                                excerciseName = "PLANK ARM REACHES";
                                attempts = 10;
                                waitTime = 3000;
                                type = ExcerciseType.ATTEMPTS;
                                imgId = getResources().getIdentifier("exercise7", "drawable", getPackageName());
                                break;
                            case 8:
                                excerciseName = "ELBOW PLANK";
                                attempts = 30;
                                waitTime = 1000;
                                type = ExcerciseType.TIME;
                                imgId = getResources().getIdentifier("exercise8", "drawable", getPackageName());
                                break;
                            case 9:
                                excerciseName = "BODY SAW";
                                attempts = 10;
                                waitTime = 3000;
                                type = ExcerciseType.ATTEMPTS;
                                imgId = getResources().getIdentifier("exercise9", "drawable", getPackageName());
                                break;
                            case 10:
                                imgVisible = false;
                                excerciseName = "RELAX";
                                excercise = 0;
                                waitTime = 60000;
                                type = ExcerciseType.NONE;
                        }
                    }
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        nrSeriesTV.setVisibility(View.GONE);
                        nameOfExcerciseTV.setText("THE END");
                        excerciseImageView.setVisibility(View.GONE);
                        attemptsTV.setVisibility(View.GONE);
                    }
                });
            }

            public void close(){
                stop = true;
            }
        };

        thread.start();
    }

    @Override
    protected void onDestroy() {
        stop = true;
        thread.interrupt();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        stop = true;
        thread.interrupt();
        super.onBackPressed();
    }
}
