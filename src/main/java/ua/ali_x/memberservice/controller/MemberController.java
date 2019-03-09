package ua.ali_x.memberservice.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.ali_x.memberservice.model.Member;
import ua.ali_x.memberservice.service.MemberService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Member> getAll() {
        return memberService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable ObjectId id) {
        memberService.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public Member updateById(@Valid @ModelAttribute Member member, @PathVariable("id") ObjectId id, @RequestParam(value = "file", required = false) MultipartFile file) {
        return memberService.save(id, member, file);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Member create(@Valid @ModelAttribute Member member, @RequestParam(value = "file", required = false) MultipartFile file) {
        return memberService.save(ObjectId.get(), member, file);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Member getById(@PathVariable("id") ObjectId id) {
        return memberService.findById(id);
    }
}
