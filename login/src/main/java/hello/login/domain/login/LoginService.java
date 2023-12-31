package hello.login.domain.login;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;

    /**
     * @return null이면 로그인 실패
     */
    public Member login(String longinId, String password) {
        /*Optional<Member> findMemberOptional = memberRepository.findByLoginId(longinId);
        Member member = findMemberOptional.get();
        if (member.getPassword().equals(password)) {
            return member;
        } else {
            return null;
        }*/

        return memberRepository.findByLoginId(longinId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);

    }
}
