package com.ks.ks.controller;

import com.alibaba.fastjson.JSONObject;
import com.ks.ks.bean.Shout;
import com.ks.ks.bean.bet;
import com.ks.ks.bean.user;
import com.ks.ks.service.userService;
import com.ks.ks.service.TokenService;
import com.ks.ks.util.PassToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class userController {
    @Autowired
    userService userService;
    @Autowired
    TokenService tokenService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;


    //前端接口
    //代理商登陆
    @PassToken
    @PostMapping("/userLogin")
    public String userLogin(@RequestBody user user){
        System.out.println("to ->登陆");

        JSONObject jsonObject=new JSONObject();
      int flag=userService.userLogin(user.getUserName(),user.getUserPwd());
        if (flag==1){
         System.out.println("登陆判定成功---");

            String token = tokenService.getToken(user);
            jsonObject.put("token", token);
            jsonObject.put("success", true);
            jsonObject.put("message", "用户登录成功");
            return jsonObject.toJSONString();


            //return "{\"success\":true,\"message\":\"用户登录成功\"}";

   }else{
       System.out.println("登陆判定失败---");

            jsonObject.put("success", false);
            jsonObject.put("errorMessage", "用户名或者密码错误");
            return jsonObject.toJSONString();
            //return "{\"success\":false,\"errorMessage\":\"用户名或者密码错误\"}";
      }
    }


    @PassToken
    @GetMapping("/time")
    public String activity(){
       // System.out.println("to ->登陆");

        JSONObject jsonObject=new JSONObject();


            System.out.println("开始计算时间---");

         //   String token = tokenService.getToken(user);
           // jsonObject.put("token", token);
            jsonObject.put("time", 10);
            jsonObject.put("message", "用户登录成功");
            return jsonObject.toJSONString();
    }


  //  @Scheduled(cron = "0 0/15 8-22 * * * ")

// //广播推送消息
  @Scheduled(fixedRate = 10000)
    public void sendTopicMessage() {
	System.out.println("后台广播推送！");
	Shout  outing = new Shout();
	outing.setCurrentTime(System.currentTimeMillis());
	outing.setStartTime(System.currentTimeMillis());
	outing.setEndTime(System.currentTimeMillis()+15*60*1000);
	outing.setCpNo(getCpNo());
	this.messagingTemplate.convertAndSend("/topic/getResponse",outing);
   }

   public String  getCpNo(){
       String cpNo="";
       Calendar now = Calendar.getInstance();
       String year=now.get(Calendar.YEAR)+"";
       String month=(now.get(Calendar.MONTH)+1)+ "";
       String day=now.get(Calendar.DAY_OF_MONTH)+"";
       if(Integer.valueOf(month)<10) {

           cpNo = year+"0"+month + day + getNo();
       }else{
           cpNo = year + month + day + getNo();

       }
       System.out.println(cpNo);

      return  cpNo;
   }
   public  int  getNo(){

       System.out.println(System.currentTimeMillis());
       System.out.println(getBeginDate());
     long   between=System.currentTimeMillis()-getBeginDate();
     long   time=10*60*1000;


       float number = between / time;
       int  result = (int)Math.floor(number);

      return result;
   }
   public  Long   getBeginDate(){

       Calendar c = Calendar.getInstance();
       c.set(Calendar.HOUR_OF_DAY, 8);
       c.set(Calendar.MINUTE, 30);
       c.set(Calendar.SECOND, 0);

       return  c.getTimeInMillis();



   }
//
//@MessageMapping("/marco")
//public Shout handleShout(Shout incoming) {
//    System.out.println("Received message:"+incoming.getMessage());
//    Shout  outing = new Shout();
//    outing.setMessage("Polo");
//    return outing;
//}












    //用户下注
    @GetMapping("/userBet")
    public Map<String,String> userBet(String userId,String betNum){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dd=new Date();
        String now=sdf.format(dd);  //获取当前时间
        System.out.println("时间-》"+now);
        System.out.println(now.charAt(14)+" <=> "+now.charAt(15));

        int fen=now.charAt(15)-'0';     //char转int
        int miao=now.charAt(17)-'0';
        int miao2=now.charAt(18)-'0';
//        miao=miao+miao2;
        System.out.println(fen+" 秒-》"+miao+"   "+miao2);


        bet bb=new bet();
        //进行判断
        if(fen<=9){
            int uid=Integer.parseInt(userId);
            int bnum=Integer.parseInt(betNum);
            int btype=0;    //未开奖
            int flag=userService.addBetHistory(uid,bnum,btype);
            if(flag==1){
                System.out.println("下注成功");
                return null;
            }else {
                System.out.println("下注失败");
                return null;
            }
        }else {
            return null;
        }




     /*   final long timeInterval = 1000;
        Runnable runnable = new Runnable() {
            public void run() {
                while (true) {
                    // ------- code for task to run
                    System.out.println("Hello !!");
                    // ------- ends here
                    try {
                        Thread.sleep(timeInterval);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();*/
    }




}
