package com.jbzh.android.layout;


import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.jbzh.android.view.R;

public class TeacherLeftSidebarV1Layout extends LinearLayout {
//    private  LinearLayout parentView;

    public TeacherLeftSidebarV1Layout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout._teacher_left_sidebar_v1_container, this);
//        parentView = findViewById(R.id.teacher_left_sidebar_v1_container);
    }
}