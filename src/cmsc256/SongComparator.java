package cmsc256;

import bridges.data_src_dependent.Song;

import java.util.Comparator;

public class SongComparator implements Comparator<Song> {

    @Override
    public int compare(Song a1, Song a2) {
        if (a1.getArtist().compareTo(a2.getArtist()) < 0)
            return -1;
        else if (a1.getArtist().compareTo(a2.getArtist()) > 0)
            return 1;

        else {
            if (a1.getAlbumTitle().compareTo(a2.getAlbumTitle()) < 0)
                return -1;

            else if (a1.getAlbumTitle().compareTo(a2.getAlbumTitle()) > 0)
                return 1;

            else {
                if (a1.getSongTitle().compareTo(a2.getSongTitle()) < 0)
                    return -1;
                else if (a1.getSongTitle().compareTo(a2.getSongTitle()) > 0)
                    return 1;
                else return 0;
            }
        }
    }
}
