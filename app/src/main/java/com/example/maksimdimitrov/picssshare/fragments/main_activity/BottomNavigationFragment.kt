package com.example.maksimdimitrov.picssshare.fragments.main_activity

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.maksimdimitrov.picssshare.R
import kotlinx.android.synthetic.main.fragment_bottom_navigation.view.*

const val NAVIGATION_HOME = 0
const val NAVIGATION_SEARCH = 1
const val NAVIGATION_UPLOAD = 2
const val NAVIGATION_FAVORITES = 3
const val NAVIGATION_ACCOUNT = 4

class BottomNavigationFragment : Fragment() {
    private lateinit var listener: BottomNavigationInteractionListener
    private lateinit var rootView : View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_bottom_navigation, container, false)
        setItemClickListener()
        return rootView
    }

    private fun setItemClickListener() {
        val lCall = {item : Int -> listener.onBottomNavigationItemClick(item)}
        rootView.bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_btn_home -> lCall(NAVIGATION_HOME)
                R.id.nav_btn_search -> lCall(NAVIGATION_SEARCH)
                R.id.nav_btn_upload -> lCall(NAVIGATION_UPLOAD)
                R.id.nav_btn_favorite -> lCall(NAVIGATION_FAVORITES)
                R.id.nav_btn_account -> lCall(NAVIGATION_ACCOUNT)
            }
            return@setOnNavigationItemSelectedListener true
        }
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BottomNavigationInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement BottomNavigationInteractionListener")
        }
    }

    interface BottomNavigationInteractionListener {
        fun onBottomNavigationItemClick(item : Int)
    }

}
