package edu.mum.cs544.imdb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.cs544.imdb.dao.EpisodeCharacterDao;
import edu.mum.cs544.imdb.dao.PersonDao;
import edu.mum.cs544.imdb.dao.SeasonDao;
import edu.mum.cs544.imdb.dao.TVSeriesDao;
import edu.mum.cs544.imdb.domain.EpisodeCharacter;
import edu.mum.cs544.imdb.domain.Genre;
import edu.mum.cs544.imdb.domain.Person;
import edu.mum.cs544.imdb.domain.SearchCriteria;
import edu.mum.cs544.imdb.domain.Season;
import edu.mum.cs544.imdb.domain.TVSeries;

@Service
@Transactional
public class TVSeriesService {
	@Autowired
	private TVSeriesDao tvSeriesDao;
	
	@Autowired
	private EpisodeCharacterDao episodeCharacterDao;	
	
	@Autowired
	private SeasonDao seasonDao;
	
	@Autowired
	private PersonDao personDao;	
	
	public List<TVSeries> search(SearchCriteria criteria) {
		if ("series_name".equals(criteria.getCriteriaType())) {
			return tvSeriesDao.findByName(criteria.getCriteria());
		} else if ("series_genre".equals(criteria.getCriteriaType())) {
			try {
				return tvSeriesDao.findByGenre(Genre.valueOf(criteria.getCriteria()));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return new ArrayList<>();
		} else if ("artist_name".equals(criteria.getCriteriaType())) {
			return tvSeriesDao.findBySeasonsEpisodesCharactersArtistNameLike(criteria.getCriteria());
		} else if ("character_name".equals(criteria.getCriteriaType())) {
			return tvSeriesDao.findBySeasonsEpisodesCharactersNameLike(criteria.getCriteria());
		} else if ("director_name".equals(criteria.getCriteriaType())) {
			return tvSeriesDao.findByDirectorNameLike(criteria.getCriteria());
		}
		return null;
	}
	
	public List<TVSeries> findAll() {
		return tvSeriesDao.findAll();
	}
	
	public TVSeries findById(int id) {
		TVSeries tvSeries =  tvSeriesDao.findOne(id);
		if (tvSeries != null) {
			tvSeries.setCasts(getCasts(tvSeries));
		}
		return tvSeries;
	}
	
	public List<EpisodeCharacter> getCasts(TVSeries tvSeries) {
		return episodeCharacterDao.findDistinceByEpisodeSeasonTvSeriesOrderByArtistName(tvSeries);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public Season updateSeason(Season season) {
		if (season != null && season.getId() != 0) {
			return seasonDao.save(season);
		}
		return season;
	}
	
	public Season findSeasonById(int id) {
		return seasonDao.findOne(id);
	}
	
	public Person findPersonById(int id) {
		return personDao.findOne(id);
	}	
}
