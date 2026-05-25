package o1.adventure

import scala.collection.mutable.Map

class Area(var name: String, var description: String):

  private val neighbors = Map[String, Area]()

  private val items = Map[String, Item]()

  
  def neighbor(direction: String) = this.neighbors.get(direction)

  
  def setNeighbor(direction: String, neighbor: Area) =
    this.neighbors += direction -> neighbor

  
  def setNeighbors(exits: Vector[(String, Area)]) =
    this.neighbors ++= exits

  
  def fullDescription: String =
    var itemList = ""
    if this.items.nonEmpty then
      itemList = "\nYou see here: " + this.items.keys.mkString(", ")
      
    val exitList = "\n\nExits available: " + this.neighbors.keys.mkString(" ")
    this.description + itemList + exitList

  
  override def toString =
    this.name + ": " + this.description.replaceAll("\n", " ").take(150)

  
  def addItem(item: Item):Unit = this.items += item.name -> item

  
  def contains(itemName: String): Boolean = this.items.keys.exists(_ == itemName)

  
  def removeItem(itemName: String): Option[Item] = this.items.remove(itemName)

end Area

