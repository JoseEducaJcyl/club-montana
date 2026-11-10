package club.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ruta")
public class Ruta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String nombreruta;
    @Enumerated(EnumType.STRING)
    @Column
    private Dificultad dificultad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idsocio")  // solo para mostrar (viene del JOIN con socio)
    private Ruta ruta;


    public Ruta() {
    }

    public Ruta(Integer id, String nombreruta, Dificultad dificultad) {
        this.id = id;
        this.nombreruta = nombreruta;
        this.dificultad = dificultad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreruta() {
        return nombreruta;
    }

    public void setNombreruta(String nombreruta) {
        this.nombreruta = nombreruta;
    }

    public Dificultad getDificultad() {
        return dificultad;
    }

    public void setDificultad(Dificultad dificultad) {
        this.dificultad = dificultad;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }
}
