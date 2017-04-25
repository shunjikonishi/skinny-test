package com.example

import scalikejdbc._

object Main {

  val DATABASE_URL = sys.env("DATABASE_URL") //postgres://username:password@localhost/dbname

  def main(args: Array[String]): Unit = {
    Class.forName("org.postgresql.Driver")

    DATABASE_URL match {
      case JDBCUrl.HerokuPostgresRegexp(user, pass, host, dbname) =>
        val url = s"jdbc:postgresql://$host/$dbname"
        ConnectionPool.singleton(url, user, pass)
      case _ => throw new IllegalStateException()
    }

    implicit val session = AutoSession

    sql"""
      DROP TABLE IF EXISTS test
    """.execute.apply()
    sql"""
      CREATE TABLE IF NOT EXISTS test (
        book_id                   BIGINT       PRIMARY KEY,
        read_count                BIGINT       NOT NULL DEFAULT 0,
        completion_count          BIGINT       NOT NULL DEFAULT 0,
        created_at                TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
        updated_at                TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
      );
    """.execute.apply()
    val id = Test.createWithNamedValues(
      Test.column.bookId -> 1L,
      Test.column.readCount -> 1L,
      Test.column.completionCount -> 1L
    )
    println(id)

    // It works 
    /*
    Test.createWithNamedValues(
      Test.column.bookId -> 1L,
      Test.column.readCount -> 1L,
      Test.column.completionCount -> 1L
    )
    println(Test.findById(1))
    */
  }
}
