package com.example.iot;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @PostMapping("/user_register")
    private Map<String, Object> user_register(@RequestParam Map<String, String> mp){
        String user_email = mp.get("user_email");
        String user_name = mp.get("user_name");
        String user_password = mp.get("user_password");
        String sql = "select * from user_table where user_email='" +user_email + "'";
        List<Map<String, Object>> ls = jdbcTemplate.queryForList(sql);
        if(ls.size() != 0){
            return Map.of("code", 1);
        }
        sql = "insert into user_table(user_email, user_name, user_password)";
        sql += "values('" + user_email + "','" + user_name + "','" + user_password + "')";
        jdbcTemplate.update(sql);
        return Map.of("code", 0);
    }

    @PostMapping("/user_login")
    private Map<String, Object> user_login(@RequestParam Map<String, String> mp) {
        String user_email = mp.get("user_email");
        String user_password = mp.get("user_password");
        String sql = "select * from user_table where user_email='" + user_email + "'";
        List<Map<String, Object>> ls = jdbcTemplate.queryForList(sql);
        if(ls.size() == 0){
            return Map.of("code", 3);
        }
        if(ls.get(0).get("user_password").toString().equals(user_password) == false){
            return Map.of("code", 2);
        }
        String user_name = ls.get(0).get("user_name").toString();
        return Map.of("code", 0, "user_name", user_name);
    }

    @GetMapping("/user_query")
    private Map<String, Object> user_query(){
        String sql = "select * from user_table";
        List<Map<String, Object>> ls = jdbcTemplate.queryForList(sql);
        for(int i = 0; i < ls.size(); i++){
            ls.get(i).remove("user_password");
        }
        return Map.of("code", 0, "content", ls);
    }
}
