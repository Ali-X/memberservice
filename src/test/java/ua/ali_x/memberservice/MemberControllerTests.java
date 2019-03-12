package ua.ali_x.memberservice;

import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ua.ali_x.memberservice.controller.MemberController;
import ua.ali_x.memberservice.model.Member;
import ua.ali_x.memberservice.service.MemberService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MemberController.class)
public class MemberControllerTests {
    @Autowired
    private MockMvc mvc;

    @InjectMocks
    private MemberController memberController;

    @MockBean
    private MemberService memberService;

    @Test
    public void getMembers() throws Exception {
        Member member = createTestMember();

        List<Member> memberList = new ArrayList<>();
        memberList.add(member);

        given(memberService.findAll()).willReturn(memberList);

        mvc.perform(MockMvcRequestBuilders.get("/member/")
                .with(user("admin").password("admin"))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].fname", is(member.getFname())));
    }

    @Test
    public void getMemberById() throws Exception {
        Member member = createTestMember();

        given(memberService.findById(member.get_id())).willReturn(member);

        mvc.perform(MockMvcRequestBuilders.get("/member/" + member.get_id())
                .with(user("admin").password("admin"))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.fname", is(member.getFname())));
    }

    @Test
    public void deleteMemberById() throws Exception {
        Member member = createTestMember();

        mvc.perform(MockMvcRequestBuilders.delete("/member/" + member.get_id())
                .with(user("admin").password("admin"))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    private Member createTestMember() {
        Member testMember = new Member();
        testMember.set_id(ObjectId.get());
        testMember.setFname(getSaltString());
        testMember.setLname(getSaltString());

        return testMember;
    }

    private String getSaltString() {
        String saltchars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();

        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * saltchars.length());
            salt.append(saltchars.charAt(index));
        }

        return salt.toString();
    }
}