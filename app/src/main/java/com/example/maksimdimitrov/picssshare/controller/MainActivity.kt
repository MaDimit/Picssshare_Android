package com.example.maksimdimitrov.picssshare.controller

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.example.maksimdimitrov.picssshare.R
import com.example.maksimdimitrov.picssshare.fragments.main_activity.*
import com.example.maksimdimitrov.picssshare.model.User
import com.example.maksimdimitrov.picssshare.utilities.EXTRA_USER
import com.example.maksimdimitrov.picssshare.utilities.toast
import kotlinx.android.synthetic.main.activity_main.*


const val FRAGMENT_FEED = "feed_fragment"

class MainActivity : AppCompatActivity()
        , BottomNavigationFragment.BottomNavigationInteractionListener
        , FeedFragment.FeedInteractionListener
        , SearchFragment.SearchInteractionListener
        , UploadFragment.UploadInteractionListener
        , AccountFragment.AccountInteractionListener
        , FavoritesFragment.FavoritesInteractionListener{

    override fun onBottomNavigationItemClick(item: Int) {
        when(item) {
            NAVIGATION_HOME -> toast("Home clicked")
            NAVIGATION_SEARCH -> toast("Search clicked")
            NAVIGATION_UPLOAD -> toast("Upload clicked")
            NAVIGATION_FAVORITES -> toast("Favorites clicked")
            NAVIGATION_ACCOUNT -> toast("Account clicked")
        }
    }

    override fun onFeedItemClick(item: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSearchItemClick(item: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onUploadItemClick(item: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAccountItemClick(item: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFavoritesItemClick(item: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    lateinit var user: User
    private lateinit var fm: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        user = intent.getParcelableExtra(EXTRA_USER)
        fm = supportFragmentManager
        savedInstanceState?.let {
            fm.beginTransaction()
                    .replace(fragment_container.id, FeedFragment(), FRAGMENT_FEED)
                    .commit()
        }
    }
}
