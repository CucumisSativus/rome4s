package net.cucumbersome.rome4s

import java.io.Reader

import cats.MonadError
import com.rometools.rome.feed.synd.SyndFeed

trait RichSyndFeedInput[F[_]] {
  def build(reader: Reader): F[SyndFeed]
}

object RichSyndFeedInput {

  def build[F[_]](fi: RichSyndFeedInput[F])(reader: Reader)(implicit F: MonadError[F, RichSyndBuilderError]): F[RichSyndFeed] = {
    F.map(fi.build(reader))(SyndFeedToRichSyndFeedParser.parse)
  }


  sealed trait RichSyndBuilderError

  case object NoParserApplicable extends RichSyndBuilderError

  case class ParsingError(message: String, cause: Throwable)

  type ParsingResult[F[_]] = MonadError[F, RichSyndBuilderError]
}
