package main;
import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class CineConfigEditor {
    private static final String CONFIG_FILE = "config.json";
    private static JSONObject config;
    private static Scanner scanner = new Scanner(System.in);

    public void configuración() {
        try {
            // Cargar configuración
            String jsonStr = new String(Files.readAllBytes(Paths.get(CONFIG_FILE)));
            config = new JSONObject(jsonStr);
            JSONObject cine = config.getJSONObject("cine");

            // Menú principal
            while (true) {
                System.out.println("\n=== EDITOR DE CONFIGURACIÓN DEL CINE ===");
                System.out.println("1. Información básica del cine");
                System.out.println("2. Horarios y días de apertura");
                System.out.println("3. Promociones");
                System.out.println("4. Textos publicitarios");
                System.out.println("5. Políticas");
                System.out.println("6. Temporadas especiales");
                System.out.println("7. Guardar y salir");
                System.out.print("Seleccione una opción: ");

                int opcion = scanner.nextInt();
                scanner.nextLine(); 

                switch (opcion) {
                    case 1:
                        editarInformacionBasica(cine);
                        break;
                    case 2:
                        editarHorarios(cine);
                        break;
                    case 3:
                        editarPromociones(cine);
                        break;
                    case 4:
                        editarTextosPublicitarios(cine);
                        break;
                    case 5:
                        editarPoliticas(cine);
                        break;
                    case 6:
                        editarTemporadas(cine);
                        break;
                    case 7:
                        guardarConfiguracion();
                        System.out.println("Cambios guardados correctamente");
                        return;
                    default:
                        System.out.println("Opción no válida");
                }
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private void editarInformacionBasica(JSONObject cine) {
        System.out.println("\n--- INFORMACIÓN BÁSICA ---");
        System.out.println("1. Nombre actual: " + cine.getString("nombre"));
        System.out.println("2. Dirección actual: " + cine.getString("direccion"));
        System.out.println("3. Teléfono actual: " + cine.getString("telefono"));
        System.out.println("4. Moneda actual: " + cine.getString("moneda"));
        System.out.println("5. IVA actual: " + cine.getInt("IVA") + "%");
        System.out.print("Seleccione qué desea cambiar (1-5) o 0 para volver: ");

        int opcion = scanner.nextInt();
        scanner.nextLine();
        if (opcion == 0) return;

        System.out.print("Nuevo valor: ");
        String nuevoValor = scanner.nextLine();

        switch (opcion) {
            case 1: cine.put("nombre", nuevoValor); break;
            case 2: cine.put("direccion", nuevoValor); break;
            case 3: cine.put("telefono", nuevoValor); break;
            case 4: cine.put("moneda", nuevoValor); break;
            case 5: cine.put("IVA", Integer.parseInt(nuevoValor)); break;
        }
    }

    private void editarHorarios(JSONObject cine) {
        System.out.println("\n--- HORARIOS ---");
        System.out.println("1. Horario apertura: " + cine.getString("horarioApertura"));
        System.out.println("2. Horario cierre: " + cine.getString("horarioCierre"));
        System.out.println("3. Días apertura: " + cine.getJSONArray("diasAperturaSemanal"));
        System.out.println("4. Días cierre: " + cine.getJSONArray("diasCierreSemanal"));
        System.out.println("5. Día espectador: " + cine.getString("diaEspectador"));
        System.out.println("6. Precio día espectador: " + cine.getDouble("PrecioDiaEspectador") + cine.getString("moneda"));
        System.out.print("Seleccione qué desea cambiar (1-6) o 0 para volver: ");

        int opcion = scanner.nextInt();
        scanner.nextLine();
        if (opcion == 0) return;

        switch (opcion) {
            case 1:
                System.out.print("Nuevo horario apertura (HH:MM): ");
                cine.put("horarioApertura", scanner.nextLine());
                break;
            case 2:
                System.out.print("Nuevo horario cierre (HH:MM): ");
                cine.put("horarioCierre", scanner.nextLine());
                break;
            case 3:
                System.out.print("Ingrese días de apertura separados por comas (ej: Lunes,Martes): ");
                cine.put("diasAperturaSemanal", new JSONArray(scanner.nextLine().split(",")));
                break;
            case 4:
                System.out.print("Ingrese días de cierre separados por comas (ej: Miercoles,Jueves): ");
                cine.put("diasCierreSemanal", new JSONArray(scanner.nextLine().split(",")));
                break;
            case 5:
                System.out.print("Nuevo día espectador: ");
                cine.put("diaEspectador", scanner.nextLine());
                break;
            case 6:
                System.out.print("Nuevo precio día espectador: ");
                cine.put("PrecioDiaEspectador", Double.parseDouble(scanner.nextLine()));
                break;
        }
    }

    private void editarPromociones(JSONObject cine) {
        JSONArray promociones = cine.getJSONArray("promociones");
        System.out.println("\n--- PROMOCIONES ---");
        
        for (int i = 0; i < promociones.length(); i++) {
            JSONObject promo = promociones.getJSONObject(i);
            System.out.println((i+1) + ". " + promo.getString("nombre") + " - " + promo.getString("descripcion"));
        }
        
        System.out.print("Seleccione promoción a editar (1-" + promociones.length() + ") o 0 para volver: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();
        if (opcion == 0 || opcion > promociones.length()) return;

        JSONObject promo = promociones.getJSONObject(opcion-1);
        System.out.println("1. Nombre: " + promo.getString("nombre"));
        System.out.println("2. Descripción: " + promo.getString("descripcion"));
        
        if (promo.has("dias_validos")) {
            System.out.println("3. Días válidos: " + promo.getJSONArray("dias_validos"));
        }
        if (promo.has("descuento")) {
            System.out.println("4. Descuento: " + promo.getInt("descuento") + "%");
        }
        if (promo.has("precio_promocion")) {
            System.out.println("5. Precio promoción: " + promo.getDouble("precio_promocion"));
        }
        
        System.out.print("Seleccione campo a editar: ");
        int campo = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Nuevo valor: ");
        String nuevoValor = scanner.nextLine();
        
        switch (campo) {
            case 1: promo.put("nombre", nuevoValor); break;
            case 2: promo.put("descripcion", nuevoValor); break;
            case 3: promo.put("dias_validos", new JSONArray(nuevoValor.split(","))); break;
            case 4: promo.put("descuento", Integer.parseInt(nuevoValor)); break;
            case 5: promo.put("precio_promocion", Double.parseDouble(nuevoValor)); break;
        }
    }

    private void editarTextosPublicitarios(JSONObject cine) {
        JSONObject textos = cine.getJSONObject("textosPublicitarios");
        System.out.println("\n--- TEXTOS PUBLICITARIOS ---");
        System.out.println("1. Mensaje bienvenida: " + textos.getString("bienvenida"));
        System.out.println("2. Mensaje pantalla: " + textos.getString("mensaje_pantalla"));
        System.out.println("3. Redes sociales: " + textos.getString("redes_sociales"));
        System.out.print("Seleccione qué desea cambiar (1-3) o 0 para volver: ");

        int opcion = scanner.nextInt();
        scanner.nextLine();
        if (opcion == 0) return;

        System.out.print("Nuevo texto: ");
        String nuevoTexto = scanner.nextLine();

        switch (opcion) {
            case 1: textos.put("bienvenida", nuevoTexto); break;
            case 2: textos.put("mensaje_pantalla", nuevoTexto); break;
            case 3: textos.put("redes_sociales", nuevoTexto); break;
        }
    }

    private void editarPoliticas(JSONObject cine) {
        JSONObject politicas = cine.getJSONObject("politicas");
        System.out.println("\n--- POLÍTICAS ---");
        System.out.println("1. Política devoluciones: " + politicas.getString("devoluciones"));
        System.out.println("2. Edad mínima no acompañado: " + politicas.getInt("edad_minima_no_acompanado"));
        System.out.println("3. Normas: " + politicas.getJSONArray("normas"));
        System.out.print("Seleccione qué desea cambiar (1-3) o 0 para volver: ");

        int opcion = scanner.nextInt();
        scanner.nextLine();
        if (opcion == 0) return;

        switch (opcion) {
            case 1:
                System.out.print("Nueva política de devoluciones: ");
                politicas.put("devoluciones", scanner.nextLine());
                break;
            case 2:
                System.out.print("Nueva edad mínima: ");
                politicas.put("edad_minima_no_acompanado", Integer.parseInt(scanner.nextLine()));
                break;
            case 3:
                System.out.print("Ingrese normas separadas por comas: ");
                politicas.put("normas", new JSONArray(scanner.nextLine().split(",")));
                break;
        }
    }

    private void editarTemporadas(JSONObject cine) {
        JSONArray temporadas = cine.getJSONArray("temporadas");
        System.out.println("\n--- TEMPORADAS ESPECIALES ---");
        
        for (int i = 0; i < temporadas.length(); i++) {
            JSONObject temp = temporadas.getJSONObject(i);
            System.out.println((i+1) + ". " + temp.getString("nombre") + 
                            " (" + temp.getString("fecha_inicio") + " a " + 
                            temp.getString("fecha_fin") + ")");
        }
        
        System.out.print("Seleccione temporada a editar (1-" + temporadas.length() + ") o 0 para volver: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();
        if (opcion == 0 || opcion > temporadas.length()) return;

        JSONObject temp = temporadas.getJSONObject(opcion-1);
        System.out.println("1. Nombre: " + temp.getString("nombre"));
        System.out.println("2. Fecha inicio: " + temp.getString("fecha_inicio"));
        System.out.println("3. Fecha fin: " + temp.getString("fecha_fin"));
        System.out.println("4. Películas especiales: " + temp.getJSONArray("peliculas_especiales"));
        System.out.println("5. Decoración: " + temp.getString("decoracion"));
        
        System.out.print("Seleccione campo a editar (1-5): ");
        int campo = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Nuevo valor: ");
        String nuevoValor = scanner.nextLine();
        
        switch (campo) {
            case 1: temp.put("nombre", nuevoValor); break;
            case 2: temp.put("fecha_inicio", nuevoValor); break;
            case 3: temp.put("fecha_fin", nuevoValor); break;
            case 4: temp.put("peliculas_especiales", new JSONArray(nuevoValor.split(","))); break;
            case 5: temp.put("decoracion", nuevoValor); break;
        }
    }

    private void guardarConfiguracion() throws Exception {
        Files.write(Paths.get(CONFIG_FILE), config.toString(4).getBytes());
    }
}