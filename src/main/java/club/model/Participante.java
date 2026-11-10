package club.model;

import jakarta.persistence.*;

@Entity
@Table(name = "participante")
public class Participante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idparticipante")
    private int id;

    @Column(name = "nombreparticipante", nullable = false, length = 60)
    private String nombre;

    @Column(name = "telefonoparticipante", length = 15)
    private String telefono;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idruta", nullable = false)
    private Ruta ruta;

    public Participante() {}

    public Participante(int id, String nombre, String telefono, Ruta ruta) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.ruta = ruta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }
}