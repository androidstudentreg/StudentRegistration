package com.example.studentinfo.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.studentinfo.R;
import com.example.studentinfo.model.Student;

import java.util.List;

/**
 * Created by vinay on 28/6/15.
 */
public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder>  {


    List<Student> studentList;
    OnItemClickListener mOnItemClickListener;

    public StudentAdapter(List<Student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_student,
                null);
        StudentViewHolder viewHolder;
        if (mOnItemClickListener != null) {
            viewHolder = new StudentViewHolder(view, mOnItemClickListener);
        } else {
            viewHolder = new StudentViewHolder(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(StudentViewHolder studentViewHolder, int position) {
        Student student = studentList.get(position);

        studentViewHolder.tvRegno.setText(student.getRegNo());
        studentViewHolder.tvName.setText(student.getName());
        studentViewHolder.tvDegree.setText(student.getDegree());
        studentViewHolder.tvDept.setText(student.getDept());
    }

    @Override
    public int getItemCount() {
        return (studentList != null ? studentList.size() : 0);
    }

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView tvRegno;
        public TextView tvName;
        public TextView tvDegree;
        public TextView tvDept;

        OnItemClickListener mOnItemClickListener;

        public StudentViewHolder(View itemView) {
            super(itemView);
            tvRegno = (TextView) itemView.findViewById(R.id.tv_regno);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvDegree = (TextView) itemView.findViewById(R.id.tv_degree);
            tvDept = (TextView) itemView.findViewById(R.id.tv_dept);
            itemView.setOnClickListener(this);
        }

        public StudentViewHolder(View itemView, OnItemClickListener mOnItemClickListener) {
            this(itemView);
            this.mOnItemClickListener = mOnItemClickListener;
        }

        @Override
        public void onClick(View view) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
