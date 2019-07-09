package org.ko.metastore.web.service.impl

import org.ko.metastore.web.domain.MetaTable
import org.ko.metastore.web.repository.MetaTableRepository
import org.ko.metastore.web.service.MetaTableService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import scala.collection.JavaConversions.iterableAsScalaIterable

@Service
@Transactional(rollbackFor = Array(classOf[Throwable]))
class MetaTableServiceImpl @Autowired()(metaTableRepository: MetaTableRepository) extends MetaTableService {

  override def save(metaTable: MetaTable): Unit = {
    metaTableRepository.save(metaTable)
  }

  override def query(): Iterable[MetaTable] = {
    metaTableRepository.findAll()
  }
}
