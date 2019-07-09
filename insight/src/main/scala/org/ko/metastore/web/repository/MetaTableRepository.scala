package org.ko.metastore.web.repository

import org.ko.metastore.web.domain.MetaTable
import org.springframework.data.repository.CrudRepository

trait MetaTableRepository extends CrudRepository[MetaTable, Integer]{

}
