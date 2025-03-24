package edu.ithaca.dragon.coursesupportserver;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

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
        List<Group> groups = Arrays.asList(new Group("Group1", Arrays.asList("student1", "student2")),
                new Group("Group2", Arrays.asList("student3", "student4")));
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
    void testCreateGroup() throws Exception {
        Group group = new Group("Group1", Arrays.asList("student1", "student2"));
        when(groupRepository.save(group)).thenReturn(group);

        mockMvc.perform(post("/api/groups")
                .contentType("application/json")
                .content("{\"name\":\"Group1\",\"studentIds\":[\"student1\",\"student2\"]}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Group1"))
                .andExpect(jsonPath("$.studentIds.size()").value(2));
    }
}