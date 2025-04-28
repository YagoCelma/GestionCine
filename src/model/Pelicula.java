package model;

public class Pelicula {
    int id;
    String titulo;
    String director;
    String genero;
    int duracion; // minutos
    String clasificacion; // público apto / edad recomendada
    double precioEntrada;
    boolean enCartelera;

    // Constructor
    public Pelicula() {
    }

    public Pelicula(int id, String titulo, String director, String genero,
            int duracion, String clasificacion, double precioEntrada, boolean enCartelera) {
        this.id = id;
        this.titulo = titulo;
        this.director = director;
        this.genero = genero;
        this.duracion = duracion;
        this.clasificacion = clasificacion;
        this.precioEntrada = precioEntrada;
        this.enCartelera = false; // Entendemos que la película se registrará antes del estreno y por tanto no
                                  // estará aún en cartelera. Si fuera de otra forma usaremos el método
                                  // modificarPelicula()
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDirector() {
        return director;
    }

    public String getGenero() {
        return genero;
    }

    public int getDuracion() {
        return duracion;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public double getPrecioEntrada() {
        return precioEntrada;
    }

    public boolean isEnCartelera() {
        return enCartelera;
    }
}
