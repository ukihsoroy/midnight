package org.ko.metastore.web.service;

import org.ko.metastore.web.domain.MetaDatabase;

public interface MetaDatabaseService {

    void save (MetaDatabase metaDatabase);

    Iterable<MetaDatabase> query();


}
