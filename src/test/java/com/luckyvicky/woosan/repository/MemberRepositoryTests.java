package com.luckyvicky.woosan.repository;

import com.luckyvicky.woosan.domain.member.entity.Member;
import com.luckyvicky.woosan.domain.member.entity.MemberType;
import com.luckyvicky.woosan.domain.member.entity.SocialType;
import com.luckyvicky.woosan.domain.member.repository.jpa.MemberRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void testAddMember() {
        IntStream.rangeClosed(1, 5).forEach(i -> {
            Member member = Member.builder()
                    .email("bit" + i + "@naver.com")
                    .nickname("유저" + i)
//                    .password(bCryptPasswordEncoder.encode("woosan" + i))
                    .password("woosan" + i)
                    .point(0)
                    .nextPoint(100)
                    .memberType(MemberType.USER)
                    .level(MemberType.Level.LEVEL_1)
                    .isActive(true)
                    .socialType(SocialType.NORMAL)
                    .build();

            log.info("member_id" + memberRepository.save(member).getId());
        });
    }

    @Test
    public void testRead() {
        String email = "bit1@naver.com";
        Member member = memberRepository.findByEmail(email);
        log.info("--------------------");
        log.info(member);
    }
}
