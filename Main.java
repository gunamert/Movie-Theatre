import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 
 *@author MERT GUNAY
 *@since 29.11.2017
 *@version 1.0
 *@see this program take a movie file and reading the movies and throw the movie class then made a menu.The user select his choice and program made the choice.
 *{@value} movies.txt user_inputs
 *{@code} list movies-find mocie-highest rated movie- watchlist- add watchlist
 */

public class Main {

	public static void main(String args[])throws Exception{
		java.io.File file = new java.io.File("movies.txt");
		Scanner input = new Scanner(file);
		Movie movie=new Movie();
		ArrayList<Movie> movieList=new ArrayList<Movie>();
		ArrayList<Movie> watchlist=new ArrayList<Movie>();
		Scanner scan=new Scanner(System.in);
		int counter=0;
		boolean c=true;
		boolean c2=true;
		String [] date=null;
		String [] time=null;
		while(input.hasNextLine())
		{
			String line = input.nextLine();
			if(counter==0){
				movie = new Movie();
				movie.name=line.replaceAll("Movie: ","").trim();
				counter++;
			}else if(counter==1){
				movie.director=line.replaceAll("Director: ", "").trim();
				counter++;
			}else if(counter==2){
				String temp=line.replaceAll("Cast: ", "").trim();
				movie.cast=new ArrayList<>(Arrays.asList(temp.split(",")));
				counter++;
			}else if(counter==3){
				String temp=line.replaceAll("Type: ", "").trim();
				movie.type=new ArrayList<>(Arrays.asList(temp.split(",")));			
				counter++;
			}else if(counter==4){
				String temp=line.replaceAll("Date:","").trim();
				String mtime=input.nextLine().replaceAll("Time:","").replaceAll(",","").trim();
				time=mtime.split(" ");
				String format="MMMM dd yyyy h:mm";
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				if(temp.contains("November")){
					String temp2=temp.replaceAll("November","").trim();
					date=temp2.split(" ");
					for(int i=0;i<date.length;i++){
						for(int k=0;k<time.length;k++){
							String stime="November "+date[i]+" 2017 "+time[k];
							Date date2 = sdf.parse(stime);
							movie.dates.add(date2);	
						}
					}	
				}
				else if(temp.contains("December")){
					String temp2=temp.replaceAll("December","").trim();
					date=temp2.split(" ");
					for(int i=0;i<date.length;i++){
						for(int k=0;k<time.length;k++){
							String stime="December "+date[i]+" 2017 "+time[k];
							Date date2 = sdf.parse(stime);
							movie.dates.add(date2);
						}
					}
				}	
				counter++;
			}
			else if(counter==5){
				movie.metascore=Integer.parseInt(line.replaceAll("Metascore: ", "").trim());
				Movie addmovie=new Movie(movie);	
				movieList.add(addmovie);
				counter++;
			}
			else if(counter==6){
				counter=0;
			}	
		}
		while(c){
			System.out.println("---------------------");
			System.out.println("[1] List All Movies\n[2] Find Movie\n[3] Highest Rated Movies\n[4] Movies in Three Hours\n[5] Add to Watchlist\n[6] Display Watchlist\n[7] See Best Metascore Movie\n[q] Quit");
			System.out.println("---------------------");
			System.out.print("ENTER YOUR CHOICE: ");
			String x;
			x=scan.next();
			c2=true;
			switch(x){
			case "1":
				for(Movie z : movieList){
					System.out.println(z.toString());
				}
				break;
			case "2":
				while(c2){
					System.out.println("---------------------");
					System.out.println("[1] Find by name\n[2] Find by director\n[3] Find by cast\n[4] Find by type\n[b] Back to the Main Menu");
					System.out.println("---------------------");
					System.out.println("ENTER YOUR CHOICE: ");
					String t;
					t =scan.next();
					switch(t){
					case "1":
						System.out.println("Find by movie name. Enter at least three characters");
						System.out.println("ENTER MOVİE NAME > ");
						String choice0=scan.next();
						if(choice0.length()<3){
							System.out.println("Write at least 3 characters");
						}
						else{
							for(Movie z : movieList){
								if(z.name.contains(choice0)){
									System.out.println(z.toString());	
								}
							}
						}
						break;
					case "2":
						System.out.println("ENTER DIRECTOR NAME > ");
						String choice1=scan.next();
						for(Movie z : movieList){
							if(z.director.contains(choice1)){
								System.out.println(z.toString());	
							}	
						}
						break;
					case "3":
						System.out.println("ENTER CAST NAME > ");
						String choice2=scan.next();
						for(Movie z : movieList){
							for(int i=0;i<z.cast.size();i++){
								if(z.cast.get(i).contains(choice2)){
									System.out.println(z.toString());	
								}	
							}
						}
						break;
					case "4":
						System.out.println("ENTER TYPE NAME > ");
						String choice3=scan.next();
						for(Movie z : movieList){
							for(int i=0;i<z.type.size();i++){
								if(z.type.contains(choice3)){
									System.out.println(z.toString());	
								}	
							}
						}
						break;
					case "b":
						c2=false;
						break;
					}
				}
				break;
			case "3":
				System.out.println("ENTER MINIMUM METASCORE >");
				int choice4=scan.nextInt();
				for(Movie q : movieList){
					if(q.metascore>=(choice4)){
						System.out.println(q.toString());
					}
				}
				if(choice4>88){
					System.out.println("no movie found");
				}
				break;
			case "4":
				Date curDate = new Date();
				for( Movie g : movieList){
					for(int k=1;k<g.dates.size();k++){
						long diff=(g.dates.get(k).getTime() -curDate.getTime())/(1000*60);
						if((g.dates.get(k).getTime() -curDate.getTime())/(1000*60)<=180 && (g.dates.get(k).getTime() -curDate.getTime())/(1000*60)>=0){
							System.out.println(g.name +"  > "+ g.dates.get(k)+" >  starts in "+ diff + " minutes");
						}		
					}
				}
				break;
			case "5":
				int count=1;
				int select;
				File files = new File("WatchList.txt");
				FileWriter fileWriter = new FileWriter(files,true);
				BufferedWriter bWriter =new BufferedWriter(fileWriter);
				for(Movie k : movieList){
					System.out.println(count+". "+k.name);
					count++;
				}
				System.out.println("ENTER SELECTION > ");
				select=scan.nextInt();
				bWriter.write(movieList.get(select-1).toString());
				bWriter.write("\n");
				System.out.println(movieList.get(select-1).name+ " is added your watchlist.");
				bWriter.close();
				break;
			case "6":
				System.out.println("Movies in the watchlist: \n");
				File filess =new File("WatchList.txt");
				BufferedReader reader = new  BufferedReader(new FileReader(filess));
				String Satir=reader.readLine();
				while(Satir!=null){
					System.out.println(Satir);
					Satir=reader.readLine();
				}
				break;
			case "7":
				int highScore=0;
				Movie admovie=new Movie(movie);
				for(Movie p : movieList){
					if(p.metascore>=highScore){
						highScore=p.metascore;
						admovie=p;
					}
				}
				System.out.println(admovie.toString());
				break;
			case "q":
				System.out.println("Quitting...");
				c=false;
				break;
			}
		}
	}
}
