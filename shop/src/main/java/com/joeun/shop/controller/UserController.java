package com.joeun.shop.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

import com.joeun.shop.dto.Order;
import com.joeun.shop.dto.Users;
import com.joeun.shop.service.OrderService;
import com.joeun.shop.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;



@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private PersistentTokenRepository persistentTokenRepository;

    /**
     * 사용자 페이지
     * @return
     */
    // 회원권한(ROLE_USER)을 가진 사용자만 접근 허용
    // @PreAuthorize("hasRole('ROLE_USER')")
    // @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    // @Secured("ROLE_USER")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping(value={"/", ""})
    public String index() {
        // int result = 10 / 0;
        // log.info(result + "");
        return "user/index";
    }


    /**
     * 회원정보 수정
     * @param param
     * @return
     * @throws Exception
     */
    @GetMapping(value="/update")
    public String userUpdate(Model model, Principal principal) throws Exception {
        String loginId = principal != null ? principal.getName() : null;

        Users user = userService.selectById(loginId);
        
        model.addAttribute("user", user);

        return "user/update";
    }

    /**
     * 회원정보 수정 처리
     * @param entity
     * @return
     * @throws Exception
     */
    @PostMapping(value="/update")
    public String updateUpdatePro(Users user
                                , HttpServletRequest request
                                , HttpServletResponse response) throws Exception {
        log.info("user : " + user);
        int result = userService.update(user);

        // 회원정보 수정 실패
        if( result == 0 ) {
            return "redirect:/user/update";
        }
        
        // HttpSession session = request.getSession();
        // session.invalidate();       // 세션 무효화(로그아웃)

        // 시큐리티 강제 로그아웃
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        new SecurityContextLogoutHandler().logout(request, response, authentication);

        // remember-me 쿠키 삭제
        Cookie cookie = new Cookie("remember-me", "");     
        cookie.setMaxAge(0);                                  
        cookie.setPath("/");        
        response.addCookie(cookie);

        // 토큰 삭제
        persistentTokenRepository.removeUserTokens(user.getUserId());

        // 로그아웃 후 ➡ 로그인 페이지
        return "redirect:/login";
    }
    
    /**
     * 주문 내역 페이지
     * @throws Exception
     */
    @GetMapping(value="/order")
    public String order(Model model, Principal principal, Order order) throws Exception {
        List<Order> orderList = null;
        // 회원 주문 내역 데이터 요청
        if( principal != null ) {
            log.info("회원 : " + principal.getName());
            String userId = principal.getName();
            orderList = orderService.listByUserId(userId);
            order = orderService.sumOrder(userId);
            log.info("order : " + order);
            model.addAttribute("orderList", orderList);
            model.addAttribute("order", order);
        }

        return "user/order";
    }

    /**
     * 주문 내역 페이지 - 비회원
     * @param model
     * @param principal
     * @param order
     * @return
     * @throws Exception
     */
    @PostMapping(value="/order")
    public String orderPost(Model model, Principal principal, Order order) throws Exception {
        List<Order> orderList = null;
        // 비회원 주문 내역 데이터 요청
        // ✅ 비회원 전화번호           - phone
        // ✅ 비회원 주문 비밀번호      - orderPw
        if( principal == null && order.getPhone() != null ) {
            log.info("비회원 : " + order.getPhone());
            orderList = orderService.listByGuest(order);
            order = orderService.sumOrderByGuest(order);
            model.addAttribute("orderList", orderList);
            model.addAttribute("order", order);
        }
        return "user/order";
    }
    

    
    
    
}
