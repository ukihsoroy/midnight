package org.ko.metastore.web.service.impl;

import org.ko.metastore.web.domain.MetaDatabase;
import org.ko.metastore.web.repository.MetaDatabaseRepository;
import org.ko.metastore.web.service.MetaDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Throwable.class)
public class MetaDatabaseServiceImpl implements MetaDatabaseService {

    @Autowired
    private MetaDatabaseRepository metaDatabaseRepository;


    public void save (MetaDatabase metaDatabase) {
        metaDatabaseRepository.save(metaDatabase);
    }

    public Iterable<MetaDatabase> query () {
        return metaDatabaseRepository.findAll();
    }

}
