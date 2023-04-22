package project.board.crew.logic.structure.member;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberService {
    private MemberRepository memberRepository;
    public Member findMemberByEmail(String email)
    {
        return memberRepository.findMemberByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Member doesnt exist"));
    }
}
