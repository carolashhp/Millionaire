package pt.challenge_it.android.flag.millionaire.activity;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pt.challenge_it.android.flag.millionaire.R;
import pt.challenge_it.android.flag.millionaire.model.Question;
import pt.challenge_it.android.flag.millionaire.provider.AnswerContract;

public class PublicoActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publico);



            TextView txtPublico = (TextView) findViewById(R.id.txtPublico);
            String correct = getIntent().getStringExtra("CORRECT_ANSWER");
            txtPublico.setText("A resposta " + correct + " obteve 70% enquanto que as restantes respostas obtiveram 10%. ");

            Button btnOk = (Button) findViewById(R.id.btnOk);

            btnOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_publico, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
