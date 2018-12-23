package net.cucumbersome.rome4s

import java.util.Date

import cats.data.NonEmptyList
import net.cucumbersome.rome4s.RichSyndFeed.{RichSyndEntry, RichSyndLink}

case class RichSyndFeed(
                         encoding: Option[String],
                         title: Option[String],
                         description: Option[String],
                         feedType: Option[String],
                         link: Option[String],
                         webMaster: Option[String],
                         author: Option[String],
                         docs: Option[String],
                         links: Option[NonEmptyList[RichSyndLink]],
                         entries: Option[NonEmptyList[RichSyndEntry]]
                       )

object RichSyndFeed {

  case class RichSyndLink(
                           href: Option[String],
                           rel: Option[String],
                           `type`: Option[String],
                           hreflang: Option[String],
                           title: Option[String],
                           length: Long
                         )

  case class RichSyndEntry(
                            uri: Option[String],
                            link: Option[String],
                            comments: Option[String],
                            updateDate: Option[Date],
                            title: Option[String],
                            description: Option[RichSyndContent],
                            links: Option[NonEmptyList[RichSyndLink]],
                            contents: Option[NonEmptyList[RichSyndContent]],
                            authors: Option[NonEmptyList[RichSyndPerson]],
                            categories: Option[NonEmptyList[RichSyndCategory]]
                          )

  case class RichSyndContent(
                              `type`: String,
                              value: String,
                              mode: String
                            )

  case class RichSyndPerson(
                             name: String,
                             uri: String,
                             email: Option[String]
                           )

  case class RichDCSubject(
                            taxonomyUri: Option[String],
                            value: Option[String],
                          )

  case class RichSyndCategory(
                               taxonomyUri: Option[String],
                               name: Option[String],
                             )

}