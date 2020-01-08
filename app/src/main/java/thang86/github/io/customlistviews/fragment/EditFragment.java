package thang86.github.io.customlistviews.fragment;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import thang86.github.io.customlistviews.R;
import thang86.github.io.customlistviews.model.Student;


/**
 * @Author ThangTX2
 */
public class EditFragment extends Fragment {

    private Student student;
    private int postion;


    private OnSendMessage mOnSendMessage;

    public EditFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mOnSendMessage = (OnSendMessage) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString());

        }
    }

    public static EditFragment newInstance(int position, Student student) {

        Bundle args = new Bundle();
        args.putParcelable("edit_student", student);
        args.putInt("pos", position);
        EditFragment fragment = new EditFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle bundle = getArguments();
        if (bundle == null || !bundle.containsKey("edit_student") || !bundle.containsKey("pos")) {
            return;
        } else {
            student = bundle.getParcelable("edit_student");
            postion = bundle.getInt("pos");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // inflater view
        final EditText studentName = view.findViewById(R.id.ed_tittle);
        Button btnEdit = view.findViewById(R.id.btn_edits);

        //set values
        studentName.setText(student.getNameStudent());

        //set event
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_edits:
                        mOnSendMessage.sendMessage(postion, new Student(student.getImgAvatar(),
                                studentName.getText().toString(), student.getClassName(), student.getBirthday()));
                        break;
                }
            }
        });

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mOnSendMessage = null;
    }

    public interface OnSendMessage {
        void sendMessage(int pos, Student student);
    }
}
