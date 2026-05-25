package o1.adventure

import scala.collection.mutable.Map


class Player(startingArea: Area, var money: Int):

  private var currentLocation = startingArea
  private var quitCommandGiven = false
  private val playerInventory = Map[String, Item]()

  def hasQuit = this.quitCommandGiven

  def location = this.currentLocation

  def go(direction: String) =
    val destination = this.location.neighbor(direction)
    this.currentLocation = destination.getOrElse(this.currentLocation)
    if destination.isDefined then s"You go $direction." else s"You can't go $direction."


  def quit() =
    this.quitCommandGiven = true
    ""

  def get(itemName: String): String =
    var changedMoney = this.money
    if currentLocation.contains(itemName) then
      if itemName == "smoothie" then
        changedMoney += -20
      if itemName == "glönkero" then
        changedMoney += -11
      if itemName == "irish coffee" then
        changedMoney += -11
      if itemName == "karhu" then
        changedMoney += -9
      if itemName == "blueberry shot" then
        changedMoney += -5
      if itemName == "minttu shot" then
        changedMoney += -10
      if itemName == "lottery ticket" then
        changedMoney += 56000000
      if changedMoney < 0 then
        "The bartender announces that your card declined.\nEither you're too wasted or you'd better call nordea."
      else
        this.money = changedMoney
        val removedItem = currentLocation.removeItem(itemName)
        removedItem.map(x => (this.playerInventory += (x.name -> x)))
        s"${this.playerInventory(itemName).description}"
    else
      s"There is no ${itemName} here to pick up. How drunk are you?"

  def has(itemName:String): Boolean =
    this.playerInventory.keys.exists(_ == itemName)
    
  def call(name: String) =
    if name == "nordea" then
      "Hello! Nordea speaking. Good that you called." +
      "\nWe have been trying to reach you regarding your financial behaviour." +
      s"\nIt seems that your balance has dropped to ${this.money} euros." +
      "\nOur team suggests you gamble in EuroJackpot, the odds are looking highly promising for tonight." +
      "\nGood night and best of luck for your endeavours!"
    else if name == "mom" then
      this.money += 50
      "Honey where are you? And why do you sound weird?\nAre you drinking again? Or oh no... have you gone back to using?" +
      "\nI told your dad those substnaces aren't good for you.\nGo buy some food. I'll send you some money."
    else
      "Looks like you don't have that number.\nHave you tried Nordea?"
    

  /** Returns a brief description of the player’s state, for debugging purposes. */
  override def toString = "Now at: " + this.location.name

end Player

