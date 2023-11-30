package com.faqiy.quran.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.faqiy.quran.databinding.ItemSurahBinding
import com.faqiy.quran.core.domain.model.Surah
import com.faqiy.quran.presentation.Quran.DetailSurahActivity

class QuranAdapter : RecyclerView.Adapter<QuranAdapter.MyViewHolder>() {
    val listSurat = ArrayList<Surah>()

    fun setData (list: List<Surah>?){
        if (list == null) return
        listSurat.clear()
        listSurat.addAll(list)
    }

    class MyViewHolder(val binding: ItemSurahBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= MyViewHolder (
        ItemSurahBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun getItemCount() = listSurat.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = listSurat[position]
        holder.binding.apply {
            tvNumber.text = data.number.toString()
            tvName.text = data.name
            tvSurah.text = data.englishName
            val revalation = data.revelationType
            val numberOfAyah = data.numberOfAyahs
            val resultOftextAyah = "$revalation - $numberOfAyah Ayahs"
            tvAyah.text = resultOftextAyah

            this.root.setOnClickListener{
                val intent = Intent(it.context, DetailSurahActivity::class.java)
                intent.putExtra(DetailSurahActivity.EXTRA_DATA, data)
                it.context.startActivity(intent)
            }
        }
    }
}