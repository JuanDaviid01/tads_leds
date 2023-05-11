package co.edu.umanizales.tads_leds.tads_leds.service;

import co.edu.umanizales.tads_leds.tads_leds.model.ListDE;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Data
public class ListDEService {
    private ListDE leds;

    public ListDEService() {
        leds = new ListDE();
    }



}
