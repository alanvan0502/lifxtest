package com.lifx.lifxtest

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lifx.lifxtest.model.Data
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    lateinit var adapter: ListAdapter
    val listData = mutableListOf<Data>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val download = DownloadFileFromURL()
        download.execute("https://cloud.lifx.com/themes/v1/curated")

        adapter = ListAdapter(listData, this)
        list.adapter = adapter
        list.layoutManager = LinearLayoutManager(applicationContext)

        val dividerItemDecoration = DividerItemDecoration(this, VERTICAL)
        list.addItemDecoration(dividerItemDecoration)
    }

    class ListAdapter(private val listData: List<Data>, private val context: Context) :
        RecyclerView.Adapter<ListAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_row, parent, false))
        }

        override fun getItemCount(): Int = listData.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.textView.text = listData[position].title
            Glide.with(context).load(listData[position].imageUrl).into(holder.imageView)
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val textView: TextView = itemView.findViewById(R.id.textView)
            val imageView: ImageView = itemView.findViewById(R.id.image_view)
        }
    }

    inner class DownloadFileFromURL : AsyncTask<String, String, String>() {

        override fun doInBackground(vararg url: String): String? {
            val results =
                JsonParser().makeHttpRequest(url[0])

            runOnUiThread {

                for (i in 0 until (results?.length() ?: 0)) {
                    val result = results?.get(i) as JSONObject
                    val title = result.optString("title", "")
                    val imageUrl = result.optString("image_url", "")
                    listData.add(Data().apply {
                        this.title = title
                        this.imageUrl = imageUrl
                    })
                }
                adapter.notifyDataSetChanged()
            }

            return null
        }
    }
}


