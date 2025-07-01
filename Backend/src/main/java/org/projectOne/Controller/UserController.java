package org.projectOne.Controller;

import org.projectOne.Entity.Users;
import org.projectOne.Repo.ContactRepo;
import org.projectOne.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private ContactRepo contactRepo;

    @Autowired
    private UserRepo userRepo;

    public UserController(ContactRepo contactRepo, UserRepo userRepo) {
        this.contactRepo = contactRepo;
        this.userRepo = userRepo;
    }

    @PostMapping("/users/new")
    public Users createUser(@RequestBody Users user){
        return userRepo.save(user);
    }
}
