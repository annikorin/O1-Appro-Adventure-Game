package o1.adventure


class Adventure:

  /** the name of the game */
  val title = "O1-Appro"

  private val kamppi      = Area("Kamppi", "You are in the heart of the city.\nA group of friendly roadmen greet you.")
  private val oldIrishPub = Area("The Old Irish Pub", "You are surrounded by creeps and loud music.\nIt all clicks, you are at the Old Irish Pub.")
  private val home        = Area("Home", "Finally home! Go have fun at the appro!\nDon't forget your overalls.\nKamppi is up north.")
  private val kraken      = Area("Kraken", "Karaoke and good vibes.\nYou are at Kraken!")
  private val villiWäinö  = Area("Villi Wäinö", "You are surrounded by underage people.\nGood thing Villi Wäinö knows their drinks.")
  private val classRoom   = Area("The Programming Classroom", "You are at the O1 exercise session.\nTime to close all the tabs.\nThe O1-Appro is starting soon.")
  private val park        = Area("The Park", "You find yourself at The Park.\nIt is a bit chilly and your hair is twirling in the wind.\nYou notice your 'old pal' in the corner waving at you\nand the withdrawals start kicking in.")
  private val store       = Area("R-kioski", "You enter the nearby R-kiosk. Feeling like a shopping spree?")
  private val destination = home

  kamppi     .setNeighbors(Vector("north" -> oldIrishPub, "east" -> villiWäinö, "south" -> home,       "west" -> kraken      ))
  home       .setNeighbors(Vector("north" -> kamppi,      "east" -> classRoom,                         "west" -> store       ))
  kraken     .setNeighbors(Vector(                        "east" -> kamppi,     "south" -> store,      "west" -> park        ))
  park       .setNeighbors(Vector(                        "east" -> kraken,     "south" -> villiWäinö, "west" -> oldIrishPub ))
  oldIrishPub.setNeighbors(Vector(                        "east" -> park,       "south" -> kamppi                            ))
  classRoom  .setNeighbors(Vector(                                                                     "west" -> home        ))
  villiWäinö .setNeighbors(Vector("north" -> park,                                                     "west" -> kamppi      ))
  store      .setNeighbors(Vector("north" -> kraken,      "east" -> home                                                     ))

  
  home.addItem(Item("overalls", "The elctricity is out but you recognise your beloved overalls by their smell.\nI should really save money to cover my electrical bills\nand get the washing machine running."))
  kamppi.addItem(Item("appro pass", "The O1 professor Juha Sorva hands you the Appro pass.\nLooks like you need three stamps.\nYou get them by purchasing a drink:\n   an Irish coffee from the Old Irish Pub,\n   a Minttu shot from Kraken\n   and a Glönkero from Villi Väinö"))
  kraken.addItem(Item("blueberry shot", "What a delight, doesn't even taste like alcohol!\nFrom Nordea: -5e"))
  kraken.addItem(Item("minttu shot", "Such a refresher.\nFrom Nordea: -10e"))
  oldIrishPub.addItem(Item("irish coffee", "WOW, that really gets my energy levels up!\nFrom Nordea: -11e"))
  oldIrishPub.addItem(Item("karhu", "Oh no, the bear inside me is awakened.\nFrom Nordea: -9e"))
  park.addItem(Item("suspicious coctail", "What an explosion of flavours. Side note: Why did my pal bring a twin??"))
  park.addItem(Item("flowers", "What a wonderful garden arrangement.\nSomeone really put in the work.\nBetter take this home with me.\n*you rip them out with roots and put in your back pocket*"))
  park.addItem(Item("drugs", ""))
  villiWäinö.addItem(Item("glönkero", "Christmas is right around the corner.\nFrom Nordea: -11e"))
  villiWäinö.addItem(Item("smoothie", "It's now or never. I'm getting fit again and getting my girlfriend back!\nTotally worth the price.\nFrom Nordea: -20e"))
  store.addItem(Item("apples", "I'm so hungry my stomach is twisting.\nThe inflation is getting out of hand.\nI'll pay them back next time.\nRUN!!!!"))
  store.addItem(Item("lottery ticket", "I really need to double my budget.\nHoly smokes!! I won EuroJackPot.\nFrom Nordea: +56million"))
  
    
  val player = Player(classRoom, 30)

  var turnCount = 0
 
  val timeLimit = 30


  def isComplete =
    this.player.location == this.destination && this.player.has("overalls") && this.player.has("appro pass") && this.player.has("glönkero") && this.player.has("irish coffee") && this.player.has("minttu shot") && this.player.money >= 10


  def isOver =
    this.isComplete || this.player.hasQuit || this.turnCount == this.timeLimit || this.player.has("drugs")


  def welcomeMessage = "Welcome to the O1 Appro Game!\nYou are a student on a tight budget who wants to have a life outside of programming." +
    "\nOnly 30 euros in your bank account you are planning to attend the infamous O1 Appro." +
    "\nTo win the game, buy the required drinks and collect the stamps for the pass given at Kamppi.\nReturn home before midnight with at least 10 euros on your name."+
    "\nIf you need help type 'help'. "


  def goodbyeMessage =
    if this.isComplete || this.player.money > 55000000 then
      "'The Winner Takes it All' by ABBA plays in the background as you bust through the door." +
      "\nCongratulations, you are now a millionaire!" +
      "\nHowever, you soon realize the night can't be over yet." +
      "\nYou bust through the broken door again and return to the buzzling center of the city,\nnot returning until every last cent is spent."

    else if this.isComplete then
      "What a night! You managed to get wasted on a budget and made it home.\nTime to go to sleep at last. Oh wait...I have one more programming chapter to do."
    else if this.turnCount == this.timeLimit then
      "Times up bro. It wasn't that hard..."
    else if this.player.has("drugs") then
      "WHAT DID YOU DO??!?!?!?!?! *died from overdose*"
    else  // game over due to player quitting
      "Skill issue"


  def playTurn(command: String): String =
    val action = Action(command)
    val outcomeReport = action.execute(this.player)
    if outcomeReport.isDefined then
      this.turnCount += 1
    outcomeReport.getOrElse(s"""Unknown command: "$command".""")

end Adventure

