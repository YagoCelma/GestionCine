package main;
 
import org.json.JSONArray;
import org.json.JSONObject;
import utils.ConfigLoader;
 
import java.util.ArrayList;
import java.util.List;
 
public class CineConfig {
    public static String diaEspectador;
    public static String direccion;
    public static String horarioApertura;
    public static String horarioCierre;
    public static int pausaEntrePeliculas;
 
    // Mapa Cine
    public static String salaEmergencia;
    public static String banos;
    public static List<String> zonaDiscapacitados;
    public static String ubicacionBar;
 
    public static int precioEntradaNormal;
 
    // Textos publicitarios
    public static String bienvenida;
    public static String redesSociales;
    public static String mensajePantalla;
 
    // Limpieza
    public static String horarioLimpiezaProfunda;
    public static int frecuenciaSalas;
    public static List<String> productosUtilizados;
 
    // Alianzas
    public static List<Alianza> alianzas;
 
    public static List<String> diasAperturaSemanal;
    public static List<String> diasCierreSemanal;
    public static double precioDiaEspectador;
 
    // Politicas
    public static int edadMinimaNoAcompanado;
    public static List<String> normas;
    public static String devoluciones;
 
    // Idiomas
    public static boolean subtitulosAutomaticos;
    public static List<String> idiomasDisponibles;
    public static String idiomaPredeterminado;
 
    // Temporadas
    public static List<Temporada> temporadas;
 
    public static int iva;
 
    // Horarios automáticos
    public static String horarioPrimerFuncion;
    public static String horarioUltimaFuncion;
    public static int intervaloEntreFunciones;
    public static int duracionPublicidadPrevia;
 
    public static List<String> diasCierreFestivo;
    public static String moneda;
    public static String telefono;
    public static String nombre;
 
    public static List<Promocion> promociones;
 
    public static void cargarDatos() {
        JSONObject cine = ConfigLoader.getCineConfig();
 
        diaEspectador = cine.getString("diaEspectador");
        direccion = cine.getString("direccion");
        horarioApertura = cine.getString("horarioApertura");
        horarioCierre = cine.getString("horarioCierre");
        pausaEntrePeliculas = cine.getInt("pausaEntrePeliculas");
 
        JSONObject mapa = cine.getJSONObject("mapa_cine");
        salaEmergencia = mapa.getString("sala_emergencia");
        banos = mapa.getString("baños");
        zonaDiscapacitados = jsonArrayToList(mapa.getJSONArray("zona_discapacitados"));
        ubicacionBar = mapa.getString("ubicacion_bar");
 
        precioEntradaNormal = cine.getInt("PrecioEntradaNormal");
 
        JSONObject textos = cine.getJSONObject("textosPublicitarios");
        bienvenida = textos.getString("bienvenida");
        redesSociales = textos.getString("redes_sociales");
        mensajePantalla = textos.getString("mensaje_pantalla");
 
        JSONObject limpieza = cine.getJSONObject("limpieza");
        horarioLimpiezaProfunda = limpieza.getString("horario_limpieza_profunda");
        frecuenciaSalas = limpieza.getInt("frecuencia_salas");
        productosUtilizados = jsonArrayToList(limpieza.getJSONArray("productos_utilizados"));
 
        diasAperturaSemanal = jsonArrayToList(cine.getJSONArray("diasAperturaSemanal"));
        diasCierreSemanal = jsonArrayToList(cine.getJSONArray("diasCierreSemanal"));
        precioDiaEspectador = cine.getDouble("PrecioDiaEspectador");
 
        JSONObject politicas = cine.getJSONObject("politicas");
        edadMinimaNoAcompanado = politicas.getInt("edad_minima_no_acompanado");
        normas = jsonArrayToList(politicas.getJSONArray("normas"));
        devoluciones = politicas.getString("devoluciones");
 
        JSONObject idiomas = cine.getJSONObject("idiomas");
        subtitulosAutomaticos = idiomas.getBoolean("subtitulos_automaticos");
        idiomasDisponibles = jsonArrayToList(idiomas.getJSONArray("disponibles"));
        idiomaPredeterminado = idiomas.getString("idioma_predeterminado");
 
        JSONArray temporadasArr = cine.getJSONArray("temporadas");
        temporadas = new ArrayList<>();
        for (int i = 0; i < temporadasArr.length(); i++) {
            JSONObject temp = temporadasArr.getJSONObject(i);
            temporadas.add(new Temporada(
                temp.getString("nombre"),
                temp.getString("fecha_inicio"),
                temp.getString("fecha_fin"),
                temp.getString("decoracion"),
                jsonArrayToList(temp.getJSONArray("peliculas_especiales"))
            ));
        }
 
        iva = cine.getInt("IVA");
 
        JSONObject horariosAuto = cine.getJSONObject("horarios_automaticos");
        horarioPrimerFuncion = horariosAuto.getString("horario_primer_funcion");
        horarioUltimaFuncion = horariosAuto.getString("horario_ultima_funcion");
        intervaloEntreFunciones = horariosAuto.getInt("intervalo_entre_funciones");
        duracionPublicidadPrevia = horariosAuto.getInt("duracion_publicidad_previa");
 
        diasCierreFestivo = jsonArrayToList(cine.getJSONArray("diasCierreFestivo"));
        moneda = cine.getString("moneda");
        telefono = cine.getString("telefono");
        nombre = cine.getString("nombre");
 
        JSONArray alianzasArr = cine.getJSONArray("alianzas");
        alianzas = new ArrayList<>();
        for (int i = 0; i < alianzasArr.length(); i++) {
            JSONObject obj = alianzasArr.getJSONObject(i);
            alianzas.add(new Alianza(
                obj.getString("empresa"),
                obj.getString("descuento"),
                obj.getString("validez")
            ));
        }
 
        JSONArray promocionesArr = cine.getJSONArray("promociones");
        promociones = new ArrayList<>();
        for (int i = 0; i < promocionesArr.length(); i++) {
            JSONObject promo = promocionesArr.getJSONObject(i);
            promociones.add(new Promocion(promo));
        }
    }
 
    private static List<String> jsonArrayToList(JSONArray array) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            list.add(array.getString(i));
        }
        return list;
    }
 
    public static class Alianza {
        public String empresa;
        public String descuento;
        public String validez;
 
        public Alianza(String empresa, String descuento, String validez) {
            this.empresa = empresa;
            this.descuento = descuento;
            this.validez = validez;
        }
    }
 
    public static class Temporada {
        public String nombre;
        public String fechaInicio;
        public String fechaFin;
        public String decoracion;
        public List<String> peliculasEspeciales;
 
        public Temporada(String nombre, String fechaInicio, String fechaFin, String decoracion, List<String> peliculas) {
            this.nombre = nombre;
            this.fechaInicio = fechaInicio;
            this.fechaFin = fechaFin;
            this.decoracion = decoracion;
            this.peliculasEspeciales = peliculas;
        }
    }
 
    public static class Promocion {
        public String nombre;
        public String descripcion;
        public double precioPromocion = -1;
        public String validoHasta = null;
        public List<String> productosAplicables;
        public List<String> diasValidos;
        public Integer descuento;
 
        public Promocion(JSONObject promo) {
            this.nombre = promo.getString("nombre");
            this.descripcion = promo.getString("descripcion");
            if (promo.has("precio_promocion")) {
                this.precioPromocion = promo.getDouble("precio_promocion");
            }
            if (promo.has("valido_hasta")) {
                this.validoHasta = promo.getString("valido_hasta");
            }
            if (promo.has("productos_aplicables")) {
                this.productosAplicables = jsonArrayToList(promo.getJSONArray("productos_aplicables"));
            }
            if (promo.has("dias_validos")) {
                this.diasValidos = jsonArrayToList(promo.getJSONArray("dias_validos"));
            }
            if (promo.has("descuento")) {
                this.descuento = promo.getInt("descuento");
            }
        }
    }
}