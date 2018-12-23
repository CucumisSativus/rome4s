package net.cucumbersome.rome4s

import java.util.{List => JList}

import cats.data.NonEmptyList
import com.rometools.rome.feed.module.DCSubject
import com.rometools.rome.feed.synd._
import net.cucumbersome.rome4s.RichSyndFeed._

import scala.collection.JavaConverters._
object SyndFeedToRichSyndFeedParser {

  def parse(feed: SyndFeed): RichSyndFeed =
    RichSyndFeed(
      encoding = Option(feed.getEncoding),
      title = Option(feed.getTitle),
      description = Option(feed.getDescription),
      feedType = Option(feed.getFeedType),
      link = Option(feed.getLink),
      webMaster = Option(feed.getWebMaster),
      author = Option(feed.getAuthor),
      docs = Option(feed.getDocs),
      links = Option(feed.getLinks).flatMap(javaListToOptionOfNel(parseSyndLink)),
      entries = Option(feed.getEntries).flatMap(javaListToOptionOfNel(parseEntry))
    )

  private[rome4s] def parseEntry(entry: SyndEntry): RichSyndEntry =
    RichSyndEntry(
      uri = Option(entry.getUri),
      link = Option(entry.getLink),
      comments = Option(entry.getComments),
      updateDate = Option(entry.getUpdatedDate),
      title = Option(entry.getTitle),
      description = Option(entry.getDescription).map(parseSyndContent),
      links = Option(entry.getLinks).flatMap(javaListToOptionOfNel(parseSyndLink)),
      contents = Option(entry.getContents).flatMap(javaListToOptionOfNel(parseSyndContent)),
      authors = Option(entry.getAuthors).flatMap(javaListToOptionOfNel(parseSyndPerson)),
      categories = Option(entry.getCategories).flatMap(javaListToOptionOfNel(parseSyndCategory))
    )

  private[rome4s] def parseDcSubject(subject: DCSubject): RichDCSubject =
    RichDCSubject(
      taxonomyUri = Option(subject.getTaxonomyUri),
      value = Option(subject.getValue)
    )

  private[rome4s] def parseSyndPerson(person: SyndPerson): RichSyndPerson =
    RichSyndPerson(
      name = person.getName,
      uri = person.getUri,
      email = Option(person.getEmail)
    )

  private[rome4s] def parseSyndContent(content: SyndContent): RichSyndContent =
    RichSyndContent(
      `type` = content.getType,
      value = content.getValue,
      mode = content.getMode
    )

  private[rome4s] def parseSyndCategory(category: SyndCategory): RichSyndCategory =
    RichSyndCategory(
      taxonomyUri = Option(category.getTaxonomyUri),
      name = Option(category.getName)
    )

  private[rome4s] def parseSyndLink(link: SyndLink): RichSyndLink =
    RichSyndLink(
      href = Option(link.getHref),
      rel = Option(link.getRel),
      `type` = Option(link.getType),
      hreflang = Option(link.getHreflang),
      title = Option(link.getTitle),
      length = link.getLength
    )

  private[rome4s] def javaListToOptionOfNel[JavaModel, ScalaModel](convert: JavaModel => ScalaModel)
                                                                  (list: JList[JavaModel]): Option[NonEmptyList[ScalaModel]] = {
    NonEmptyList.fromList(
      list.asScala.toList.map(convert)
    )
  }
}
