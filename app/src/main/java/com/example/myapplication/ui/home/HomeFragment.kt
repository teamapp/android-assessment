package com.example.myapplication.ui.home

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
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeBinding
import java.text.SimpleDateFormat
import java.util.Date

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: LinearLayout = binding.root

        val imageTemplate = inflater.inflate(R.layout.image_template, null)
        imageTemplate.findViewById<ImageView>(R.id.image_view).background = BitmapDrawable( BitmapFactory.decodeResource(resources, R.drawable._43) )
        imageTemplate.findViewById<TextView>(R.id.textView).text = "12 views"
        var tst = SimpleDateFormat("yy/MM/dd_HHmm")
        var timestamp = tst.format(Date())
        var pictureDate = "$timestamp"
        imageTemplate.findViewById<TextView>(R.id.dateView).text = pictureDate
        root.addView(imageTemplate)


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}