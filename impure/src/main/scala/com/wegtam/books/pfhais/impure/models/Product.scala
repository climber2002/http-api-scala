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

import cats.data.NonEmptySet
import io.circe._

final case class Product(id: ProductId, names: NonEmptySet[Translation])

object Product {
  implicit val decode: Decoder[Product] =
    Decoder.forProduct2("id", "names")(Product.apply)

  implicit val encode: Encoder[Product] =
    Encoder.forProduct2("id", "names")(p => (p.id, p.names))
}
