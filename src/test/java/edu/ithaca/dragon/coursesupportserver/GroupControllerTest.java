package edu.ithaca.dragon.coursesupportserver;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
                // Mock groups
                String subgroups1 = "[[ally, kate, brendan]; [sasha, connie, eren]]";
                String subgroups2 = "[[john, jane]; [alex, emily]]";
                List<Group> groups = List.of(
                                new Group("2023-10-01", subgroups1),
                                new Group("2023-10-02", subgroups2));
                when(groupRepository.findAll()).thenReturn(groups);

                // Perform GET request
                mockMvc.perform(get("/api/groups"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.size()").value(2))
                                .andExpect(jsonPath("$[0].name").value("2023-10-01"))
                                .andExpect(jsonPath("$[0].subgroups").value(subgroups1))
                                .andExpect(jsonPath("$[1].name").value("2023-10-02"))
                                .andExpect(jsonPath("$[1].subgroups").value(subgroups2));
        }

        @Test
        void testGetGroupByName() throws Exception {
                // Mock group
                String subgroups = "[[ally, kate, brendan]; [sasha, connie, eren]]";
                Group group = new Group("2023-10-01", subgroups);
                when(groupRepository.findByName("2023-10-01")).thenReturn(java.util.Optional.of(group));

                // Perform GET request
                mockMvc.perform(get("/api/groups/by-name/2023-10-01"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.name").value("2023-10-01"))
                                .andExpect(jsonPath("$.subgroups").value(subgroups));
        }
}