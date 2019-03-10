package ua.ali_x.memberservice.controller;

import io.swagger.annotations.ApiOperation;
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

    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ApiOperation(value = "Find all members of company")
    public List<Member> getAll() {
        return memberService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete member of company by member id",
            notes = "Keep in mind that member id has ObjectId type")
    public void delete(@PathVariable ObjectId id) {
        memberService.delete(id);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.POST,
            produces = {"application/json", "application/xml"},
            consumes = {"multipart/form-data"})
    @ApiOperation(value = "Update member of company by member id",
            notes = "This is one of two methods to update the member. " +
                    "This method consumes only \"multipart/form-data\" Content-Type header. " +
                    "The response can be both in json and in xml formats. " +
                    "Keep in mind that id has ObjectId type")
    public Member updateById(@Valid @ModelAttribute Member member,
                             @PathVariable("id") ObjectId id,
                             @RequestParam(value = "file", required = false) MultipartFile file) {
        return memberService.save(id, member, file);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.POST,
            produces = {"application/json", "application/xml"},
            consumes = {"application/json", "application/xml"})
    @ApiOperation(value = "Update member of company by member id",
            notes = "This is one of two methods to update the member. " +
                    "This method consumes only json or xml Content-Type header. " +
                    "The response can be both in json and in xml formats. " +
                    "Keep in mind that id has ObjectId type")
    public Member updateById(@Valid @RequestBody Member member, @PathVariable("id") ObjectId id) {
        return memberService.save(id, member, null);
    }

    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.POST,
            produces = {"application/json", "application/xml"},
            consumes = {"application/json", "application/xml"})
    @ApiOperation(value = "Save new member of company",
            notes = "This is one of two methods to create new member. " +
                    "This method consumes only json or xml Content-Type header. " +
                    "The response can be both in json and in xml formats.")
    public Member create(@Valid @RequestBody Member member) {
        return memberService.save(ObjectId.get(), member, null);
    }

    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.POST,
            produces = {"application/json", "application/xml"},
            consumes = {"multipart/form-data"})
    @ApiOperation(value = "Save new member of company",
            notes = "This is one of two methods to create new member. " +
                    "This method consumes only \"multipart/form-data\" Content-Type header. " +
                    "The response can be both in json and in xml formats.")
    public Member create(@Valid @ModelAttribute Member member,
                         @RequestParam(value = "file", required = false) MultipartFile file) {
        return memberService.save(ObjectId.get(), member, file);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ApiOperation(value = "Find member of company by member id",
            notes = "The response can be both in json and in xml formats. " +
                    "Keep in mind that member id has ObjectId type")
    public Member getById(@PathVariable("id") ObjectId id) {
        return memberService.findById(id);
    }
}
