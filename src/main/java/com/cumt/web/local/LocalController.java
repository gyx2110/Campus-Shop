package com.cumt.web.local;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 本地用户页面跳转控制类
 * @author draymonder
 *
 */

@Controller
@RequestMapping("/local")
public class LocalController {
	 /**
     * 绑定账号页路由
     *
     * @return
     */
    @GetMapping("/accountbind")
    private String accountBind() {
        return "local/accountbind";
    }

    /**
     * 修改密码页路由
     *
     * @return
     */
    @GetMapping("/changepwd")
    private String changePwd() {
        return "local/changepwd";
    }

    /**
     * 登录页路由
     *
     * @return
     */
    @GetMapping("/login")
    private String login() {
        return "local/login";
    }
}
