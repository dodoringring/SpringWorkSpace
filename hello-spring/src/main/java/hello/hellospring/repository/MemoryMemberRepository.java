package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    //멤버담아줄 맵을 선언
    private static Map<Long, Member> store=new HashMap<>();
    private static long sequence = 0L;//0,1,2생성해주는 애다 시퀀스
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //스토어에서 꺼낸다. 결과가 없으면 null이 반환될 가능성이 있다. 옵셔널로 감싼다. null이어도 감쌀수있다.
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        //스토어에서 루프를 돌린다. 필터를 사용(람다식) 파라미터로 넘어온 name이랑 같은지 찾는다.
        // 끝까지 돌렸는데 없으면 옵셔널로 반환된다.

        return store.values().stream()
                .filter(member -> member.getName().equals(name))//람다로 넘어온 네임과 파라미터로 넘어온 네임이 같은지
                .findAny();//결과 옵셔널로 반환. 없을때

    }

    @Override
    public List<Member> findAll() { //실무에서는 리스트 많이 쓴다. 편하다.
        return new ArrayList<>(store.values());//member들
    }
}

/*제대로 동작하는지 어떻게 알지? 테스트 케이스를 작성한다.*/