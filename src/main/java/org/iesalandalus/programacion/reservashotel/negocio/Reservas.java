package org.iesalandalus.programacion.reservashotel.negocio;

import org.iesalandalus.programacion.reservashotel.dominio.*;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.Arrays;

public class Reservas {
    public Reserva[] coleccionReservas;
    private int capacidad;
    private int tamano;

    public Reservas(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        this.coleccionReservas = new Reserva[capacidad];
        this.tamano = 0;
    }

    public Reserva[] get() {
        return copiaProfundaReservas();
    }

    private Reserva[] copiaProfundaReservas() {
        Reserva[] copiaReservas = new Reserva[tamano];
        for (int i = 0; i < tamano; i++) {
            copiaReservas[i] = new Reserva(coleccionReservas[i]);
        }
        return copiaReservas;
    }

    public int getTamano() {
        return tamano;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void insertar(Reserva reserva) throws OperationNotSupportedException {
        if (reserva == null) {
            throw new NullPointerException("ERROR: No se puede insertar una reserva nula.");
        }
        int posicion = buscarIndice(reserva);
        if (capacidadSuperada(posicion)) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más reservas.");
        }
        if (!tamanoSuperado(posicion)) {
            throw new OperationNotSupportedException("ERROR: Ya existe una reserva igual.");
        }
        if (tamanoSuperado(posicion)) {
            coleccionReservas[posicion] = new Reserva(reserva);
            tamano++;
        }
    }

    private int buscarIndice(Reserva reserva) {
        int indice = 0;
        for (int i = 0; i < tamano; i++) {
            if (coleccionReservas[i].equals(reserva)) {
                return i;
            }
            indice++;
        }
        return indice;
    }

    private boolean tamanoSuperado(int indice) {
        if (indice >= tamano) {
            return true;
        } else
            return false;
    }

    private boolean capacidadSuperada(int indice) {
        if (indice >= capacidad) {
            return true;
        } else
            return false;
    }

    public Reserva buscar(Reserva reserva) {
        int posicion = buscarIndice(reserva);
        if (tamanoSuperado(posicion)) {
            return null;
        }
        if (!tamanoSuperado(posicion)) {
            return coleccionReservas[posicion];
        }
        return reserva;
    }

    public void borrar(Reserva reserva) throws OperationNotSupportedException {

        if (reserva == null) {
            throw new NullPointerException("ERROR: No se puede borrar una reserva nula.");
        }
        int posicion = buscarIndice(reserva);

        if (tamanoSuperado(posicion)) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna reserva como la indicada.");
        }
        if (!tamanoSuperado(posicion)) {
            desplazarunaposicionHaciaIzquierda(posicion);
        }
    }

    private void desplazarunaposicionHaciaIzquierda(int indice) {
        for (int i = indice; i < tamano - 1; i++) {
            coleccionReservas[i] = coleccionReservas[i + 1];
        }
        coleccionReservas[tamano - 1] = null;
        tamano--;
    }


    public Reserva[] getReservas(Huesped huesped) {
        if(huesped == null) {
            throw  new NullPointerException("ERROR: No se pueden buscar reservas de un huesped nulo.");
        }
        int contador = 0;
        for(int i= 0; i < tamano; i++) {
            if(coleccionReservas[i].getHuesped().equals(huesped)) {
                contador++;
            }
        }
        Reserva[] reservasHuesped = new Reserva[contador];
        contador = 0;
        for(int i = 0; i< tamano; i++) {
            if(coleccionReservas[i].getHuesped().equals(huesped)) {
                reservasHuesped[contador++] = coleccionReservas[i];
            }
        }
        return reservasHuesped;
    }

    public Reserva[] getReservas(TipoHabitacion tipoHabitacion) {
        if(tipoHabitacion == null) {
            throw new NullPointerException("ERROR: No se pueden buscar reservas de un tipo de habitación nula.");
        }
        int contador = 0;
        for(int i = 0; i< tamano; i++) {
            if(coleccionReservas[i].getHabitacion().getTipoHabitacion().equals(tipoHabitacion)) {
                contador++;
            }
        }
        Reserva[] reservasTipoHabitacion = new Reserva[contador];
        contador = 0;
        for(int i = 0; i< tamano; i++) {
            if(coleccionReservas[i].getHabitacion().getTipoHabitacion().equals(tipoHabitacion)) {
                reservasTipoHabitacion[contador++] = coleccionReservas[i];
            }
        }
        return reservasTipoHabitacion;
    }

    public Reserva[] getReservasFuturas(Habitacion habitacion) {
        if(habitacion == null) {
            throw new NullPointerException("ERROR: No se pueden buscar reservas de una habitación nula.");
        }
        //tengo que saber cuantas reservas tiene una habitación
        int coincidencias = 0;
        for(int i = 0; i< tamano; i++) {
            if(coleccionReservas[i].getHabitacion().equals(habitacion)) {
                coincidencias++;
            }
        }
        Reserva[] reservasFuturas = new Reserva[coincidencias];
        int contador = 0;
        for(int i = 0; i< tamano; i++) {
            if(coleccionReservas[i].getHabitacion().equals(habitacion)) {
                reservasFuturas[contador++] = coleccionReservas[i];
            }
        }
        return reservasFuturas;
    }

    public static void main(String[] args) {
        try{
            Huesped huesped1=new Huesped("11111daniel lopez perez","75720662Q","l@gmail.com","626626626", LocalDate.of(1987,12,12));
            Huesped huesped2=new Huesped("22222paco clavero golvan","11223344B","l@gmail.com","626626626",LocalDate.of(1987,12,12));
            Huesped huesped3=new Huesped("33333david martinez soria","22334455Y","l@gmail.com","626626626",LocalDate.of(1987,12,12));

            Habitacion habitacion1=new Habitacion(2,1,40, TipoHabitacion.SIMPLE);
            Habitacion habitacion2=new Habitacion(2,2,45, TipoHabitacion.DOBLE);
            Habitacion habitacion3=new Habitacion(2,3,55, TipoHabitacion.TRIPLE);
            Habitacion habitacion4=new Habitacion(1,1,45, TipoHabitacion.SIMPLE);
            Habitacion habitacion5=new Habitacion(1,2,40, TipoHabitacion.DOBLE);
            Habitacion habitacion6=new Habitacion(1,3,50, TipoHabitacion.TRIPLE);

            Reserva reserva1=new Reserva(huesped1,habitacion2, Regimen.MEDIA_PENSION,LocalDate.of(2024,1,26),LocalDate.of(2024,1,28),2 );
            Reserva reserva2=new Reserva(huesped2,habitacion2, Regimen.MEDIA_PENSION,LocalDate.of(2024,1,22),LocalDate.of(2024,1,25),1 );
            Reserva reserva3=new Reserva(huesped3,habitacion1, Regimen.MEDIA_PENSION,LocalDate.of(2024,2,2),LocalDate.of(2024,2,3),1 );

            Reservas reservasLista1=new Reservas(5);
            reservasLista1.insertar(reserva1);
            reservasLista1.insertar(reserva2);
            reservasLista1.insertar(reserva3);
            System.out.println(Arrays.toString(reservasLista1.getReservas(huesped1)));





        }catch (NullPointerException   |OperationNotSupportedException| IllegalArgumentException e){
            System.out.println("-"+e.getMessage());

        }
    }

}


