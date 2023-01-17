package com.example.restservice11db;

import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;

@RestController
@RequestMapping("user")
public class UserController {
    DB db = new DB();
    @GetMapping(value = "{login}")
    User getUserLogin(@PathVariable String login) throws SQLException {
        User usr = db.getUserLogin(login);
        return usr;
    }

    @PostMapping
    User postUser(@RequestBody User user){
        User userPost = db.postUser(user.getLogin(), user.getPassword(), user.getEmail());
        return userPost;
    }
}