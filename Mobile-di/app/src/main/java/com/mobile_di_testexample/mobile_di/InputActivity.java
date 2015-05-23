package com.mobile_di_testexample.mobile_di;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

// Here in this activity we simply take data from the form and pass it to second activity
public class InputActivity extends Activity {
    EditText firstName, lastName;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        // Here we hide Action Bar from the top of application
        if(getActionBar() != null)
            getActionBar().hide();
        firstName = (EditText) findViewById(R.id.editFname);
        lastName = (EditText) findViewById(R.id.editLname);
        submit = (Button) findViewById(R.id.submit);
        validationView();

    }

    private void validationView(){

        // Submitting you details here and passes to OutputActivity
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isFormDataValid(firstName)  && isFormDataValid(lastName)) {
                    // with the intent we move our data from one activity to another activity
                    Intent intent = new Intent(InputActivity.this, OutputActivity.class);
                    intent.putExtra("first", firstName.getText().toString());
                    intent.putExtra("last", lastName.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }
    // here we actually validating input data
    private boolean isFormDataValid(EditText editText) {
        String name = editText.getText().toString();
        if (name.trim().equals("")) {
            showErrorMessage(editText, "This field is required!!");
            return false;
        }else{
            for (int i = 0; i < name.length()-1; i++) {
                if (!Character.isLetter(name.charAt(i))) {
                    showErrorMessage(editText, "Enter valid name!");
                    return false;
                }
            }
        }
        return true;
    }


    /** Displaying error if any detail is wrong */
    private void showErrorMessage(final EditText view, String string) {
        view.setError(string);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setError(null);
            }
        }, 3500);
    }

}
