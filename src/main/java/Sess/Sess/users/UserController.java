package Sess.Sess.users;

import Sess.Sess.database.Database;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @GetMapping
    public ResponseEntity<ArrayList<User>> getAllUsers() {
        Database db = Database.getInstance();
        return new ResponseEntity<>(db.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable int userId) {
        Database db = Database.getInstance();
        return new ResponseEntity<>(db.getUserById(userId), HttpStatus.OK);
    }
}
