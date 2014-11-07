package me.xyzlast.domain.vo;

import static org.junit.Assert.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import me.xyzlast.domain.entities.Game;
import me.xyzlast.domain.entities.Player;
import me.xyzlast.domain.entities.PlayerResult;
import org.joda.time.DateTime;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SuppressWarnings("unused")
public class DayGameTableTest {
    @Test
    public void generateDayGameTable() {
        Game game = new Game();
        game.setDate((new DateTime(2014, 11, 8, 12, 0, 0)).toDate());
        game.setMatchingNumber("6");

        for(int i = 0 ; i < 10 ; i++) {
            PlayerResult result = new PlayerResult();
            result.setOwnNumber(String.format("%d", i));

            Player player = new Player();
            player.setName(String.format("name-%d", i));
            result.setPlayer(player);

            game.getResults().add(result);
        }

        DayGameTable dayGameTable = new DayGameTable(game);
//        String expectedOutput = "DayGameTable{date='2014-11-08', playerNames='name-0(0),name-1(1),name-2(2),name-3(3),name-4(4),name-5(5),name-6(6),name-7(7),name-8(8),name-9(9)', matchPlayerName='name-6', matchNumber='6'}";
//        assertThat(dayGameTable.toString(), is(expectedOutput));
        assertThat(dayGameTable.isValid(), is(true));
        assertThat((new DayGameTable()).isValid(), is(false));
    }
}