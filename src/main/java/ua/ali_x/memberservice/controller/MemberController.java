package ua.ali_x.memberservice.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.ali_x.memberservice.model.Member;
import ua.ali_x.memberservice.service.MemberService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Member> getAllPets() {
        return service.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deletePet(@PathVariable ObjectId id) {
        service.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void modifyPetById(@PathVariable("id") ObjectId id, @Valid @RequestBody Member member) {
        service.save(id, member);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Member createPet(@Valid @RequestBody Member member) {
        service.save(ObjectId.get(), member);
        return member;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Member getPetById(@PathVariable("id") ObjectId id) {
        return service.findById(id);
    }
}
