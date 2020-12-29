package com.jingwei.dispatch.db.dao;

import com.jingwei.dispatch.db.model.Customer;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CustomerDao {

    @Select("SELECT * FROM customer WHERE username = #{username}")
    public Customer findByUserName(String username);

    @Select("SELECT * FROM customer WHERE id = #{id}")
    public Customer findByID(Long id);

    @Select("INSERT INTO customer(username, password) VALUES (#{name}, #{password}) RETURNING *")
    @Options(flushCache = Options.FlushCachePolicy.TRUE)
    public Customer save(String name, String password);

    @Select("UPDATE customer SET username=#{name}, password=#{password}, is_active=#{is_active} WHERE id=#{id} RETURNING *")
    @Options(flushCache = Options.FlushCachePolicy.TRUE)
    public Customer update(Customer customer);

    @Select("DELETE FROM customer WHERE id = #{id} RETURNING *")
    @Options(flushCache = Options.FlushCachePolicy.TRUE)
    public Customer remove (Long id);
}
