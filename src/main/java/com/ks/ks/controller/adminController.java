package com.ks.ks.controller;

import com.ks.ks.bean.history;
import com.ks.ks.bean.user;
import com.ks.ks.service.adminService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class adminController {

    @Autowired
    adminService adminService;
    //注解
    Logger logger= LoggerFactory.getLogger(getClass());

    @GetMapping("/welcom")     //首页
    public String toLogin(){
        return "login";
    }

    //管理员登陆
    @PostMapping("login")       //post登陆 参数不通过URL
    public String adminLogin(@RequestParam(value = "adminName",required = false)String name,
                             @RequestParam(value = "adminPwd",required = false)String pwd){
        int flag=adminService.adminLogin(name,pwd);
        if(flag==1){
            System.out.println("管理员登陆判定成功");
            return "redirect:userHome";
        }else {
            System.out.println("管理员登陆判定失败");
            return "login";
        }

    }

    //增加
    @PostMapping("/addUser")
    public String addUser(@RequestParam(value = "adminName",required = false)String name,
                          @RequestParam(value = "adminPwd",required = false)String pwd){
        int flag=adminService.addUser(name,pwd);
        if(flag==1){
            System.out.println("操作成功");
        }else{
            System.out.println("增加失败");
        }
        return "redirect:userHome";
    }

    //获取列表
    @RequestMapping("userHome")
    public String adminHome(ModelMap map){
        System.out.println("登陆/操作 成功---》");
        List<user> userList=adminService.userList();
        map.addAttribute("userList",userList);
        return "adminHome";
    }

    //删除
    @DeleteMapping("/deleteUser/{userId}")
    @ResponseBody
    public Map<String,String> deleteUser(@PathVariable("userId") int id){
        //删除用户
        Map<String,String> map=new HashMap<>(); //键值对的哈希map
        logger.info("删除用户 =userID："+id);
        int flag=adminService.deleteUser(id);
        if(flag>0){
            logger.info("删除成功");
            map.put("result","删除成功");   //存入map
        }else{
            logger.error("删除失败");
            map.put("result","删除失败");
        }
        return map;
    }


    //未开奖展示
    @GetMapping("/betList")
    public String betList(ModelMap map, HttpServletRequest request){
        List<history> historyList=new ArrayList<>();
        historyList=adminService.getHistoryList();
        //循环输出：

        map.addAttribute("historyList",historyList);    //将未开奖传递
        HttpSession session=request.getSession();
        session.setAttribute("betNumList",historyList);
        return "betManage";
    }

    //开奖
    @PostMapping("/setBetNum")          //可以为空，为空随机生成
    public String setBetNum(HttpServletRequest request,@RequestParam(value = "betNum",required = true)String betNum){
        if(betNum != null){
            System.out.println("手动设置"+betNum+"为中奖号码");
        }else{
            List<history> historyList=new ArrayList<>();
            historyList= (List<history>) request.getSession().getAttribute("betNumList");
           int num= (int)(1+Math.random()*(10-1+1));
           int setNum=historyList.get(num).getBetNum();
            System.out.println("手动设置的:"+setNum);
        }

        return null;
    }



}
