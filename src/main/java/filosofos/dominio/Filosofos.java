package filosofos.dominio;

import java.util.Objects;

public class Filosofos {
    private int id;
    private String nombre;
    private String apellido;
    private String pensamiento;

    public Filosofos(){}

    public Filosofos(int id){
        this.id = id;
    }

    public Filosofos(String nombre, String apellido, String pensamiento){
        this.nombre = nombre;
        this.apellido = apellido;
        this.pensamiento = pensamiento;
    }

    public Filosofos(int id, String nombre, String apellido, String pensamiento){
        this(nombre, apellido, pensamiento);
        this.id = id;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPensamiento() {
        return pensamiento;
    }

    public void setPensamiento(String pensamiento) {
        this.pensamiento = pensamiento;
    }

    @Override
    public String toString() {
        return "Filosofos{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", pensamiento='" + pensamiento + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Filosofos filosofos = (Filosofos) o;
        return id == filosofos.id && Objects.equals(nombre, filosofos.nombre) && Objects.equals(apellido, filosofos.apellido) && Objects.equals(pensamiento, filosofos.pensamiento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellido, pensamiento);
    }
}
