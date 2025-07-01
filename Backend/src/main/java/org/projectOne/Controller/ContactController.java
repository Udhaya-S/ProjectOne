package org.projectOne.Controller;

import org.projectOne.Entity.Contacts;
import org.projectOne.Repo.ContactRepo;
import org.projectOne.Repo.UserRepo;
import org.projectOne.Service.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ContactController {
    @Autowired
    private ContactRepo contactRepo;

    @Autowired
    private UserRepo userRepo;

    public ContactController(ContactRepo contactRepo, UserRepo userRepo) {
        this.contactRepo = contactRepo;
        this.userRepo = userRepo;
    }

    @PostMapping("/con/new")
    public ResponseEntity<Contacts> createnew(@RequestBody Contacts contact){
    String userId=getCurrentUserName();
        if(userId==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        contact.setUsers(userRepo.findByUsername(userId).orElseThrow());
        contactRepo.save(contact);
        return ResponseEntity.ok(contact);
    }

    @GetMapping("/con/all")
    public List<Contacts> getallcon(){
        String username=getCurrentUserName();
        if(username==null){
            return null;
        }
        return contactRepo.findByUsers_Username(username);
    }

    @DeleteMapping("/con/delete/{id}")
    public void deletecon(@PathVariable Long id){
        contactRepo.deleteById(id);
    }
    private String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails userDetails) {
            return userDetails.getUsername();
        }
        return null;
    }
}
