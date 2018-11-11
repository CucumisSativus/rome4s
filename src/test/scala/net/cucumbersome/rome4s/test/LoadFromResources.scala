package net.cucumbersome.rome4s.test

import java.io.FileNotFoundException

import scala.io.Source
import scala.util.Try

trait LoadFromResources {
  def readResource(resource: String): String =
    Try(Source.fromResource(resource).getLines.toList.mkString("\n"))
      .getOrElse(throw new FileNotFoundException(resource))
}
