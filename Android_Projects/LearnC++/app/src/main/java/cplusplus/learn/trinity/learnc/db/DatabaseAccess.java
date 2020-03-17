package cplusplus.learn.trinity.learnc.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import cplusplus.learn.trinity.learnc.model.interviewFaqModel;
import cplusplus.learn.trinity.learnc.model.SingleQuestion;


public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;
    private String question="question" ;
    private String optionA="optionA";
    private String optionB="optionB";
    private String optionC="optionC";
    private String optionD="optionD";
    private String answer="answer";
    private String explanation="explanation";
    private String category="category";
    private String difficulty="difficulty";

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    /**
     * Read all quotes from the database.
     *
     * @return a List of quotes
     */

    public ArrayList<SingleQuestion> getAllQuestions(String difficulty) {
        ArrayList<SingleQuestion> questionsList = new ArrayList<>();
        String selectQuery = "SELECT  id,question,A,B,C,D,answer,explanation,category,difficulty FROM quizMaster where difficulty='" + difficulty + "'";
        Log.d("Select Query", "selectQuery" + selectQuery);
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                SingleQuestion singleQuestion = new SingleQuestion();
                singleQuestion.setId(String.valueOf(cursor.getInt(0)));
                singleQuestion.setQuestion(cursor.getString(1));
                singleQuestion.setOptionA(cursor.getString(2));
                singleQuestion.setOptionB(cursor.getString(3));
                singleQuestion.setOptionC(cursor.getString(4));
                singleQuestion.setOptionD(cursor.getString(5));
                singleQuestion.setAnswer(cursor.getString(6));
                singleQuestion.setExplanation(cursor.getString(7));
                singleQuestion.setCategory(cursor.getString(8));
                singleQuestion.setDifficulty(cursor.getString(9));
                questionsList.add(singleQuestion);
            } while (cursor.moveToNext());
        }
        
        return questionsList;
    }

    public ArrayList<interviewFaqModel> getAllFAQ() {
        ArrayList<interviewFaqModel> faqList = new ArrayList<>();
        String selectQuery = "SELECT  id,question,answer,favourite FROM faqMaster";
        Log.d("Select Query", "selectQuery" + selectQuery);
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                interviewFaqModel singleFAQ = new interviewFaqModel();
                singleFAQ.setId(String.valueOf(cursor.getInt(0)));
                singleFAQ.setQuestion(cursor.getString(1));
                singleFAQ.setAnswer(cursor.getString(2));
                singleFAQ.setFavourite(cursor.getString(3));
                faqList.add(singleFAQ);
            } while (cursor.moveToNext());
        }

        return faqList;
    }

    public ArrayList<interviewFaqModel> getAllInterviewQuestion() {
        ArrayList<interviewFaqModel> faqList = new ArrayList<>();
        String selectQuery = "SELECT  id,question,answer,favourite FROM interviewQuestionMaster";
        Log.d("Select Query", "selectQuery" + selectQuery);
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                interviewFaqModel singleFAQ = new interviewFaqModel();
                singleFAQ.setId(String.valueOf(cursor.getInt(0)));
                singleFAQ.setQuestion(cursor.getString(1));
                singleFAQ.setAnswer(cursor.getString(2));
                singleFAQ.setFavourite(cursor.getString(3));
                faqList.add(singleFAQ);
            } while (cursor.moveToNext());
        }

        return faqList;
    }

    // favourites
    public ArrayList<interviewFaqModel> getFavFAQ() {
        ArrayList<interviewFaqModel> faqList = new ArrayList<>();
        String selectQuery = "SELECT  id,question,answer,favourite FROM faqMaster WHERE Favourite='Yes'";
        Log.d("Select Query", "selectQuery" + selectQuery);
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                interviewFaqModel singleFAQ = new interviewFaqModel();
                singleFAQ.setId(String.valueOf(cursor.getInt(0)));
                singleFAQ.setQuestion(cursor.getString(1));
                singleFAQ.setAnswer(cursor.getString(2));
                singleFAQ.setFavourite(cursor.getString(3));
                faqList.add(singleFAQ);
            } while (cursor.moveToNext());
        }

        return faqList;
    }

    public ArrayList<interviewFaqModel> getFavInterviewQuestion() {
        ArrayList<interviewFaqModel> faqList = new ArrayList<>();
        String selectQuery = "SELECT  id,question,answer,favourite FROM interviewQuestionMaster WHERE Favourite='Yes'";
        Log.d("Select Query", "selectQuery" + selectQuery);
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                interviewFaqModel singleFAQ = new interviewFaqModel();
                singleFAQ.setId(String.valueOf(cursor.getInt(0)));
                singleFAQ.setQuestion(cursor.getString(1));
                singleFAQ.setAnswer(cursor.getString(2));
                singleFAQ.setFavourite(cursor.getString(3));
                faqList.add(singleFAQ);
            } while (cursor.moveToNext());
        }

        return faqList;
    }

    public void updateFaq(interviewFaqModel faqModel,String fav){
        ContentValues values = new ContentValues();
        values.put("Favourite", fav);
        database.update("faqMaster",values,"ID=?",new String[]{String.valueOf(faqModel.getId())});
    }

    public void updateInterviewQues(interviewFaqModel faqModel,String fav){
        ContentValues values = new ContentValues();
        values.put("Favourite", fav);
        database.update("interviewQuestionMaster",values,"ID=?",new String[]{String.valueOf(faqModel.getId())});
    }

}
