package com.example.maksimdimitrov.picssshare.controller

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.example.maksimdimitrov.picssshare.R
import com.example.maksimdimitrov.picssshare.fragments.main_activity.*
import com.example.maksimdimitrov.picssshare.model.DataSource
import com.example.maksimdimitrov.picssshare.model.Post
import com.example.maksimdimitrov.picssshare.model.User
import com.example.maksimdimitrov.picssshare.utilities.EXTRA_USER
import com.example.maksimdimitrov.picssshare.utilities.whenNull
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_bottom_navigation.*


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
        , FavoritesFragment.FavoritesInteractionListener {

    override fun onBottomNavigationItemClick(item: Int) {
        when (item) {
            NAVIGATION_HOME -> replaceFragment(fm.findFragmentByTag(FRAGMENT_FEED)
                    ?: FeedFragment.newInstance(FeedData(user, DataSource.getPosts(user.username))))
            NAVIGATION_SEARCH -> replaceFragment(fm.findFragmentByTag(FRAGMENT_SEARCH)
                    ?: SearchFragment())
            NAVIGATION_UPLOAD -> replaceFragment(fm.findFragmentByTag(FRAGMENT_UPLOAD)
                    ?: UploadFragment())
            NAVIGATION_FAVORITES -> replaceFragment(fm.findFragmentByTag(FRAGMENT_FAVORITES)
                    ?: FavoritesFragment())
            NAVIGATION_ACCOUNT -> replaceFragment(fm.findFragmentByTag(FRAGMENT_ACCOUNT)
                    ?: AccountFragment())
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

    private fun replaceFragment(fragment: Fragment, tag: String? = null) {
        fm.beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
                .replace(fragment_container.id, fragment, tag)
                .commit()
    }

    override fun onBackPressed() {
        val navBar = frag_bottom_navigation.bottom_navigation
        val selectedItemId = navBar.selectedItemId
        if (R.id.nav_btn_home != selectedItemId) {
            navBar.selectedItemId = R.id.nav_btn_home
        } else {
            super.onBackPressed()
        }
    }

    private lateinit var user: User
    private lateinit var fm: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        user = intent.getParcelableExtra(EXTRA_USER)
        fm = supportFragmentManager
        savedInstanceState.whenNull {
            replaceFragment(FeedFragment.newInstance(FeedData(user, DataSource.getPosts(user.username))))
        }
    }

}
