package model;

public class InventarioProductos {

    private int id_inventario_productos;
    private String nombre;
    private int cantidad;
    private int cantidad_minima;
    private int id_proveedor;


    public InventarioProductos(){

    }

    public InventarioProductos(String nombre, int cantidad, int cantidad_minima, int id_proveedor){

        this.nombre = nombre;
        this.cantidad = cantidad;
        this.cantidad_minima = cantidad_minima;
        this.id_proveedor = id_proveedor;
    }


    public int getId() {return id_inventario_productos;}

    public void setId(int id_inventario_productos) {this.id_inventario_productos = id_inventario_productos;}

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public int getCantidad() {return cantidad;}

    public void setCantidad(int cantidad) {this.cantidad = cantidad;}

    public int getCantidadMinima() {return cantidad_minima;}

    public void setCantidadMinima(int cantidad_minima) {this.cantidad_minima = cantidad_minima ;}

    public int getIdProveedor() {return id_proveedor;}

    public void setIdProveedor(int id_proveedor) {this.id_proveedor = id_proveedor;}

        @Override
    public String toString() {
        return "InventarioProductos{" +
               "id=" + id_inventario_productos +
               ", nombre='" + nombre + '\'' +
               ", cantidad=" + cantidad +
               ", cantidadMinima=" + cantidad_minima +
               ", idProveedor=" + id_proveedor +
               '}';
    }

}
