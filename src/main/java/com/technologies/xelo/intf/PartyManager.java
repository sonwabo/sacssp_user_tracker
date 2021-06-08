package com.technologies.xelo.intf;

import com.technologies.xelo.dto.PartyDTO;
import com.technologies.xelo.dto.UserDetailsDTO;
import java.util.List;
import org.springframework.data.domain.Page;


public interface PartyManager {

    /**
     * Creates new Party Instance
     *
     * @param party
     * @return
     */
    PartyDTO createParty(PartyDTO party);

    /**
     * Returns  Specified Party by Id in Database
     *
     * @param party
     * @return
     */
    PartyDTO getPartyById(Long party);

    List<PartyDTO> getAllUsers();

    /**
     * Return all the users
     * @param page
     * @param size
     * @return
     */
    Page<UserDetailsDTO> getAllUsers(int page, int size);

    /**
     * Creates new users
     *
     * @param user
     * @return
     */
    String createOrUpdateUser(UserDetailsDTO user);

    /**
     * Get User Information
     *
     * @param userid
     * @return
     */
    UserDetailsDTO getUserDetails(Long userid);

     /**
     * Returns User by generated reference
     *
     * @param userid
     * @return
     */
    UserDetailsDTO getUserDetailsByReference(String userid);

    /**
     * Returns the party's next sequence
     * @return
     */
    Long getNextPartySequence();

}
