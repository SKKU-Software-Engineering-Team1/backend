package com.example.demo.entity.User;

import com.example.demo.entity.enums.TagType;

public class UserMethod {
    // String 형태로 받은 Tag 리스트를 변환
    public static UserTag strToUserTag(Users users, String strTag) {

        TagType userTagType = null;

        userTagType = TagType.valueOf(strTag);

        // 위 정보로 userTag 생성후 반환
        UserTag userTag = new UserTag();
        userTag.setUsers(users);
        userTag.setUserTag(userTagType);

        return userTag;
    }
}
