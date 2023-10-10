package hello.hellospring.repository;

import hello.hellospring.domain.Member;
//import org.assertj.core.api.Assertions;
import static org.assertj.core.api.Assertions.*;//이렇게하면 Assertions앞에 안붙여도 된다.
import org.junit.jupiter.api.Test;//제이유닛

class MemoryMemberRepositoryTest {
    MemberRepository repository=new MemoryMemberRepository();

    /*메인메소드 쓰는거랑 비슷하다*/
    @Test
    public void save(){
        Member member= new Member();
        member.setName("spring");

        repository.save(member);

        Member result=repository.findById(member.getId()).get();//꺼낸다
//       org.junit.jupiter.api.Assertions.assertEquals(member, result);//첫번째 파라미터 넣는것 두번째 기대하는값

        assertThat(member).isEqualTo(result); //왜 안되는건지..

    }

    @Test
    public void findByName(){
        Member member1 =new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 =new Member(); //shift F6하면 Rename된다.
        member2.setName("spring2");
        repository.save(member2);

        Member result=repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }//리턴값이 잘못되있었다. 확인하고 잘 돌리자.
}
