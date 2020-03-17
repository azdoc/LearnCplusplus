package cplusplus.learn.trinity.learnc.activites;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import cplusplus.learn.trinity.learnc.R;
import cplusplus.learn.trinity.learnc.model.TutorialModel;
import cplusplus.learn.trinity.learnc.utilities.CommonActivity;

public class TutorialDispalyActivity extends CommonActivity {

    WebView webViewDesccription;
    TextView textViewTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_dispaly);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TutorialModel tutorialModel =(TutorialModel) getIntent().getSerializableExtra("tutorial");
        webViewDesccription=(WebView) findViewById(R.id.webView);
        textViewTitle=(TextView)findViewById(R.id.title) ;
        webViewDesccription.loadDataWithBaseURL(null, getString(tutorialModel.getResource()), "text/html", "utf-8", null);
        webViewDesccription.setScrollContainer(false);
        textViewTitle.setText(tutorialModel.getTopic());

        SharedPreferences sharedPreferences=getSharedPreferences("SetFontSize", Context.MODE_PRIVATE);
        int fontsize= sharedPreferences.getInt("Fontsize",15);
        final WebSettings webSettings = webViewDesccription.getSettings();
        webSettings.setDefaultFontSize(fontsize);



    }




}
