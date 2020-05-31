package com.example.estudando;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ContentCourseEadActivity extends AppCompatActivity {
    TextView nameOfCourse, descriptionOfCourse;
    ImageView imageOfCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_course_ead);

        ActionBar actionBar = getSupportActionBar();

        nameOfCourse = findViewById(R.id.namecourse);
        descriptionOfCourse = findViewById(R.id.description);
        imageOfCourse = findViewById(R.id.imagecourse);

        Intent intent = getIntent();

        String mnameOfCourse = intent.getStringExtra("iTitle");
        String mdescriptionOfCourse = intent.getStringExtra("iDesc");

        actionBar.setTitle(mnameOfCourse);

        nameOfCourse.setText(mnameOfCourse);
        descriptionOfCourse.setText(mdescriptionOfCourse);
    }
}
