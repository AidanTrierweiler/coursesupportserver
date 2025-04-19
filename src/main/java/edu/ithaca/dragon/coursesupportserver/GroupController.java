package edu.ithaca.dragon.coursesupportserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/groups")
public class GroupController {

    @Autowired
    private GroupRepository groupRepository;

    @PostMapping
    public ResponseEntity<Group> createGroup(@RequestBody Group group) {
        Group savedGroup = groupRepository.save(group);
        return new ResponseEntity<>(savedGroup, HttpStatus.CREATED); // Explicitly return 201 Created
    }

    @GetMapping
    public List<Group> getAllGroups(@RequestParam(required = false) String name) {
        if (name != null) {
            return groupRepository.findByName(name); // Filter by name if provided
        }
        return groupRepository.findAll(); // Return all groups if no filter is provided
    }
}