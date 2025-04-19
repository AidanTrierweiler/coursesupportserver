package edu.ithaca.dragon.coursesupportserver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test") // Use the H2 database for this test
public class GroupControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateAndGetGroups() throws Exception {
        // Clear the database
        groupRepository.deleteAll();

        // Create and save a group
        Group group = new Group("Group1", List.of("student1", "student2"));

        // Convert the group to JSON
        String groupJson = objectMapper.writeValueAsString(group);

        // POST the group
        mockMvc.perform(post("/api/groups")
                .contentType(MediaType.APPLICATION_JSON)
                .content(groupJson))
                .andExpect(status().isCreated()); // Expect 201 Created

        // GET all groups
        mockMvc.perform(get("/api/groups"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].name").value("Group1"))
                .andExpect(jsonPath("$[0].studentIds.size()").value(2))
                .andExpect(jsonPath("$[0].studentIds[0]").value("student1"))
                .andExpect(jsonPath("$[0].studentIds[1]").value("student2"));
    }

    @Test
    public void testFilterGroups() throws Exception {
        // Clear the database
        groupRepository.deleteAll();

        // Save multiple groups
        Group group1 = new Group("Group1", List.of("student1", "student2"));
        Group group2 = new Group("Group2", List.of("student3", "student4"));
        groupRepository.saveAll(List.of(group1, group2));

        // GET all groups
        mockMvc.perform(get("/api/groups"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));

        // GET groups filtered by name
        mockMvc.perform(get("/api/groups?name=Group1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].name").value("Group1"))
                .andExpect(jsonPath("$[0].studentIds.size()").value(2))
                .andExpect(jsonPath("$[0].studentIds[0]").value("student1"))
                .andExpect(jsonPath("$[0].studentIds[1]").value("student2"));
    }
}