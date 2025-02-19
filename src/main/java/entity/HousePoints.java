package entity;

import javax.persistence.*;

@Entity
@Table(name = "house_points", schema = "hogwarts", catalog = "")
@NamedQuery(name = "Persona.PuntuacionMasAlta", query = "SELECT p.id, p.nombre, h.points FROM person p JOIN housepoints h ON p.id = h.id WHERE h.points = 100")
@NamedQuery(name = "Persona.PuntuacionEntregada", query = "SELECT p.id, p.nombre, h.personByGiver FROM person p JOIN housepoints h ON p.id = h.id WHERE h.personByGiver > 98")
public class HousePoints {
    private int id;
    private Integer points;
    private Person personByGiver;
    private Person personByReceiver;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "points")
    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HousePoints that = (HousePoints) o;

        if (id != that.id) return false;
        if (points != null ? !points.equals(that.points) : that.points != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (points != null ? points.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "giver", referencedColumnName = "id")
    public Person getPersonByGiver() {
        return personByGiver;
    }

    public void setPersonByGiver(Person personByGiver) {
        this.personByGiver = personByGiver;
    }

    @ManyToOne
    @JoinColumn(name = "receiver", referencedColumnName = "id")
    public Person getPersonByReceiver() {
        return personByReceiver;
    }

    public void setPersonByReceiver(Person personByReceiver) {
        this.personByReceiver = personByReceiver;
    }
}
