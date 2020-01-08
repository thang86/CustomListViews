package thang86.github.io.customlistviews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import thang86.github.io.customlistviews.R;
import thang86.github.io.customlistviews.model.Student;


/**
 * @Author ThangTX2
 */
public class MyAdapter extends BaseAdapter {

    private List<Student> mStudentList;
    private Context mContext;
    private OnClickItem mListener;

    public MyAdapter(Context mContext, List<Student> mStudentList, OnClickItem onClickItem) {
        this.mContext = mContext;
        this.mStudentList = mStudentList;
        mListener = onClickItem;
    }

    @Override
    public int getCount() {
        return mStudentList == null ? 0 : mStudentList.size();
    }

    @Override
    public Object getItem(int position) {
        return mStudentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).
                    inflate(R.layout.item_layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Student student = (Student) getItem(position);
        viewHolder.nameStudent.setText(student.getNameStudent());
        viewHolder.className.setText(student.getClassName());
        viewHolder.birthday.setText(student.getBirthday());


        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClickItem(position);
            }
        });
        viewHolder.ivAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClickDeleteItem(position);
            }
        });

        return convertView;
    }

    private class ViewHolder {
        private View linearLayout;
        private ImageView ivAvatar;
        private TextView nameStudent;
        private TextView className;
        private TextView birthday;

        public ViewHolder(View view) {
            linearLayout = view.findViewById(R.id.my_linearlayout);
            ivAvatar = view.findViewById(R.id.iv_avatar);
            nameStudent = view.findViewById(R.id.tv_name_student);
            className = view.findViewById(R.id.tv_class_name);
            birthday = view.findViewById(R.id.tv_birthday);

        }

    }

    public interface OnClickItem {
        void onClickItem(int position);

        void onClickDeleteItem(int position);
    }
}
