package com.imooc.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.dto.User;
import com.imooc.dto.UserQueryCondition;
import com.imooc.exception.UserNotExistException;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fengdh
 * @date 2020/4/16
 */
@RestController
@RequestMapping("/user")
public class UserController {
    /**
     * 3.4用户创建请求
     * 传递参数不为空校验（不进方法体）：@Valid 和 @NotBlank配合使用
     * 传递参数不为空校验（进入方法体）：@Valid 和 @NotBlank 和 BindingResult配合使用
     */
    @PostMapping
    public User create(@Valid @RequestBody User user, BindingResult errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach(error -> {
                System.out.println(error.getDefaultMessage());
            });
        }
        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());
        user.setId("1");
        return user;
    }
    /**
     * 3.5修改请求
     */
    @PutMapping("/{id:\\d+}")
    public User update(@Valid @RequestBody User user, BindingResult errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach(error -> {
//                FieldError fieldError = (FieldError) error;
//                String message = fieldError.getField() + " " + error.getDefaultMessage();
//                System.out.println(message);
                System.out.println(error.getDefaultMessage());
            });
        }
        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());
        user.setId("1");
        return user;
    }
    /**
     * 3.5删除请求
     */
    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable String id) {
        System.out.println(id);
    }

    /**
     * 3.1常用注解
     */
//    @RequestMapping(value = "/user", method = RequestMethod.GET)
//    public List<User> query(@RequestParam(required = false, defaultValue = "tom", name = "username") String name) {
//        System.out.println(name);
//        List<User> users = new ArrayList<>();
//        users.add(new User());
//        users.add(new User());
//        users.add(new User());
//        return users;
//    }
    /**
     * 3.2查询请求
     * 传递对象
     * 分页：Pageable
     *
     * 查询时不给密码，查询详情时给密码
     * 3.在controller方法上指定视图
     */
//    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @GetMapping
    @JsonView(User.UserSimpleView.class)
    public List<User> query(UserQueryCondition condition, @PageableDefault(page = 2, size = 17, sort = "username,asc") Pageable pageable) {
        System.out.println(ReflectionToStringBuilder.toString(condition));
        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getPageNumber());
        System.out.println(pageable.getSort());
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }
    /**
     * 3.3编写用户详情服务
     * @PathVariable 带占位符的 URL 是 Spring3.0 新增的功能
     * 正则（参数为数字）：id:\d+
     */
//    @RequestMapping(value = "/user/{id:\\d+}", method = RequestMethod.GET)
    @GetMapping("/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User getInfo(@PathVariable String id) {

        /**
         * 抛出异常
         */
//        throw new RuntimeException("user not exist");
//        throw new UserNotExistException(id);
        /**
         * 1.过滤器拦截
         */
        System.out.println("进入getInfo服务");
        User user = new User();
        user.setUsername("tom");
        return user;
    }
}
