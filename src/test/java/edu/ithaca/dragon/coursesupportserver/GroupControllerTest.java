package edu.ithaca.dragon.coursesupportserver;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(GroupController.class)
public class GroupControllerTest {

        @MockBean
        private GroupRepository groupRepository;

        @Autowired
        private MockMvc mockMvc;

        @Test
        void testGetAllGroups() throws Exception {
                // Existing test for retrieving groups
                List<Group> groups = List.of(
                                new Group("Group1", List.of("student1", "student2")),
                                new Group("Group2", List.of("student3", "student4")));
                when(groupRepository.findAll()).thenReturn(groups);

                mockMvc.perform(get("/api/groups"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.size()").value(2))
                                .andExpect(jsonPath("$[0].name").value("Group1"))
                                .andExpect(jsonPath("$[0].studentIds.size()").value(2))
                                .andExpect(jsonPath("$[1].name").value("Group2"))
                                .andExpect(jsonPath("$[1].studentIds.size()").value(2));
        }

        @Test
        void testGetAllGroupsSchemaValidation() throws Exception {
                // New test for schema validation
                List<Group> groups = List.of(
                                new Group("Group1", List.of("student1", "student2")),
                                new Group("Group2", List.of("student3", "student4")));
                when(groupRepository.findAll()).thenReturn(groups);

                String jsonResponse = mockMvc.perform(get("/api/groups"))
                                .andExpect(status().isOk())
                                .andReturn()
                                .getResponse()
                                .getContentAsString();

                String schemaPath = "schemas/group.schema.json";
                String schemaString = new String(
                                Files.readAllBytes(Paths
                                                .get(getClass().getClassLoader().getResource(schemaPath).toURI())));
                Schema schema = SchemaLoader.load(new JSONObject(schemaString));

                JSONArray jsonArray = new JSONArray(jsonResponse);
                for (int i = 0; i < jsonArray.length(); i++) {
                        schema.validate(jsonArray.getJSONObject(i)); // Throws exception if validation fails
                }
        }

        @Test
        void testCreateGroup() throws Exception {
                Group group = new Group("Group1", List.of("student1", "student2"));

                // Use `any()` to match any Group object
                when(groupRepository.save(org.mockito.ArgumentMatchers.any(Group.class))).thenReturn(group);

                mockMvc.perform(post("/api/groups")
                                .contentType("application/json")
                                .content("{\"name\":\"Group1\",\"studentIds\":[\"student1\",\"student2\"]}"))
                                .andExpect(status().isCreated()) // Expect 201 Created
                                .andExpect(jsonPath("$.name").value("Group1"))
                                .andExpect(jsonPath("$.studentIds.size()").value(2));
        }
}