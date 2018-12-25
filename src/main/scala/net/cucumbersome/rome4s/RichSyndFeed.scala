package net.cucumbersome.rome4s

import java.util.Date

import com.rometools.rome.feed.module.DCSubject
import com.rometools.rome.feed.synd._
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
                         links: List[RichSyndLink],
                         entries: List[RichSyndEntry],
                         original: SyndFeed

                       )

object RichSyndFeed {

  case class RichSyndLink(
                           href: Option[String],
                           rel: Option[String],
                           `type`: Option[String],
                           hreflang: Option[String],
                           title: Option[String],
                           length: Long,
                           original: SyndLink
                         )

  case class RichSyndEntry(
                            uri: Option[String],
                            link: Option[String],
                            comments: Option[String],
                            updateDate: Option[Date],
                            title: Option[String],
                            description: Option[RichSyndContent],
                            links: List[RichSyndLink],
                            contents: List[RichSyndContent],
                            authors: List[RichSyndPerson],
                            categories: List[RichSyndCategory],
                            original: SyndEntry
                          )

  case class RichSyndContent(
                              `type`: String,
                              value: String,
                              mode: String,
                              original: SyndContent
                            )

  case class RichSyndPerson(
                             name: String,
                             uri: String,
                             email: Option[String],
                             original: SyndPerson
                           )

  case class RichDCSubject(
                            taxonomyUri: Option[String],
                            value: Option[String],
                            original: DCSubject
                          )

  case class RichSyndCategory(
                               taxonomyUri: Option[String],
                               name: Option[String],
                               original: SyndCategory
                             )

}