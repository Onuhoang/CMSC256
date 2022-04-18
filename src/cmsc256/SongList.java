/********************************************************************************
 * SongList
 * Hoang Tran
 * Project 3
 * CMSC 256
 *********************************************************************************
 * This project will be used to search and sort data of songs.
 *********************************************************************************/

package cmsc256;

import bridges.base.SLelement;
import bridges.connect.Bridges;
import bridges.connect.DataSource;
import bridges.data_src_dependent.Song;

import java.util.ArrayList;

public class SongList implements cmsc256.List<bridges.data_src_dependent.Song> {
    public int listSize;
    public SLelement<Song> curr;
    public SLelement<Song> head;
    public SLelement<Song> tail;

    public SongList(int listSize){
        this();
    }

    public SongList(){
        clear();
        Bridge();
    }

    public void Bridge() {
        Bridges bridges = new Bridges(3,"hoangth844" , "497417069475" );

        DataSource ds = bridges.getDataSource();

        ArrayList<Song> songArrayList = null;

        try {

            songArrayList = ds.getSongData();

        } catch (Exception e) {

            System.out.println("There is no songs");

        }
        for(int i = 0; i< songArrayList.size(); i++){
            append(songArrayList.get(i));
        }

    }
    public String getSongsByArtist(String artist) {
       return null;
    }

    @Override
    public void clear() {
        curr = tail = new SLelement<>();      // Create trailer
        head = new SLelement<>(tail);        // Create header
        listSize = 0;
    }

    @Override
    public boolean insert(Song it) {
        curr.setNext(new SLelement<>(curr.getValue(), curr.getNext()));
        curr.setValue(it);
        if (curr == tail)
            tail = curr.getNext();
        listSize++;
        return false;
    }

    @Override
    public boolean append(Song it) {
        tail.setNext(new SLelement<>());
        tail.setValue(it);
        tail = tail.getNext(); //reassign tail to last
        listSize++;

        return false;
    }

    @Override
    public Song remove() {
        if (curr == tail) {
            return tail.getValue();
        }
        Song it = curr.getValue();  // Remember value
        curr.setValue(curr.getNext().getValue());  // Pull forward the next element
        if (curr.getNext() == tail) {
            tail = curr;
        }
        curr.setNext(curr.getNext().getNext()); // Point around unneeded link
        listSize--;
        return it;
    }

    @Override
    public void moveToStart() {
        curr = head.getNext(); //Set curr at head
    }

    @Override
    public void moveToEnd() {
        curr = tail; //set curr to tail
    }

    @Override
    public void prev() {
        if (head.getNext() == curr) {
            return; // No previous element
        }
        SLelement temp = head;
        // March down list until we find the previous element
        while (temp.getNext() != curr) {
            temp = temp.getNext();
        }
        curr = temp;
    }

    @Override
    public void next() {
        if (curr != tail) {
            curr = curr.getNext();
        }
    }

    @Override
    public int length() {
        return listSize;
    }

    @Override
    public int currPos() {
        SLelement temp = head.getNext();
        int i;
        for (i=0; curr != temp; i++) {
            temp = temp.getNext();
        }
        return i;
    }


    @Override
    public boolean moveToPos(int pos) {
        if ((pos < 0) || (pos > listSize)) {
            return false;
        }
        curr = head.getNext();
        for(int i=0; i<pos; i++) { curr = curr.getNext(); }
        return true;
    }


    @Override
    public boolean isAtEnd() {
        return curr == tail;
    }

    @Override
    public Song getValue() {
        return curr.getValue();
    }
}
