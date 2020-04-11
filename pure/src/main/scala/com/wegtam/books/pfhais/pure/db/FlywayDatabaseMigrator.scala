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

package com.wegtam.books.pfhais.pure.db

import cats.effect.IO
import com.wegtam.books.pfhais.pure.{ DatabaseLogin, DatabasePassword, DatabaseUrl }
import org.flywaydb.core.Flyway
import eu.timepit.refined.auto._

final class FlywayDatabaseMigrator extends DatabaseMigrator[IO] {
  override def migrate(url: DatabaseUrl, user: DatabaseLogin, pass: DatabasePassword): IO[Int] =
    IO {
      val flyway: Flyway = Flyway
        .configure()
        .dataSource(url, user, pass)
        .load()
      flyway.migrate()
    }
}
