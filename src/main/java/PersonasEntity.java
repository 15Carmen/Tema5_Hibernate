import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Jugadores")
public class PersonasEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idJugador")
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "numAciertos")
    private int numAciertos;

    public PersonasEntity() {
    }

    public PersonasEntity(String nombre, int numAciertos) {
        this.nombre = nombre;
        this.numAciertos = numAciertos;
    }

    public int getIdJugador() {
        return id;
    }

    public void setIdJugador(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumAciertos() {
        return numAciertos;
    }

    public void setNumAciertos(int numAciertos) {
        this.numAciertos = numAciertos;
    }
}
