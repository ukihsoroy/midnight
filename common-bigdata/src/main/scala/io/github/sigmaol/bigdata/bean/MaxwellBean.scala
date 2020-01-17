package io.github.sigmaol.bigdata.bean

import org.json.JSONObject

import scala.beans.BeanProperty

class MaxwellBean extends Serializable {
  /**
    * 数据库名
    **/
  @BeanProperty
  var database: String = _

  /**
    * 表名
    **/
  @BeanProperty
  var table: String = _

  /**
    * 操作类型,insert、update or delete
    **/
  @BeanProperty
  var `type`: String = _

  /**
    * 数据集,json字符串
    **/
  @BeanProperty
  var data: String = _

  /**
    * 操作的时间戳
    **/
  @BeanProperty
  var ts: java.sql.Timestamp = _

  /**
    *
    **/
  @BeanProperty
  var xid: Long = _

  /**
    *
    **/
  @BeanProperty
  var commit: Boolean = _

  /**
    * 如果为update,旧数据保存在old中,json字符串
    **/
  @BeanProperty
  var old: String = _

  override def toString: String = {
    s"$database,$table," + this.`type` + s",$data,$ts,$xid,$commit,$old"
  }

  def toJSONString: String = {
    val json = new JSONObject()
    json.put("database", database)
    json.put("table", table)
    json.put("type", `type`)
    json.put("data", data)
    json.put("ts", ts)
    json.put("xid", xid)
    json.put("commit", commit)
    json.put("old", old)
    json.toString()
  }
}
