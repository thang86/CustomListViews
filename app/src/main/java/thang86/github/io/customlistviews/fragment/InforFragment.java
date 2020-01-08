package thang86.github.io.customlistviews.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import thang86.github.io.customlistviews.MainActivity;
import thang86.github.io.customlistviews.R;
import thang86.github.io.customlistviews.model.Student;


/**
 * @Author ThangTX2
 */
public class InforFragment extends Fragment implements View.OnClickListener {

    private Student student;
    private int positon;


    public InforFragment() {
        // Required empty public constructor
    }

    public static InforFragment newInstance(int position, Student student) {

        Bundle args = new Bundle();
        args.putParcelable("student", student);
        args.putInt("position", position);
        InforFragment fragment = new InforFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle bundle = getArguments();
        if (bundle == null || !bundle.containsKey("student") || !bundle.containsKey("position")) {
            return;
        } else {
            positon = bundle.getInt("position");
            student = bundle.getParcelable("student");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_infor, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // inflater view
        TextView nameStudent = view.findViewById(R.id.tv_info_name_student);
        Button btnEdit = view.findViewById(R.id.btn_edit);
        TextView className = view.findViewById(R.id.tv_info_class_name);
        TextView birthday = view.findViewById(R.id.tv_info_birthday);

        //set value
        nameStudent.setText(student.getNameStudent().toString());
        className.setText(student.getClassName().toString());
        birthday.setText(student.getBirthday().toString());

        //set event
        btnEdit.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_edit:
                EditFragment editFragment = EditFragment.newInstance(positon, student);
                ((MainActivity) getActivity()).replaceFragment(editFragment, EditFragment.class.getName());
                break;
        }
    }


}
