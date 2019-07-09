package com.abdhilabs.yourcaption.ui


import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.abdhilabs.yourcaption.database.DataBaseHelper
import com.abdhilabs.yourcaption.model.Caption
import com.abdhilabs.yourcaption.R

class AddFragment : Fragment(), View.OnClickListener {

    private val activity = this@AddFragment

    private lateinit var rootView: View

    private lateinit var textInputCaption: EditText

    private lateinit var btnSave: Button

    private lateinit var btnClear:TextView

    private lateinit var databaseHelper: DataBaseHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_add, container, false)
        // Initializing the Views
        initViews()

        //Initializing the listener
        initListeners()

        //Initializing the objects
        initObjects()

        return rootView
    }

    private fun initViews() {
        textInputCaption = rootView.findViewById<View>(R.id.et_caption) as EditText

        btnSave = rootView.findViewById<View>(R.id.btn_save) as Button

        btnClear = rootView.findViewById<View>(R.id.btn_clear) as TextView
    }

    /**
     * Method untuk Initialize Listeners
     */
    private fun initListeners() {
        btnSave!!.setOnClickListener(this)
        btnClear!!.setOnClickListener(this)
    }

    /**
     * Method untuk Initialize Object
     */
    private fun initObjects() {
        databaseHelper = DataBaseHelper(rootView.context)
    }

    /**
     * Implemented method to listen Click on View
     */
    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_save -> postDataToSQLite()
            R.id.btn_clear -> clearCaption()
        }
    }

    private fun clearCaption() {
        Toast.makeText(getActivity(), "Anda click Button Clear", Toast.LENGTH_SHORT).show()
    }

    private fun postDataToSQLite() {
        if (textInputCaption.text?.length!!.equals(0)) {
            Snackbar.make(rootView, "Caption anda kosong", Snackbar.LENGTH_LONG).show()
            return
        } else {
            var caption = Caption(isicaption = textInputCaption!!.text.toString().trim())

            databaseHelper!!.addCaption(caption)

            // Snackbar to show Succes
            Snackbar.make(rootView, "Caption berhasil di simpan", Snackbar.LENGTH_LONG).show()
            empetyCaption()
        }
    }

    /**
     * Method untuk mengosongkan Edit Text (Caption)
     */
    private fun empetyCaption() {
        textInputCaption!!.text = null
    }

}
