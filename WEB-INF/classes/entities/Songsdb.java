package entities;

import javax.persistence.*;

@Entity
@Table(name = "songsdb")
public class Songsdb {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SongID", nullable = false)
    private Integer id;

    @Column(name = "SongName")
    private String songName;

    @Column(name = "Duration")
    private Integer duration;

    @Column(name = "Artist")
    private String artist;

    @Column(name = "Score")
    private Integer score;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

}