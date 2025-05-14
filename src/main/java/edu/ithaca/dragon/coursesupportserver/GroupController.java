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
        return new ResponseEntity<>(savedGroup, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Group> getAllGroups(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String courseId) {
        if (name != null) {
            return groupRepository.findByName(name)
                    .map(List::of)
                    .orElseThrow(() -> new IllegalArgumentException("Group not found with name: " + name));
        }
        if (courseId != null) {
            return groupRepository.findByCourseId(courseId);
        }
        return groupRepository.findAll();
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<Group> getGroupByName(@PathVariable String name) {
        return groupRepository.findByName(name)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalArgumentException("Group not found with name: " + name));
    }
}