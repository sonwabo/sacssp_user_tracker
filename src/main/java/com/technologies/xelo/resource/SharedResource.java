package com.technologies.xelo.resource;

import com.technologies.xelo.intf.PartyManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author <a href="mailto:s.singatha@gmail.com">Sonwabo Singatha</a>
 * @version 1.0
 * @Date 2021/04/27
 * @TIME 22:02
 */
@CrossOrigin
@RestController
public class SharedResource {

    private final PartyManager partyManager;

    public SharedResource(PartyManager partyManager) {
        this.partyManager = partyManager;
    }

    @GetMapping(value = "/v1/getCitizenshipCount")
    public ResponseEntity getCitizenshipCount() {
        return ResponseEntity.ok(this.partyManager.getCitizenshipCount());
    }

    @GetMapping(value = "/v1/getCommunityPractitionerCount")
    public ResponseEntity getCommunityPractitionerCount() {
        return ResponseEntity.ok(this.partyManager.getCommunityPractitionerCount());
    }

    @GetMapping(value = "/v1/validateIdentification")
    public ResponseEntity validateIdentification(@RequestParam(value = "identification") String identification, @RequestParam(value = "type") String identificationType) {
        return ResponseEntity.ok(this.partyManager.validateIdentification(identification,identificationType));
    }
}