package net.cucumbersome.rome4s.test

import com.rometools.rome.feed.module.{DCSubject, DCSubjectImpl}
import com.rometools.rome.feed.synd.{SyndPerson, SyndPersonImpl}
import org.scalacheck.{Arbitrary, Gen}

trait Generators {

  val dcSubjectGenerator: Gen[DCSubject] = for {
    taxonomyUri <- Arbitrary.arbitrary[String]
    value <- Arbitrary.arbitrary[String]
  } yield {
    val a = new DCSubjectImpl()
    a.setTaxonomyUri(taxonomyUri)
    a.setValue(value)
    a
  }

  val syndPerson: Gen[SyndPerson] = for {
    name <- Arbitrary.arbitrary[String]
    uri <- Arbitrary.arbitrary[String]
    email <- Arbitrary.arbitrary[Option[String]]
  } yield {
    val a = new SyndPersonImpl
    a.setName(name)
    a.setUri(uri)
    a.setEmail(email.orNull)
    a
  }

  implicit val subjectArb: Arbitrary[DCSubject] = Arbitrary(dcSubjectGenerator)
  implicit val personArb: Arbitrary[SyndPerson] = Arbitrary(syndPerson)
}
