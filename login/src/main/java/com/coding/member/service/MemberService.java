package com.coding.member.service;

import com.coding.member.Dto.MemberDTO;
import com.coding.member.Entity.MemberEntity;
import com.coding.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void save(MemberDTO memberDTO) {
        // dto -> entity 로 변환
        // repository save 메서드 호출
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity);
        //repository의 save 메서도 호출 ( 조건 . entity객체 )

    }

    public String Idcheck(String memberId) {
        Optional<MemberEntity> byMemberId = memberRepository.findByMemberId(memberId);
        if (byMemberId.isPresent()) {
            // 조회결과가 있다 -> 사용할 수 없다.
            return "no";
        } else {
            // 조회결과가 없다 -> 사용할 수 있다.
            return "ok";
        }
    }

    public MemberDTO login(MemberDTO memberDTO) {
        Optional<MemberEntity> byMemberId = memberRepository.findByMemberId(memberDTO.getMemberId());

        if (byMemberId.isPresent()) {
            // 조회결과가 있다 -> 비밀번호 체크
            MemberEntity memberEntity = byMemberId.get();
            if (memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())) {
                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
                return dto;
            } else {
                // 조회결과가 없다 -> 사용할 수 있다.
                return null;
            }
        } else {
            return null;
        }
    }

    public MemberDTO updateForm(String myId) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberId(myId);
        if (optionalMemberEntity.isPresent()){
            return MemberDTO.toMemberDTO(optionalMemberEntity.get());
        }
        else {
            return null;
        }

    }

    public void update(MemberDTO memberDTO) {
        // update라는 메서드가 없어서 save 사용
        // save가 아이디가없으면 인서트 쿼리  근데 db에 있는 id entity 객체가 넘어오면
//        업데이트 쿼리 날림  지금은 업데이트 쿼리
        // entity에 setid 넣어줘야함
        memberRepository.save(MemberEntity.toUpdateMemberEntity(memberDTO));
    }

    public List<MemberDTO> findAll(){
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberDTO> memberDTOList=new ArrayList<>();
        //엔티티 -> 디티오 변환
        for(MemberEntity memberEntity : memberEntityList){
            memberDTOList.add(MemberDTO.toMemberDTO(memberEntity));

        }
        return memberDTOList;
    }
//    public MemberDTO findById(Long id){
//        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
//        if (optionalMemberEntity.isPresent()){
//            return MemberDTO.toMemberDTO(optionalMemberEntity.get());
//        }
//        else {
//            return  null;
//        }
//    }

    public MemberDTO findById(String myId) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberId(myId);
        if (optionalMemberEntity.isPresent()){
            return MemberDTO.toMemberDTO(optionalMemberEntity.get());
        }
        else {
            return null;
        }
    }

    public MemberDTO deleteById(String myId) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberId(myId);
        optionalMemberEntity.ifPresent(memberRepository::delete);
        return null;
    }
}
