package model;

import com.google.gson.Gson;
import org.hibernate.Session;
import org.hibernate.Transaction;
import persistance.HibernateUtils;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "game")

public class Game {
    private int id;
    private String title;
    private String developpeur;
    private String description;
    private Byte onSteam;
    private Integer appsid;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "developpeur")
    public String getDeveloppeur() {
        return developpeur;
    }

    public void setDeveloppeur(String developpeur) {
        this.developpeur = developpeur;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "onSteam")
    public Byte getOnSteam() {
        return onSteam;
    }

    public void setOnSteam(Byte onSteam) {
        this.onSteam = onSteam;
    }

    @Basic
    @Column(name = "appsid")
    public Integer getAppsid() {
        return appsid;
    }

    public void setAppsid(Integer appsid) {
        this.appsid = appsid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return id == game.id &&
                Objects.equals(title, game.title) &&
                Objects.equals(developpeur, game.developpeur) &&
                Objects.equals(description, game.description) &&
                Objects.equals(onSteam, game.onSteam) &&
                Objects.equals(appsid, game.appsid);

    }
    @Override
    public String toString(){
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json ;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, title, developpeur, description, onSteam, appsid);
    }
/*
    public Game(String title, String developpeur, String description, Byte onSteam, Integer appsid, Integer playTime) {
        this.title = title;
        this.developpeur = developpeur;
        this.description = description;
        this.onSteam = onSteam;
        this.appsid = appsid;
        this.playTime = playTime;
    }*/
    public void save()
    {
        Session session = HibernateUtils.getSession() ;
        Transaction tx = session.beginTransaction() ;
        session.saveOrUpdate(this);
        session.flush();
        tx.commit();
        session.clear();
        System.out.println("Enregistrement réussi : " + this);
    }
    public void delete(){
        Session session = HibernateUtils.getSession();
        Transaction tx = session.beginTransaction();
        session.delete( this);
        session.flush();
        tx.commit();
        session.clear();
        System.out.println("suppression reussi");
    }
}
