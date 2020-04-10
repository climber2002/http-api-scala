/*
 * CC0 1.0 Universal (CC0 1.0) - Public Domain Dedication
 *
 *                                No Copyright
 *
 * The person who associated a work with this deed has dedicated the work to
 * the public domain by waiving all of his or her rights to the work worldwide
 * under copyright law, including all related and neighboring rights, to the
 * extent allowed by law.
 */

package com.wegtam.books.pfhais.impure

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import org.flywaydb.core.Flyway

import scala.concurrent.ExecutionContext

object Impure {

  def main(args: Array[String]): Unit = {
    implicit val system: ActorSystem    = ActorSystem()
    implicit val mat: ActorMaterializer = ActorMaterializer()
    implicit val ec: ExecutionContext   = system.dispatcher

    val config = system.settings.config

    val url = "jdbc:postgresql://" +
      config.getString("database.db.properties.serverName") +
      ":" + config.getString("database.db.properties.portNumber") +
      "/" + config.getString("database.db.properties.databaseName")

    val user = config.getString("database.db.properties.user")
    val pass = config.getString("database.db.properties.password")

    val flyway = Flyway.configure().dataSource(url, user, pass).load()
    val _      = flyway.migrate()
  }

}
