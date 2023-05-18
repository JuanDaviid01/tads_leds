package co.edu.umanizales.tads_leds.tads_leds.model;

import co.edu.umanizales.tads_leds.tads_leds.exception.ListDEException;
import lombok.Data;

import java.time.LocalTime;
import java.util.ArrayList;

@Data
public class ListDE {
    private NodeDE head;
    int size;

    public void add(Led led) throws ListDEException {
        if (head != null) {
            NodeDE temp = head;
            while (temp.getNext() != null) {
                if (temp.getData().getId() == (led.getId())) {
                    throw new ListDEException("Ya existe un led");
                }
                temp = temp.getNext();
            }
            if (temp.getData().getId() == (led.getId())) {
                throw new ListDEException("Ya existe un led");
            }
            NodeDE newNode = new NodeDE(led);
            temp.setNext(newNode);
            newNode.setPrevious(temp);
        } else {
            head = new NodeDE(led);
        }
        size++;
    }//fin añadir led

    public void addToStart(Led led) {
        if (head != null) {
            NodeDE newNode = new NodeDE(led);
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = newNode;
        } else {
            head = new NodeDE(led);
        }
        size++;
    }//fin añadir al principio

    public ArrayList listLeds() {
        ArrayList listFinal = null;
        if (head != null) {
            NodeDE temp = head;
            listFinal = new ArrayList<>();
            while (temp != null) {
                listFinal.add(temp.getData());
                temp = temp.getNext();
            }
        }
        return listFinal;
    }

    public void rebootLeds() {
        if (head != null) {
            NodeDE temp = head;
            while (temp != null) {
                temp.getData().setState(false);
                temp.getData().setDateOff(null);
                temp.getData().setDateOn(null);
                temp = temp.getNext();
            }
        }
    }//reiniciar leds

    /*
    si hay datos...
    si hay por lo menos 2 leds
        crear 2 ayudantes para recorrer la lista hacia arriba y hacia abajo
        buscar la mitad de la lista utilizando el size
        usar un contador para llegar a la mitad
        una vez este en la mitad decirle a los 2 ayudantes que se ubiquen en ese nodo
            si es par decirle al ayudante 1 que se ubique en la mitad y al ayudante 2 que se pare uno adelante
            si es impar decirle al ayudante 1 y 2 que se paren en la mitad+1
        hasta que lleguen a los extremos
            que los prendan, esperen un segundo y los apaguen
            y le digo el temp1 que se pase al siguiente y al temp2 que pase al previo.
       cuando esten en los extremos
            los dejen prendidos
       si no hay minimo  2
            no se puede hacer el metodo entonces lo dejo prendido.
     */
    public void travelLedsOnOff() throws ListDEException, InterruptedException {
        if (head != null && size >= 2) {
            int pos = size / 2;
            int cont = 0;
            NodeDE temp1 = head;
            NodeDE temp2 = head;
            if (size % 2 == 0) {
                while (cont != pos) {
                    temp1 = temp1.getNext();
                    cont = cont + 1;
                }
                temp2 = temp1.getNext();
                //mitad
                while (temp2.getNext() != null && temp1.getPrevious() != null) {
                    //hacia abajo
                    temp2.getData().setState(true);
                    temp2.getData().setDateOn(LocalTime.now());
                    Thread.sleep(1000);
                    temp2.getData().setState(false);
                    temp2.getData().setDateOff(LocalTime.now());
                    temp2 = temp2.getNext();
                    //hacia arriba
                    temp1.getData().setState(true);
                    temp1.getData().setDateOn(LocalTime.now());
                    Thread.sleep(1000);
                    temp1.getData().setState(false);
                    temp1.getData().setDateOff(LocalTime.now());
                    temp1 = temp1.getPrevious();
                }//en los extremos
                temp2.getData().setState(true);
                temp2.getData().setDateOn(LocalTime.now());
                temp1.getData().setState(true);
                temp1.getData().setDateOn(LocalTime.now());
            } else {
                while (cont != pos) {
                    temp1 = temp1.getNext();
                    cont = cont + 1;
                }
                temp2 = temp1;
                //mitad
                while (temp2.getNext() != null && temp1.getPrevious() != null) {
                    //hacia abajo
                    temp2.getData().setState(true);
                    temp2.getData().setDateOn(LocalTime.now());
                    Thread.sleep(1000);
                    temp2.getData().setState(false);
                    temp2.getData().setDateOff(LocalTime.now());
                    temp2 = temp2.getNext();
                    //hacia arriba
                    temp1.getData().setState(true);
                    temp1.getData().setDateOn(LocalTime.now());
                    Thread.sleep(1000);
                    temp1.getData().setState(false);
                    temp1.getData().setDateOff(LocalTime.now());
                    temp1 = temp1.getPrevious();
                }//en los extremos
                temp2.getData().setState(true);
                temp2.getData().setDateOn(LocalTime.now());
                temp1.getData().setState(true);
                temp1.getData().setDateOn(LocalTime.now());
            }//else impar
        } else {
            if (head != null) {
                head.getData().setState(true);
            }
            throw new ListDEException("No hay suficientes Datos para hacer el metodo");
        }
    }// recorrer desde el centro
}//fin listDE
