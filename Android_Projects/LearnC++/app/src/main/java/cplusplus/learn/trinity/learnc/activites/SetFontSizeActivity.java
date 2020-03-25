package cplusplus.learn.trinity.learnc.activites;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import cplusplus.learn.trinity.learnc.R;

public class SetFontSizeActivity extends AppCompatActivity {
    DiscreteSeekBar discreteSeekBar;
    TextView textView;
    Button setSize;
    int fontsize;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_font_size);


        SharedPreferences sharedPreferences=getSharedPreferences("SetFontSize", Context.MODE_PRIVATE);
        fontsize= sharedPreferences.getInt("Fontsize",15);


        discreteSeekBar = (DiscreteSeekBar) findViewById(R.id.slider);
        textView=(TextView)findViewById(R.id.tvJava);
        setSize=(Button)findViewById(R.id.button);


        discreteSeekBar.setProgress(fontsize);

       textView.setTextSize(discreteSeekBar.getProgress());


        discreteSeekBar.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                textView.setTextSize(discreteSeekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {

            }
        });

        setSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("SetFontSize", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("Fontsize", discreteSeekBar.getProgress());
                editor.apply();
                editor.commit();
                Toast.makeText(SetFontSizeActivity.this,"Selected Font size:"+discreteSeekBar.getProgress(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
