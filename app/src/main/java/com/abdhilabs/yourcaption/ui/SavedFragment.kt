package com.abdhilabs.yourcaption.ui


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abdhilabs.yourcaption.adapter.CaptionRecycleAdapter
import com.abdhilabs.yourcaption.database.DataBaseHelper
import com.abdhilabs.yourcaption.model.Caption
import com.abdhilabs.yourcaption.R

class SavedFragment : Fragment() {

    private lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_saved, container, false)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initObjects()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            fragmentManager?.beginTransaction()?.detach(this)?.attach(this)?.commit()
        }
    }

    private fun initViews() {
        recycleViewSaved = rootView.findViewById<View>(R.id.rv_saved) as RecyclerView
    }

    private fun initObjects() {
        listCaption = ArrayList()
        captionRecycleAdapter = CaptionRecycleAdapter(listCaption, rootView.context)

        val mLayoutManager = LinearLayoutManager(context)
        recycleViewSaved.layoutManager = mLayoutManager
        recycleViewSaved.itemAnimator = DefaultItemAnimator()
        recycleViewSaved.setHasFixedSize(true)
        recycleViewSaved.adapter = captionRecycleAdapter
        dataBaseHelper = DataBaseHelper(rootView.context)
        dataBaseHelper.getAllCaption()
        listCaption.clear()
        listCaption.addAll(dataBaseHelper.getAllCaption())

    }
    /**
     * Method for initialized object to be used
     */
    companion object {
        lateinit var dataBaseHelper: DataBaseHelper
        private lateinit var captionRecycleAdapter: CaptionRecycleAdapter
        private lateinit var recycleViewSaved: RecyclerView
        private lateinit var listCaption: ArrayList<Caption>
    }

}
