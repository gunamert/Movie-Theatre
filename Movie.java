import java.util.Date;
import java.util.ArrayList;

/**
 * 
 * @author MERT GUNAY
 * @since 15.11.2017
 * @version 1.0
 * @see create a movie class and holding the movies
 * {@code} movie
 * {@value} movie
 *
 */
public class Movie {
	public String name;
	public String director;
	public ArrayList<String> cast =new ArrayList<>();
	public ArrayList<String> type =new ArrayList<>();
	public ArrayList<Date> dates =new ArrayList<Date>();
	public int metascore;	
	public Movie(){		
	}
	public Movie(Movie movie){
		name=movie.name;
		director=movie.director;
		cast=movie.cast;
		type=movie.type;
		dates=movie.dates;
		metascore=movie.metascore;
	}
	/**
	 * {@code} movie
	 * {@value} printing the movie 
	 * @see take the movie and print it
	 */
	public String toString(){	
		return String.format("Name: %s\nDirector: %s\nCast: %s\nType: %s\nMetascore: %d\n",name,director,cast,type,metascore);
	}
}
