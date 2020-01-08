package thang86.github.io.customlistviews.fragment;


import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import thang86.github.io.customlistviews.MainActivity;
import thang86.github.io.customlistviews.R;
import thang86.github.io.customlistviews.adapter.MyAdapter;
import thang86.github.io.customlistviews.customview.MyListView;
import thang86.github.io.customlistviews.model.Student;


/**
 * @Author ThangTX2
 */
public class MainFragment extends Fragment implements MyAdapter.OnClickItem {

    private MyAdapter adapter;
    private List<Student> mStudentList;


    public MainFragment() {
    }

    public static MainFragment newInstance(List<Student> mStudentList) {

        Bundle args = new Bundle();
        args.putParcelableArrayList("students", (ArrayList<? extends Parcelable>) mStudentList);

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle bundle = getArguments();
        if (bundle == null || !bundle.containsKey("students")) {
            return;
        } else {
            mStudentList = bundle.getParcelableArrayList("students");
        }

    }

    /**
     * Init view
     *
     * @param view
     */
    public void initView(View view) {

        final MyListView mMyListView = view.findViewById(R.id.my_list_view);
        adapter = new MyAdapter(getContext(), mStudentList, this);
        mMyListView.setAdapter(adapter);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflater view
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    @Override
    public void onClickItem(int position) {
        Student student = mStudentList.get(position);
        InforFragment inforFragment = InforFragment.newInstance(position, student);
        ((MainActivity) getActivity()).replaceFragment(inforFragment, InforFragment.class.getName());

    }

    @Override
    public void onClickDeleteItem(int position) {
        mStudentList.remove(position);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();

    }


    public void updates(int pos, Student student) {
        adapter = new MyAdapter(getContext(), mStudentList, this);
        mStudentList.set(pos, student);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
