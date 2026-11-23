package club.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ruta")
public class Ruta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idruta")
    private Integer id;
    @Column
    private String nombreruta;
    @Enumerated(EnumType.STRING)
    @Column
    private Dificultad dificultad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idsocio")  // solo para mostrar (viene del JOIN con socio)
    private Socio guia;


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

    public Socio getGuia() {
        return guia;
    }

    public void setGuia(Socio guia) {
        this.guia = guia;
    }

    @Override
    public String toString() {
        return "Ruta{" +
                "id=" + id +
                ", nombreruta='" + nombreruta + '\'' +
                ", dificultad=" + dificultad +
                ", guia=" + guia +
                '}';
    }
}
