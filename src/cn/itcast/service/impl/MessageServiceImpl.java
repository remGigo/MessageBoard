package cn.itcast.service.impl;

import cn.itcast.dao.MessageDao;
import cn.itcast.dao.impl.MessageDaoImpl;
import cn.itcast.domain.Message;
import cn.itcast.domain.MessageToShow;
import cn.itcast.domain.PageBean;
import cn.itcast.service.MessageService;

import java.util.List;

public class MessageServiceImpl implements MessageService {

    //service是要调用dao层的：
    private MessageDao dao = new MessageDaoImpl();

    @Override
    public void addMessage(Message message) {
        dao.add(message);
    }


    @Override
    public void deleteMessage(String id) {
        dao.delete(Integer.parseInt(id));
    }

    @Override
    public int nameToId(String username) {

        return dao.nameToId(username);
    }


    //这里要实现分页查询的主要逻辑！ 把Message统统给我换成MessageToShow
    @Override
    public PageBean<MessageToShow> findMessageByPage(String _currentPage, String _rows) {

        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);

        if(currentPage <=0) {
            currentPage = 1;
        }
        //1.创建空的PageBean对象
        PageBean<MessageToShow> pb = new PageBean<>();
        //2.设置参数
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);

        //3.调用dao查询总记录数
        int totalCount = dao.findTotalCount();
        pb.setTotalCount(totalCount);

        //4.调用dao查询List集合
        //计算开始的记录索引,下面都要用
        int start = (currentPage - 1) * rows;

        List<MessageToShow> list = dao.findByPage(start,rows);
        pb.setList(list);

        //5.计算总页码
        int totalPage = (totalCount % rows)  == 0 ? totalCount/rows : (totalCount/rows) + 1;
        pb.setTotalPage(totalPage);

        return pb;
    }



}
