package com.coding.member.Dto;

import com.coding.member.Entity.MemberEntity;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDTO {
    private Long id;
    private String memberId;
    private String memberPassword;
    private String memberName;
    private String parentArea;


    public static MemberDTO toMemberDTO(MemberEntity memberEntity){
        MemberDTO memberDTO=new MemberDTO();
        memberDTO.setId(memberEntity.getId());
        memberDTO.setMemberId(memberEntity.getMemberId());
        memberDTO.setMemberPassword(memberEntity.getMemberPassword());
        memberDTO.setMemberName(memberEntity.getMemberName());
        memberDTO.setParentArea(memberEntity.getParentArea());

       return memberDTO;
    }
}
