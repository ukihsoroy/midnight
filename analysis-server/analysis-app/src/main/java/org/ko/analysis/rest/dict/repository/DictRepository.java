package org.ko.analysis.rest.dict.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.ko.analysis.rest.dict.condition.QueryDictCondition;
import org.ko.analysis.rest.dict.dto.DictDTO;
import org.ko.analysis.store.master.domain.Dict;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DictRepository extends BaseMapper<Dict> {

    List<DictDTO> queryList(QueryDictCondition condition);

    int insertList (List<Dict> dicts);

}
