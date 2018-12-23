package net.cucumbersome.rome4s

import com.rometools.rome.feed.module.DCSubject
import com.rometools.rome.feed.synd.{SyndCategory, SyndContent, SyndPerson}
import net.cucumbersome.rome4s.RichSyndFeed.{RichDCSubject, RichSyndCategory, RichSyndContent, RichSyndPerson}
import net.cucumbersome.rome4s.SyndFeedToRichSyndFeedParser._
import net.cucumbersome.rome4s.test.Generators
import org.specs2._
import org.specs2.mutable.Specification

class SyndFeedToRichSyndFeedParserTest extends Specification with ScalaCheck with Generators {
  "This is the specification of synd feed parser".txt

  "Parsing DCSubject" >> prop { dcSubject: DCSubject =>
    val expected = RichDCSubject(Option(dcSubject.getTaxonomyUri), Option(dcSubject.getValue))
    parseDcSubject(dcSubject) should_=== expected
  }

  "Parsing person" >> prop { syndPerson: SyndPerson =>
    val expected = RichSyndPerson(syndPerson.getName, syndPerson.getUri, Option(syndPerson.getEmail))
    parseSyndPerson(syndPerson) should_=== expected
  }

  "Parsing content" >> prop { syndContent: SyndContent =>
    val expected = RichSyndContent(syndContent.getType, syndContent.getValue, syndContent.getMode)
    parseSyndContent(syndContent) should_=== expected
  }

  "Parsing syndCategory" >> prop { syndCategory: SyndCategory =>
    val expected = RichSyndCategory(Option(syndCategory.getTaxonomyUri), Option(syndCategory.getName))
    parseSyndCategory(syndCategory) should_=== expected
  }
}
