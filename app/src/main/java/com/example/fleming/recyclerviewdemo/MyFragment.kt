package com.example.fleming.recyclerviewdemo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.*
import android.widget.Toast

/**
 * MyFragment
 * Created by Fleming on 2016/11/15.
 */

class MyFragment : Fragment(), MyAdapter.OnItemClickListener {
    var mRecyclerView: RecyclerView? = null
    var mLayoutManager: RecyclerView.LayoutManager? = null
    var isStaggered: Boolean = false
    lateinit var mAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment, container, false)
        mRecyclerView = view.findViewById(R.id.recycler_view) as RecyclerView
        return view
    }

    override fun onResume() {
        super.onResume()
        changeMode(LINEAR_LAYOUT_MODE)
    }

    fun changeMode(mode: Int) {

        when (mode) {
            LINEAR_LAYOUT_MODE -> {
                mLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                isStaggered = false
            }
            GRIDLAYOUT_MODE -> {
                mLayoutManager = GridLayoutManager(activity, 3, LinearLayoutManager.VERTICAL, false)
                isStaggered = false
            }
            STAGGERED_GRID_LAYOUT_MODE -> {
                mLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                isStaggered = true
            }
        }

        mRecyclerView!!.layoutManager = mLayoutManager
        mRecyclerView!!.setHasFixedSize(true)
        mRecyclerView!!.adapter = MyAdapter(dataSet, isStaggered)
        mAdapter = mRecyclerView!!.adapter as MyAdapter
        mAdapter.setOnItemClickListener(this)
    }

    override fun onItemClick(view: View, position: Int) {
        Toast.makeText(activity, "clicked " + position, Toast.LENGTH_SHORT).show()
    }

    override fun onItemLongClick(view: View, position: Int) {
        Toast.makeText(activity, "long clicked " + position, Toast.LENGTH_SHORT).show()
    }

    val dataSet: Array<String?>
        get() {
            val dataSet = arrayOfNulls<String>(50)
            for (i in dataSet.indices) {
                dataSet[i] = "photo " + i
            }
            return dataSet
        }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater!!.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item!!.itemId

        if (id == R.id.linear) {
            changeMode(LINEAR_LAYOUT_MODE)
            return true
        } else if (id == R.id.grid) {
            changeMode(GRIDLAYOUT_MODE)
            return true
        } else if (id == R.id.staggered) {
            changeMode(STAGGERED_GRID_LAYOUT_MODE)
            return true
        }
        return false
    }

    companion object {
        val LINEAR_LAYOUT_MODE = 0
        val GRIDLAYOUT_MODE = 1
        val STAGGERED_GRID_LAYOUT_MODE = 2
    }
}
