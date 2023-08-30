package com.example.myapplication.ui.photos

import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentPhotosBinding
import com.example.myapplication.ui.article.ArticleFragment
import com.example.myapplication.ui.article.ArticleViewModel
import java.text.SimpleDateFormat
import java.util.Date

class PhotosFragment : Fragment() {

    private var _binding: FragmentPhotosBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotosBinding.inflate(inflater, container, false)
        val root: LinearLayout = binding.root

        var images = listOf(BitmapFactory.decodeResource(resources, R.drawable._43), BitmapFactory.decodeResource(resources, R.drawable._44), BitmapFactory.decodeResource(resources, R.drawable._73))

        images.forEachIndexed { index, bitmap ->
            val imageTemplate = inflater.inflate(R.layout.image_template, null)
            imageTemplate.findViewById<ImageView>(R.id.image_view).background = BitmapDrawable(bitmap)
            imageTemplate.findViewById<TextView>(R.id.textView).text = imageName(index)
            imageTemplate.findViewById<TextView>(R.id.likeView).text = "$index likes"
            var tst = SimpleDateFormat("yy/MM/dd_HHmm")
            var timestamp = tst.format(Date())
            var pictureDate = "$timestamp"
            imageTemplate.findViewById<TextView>(R.id.dateView).text = pictureDate
            imageTemplate.setOnClickListener {
                (activity as MainActivity).setUrl(articleContentUrl(index))
                (activity as MainActivity).setImages(images, index)
                root.findNavController().navigate(R.id.navigation_notifications)
            }
            root.addView(imageTemplate)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun imageName(index : Int) : String{
        return when(index){
            1 -> getString(R.string.first_photo)
            2 -> getString(R.string.second_photo)
            else -> getString(R.string.third_photo)
        }
    }

    fun articleContentUrl(index : Int) : String{
        return when(index){
            1 -> "https://baconipsum.com/api/?type=meat-and-filler"
            2 -> "https://baconipsum.com/api/?type=all-meat&paras=2&start-with-lorem=1"
            else -> "https://baconipsum.com/api/?type=all-meat&sentences=1&start-with-lorem=1"
        }
    }
}