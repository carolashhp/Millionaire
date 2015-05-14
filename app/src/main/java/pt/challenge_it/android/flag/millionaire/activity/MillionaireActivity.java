package pt.challenge_it.android.flag.millionaire.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

import pt.challenge_it.android.flag.millionaire.R;
import pt.challenge_it.android.flag.millionaire.model.Question;
import pt.challenge_it.android.flag.millionaire.provider.AnswerContract;
import pt.challenge_it.android.flag.millionaire.provider.OperationsManager;
import pt.challenge_it.android.flag.millionaire.provider.QuestionContract;

public class MillionaireActivity extends Activity implements View.OnClickListener{

    private Question[] questions;
    private int currentQuestionIndex;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_millionaire);

        new GetQuestionsTask().execute();



        Button btnCinco = (Button)findViewById(R.id.btnCinco);
        final Button btnPublico = (Button)findViewById(R.id.btnPublico);
        final Button btnTelefone = (Button)findViewById(R.id.btnTelefone);

        btnCinco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });

        btnPublico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent publicoIntent = new Intent(getApplicationContext(), PublicoActivity.class);


                publicoIntent.putExtra("CORRECT_ANSWER", String.valueOf(questions[currentQuestionIndex].getCorrectAnswer().getIdentifier()));
                startActivity(publicoIntent);

                btnPublico.setVisibility(View.INVISIBLE);


            }
        });

        btnTelefone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


               Toast.makeText(getApplicationContext(), "A resposta correcta é a " + questions[currentQuestionIndex].getCorrectAnswer().getIdentifier() , Toast.LENGTH_SHORT).show();

                btnTelefone.setVisibility(View.INVISIBLE);


            }
        });



    }

    

    @Override
    public void onClick(View v) {

        if ((boolean)v.getTag())
        {
            this.currentQuestionIndex++;

            if (currentQuestionIndex<5){
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.toast_game_on), Toast.LENGTH_SHORT).show();

            }

            if (currentQuestionIndex==5){
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.toast_game_on_5), Toast.LENGTH_SHORT).show();
            }

            if (currentQuestionIndex>5&&currentQuestionIndex<10){
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.toast_game_on), Toast.LENGTH_SHORT).show();
            }

            if (currentQuestionIndex==10){
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.toast_game_on_10), Toast.LENGTH_SHORT).show();
            }

            if (currentQuestionIndex>10&&currentQuestionIndex<15){
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.toast_game_on), Toast.LENGTH_SHORT).show();
            }

            if(currentQuestionIndex==questions.length){

                Toast.makeText(getApplicationContext(), getResources().getString(R.string.toast_final), Toast.LENGTH_SHORT).show();
                finish();

            }
            else{
                changeToNextQuestion();
            }
        }
        else{

            if (currentQuestionIndex<5){
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.toast_game_over), Toast.LENGTH_SHORT).show();
                finish();
            }

            if(currentQuestionIndex>4&&currentQuestionIndex<10){
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.toast_game_over_5), Toast.LENGTH_SHORT).show();
                finish();
            }

            if(currentQuestionIndex>9&&currentQuestionIndex<16){
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.toast_game_over_10), Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }



    private void changeToNextQuestion(){
        Question question = this.questions[this.currentQuestionIndex];

        TextView txtQuestion = (TextView)findViewById(R.id.txtQuestion);
        txtQuestion.setText(question.getIdentifier() + " - " + question.getText());

        for(Question.Answer answer : question.getAnswers()){

            switch (answer.getIdentifier()){
                case 'A':
                    Button btnAnswerA = (Button)findViewById(R.id.btnAnswerA);
                    btnAnswerA.setText(answer.getIdentifier() + " - " + answer.getText());
                    btnAnswerA.setTag(answer.isCorrect());
                    btnAnswerA.setOnClickListener(this);

                    break;
                case 'B':
                    Button btnAnswerB = (Button)findViewById(R.id.btnAnswerB);
                    btnAnswerB.setText(answer.getIdentifier() + " - " + answer.getText());
                    btnAnswerB.setTag(answer.isCorrect());
                    btnAnswerB.setOnClickListener(this);
                    break;
                case 'C':
                    Button btnAnswerC = (Button)findViewById(R.id.btnAnswerC);
                    btnAnswerC.setText(answer.getIdentifier() + " - " + answer.getText());
                    btnAnswerC.setTag(answer.isCorrect());
                    btnAnswerC.setOnClickListener(this);
                    break;
                case 'D':
                    Button btnAnswerD = (Button)findViewById(R.id.btnAnswerD);
                    btnAnswerD.setText(answer.getIdentifier() + " - " + answer.getText());
                    btnAnswerD.setTag(answer.isCorrect());
                    btnAnswerD.setOnClickListener(this);
                    break;

                default:
                    break;
            }
        }


    }

    public class GetQuestionsTask extends AsyncTask<Void, Void, Question[]>{

        @Override
        protected Question[] doInBackground(Void... params) {


            return OperationsManager.GetAllTemp();
        }

        @Override
        protected void onPostExecute(Question[] questions) {
            MillionaireActivity.this.questions = questions;
            MillionaireActivity.this.currentQuestionIndex = 0;
            changeToNextQuestion();
        }
    }

}


