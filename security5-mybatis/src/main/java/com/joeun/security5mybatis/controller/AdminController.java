package com.joeun.security5mybatis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joeun.security5mybatis.dto.Code;
import com.joeun.security5mybatis.dto.CodeGroup;
import com.joeun.security5mybatis.dto.UserAuth;
import com.joeun.security5mybatis.dto.Users;
import com.joeun.security5mybatis.service.CodeGroupService;
import com.joeun.security5mybatis.service.CodeService;
import com.joeun.security5mybatis.service.UserAuthService;
import com.joeun.security5mybatis.service.UserService;

import lombok.extern.slf4j.Slf4j;





@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private CodeGroupService codeGroupService;

    @Autowired
    private CodeService codeService;

    /**
     * 🌍 관리자 메인 화면 
     * 🔗 /admin/, /admin
     * 💻 ~/admin/index.html
     * - 관리자 권한(ROLE_ADMIN)을 가진 사용자만 접근 허용
     * - @PreAuthorize("hasRole('ROLE_ADMIN')")
     * @return
     */
    @GetMapping(value={"/", ""})
    public String index() {
        log.info("[GET] - /admin");
        return "admin/index";
    }

    /**
     * 🌍 관리자 - 코드 그룹 관리
     * 🔗 /admin/code/groups
     * 💻 ~/admin/code/groups.html
     * @throws Exception
     */
    @GetMapping(value="/code/groups")
    public String codeGroup(Model model) throws Exception {
        List<CodeGroup> codeGroupList = codeGroupService.list();
        model.addAttribute("codeGroupList", codeGroupList);
        return "admin/code/groups";
    }

    
    /**
     * 🌍 관리자 - 코드 그룹 등록
     * 🔗 /admin/code/groups
     * 💻 ➡ ~/admin/code/groups.html 
     * @param codeGroup
     * @return
     * @throws Exception
     */
    @PostMapping(value="/code/groups")
    public String codeGroupPost(CodeGroup codeGroup) throws Exception {
        int result = codeGroupService.insert(codeGroup);
        return "redirect:/admin/code/groups";
    }

     /**
     * 🌍 관리자 - 코드 관리
     * 🔗 /admin/code/codes
     * 💻 ~/admin/code/codes.html
     * @throws Exception
     */
    @GetMapping(value="/code/codes")
    public String code(Model model) throws Exception {
        List<CodeGroup> codeGroupList = codeGroupService.list();
        model.addAttribute("codeGroupList", codeGroupList);
        List<Code> codeList = codeService.list();
        model.addAttribute("codeList", codeList);
        return "admin/code/codes";
    }

    
    /**
     * 🌍 관리자 - 코드 등록
     * 🔗 /admin/code/codes
     * 💻 ➡ ~/admin/code/codes.html 
     * @param code
     * @return
     * @throws Exception
     */
    @PostMapping(value="/code/codes")
    public String codePost(Code code) throws Exception {
        int result = codeService.insert(code);
        return "redirect:/admin/code/codes";
    }
    
    


    /**
     * 🌍 관리자 - 회원 관리
     * 🔗 /admin/users
     * 💻 ~/admin/users.html
     * @throws Exception
     */
    @GetMapping(value="/users")
    public String users(Model model) throws Exception {
        List<Users> userList = userService.list();
        model.addAttribute("userList", userList);
        List<Code> codeList = codeService.selectByGroupCode("AUTH");
        model.addAttribute("codeList", codeList);
        return "admin/users";
    }

    @PostMapping(value="/users")
    public String usersPost(UserAuth userAuth) throws Exception {
        log.info(userAuth.toString());
        int result = userAuthService.insert(userAuth);
        return "redirect:/admin/users";
    }
    
    
    @DeleteMapping(value="/users")
    public ResponseEntity<String> usersDelete(UserAuth userAuth) throws Exception {
        log.info(userAuth.toString());
        int result = userAuthService.deleteByUserIdAndAuth(userAuth);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }
    
    

    
}
