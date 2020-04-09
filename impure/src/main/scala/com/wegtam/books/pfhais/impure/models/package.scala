package com.wegtam.books.pfhais.impure

import java.util.UUID

import eu.timepit.refined.api.Refined
import eu.timepit.refined.string.MatchesRegex
import eu.timepit.refined._
import eu.timepit.refined.collection.NonEmpty

package object models {
  type LanguageCode = String Refined MatchesRegex[W.`"^[a-z]{2}$"`.T]
  type ProductName = String Refined NonEmpty

  type ProductId = UUID
}
