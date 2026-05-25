package o1.adventure


class Action(input: String):

  private val commandText = input.trim.toLowerCase
  private val verb        = commandText.takeWhile( _ != ' ' )
  private val modifiers   = commandText.drop(verb.length).trim

  def execute(actor: Player): Option[String] =
    this.verb match
      case "call"      => Some(actor.call(this.modifiers))
      case "go"        => Some(actor.go(this.modifiers))
      case "quit"      => Some(actor.quit())
      case "get"       => Some(actor.get(this.modifiers))
      case "help"      => Some("You need help? Ummmmmm okayyy........ \nTo win you need to:\n1. Get the overalls.\n2. Get the pass from Kamppi." +
                               "\n3. Collect all the stamps - you get them by purchasing a drink:\n   an Irish coffee from the Old Irish Pub,\n   a Minttu shot from Kraken\n   and a Glönkero from Villi Väinö \nThe stamp will be added automatically once you get the drink." +
                               "\n4. After that go home while still having 10 euros. (If money runs low, gambling's always an option...)"+
                               "\n\nCommands: \n   go *direction* \n   get *item*\n   call *nordea etc.* (to check your balance)\n   quit (If you are a loser) \n   help (Oh wait...you are a loser)")
      case other       => None

  
  override def toString = s"$verb (modifiers: $modifiers)"

end Action

