package com.example.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Result;
import com.example.springboot.entity.User;
import com.example.springboot.entity.UserVIP;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.mapper.UserVIPMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/uservip")
public class UserVIPController {

    @Resource
    UserVIPMapper userVIPMapper;

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<UserVIP> wrapper = Wrappers.<UserVIP>lambdaQuery();
        if(StringUtils.isNotBlank(search)){
            wrapper.like(UserVIP::getUsername, search);
        }
        Page<UserVIP> userPage = userVIPMapper.selectPage(new Page<UserVIP>(pageNum, pageSize), wrapper);
        return Result.success(userPage);
    }



    @GetMapping("/selectUsername")
    public UserVIP selectUsername(@RequestParam("username") String username){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("username", username);
        UserVIP userVIP = userVIPMapper.selectOne(wrapper);
        return userVIP;
    }

    @PostMapping
    public Result insert(@RequestBody UserVIP userVIP){
        userVIPMapper.insert(userVIP);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody UserVIP userVIP){
        userVIPMapper.updateById(userVIP);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> update(@PathVariable Long id){
        userVIPMapper.deleteById(id);
        return Result.success();
    }
}
