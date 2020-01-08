package thang86.github.io.customlistviews;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

import thang86.github.io.customlistviews.fragment.EditFragment;
import thang86.github.io.customlistviews.fragment.MainFragment;
import thang86.github.io.customlistviews.model.Student;
import thang86.github.io.customlistviews.utils.FakeDateDemo;


/**
 * @Author ThangTX2
 */
public class MainActivity extends AppCompatActivity implements EditFragment.OnSendMessage {

    private List<Student> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        studentList = FakeDateDemo.demoList();
        if (null == savedInstanceState) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction().addToBackStack(MainFragment.class.getName());
            MainFragment inforFragment = MainFragment.newInstance(studentList);
            ft.add(R.id.container, inforFragment, MainFragment.class.getName());
            ft.commit();
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void replaceFragment(Fragment fragment, String tag) {
        Fragment curentFragment = getSupportFragmentManager().findFragmentById(R.id.container);
        if (curentFragment.getClass() == fragment.getClass()) {
            return;
        }
        if (getSupportFragmentManager().findFragmentByTag(tag) != null) {
            getSupportFragmentManager().popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(tag)
                .replace(R.id.container, fragment, tag)
                .commit();

    }


    @Override
    public void onBackPressed() {
        int fragmentsInStack = getSupportFragmentManager().getBackStackEntryCount();
        if (fragmentsInStack > 1) {
            getSupportFragmentManager().popBackStack();
        } else if (fragmentsInStack == 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void sendMessage(int pos, Student student) {

        FragmentManager fm = getSupportFragmentManager();
        Fragment curentFragment = getSupportFragmentManager().findFragmentByTag(MainFragment.class.getName());

        for (int i = 0; i < fm.getBackStackEntryCount(); i++) {
            if (!curentFragment.getClass().getName().equals(fm.getBackStackEntryAt(i).getName())) {
                getSupportFragmentManager().popBackStack();
            }
        }
        MainFragment main = (MainFragment) fm.findFragmentByTag(MainFragment.class.getName());
        if (main != null) {
            main.updates(pos, student);
        }


    }
}

