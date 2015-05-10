package pt.challenge_it.android.flag.millionaire.activity;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pt.challenge_it.android.flag.millionaire.R;
import pt.challenge_it.android.flag.millionaire.model.Question;
import pt.challenge_it.android.flag.millionaire.provider.AnswerContract;
import pt.challenge_it.android.flag.millionaire.provider.OperationsManager;
import pt.challenge_it.android.flag.millionaire.provider.QuestionContract;

/**
 * Application for Millionaire.
 * @author Challenge.IT
 */
public class MillionaireApplication extends Application
{
    private CursorAdapter _adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate();
        //setContentView(R.layout.activity_main);

        //new MillionaireAsyncTask().execute();

        OperationsManager manager = new OperationsManager(this);
        List<Question> questions = manager.getAllQuestions();
        Question primeira = questions.get(0);
        final Question answers = primeira.getAnswers();

        //ArrayList<Question> questionsList = manager.getAll();
        //Question primeira = questionsList.get(0);
        //ArrayList<Answer> answers = primeira.getAnswers();


        Button btnCinco = (Button)findViewById(R.id.btnCinco);
        Button btnPublico = (Button)findViewById(R.id.btnPublico);
        Button btnTelefone = (Button)findViewById(R.id.btnTelefone);

        TextView txtQuestion = (TextView)findViewById(R.id.txtQuestion);
        txtQuestion.setText(primeira.getText());

        Button btnAnswerA = (Button)findViewById(R.id.btnAnswerA);
        Button btnAnswerB = (Button)findViewById(R.id.btnAnswerB);
        Button btnAnswerC = (Button)findViewById(R.id.btnAnswerC);
        Button btnAnswerD = (Button)findViewById(R.id.btnAnswerD);

        btnAnswerA.setText(answers.getText(0).getAnswer());
        btnAnswerB.setText(answers.getText(1).getAnswer());
        btnAnswerC.setText(answers.getText(2).getAnswer());
        btnAnswerD.setText(answers.getText(3).getAnswer());


        btnCinco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete answsers

            }
        });

        btnPublico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent cincoIntent = new Intent(getApplicationContext(), CincoActivity.class);
                startActivity(cincoIntent);




            }
        });

        btnTelefone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "A resposta correcta é a " + answers.getIdentifier(AnswerContract.CORRECT==1), Toast.LENGTH_SHORT)
                        .show();


            }
        });

        btnAnswerA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AnswerContract.CORRECT ==1){
                    Toast.makeText(getApplicationContext(), "Resposta Correcta ", Toast.LENGTH_SHORT).show();
                    /// aparecer próxima questao
                }
            }
        });

        btnAnswerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnAnswerC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnAnswerD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    private class MillionaireAdapter extends CursorAdapter
    {
        /**

         *
         * @pt Construtor de Adapter que recebe o cursor com a informação a colocar na listagem de temperaturas.
         */
        public MillionaireAdapter(Cursor cursor)
        {
            super(MillionaireApplication.this, cursor, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        }

        @Override
        public boolean areAllItemsEnabled()
        {
            return true;
        }

        @Override
        public boolean isEnabled(int position)
        {
            return true;
        }

        @Override
        public void bindView(View v, final Context ctx, final Cursor cursor)
        {
            /**
             * Affect values of temperatures in each row of list.
             *
             * @pt Afectar valores de cada linha de registo na vista da Activity.
             */


            ((TextView) v.findViewById(R.id.txtQuestion))
                    .setText(cursor.getString(cursor.getColumnIndex(QuestionContract.QUESTION)));

        }

        @Override
        public View newView(Context ctx, Cursor cursor, ViewGroup vg)
        {
            return getLayoutInflater().inflate(R.layout.application_millionaire, null);
        }
    }


    private class MillionaireAsyncTask extends AsyncTask<Void, Void, Cursor>
    {
        @Override
        protected Cursor doInBackground(Void... params)
        {
            /**
             * Make the query. Start managing cursor and return it.
             *
             * @pt Realizar o pedido ao ContentProvider da listagem das questões.
             */
            Cursor QuestionCursor = getContentResolver().query(QuestionContract.CONTENT_PROVIDER, null, null, null, null);
            Cursor AnswerCursor = getContentResolver().query(AnswerContract.CONTENT_PROVIDER, null, null, null, null);

            /**
             * Tells that the MailActivity controls the life-cycle of the cursor.
             *
             * @pt Delegar na Activity responsabilidade de controlar o tempo de vida do cursor com os dados.
             */
            startManagingCursor(QuestionCursor);
            return QuestionCursor;
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            /**
             * If is the first time, creates the adapter.
             *
             * @pt Se foi a primeira chamada, criar o adapter com os dados do cursor.
             */
            /*if(_adapter == null)
            {
                _adapter = new MillionaireAdapter(cursor);
                // Set adapter in the list.
                MillionaireActivity.this.setAdapter(_adapter);
            }
            /**
             * If the adapter already exists, swap the cursor in adapter.
             *
             * @pt Se já existir, colocar o novo cursor com possíveis novos dados.
             */
            /*else
            {
                // Stop using the old version of the temperatures in the cursor.
                stopManagingCursor(_adapter.getCursor());
                _adapter.changeCursor(cursor);
            }*/
        }
    }
}
