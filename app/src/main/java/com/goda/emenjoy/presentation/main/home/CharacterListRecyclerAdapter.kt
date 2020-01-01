package com.goda.emenjoy.presentation.main.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.goda.emenjoy.R
import com.goda.emenjoy.model.data_model.Video
import com.goda.emenjoy.presentation.widgets.paginatedRecyclerView.PaginationAdapter
import com.goda.emenjoy.presentation.common.RecyclerViewCallback
import com.goda.emenjoy.presentation.common.loadImage
import kotlinx.android.synthetic.main.video_list_item.view.*


private const val DECIMALS_FIAT = 4
private const val DECIMALS_BTC = 7
private const val DECIMALS_CHANGE = 2

class CharacterListRecyclerAdapter(private var clickCallback: RecyclerViewCallback = object : RecyclerViewCallback {}) : PaginationAdapter<Video>() {

    override fun addLoadingViewFooter() {
       // addLoadingViewFooter(emptyVideoViewModel)
    }

    override fun onBindItemViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder is VideoViewHolder) holder.bind(clickCallback as VideoCallback, dataList[position])
    }

    override fun onCreateItemViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.video_list_item, parent, false)
        return VideoViewHolder(view)
    }

    fun updateData(newData: List<Video>) {
        val fromIndex = dataList.size
        dataList = newData.toMutableList()
        notifyItemRangeInserted(fromIndex, newData.size)
    }
}

class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val resources by lazy { itemView.context.resources }

    fun bind(clickCallback: VideoCallback, item: Video) {
        itemView.apply {
            setOnClickListener{
                item.views++
                clickCallback.videoClicked(item)
                clickCallback.viewDone(item)

            }
            likeImage.setOnClickListener {
                if (!item.liked){
                    likeImage.setImageDrawable(context!!.resources.getDrawable(R.drawable.ic_like_svg))
                      item.liked=true
                    item.likes++
                }
                else {
                    likeImage.setImageDrawable(context!!.resources.getDrawable(R.drawable.like))
                    item.liked=false
                    item.likes--

                }

                clickCallback.likeClicked(item)
            }
            //item.
            charactrImage.apply {
                loadImage(this, item.thumb_nail)
            }
            profile_image.apply {
                loadImage(this, item.artist.image)
            }
            category.text = item.category.nameCategory
            numViews.text = item.views.toString()
            numLikes.text = item.likes.toString()
            title.text = item.title
            artist.text = item.artist.nameArtist

        }
    }


}
