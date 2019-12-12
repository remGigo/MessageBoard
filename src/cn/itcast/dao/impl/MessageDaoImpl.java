package cn.itcast.dao.impl;

import cn.itcast.dao.MessageDao;
import cn.itcast.domain.Message;
import cn.itcast.domain.MessageToShow;
import cn.itcast.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class MessageDaoImpl implements MessageDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public void add(Message message) {
        //1.定义sql
        String sql = "insert into message values(null,?,?)";
        //2.执行sql

        template.update(sql,message.getMessage(),message.getUser_id());
    }

    @Override
    public void delete(int id) {

        String sql = "delete from message where id = ?";
        template.update(sql,id);
    }

    @Override
    public int nameToId(String name) {

        String sql = "select id from user where username = ?";

        return template.queryForObject(sql,Integer.class,name); //啊啊啊啊这少了哥排bug排了一个小时

    }


    @Override
    public int findTotalCount() {

        String sql = "select count(*) from message";

        return template.queryForObject(sql,Integer.class);
    }


    //如果设计成两张表就不能简单的这样查了
//    @Override
//    public List<Message> findByPage(int start, int rows) {
//
//        String sql = "select * from message limit ? , ?";
//
//        List<Message> query = template.query(sql, new BeanPropertyRowMapper<Message>(Message.class), start, rows);
//
//        return query;
//    }

    @Override
    public List<MessageToShow> findByPage(int start, int rows) {
        String sql = "select username,message,message.id from user,message where message.user_id = user.id limit ? , ?";
        List<MessageToShow> list = template.query(sql, new BeanPropertyRowMapper<>(MessageToShow.class),start,rows);

        return list;
    }


//    //写的根本不对，大思路就有问题
//    @Override
//    public List<String> findByPage_user(int start, int rows) {
//        String sql = "select username from user where id > ? and id < ?";
//        List<String> query = template.query(sql, new BeanPropertyRowMapper<String>(String.class), start, start + rows + 1);
//        return query;
////        List<Map<String, Object>> list = template.queryForList(sql, start, rows);这方法根本不实用！
//    }


}
