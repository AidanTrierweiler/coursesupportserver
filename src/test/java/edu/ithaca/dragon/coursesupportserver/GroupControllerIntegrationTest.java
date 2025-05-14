package edu.ithaca.dragon.coursesupportserver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test") // Use the H2 database for this test
public class GroupControllerIntegrationTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private GroupRepository groupRepository;

        @Test
        public void testCreateAndGetGroups() throws Exception {
                // Clear the database
                groupRepository.deleteAll();

                // Create and save a group
                String subgroups = "group1: ally, kate, brendan; group2: sasha, connie, eren";
                String courseId = "COMP220";
                Group group = new Group("2023-10-01", subgroups, courseId);

                // Convert the group to JSON
                String groupJson = String.format(
                                "{\"name\":\"%s\",\"subgroups\":\"%s\",\"courseId\":\"%s\"}",
                                group.getName(), group.getSubgroups(), group.getCourseId());

                // POST the group
                mockMvc.perform(post("/api/groups")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(groupJson))
                                .andExpect(status().isCreated()); // Expect 201 Created

                // GET all groups
                mockMvc.perform(get("/api/groups"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.size()").value(1))
                                .andExpect(jsonPath("$[0].name").value("2023-10-01"))
                                .andExpect(jsonPath("$[0].subgroups").value(subgroups))
                                .andExpect(jsonPath("$[0].courseId").value(courseId));
        }
}