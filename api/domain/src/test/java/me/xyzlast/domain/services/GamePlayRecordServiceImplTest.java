package me.xyzlast.domain.services;

import static org.junit.Assert.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.xyzlast.domain.DomainConfiguration;
import me.xyzlast.domain.entities.Game;
import me.xyzlast.domain.entities.Player;
import me.xyzlast.domain.entities.PlayerResult;
import me.xyzlast.domain.vo.MonthGameTable;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SuppressWarnings("unused")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DomainConfiguration.class})
public class GamePlayRecordServiceImplTest {
    @Autowired
    private GamePlayRecordService service;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PlayerManagementService playerManagementService;

    @Before
    public void setUp() {
        assertThat(service, is(notNullValue()));
    }

    @Test
    public void recordNewGame() throws Exception {
        List<Player> players = playerManagementService.list();
        List<PlayerResult> playerResults = new ArrayList<>();
        Integer number = 1;
        for(Player player : players) {
            PlayerResult result = new PlayerResult();
            result.setPlayer(player);
            result.setOwnNumber(number.toString());
            number++;

            playerResults.add(result);
        }

        Game game = service.recordNewGame(new Date(), "5", 5900, "주진영책임 걸리다.", playerResults);
        assertThat(game, is(notNullValue()));
    }

    @Test
    public void getMonthResult() throws Exception {
        MonthGameTable monthResult = service.getMonthResult(2014, 11);
        assertThat(monthResult, is(notNullValue()));
        System.out.println(objectMapper.writeValueAsString(monthResult));
    }

    @Test
    public void getByDays() throws Exception {
        List<Game> games = service.getGamesByDays(2014, 11, 10);
        for(Game game : games) {
            game.getResults().forEach(System.out::println);
        }
    }

    @Test
    public void getGame() throws Exception {
        Game game = service.read(1L);
        assertThat(game, is(notNullValue()));
        for(PlayerResult playerResult : game.getResults()) {
            assertThat(playerResult.getPlayer(), is(notNullValue()));
        }
    }
}