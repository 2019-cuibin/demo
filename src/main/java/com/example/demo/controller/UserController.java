package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author: cuibin
 * @Date: 2019/9/17 10:09
 * @Description:
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 为了解决整合mybatis时，版本过高，不能够自动注入SqlSessionFactory 和 SqlSessionTemplate，需要手动注入。
     */
    @Resource
    private DataSource dataSource;
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean.getObject();
    }



    @RequestMapping("/all")
     public String all(){
        return "login";
     }

    /**
     * 控制层登录方法
     * @param user
     * @return
     */
    @RequestMapping("/login")
     public String login(User user){
        boolean success = userService.login(user);
        if(success){
            return "hello";
        }else{
            return "1";
        }
     }

    @RequestMapping("/uploadfile")
    public String upLoadFile(@RequestParam("file")MultipartFile file, HttpServletRequest request){
        //获取文件名
        String fileName = file.getOriginalFilename();
        //截取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        ///
        fileName = UUID.randomUUID()+suffixName;
        //指定本地文件夹存储文件
        String filePath = "G:\\IDEA work-space\\demo\\src\\main\\resources\\static\\";
        try {
            //将文件存储到文件夹中
            file.transferTo(new File(filePath+fileName));
            return "hello";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "1";
    }

     @RequestMapping("/list")
     public String selectAll(Model model){
        List<User> ls = userService.selectAll();
        model.addAttribute("User",ls);
        return "list";
     }

     @RequestMapping("/page")
     public  String page(String pageNum,Model model){
        String sPage = pageNum;
        int pageSize = 5;
        int pageNo = 0;
        if(sPage == null){
            pageNo = 1;
        }else{
            pageNo = Integer.valueOf(sPage);
            if(pageNo < 1){
                pageNo = 1;
            }
        }

        int totalCount = 0;
        int count = userService.allCount();
        if(count > 0){
            totalCount = count;
        }
        int maxPage = totalCount%pageSize == 0? totalCount/pageSize:totalCount/pageSize+1;
        if(pageNo > maxPage){
            pageNo = maxPage;
        }

        int tempPageNo = (pageNo - 1)*pageSize;
        Map map = new HashMap();
        map.put("pageNo",tempPageNo);
        map.put("pageSize",pageSize);
        List<Map> list = userService.selectByPage(map);
        model.addAttribute("list",list);
        model.addAttribute("pageNo",pageNo);
        model.addAttribute("totalCount",totalCount);
        model.addAttribute("maxPage",maxPage);

        return "page";
     }
}
