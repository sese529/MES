package com.B1team.b01.service;


import com.B1team.b01.dto.WorderDto;
import com.B1team.b01.dto.WplanDto;
import com.B1team.b01.entity.Rorder;
import com.B1team.b01.entity.Worder;
import com.B1team.b01.repository.RorderRepository;
import com.B1team.b01.repository.WorderRepository;
import com.B1team.b01.repository.WplanRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.time.LocalTime.now;


@RequiredArgsConstructor
@Service

public class WorderService {
    @Autowired
    private final WorderRepository worderRepository;
    private final RorderRepository rorderRepository;
    private final WplanRepository wplanRepository;

    @Autowired
    private MprocessService mprocessService;


    private String processId;
    private int i;

    @PersistenceContext
    private EntityManager entityManager;

    //문자열 시퀀스 메소드

    public String makeStringId() {
        BigDecimal sequenceValue = (BigDecimal) entityManager.createNativeQuery("SELECT worder_seq.NEXTVAL FROM dual").getSingleResult();
        String id = "WORD" + sequenceValue;
        return id;

    }

    //현재 시간에 작동중인 설비가 있는지 체크 //현재 작업지시 상태 조회
    public String checkWorder(String processId, int i){  //단일 설비에는 0을 적용하고, 1을 넣으면 설비 2개중 2번째를 선택하게됨

        List <Worder> nowWorderTime = worderRepository.findByState(processId); //시작시간과 끝시간을 받아옴
        //현재 작업지시 상태
        if (nowWorderTime.get(i).getStartDate().isAfter(LocalDateTime.now())) {
            return "진행대기";//현재시간이 시작시간보다 나중인 경우
        }else if(nowWorderTime.get(i).getFinishDate().isBefore(LocalDateTime.now())){ //현재시간이 종료시간보다 뒤인 경우 = 공정 종료 = 사용중인 설비가 없음
            System.out.println(processId + "완료");
            return "완료";
        } else{
            System.out.println(processId + "진행중");
            return "진행중";
        }
    }







    //작업지시 등록메소드
    public List<WorderDto> doWorder(String orderId, LocalDateTime materialReadyDate, String productId) {

        //작업지시를 내릴 '진행대기' 상태의 작업 계획이 있는지 조회(수주번호가 필요한?)
        Optional<WplanDto> result = Optional.ofNullable(wplanRepository.findByOrderId(orderId));
        System.out.println("조회" + result);


        // 있다면 작업지시 등록하기
        Optional<Rorder> rorder = rorderRepository.findById(orderId);
        Rorder rorderList = rorder.get();

        List<WorderDto> worderDtos = mprocessService.calculateWorderDate(materialReadyDate, productId);

/*
        if (result.isPresent()) {

            WorderDto worderDto = new WorderDto();

            //원료계량
            worderDto.setId(makeStringId()); //작업지시 고유번호
//품목명
            worderDto.setProcessId(worderDtos.get(0).getProcessId());//공정 고유번호
            worderDto.setWplanId(result.get().getId()); //작업계획코드 = 작업계획 고유번호
            worderDto.setFacilityId(worderDtos.get(0).getFacilityId()); //설비정보 고유번호
            worderDto.setStartDate(worderDtos.get(0).getStartDate()); //작업시작일자
            worderDto.setFinishDate(worderDtos.get(0).getFinishDate()); //작업종료일자

            worderRepository.save((worderDto.toEntity()));


            //전처리
            worderDto.setId(makeStringId()); //작업지시 고유번호
            worderDto.setProcessId(worderDtos.get(1).getProcessId());//공정 고유번호
            worderDto.setWplanId(result.get().getId()); //작업계획코드 = 작업계획 고유번호
            worderDto.setFacilityId(worderDtos.get(1).getFacilityId()); //설비정보 고유번호
            worderDto.setStartDate(worderDtos.get(1).getStartDate()); //작업시작일자
            worderDto.setFinishDate(worderDtos.get(1).getFinishDate()); //작업종료일자

            if("완료".equals(checkWorder(processId, i))) { //1번째 설비
                worderDto.setFacilityId(worderDtos.get(3).getFacilityId()); //설비정보 고유번호
                worderDto.setStartDate(worderDtos.get(3).getStartDate()); //작업시작일자
                worderDto.setFinishDate(worderDtos.get(3).getFinishDate()); //작업종료일자

            } else if("진행대기".equals(checkWorder(processId, i)) || "진행중".equals(checkWorder(processId, i)))  //2번째 설비
                worderDto.setFacilityId(worderDtos.get(4).getFacilityId()); //설비정보 고유번호
                worderDto.setStartDate(worderDtos.get(4).getStartDate()); //작업시작일자
                worderDto.setFinishDate(worderDtos.get(4).getFinishDate()); //작업종료일자
        }


            //worderDto.setProcessId(rorderList.getProductId()); //품목코드
            //worderDto.setFacilityId("A102"); //공정 고유번호
            //worderDto.setWplanId(result.get().getId()); //작업계획코드 = 작업계획 고유번호
            //worderDto.setFacilityId("FAC79"); //설비코드 = 포장작업대
            //worderDto.setStartDate(result.get().getStartDate()); //작업시작일자
            //worderDto.setFinishDate(result.get().getEndDate()); //작업종료일자

            worderRepository.save((worderDto.toEntity()));


            //액상추출
            worderDto.setId(makeStringId()); //작업지시 고유번호
            worderDto.setProcessId(worderDtos.get(3).getProcessId());//공정 고유번호
            worderDto.setWplanId(result.get().getId()); //작업계획코드 = 작업계획 고유번호

           if("완료".equals(checkWorder(processId, i))) { //1번째 설비
               worderDto.setFacilityId(worderDtos.get(3).getFacilityId()); //설비정보 고유번호
               worderDto.setStartDate(worderDtos.get(3).getStartDate()); //작업시작일자
               worderDto.setFinishDate(worderDtos.get(3).getFinishDate()); //작업종료일자

           } else if("진행대기".equals(checkWorder(processId, i)) || "진행중".equals(checkWorder(processId, i)))  //2번째 설비
                    worderDto.setFacilityId(worderDtos.get(4).getFacilityId()); //설비정보 고유번호
                    worderDto.setStartDate(worderDtos.get(4).getStartDate()); //작업시작일자
                    worderDto.setFinishDate(worderDtos.get(4).getFinishDate()); //작업종료일자
            }




            worderDto.setId(makeStringId()); //작업지시 고유번호
            worderDto.setProcessId(rorderList.getProductId()); //품목코드
            worderDto.setFacilityId("A103"); //공정 고유번호
            worderDto.setWplanId(result.get().getId()); //작업계획코드 = 작업계획 고유번호
            if()){ //설비 등록 예약 혹은 사용중이라 쓸 수 없음
                worderDto.setFacilityId("FAC67"); //설비코드 = 추출기1
            }else{
                worderDto.setFacilityId("FAC68"); //설비코드 = 추출기2
            }
            worderDto.setStartDate(result.get().getStartDate()); //작업시작일자
            worderDto.setFinishDate(result.get().getEndDate()); //작업종료일자

            worderRepository.save((worderDto.toEntity()));

        }

        return worderDtos;
    }
*/


        //새로 파기
        if (result.isPresent()) {
            WorderDto worderDto = new WorderDto();

            for(int i = 0; i<worderDtos.size(); i++){
                worderDto.setId(makeStringId()); //작업지시 고유번호
                worderDto.setProcessId(worderDtos.get(i).getProcessId());//공정 고유번호
                worderDto.setWplanId(result.get().getId()); //작업계획코드 = 작업계획 고유번호

                    if("완료".equals(checkWorder(worderDtos.get(i).getProcessId(), i))) { //1번째 설비
                        worderDto.setFacilityId(worderDtos.get(i).getFacilityId()); //설비정보 고유번호
                        worderDto.setStartDate(worderDtos.get(i).getStartDate()); //작업시작일자
                        worderDto.setFinishDate(worderDtos.get(i).getFinishDate()); //작업종료일자

                    } else if("진행대기".equals(checkWorder(worderDtos.get(i).getProcessId(), i)) || "진행중".equals(checkWorder(processId, i)))  //2번째 설비
                        worderDto.setFacilityId(worderDtos.get(i+1).getFacilityId()); //설비정보 고유번호
                        worderDto.setStartDate(worderDtos.get(i+1).getStartDate()); //작업시작일자
                        worderDto.setFinishDate(worderDtos.get(i+1).getFinishDate()); //작업종료일자

                    }

                worderRepository.save((worderDto.toEntity()));
            }

        return worderDtos;
    }
}

