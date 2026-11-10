package club.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "socio")
public class Socio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idsocio")
    private int id;

    @Column(name = "nombresocio", nullable = false, length = 60)
    private String nombre;

    @Column(name = "telefonosocio", length = 15)
    private String telefono;

    @Column(name = "fecha_alta", nullable = false)
    private LocalDate fechaAlta;

    // Relación 1:N - Un socio tiene muchas rutas
    @OneToMany(mappedBy = "guia", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ruta> rutas = new ArrayList<>();

    public Socio() {
    }

    public Socio(int id, String nombre, String telefono, LocalDate fechaAlta, List<Ruta> rutas) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.fechaAlta = fechaAlta;
        this.rutas = rutas;
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

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public List<Ruta> getRutas() {
        return rutas;
    }

    public void setRutas(List<Ruta> rutas) {
        this.rutas = rutas;
    }

    /** ComboBox y ListView muestran este texto. */
    @Override
    public String toString() {
        return nombre;
    }
}
