package um.edu.uy.controller.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import um.edu.uy.services.AirlineMgr;
@RestController

@RequestMapping(value = "/airlines")
public class AirlineRestController {
    private AirlineMgr airlineMgr;

}
