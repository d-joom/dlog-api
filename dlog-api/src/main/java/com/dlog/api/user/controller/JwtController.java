package com.dlog.api.user.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dlog.api.user.Dto.LoginDto;
import com.dlog.api.user.model.User;
import com.dlog.api.user.service.UserService;
import com.dlog.api.utils.JwtTokenUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = {"login-controller"})
@RestController
@RequiredArgsConstructor
public class JwtController {
	private final UserService userService;

//    @PostMapping("/join")
//    public String join(@RequestBody JoinRequest joinRequest) {
//
//        // loginId 중복 체크
//        if(userService.checkLoginIdDuplicate(joinRequest.getLoginId())) {
//            return "로그인 아이디가 중복됩니다.";
//        }
//        // 닉네임 중복 체크
//        if(userService.checkNicknameDuplicate(joinRequest.getNickname())) {
//            return "닉네임이 중복됩니다.";
//        }
//        // password와 passwordCheck가 같은지 체크
//        if(!joinRequest.getPassword().equals(joinRequest.getPasswordCheck())) {
//            return"바밀번호가 일치하지 않습니다.";
//        }
//
//        userService.join2(joinRequest);
//        return "회원가입 성공";
//    }

	@ApiOperation(value = "회원 로그인(jwt token 발급)", notes = "회원 로그인(jwt token 발급)")
    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto) {

        User user = userService.login(loginDto);

        // 로그인 아이디나 비밀번호가 틀린 경우 global error return
        if(user == null) {
            return"로그인 아이디 또는 비밀번호가 틀렸습니다.";
        }

        // 로그인 성공 => Jwt Token 발급
        String secretKey = "dlog-secretkey";
        long expireTimeMs = 1000 * 60 * 60;     // Token 유효 시간 = 60분

        String jwtToken = JwtTokenUtil.createToken(user, secretKey, expireTimeMs);
        System.out.println("token : " + jwtToken);

        return jwtToken;
    }

//    @GetMapping("/info")
//    public String userInfo(Authentication auth) {
//        User loginUser = userService.getLoginUserByLoginId(auth.getName());
//
//        return String.format("loginId : %s\nnickname : %s\nrole : %s",
//                loginUser.getLoginId(), loginUser.getNickname(), loginUser.getRole().name());
//    }
//
//    @GetMapping("/admin")
//    public String adminPage() {
//        return "관리자 페이지 접근 성공";
//    }
}
