package org.ko.generator.entity

case class Table (
                   name: String,
                   entityName: String,
                   comment: String,
                   columns: List[Column]
                 )
