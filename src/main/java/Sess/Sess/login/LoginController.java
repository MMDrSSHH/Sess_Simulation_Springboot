package Sess.Sess.login;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class LoginController {
    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
    public ResponseEntity<HashMap<String, Integer>> login(@RequestBody HashMap<String, String> payload, HttpServletResponse response) {
        Login l = Login.getInstance();

        HashMap<String, Integer> responseMap = new HashMap<>();

        int userId = l.login(payload.get("username"));

        if (userId > 0) {
            Cookie cookie = new Cookie("userId", userId + "");
            response.addCookie(cookie);
        }

        responseMap.put("userId", userId);

        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }
}

