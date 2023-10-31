package com.example.demo.entity.User;

import com.example.demo.entity.enums.GenderType;
import com.example.demo.entity.enums.SchoolType;
import com.example.demo.entity.enums.UserTagType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@Entity
@AllArgsConstructor // constructor 생성
@NoArgsConstructor // getter, setter 생성
public class Users implements UserDetails {

    // 혹시나 나중에 수정할지도 몰라서 전혀 관련이 없는 DB 자체 내부 설정값을 PK로 두겠습니다.
    // 다른 Table에서도 id를 사용할 예정이니까. 얘만 이름을 따로 설정할게요.
    @Id @GeneratedValue
    @Column(name = "USERS_ID")
    private Long Id;

    @NotNull
    private String userEmail; // DB 내부 user_Email로 변경

    @NotNull
    private String userPassword; // DB 내부 user_password로 변경


    private String userName; // DB 내부 user_name으로 변경

    // 이거 어디에는 있고 어디에는 없어서 그냥 일단 넣었습니다. 2023/10/28 백명규
    private String userPhonenumber;

    // 이것도 로그인 화면에는 안 보이는데 일단 넣었습니다. 2023/10/28 백명규
    @Enumerated(EnumType.STRING)
    private SchoolType userSchool;

    private int userAge;

    // Enum 타입이 되 STRING으로 저장함.
    // 성별은 수정될 일이 없지만 다른 경우에 요청 사항이 늘어나면 이렇게 작성할 예정.
    @Enumerated(EnumType.STRING)
    private GenderType userGender;

    // User 1개 당 여러 개의 UserTag를 가짐. 그렇기 때문에 @OneToMany 어노테이션
    @OneToMany(mappedBy = "users")
    @Builder.Default
    private List<UserTag> userTags = new ArrayList<>();


    // 여기는 권한과 관련된 부분을 넣을 거고 우선 USER와 ADMIN으로만 구분했습니다.
    // Authorities를 구현하기 위해서 만들었어요. 약간 Table 내용이 바뀌겠네요.
    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();


    // User와 String을 받으면 해당하는 userTag List 작성
    public static List<UserTag> makeUserTags(Users users, List<String> userTags){

        List<UserTag> userTagsList = new ArrayList<>();

        for(int i = 0; i < userTags.size(); i++){

            String str = userTags.get(i);
            UserTag TempUserTag = UserMethod.strToUserTag(users, str);

            userTagsList.add(TempUserTag);
        }

        return userTagsList;
    }

    // roles 리스트를 가지고서 Spring Security의 Authorities를 설정할 수 있습니다.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public String getUsername() {
        return userEmail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
