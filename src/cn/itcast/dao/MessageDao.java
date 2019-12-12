package cn.itcast.dao;

import cn.itcast.domain.Message;
import cn.itcast.domain.MessageToShow;
import cn.itcast.domain.User;

import java.util.List;
import java.util.Map;

public interface MessageDao {


    void add(Message message);

//    void delete(int id);


    /**
     * 查询总记录数
     * @return
     */
    int findTotalCount();

    /**
     * 分页查询每页记录
     * @param start
     * @param rows
     * @return
     */
    List<MessageToShow> findByPage(int start, int rows);

    void delete(int id);

    int nameToId(String name);

//    List<User> findByPage_user(int start,int rows,int user_id);不对
//    List<String> findByPage_user(int start,int rows);也不对，大思路就不对！

}
