package cplusplus.learn.trinity.learnc.utilities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import cplusplus.learn.trinity.learnc.R;
import cplusplus.learn.trinity.learnc.activites.FavouritesActivity;
import cplusplus.learn.trinity.learnc.activites.HomeActivity;
import cplusplus.learn.trinity.learnc.activites.SetFontSizeActivity;
import cplusplus.learn.trinity.learnc.activites.TutorialDispalyActivity;
import cplusplus.learn.trinity.learnc.activites.TutorialsHomeActivity;
import cplusplus.learn.trinity.learnc.model.TutorialModel;

import static java.security.AccessController.getContext;

public class CommonActivity extends AppCompatActivity {
    private static Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about_us) {
            return true;
        }
        else if(id==R.id.action_font_size){
            Intent intent = new Intent(getBaseContext(), SetFontSizeActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static void showToast(String message,Context context){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void fabCommonAction(View view){
        mContext.startActivity(new Intent(mContext,FavouritesActivity.class));
    }


}
