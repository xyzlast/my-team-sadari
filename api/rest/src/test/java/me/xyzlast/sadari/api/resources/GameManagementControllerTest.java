package me.xyzlast.sadari.api.resources;

import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GameManagementControllerTest extends AbstractBaseController {
    @Before
    public void setUp() {
        init();
    }

    @Test
    public void getEditData() throws Exception {
        getMockMvc().perform(get("/game/edit/4"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}