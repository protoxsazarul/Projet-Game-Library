package model;
import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class UsersgamesPK implements Serializable {
    private int idUser;
    private int idGame;

    @Column(name = "idUser")
    @Id
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Column(name = "idGame")
    @Id
    public int getIdGame() {
        return idGame;
    }

    public void setIdGame(int idGame) {
        this.idGame = idGame;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersgamesPK that = (UsersgamesPK) o;
        return idUser == that.idUser &&
                idGame == that.idGame;
    }

    @Override

    public int hashCode() {
        return Objects.hash(idUser, idGame);
    }
}
