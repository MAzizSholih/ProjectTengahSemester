package com.mazizs.movies.data

import com.mazizs.movies.R
import com.mazizs.movies.model.Topic

object DataSource {
    val topics = listOf(
        Topic(1, R.string.the_nun_ii, R.string.the_nun_ii_movie_description, 5.7f, R.drawable.the_nun_ii),
        Topic(2, R.string.teenage_mutant_ninja_turtle_mutant_mayhem, R.string.teenage_mutant_ninja_turtle_mutant_mayhem_movie_description,7.3f, R.drawable.teenage_mutant_ninja_turtle_mutant_mayhem),
        Topic(3, R.string.transformers_rise_of_the_beasts, R.string.transformers_rise_of_the_beasts_movie_description,6.1f, R.drawable.transformers_rise_of_the_beasts),
        Topic(4, R.string.insidious_the_red_door, R.string.insidious_the_red_door_movie_description,5.5f, R.drawable.insidious_the_red_door),
        Topic(5, R.string.indiana_jones_and_the_dial_of_destiny, R.string.indiana_jones_and_the_dial_of_destiny_movie_description, 6.7f, R.drawable.indiana_jones_and_the_dial_of_destiny),
        Topic(6, R.string.evil_dead_rise, R.string.evil_dead_rise_movie_description,6.6f, R.drawable.evil_dead_rise),
        Topic(7, R.string.fast_x, R.string.fast_x_movie_description,5.8f, R.drawable.fast_x),
        Topic(8, R.string.oppenheimer, R.string.oppenheimer_movie_description,8.5f, R.drawable.oppenheimer),
        Topic(9, R.string.saw_x, R.string.saw_x_movie_description,7.1f, R.drawable.saw_x),
        Topic(10, R.string.barbie, R.string.barbie_movie_description,7.1f, R.drawable.barbie),
        Topic(11, R.string.blue_beetle, R.string.blue_beetle_movie_description,6.1f, R.drawable.blue_beetle),
        Topic(12, R.string.gran_turismo, R.string.gran_turismo_movie_description,7.2f, R.drawable.gran_turismo),
        Topic(13, R.string.spider_man_across_the_spider_verse, R.string.spider_man_across_the_spider_verse_description,8.7f, R.drawable.spider_man_across_the_spider_verse),
        Topic(14, R.string.ruby_gillman_teenage_kraken, R.string.ruby_gillman_teenage_kraken_movie_description,5.7f, R.drawable.ruby_gillman_teenage_kraken),
        Topic(15, R.string.meg_2_the_trench, R.string.meg_2_the_trench_movie_description,5.1f, R.drawable.meg_2_the_trench)
    )
    fun getMovieById(id: Int): Topic? {
        return topics.find { it.id == id }
    }
}