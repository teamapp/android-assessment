package com.example.myapplication.ui.article

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentArticleBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class ArticleFragment : Fragment() {

    private var _binding: FragmentArticleBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        val root: LinearLayout = binding.root

        val imageTemplate = inflater.inflate(R.layout.article_template, null)
        imageTemplate.findViewById<ImageView>(R.id.image_view).background = BitmapDrawable((activity as MainActivity).getImages().get((activity as MainActivity).getIndex().toInt()))
        imageTemplate.findViewById<TextView>(R.id.textView).text = (activity as MainActivity).getUrl()
        root.addView(imageTemplate)
        CoroutineScope(Dispatchers.IO).launch{
            val text = getContent((activity as MainActivity).getUrl())
            (activity as MainActivity).runOnUiThread {
                imageTemplate.findViewById<TextView>(R.id.textView).text = text
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun getContent(uri: String) : String{
        val url: URL
        var server_response = "no content"
        var urlConnection: HttpURLConnection? = null
        try {
            url = URL(uri)
            urlConnection = url.openConnection() as HttpURLConnection

            val responseCode = urlConnection.responseCode

            if (responseCode == HttpURLConnection.HTTP_OK) {
                server_response = readStream(urlConnection.inputStream).toString()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            urlConnection?.disconnect()
            return server_response
        }
    }

    private fun readStream(`in`: InputStream): String? {
        var reader: BufferedReader? = null
        val response = StringBuffer()
        try {
            reader = BufferedReader(InputStreamReader(`in`))
            var line: String? = ""
            while (reader.readLine().also { line = it } != null) {
                response.append(line)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (reader != null) {
                try {
                    reader.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        return response.toString()
    }

}