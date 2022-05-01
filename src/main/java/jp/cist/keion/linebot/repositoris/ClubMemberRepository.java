package jp.cist.keion.linebot.repositoris;

import jp.cist.keion.linebot.models.ClubMember;
import org.springframework.data.repository.CrudRepository;

public interface ClubMemberRepository extends CrudRepository<ClubMember, Integer> {
}
