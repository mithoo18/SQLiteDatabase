package com.example.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String DATABASE_NAME = "myemployeedatabase";
    TextView textViewViewEmployees;
    EditText editTextName, editTextSalary;
    Spinner spinnerDepartment;
    SQLiteDatabase mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewViewEmployees = (TextView) findViewById(R.id.textViewViewEmployees);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextSalary = (EditText) findViewById(R.id.editTextSalary);
        spinnerDepartment = (Spinner) findViewById(R.id.spinnerDepartment);

        findViewById(R.id.buttonAddEmployee).setOnClickListener(this);
        textViewViewEmployees.setOnClickListener(this);

        //CREATE DATABASE
        mDatabase = openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
    }
    //VALIDATION
    private boolean inputAreCorrect(String name,String salary) {

        if (name.isEmpty()) {
            editTextName.setError("Enter Name");
            editTextName.requestFocus();
            return false;
        }
        if (salary.isEmpty()) {
            editTextSalary.setError("Enter salary");
            editTextSalary.requestFocus();
            return false;
        }
        return true;
    }

    private void addEmployee() {
    }

    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.buttonAddEmployee:
                addEmployee();
                break;

            case R.id.textViewViewEmployees:
                startActivity(new Intent(this,EmployeeActivity.class));
                break;
        }
    }


}


