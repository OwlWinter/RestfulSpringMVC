package cn.mogeek.service;

import cn.mogeek.model.Disciple;

import java.util.List;
import java.util.Map;

public interface DiscipleService {
    /**
     *根据传入对象的非空字段来查找
     * @param disciple
     * @return
     */
    List<Disciple> query(Disciple disciple) throws Exception;

    /**
     * 插入一条数据
     * @param disciple
     * @return
     */
    Integer insert(Disciple disciple) throws Exception;

    /**
     * 根据传入对象的 id 来修改数据
     * 传入对象有几个非空字段就修改数据库对应的几个字段
     * @param disciple
     * @return
     */
    boolean update(Disciple disciple) throws Exception;

    /**
     * 根据 id 删除数据
     * @param id
     * @return
     */
    boolean delete(Integer id) throws Exception;

    /**
     * 根据姓名或者 id 来获取日报链接
     * @param name_OR_id
     * @return
     */
    String queryForLink(Map<String,String> name_OR_id);
}
