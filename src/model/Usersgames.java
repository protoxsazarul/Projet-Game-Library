package model;

import com.google.gson.Gson;
import org.hibernate.Session;
import org.hibernate.Transaction;
import persistance.HibernateUtils;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "usersgames")

@IdClass(UsersgamesPK.class)
public class Usersgames {
    @Id
    @Column(name = "idUser")
    private int idUser;

    @Id
    @Column(name = "idGame")
    private int idGame;

    @Basic
    @Column(name = "note")
    private Double note;

    @Basic
    @Column(name = "review")
    private String review;
    @Basic
    @Column(name="playtime")
    private int playtime;
    @ManyToOne
    @JoinColumn(name = "idUser", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private User userByIdUser;

    @ManyToOne
    @JoinColumn(name = "idGame", referencedColumnName = "id", nullable = false,insertable = false, updatable = false)
    private Game gameByIdGame;


    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }


    public int getIdGame() {
        return idGame;
    }

    public void setIdGame(int idGame) {
        this.idGame = idGame;
    }


    public Double getNote() {
        return note;
    }

    public void setNote(Double note) {
        this.note = note;
    }


    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getPlaytime() {
        return playtime;
    }

    public void setPlaytime(int playtime) {
        this.playtime = playtime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usersgames that = (Usersgames) o;
        return idUser == that.idUser &&
                idGame == that.idGame &&
                Objects.equals(note, that.note) &&
                Objects.equals(review, that.review);
    }

    @Override
    public String toString(){
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json ;
    }
    @Override
    public int hashCode() {
        return Objects.hash(idUser, idGame, note, review);
    }


    public User getUserByIdUser() {
        return userByIdUser;
    }

    public void setUserByIdUser(User userByIdUser) {
        this.userByIdUser = userByIdUser;
    }


    public Game getGameByIdGame() {
        return gameByIdGame;
    }

    public void setGameByIdGame(Game gameByIdGame) {
        this.gameByIdGame = gameByIdGame;
    }
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
}
