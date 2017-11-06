package mobpro.praktikum.afif_praktikum8_whowriteapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText mBookInput;
    TextView mTitleText, mAuthorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBookInput = (EditText) findViewById(R.id.bookInput);
        mTitleText = (TextView) findViewById(R.id.authorText);
        mAuthorText = (TextView) findViewById(R.id.titleText);
    }

    public void searchBookss(View view){
        String queryString =  mBookInput.getText().toString();
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected() && queryString.length() !=0) {
            new FetchBook(mTitleText, mAuthorText).execute(queryString);
            mAuthorText.setText("");
            mTitleText.setText("Loading");
        }else{
            if (queryString.length() == 0){
                mAuthorText.setText("");
                mTitleText.setText("Please enter a search term");
            }else{
                mAuthorText.setText("");
                mTitleText.setText("Please check your network connection and try again");
            }
        }
    }
}
