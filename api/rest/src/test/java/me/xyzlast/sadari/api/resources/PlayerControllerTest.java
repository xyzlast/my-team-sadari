package me.xyzlast.sadari.api.resources;

import static org.junit.Assert.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import me.xyzlast.domain.DomainConfiguration;
import me.xyzlast.sadari.api.configs.ControllerConfiguration;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

public class PlayerControllerTest extends AbstractBaseController {
    @Before
    public void setUp() {
        init();
    }

    @Test
    public void list() throws Exception {
        MvcResult mvcResult = getMockMvc().perform(get("/players"))
                .andDo(print()).andExpect(status().isOk()).andReturn();

    }
}