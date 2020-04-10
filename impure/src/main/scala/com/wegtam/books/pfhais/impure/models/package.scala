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

import java.util.UUID

//import eu.timepit.refined.api.Refined
//import eu.timepit.refined.string.MatchesRegex
//import eu.timepit.refined._
//import eu.timepit.refined.collection.NonEmpty

package object models {
  type LanguageCode = String // Refined MatchesRegex[W.`"^[a-z]{2}$"`.T]
  type ProductName  = String // Refined NonEmpty

  type ProductId = UUID
}
