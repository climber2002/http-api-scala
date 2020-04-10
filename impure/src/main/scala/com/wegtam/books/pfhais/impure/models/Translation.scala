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

package com.wegtam.books.pfhais.impure.models

import cats._
import io.circe._

final case class Translation(lang: LanguageCode, name: ProductName)

object Translation {
  implicit val decode: Decoder[Translation] =
    Decoder.forProduct2("lang", "name")(Translation.apply)

  implicit val encode: Encoder[Translation] =
    Encoder.forProduct2("lang", "name")(t => (t.lang, t.name))

  implicit val order: Order[Translation] = new Order[Translation] {
    def compare(x: Translation, y: Translation): Int =
      x.lang.compare(y.lang)
  }
}
