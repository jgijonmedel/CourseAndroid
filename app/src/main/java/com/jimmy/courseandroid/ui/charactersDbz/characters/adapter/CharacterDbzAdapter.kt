package com.jimmy.courseandroid.ui.charactersDbz.characters.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jimmy.courseandroid.R
import com.jimmy.courseandroid.data.entity.CharacterDbzEntity
import com.jimmy.courseandroid.databinding.ItemCharacterDbzBinding

class CharacterDbzAdapter(
    private val onDeleted: (CharacterDbzEntity)->Unit
) : RecyclerView.Adapter<CharacterDbzViewHolder>() {

    private var characterList: List<CharacterDbzEntity> = emptyList()

    fun setList(characters: List<CharacterDbzEntity>) {
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
        holder.render(character, onDeleted)
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

}