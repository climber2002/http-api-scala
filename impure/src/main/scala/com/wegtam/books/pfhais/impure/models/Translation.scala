package com.wegtam.books.pfhais.impure.models

import io.circe.{Decoder, Encoder}

final case class Translation(lang: LanguageCode, name: ProductName)

object Translation {
  implicit val decode: Decoder[Translation] =
    Decoder.forProduct2("lang", "name")(Translation.apply)

  implicit val encode: Encoder[Translation] =
    Encoder.forProduct2("lang", "name")(t => (t.lang, t.name))
}