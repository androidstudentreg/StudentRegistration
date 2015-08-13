package com.example.studentinfo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.studentinfo.db.DBHelper;
import com.example.studentinfo.model.Student;

public class StudentActivity extends AppCompatActivity {

    EditText etRegno, etName;
    Spinner spDegree, spDept;
    Button btnAction;

    boolean isEdiMode;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        isEdiMode = getIntent().getBooleanExtra(AppConstUtils.KEY_EDIT_STUDENT, false);

        dbHelper = new DBHelper(this);

        etRegno = (EditText) findViewById(R.id.et_regno);
        etName = (EditText) findViewById(R.id.et_name);
        spDegree = (Spinner) findViewById(R.id.sp_degree);
        spDept = (Spinner) findViewById(R.id.sp_dept);

        btnAction = (Button) findViewById(R.id.btn_action);

        btnAction.setText(isEdiMode ? "Update" : "Add");

        if (isEdiMode) {
            etRegno.setText(getIntent().getStringExtra(DBHelper.KEY_REGNO));
            etName.setText(getIntent().getStringExtra(DBHelper.KEY_NAME));
            String[] degree = getResources().getStringArray(R.array.degree_array);
            String[] dept = getResources().getStringArray(R.array.dept_array);
            for (int i = 0; i < degree.length; i++) {
                if (degree[i].equals(getIntent().getStringExtra(DBHelper.KEY_DEGREE))) {
                    spDegree.setSelection(i);
                    break;
                }
            }

            for (int i = 0; i < dept.length; i++) {
                if (dept[i].equals(getIntent().getStringExtra(DBHelper.KEY_DEPT))) {
                    spDept.setSelection(i);
                    break;
                }
            }
        }

        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student = new Student();
                student.setRegNo(etRegno.getText().toString());
                student.setName(etName.getText().toString());
                student.setDegree(spDegree.getSelectedItem().toString());
                student.setDept(spDept.getSelectedItem().toString());

                if (isEdiMode)
                    dbHelper.updateStudent(student);
                else
                    dbHelper.addStudent(student);

                Intent intent = new Intent(StudentActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_student, menu);
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
