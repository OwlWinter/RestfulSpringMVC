package cn.mogeek.mapper;

import cn.mogeek.model.Disciple;

import java.util.List;

public interface DiscipleMapper {
    List<Disciple> query(Disciple disciple) throws Exception;
    int insert(Disciple disciple) throws Exception;
    int update(Disciple disciple) throws Exception;
    int delete(Integer id) throws Exception;
}
