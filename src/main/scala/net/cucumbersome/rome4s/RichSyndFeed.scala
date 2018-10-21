package net.cucumbersome.rome4s

import java.time.LocalDateTime

import net.cucumbersome.rome4s.RichSyndFeed.{RichSyndEntry, RichSyndLink}

case class RichSyndFeed(
                       encoding: Option[String],
                       title: Option[String],
                       description: Option[String],
                       feedType: String,
                       link: String,
                       webMaster: Option[String],
                       managingEditor: Option[String],
                       docs: Option[String],
                       generator: Option[String],
                       styleSheet: Option[String],
                       links: List[RichSyndLink],
                       icon: Option[String],
                       image: Option[String],
                       entries: List[RichSyndEntry]
                       )

object RichSyndFeed{
  case class RichSyndLink(
                         href: String,
                         ref: String,
                         `type`: String,
                         hreflang: Option[String],
                         title: Option[String]
                         )

  case class RichSyndEntry(
                          uri: String,
                          link: String,
                          comments: Option[String],
                          updateDate: LocalDateTime,
                          title: RichSyndContent,
                          description: RichSyndContent,
                          links: List[RichSyndLink],
                          contents: List[RichSyndContent],
                          entries: List[RichSyndEntry],
                          authors: List[RichSyndPerson],
                          categories: List[RichSyndCategory]
                          )
  case class RichSyndContent(
                            `type`: String,
                            value: String
                            )
  case class RichSyndPerson(
                           name: String,
                           uri: String,
                           email: Option[String]
                           )
  case class RichDCSubject(
                          taxonomyUri: String,
                          value: String,
                          )
  case class RichSyndCategory(
                              subject: RichDCSubject
                             )
}