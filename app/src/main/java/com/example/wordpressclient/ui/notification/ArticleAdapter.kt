package com.example.wordpressclient.ui.notification

import android.graphics.Color
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wordpressclient.R

class ArticleAdapter(private val articles: List<Article>) :
    RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(val layout: LinearLayout) : RecyclerView.ViewHolder(layout) {
        val img = ImageView(layout.context)
        val tvCategory = TextView(layout.context)
        val tvTitle = TextView(layout.context)
        val tvTime = TextView(layout.context)
        val bookmark = ImageView(layout.context)

        init {
            layout.orientation = LinearLayout.HORIZONTAL
            layout.setPadding(32, 32, 32, 32)

            // Thumbnail
            img.layoutParams = LinearLayout.LayoutParams(160, 160).apply {
                marginEnd = 32
            }
            layout.addView(img)

            // Nội dung
            val content = LinearLayout(layout.context).apply {
                orientation = LinearLayout.VERTICAL
                layoutParams = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f)
            }

            tvCategory.textSize = 12f
            tvCategory.setTextColor(Color.parseColor("#666666"))

            tvTitle.textSize = 15f
            tvTitle.setTypeface(tvTitle.typeface, android.graphics.Typeface.BOLD)
            tvTitle.setTextColor(Color.BLACK)
            tvTitle.maxLines = 2

            tvTime.textSize = 12f
            tvTime.setTextColor(Color.parseColor("#999999"))

            content.addView(tvCategory)
            content.addView(tvTitle)
            content.addView(tvTime)

            layout.addView(content)

            // Bookmark
            bookmark.layoutParams = LinearLayout.LayoutParams(64, 64)
            bookmark.setImageResource(R.drawable.ic_bookmark_border)
            layout.addView(bookmark)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val layout = LinearLayout(parent.context)
        return ArticleViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        holder.img.setImageResource(article.imageRes)
        holder.tvCategory.text = article.category
        holder.tvTitle.text = article.title
        holder.tvTime.text = article.time
    }

    override fun getItemCount(): Int = articles.size
}

