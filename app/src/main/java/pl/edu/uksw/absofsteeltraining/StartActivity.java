package pl.edu.uksw.absofsteeltraining;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "pl.edu.uksw.absofsteeltraining.MESSAGE";

    @BindView(R.id.level1Btn) Button level1Btn;
    @BindView(R.id.level2Btn) Button level2Btn;
    @BindView(R.id.level3Btn) Button level3Btn;
    @BindView(R.id.level4Btn) Button level4Btn;
    @BindView(R.id.toolbar_actionbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
    }

    @OnClick(R.id.level1Btn)
    public void loadLevel1() {
        Log.d("MainActivity", "LOAD LEVEL 1");

        Intent intent = new Intent(this, TrainingActivity.class);
        intent.putExtra(EXTRA_MESSAGE, 1);
        startActivity(intent);
    }

    @OnClick(R.id.level2Btn)
    public void loadLevel2() {
        Log.d("MainActivity", "LOAD LEVEL 2");

        Intent intent = new Intent(this, TrainingActivity.class);
        intent.putExtra(EXTRA_MESSAGE, 2);
        startActivity(intent);
    }

    @OnClick(R.id.level3Btn)
    public void loadLevel3() {
        Log.d("MainActivity", "LOAD LEVEL 3");

        Intent intent = new Intent(this, TrainingActivity.class);
        intent.putExtra(EXTRA_MESSAGE, 3);
        startActivity(intent);
    }

    @OnClick(R.id.level4Btn)
    public void loadLevel4() {
        Log.d("MainActivity", "LOAD LEVEL 4");

        Intent intent = new Intent(this, TrainingActivity.class);
        intent.putExtra(EXTRA_MESSAGE, 4);
        startActivity(intent);
    }
}
