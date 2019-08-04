package model;

import com.google.gson.Gson;
import org.hibernate.Session;
import org.hibernate.Transaction;
import persistance.HibernateUtils;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user")

public class User {
    private int id;
    private String userName;
    private String password;
    private String email;
    private String steamid;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "userName")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "Steamid")
    public String getSteamid() {
        return steamid;
    }

    public void setSteamid(String steamid) {
        this.steamid = steamid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(steamid, user.steamid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, password, email, steamid);
    }
    @Override
    public String toString(){
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json ;
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
