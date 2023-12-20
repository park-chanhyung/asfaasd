package com.coding.member.Entity;

import com.coding.member.Dto.MemberDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Getter
@Setter
@Table(name="member")
public class MemberEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    @Column(unique = true) // 제약조건 추가  // db에 테이블에 컬럼을 자동 추가
    private String memberId;
    @Column
    private String memberPassword;
    @Column
    private String memberName;
    @Column
    private String parentArea;
    //  private MemberEntity memberEntity;  요 이름과 매치
//    @OneToMany(mappedBy = "memberEntity" , cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
//    private List<BoardEntity> boardMemberList =new ArrayList<>();
    public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberId(memberDTO.getMemberId());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberName(memberDTO.getMemberName());
        memberEntity.setParentArea(memberDTO.getParentArea());
        return memberEntity;
    }
    public static MemberEntity toUpdateMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(memberDTO.getId());
        memberEntity.setMemberId(memberDTO.getMemberId());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberName(memberDTO.getMemberName());
        memberEntity.setParentArea(memberDTO.getParentArea());
        return memberEntity;
    }
}
