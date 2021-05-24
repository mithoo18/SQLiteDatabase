package com.example.sqlitedatabase;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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
        createEmployeeTable();

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
        String name = editTextName.getText().toString().trim();
        String salary = editTextSalary.getText().toString().trim();
        String dept = spinnerDepartment.getSelectedItem().toString();

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String joiningDate = sdf.format(cal.getTime());

        if(inputAreCorrect(name,salary)){

            String insertSQL = "INSERT INTO employees \n" +
                    "(name, department, joiningdate, salary)\n" +
                    "VALUES \n" +
                    "(?, ?, ?, ?);";

            mDatabase.execSQL(insertSQL,new String[]{name, dept, joiningDate, salary});
            Toast.makeText(this, "Employee Added Successfully", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
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
    private void createEmployeeTable() {
    mDatabase.execSQL(
            "CREATE TABLE IF NOT EXISTS employees (\n" +
                    "    id INTEGER NOT NULL CONSTRAINT employees_pk PRIMARY KEY AUTOINCREMENT,\n" +
                            "    name varchar(200) NOT NULL,\n" +
                            "    department varchar(200) NOT NULL,\n" +
                            "    joiningdate datetime NOT NULL,\n" +
                            "    salary double NOT NULL\n" +
                            ");"
        );
    }


}


