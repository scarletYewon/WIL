package com.test.myapplication

import android.content.Context
import android.icu.text.Transliterator.Position
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.BaseAdapter
import android.widget.TextView
import java.text.FieldPosition
import java.util.Random

class Topic_Adpater (val context: Context, val UserList: ArrayList<Topic_Item>): BaseAdapter()
    {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from (context).inflate(R.layout.activity_topic_item,null)
        val user = UserList[position]

        val title = view.findViewById<TextView>(R.id.textView6)
        val count = view.findViewById<TextView>(R.id.text_1)
        val write = view.findViewById<TextView>(R.id.text_1_1)
        val like = view.findViewById<TextView>(R.id.text_1_2)


        title.text = user.title
        count.text = "조회수 " + Random().nextInt(100)
        write.text = "댓글 " + Random().nextInt(100)
        like.text = "좋아요 " + Random().nextInt(100)
        return view
    }

    override fun getItem(position: Int): Any {
        return  UserList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

        override fun getCount(): Int {
        return UserList.size
        }
    }

