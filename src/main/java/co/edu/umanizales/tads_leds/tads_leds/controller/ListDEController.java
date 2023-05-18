package co.edu.umanizales.tads_leds.tads_leds.controller;

import co.edu.umanizales.tads_leds.tads_leds.controller.dto.LedDTO;
import co.edu.umanizales.tads_leds.tads_leds.controller.dto.ResponseDTO;
import co.edu.umanizales.tads_leds.tads_leds.exception.ListDEException;
import co.edu.umanizales.tads_leds.tads_leds.model.Led;
import co.edu.umanizales.tads_leds.tads_leds.service.ListDEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/listde")
public class ListDEController {
    @Autowired
    private ListDEService listDEService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getLeds() {
        return new ResponseEntity<>(new ResponseDTO(200, listDEService.getLeds().listLeds(), null), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> addLed(@RequestBody LedDTO ledDTO) throws ListDEException {
        listDEService.getLeds().add(new Led(ledDTO.getId(), false, null, null));
        return new ResponseEntity<>(new ResponseDTO(200, "Se ha adicionado el led", null), HttpStatus.OK);
    }

    @PostMapping(path = "/add_to_start")
    public ResponseEntity<ResponseDTO> addLedToStart(@RequestBody LedDTO ledDTO) {
        listDEService.getLeds().addToStart(new Led(ledDTO.getId(), false, null, null));
        return new ResponseEntity<>(new ResponseDTO(200, "Se ha adicionado el led", null), HttpStatus.OK);
    }

    @GetMapping(path = "/reboot_leds")
    public ResponseEntity<ResponseDTO> rebootLeds() {
        listDEService.getLeds().rebootLeds();
        return new ResponseEntity<>(new ResponseDTO(200, "se reinciaron los leds", null), HttpStatus.OK);
    }

    @GetMapping(path = "/travel_on_off_leds")
    public ResponseEntity<ResponseDTO> travelOnOffLeds() throws ListDEException, InterruptedException {
        listDEService.getLeds().travelLedsOnOff();
        return new ResponseEntity<>(new ResponseDTO(200, "se prendieron y apgaron exitosamente", null), HttpStatus.OK);
    }

}//fin controller