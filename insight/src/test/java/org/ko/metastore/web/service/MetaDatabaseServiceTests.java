package org.ko.metastore.web.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ko.metastore.web.domain.MetaDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class MetaDatabaseServiceTests {

    @Autowired
    private MetaDatabaseService metaDatabaseService;

    @Test
    public void testSave () {
        MetaDatabase metaDatabase = new MetaDatabase();
        metaDatabase.setName("default");
        metaDatabase.setLocation("hdfs://localhost:9000/user/hive/warehouse");
        metaDatabaseService.save(metaDatabase);
    }


}
