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
public class MillionaireApplication extends Application {
}
