package com.example.demo.service;

import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.SignUpDto;
import com.example.demo.entity.User;
import com.example.demo.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    LoginRepository loginRepository;

    public ResponseDto<?> signUp(SignUpDto dto){

        String userEmail = dto.getUserEmail();
        String userPassword = dto.getUserPassword();
        String userName = dto.getUserName();
        String userPhoneNumber = dto.getUserPhoneNumber();

        // email이 DB 내부에 있는지만 확인
        User user = loginRepository.findByUserEmail(userEmail);

        if(user == null){
            System.out.println("it is not existed");
            try{
                // id는 pk인데 데이터베이스에서 관리하는 키 값이기 때문에 null로 생성
                // set~~로 해도 되는데 보기에도 좋지 않고 좋은 선택은 아님.
                // 이미 영속성 컨텍스트 내부에 있는 값을 set으로 변경하면 그거 호출마다 업데이트 쿼리가 자동으로 생성돼서 겁나 날라감.
                // user = new User(null, userEmail, userName, userPassword, userPhoneNumber);

                // save는 데이터 베이스에 저장하는 것을 의미함.
                loginRepository.save(user);
            } catch(Exception e){
                System.out.println("database Error");
            }
        } else{
            System.out.println("it is already existed");
            return ResponseDto.setSuccess("SignUp Failed", null);
        }

        return ResponseDto.setSuccess("SignUp Success!", dto);
    }
}