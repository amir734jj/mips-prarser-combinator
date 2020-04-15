package parser

import models.tokens.misc.Register.{RegisterT, names}

import scala.language.{implicitConversions, postfixOps}
import scala.util.parsing.combinator.RegexParsers

object GenericParser extends RegexParsers {

  type ParserT[E] = Parser[E]

  // Separator which is a comma followed by whitespace or just whitespace
  def separatorP: ParserT[Option[String]] = literal(",") ?

  // Word parser
  def wordP: Parser[String] = """[\w]+""".r ^^ { x => x }

  // Number parser
  def numberP: Parser[Int] = """^-?[\d][\d]*""".r ^^ { _.toInt }

  // Register parser
  def registerP: Parser[RegisterT] = names
    .foldRight(literal(names.head))((x, xs) => literal(x) ||| xs)
}
