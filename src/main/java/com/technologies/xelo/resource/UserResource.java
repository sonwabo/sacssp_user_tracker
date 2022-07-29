package com.technologies.xelo.resource;

import com.technologies.xelo.dto.UserDetailsDTO;
import com.technologies.xelo.intf.PartyManager;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.technologies.xelo.security.model.Role;
import com.technologies.xelo.security.model.User;
import com.technologies.xelo.security.service.UserService;
import com.technologies.xelo.util.SheetsGenerator;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * @author <a href="mailto:s.singatha@gmail.com">Sonwabo Singatha</a>
 * @version 1.0
 * @Date 2021/04/27
 * @TIME 22:02
 */
@CrossOrigin
@RestController
@RequiredArgsConstructor
public class UserResource {

    private final PartyManager partyManager;
    private final SheetsGenerator sheetsGenerator;
    private final UserService userService;

    @GetMapping(value = "/v1/users")
    public ResponseEntity<List<User>> getUsers(){
        return   ResponseEntity.created(null).body(this.userService.getUsers());
    }

    @PostMapping(value = "/v1/user/save")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("").toUriString());
        return   ResponseEntity.created(uri).body(this.userService.saveUser(user));
    }

    @PostMapping(value = "/v1/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("").toUriString());
        return   ResponseEntity.created(uri).body(this.userService.saveRole(role));
    }

    @PostMapping(value = "/v1/role/addtouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("").toUriString());
        this.userService.addRoleToUser(form.getUsername(), form.getRoleName());
        return   ResponseEntity.ok().build();
    }

    @GetMapping(value = "/v1/listAllUsers")
    public Page<UserDetailsDTO> getAllUsers(@RequestParam(value = "number") int page, @RequestParam(value = "size") int size) {
        return this.partyManager.getAllUsers((page - 1), size);
    }

    @GetMapping(value = "/v1/getUserById")
    public ResponseEntity<UserDetailsDTO> getUserById(@RequestParam(value = "userid") Long userid) {
        return ResponseEntity.ok(this.partyManager.getUserDetails(userid));
    }

    @GetMapping(value = "/v1/findUserByExternalRef")
    public Map<String, Object> findUserByReference(@RequestParam(value = "reference") String reference) {
        final  UserDetailsDTO response = this.partyManager.getUserDetailsByReference(reference);
        return new HashMap<String, Object>() {{
            put("response", response);
        }};
    }

    @PostMapping(value = "/v1/createUser")
    public ResponseEntity<Map<String, String>> createUser(@RequestBody UserDetailsDTO user) {

        if(user.userId != null && user.reference != null){
            return ResponseEntity.ok(this.partyManager.updateUser(user));
        }
        return ResponseEntity.ok(this.partyManager.createOrUpdateUser(user));
    }

    @PostMapping(value = "/v1/updateUser")
    public ResponseEntity<Map<String, String>> updateUser(@RequestBody UserDetailsDTO user) {
        return ResponseEntity.ok(this.partyManager.updateUser(user));
    }

    @GetMapping(value = "/v1/generate-sheets")
    public ResponseEntity<String> generateSheets(@RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
        try {
            this.sheetsGenerator.generateSheets(page, size);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("Generate");
    }

    @GetMapping(value = "/v1/generate-next-sequence")
    public ResponseEntity<Map<String, String>> generateSheets() {
        final String id = String.valueOf(this.partyManager.getNextPartySequence());
        return ResponseEntity.ok(new HashMap<String, String>() {{
            put("reference", id);
        }});
    }
}

@Data
class RoleToUserForm {
    private String username;
    private String roleName;
}

