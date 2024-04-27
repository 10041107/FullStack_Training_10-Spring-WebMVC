package com.ohgiraffers.exception;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExceptionHandlerController {


    @GetMapping("controller-null")
    public String nullPointerExceptionTest(Model model) {
        String str = null;

        System.out.println(str.charAt(0));

        return "/main";
    }

    @ExceptionHandler(NullPointerException.class)
    public String nullPointerExceptionHandler(NullPointerException e){
        System.out.println("controller 레벨의 Exception 처리");
        return "error/nullPointer";
    }


    @GetMapping("controller-user")
    public String userException() throws MemberRegistException{
        boolean check = true;
        if(check){
            throw new MemberRegistException("입사가 불가능합니다.");
        }
        return "/";
    }

    @ExceptionHandler(MemberRegistException.class)
    public String userExceptionHandler(Model model, MemberRegistException exception){
        System.out.println("controller 레벨의 exception 처리");
        model.addAttribute("exception", exception);
        return "error/memberRegist";
    }


}
