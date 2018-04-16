package edu.mum.cs544.imdb.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.mum.cs544.imdb.domain.EpisodeCharacter;
import edu.mum.cs544.imdb.domain.TVSeries;

public interface EpisodeCharacterDao extends JpaRepository<EpisodeCharacter, Integer> {
	public List<EpisodeCharacter> findDistinceByEpisodeSeasonTvSeriesOrderByArtistName(TVSeries tvSeries);
}
