package com.example.studentinfo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.studentinfo.adapters.StudentAdapter;
import com.example.studentinfo.db.DBHelper;
import com.example.studentinfo.dialog.MenuDialog;
import com.example.studentinfo.model.Student;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerStudent;

    StudentAdapter studentAdapter;
    LinearLayoutManager layoutManager;

    List<Student> studentList;

    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dbHelper = new DBHelper(this);

        recyclerStudent = (RecyclerView) findViewById(R.id.recycler_student);
        findViewById(R.id.btn_add_student).setOnClickListener(this);

        initDataset();
    }

    private void initDataset() {
        studentList = dbHelper.getStudentList();
        layoutManager = new LinearLayoutManager(this);
        studentAdapter = new StudentAdapter(studentList);

        recyclerStudent.setAdapter(studentAdapter);
        recyclerStudent.setLayoutManager(layoutManager);

        studentAdapter.setmOnItemClickListener(new StudentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                final Student student = studentAdapter.getStudentList().get(position);

                MenuDialog menuDialog = new MenuDialog(HomeActivity.this);
                menuDialog.show();

                menuDialog.setOnDialogItemClick(new MenuDialog.OnDialogItemClick() {
                    @Override
                    public void onClick(int clickCode) {
                        if (clickCode == MenuDialog.DIALOG_EDIT) {
                            Intent intent = new Intent(HomeActivity.this, StudentActivity.class);
                            intent.putExtra(AppConstUtils.KEY_EDIT_STUDENT, true);
                            intent.putExtra(DBHelper.KEY_REGNO, student.getRegNo());
                            intent.putExtra(DBHelper.KEY_NAME, student.getName());
                            intent.putExtra(DBHelper.KEY_DEGREE, student.getDegree());
                            intent.putExtra(DBHelper.KEY_DEPT, student.getDept());
                            startActivity(intent);
                        } else {
                            dbHelper.deleteStudent(student);
                            recreate();
                        }
                    }
                });

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(HomeActivity.this, StudentActivity.class);
        startActivity(intent);
    }
}
