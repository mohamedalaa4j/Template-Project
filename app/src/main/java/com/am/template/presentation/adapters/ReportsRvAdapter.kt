package com.am.template.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.am.template.R
import com.am.template.data.local.models.ReportModel
import com.am.template.databinding.ItemReportBinding
import javax.inject.Inject


class ReportsRvAdapter @Inject constructor() : ListAdapter<ReportModel, ReportsRvAdapter.ProductViewHolder>(
    PRODUCT_COMPARATOR
) {
    private lateinit var listener: OnItemClickListener
    private var favPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProductViewHolder(
        ItemReportBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        getItem(position).let { holder.bind(it) }
    }

    inner class ProductViewHolder(private val binding: ItemReportBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                    getItem(bindingAdapterPosition).let {
                        listener.onReportClick(it)
                    }
                }
            }
        }

        fun bind(model: ReportModel) {
            binding.apply {
                itemReportTvName.text = model.title
                itemReportIvImage.setImageDrawable(model.image)
                itemReportTvPrice.text = itemView.context.getString(R.string.s_sr, model.price.toString())

            }
        }
    }

    fun setListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    interface OnItemClickListener {
        fun onReportClick(model: ReportModel)
    }

    //check difference
    companion object {
        private val PRODUCT_COMPARATOR = object : DiffUtil.ItemCallback<ReportModel>() {
            override fun areItemsTheSame(
                oldItem: ReportModel,
                newItem: ReportModel
            ) = oldItem.title == newItem.title

            override fun areContentsTheSame(
                oldItem: ReportModel,
                newItem: ReportModel
            ) = oldItem == newItem
        }
    }
}