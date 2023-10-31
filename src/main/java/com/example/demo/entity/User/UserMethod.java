package com.example.demo.entity.User;

import com.example.demo.entity.enums.TagType;

public class UserMethod {
    // String 형태로 받은 Tag 리스트를 변환
    public static UserTag strToUserTag(Users users, String strTag){

        TagType userTagType = null;

        // strTag
        if(strTag.equals("맛집"))
            userTagType = TagType.맛집;
        else if(strTag.equals("번개"))
            userTagType = TagType.번개;
        else if(strTag.equals("핫플"))
            userTagType = TagType.핫플;
        else if(strTag.equals("연합"))
            userTagType = TagType.연합;
        else if(strTag.equals("프로젝트"))
            userTagType = TagType.프로젝트;
        else if(strTag.equals("기획"))
            userTagType = TagType.기획;
        else if(strTag.equals("친목"))
            userTagType = TagType.친목;
        else if(strTag.equals("보드게임"))
            userTagType = TagType.보드게임;
        else if(strTag.equals("술"))
            userTagType = TagType.술;
        else if(strTag.equals("여행"))
            userTagType = TagType.여행;
        else if(strTag.equals("AI"))
            userTagType = TagType.AI;
            /////////////////////////////////////////
        else if(strTag.equals("부트캠프"))
            userTagType = TagType.부트캠프;
        else if(strTag.equals("블로그"))
            userTagType = TagType.블로그;
        else if(strTag.equals("스터디"))
            userTagType = TagType.스터디;
        else if(strTag.equals("멘토링"))
            userTagType = TagType.멘토링;
        else if(strTag.equals("개발"))
            userTagType = TagType.개발;
        else if(strTag.equals("데이터"))
            userTagType = TagType.데이터;
        else if(strTag.equals("수학"))
            userTagType = TagType.수학;
        else if(strTag.equals("세미나"))
            userTagType = TagType.세미나;
        else if(strTag.equals("튜터링"))
            userTagType = TagType.튜터링;
        else if(strTag.equals("경기도"))
            userTagType = TagType.경기도;
        else if(strTag.equals("노래"))
            userTagType = TagType.노래;
            ////////////////////////////////////////////////////////////
        else if(strTag.equals("버스킹"))
            userTagType = TagType.버스킹;
        else if(strTag.equals("정기공연"))
            userTagType = TagType.정기공연;
        else if(strTag.equals("밴드"))
            userTagType = TagType.밴드;
        else if(strTag.equals("수도권"))
            userTagType = TagType.수도권;
        else if(strTag.equals("애니메이션"))
            userTagType = TagType.애니메이션;
        else if(strTag.equals("덕질"))
            userTagType = TagType.덕질;
        else if(strTag.equals("만화카페"))
            userTagType = TagType.만화카페;
        else if(strTag.equals("서브컬쳐"))
            userTagType = TagType.서브컬쳐;
        else if(strTag.equals("모션그래픽"))
            userTagType = TagType.모션그래픽;
        else if(strTag.equals("SNS"))
            userTagType = TagType.SNS;
            ////////////////////////////////////////////////////
        else if(strTag.equals("독서"))
            userTagType = TagType.독서;
        else if(strTag.equals("영어회화"))
            userTagType = TagType.영어회화;
        else if(strTag.equals("전화"))
            userTagType = TagType.전화;
        else if(strTag.equals("합주"))
            userTagType = TagType.합주;
        else if(strTag.equals("온라인"))
            userTagType = TagType.온라인;
        else if(strTag.equals("채플"))
            userTagType = TagType.채플;
        else if(strTag.equals("선교"))
            userTagType = TagType.선교;
        else if(strTag.equals("수련회"))
            userTagType = TagType.수련회;
        else if(strTag.equals("사진"))
            userTagType = TagType.사진;
        else if(strTag.equals("출사"))
            userTagType = TagType.출사;
        else if(strTag.equals("부스"))
            userTagType = TagType.부스;
            ///////////////////////////////////////////////////////
        else if(strTag.equals("사진전"))
            userTagType = TagType.사진전;
        else if(strTag.equals("미디어"))
            userTagType = TagType.미디어;
        else if(strTag.equals("K_POP"))
            userTagType = TagType.K_POP;
        else if(strTag.equals("콘텐츠"))
            userTagType = TagType.콘텐츠;
        else if(strTag.equals("아이돌"))
            userTagType = TagType.아이돌;
        else if(strTag.equals("방송"))
            userTagType = TagType.방송;
        else if(strTag.equals("연예"))
            userTagType = TagType.연예;
        else if(strTag.equals("마케팅"))
            userTagType = TagType.마케팅;
        else if(strTag.equals("추리"))
            userTagType = TagType.추리;
        else if(strTag.equals("스누핑"))
            userTagType = TagType.스누핑;
            ////////////////////////////////////////////////////////
        else if(strTag.equals("방탈출"))
            userTagType = TagType.방탈출;
        else if(strTag.equals("향수"))
            userTagType = TagType.향수;
        else if(strTag.equals("견학"))
            userTagType = TagType.견학;
        else if(strTag.equals("축구"))
            userTagType = TagType.축구;
        else if(strTag.equals("풋살"))
            userTagType = TagType.풋살;
        else if(strTag.equals("태권도"))
            userTagType = TagType.태권도;
        else if(strTag.equals("체육대회"))
            userTagType = TagType.체육대회;
        else if(strTag.equals("자과캠"))
            userTagType = TagType.자과캠;
        else if(strTag.equals("명륜캠"))
            userTagType = TagType.명륜캠;
        else if(strTag.equals("당구"))
            userTagType = TagType.당구;
        else if(strTag.equals("심리학"))
            userTagType = TagType.심리학;
            ///////////////////////////////////
        else if(strTag.equals("굿즈"))
            userTagType = TagType.굿즈;
        else if(strTag.equals("제작"))
            userTagType = TagType.제작;
        else if(strTag.equals("불교"))
            userTagType = TagType.불교;
        else if(strTag.equals("템플스테이"))
            userTagType = TagType.템플스테이;
        else if(strTag.equals("법회"))
            userTagType = TagType.법회;
        else if(strTag.equals("문화생활"))
            userTagType = TagType.문화생활;
        else if(strTag.equals("전시회"))
            userTagType = TagType.전시회;
        else if(strTag.equals("글쓰기"))
            userTagType = TagType.합평;
        else if(strTag.equals("봉사"))
            userTagType = TagType.봉사;
            /////////////////////////////////////////
        else if(strTag.equals("유기동물"))
            userTagType = TagType.유기동물;
        else if(strTag.equals("야구"))
            userTagType = TagType.야구;
        else if(strTag.equals("공대"))
            userTagType = TagType.공대;
        else if(strTag.equals("문화"))
            userTagType = TagType.문화;
        else if(strTag.equals("예술"))
            userTagType = TagType.예술;
        else if(strTag.equals("매거진"))
            userTagType = TagType.매거진;
        else if(strTag.equals("파티"))
            userTagType = TagType.파티;
        else if(strTag.equals("디제잉"))
            userTagType = TagType.디제잉;
        else if(strTag.equals("성소수자"))
            userTagType = TagType.성소수자;
        else if(strTag.equals("퀴어"))
            userTagType = TagType.퀴어;
            /////////////////////////////////////////////
        else if(strTag.equals("영상"))
            userTagType = TagType.영상;
        else if(strTag.equals("촬영"))
            userTagType = TagType.촬영;
        else if(strTag.equals("포트폴리오"))
            userTagType = TagType.포트폴리오;
        else if(strTag.equals("바둑"))
            userTagType = TagType.바둑;
        else if(strTag.equals("걷기"))
            userTagType = TagType.걷기;
        else if(strTag.equals("농구"))
            userTagType = TagType.농구;
        else if(strTag.equals("음악"))
            userTagType = TagType.음악;
        else if(strTag.equals("감상"))
            userTagType = TagType.감상;
        else if(strTag.equals("클래식"))
            userTagType = TagType.클래식;
        else if(strTag.equals("연주회"))
            userTagType = TagType.연주회;
            /////////////////////////////////////////////////////
        else if(strTag.equals("영화"))
            userTagType = TagType.영화;
        else if(strTag.equals("스피치"))
            userTagType = TagType.스피치;
        else if(strTag.equals("발표"))
            userTagType = TagType.발표;
        else if(strTag.equals("레저스포츠"))
            userTagType = TagType.레저스포츠;
        else if(strTag.equals("풍물패"))
            userTagType = TagType.풍물패;
        else if(strTag.equals("국악"))
            userTagType = TagType.국악;
        else if(strTag.equals("교환학생"))
            userTagType = TagType.교환학생;
        else if(strTag.equals("시나리오"))
            userTagType = TagType.시나리오;
        else if(strTag.equals("작곡"))
            userTagType = TagType.작곡;
        else if(strTag.equals("합창"))
            userTagType = TagType.합창;
            /////////////////////////////////////
        else if(strTag.equals("발표"))
            userTagType = TagType.발표;
        else if(strTag.equals("역사"))
            userTagType = TagType.역사;
        else if(strTag.equals("피아노"))
            userTagType = TagType.피아노;
        else if(strTag.equals("연극"))
            userTagType = TagType.연극;
        else if(strTag.equals("연기"))
            userTagType = TagType.연기;
        else if(strTag.equals("지휘"))
            userTagType = TagType.지휘;
        else if(strTag.equals("미술"))
            userTagType = TagType.미술;
        else if(strTag.equals("스케치"))
            userTagType = TagType.스케치;
        else if(strTag.equals("창업"))
            userTagType = TagType.창업;
        else if(strTag.equals("loT")) // ????
            userTagType = TagType.loT;
        else if(strTag.equals("공모전"))
            userTagType = TagType.공모전;
            /////////////////////////////////////////////////////////////
        else if(strTag.equals("게임"))
            userTagType = TagType.게임;
        else if(strTag.equals("롤"))
            userTagType = TagType.롤;
        else if(strTag.equals("롤토체스"))
            userTagType = TagType.롤토체스;
        else if(strTag.equals("발로란트"))
            userTagType = TagType.발로란트;
        else if(strTag.equals("디자인"))
            userTagType = TagType.디자인;
        else if(strTag.equals("뮤지컬"))
            userTagType = TagType.뮤지컬;
        else if(strTag.equals("정보"))
            userTagType = TagType.정보;
        else if(strTag.equals("보안"))
            userTagType = TagType.보안;
        else if(strTag.equals("여자농구"))
            userTagType = TagType.여자농구;
        else if(strTag.equals("시사"))
            userTagType = TagType.시사;
            ////////////////////////////////////////
        else if(strTag.equals("사회"))
            userTagType = TagType.사회;
        else if(strTag.equals("토론"))
            userTagType = TagType.토론;
        else if(strTag.equals("베이커리"))
            userTagType = TagType.베이커리;
        else if(strTag.equals("빵"))
            userTagType = TagType.빵;
        else if(strTag.equals("피트니스"))
            userTagType = TagType.피트니스;
        else if(strTag.equals("포토샵"))
            userTagType = TagType.포토샵;
        else if(strTag.equals("배우"))
            userTagType = TagType.배우;
        else if(strTag.equals("극본"))
            userTagType = TagType.극본;
        else if(strTag.equals("만화"))
            userTagType = TagType.만화;
        else if(strTag.equals("그림"))
            userTagType = TagType.그림;
            /////////////////////////////////////////////
        else if(strTag.equals("고전"))
            userTagType = TagType.고전;
        else if(strTag.equals("인문"))
            userTagType = TagType.인문;
        else if(strTag.equals("러닝"))
            userTagType = TagType.러닝;
        else if(strTag.equals("배드민턴"))
            userTagType = TagType.배드민턴;
        else if(strTag.equals("강연"))
            userTagType = TagType.강연;
        else if(strTag.equals("고양이"))
            userTagType = TagType.고양이;
        else if(strTag.equals("댄스스포츠"))
            userTagType = TagType.댄스스포츠;
        else if(strTag.equals("댄스"))
            userTagType = TagType.댄스;
        else if(strTag.equals("라틴댄스"))
            userTagType = TagType.라틴댄스;
        else if(strTag.equals("마술"))
            userTagType = TagType.마술;
            /////////////////////////////////////////
        else if(strTag.equals("공연"))
            userTagType = TagType.공연;
        else if(strTag.equals("보컬"))
            userTagType = TagType.보컬;
        else if(strTag.equals("힙합"))
            userTagType = TagType.힙합;
        else if(strTag.equals("세션"))
            userTagType = TagType.세션;
        else if(strTag.equals("타로"))
            userTagType = TagType.타로;
        else if(strTag.equals("카드"))
            userTagType = TagType.카드;
        else if(strTag.equals("헬스"))
            userTagType = TagType.헬스;
        else if(strTag.equals("운동"))
            userTagType = TagType.운동;
        else if(strTag.equals("채식"))
            userTagType = TagType.채식;
        else if(strTag.equals("비건"))
            userTagType = TagType.비건;
        else if(strTag.equals("버킷리스트"))
            userTagType = TagType.버킷리스트;

        // 위 정보로 userTag 생성후 반환
        UserTag  userTag = new UserTag();
        userTag.setUsers(users);
        userTag.setUserTag(userTagType);

        return userTag;
    }
}
