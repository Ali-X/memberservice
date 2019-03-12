package ua.ali_x.memberservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ua.ali_x.memberservice.controller.MemberController;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MemberController.class)
public class AuthenticationTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private MemberController memberController;

    @Test
    public void testAuthenticationIsPresent() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/member/")
                .with(user("admin").password("admin"))
                .contentType(APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    public void testAuthenticationIsAbsent() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/member/")
                .contentType(APPLICATION_JSON))
                .andExpect(status().is(401));
    }
}
