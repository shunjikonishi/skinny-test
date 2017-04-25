package com.example

import skinny.orm._, feature._
import scalikejdbc._
import org.joda.time._

case class Test(
  bookId: Long,
  readCount: Long,
  completionCount: Long,
  createdAt: DateTime,
  updatedAt: DateTime
)

object Test extends SkinnyCRUDMapper[Test] {
  override lazy val tableName = "test"
  override lazy val defaultAlias = createAlias("t")

  override def useAutoIncrementPrimaryKey: Boolean = false
  override def primaryKeyFieldName = "bookId"

  override def extract(rs: WrappedResultSet, rn: ResultName[Test]): Test = {
    autoConstruct(rs, rn)
  }

  afterCreate { (session, values, id) =>
    println("afterCreate: " + id)
  }

}
