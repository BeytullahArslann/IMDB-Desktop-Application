/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nypProject;

/**
 *
 * @author beytu
 */
public class Movie {
    private int  id;
    private String name;
    private String director;
    private String writers;
    private int ımdbScore;
    private String relaseDate;
    private String type;
    
    public Movie(int id, String name, String director, String writers, int ımdbscore, String relasedate, String type) {
        this.id = id;
        this.name = name;
        this.director = director;
        this.writers = writers;
        this.ımdbScore = ımdbscore;
        this.relaseDate = relasedate;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriters() {
        return writers;
    }

    public void setWriters(String writers) {
        this.writers = writers;
    }

    public int getImdbscore() {
        return ımdbScore;
    }

    public void setImdbscore(int ımdbscore) {
        this.ımdbScore = ımdbscore;
    }

    public String getRelasedate() {
        return relaseDate;
    }

    public void setRelasedate(String relasedate) {
        this.relaseDate = relasedate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    

    
}
