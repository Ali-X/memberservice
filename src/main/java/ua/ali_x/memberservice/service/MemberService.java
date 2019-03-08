package ua.ali_x.memberservice.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ali_x.memberservice.model.Member;
import ua.ali_x.memberservice.repository.MemberRepository;

import java.util.List;

@Service
public class MemberService {
    @Autowired
    private MemberRepository repository;


    public List<Member> findAll() {
        return repository.findAll();
    }

    public void delete(ObjectId id) {
        repository.delete(repository.findBy_id(id));
    }

    public void save(ObjectId id, Member member) {
        member.set_id(id);
        repository.save(member);
    }

    public Member findById(ObjectId id) {
        return repository.findBy_id(id);
    }
}
