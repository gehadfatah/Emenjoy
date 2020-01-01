package com.goda.emenjoy.presentation.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.goda.emenjoy.R
import com.goda.emenjoy.presentation.common.loadImage
import kotlinx.android.synthetic.main.fragment_slide_result.view.*


private const val ARG_Cess = "cessarg"
private const val ARG_NUM = "num"

class CessResultsFragment : Fragment() {
    val NO_RESERVATIONS = 1
    val DONE = 2
    fun newCessResultsFragment() = CessResultsFragment()

    val videoUrl: String? by lazy { arguments!!.getString(ARG_Cess) }
    val num: Int? by lazy { arguments!!.getInt(ARG_NUM) }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDetail(view)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_slide_result, container, false)

        return view
    }

    private fun setDetail(view: View) {
        /* arguments?.let {
             cessResult = it.getParcelable(ARG_Cess) as CESSResult
             cessResult?.run {

             }
         }*/
        view.apply {
            videoUrl?.run {
                    imageDetail?.apply { loadImage(this, this@run) }


                //indicator.text = num
                num?.let {
                    //if (it.substring(0, 1).equals("1") && it.substring(1, 2).equals("/")) closeImage.visibility = View.VISIBLE
                }
            }
          /*  imageDetail.setOnClickListener {
                //findNavController().navigate(R.id.action_cessResultListsFragment_to_detailFragment) }
                activity?.onBackPressed()
            }*/
        }


    }


    companion object {
        @JvmStatic
        fun newInstance(c: String?, order: Int) =
                CessResultsFragment().apply {
                    arguments = Bundle().apply {
                        //putParcelable(ARG_Cess, c)
                        putString(ARG_Cess, c)
                        putInt(ARG_NUM, order)
                    }
                }
    }
}