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

import com.wegtam.books.pfhais.pure.models.{ LanguageCode, ProductId, ProductName }
import fs2.Stream
import com.wegtam.books.pfhais.pure.models._

import scala.collection.immutable.Seq

trait Repository[F[_]] {
  def loadProduct(id: ProductId): F[Seq[(ProductId, LanguageCode, ProductName)]]

  def loadProducts(): Stream[F, (ProductId, LanguageCode, ProductName)]

  def saveProduct(p: Product): F[Int]

  def updateProduct(p: Product): F[Int]
}
