package com.goda.ikhair.presentation.main.home

import android.R.attr.path
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.MediaController
import android.widget.TableLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.goda.ikhair.R
import com.goda.ikhair.model.data_model.Ad
import com.goda.ikhair.model.data_model.Slider
import com.goda.ikhair.model.data_model.Video
import com.goda.ikhair.presentation.common.isOnline
import com.goda.ikhair.presentation.common.loadImage
import com.goda.ikhair.presentation.common.showMessage
import com.goda.ikhair.presentation.main.home.CessResultsFragment.Companion.newInstance
import com.goda.ikhair.presentation.widgets.paginatedRecyclerView.PaginationScrollListener
import kotlinx.android.synthetic.main.fragment_slide_result.*
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.home_fragment.view.*


val HomeFragment_FRAGMENT_TAG = HomeFragment::class.java.name


fun newHomeFragment() = HomeFragment()

class HomeFragment : Fragment(), VideoCallback {

    private lateinit var viewModel: CharacterListViewModel

    private val characterListAdapter by lazy { CharacterListRecyclerAdapter(this) }
    private var isLoading = false
    private var isLastPage = false
    lateinit var view6: View
    lateinit var mediaController: MediaController

    private val stateObserver = Observer<CharacterListState> { state ->
        state?.let {
            isLastPage = state.loadedAllItems
            when (state) {
                is DefaultState -> {
                    isLoading = false
                    swipeRefreshLayout.isRefreshing = false

                    characterListAdapter.updateData(it.data)
                }
                is LoadingState -> {
                    swipeRefreshLayout.isRefreshing = true
                    isLoading = true
                }
                is PaginatingState -> {
                    isLoading = true
                }
                is ErrorState -> {
                    isLoading = false
                    swipeRefreshLayout.isRefreshing = false
                    characterListAdapter.removeLoadingViewFooter()
                    showMessage((it as ErrorState).error)
                }
            }
        }
    }


    private val adObserver = Observer<Ad> {
        it?.let {
            adImage.loadImage(adImage, it.image)

        }
    }

    private val slidersObserver = Observer<List<Slider>> {
        it?.let {
            setupViewPager(it)

        }
    }
    private val videoDAtabasesObserver = Observer<List<Video>> {
        it?.let {
            characterListAdapter.updateData(it)

        }
    }

    private fun setupViewPager(it: List<Slider>) {
        val adapter = ViewPagerAdapter(childFragmentManager)
        var count = 1
        val str = StringBuilder()
        //this can be any container tablelayout or any viewgroup
        val tabParams = TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT)
        val tableLayout = TableLayout(activity)
        tableLayout.layoutParams = tabParams
       // lin.removeAllViews()
        for (item in it.iterator()) {
            //adapter.addFragment(newCessResultsFragment(), "")
            str.clear()
            adapter.addFragment(
                    newInstance(
                            item.video
                            , count++), ""
            )
            // var rowView = View(activity)
            //rowView = View.inflate(activity,R.layout.circle,view6.lin)

        }
        addIndicatior(it.size,0)

        viewpager.adapter = adapter
        viewpager.clipChildren = false
        viewpager.clipToPadding = false
        val margin = 32
        val padding = 40
        viewpager.pageMargin = margin
        viewpager.setPadding(padding * 2, 0, padding * 2, 0)
        viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }
            override fun onPageSelected(position: Int) {
                addIndicatior(it.size,viewpager.currentItem)

            }

        })
        viewpager.setPageTransformer(false, ViewPager.PageTransformer() { page, position ->


            when (viewpager.currentItem) {
                0 -> {
                    page.translationX = (-padding).toFloat()
                }
                adapter.count - 1 -> {
                    page.translationX = padding.toFloat()
                }
                else -> {
                    page.translationX = 0f
                }
            }
            /* val MIN_SCALE = 0.65f
             val MIN_ALPHA = 0.3f
             if (position < -1) { // [-Infinity,-1)
 // This page is way off-screen to the left.
                 page.alpha = .3f
             } else if (position <= 1) { // [-1,1]
                 page.scaleX = Math.max(MIN_SCALE, 1 - Math.abs(position))
                 page.scaleY = Math.max(MIN_SCALE, 1 - Math.abs(position))
                 page.alpha = Math.max(MIN_ALPHA, 1 - Math.abs(position))
             } else { // (1,+Infinity]
 // This page is way off-screen to the right.
                 page.alpha = .3f
             }*/
        }/*ZoomOutTransformation()*/)

    }

    fun addIndicatior(count: Int, positon: Int) {
        lin.removeAllViews()
        for (i in 0 until count) {
            val imageView: ImageView = ImageView(context)

            // Creating a LinearLayout.LayoutParams object for text view
            var params: ViewGroup.LayoutParams = ViewGroup.LayoutParams(
                   20, // This will define text view width
                    20// This will define text view height
            )
            if (i == positon)
                imageView.setImageDrawable(resources.getDrawable(R.drawable.selec))
            else imageView.setImageDrawable(resources.getDrawable(R.drawable.selnot))

            imageView.layoutParams = params
            lin.addView(imageView)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CharacterListViewModel::class.java)
        observeViewModel()
        savedInstanceState?.let {
            viewModel.restoreCryptoList()
        } ?: viewModel.updateCryptoList()
    }

    private fun searchConfigClickListner(view: View) {
        /* view.searchimg.setOnClickListener {
             *//*  searchimg.visibility = View.GONE
              RelafterSearch.visibility = View.VISIBLE
              cancelTv.visibility = View.VISIBLE*//*
            findNavController().navigate(CharacterListFragmentDirections.actionCharactersToSearch())

        }*/


    }


    private fun observeViewModel() {
        if (!context!!.isOnline()){
            viewModel.getvideosDataLiveData()
            viewModel.videosDataLiveData.observe(this, videoDAtabasesObserver)

        }

        viewModel.stateLiveData.observe(this, stateObserver)
        viewModel.adLiveData.observe(this, adObserver)
        viewModel.sliderLiveData.observe(this, slidersObserver)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.home_fragment, container, false)



        searchConfigClickListner(view)
        initializeRecyclerView(view)
        initializeSwipeToRefreshView(view)
        this.view6 = view
        return view
    }


    private fun initializeRecyclerView(view: View) {
        val linearLayoutManager = LinearLayoutManager(context)
        view.recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = characterListAdapter
            addOnScrollListener(OnScrollListener(linearLayoutManager))
        }
    }

    private fun initializeSwipeToRefreshView(view: View) {
        view.swipeRefreshLayout.setOnRefreshListener { viewModel.resetCryptoList() }
    }

    private fun loadNextPage() {
        characterListAdapter.addLoadingViewFooter()
        viewModel.updateCryptoList()
    }


    inner class OnScrollListener(layoutManager: LinearLayoutManager) : PaginationScrollListener(layoutManager) {
        override fun isLoading() = isLoading
        override fun loadMoreItems() = loadNextPage()
        override fun getTotalPageCount() = 3
        override fun isLastPage() = isLastPage
    }

    override fun likeClicked(v: Video) {
       viewModel.setupdateVideoInDatabase(v)
    }

    override fun viewDone(v: Video) {
        viewModel.setupdateVideoInDatabase(v)

    }

    override fun videoClicked(v: Video) {
        videoview.visibility=View.VISIBLE
        closeImage.visibility=View.VISIBLE
        videoview.setVideoPath(v.video)
        videoview.start()
        mediaController = MediaController(context)
        mediaController.setAnchorView(videoview)
        videoview.setMediaController(mediaController)

        videoview.setOnPreparedListener(MediaPlayer.OnPreparedListener {
            //progressbar.visibility = View.GONE
        })
        closeImage.setOnClickListener {
            videoview.visibility=View.GONE
            closeImage.visibility=View.GONE
        }
        videoview.setOnCompletionListener {
            videoview.visibility=View.GONE
            closeImage.visibility=View.GONE
        }
    }


}