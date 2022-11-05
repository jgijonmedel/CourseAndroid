package com.jimmy.courseandroid.charactersDbz.characters.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jimmy.courseandroid.R
import com.jimmy.courseandroid.charactersDbz.data.CharacterDbz
import com.jimmy.courseandroid.databinding.ItemCharacterDbzBinding

class CharacterDbzAdapter : RecyclerView.Adapter<CharacterDbzViewHolder>() {

    private var characterList: List< CharacterDbz> = emptyList()

    fun setList(characters: List<CharacterDbz>) {
        characterList = characters
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterDbzViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_character_dbz, parent, false)
        val binding = ItemCharacterDbzBinding.bind(view)
        return CharacterDbzViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterDbzViewHolder, position: Int) {
        val character = characterList[position]
        holder.render(character)
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

}