package net.cucumbersome.rome4s.test

import com.rometools.rome.feed.module.{DCSubject, DCSubjectImpl}
import com.rometools.rome.feed.synd._
import org.scalacheck.{Arbitrary, Gen}

trait Generators {
  private val optionStringGen: Gen[Option[String]] =
    Arbitrary.arbitrary[Option[String]]
  private val stringGen: Gen[String] = Arbitrary.arbitrary[String]

  val dcSubjectGenerator: Gen[DCSubject] = for {
    taxonomyUri <- optionStringGen
    value       <- optionStringGen
  } yield {
    val a = new DCSubjectImpl()
    a.setTaxonomyUri(taxonomyUri.orNull)
    a.setValue(value.orNull)
    a
  }

  val syndPerson: Gen[SyndPerson] = for {
    name  <- stringGen
    uri   <- stringGen
    email <- optionStringGen
  } yield {
    val a = new SyndPersonImpl
    a.setName(name)
    a.setUri(uri)
    a.setEmail(email.orNull)
    a
  }

  val syndContent: Gen[SyndContent] = for {
    typ   <- stringGen
    value <- stringGen
    mode  <- stringGen
  } yield {
    val a = new SyndContentImpl()
    a.setMode(mode)
    a.setValue(value)
    a.setType(typ)
    a
  }

  val syndCategory: Gen[SyndCategory] = for {
    subject <- dcSubjectGenerator
  } yield {
    val a = new SyndCategoryImpl()
    a.setName(subject.getValue)
    a.setTaxonomyUri(subject.getTaxonomyUri)
    a
  }

  val syndLink: Gen[SyndLink] = for {
    href     <- optionStringGen
    rel      <- optionStringGen
    typ      <- optionStringGen
    hreflang <- optionStringGen
    title    <- optionStringGen
    length   <- Arbitrary.arbitrary[Long]
  } yield {
    val a = new SyndLinkImpl
    a.setHref(href.orNull)
    a.setRel(rel.orNull)
    a.setType(typ.orNull)
    a.setHreflang(hreflang.orNull)
    a.setTitle(title.orNull)
    a.setLength(length)
    a
  }

  implicit val subjectArb: Arbitrary[DCSubject]   = Arbitrary(dcSubjectGenerator)
  implicit val personArb: Arbitrary[SyndPerson]   = Arbitrary(syndPerson)
  implicit val contentArb: Arbitrary[SyndContent] = Arbitrary(syndContent)
  implicit val syndCategoryArb: Arbitrary[SyndCategory] = Arbitrary(
    syndCategory)
  implicit val syndLinkArb: Arbitrary[SyndLink] = Arbitrary(syndLink)
}
