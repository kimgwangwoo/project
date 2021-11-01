package com.woo.project.validator;

import com.woo.project.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

@Component
public class UserValidator implements Validator{

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }



    @Override
    public void validate(Object obj, Errors errors) {

        User u = (User) obj;
        if (StringUtils.isEmpty(u.getMem_name())) {
            errors.rejectValue("mem_name", "key", "이름을 입력하세요:");
        }
        if (StringUtils.isEmpty(u.getUsername())) {
            errors.rejectValue("username", "key", "아이디를 입력하세요: ");
        }
        if (StringUtils.isEmpty(u.getPassword())) {
            errors.rejectValue("password", "key", "비밀번호를 입력하세요: ");
        }
        if (StringUtils.isEmpty(u.getMem_phone())) {
            errors.rejectValue("mem_phone", "key", "전화번호를 입력하세요: ");
        }
        if (StringUtils.isEmpty(u.getMem_address())) {
            errors.rejectValue("mem_address", "key", "주소를 입력하세요: ");
        }

    }

}
