package com.xeniabuscarons.restapirockets;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RocketRestController {
    private RocketService service;

    public RocketRestController(RocketService service){this.service=service;}

    @PostMapping("/rockets")
    public Rocket createRocket(@RequestBody Rocket rocketToCreate) {
        return service.createRocket(rocketToCreate);
    }

    @PostMapping("/rockets/{rocketId}/propellants")
    public Propellant createPropellantsOnRocket(@PathVariable String rocketId, @RequestBody Propellant propellant) throws Exception {
        return service.createPropellantsOnRocket(rocketId, propellant);
    }

    @PostMapping("/rockets/{rocketId}/movement")
    public Rocket moveRocket(@PathVariable String rocketId, @RequestBody String jsonString) throws Exception {
        JSONObject json = new JSONObject(jsonString);
        int speed = json.getInt("speed");
        String movement = json.getString("movement");
        return service.moveRocket(rocketId,movement,speed);
    }

    @GetMapping("/rockets")
    public List<Rocket> getRockets() throws Exception {
        return service.getRockets();
    }

    @GetMapping("/rockets/{rocketId}")
    public Rocket getRocket(@PathVariable String rocketId) throws Exception {
        return service.getRocket(rocketId);
    }
    @GetMapping("/rockets/{rocketId}/propellants")
    public List<Propellant> getPropellants(@PathVariable String rocketId) throws Exception {
        return service.getPropellants(rocketId);
    }

    @GetMapping("/rockets/{rocketId}/propellants/{propellantId}")
    public Propellant getpropellant(@PathVariable String rocketId, @PathVariable String propellantId) throws Exception {
        return service.getPropellant(rocketId, propellantId);
    }

    @DeleteMapping("/rockets")
    public void removeAllRestaurants() {
        service.removeAllRockets();
    }

    @DeleteMapping("/rockets/{rocketId}")
    public void removeRocket(@PathVariable String rocketId) {
        service.removeRocket(rocketId);
    }

    @DeleteMapping("/rockets/{rocketId}/propellants")
    public void removeAllPropellants(@PathVariable String rocketId) throws Exception {
        service.removeAllPropellants(rocketId);
    }
    @DeleteMapping("/rockets/{rocketId}/propellants/{propellantId}")
    public void removeTable(@PathVariable String rocketId, @PathVariable String propellantId) throws Exception {
        service.removePropellant(rocketId,propellantId);
    }

    @PutMapping("/rockets/{rocketId}")
    public Rocket updateRocket (@PathVariable String rocketId, @RequestBody Rocket rocketToUpdate) throws Exception {
        return service.updateRocket(rocketId,rocketToUpdate);
    }

    @PutMapping("/rockets/{rocketId}/propellants/{propellantId}")
    public Propellant updatePropellant (@PathVariable String propellantId,@RequestBody Propellant propellantToUpdate) throws Exception {
        return service.updatePropellant(propellantId,propellantToUpdate);
    }
}
