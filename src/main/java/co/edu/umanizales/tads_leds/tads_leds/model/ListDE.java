package co.edu.umanizales.tads_leds.tads_leds.model;

import co.edu.umanizales.tads_leds.tads_leds.exception.ListDEException;
import lombok.Data;

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
                temp = temp.getNext();
            }
        }
    }//reiniciar leds


    /*
    si hay datos...
    si hay mas de 2 leds
        crear 2 ayudantes para recorrer la lista hacia arriba y hacia abajo
        buscar la mitad de la lista utilizando el size
        usar un contaodr para llegar a la mitad
        una vez este en la mitad decirle a los 2 ayudantes que se ubiquen en ese nodo
            si es par decirle al ayudante 1 que se ubique en la mitady al ayudante 2 que se ubique uno adelante
            si es impar decirle al ayudante 1 y 2 que se ubquien en la mitad+1
        hasta que lleguen a los extremos
            que los prendadn, esperen un segundo y lo apaguen
            y le digo el temp1 que se pase al siguiente y al temp2 que pase al previo.
       cuando esten en los extremos que los dejen prendidos
     */
    public void travelLedsOnOff() throws ListDEException {
        if (head != null && size >= 2) {
            if (size % 2 == 0) {
                int pos = size / 2;
                int cont = 0;
                NodeDE temp1 = head;
                NodeDE temp2 = head;
                while (cont != pos) {
                    temp1 = temp1.getNext();
                    cont = cont + 1;
                }
                temp2 = temp1.getNext();
            } else if (size % 2 != 0) {
                int pos = size / 2;
                int cont = 0;
                NodeDE temp1 = head;
                NodeDE temp2 = head;
                while (cont != pos) {
                    temp1 = temp1.getNext();
                    cont = cont + 1;
                }
                temp2 = temp1;
            }
        } else {
            head.getData().setState(true);
            throw new ListDEException("No hay suficientes Datos para hacer el metodo");
        }
    }


}//fin listDE
