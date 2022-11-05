package com.jimmy.courseandroid.charactersDbz.characters.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jimmy.courseandroid.charactersDbz.data.CharacterDbz
import com.jimmy.courseandroid.databinding.ItemCharacterDbzBinding
import java.text.DecimalFormat

class CharacterDbzViewHolder(
    private val binding: ItemCharacterDbzBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun render(character: CharacterDbz) {
        binding.tvName.text = character.name
        binding.tvRace.text = "Raza: ${character.race}"
        binding.tvBasePower.text = formatPower(character.basePower)
        Glide
            .with(binding.root.context)
            .load(character.image)
            .into(binding.ivImage)
    }

    private fun formatPower(numPower: Int): String {
        val formatter = DecimalFormat("#,###")
        return "Nivel de poder: ${formatter.format(numPower)}"
    }

}