package me.xyzlast.domain.services;

import static org.junit.Assert.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import me.xyzlast.domain.DomainConfiguration;
import me.xyzlast.domain.entities.Player;
import me.xyzlast.domain.vo.DayGameTable;
import me.xyzlast.domain.vo.MatchNumberCalculateResult;
import org.joda.time.DateTime;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
@ContextConfiguration(classes = DomainConfiguration.class)
public class GameStatisticsServiceImplTest {
    @Autowired
    private GameStatisticsService gameStatisticsService;

    @Before
    public void setUp() {
        assertThat(gameStatisticsService, is(notNullValue()));
    }

    @Test
    public void listupResult() {
        Date start = (new DateTime(2014, 11, 6, 0, 0, 0, 0)).toDate();
        Date end = (new DateTime(2014, 11, 10, 0, 0, 0, 0)).toDate();

        List<DayGameTable> gameTable = gameStatisticsService.listupResult(start, end);
        assertThat(gameTable.size(), is(not(0)));
        DayGameTable game = gameTable.get(0);
        assertThat(game.getMatchPlayerName(), is("강민석"));
    }

    @Test
    public void calculateByPlayer() {
        Date start = (new DateTime(2014, 11, 6, 0, 0, 0, 0)).toDate();
        Date end = (new DateTime(2014, 11, 10, 0, 0, 0, 0)).toDate();
        List<Player> players = gameStatisticsService.calculateByPlayer(start, end);
        for(Player player : players) {
            System.out.println(String.format("%s : %d", player.getName(), player.getAmount()));
        }
    }

    @Test
    public void calculateByNumber() {
        Date start = (new DateTime(2014, 11, 6, 0, 0, 0, 0)).toDate();
        Date end = (new DateTime(2014, 11, 10, 0, 0, 0, 0)).toDate();

        List<MatchNumberCalculateResult> result = gameStatisticsService.calculateByMachingNumber(start, end);
        for(MatchNumberCalculateResult r : result) {
            System.out.println(String.format("%s : %d", r.getNumber(), r.getCount()));
        }
    }
}