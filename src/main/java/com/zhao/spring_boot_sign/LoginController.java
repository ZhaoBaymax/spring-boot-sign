package com.zhao.spring_boot_sign;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.zhao.spring_boot_sign.Demo.Account;
import com.zhao.spring_boot_sign.Demo.UserDao;


@Controller
public class LoginController {

	 @Autowired
	 private UserDao userDao;
	
    @GetMapping("/")
    public String index(@SessionAttribute(WebSecurityConfig.SESSION_KEY) String account, Model model) {
        model.addAttribute("name", account);
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @GetMapping("/register")
    public String register(){
    	return "register";
    }
    
    @RequestMapping("/addregister")
	 public String register(HttpServletRequest request){  
	        String username = request.getParameter("username");  
	        String password = request.getParameter("password");  
	        String password2 = request.getParameter("password2");  
	        if (password.equals(password2) && password.length() > 0){  
	            Account accEntity = new Account();  
	            accEntity.setUsername(username);  
	            accEntity.setUserpaw(password);  
	            userDao.save(accEntity);  
	            return "login";  
	        }else {  
	            return "register";  
	        }  
	        
	    }  
    

    @PostMapping("/loginPost")
    public @ResponseBody Map<String, Object> loginPost(String account,String password, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
 
        Account accEntity = userDao.findByUsernameAndUserpaw(account, password);
        if(accEntity != null){
    	session.setAttribute(WebSecurityConfig.SESSION_KEY, account);
        map.put("success", true);
        map.put("message", "登录成功");
        return map;
       }
            
        map.put("success", false);
        map.put("message", "帐号密码错误");
        return map;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 移除session
        session.removeAttribute(WebSecurityConfig.SESSION_KEY);
        return "redirect:/login";
    }

}
