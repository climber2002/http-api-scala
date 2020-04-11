package com.wegtam.books.pfhais.pure.api

import cats.effect.Sync
import cats.implicits._
import com.wegtam.books.pfhais.pure.db._
import com.wegtam.books.pfhais.pure.models._
import eu.timepit.refined.auto._
import fs2.Stream
import io.circe.syntax._
import org.http4s._
import org.http4s.circe._
import org.http4s.dsl._

final class ProductsRoutes[F[_]: Sync](repo: Repository[F]) extends Http4sDsl[F] {
  implicit def decodeProduct: EntityDecoder[F, Product] = jsonOf

  val routes: HttpRoutes[F] = HttpRoutes.of[F] {
    case GET -> Root / "products" =>
      val prefix = Stream.eval("[".pure[F])
      val suffix = Stream.eval("]".pure[F])
      val ps = repo.loadProducts
        .groupAdjacentBy(_._1)
        .map {
          case (id, rows) => Product.fromDatabase(rows.toList)
        }
        .collect {
          case Some(p) => p
        }
        .map(_.asJson.noSpaces)
        .intersperse(",")
      @SuppressWarnings(Array("org.wartremover.warts.Any"))
      val result: Stream[F, String] = prefix ++ ps ++ suffix
      Ok(result)
    case req @ POST -> Root / "products" =>
      req
        .as[Product]
        .flatMap { p =>
          for {
            cnt <- repo.saveProduct(p)
            res <- cnt match {
              case 0 => InternalServerError()
              case _ => NoContent()
            }
          } yield res
        }
        .handleErrorWith {
          case InvalidMessageBodyFailure(_, _) => BadRequest()
        }
  }

}
