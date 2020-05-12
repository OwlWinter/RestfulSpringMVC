package cn.mogeek.service;

import cn.mogeek.mapper.DiscipleMapper;
import cn.mogeek.model.Disciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class DiscipleServiceImpl implements DiscipleService {

    @Autowired
    DiscipleMapper discipleMapper;

    @Override
    public List<Disciple> query(Disciple disciple) throws Exception {
        List<Disciple> discipleList = discipleMapper.query(disciple);
        return discipleList;
    }

    @Override
    public Integer insert(Disciple disciple) throws Exception{
        int result = discipleMapper.insert(disciple);
        return result;
    }

    @Override
    public boolean update(Disciple disciple) throws Exception {
        int result = discipleMapper.update(disciple);
        System.out.println("discipleMapper.update");
        return result == 1 ? true : false;
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        int result = discipleMapper.delete(id);
        return result == 1 ? true : false;
    }

    @Override
    public String queryForLink(Map<String, String> name_OR_id) {
        String result = "Empty map!";
        if (!name_OR_id.isEmpty()){
            Disciple disciple = new Disciple();
            if (name_OR_id.containsKey("name"))
                disciple.setStudent_name(name_OR_id.get("name"));

            if (name_OR_id.containsKey("id"))
                disciple.setId(Integer.parseInt(name_OR_id.get("id")));

            try {
                List<Disciple> discipleList = query(disciple);

                switch (discipleList.size()){
                    case 0:{
                        result = "Empty list!";
                        break;
                    }
                    case 1:{
                        result = discipleList.get(0).getDaily_report();
                        break;
                    }
                    default:
                        result = "Too many context!";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return result;
    }
}
