package com.dz.stubby.core.service.model

import org.scalatest.FunSuite
import scala.util.matching.Regex
import java.util.regex.Pattern

class TextPatternTest extends FunSuite {

  test("equality with self") {
    assert(new TextPattern("foo") === new TextPattern("foo"))
  }

  test("equality with string") {
    assert(new TextPattern("foo") === "foo")
  }

  test("equality with Scala Regex") {
    assert(new TextPattern("foo") === new Regex("foo"))
  }

  test("equality with Java Pattern") {
    assert(new TextPattern("foo") === Pattern.compile("foo"))
  }

  test("matches") {
    assert(new TextPattern("fo+").matches("foo"))
  }

  test("matches with groups") {
    assert(new TextPattern("(f)(o)+").matches("foo"))
  }
  
  test("doesn't match sub-string") {
    assert(!new TextPattern("fo+").matches("foobar"))
  }

}
