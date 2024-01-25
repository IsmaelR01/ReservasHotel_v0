package org.iesalandalus.programacion.reservashotel;

import org.iesalandalus.programacion.reservashotel.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.dominio.TipoHabitacion;
import org.iesalandalus.programacion.reservashotel.negocio.Habitaciones;
import org.iesalandalus.programacion.reservashotel.negocio.Huespedes;
import org.iesalandalus.programacion.reservashotel.negocio.Reservas;
import org.iesalandalus.programacion.reservashotel.vista.Consola;
import org.iesalandalus.programacion.reservashotel.vista.Opcion;

import javax.naming.OperationNotSupportedException;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class MainApp {
    private static Huespedes huespedes;
    private static Habitaciones habitaciones;
    private static Reservas reservas;
    public static final int CAPACIDAD = 10;

    public static void main(String[] args) {
        Opcion opcion;
        huespedes = new Huespedes(CAPACIDAD);
        habitaciones = new Habitaciones(CAPACIDAD);
        reservas = new Reservas(CAPACIDAD);

        do {
            Consola.mostrarMenu();
            ejecutarOpcion(opcion = Consola.elegirOpcion());
        }while(opcion != Opcion.SALIR);

    }

    private static void ejecutarOpcion(Opcion opcion) {
        switch (opcion) {
            case SALIR:
                System.out.println("Adios, hasta luego");
                break;
            case INSERTAR_HUESPED:
                insertarHuesped();
                break;
            case BUSCAR_HUESPED:
                buscarHuesped();
                break;
            case BORRAR_HUESPED:
                borrarHuesped();
                break;
            case MOSTRAR_HUESPEDES:
                mostrarHuespedes();
                break;
            case INSERTAR_HABITACION:
                insertarHabitacion();
                break;
            case BUSCAR_HABITACION:
                buscarHabitacion();
                break;
            case BORRAR_HABITACION:
                borrarHabitacion();
                break;
            case MOSTRAR_HABITACIONES:
                mostrarHabitaciones();
                break;
            case INSERTAR_RESERVA:
                insertarReserva();
                break;
            case ANULAR_RESERVA:
                anularReserva();
                break;
            case MOSTRAR_RESERVAS:
                mostrarReservas();
                break;
                /*
            case CONSULTAR_DISPONIBILIDAD:
                consultarDisponibilidad();
                 */
            default:
                System.out.println("Opción no valida inténtalo de nuevo");

        }

    }



    private static void insertarHuesped() {
        try {
            huespedes.insertar(Consola.leerHuesped());
            System.out.println("Huésped insertado correctamente.");
        }catch(NullPointerException | IllegalArgumentException | OperationNotSupportedException |
               DateTimeParseException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void buscarHuesped() {
        try {
            Consola.getHuespedPorDni();
        }catch (NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void borrarHuesped() {
        try {
            huespedes.borrar(Consola.getHuespedPorDni());
            System.out.println("Huesped borrado correctamente.");
        }catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void mostrarHuespedes() {
        System.out.println(Arrays.toString(huespedes.get()));
    }

    private static void insertarHabitacion() {
        try {
            habitaciones.insertar(Consola.leerHabitacion());
            System.out.println("Habitación insertada correctamente");
        } catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }
    private static void buscarHabitacion() {
        try {
            Consola.leerHabitacionPorIdentificador();
        }catch (NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void borrarHabitacion() {
        try {
            habitaciones.borrar(Consola.leerHabitacionPorIdentificador());
            System.out.println("Habitación borrada correctamente.");
        }catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void mostrarHabitaciones() {
        System.out.println(Arrays.toString(habitaciones.get()));
    }

    private static void insertarReserva() {
        try {
            reservas.insertar(Consola.leerReserva());
            System.out.println("Reserva insertada correctamente.");
        }catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listarReservas(Huesped huesped) {
        Reserva[] reservasHuesped = reservas.getReservas(huesped);

        if (reservasHuesped == null) {
            System.out.println("No hay reservas para el huésped con DNI: " + huesped.getDni());
        } else {
            System.out.println("Reservas para el huésped con DNI " + huesped.getDni() + ":");
            for (int i = 0; i < reservasHuesped.length; i++) {
                System.out.println(reservasHuesped[i]);
            }
        }
    }

    private static void listarReservas(TipoHabitacion tipoHabitacion) {

    }

    private static Reserva[] getReservasAnulables(Reserva[] reservaAAnular) {
        return null;
    }

    private static void anularReserva() {
    }

    private static void mostrarReservas() {
        System.out.println(Arrays.toString(reservas.get()));
    }


/*
    private static Habitacion consultarDisponibilidad(TipoHabitacion tipoHabitacion, LocalDate fechaInicioReserva, LocalDate fechaFinReserva) {
        boolean tipoHabitacionEncontrada=false;
        Habitacion habitacionDisponible=null;
        int numElementos=0;

        Habitacion[] habitacionesTipoSolicitado= habitaciones.get(tipoHabitacion.);

        if (habitacionesTipoSolicitado==null)
            return habitacionDisponible;

        for (int i=0; i<habitacionesTipoSolicitado.length && !tipoHabitacionEncontrada; i++)
        {

            if (habitacionesTipoSolicitado[i]!=null)
            {
                Reserva[] reservasFuturas = reservas.getReservasFuturas(habitacionesTipoSolicitado[i]);
                numElementos=getNumElementosNoNulos(reservasFuturas);

                if (numElementos == 0)
                {
                    //Si la primera de las habitaciones encontradas del tipo solicitado no tiene reservas en el futuro,
                    // quiere decir que está disponible.
                    habitacionDisponible=new Habitacion(habitacionesTipoSolicitado[i]);
                    tipoHabitacionEncontrada=true;
                }
                else {

                    //Ordenamos de mayor a menor las reservas futuras encontradas por fecha de fin de la reserva.
                    // Si la fecha de inicio de la reserva es posterior a la mayor de las fechas de fin de las reservas
                    // (la reserva de la posición 0), quiere decir que la habitación está disponible en las fechas indicadas.

                    Arrays.sort(reservasFuturas, 0, numElementos, Comparator.comparing(Reserva::getFechaFinReserva).reversed());

                    /*System.out.println("\n\nMostramos las reservas ordenadas por fecha de inicio de menor a mayor (numelementos="+numElementos+")");
                    mostrar(reservasFuturas);*/
/*
                    if (fechaInicioReserva.isAfter(reservasFuturas[0].getFechaFinReserva())) {
                        habitacionDisponible = new Habitacion(habitacionesTipoSolicitado[i]);
                        tipoHabitacionEncontrada = true;
                    }

                    if (!tipoHabitacionEncontrada)
                    {
                        //Ordenamos de menor a mayor las reservas futuras encontradas por fecha de inicio de la reserva.
                        // Si la fecha de fin de la reserva es anterior a la menor de las fechas de inicio de las reservas
                        // (la reserva de la posición 0), quiere decir que la habitación está disponible en las fechas indicadas.

                        Arrays.sort(reservasFuturas, 0, numElementos, Comparator.comparing(Reserva::getFechaInicioReserva));

                        /*System.out.println("\n\nMostramos las reservas ordenadas por fecha de inicio de menor a mayor (numelementos="+numElementos+")");
                        mostrar(reservasFuturas);*/
/*
                        if (fechaFinReserva.isBefore(reservasFuturas[0].getFechaInicioReserva())) {
                            habitacionDisponible = new Habitacion(habitacionesTipoSolicitado[i]);
                            tipoHabitacionEncontrada = true;
                        }
                    }

                    //Recorremos el array de reservas futuras para ver si las fechas solicitadas están algún hueco existente entre las fechas reservadas
                    if (!tipoHabitacionEncontrada)
                    {
                        for(int j=1;j<reservasFuturas.length && !tipoHabitacionEncontrada;j++)
                        {
                            if (reservasFuturas[j]!=null && reservasFuturas[j-1]!=null)
                            {
                                if(fechaInicioReserva.isAfter(reservasFuturas[j-1].getFechaFinReserva()) &&
                                        fechaFinReserva.isBefore(reservasFuturas[j].getFechaInicioReserva())) {

                                    habitacionDisponible = new Habitacion(habitacionesTipoSolicitado[i]);
                                    tipoHabitacionEncontrada = true;
                                }
                            }
                        }
                    }


                }
            }
        }
        return habitacionDisponible;
    }

 */
}

