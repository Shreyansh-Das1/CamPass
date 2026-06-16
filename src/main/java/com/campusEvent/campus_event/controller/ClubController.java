package com.campusEvent.campus_event.controller;

import com.campusEvent.campus_event.dto.Club.ClubReqDTO;
import com.campusEvent.campus_event.dto.Club.ClubResDTO;
import com.campusEvent.campus_event.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/club")
public class ClubController {

    @Autowired
    ClubService clubService;

    @PostMapping("/createclub")
    @PreAuthorize("hasRole('ORGANIZER') or hasRole('ADMIN')")
    public @ResponseBody ClubResDTO createClub(@RequestBody ClubReqDTO club) {
       return clubService.createClub(club);
    }

    @GetMapping("/get")
    public @ResponseBody ClubResDTO getClub(@RequestParam Long clubID) {
       return clubService.retClub(clubID);
    }
}
