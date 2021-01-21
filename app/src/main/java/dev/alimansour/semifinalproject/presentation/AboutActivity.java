package dev.alimansour.semifinalproject.presentation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import dev.alimansour.semifinalproject.R;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setTitle(getString(R.string.about_us));
    }
}