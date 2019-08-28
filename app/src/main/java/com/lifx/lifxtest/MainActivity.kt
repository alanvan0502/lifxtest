package com.lifx.lifxtest

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lifx.lifxtest.model.Data
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    lateinit var adapter: ListAdapter
    val listData = mutableListOf<Data>()
    private lateinit var viewModel: AppViewModel
    private var bag: CompositeDisposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(AppViewModel::class.java)

        val dividerItemDecoration = DividerItemDecoration(this, VERTICAL)
        list.addItemDecoration(dividerItemDecoration)

        adapter = ListAdapter(listData, this)
        list.adapter = adapter
        list.layoutManager = LinearLayoutManager(applicationContext)

        bag = CompositeDisposable()
        val disposable = viewModel.loadData().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())?.subscribe({
            adapter.listData = it
            adapter.notifyDataSetChanged()
        }, {
            Log.d("MainActivity", "error loading data")
        })

        disposable?.let {
            bag?.add(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        bag?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }
    }

    class ListAdapter(var listData: List<Data>, private val context: Context) :
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
            val imageView: ImageView = itemView.findViewById(R.id.imageView)
        }
    }
}


