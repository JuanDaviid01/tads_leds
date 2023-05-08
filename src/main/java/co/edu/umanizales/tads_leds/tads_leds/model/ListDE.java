package co.edu.umanizales.tads_leds.tads_leds.model;

import co.edu.umanizales.tads_leds.tads_leds.exception.ListDEException;
import lombok.Data;

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


}//fin listDE
