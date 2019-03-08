package ua.ali_x.memberservice.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import ua.ali_x.memberservice.model.Member;

public interface MemberRepository extends MongoRepository<Member, String> {
    Member findBy_id(ObjectId id);
}
