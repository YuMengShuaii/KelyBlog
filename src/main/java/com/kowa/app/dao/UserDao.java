
package com.kowa.app.dao;  
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kowa.app.po.UserPo;

import javax.transaction.Transactional;  
import java.math.BigDecimal;  
import java.util.Date;  
import java.util.List;  
  
@Transactional 
public interface UserDao extends CrudRepository<UserPo, Integer> {  
    UserPo findByUsername(String name);
    
}  