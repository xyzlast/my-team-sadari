package me.xyzlast.domain.services;

import static org.junit.Assert.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import me.xyzlast.domain.DomainConfiguration;
import me.xyzlast.domain.entities.Player;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SuppressWarnings("unused")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DomainConfiguration.class})
public class PlayerManagementServiceImplTest {
    @Autowired
    private PlayerManagementService playerManagementService;

    @Before
    public void setUp() {
        assertThat(playerManagementService, is(notNullValue()));
    }

    @Test
    public void addNewUser() {
        String name = "윤영권";
        Player player = playerManagementService.add(name);
        assertThat(player.getId(), is(notNullValue()));
        assertThat(player.getName(), is(name));
    }

    @Test
    public void list() {
        playerManagementService.list().forEach(System.out::println);
    }
}