package me.xyzlast.sadari.api.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GameListControllerTest extends AbstractBaseController {
    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        init();
    }

    @Test
    public void getDayGames() throws Exception {
        MvcResult result = getMockMvc().perform(get("/game/day")
                .param("year", "2014")
                .param("month", "11")
                .param("day", "10"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
}