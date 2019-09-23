package com.cg.movies.dto;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "movie")
	public class Movie {
		@Id @GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name="movie_id")
		private Integer movieId;
		@Column(name="movie_name")
		private String movieName;
		@Column(name="movie_genre")
		private String genre;
		@Column(name="movie_director")
		private String director;
		@Column(name="movie_length")
		private Integer movieLength;
		@Column(name="movie_release_date")
		private Date movieReleaseDate;
		@Column(name="movie_language")
		private String language;
		@ManyToMany(cascade = CascadeType.PERSIST)
		@JoinTable(joinColumns = @JoinColumn(name = "movie_fk"), inverseJoinColumns = @JoinColumn(name = "theatre_fk"))
		private List<Theatre> theatre;
		@OneToMany(mappedBy = "movie")
		private List<Show> showsList;
		
		public Movie(Integer movieId, String movieName, String genre, String director, Integer movieLength,
				Date movieReleaseDate, String language, List<Theatre> theatre, List<Show> showsList) {
			super();
			this.movieId = movieId;
			this.movieName = movieName;
			this.genre = genre;
			this.director = director;
			this.movieLength = movieLength;
			this.movieReleaseDate = movieReleaseDate;
			this.language = language;
			this.theatre = theatre;
			this.showsList = showsList;
		}
		public Movie() {
			// TODO Auto-generated constructor stub
		}
		public Integer getMovieId() {
			return movieId;
		}
		public void setMovieId(Integer movieId) {
			this.movieId = movieId;
		}
		public String getMovieName() {
			return movieName;
		}
		public void setMovieName(String movieName) {
			this.movieName = movieName;
		}
		public String getGenre() {
			return genre;
		}
		public void setGenre(String genre) {
			this.genre = genre;
		}
		public String getDirector() {
			return director;
		}
		public void setDirector(String director) {
			this.director = director;
		}
		public Integer getMovieLength() {
			return movieLength;
		}
		public void setMovieLength(Integer movieLength) {
			this.movieLength = movieLength;
		}
		public Date getMovieReleaseDate() {
			return movieReleaseDate;
		}
		public void setMovieReleaseDate(Date movieReleaseDate) {
			this.movieReleaseDate = movieReleaseDate;
		}
		public String getLanguage() {
			return language;
		}
		public void setLanguage(String language) {
			this.language = language;
		}
		public List<Theatre> getTheatre() {
			return theatre;
		}
		public void setTheatre(List<Theatre> theatre) {
			this.theatre = theatre;
		}
		public List<Show> getShowsList() {
			return showsList;
		}
		public void setShowsList(List<Show> showsList) {
			this.showsList = showsList;
		}
		
		

		
	}

