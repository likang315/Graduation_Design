package com.ly.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ly.entity.background.Account;
import com.ly.entity.background.Resources;
import com.ly.entity.background.UserLogin;
import com.ly.service.AccountService;
import com.ly.service.ResourcesService;
import com.ly.service.UserLoginService;
import com.ly.util.Common;
import com.ly.util.Md5Tool;
import com.ly.util.PropertiesUtils;
import com.ly.util.TreeObject;
import com.ly.util.TreeUtil;

/**
 * dispatcherServlet 登录、后台管理框架界面的类
 *
 * @Author kangkang.li@qunar.com
 * @Date 2020-04-01 11:05
 */
@Controller
@RequestMapping("/")
public class BackHomepageController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthenticationManager myAuthenticationManager;

    @Inject
    private UserLoginService userLoginService;

    @Autowired
    private ResourcesService resourcesService;

    /**
     * 01.跳转到登录页面
     *
     * @return
     */
    @RequestMapping("login")
    public String login(Model model, HttpServletRequest request) {

        //重新登录时销毁该用户的Session
        Object o = request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        if (null != o) {
            request.getSession().removeAttribute("SPRING_SECURITY_CONTEXT");
        }
        return Common.BACKGROUND_PATH + "/login/login";
    }

    /**
     * 02.检查用户登录，密码使用MD5 加密，使用密文存储
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("loginCheck")
    @ResponseBody
    public Map<String, Object> loginCheck(String username, String password) {
        Map<String, Object> map = new HashMap<String, Object>();
        Account account = new Account();
        account.setAccountName(username);
        account.setPassword(Md5Tool.getMd5(password));
        // 验证用户账号与密码是否正确
        Account users = this.accountService.countAccount(account);
        map.put("error", "0");
        if (users == null) {
            map.put("error", "用户或密码不正确！");
        } else if (users != null && Common.isEmpty(users.getAccountName())) {
            map.put("error", "用户或密码不正确！");
        }
        return map;
    }

    /**
     * 03.设置用户登录session，ip
     *
     * @param username
     * @param password
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("submitlogin")
    public String submitlogin(String username, String password, HttpServletRequest request) throws Exception {
        try {
            if (!request.getMethod().equals("POST")) {
                request.setAttribute("error", "支持POST方法提交！");
            }
            if (Common.isEmpty(username) || Common.isEmpty(password)) {
                request.setAttribute("error", "用户名或密码不能为空！");
                return Common.BACKGROUND_PATH + "/framework/login";
            }
            // 验证用户账号与密码是否正确
            Account users = this.accountService.querySingleAccount(username);
            if (users == null) {
                request.setAttribute("error", "用户或密码不正确！");
                return Common.BACKGROUND_PATH + "/framework/login";
            } else if (users != null && Common.isEmpty(users.getAccountName()) && !Md5Tool.getMd5(password).equals(users.getPassword())) {
                request.setAttribute("error", "用户或密码不正确！");
                return Common.BACKGROUND_PATH + "/framework/login";
            }
            Authentication authentication = myAuthenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, users.getPassword()));
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);
            HttpSession session = request.getSession(true);
            session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
            // 当验证都通过后，把用户信息放在session里
            request.getSession().setAttribute("userSession", users);
            request.getSession().setAttribute("userSessionId", users.getId());
            request.getSession().setAttribute("userSessionAccountName", users.getAccountName());
            System.out.println(authentication.getPrincipal().toString());
            String userId = request.getSession().getAttribute("userSessionId").toString();
            String userName = users.getAccountName();
            String ip = Common.toIpAddr(request);
            System.out.println("ip:" + ip);
            UserLogin userLogin = new UserLogin();
            userLogin.setUserId(Integer.parseInt(userId));
            userLogin.setUserName(userName);
            userLogin.setloginIP(ip);
            userLoginService.add(userLogin);
            //request.getSession().setAttribute("userRole",authentication.getPrincipal());
            request.removeAttribute("error");
        } catch (AuthenticationException ae) {
            request.setAttribute("error", "登录异常，请联系管理员！");
            return Common.BACKGROUND_PATH + "/login/login";
        }
        return "redirect:homepage.html";
    }

    /**
     * 04.跳转到后台管理页面
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("homepage")
    public String homepage(Model model) {
        return Common.BACKGROUND_PATH + "/homepage/main";
    }

    /**
     * 05.HomePage top
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("top")
    public String top(Model model) {
        return Common.BACKGROUND_PATH + "/homepage/top";
    }

    /**
     * 06.HomePage center
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("center")
    public String center(Model model) {
        return Common.BACKGROUND_PATH + "/homepage/center";
    }

    /**
     * 07.Homepage center_left
     * 获取后台所有目录名，并转换成一级目录、二级目录
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("left")
    public String left(Model model, Resources resources, HttpServletRequest request) {
        List<Resources> rs;
        if (PropertiesUtils.findPropertiesKey("rootName").equals(Common.findAuthenticatedUsername())) {
            rs = resourcesService.queryAll(resources);
        } else {
            rs = resourcesService.findAccountResourcess(Common.findUserSessionId(request));
        }
        // Menu [id=119, name=快递员管理, parentId=109, parentName=物流管理, resKey=courier,
        // resUrl=/background/Express/courierList.html, level=null, type=2, description=courier, children=[]]

        List<TreeObject> treeObjects = new ArrayList<TreeObject>();
        for (Resources res : rs) {
            TreeObject t = new TreeObject();
            try {
                PropertyUtils.copyProperties(t, res);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            treeObjects.add(t);
        }
        // parentId
        List<TreeObject> ns = TreeUtil.getChildResourcess(treeObjects, 0);
        model.addAttribute("resourceslists", ns);
        return Common.BACKGROUND_PATH + "/homepage/left";
    }

    /**
     * 08.center_right
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("right")
    public String right(Model model) {
        return Common.BACKGROUND_PATH + "/homepage/right";
    }

    // ------------------------------------------------------------------------------------------
    /**
     * 获取某个用户的权限资源
     */
    @SuppressWarnings(value = {"unchecked"})
    @RequestMapping("findAuthority")
    @ResponseBody
    public List<String> findAuthority(HttpServletRequest request) {
        SecurityContextImpl securityContextImpl = (SecurityContextImpl) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) securityContextImpl.getAuthentication().getAuthorities();
        List<String> strings = new ArrayList<String>();
        for (GrantedAuthority grantedAuthority : authorities) {

            strings.add(grantedAuthority.getAuthority());

        }
        return strings;
    }

    @ResponseBody
    @RequestMapping("install")
    public Map<String, Object> install(Model model) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("success", "初始化成功!");
        } catch (Exception e) {
            map.put("error", "初始化失败  ---------- >   " + e);
        }
        return map;
    }
}
