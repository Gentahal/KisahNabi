package com.genta.kisahnabi.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.genta.kisahnabi.data.KisahResponse
import com.genta.kisahnabi.databinding.RowItemKisahBinding
import com.genta.kisahnabi.ui.utils.OnItemClickCallBack

class KisahAdapter : RecyclerView.Adapter<KisahAdapter.MyViewHolder>() {

    private var listNabi = ArrayList<KisahResponse>()

    fun setData(data: List<KisahResponse>?) {
        if (data == null) return
        listNabi.clear()
        listNabi.addAll(data)
    }

    private var onItemClickCallback: OnItemClickCallBack? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallBack){
        this.onItemClickCallback = onItemClickCallback
    }

    class MyViewHolder(val binding: RowItemKisahBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        RowItemKisahBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = listNabi[position]
        holder.binding.apply {
            itemName.text = data.name
            Glide.with(itemImg.context)
                .load(data.imageUrl)
                .apply(RequestOptions())
                .override(500, 500)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
                .into(itemImg)

            holder.itemView.setOnClickListener {
                onItemClickCallback?.onItemClicked(data)
            }
        }
    }

    override fun getItemCount() = listNabi.size
}