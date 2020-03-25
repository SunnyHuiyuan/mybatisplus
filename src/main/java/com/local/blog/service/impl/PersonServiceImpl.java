package com.local.blog.service.impl;

import com.local.blog.model.Person;
import com.local.blog.mapper.PersonMapper;
import com.local.blog.service.PersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author louKe
 * @since 2020-03-26
 */
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements PersonService {

}
