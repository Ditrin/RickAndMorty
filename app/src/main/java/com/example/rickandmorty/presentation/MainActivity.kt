package com.example.rickandmorty.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.ActivityMainBinding
import com.example.rickandmorty.presentation.fragments.CharactersFragment
import com.example.rickandmorty.presentation.fragments.EpisodesFragment
import com.example.rickandmorty.presentation.fragments.LocationsFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_RICKAndMORTY)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentCharacter = CharactersFragment()
        val fragmentLocations = LocationsFragment()
        val fragmentEpisodes = EpisodesFragment()
        setCurrentFragment(fragmentCharacter)
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.characters -> setCurrentFragment(fragmentCharacter)
                R.id.location -> setCurrentFragment(fragmentLocations)
                R.id.episodes -> setCurrentFragment(fragmentEpisodes)
            }
            true
        }
    }

    fun setCurrentFragment(fragment: Fragment) = supportFragmentManager.beginTransaction().apply {
        replace(R.id.fragmentContainer, fragment)
        commit()
    }
}