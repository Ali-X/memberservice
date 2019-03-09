package ua.ali_x.memberservice.controller;

import com.mongodb.gridfs.GridFSDBFile;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.ali_x.memberservice.model.Member;
import ua.ali_x.memberservice.service.MemberService;
import ua.ali_x.memberservice.service.MongoGridFsService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private MongoGridFsService mongoGridFsService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Member> getAll() {
        return memberService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable ObjectId id) {
        memberService.delete(id);

        return ResponseEntity.ok("Success!");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResponseEntity<Member> updateById(@Valid @ModelAttribute Member member, @PathVariable("id") ObjectId id, @RequestParam(value = "file", required = false) MultipartFile file) {
        return ResponseEntity.ok(memberService.save(id, member, file));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Member> create(@Valid @ModelAttribute Member member, @RequestParam(value = "file", required = false) MultipartFile file) {
        return ResponseEntity.ok(memberService.save(ObjectId.get(), member, file));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> getById(@PathVariable("id") ObjectId id) {
        Member member = memberService.findById(id);

        if (member != null) {
            GridFSDBFile gridFSDBFile = mongoGridFsService.retrieveImageFile(member.getPictureId());

            return ResponseEntity.ok()
                    .body("Member: " + member + "\nPicture: " + gridFSDBFile.getFilename());
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body("Member is not found!");
    }
}
