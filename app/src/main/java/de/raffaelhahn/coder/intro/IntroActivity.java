package de.raffaelhahn.coder.intro;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import de.raffaelhahn.coder.R;
import de.raffaelhahn.coder.projectmanagement.ProjectSelectionActivity;

public class IntroActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private IntroFragmentStateAdapter fragmentStateAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_intro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        fragmentStateAdapter = new IntroFragmentStateAdapter(this);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(fragmentStateAdapter);
        viewPager.setUserInputEnabled(false);
    }

    public void nextFragment() {
        if(viewPager.getCurrentItem() == fragmentStateAdapter.getItemCount()-1) {
            startActivity(new Intent(this, ProjectSelectionActivity.class));
            finish();
            return;
        }
        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
    }
}