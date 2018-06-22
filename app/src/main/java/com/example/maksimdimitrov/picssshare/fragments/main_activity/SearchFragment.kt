package com.example.maksimdimitrov.picssshare.fragments.main_activity

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.maksimdimitrov.picssshare.R

class SearchFragment : Fragment() {
    private lateinit var listener: SearchInteractionListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SearchInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement SearchInteractionListener")
        }
    }

    interface SearchInteractionListener {
        fun onSearchItemClick(item : Int)
    }

}
