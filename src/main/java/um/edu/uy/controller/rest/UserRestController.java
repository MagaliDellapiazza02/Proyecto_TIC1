package um.edu.uy.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import um.edu.uy.services.UserMgr;
import um.edu.uy.business.entities.User;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserRestController {

    @Autowired
    private UserMgr userService;
    @GetMapping("/list")
    public ResponseEntity<List<User>> listaUsuarios()
    {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
