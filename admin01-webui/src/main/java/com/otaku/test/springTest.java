package com.otaku.test;

import com.google.gson.JsonObject;
import com.otaku.mapper.AdminMapper;
import com.otaku.pojo.Admin;
import com.otaku.pojo.Role;
import com.otaku.service.AdminService;
import com.otaku.service.RoleService;
import com.otaku.utils.MD5Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

/**
 * @author Otaku
 * @date 2021/10/15-10:54
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/spring/*.xml"})
public class springTest {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private AdminService adminService;
    @Autowired
    private RoleService roleService;
    @Test
    public void DataSoruceConnet() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }
     @Test
    public void insetAdmin(){
//        Admin admin = new Admin(null, "tom", "123456", "汤姆", "12455@qq.com", null);
        Admin admin = new Admin(null, "jerry", "123456", "杰瑞", "12455@qq.com", null);
         int i = adminService.saveAdmin(admin);
     }
     @Test
    public void getAll(){
         List<Admin> adminList = adminService.getAll();
         for (Admin admin : adminList){
             System.out.println(admin);
         }
     }
     @Test
    public void getMdt(){
         String md5 = MD5Util.getMd5("123456");
         System.out.println(md5);
     }
     @Test
    public void insertFakerData(){
        for (int i=0;i<30;i++){
            adminMapper.insert(new Admin(null, "loginAcct"+i, "123456", "login"+i, "123"+i,null));
        }
     }
     @Test
    public void  accredit(){
         List<Role> accredited = roleService.accredited(1);
         System.out.println(accredited.size());
         for (Role role : accredited){
             System.out.println(role);
         }
     }
}
