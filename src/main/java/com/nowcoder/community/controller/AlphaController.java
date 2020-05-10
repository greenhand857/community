package com.nowcoder.community.controller;

import com.nowcoder.community.service.AlphaServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller  //controller处理浏览器的请求，调用Service业务组件，业务组件调用Dao访问数据库
@RequestMapping("/alpha")
public class AlphaController {

    @Autowired
    private AlphaServie alphaServie;

    //该注释 @RequestMapping("/alpha") 给浏览器提供访问路径
    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello(){
        return "Hello Spring Boot. I miss YuXiaoWen";
    }

    //写一个处理查询请求的方法
    //浏览器能够访问这个方法的前提是得有注解声明
    @RequestMapping("/data")
    @ResponseBody
    public String getData(){
        return alphaServie.find();//吧find结果返回给浏览器
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response){
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()){
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + ": " + value);
        }
        System.out.println(request.getParameter("code"));

        //返回响应数据
        //首先设置返回的数据类型，是网页文本，还是图片...
        response.setContentType("text/html;charset=utf-8");
        try(
                PrintWriter writer = response.getWriter(); //获取输出流
        ){
            writer.write("<h1>牛客网</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // /students?current=1&limit=20
    @RequestMapping(path = "/students", method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(
            @RequestParam(name = "current", required = false, defaultValue = "1") int current,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit){
        System.out.println(current);
        System.out.println(limit);

        return "some students";
    }

    // /student/123
    @RequestMapping(path = "/students/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id){
        System.out.println(id);
        return "a student";
    }

    // post请求
    @RequestMapping(path = "/student", method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, int age){
        System.out.println(name);
        System.out.println(age);

        return "success";
    }
    // 向浏览器响应HTML的两种办法
    // 响应HTML数据
    @RequestMapping(path = "/teacher", method = RequestMethod.GET)
    public ModelAndView getTeacher(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("name","张三");
        mav.addObject("age", 30);
        //设置模板名称
        mav.setViewName("/demo/view");
        return mav;
    }

    @RequestMapping(path = "/school", method = RequestMethod.GET)
    // 装model对象，返回view路径
    public String getSchool(Model model){
        model.addAttribute("name","北京大学");
        model.addAttribute("age","80");
        return "/demo/view";
    }

    // 响应JSON请求 （异步请求） 不改变当前网页，只是访问数据库判断用户名是否存在
    // 返回局部验证的结果是否正确
    // Java对象  --> JSON字符串 --> JS对象 浏览器解析Java对象用JS
    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getEmp(){
        Map<String,Object> emp = new HashMap<>();
        emp.put("name","张三");
        emp.put("age",23);
        emp.put("salary",8000);
        return emp;
    }

    @RequestMapping(path = "/emps", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String,Object>> getEmps() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "张三");
        emp.put("age", 23);
        emp.put("salary", 8000);
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name", "李四");
        emp.put("age", 24);
        emp.put("salary", 9000);
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name", "王五");
        emp.put("age", 25);
        emp.put("salary", 10000);
        list.add(emp);
        return list;
    }
}