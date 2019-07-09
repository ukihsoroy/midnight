package org.ko.metastore.web.service

import org.ko.metastore.web.domain.MetaTable

trait MetaTableService {

  def save(metaTable: MetaTable)

  def query(): Iterable[MetaTable]

}
