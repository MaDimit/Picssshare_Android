package com.example.maksimdimitrov.picssshare.fragments.main_activity

import android.content.Context
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.maksimdimitrov.picssshare.R
import com.example.maksimdimitrov.picssshare.adapters.FeedAdapter
import com.example.maksimdimitrov.picssshare.model.DateOfBirth
import com.example.maksimdimitrov.picssshare.model.Post
import com.example.maksimdimitrov.picssshare.model.User
import com.example.maksimdimitrov.picssshare.utilities.toast
import kotlinx.android.synthetic.main.fragment_feed.view.*


data class FeedData(val user: User, val dataSet: List<Post>) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readParcelable(User::class.java.classLoader),
            parcel.createTypedArrayList(Post))


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(user, flags)
        parcel.writeTypedList(dataSet)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FeedData> {
        override fun createFromParcel(parcel: Parcel): FeedData {
            return FeedData(parcel)
        }

        override fun newArray(size: Int): Array<FeedData?> {
            return arrayOfNulls(size)
        }
    }
}

const val FEED_DATA = "feed_data"
const val BUNDLE_LIST_STATE = "list_state_bundle"

class FeedFragment : Fragment(), FeedAdapter.FeedAdapterListener {

    override fun onInteraction(view: View, position: Int) {
        context?.toast("$view with position $position is clicked")
    }

    private lateinit var listener: FeedInteractionListener
    private lateinit var rootView: View
    private lateinit var feedData: FeedData
    private lateinit var rv: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        feedData = arguments?.getParcelable(FEED_DATA)
                ?: FeedData(User("null"
                , "null"
                , "null"
                , DateOfBirth(0, 0, 0), false)
                , listOf())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_feed, container, false)
        setRecycler()
        return rootView
    }

    private fun setRecycler() {
        rv = rootView.recycler_view
        rv.adapter = FeedAdapter(feedData)
        rv.layoutManager = LinearLayoutManager(activity)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FeedInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement FeedInteractionListener")
        }
    }

    interface FeedInteractionListener {
        fun onFeedItemClick(item: Int)

    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            val savedRecyclerLayoutState = savedInstanceState.getParcelable<Parcelable>(BUNDLE_LIST_STATE)
            rv.layoutManager?.onRestoreInstanceState(savedRecyclerLayoutState)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(BUNDLE_LIST_STATE, rv.layoutManager?.onSaveInstanceState())
    }

    companion object {
        @JvmStatic
        fun newInstance(feedData: FeedData) =
                FeedFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(FEED_DATA, feedData)
                    }
                }
    }

}
