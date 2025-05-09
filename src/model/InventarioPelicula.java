package model;

public class InventarioPelicula {
    int id;
    Pelicula pelicula;
    int copias;
    int copiasDisponibles;

    public InventarioPelicula() {
    }

    public InventarioPelicula(Pelicula pelicula, int copias) {
        this.pelicula = pelicula;
        this.copias = copias;
        this.copiasDisponibles = copias;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public int getCopias() {
        return copias;
    }

    public void setCopias(int copias) {
        this.copias = copias;
    }

    public int getCopiasDisponibles() {
        return copiasDisponibles;
    }

    public void setCopiasDisponibles(int copiasDisponibles) {
        this.copiasDisponibles = copiasDisponibles;
    }

        @Override
    public String toString() {
        return "InventarioPelicula{" +
               "id=" + id +
               ", pelicula=" + pelicula +
               ", copias=" + copias +
               ", copiasDisponibles=" + copiasDisponibles +
               '}';
    }

} 