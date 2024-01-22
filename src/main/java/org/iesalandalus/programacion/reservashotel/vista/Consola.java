package org.iesalandalus.programacion.reservashotel.vista;

import org.iesalandalus.programacion.reservashotel.dominio.Huesped;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {
    private Consola() {

    }

    public static void mostrarMenu() {
        System.out.println("Seleccione una opción");
        System.out.println("---------------------");
        System.out.println(Opcion.SALIR);
        System.out.println(Opcion.INSERTAR_HUESPED);
        System.out.println(Opcion.BUSCAR_HUESPED);
        System.out.println(Opcion.BORRAR_HUESPED);
        System.out.println(Opcion.MOSTRAR_HUESPEDES);
        System.out.println(Opcion.INSERTAR_HABITACION);
        System.out.println(Opcion.BUSCAR_HABITACION);
        System.out.println(Opcion.BORRAR_HABITACION);
        System.out.println(Opcion.MOSTRAR_HABITACIONES);
        System.out.println(Opcion.INSERTAR_RESERVA);
        System.out.println(Opcion.ANULAR_RESERVA);
        System.out.println(Opcion.MOSTRAR_RESERVAS);
        System.out.println(Opcion.CONSULTAR_DISPONIBILIDAD);
    }

}

