package net.cucumbersome.rome4s

import com.rometools.rome.feed.module.DCSubject
import com.rometools.rome.feed.synd.{SyndFeed, SyndPerson}
import net.cucumbersome.rome4s.RichSyndFeed.{RichDCSubject, RichSyndPerson}

object SyndFeedToRichSyndFeedParser {

  def parse(feed: SyndFeed): RichSyndFeed = ???

  private[rome4s] def parseDcSubject(subject: DCSubject): RichDCSubject =
    RichDCSubject(subject.getTaxonomyUri, subject.getValue)

  private[rome4s] def parseSyndPerson(person: SyndPerson): RichSyndPerson =
    RichSyndPerson(person.getName, person.getUri, Option(person.getEmail))
}
