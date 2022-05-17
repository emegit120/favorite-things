package br.com.threeX.favoritethings.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.threeX.favoritethings.R
import br.com.threeX.favoritethings.domain.entity.DashboardItem

class HomeAdapter(
    private var menuItems: List<DashboardItem>,
    private var clickListener: (DashboardItem) -> Unit
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_splash, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return menuItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(menuItems[position], clickListener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: DashboardItem, clickListener: (DashboardItem) -> Unit) {
            val label = itemView.findViewById<TextView>(R.id.textView)
            val imageView = itemView.findViewById<ImageView>(R.id.textView)

            label.text = item.label

            itemView.setOnClickListener { clickListener(item) }
        }
    }
}