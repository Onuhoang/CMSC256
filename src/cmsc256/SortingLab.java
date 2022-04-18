package cmsc256;

import bridges.connect.Bridges;
import bridges.connect.DataSource;
import bridges.data_src_dependent.ActorMovieIMDB;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SortingLab {
    // In the main method, create the Bridges object replacing

    //  the 2nd & 3rd parameters with

    //  your individual Bridges user id and API key
    public static void main(String[] args) {


        Bridges bridges = new Bridges(3,"hoangth844" , "497417069475" );
        DataSource ds = bridges.getDataSource();
        List<ActorMovieIMDB> movieData = null;
        ArrayList<ActorMovieIMDB> filteredMovieList = new ArrayList<>();
        try {

            movieData = ds.getActorMovieIMDBData(Integer.MAX_VALUE);

        } catch (Exception e) {

            System.out.println("Unable to connect to Bridges.");

        }
        for(int i=0;i<5;i++){
            ActorMovieIMDB list=movieData.get(i);
            System.out.println(""+i+"."+list.getActor()+" was in "+list.getMovie());
        }
        for(ActorMovieIMDB movie: movieData){
            if(movie.getMovie().equals("Being_John_Malkovich_(1999)")){
                System.out.println("\t "+ movie.getActor());
                filteredMovieList.add(movie);
            }
        }
        System.out.println("The following actors appeared in the movie, Being John Malkovich:");
        filteredMovieList.sort(new ActorComparator());
        for(ActorMovieIMDB movie:filteredMovieList){
            System.out.println("\t"+movie.getActor());
        }

    }

}
