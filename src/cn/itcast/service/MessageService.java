package cn.itcast.service;

import cn.itcast.domain.Message;
import cn.itcast.domain.MessageToShow;
import cn.itcast.domain.PageBean;

/**
 * 留言管理的业务接口
 */
public interface MessageService {

    /**
     * 保存Message
     * @param message
     */
    void addMessage(Message message);

    /**
     * 根据id删除Message
     * @param id
     */
//    void deleteMessage(String id);


    /**
     * 分页查询
     * @param currentPage
     * @param rows
     * @return
     */
    PageBean<MessageToShow> findMessageByPage(String currentPage, String rows);

    void deleteMessage(String id);

    public int nameToId(String username);


    /*
    查询分页查询出的五条留言的作者,啊错了，不是一个方法
     */
    //    List<String> findNameByPage(String currentPage,String rows);
}
