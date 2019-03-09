package ua.ali_x.memberservice.service;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.ali_x.memberservice.model.Member;
import ua.ali_x.memberservice.repository.MemberRepository;

import java.util.List;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MongoGridFsService mongoGridFsService;


    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public void delete(ObjectId id) {
        Member member = memberRepository.findBy_id(id);

        if (member != null) {
            mongoGridFsService.deleteFile(member.getPictureId());
            memberRepository.delete(member);
        }
    }

    public Member save(ObjectId id, Member member, MultipartFile file) {
        String fileId = mongoGridFsService.saveFile(file);

        if (StringUtils.isNotBlank(fileId)) {
            member.setPictureId(fileId);
        }

        member.set_id(id);

        return memberRepository.save(member);
    }

    public Member findById(ObjectId id) {
        return memberRepository.findBy_id(id);
    }
}
