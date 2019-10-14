package base

import base.word.funnel.WordFunnel

import scala.io.Source

object App extends scala.App {

  val wordList = {
    val source = Source.fromFile("resources/enable1.txt")
    val file = source.getLines().toSet
    source.close()
    file
  }

  println(wordList.size)

  val funnel = new WordFunnel(wordList).wordFunnel("preformationists")

  println(funnel)
}
