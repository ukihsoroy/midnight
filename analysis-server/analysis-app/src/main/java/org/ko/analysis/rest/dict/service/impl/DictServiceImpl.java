package org.ko.analysis.rest.dict.service.impl;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.ko.analysis.conf.api.ResponseCode;
import org.ko.analysis.conf.exp.BusinessException;
import org.ko.analysis.rest.dict.condition.QueryDictCondition;
import org.ko.analysis.rest.dict.repository.DictRepository;
import org.ko.analysis.rest.dict.service.DictService;
import org.ko.analysis.store.ads.domain.Dict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Throwable.class)
public class DictServiceImpl extends ServiceImpl<DictRepository, Dict> implements DictService {

    /**
     * 字典表数据库对象
     */
    @Autowired
    private DictRepository dictRepository;

    @Override
    public List<Dict> queryDictList(QueryDictCondition condition) {
        return dictRepository.selectList(null);
    }

    @Override
    public Dict queryDictInfo(Long id) {
        return dictRepository.selectById(id);
    }

    @Override
    public Long createDict(Dict dict) {
        if (dictRepository.insert(dict) == 0) {
            throw new BusinessException(ResponseCode.INTERNAL_SERVER_ERROR);
        }
        return dict.getId();
    }

    @Override
    public Dict updateDict(Long id, Dict dict) {
        dict.setId(id);
        if (dictRepository.updateById(dict) == 0) {
            throw new BusinessException(ResponseCode.INTERNAL_SERVER_ERROR);
        }
        return dict;
    }

    @Override
    public Long deleteDict(Long id) {
        if (dictRepository.deleteById(id) == 0) {
            throw new BusinessException(ResponseCode.INTERNAL_SERVER_ERROR);
        }
        return id;
    }

    @Override
    public Dict findDictByCodeAndType(String code, String type) {
        return dictRepository.selectOne(Dict.builder().code(code).type(type).build());
    }

    @Override
    public List<Dict> findDictByCode(String code) {
        return dictRepository.selectList(Condition.create().eq("code", code));
    }

}