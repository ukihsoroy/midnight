package org.ko.metastore.web.domain

import javax.persistence.{Entity, GeneratedValue, Id, Table}

import scala.beans.BeanProperty


@Entity
@Table(name = "T_META_TABLE")
class MetaTable {

  @Id
  @GeneratedValue
  @BeanProperty
  var id: Integer = _

  @BeanProperty
  var name: String = _

  @BeanProperty
  var tableType: String = _

  @BeanProperty
  var dbId: Integer = _


}
