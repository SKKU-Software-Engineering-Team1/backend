package com.example.demo.entity.User;

import com.example.demo.entity.enums.UserTagType;

public class UserMethod {
    // String 형태로 받은 Tag 리스트를 변환
    public static UserTag strToUserTag(Users users, String strTag){

        UserTagType userTagType = null;

        // strTag
        if(strTag.equals("맛집"))
            userTagType = UserTagType.맛집;
        else if(strTag.equals("번개"))
            userTagType = UserTagType.번개;
        else if(strTag.equals("핫플"))
            userTagType = UserTagType.핫플;
        else if(strTag.equals("연합"))
            userTagType = UserTagType.연합;
        else if(strTag.equals("프로젝트"))
            userTagType = UserTagType.프로젝트;
        else if(strTag.equals("기획"))
            userTagType = UserTagType.기획;
        else if(strTag.equals("친목"))
            userTagType = UserTagType.친목;
        else if(strTag.equals("보드게임"))
            userTagType = UserTagType.보드게임;
        else if(strTag.equals("술"))
            userTagType = UserTagType.술;
        else if(strTag.equals("여행"))
            userTagType = UserTagType.여행;
        else if(strTag.equals("AI"))
            userTagType = UserTagType.AI;
            /////////////////////////////////////////
        else if(strTag.equals("부트캠프"))
            userTagType = UserTagType.부트캠프;
        else if(strTag.equals("블로그"))
            userTagType = UserTagType.블로그;
        else if(strTag.equals("스터디"))
            userTagType = UserTagType.스터디;
        else if(strTag.equals("멘토링"))
            userTagType = UserTagType.멘토링;
        else if(strTag.equals("개발"))
            userTagType = UserTagType.개발;
        else if(strTag.equals("데이터"))
            userTagType = UserTagType.데이터;
        else if(strTag.equals("수학"))
            userTagType = UserTagType.수학;
        else if(strTag.equals("세미나"))
            userTagType = UserTagType.세미나;
        else if(strTag.equals("튜터링"))
            userTagType = UserTagType.튜터링;
        else if(strTag.equals("경기도"))
            userTagType = UserTagType.경기도;
        else if(strTag.equals("노래"))
            userTagType = UserTagType.노래;
            ////////////////////////////////////////////////////////////
        else if(strTag.equals("버스킹"))
            userTagType = UserTagType.버스킹;
        else if(strTag.equals("정기공연"))
            userTagType = UserTagType.정기공연;
        else if(strTag.equals("밴드"))
            userTagType = UserTagType.밴드;
        else if(strTag.equals("수도권"))
            userTagType = UserTagType.수도권;
        else if(strTag.equals("애니메이션"))
            userTagType = UserTagType.애니메이션;
        else if(strTag.equals("덕질"))
            userTagType = UserTagType.덕질;
        else if(strTag.equals("만화카페"))
            userTagType = UserTagType.만화카페;
        else if(strTag.equals("서브컬쳐"))
            userTagType = UserTagType.서브컬쳐;
        else if(strTag.equals("모션그래픽"))
            userTagType = UserTagType.모션그래픽;
        else if(strTag.equals("SNS"))
            userTagType = UserTagType.SNS;
            ////////////////////////////////////////////////////
        else if(strTag.equals("독서"))
            userTagType = UserTagType.독서;
        else if(strTag.equals("영어회화"))
            userTagType = UserTagType.영어회화;
        else if(strTag.equals("전화"))
            userTagType = UserTagType.전화;
        else if(strTag.equals("합주"))
            userTagType = UserTagType.합주;
        else if(strTag.equals("온라인"))
            userTagType = UserTagType.온라인;
        else if(strTag.equals("채플"))
            userTagType = UserTagType.채플;
        else if(strTag.equals("선교"))
            userTagType = UserTagType.선교;
        else if(strTag.equals("수련회"))
            userTagType = UserTagType.수련회;
        else if(strTag.equals("사진"))
            userTagType = UserTagType.사진;
        else if(strTag.equals("출사"))
            userTagType = UserTagType.출사;
        else if(strTag.equals("부스"))
            userTagType = UserTagType.부스;
            ///////////////////////////////////////////////////////
        else if(strTag.equals("사진전"))
            userTagType = UserTagType.사진전;
        else if(strTag.equals("미디어"))
            userTagType = UserTagType.미디어;
        else if(strTag.equals("K_POP"))
            userTagType = UserTagType.K_POP;
        else if(strTag.equals("콘텐츠"))
            userTagType = UserTagType.콘텐츠;
        else if(strTag.equals("아이돌"))
            userTagType = UserTagType.아이돌;
        else if(strTag.equals("방송"))
            userTagType = UserTagType.방송;
        else if(strTag.equals("연예"))
            userTagType = UserTagType.연예;
        else if(strTag.equals("마케팅"))
            userTagType = UserTagType.마케팅;
        else if(strTag.equals("추리"))
            userTagType = UserTagType.추리;
        else if(strTag.equals("스누핑"))
            userTagType = UserTagType.스누핑;
            ////////////////////////////////////////////////////////
        else if(strTag.equals("방탈출"))
            userTagType = UserTagType.방탈출;
        else if(strTag.equals("향수"))
            userTagType = UserTagType.향수;
        else if(strTag.equals("견학"))
            userTagType = UserTagType.견학;
        else if(strTag.equals("축구"))
            userTagType = UserTagType.축구;
        else if(strTag.equals("풋살"))
            userTagType = UserTagType.풋살;
        else if(strTag.equals("태권도"))
            userTagType = UserTagType.태권도;
        else if(strTag.equals("체육대회"))
            userTagType = UserTagType.체육대회;
        else if(strTag.equals("자과캠"))
            userTagType = UserTagType.자과캠;
        else if(strTag.equals("명륜캠"))
            userTagType = UserTagType.명륜캠;
        else if(strTag.equals("당구"))
            userTagType = UserTagType.당구;
        else if(strTag.equals("심리학"))
            userTagType = UserTagType.심리학;
            ///////////////////////////////////
        else if(strTag.equals("굿즈"))
            userTagType = UserTagType.굿즈;
        else if(strTag.equals("제작"))
            userTagType = UserTagType.제작;
        else if(strTag.equals("불교"))
            userTagType = UserTagType.불교;
        else if(strTag.equals("템플스테이"))
            userTagType = UserTagType.템플스테이;
        else if(strTag.equals("법회"))
            userTagType = UserTagType.법회;
        else if(strTag.equals("문화생활"))
            userTagType = UserTagType.문화생활;
        else if(strTag.equals("전시회"))
            userTagType = UserTagType.전시회;
        else if(strTag.equals("글쓰기"))
            userTagType = UserTagType.합평;
        else if(strTag.equals("봉사"))
            userTagType = UserTagType.봉사;
            /////////////////////////////////////////
        else if(strTag.equals("유기동물"))
            userTagType = UserTagType.유기동물;
        else if(strTag.equals("야구"))
            userTagType = UserTagType.야구;
        else if(strTag.equals("공대"))
            userTagType = UserTagType.공대;
        else if(strTag.equals("문화"))
            userTagType = UserTagType.문화;
        else if(strTag.equals("예술"))
            userTagType = UserTagType.예술;
        else if(strTag.equals("매거진"))
            userTagType = UserTagType.매거진;
        else if(strTag.equals("파티"))
            userTagType = UserTagType.파티;
        else if(strTag.equals("디제잉"))
            userTagType = UserTagType.디제잉;
        else if(strTag.equals("성소수자"))
            userTagType = UserTagType.성소수자;
        else if(strTag.equals("퀴어"))
            userTagType = UserTagType.퀴어;
            /////////////////////////////////////////////
        else if(strTag.equals("영상"))
            userTagType = UserTagType.영상;
        else if(strTag.equals("촬영"))
            userTagType = UserTagType.촬영;
        else if(strTag.equals("포트폴리오"))
            userTagType = UserTagType.포트폴리오;
        else if(strTag.equals("바둑"))
            userTagType = UserTagType.바둑;
        else if(strTag.equals("걷기"))
            userTagType = UserTagType.걷기;
        else if(strTag.equals("농구"))
            userTagType = UserTagType.농구;
        else if(strTag.equals("음악"))
            userTagType = UserTagType.음악;
        else if(strTag.equals("감상"))
            userTagType = UserTagType.감상;
        else if(strTag.equals("클래식"))
            userTagType = UserTagType.클래식;
        else if(strTag.equals("연주회"))
            userTagType = UserTagType.연주회;
            /////////////////////////////////////////////////////
        else if(strTag.equals("영화"))
            userTagType = UserTagType.영화;
        else if(strTag.equals("스피치"))
            userTagType = UserTagType.스피치;
        else if(strTag.equals("발표"))
            userTagType = UserTagType.발표;
        else if(strTag.equals("레저스포츠"))
            userTagType = UserTagType.레저스포츠;
        else if(strTag.equals("풍물패"))
            userTagType = UserTagType.풍물패;
        else if(strTag.equals("국악"))
            userTagType = UserTagType.국악;
        else if(strTag.equals("교환학생"))
            userTagType = UserTagType.교환학생;
        else if(strTag.equals("시나리오"))
            userTagType = UserTagType.시나리오;
        else if(strTag.equals("작곡"))
            userTagType = UserTagType.작곡;
        else if(strTag.equals("합창"))
            userTagType = UserTagType.합창;
            /////////////////////////////////////
        else if(strTag.equals("발표"))
            userTagType = UserTagType.발표;
        else if(strTag.equals("역사"))
            userTagType = UserTagType.역사;
        else if(strTag.equals("피아노"))
            userTagType = UserTagType.피아노;
        else if(strTag.equals("연극"))
            userTagType = UserTagType.연극;
        else if(strTag.equals("연기"))
            userTagType = UserTagType.연기;
        else if(strTag.equals("지휘"))
            userTagType = UserTagType.지휘;
        else if(strTag.equals("미술"))
            userTagType = UserTagType.미술;
        else if(strTag.equals("스케치"))
            userTagType = UserTagType.스케치;
        else if(strTag.equals("창업"))
            userTagType = UserTagType.창업;
        else if(strTag.equals("loT")) // ????
            userTagType = UserTagType.loT;
        else if(strTag.equals("공모전"))
            userTagType = UserTagType.공모전;
            /////////////////////////////////////////////////////////////
        else if(strTag.equals("게임"))
            userTagType = UserTagType.게임;
        else if(strTag.equals("롤"))
            userTagType = UserTagType.롤;
        else if(strTag.equals("롤토체스"))
            userTagType = UserTagType.롤토체스;
        else if(strTag.equals("발로란트"))
            userTagType = UserTagType.발로란트;
        else if(strTag.equals("디자인"))
            userTagType = UserTagType.디자인;
        else if(strTag.equals("뮤지컬"))
            userTagType = UserTagType.뮤지컬;
        else if(strTag.equals("정보"))
            userTagType = UserTagType.정보;
        else if(strTag.equals("보안"))
            userTagType = UserTagType.보안;
        else if(strTag.equals("여자농구"))
            userTagType = UserTagType.여자농구;
        else if(strTag.equals("시사"))
            userTagType = UserTagType.시사;
            ////////////////////////////////////////
        else if(strTag.equals("사회"))
            userTagType = UserTagType.사회;
        else if(strTag.equals("토론"))
            userTagType = UserTagType.토론;
        else if(strTag.equals("베이커리"))
            userTagType = UserTagType.베이커리;
        else if(strTag.equals("빵"))
            userTagType = UserTagType.빵;
        else if(strTag.equals("피트니스"))
            userTagType = UserTagType.피트니스;
        else if(strTag.equals("포토샵"))
            userTagType = UserTagType.포토샵;
        else if(strTag.equals("배우"))
            userTagType = UserTagType.배우;
        else if(strTag.equals("극본"))
            userTagType = UserTagType.극본;
        else if(strTag.equals("만화"))
            userTagType = UserTagType.만화;
        else if(strTag.equals("그림"))
            userTagType = UserTagType.그림;
            /////////////////////////////////////////////
        else if(strTag.equals("고전"))
            userTagType = UserTagType.고전;
        else if(strTag.equals("인문"))
            userTagType = UserTagType.인문;
        else if(strTag.equals("러닝"))
            userTagType = UserTagType.러닝;
        else if(strTag.equals("배드민턴"))
            userTagType = UserTagType.배드민턴;
        else if(strTag.equals("강연"))
            userTagType = UserTagType.강연;
        else if(strTag.equals("고양이"))
            userTagType = UserTagType.고양이;
        else if(strTag.equals("댄스스포츠"))
            userTagType = UserTagType.댄스스포츠;
        else if(strTag.equals("댄스"))
            userTagType = UserTagType.댄스;
        else if(strTag.equals("라틴댄스"))
            userTagType = UserTagType.라틴댄스;
        else if(strTag.equals("마술"))
            userTagType = UserTagType.마술;
            /////////////////////////////////////////
        else if(strTag.equals("공연"))
            userTagType = UserTagType.공연;
        else if(strTag.equals("보컬"))
            userTagType = UserTagType.보컬;
        else if(strTag.equals("힙합"))
            userTagType = UserTagType.힙합;
        else if(strTag.equals("세션"))
            userTagType = UserTagType.세션;
        else if(strTag.equals("타로"))
            userTagType = UserTagType.타로;
        else if(strTag.equals("카드"))
            userTagType = UserTagType.카드;
        else if(strTag.equals("헬스"))
            userTagType = UserTagType.헬스;
        else if(strTag.equals("운동"))
            userTagType = UserTagType.운동;
        else if(strTag.equals("채식"))
            userTagType = UserTagType.채식;
        else if(strTag.equals("비건"))
            userTagType = UserTagType.비건;
        else if(strTag.equals("버킷리스트"))
            userTagType = UserTagType.버킷리스트;

        // 위 정보로 userTag 생성후 반환
        UserTag  userTag = new UserTag();
        userTag.setUsers(users);
        userTag.setUserTag(userTagType);

        return userTag;
    }
}
