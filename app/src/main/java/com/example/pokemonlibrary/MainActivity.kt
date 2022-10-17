package com.example.pokemonlibrary

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.pokemonlibrary.client.PokeApiClient
import com.squareup.picasso.Picasso
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {
    private val pokeApi = PokeApiClient()
    private  var pokemonId : Int = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       getPokemon()
        val button: Button = findViewById<Button>(R.id.nextPkm)
        button.setOnClickListener(object : View.OnClickListener {
            //On click function
            override fun onClick(view: View) {
                pokemonId += 1
                getPokemon()
            }
        })
        //getPokemonCarrousel()
    }

    private fun getPokemon(){
        thread {
            var pokemon =pokeApi.getPokemon(pokemonId)
            val textView: TextView = findViewById<TextView>(R.id.Name)
            val image: ImageView = findViewById<ImageView>(R.id.Photo)
            runOnUiThread {
                textView.text = pokemon.name
                Picasso.get().load(pokemon.sprites.frontDefault).into(image);
            }
        }
    }

    private fun getPokemonCarrousel(){
        thread {
            for (i in 0..4) {
                var pokemon =pokeApi.getPokemon((pokemonId + i))
                val scrollView: HorizontalScrollView = findViewById<HorizontalScrollView>(R.id.PokemonCarrousel)
                val image: ImageView = ImageView(this)

                scrollView.addView(image)
                runOnUiThread {
                    Picasso.get().load(pokemon.sprites.frontDefault).into(image);
                }
            }

        }
    }



}