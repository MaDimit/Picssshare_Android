package com.example.maksimdimitrov.picssshare.controller

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.example.maksimdimitrov.picssshare.R
import com.example.maksimdimitrov.picssshare.fragments.main_activity.*
import com.example.maksimdimitrov.picssshare.model.User
import com.example.maksimdimitrov.picssshare.utilities.EXTRA_USER
import com.example.maksimdimitrov.picssshare.utilities.whenNull
import kotlinx.android.synthetic.main.activity_main.*

const val FRAGMENT_FEED = "feed_fragment"
const val FRAGMENT_SEARCH = "search_fragment"
const val FRAGMENT_UPLOAD = "upload_fragment"
const val FRAGMENT_FAVORITES = "favorites_fragment"
const val FRAGMENT_ACCOUNT = "account_fragment"

class MainActivity : AppCompatActivity()
        , BottomNavigationFragment.BottomNavigationInteractionListener
        , FeedFragment.FeedInteractionListener
        , SearchFragment.SearchInteractionListener
        , UploadFragment.UploadInteractionListener
        , AccountFragment.AccountInteractionListener
        , FavoritesFragment.FavoritesInteractionListener{

    override fun onBottomNavigationItemClick(item: Int) {
        when(item) {
            NAVIGATION_HOME -> replaceFragment(fm.findFragmentByTag(FRAGMENT_FEED) ?: FeedFragment())
            NAVIGATION_SEARCH -> replaceFragment(fm.findFragmentByTag(FRAGMENT_SEARCH) ?: SearchFragment())
            NAVIGATION_UPLOAD -> replaceFragment(fm.findFragmentByTag(FRAGMENT_UPLOAD) ?: UploadFragment())
            NAVIGATION_FAVORITES -> replaceFragment(fm.findFragmentByTag(FRAGMENT_FAVORITES) ?: FavoritesFragment())
            NAVIGATION_ACCOUNT -> replaceFragment(fm.findFragmentByTag(FRAGMENT_ACCOUNT) ?: AccountFragment())
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

    private fun replaceFragment(fragment : Fragment, tag : String? = null){
        fm.beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out,R.anim.fade_in, R.anim.fade_out)
                .replace(fragment_container.id, fragment, tag)
                .addToBackStack(null)
                .commit()
    }

    lateinit var user: User
    private lateinit var fm: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        user = intent.getParcelableExtra(EXTRA_USER)
        fm = supportFragmentManager
        savedInstanceState.whenNull {
            fm.beginTransaction()
                    .replace(fragment_container.id, FeedFragment(), FRAGMENT_FEED)
                    .commit()
        }
    }
}
