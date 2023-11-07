package com.example.demo.entity.Chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
@AllArgsConstructor // constructor 생성
@NoArgsConstructor // getter, setter 생성
public class Chat {

    @Id @GeneratedValue
    @Column(name = "CHAT_ID")
    private Long id;

    // 이 부분은 name으로 하되, 데이터를 가져올 때 직접 쿼리를 작성해야 될 것 같습니다.
    // JPA로 user_sender_id랑 user_responder_name으로 자동쿼리 생성하는 방법이 떠오르지 않아서
    // 차라리 회원 인증 토큰에 name을 나중에 만들어서 넣을테니까 로그인돼있으면 그걸로 name 받아서
    // 이거 아래 틀릴 수는 있는데 일단 대충 로직 작성하면
    // SELECT * FROM Chat c, User u where u.user_name = c.user_sender_name
    // or u.user_name = c.user_sender_name
    // 이런 식으로 가져와서 목록을 그냥 띄워버리죠.
    // Repository 설계할 때 한번 데이터 넣고 해보면 될 것 같습니다.
    private String userSenderName;

    private String userResponderName;

    @OneToMany(mappedBy = "chat")
    @Builder.Default
    private List<ChatText> chatTexts = new ArrayList<>();
}
