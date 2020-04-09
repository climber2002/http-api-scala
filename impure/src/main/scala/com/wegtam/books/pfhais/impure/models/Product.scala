package com.wegtam.books.pfhais.impure.models

import cats.data.NonEmptySet
import io.circe.{Decoder, Encoder}

final case class Product(id: ProductId, names: NonEmptySet[Translation])

object Product {
  implicit val decode: Decoder[Product] =
    Decoder.forProduct2("id", "names")(Product.apply)

  implicit val encode: Encoder[Product] =
    Encoder.forProduct2("id", "names")(p => (p.id, p.names))
}
