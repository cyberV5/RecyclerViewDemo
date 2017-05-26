package com.example.fleming.recyclerviewdemo

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import java.util.*

/**
 * MyAdapter
 * Created by Fleming on 2016/11/15.
 */

class MyAdapter(val dataSet: Array<String?>, val isStaggered: Boolean) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    val mHights: MutableList<Int>

    init {
        mHights = ArrayList<Int>()
        for (i in dataSet.indices) {
            mHights.add((100 + Math.random() * 300).toInt())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val lp = holder.imageView.layoutParams
        if (isStaggered) {
            lp.height = mHights[position]
        } else {
            lp.height = 80
        }
        holder.textView.layoutParams = lp
        holder.textView.text = dataSet[position]

        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener { view ->
                mOnItemClickListener!!.onItemClick(view, holder.adapterPosition)
            }

            holder.itemView.setOnLongClickListener { view ->
                mOnItemClickListener!!.onItemLongClick(view, holder.adapterPosition)
                true
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById(R.id.textView) as TextView
        val imageView = itemView.findViewById(R.id.imageView) as ImageView
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
        fun onItemLongClick(view: View, position: Int)
    }

    var mOnItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mOnItemClickListener = listener
    }
}
