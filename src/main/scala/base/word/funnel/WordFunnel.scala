package base.word.funnel

import OrderedSubset._

import scala.collection.{Set, mutable}

final class WordFunnel(wordList: Set[String]) {

  private val memoisedChildren: mutable.Map[String, Set[String]] =
    mutable.Map.empty

  protected def childrenOfWord(starterWord: String,
                               possibleWords: Set[String]): Set[String] =
    memoisedChildren.getOrElseUpdate(starterWord, {
      def isChild(x: String) =
        x != starterWord && x.orderedSubsetOf(starterWord)
      possibleWords filter isChild
    })

  def wordFunnel(starterWord: String): Seq[String] = {
    def make(starterWord: String,
             wordSet: Set[String],
             funnel: Seq[String]): Seq[String] = {
      childrenOfWord(starterWord, wordSet).toSeq match {
        case Nil      => funnel
        case x :: Nil => funnel :+ x
        case xs =>
          xs.map(x => make(x, xs.toSet, funnel :+ x))
            .maxBy(_.size)
      }
    }

    starterWord +: make(starterWord, wordList, Seq.empty)
  }

}
