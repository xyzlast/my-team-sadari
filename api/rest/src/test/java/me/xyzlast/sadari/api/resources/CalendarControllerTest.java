package me.xyzlast.sadari.api.resources;

import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.xyzlast.sadari.api.aop.ResultData;
import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

public class CalendarControllerTest extends AbstractBaseController {
    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        init();
    }

    @Test
    public void getCalendarData() throws Exception {
        MvcResult mvcResult = getMockMvc().perform(get("/game/calendar").param("yearMonth", "201411"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        ResultData resultData = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ResultData.class);
        assertThat(resultData.isOk(), is(true));
    }
}