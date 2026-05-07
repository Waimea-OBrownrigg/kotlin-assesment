/**
 * =====================================================================
 * Programming Project for NCEA Level 3, Standard 91906
 * ---------------------------------------------------------------------
 * Project Name:   Escape Room
 * Project Author: Oliver Brownrigg
 * GitHub Repo:    https://github.com/Waimea-OBrownrigg/kotlin-assesment
 * ---------------------------------------------------------------------
 * Notes:
 * PROJECT NOTES HERE
 * =====================================================================
 */

import com.formdev.flatlaf.themes.FlatMacDarkLaf
import java.awt.Font
import javax.swing.*
import kotlin.system.exitProcess

/**
 * Application entry point
 */
fun main() {
    FlatMacDarkLaf.setup()          // Initialise the LAF

    val player = Player()           // Get an app state object
}

/**
 * This is where information about the various rooms are stored
 *
 *  @property name, The name of the room
 *  @property roomDesc, A description of the room, may contain a hint to get the item
 *  @property roomDescClear, A replacement for the description for once you have collected the item
 *  @property lock, The item required to enter the room, if empty, no item is required
 *  @property lock2, The second item required to enter the room, if applicable
 *  @property lock3, The third item required to enter the room, if applicable
 *  @property lock, A description that gives you information on what you need to enter a room
 *  @property lock2, A description that replaces the first one if the second lock is being used
 *  @property lock3, A description that replaces the second one if the third lock is being used
 *  @property freeItem, An item you get just for entering the room and searching
 *  @property itemLock, An item required to get the item in this room
 *  @property item, The item you receive for fulfilling the criteria of itemLock
 *  @property adjacent, A list of places each room can travel to.
 */
class Room(
    val name: String,
    var roomDesc: String,
    var roomDescClear: String,
    var lock: String,
    var lock2: String,
    var lock3: String,
    var lockdes: String,
    var lockdes2: String,
    var lockdes3: String,
    var freeItem: String,
    var itemLock: String,
    var item: String,
) {
    val adjacent = mutableListOf<Room>()

    fun addDoor(room: Room) {
        adjacent.add(room)
    }
}

/**
 * This is where information about each item is kept.
 */
class Item(
    val name: String,
    val description: String,
) { }

/**
 * This class serves as a sort of central hub for the game.
 * Important information and most of the functions are stored here.
 *
 *  @property location, The room the player is currently in.
 *  @property inventory, The items the player currently has access to.
 *  @property items, All of the items in the game.
 *  @property rooms, All of the rooms in the game.
 *  @property inspectInventory, A special version of the inventory that includes descriptions for all the items.
 *  @property inspectUsed, A variable that states whether the inspect menu has been used.
 *  @property tutorialUsed, A variable that states whether the tutorial has been used.
 */
class Player {
    var location = "Driveway"
    val inventory = mutableListOf<String>()
    val items = mutableListOf<Item>()
    val rooms: MutableList<Room> = mutableListOf()
    var inspectInventory = mutableListOf<Item>()
    var inspectUsed = false
    var tutorialused = false

    // With these vars, all functions can access these windows.
    lateinit var mainWindow: MainWindow
    lateinit var roomWindow: RoomWindow
    lateinit var travelWindow: TravelWindow
    lateinit var keyWindow: KeyWindow
    lateinit var inspectWindow: InspectWindow
    lateinit var wordWindow: WordWindow
    lateinit var tutorialWindow: TutorialWindow

    //Creates all of the rooms and items, then adds them to their respective lists.
    init {

        val driveway = Room("Driveway",
            "The mansion looms before you under a cloudy gray sky, a flattened cardboard box has found its way up onto the roof, where it lies, a blemish upon the otherwise magnificent architecture, if only you could knock it down...",
            "The mansion looms before you under a night sky, its windows illuminated by a warm light from within.",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Ball",
            "Cardboard Box")

        val garden = Room("Garden",
            "A well kept garden surrounds a slightly overgrown lawn, that's why you're here, after all.",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Metal Detector",
            "Rusty Key")

        val tree = Room("Oak Tree",
            "A large oak tree stands here, it's leafy branches extending high above.",
            "After climbing up the tree, you fond a blue ball wedged in the branches!",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Knowledge",
            "Ball")

        val pool = Room("Pool",
            "A tiled surface surrounds the water of the pool, looking closer, it seems that there's something glimmering at the bottom...",
            "A tiled surface surrounds the water of the pool, which sits still and quiet.",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Net",
            "Basement Key")

        val greenhouse = Room("Greenhouse",
            "Inside the greenhouse, it's uncomfortably warm, various plants line the side of a path through the center, and towards the end, a large bunch of bananas!",
            "Empty",
            "Greenhouse Key",
            "Empty",
            "Empty",
            "You try to turn the door handle, unfortunately, it seems to be locked...",
            "Empty",
            "Empty",
            "Banana",
            "Empty",
            "Empty")

        val foyer = Room("Foyer",
            "Standing in the foyer, it doesn't look like there's much here, just a coat rack in the corner and a staircase to the second floor.",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty")

        val garage = Room("Garage",
            "The garage is filled with all sorts of junk, lockers and bikes line one wall, A staircase leading down takes up another, and various fishing and golf equipment clutter up the last side, only the place reserved for a car is empty.",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Net",
            "Locker Key",
            "Torch")

        val basement = Room("Basement",
            "Once you fire a single shot with your trusty slingshot, the fiendish bat lets out a squeak and disappears through a crack in the ceiling, leaving the basement unguarded. There's a metal detector in the corner, but it's flat, and for some reason, it seems to run on AA batteries...",
            "This place is creepy...",
            "Basement Key",
            "Torch",
            "Slingshot",
            "You try the trapdoor, but it's locked...",
            "Entering the basement, you realize it's far too dark to see anything, and you can't seem to find a lightswitch...",
            "The moment you turn on the torch a small blur flies towards you and attacks! After you've retreated, you peer back in and spy a small bat hanging in the corner. What is it with you and small, furry creatures today?",
            "Empty",
            "AA Batteries",
            "Metal Detector")

        val hall1 = Room("Downstairs Hallway",
            "A hallway, with a really nice carpet, like, really nice, like you could probably walk back and forth on this carpet forever, it's that good!",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty")

        val livroom1 = Room("Downstairs Living Room",
            "A cozy living room, a fire crackles away in a fireplace on one side of the room, while the other is lined with bookshelves, maybe you've got time to take a peek through one....",
            "After a short bit of reading you feel ever so slightly more intelligent.",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Common Sense",
            "Empty",
            "Empty")

        val dinroom = Room("Dining Room",
            "A long wooden table with a small fruit bowl stands in the center of the room, along the walls hang numerous paintings, one shows a view of a city at night, one shows a lady with a violin, and one shows a singular banana, curious.",
            "Upon placing the banana in the fruit bowl, one of the paintings swings forwards, revealing a small safe set into the wall, unfortunately, you don't know the code for the safe, but there is a small key hanging beside it!",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Mousetrap",
            "Banana",
            "Locker Key")

        val kitchen = Room("Kitchen",
            "The kitchen, now freed from it's tiny tyrants, lies before you. Kitchens can't talk, but if they could, you feel like this one would be thanking you right now.",
            "Empty",
            "Mousetrap",
            "Cheese",
            "Empty",
            "As you're about to step into the kitchen, you spot it, a mouse, it stares up at you, it's tiny eyes no doubt filled with burning hatred, you hastily close the door, you can't go in the kitchen as long as that mouse is there.",
            "You've cleverly placed the mousetrap, but it doesn't have any bait...",
            "Empty",
            "Greenhouse Key",
            "Empty",
            "Empty")

        val pantry = Room("Pantry",
            "There's so much food in here, if only you knew what to pick...",
            "A quite frankly unreasonably large pantry, like seriously, how would you even manage to eat this much food?",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Common Sense",
            "Cheese")

        val bath1 = Room("Downstairs Bathroom",
            "A bathroom, not much to it, but there's a scented card on the door handle that smells really good.",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty")

        val laundry = Room("Laundry",
            "The laundry is... Surprisingly cozy! A washing machine drones away... There are fluffy towels hung on the wall... And there's one of those de-humidifier thingies! Or maybe you're just weird.",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Vacuum",
            "Reminder Note",
            "Laundry Basket")

        val hall2 = Room("Upstairs Hallway",
            "A hallway with a mediocre carpet.",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty")

        val livroom2 = Room("Upstairs Living Room",
            "After getting rid of all that dust, the living room is quite nice! It's even got a balcony!",
            "Empty",
            "Vacuum",
            "Empty",
            "Empty",
            "Upon entering the room you are met with a cloud of dust and immediately begin couching, every surface in the room is covered in piles of dust, you figure staying in this room any longer may be hazardous to your health, so you make a hasty exit.",
            "Empty",
            "Empty",
            "Guestroom Key",
            "Handwritten Note",
            "Binoculars")

        val bal1 = Room("Balcony",
            "Looking out from the balcony, a beautiful landscape stretches before you, a see of trees with leaves in shades of red and orange and purple. Up above, the clouds stretch across the sky towards the horizon, where a warm glow signals the end of the day, better get back to searching, before the owner gets back.",
            "Looking through the binoculars, you spot something bright blue in the big oak tree in the garden!",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Binoculars",
            "Knowledge")

        val bath2 = Room("Upstairs Bathroom",
            "A bathroom, seeing yourself in the mirror you decide to play a short game of Rock Paper Scissors, unfortunately, you lose.",
            "After you finished being distracted by your reflection, you noticed a note to the side of the mirror!",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Reminder Note",
            "Empty",
            "Empty")

        val gamesroom = Room("Games Room",
            "While packing everything into the box, you find a Gameboy! It has some batteries in it, those could be usefull...",
            "After shoving everything that was on the floor into the box, you can finally walk around, what a relief! As for the gamesroom itself, it has a pool table, a dart board, some beanbags and one of those fancy jukeboxes, among other things.",
            "Cardboard Box",
            "Empty",
            "Empty",
            "You enter the gamesroom, but are immediately blocked by a knee-deep sea of bard games, card games, ad other various items, rendering further progress into the room impossible. Where do they even put all this stuff when it's tidy?",
            "Empty",
            "Empty",
            "AA Batteries",
            "Empty",
            "Empty")

        val guestroom = Room("Guest Room",
            "Upon entering the guestroom, you realize that it's frighteningly messy, in fact, it doesn't seem like it's been cleaned since someone last stayed here!",
            "Empty",
            "Guestroom Key",
            "Empty",
            "Empty",
            "The door to the guestroom seems to be locked...",
            "Empty",
            "Empty",
            "Handwritten Note",
            "Empty",
            "Empty")

        val bedroom = Room("Bedroom",
            "Fortunately, with this laundry, you have official business in here, the room itself is surprisingly tidy, there are posters on the wall and a couple toys on the floor, but that's about it.",
            "Empty",
            "Laundry Basket",
            "Empty",
            "Empty",
            "There's a piece of paper sellotaped to the door, it reads: ABSOLUTELY NO ENTRY (Unless on official business) in messy handwriting. Huh, guess you can't go in.",
            "Empty",
            "Empty",
            "Slingshot",
            "Empty",
            "Empty")

        val bal2 = Room("Bedroom Balcony",
            "The Garden stretches below you, the lawn still un-mowed, and behind you a door to the master bedroom!",
            "Empty",
            "Rusty Key",
            "Empty",
            "Empty",
            "From the bedroom window, you might just be able to reach the balcony that connects to the master bedroom, but the window is locked...",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty")

        val mBedroom = Room("Master Bedroom",
            "It only takes a moment for you to spot it, on a desk to the side of the room, a sticky note labelled: Shed Code.",
            "Empty",
            "HHAHAHAH YOU CANT GET IN",
            "Empty",
            "Empty",
            "The door is locked, hopefully the combination for the padlock isn't in there... Hah... Wouldn't that be crazy.",
            "Empty",
            "Empty",
            "Sticky Note",
            "Empty",
            "Empty")

        val shed = Room("Shed",
            "At long, long last, you've found your way into the shed, and there, parked in the corner, A gleaming piece of machinery stands ready to be put into action. You've finally found the lawnmower.",
            "Empty",
            "Sticky Note",
            "Empty",
            "Empty",
            "Upon approaching the shed, you notice a padlock with a combination lock, but you weren't told about any code... Maybe you can find some clues in the house.",
            "Empty",
            "Empty",
            "Lawnmower",
            "Empty",
            "Empty")

        val CS = Item("Common Sense", "Might come in handy one of these days.")
        val vac = Item("Vacuum", "A vacuum in a mansion... Why is that familiar?")
        val net = Item("Net","A net affixed to a long pole.")
        val MT = Item("Mousetrap","Don't worry, it's a non-lethal one.")
        val cheese = Item("Cheese","Don't eat it! What if you need it later?")
        val BK = Item("Basement Key","A key to the basement, pretty self explanatory.")
        val GRK = Item("Guestroom Key","A key to the guestroom, pretty self explanatory.")
        val GHK = Item("Greenhouse Key","A key to the greenhouse, pretty self explanatory.")
        val LK = Item("Locker Key","A key to a locker, pretty self explanatory.")
        val RK = Item("Rusty Key","A key to the rusty, pretty self explanatory... Wait...")
        val banana = Item("Banana","It's a banana.")
        val note = Item("Handwritten Note","It reads as following: Thanks for having me! I left a gift in the upstairs livingroom!")
        val binoculars = Item("Binoculars","These things are so cool.")
        val knowledge = Item("Knowledge","You should check out that tree...")
        val torch = Item("Torch","You get a sudden urge to shine it in your eyes.")
        val RN = Item("Reminder Note", "It reads: Don't forget! Take clothes out of dryer.")
        val LB = Item("Laundry Basket","While you're here, you could put these back were they belong.")
        val ball = Item("Ball","You feel like you could throw this very high...")
        val CB = Item("Cardboard Box", "Though small, it looks like it could fit a lot.")
        val AAB = Item("AA Batteries","You never seem to have enough of these...")
        val sling = Item("Slingshot","You found the Fairy Slingshot! Wait, wrong game.")
        val MD = Item("Metal Detector","It's beeping angrily at you.")
        val sNote = Item("Sticky Note","It reads: Shed Code: 0001")
        val LM = Item("Lawnmower","What are you doing reading this? Finish the game already!")

        items.add(CS)
        items.add(vac)
        items.add(net)
        items.add(MT)
        items.add(cheese)
        items.add(BK)
        items.add(GRK)
        items.add(GHK)
        items.add(LK)
        items.add(RK)
        items.add(banana)
        items.add(note)
        items.add(binoculars)
        items.add(knowledge)
        items.add(torch)
        items.add(RN)
        items.add(LB)
        items.add(ball)
        items.add(CB)
        items.add(AAB)
        items.add(sling)
        items.add(MD)
        items.add(sNote)
        items.add(LM)


        rooms.add(driveway)
        rooms.add(garden)
        rooms.add(tree)
        rooms.add(pool)
        rooms.add(greenhouse)
        rooms.add(foyer)
        rooms.add(garage)
        rooms.add(basement)
        rooms.add(hall1)
        rooms.add(livroom1)
        rooms.add(dinroom)
        rooms.add(kitchen)
        rooms.add(pantry)
        rooms.add(bath1)
        rooms.add(laundry)
        rooms.add(hall2)
        rooms.add(livroom2)
        rooms.add(bal1)
        rooms.add(bath2)
        rooms.add(guestroom)
        rooms.add(gamesroom)
        rooms.add(bedroom)
        rooms.add(bal2)
        rooms.add(mBedroom)
        rooms.add(shed)


        driveway.addDoor(garden)
        driveway.addDoor(garage)
        driveway.addDoor(foyer)

        garden.addDoor(driveway)
        garden.addDoor(shed)
        garden.addDoor(tree)
        garden.addDoor(pool)

        tree.addDoor(garden)

        pool.addDoor(garden)
        pool.addDoor(livroom1)
        pool.addDoor(greenhouse)

        greenhouse.addDoor(pool)
        greenhouse.addDoor(kitchen)

        foyer.addDoor(driveway)
        foyer.addDoor(garage)
        foyer.addDoor(hall1)
        foyer.addDoor(hall2)

        garage.addDoor(driveway)
        garage.addDoor(foyer)
        garage.addDoor(basement)

        basement.addDoor(garage)

        hall1.addDoor(foyer)
        hall1.addDoor(hall2)
        hall1.addDoor(livroom1)
        hall1.addDoor(dinroom)
        hall1.addDoor(kitchen)
        hall1.addDoor(pantry)
        hall1.addDoor(bath1)
        hall1.addDoor(laundry)

        livroom1.addDoor(pool)
        livroom1.addDoor(dinroom)
        livroom1.addDoor(hall1)

        dinroom.addDoor(livroom1)
        dinroom.addDoor(hall1)
        dinroom.addDoor(kitchen)

        kitchen.addDoor(dinroom)
        kitchen.addDoor(greenhouse)
        kitchen.addDoor(hall1)
        kitchen.addDoor(pantry)

        pantry.addDoor(kitchen)
        pantry.addDoor(hall1)

        bath1.addDoor(hall1)

        laundry.addDoor(hall1)

        hall2.addDoor(hall1)
        hall2.addDoor(foyer)
        hall2.addDoor(livroom2)
        hall2.addDoor(bath2)
        hall2.addDoor(gamesroom)
        hall2.addDoor(guestroom)
        hall2.addDoor(bedroom)
        hall2.addDoor(mBedroom)

        livroom2.addDoor(hall2)
        livroom2.addDoor(bal1)

        bal1.addDoor(livroom2)

        bath2.addDoor(hall2)

        gamesroom.addDoor(hall2)

        guestroom.addDoor(hall2)

        bedroom.addDoor(hall2)
        bedroom.addDoor(bal2)

        bal2.addDoor(mBedroom)

        mBedroom.addDoor(hall2)
        mBedroom.addDoor(bal2)

        shed.addDoor(garden)


        intro()
    }

    // Runs the intro, before starting the game.
    private fun intro() {
        val tutorialDialogue = mutableListOf("You've just arrived for the first day of your new job! You were hired at a house a couple minutes out of town to maintain the garden, upon arriving, the owner greets you.", "Why hello there! I'm Mr Coleman, but please, call me Riley, and you must be my new hire!", "To be honest, I almost forgot you were coming! I'm the type of person who has to write down all of his codes and passwords, else I'll forget them!", "Anyway, since it's your first day, we'll keep it simple, the lawn's a little overgrown, so it would be good if you were to mow it.", "Perfect! Well, no one is going to be home unfortunately, we're all going out to dinner, but I'm confident you can handle yourself! Good Luck!")
        wordWindow = WordWindow(false, tutorialDialogue)
        wordWindow.show()
        //Checks to see if the player has finished reading.
        while (true) {
            print("If this line isn't here my code breaks.")
            if (wordWindow.complete == true) {
                break
            }
        }
        makeWindows()
    }

    // Creates the two permanent windows.
    private fun makeWindows() {
        mainWindow = MainWindow(this)
        roomWindow = RoomWindow(this, rooms)
        mainWindow.show()
        roomWindow.show()
        wordWindow.hide()
    }

    // Gives the player an item for looking in certain rooms.
    // target = Room that the item is in.
    fun search(target: Room) {
        // Checks if there is actually an item in the room.
        if (target.freeItem == "Empty") {
            roomWindow.failSearch()
        }
        // Gives the player the item, updates necessary windows, and removes the item from the room.
        else {
            inventory.add(target.freeItem)
            mainWindow.updateUI()
            roomWindow.foundItem(target.freeItem)
            // Usually a room can only have one itemLock, so to use the lawnmower on the garden and beat the game,
            // I have to replace the last itemLock
            if (target.freeItem == "Lawnmower") {
                rooms[1].itemLock = "Lawnmower"
            }
            target.freeItem = "Empty"
            // If the room has no items left, an alternate description is sometimes applied.
            if (target.item == "Empty") {
                if (target.roomDescClear != "Empty") {
                    target.roomDesc = target.roomDescClear
                }
            }
            roomWindow.updateUI()
            keyWindow.hide()
        }
    }

    // Gives the player an item, in this case when the player used a different item to unlock.
    // target = Room that the item is in.
    fun getItem(target: Room) {
        inventory.add(target.item)
        mainWindow.updateUI()
        roomWindow.foundItem(target.item)
        target.itemLock = "Empty"
        target.item = "Empty"
        // If the room has no items left, an alternate description is sometimes applied.
        if (target.freeItem == "Empty") {
            if (target.roomDescClear != "Empty") {
                target.roomDesc = target.roomDescClear
            }
        }
        roomWindow.updateUI()
        keyWindow.hide()
    }

    // Updates inspectInventory, then passes it to the inspect window.
    fun openInspectMenu() {
        for (item in items) {
            for (invItem in inventory) {
                if (invItem == item.name) {
                    inspectInventory.add(item)
                }
            }
        }
        inspectWindow = InspectWindow(inspectInventory)
        inspectWindow.show()
        inspectUsed = true
    }

    // Opens the movement window.
    // theCoolerRooms = Rooms you can currently travel to.
    fun openTravelMenu(theCoolerRooms: MutableList<Room>) {
        travelWindow = TravelWindow(this, theCoolerRooms)
        travelWindow.show()
    }

    // Opens the key window.
    fun openKeyWindow(room: Room) {
        keyWindow = KeyWindow(this, inventory, room, false)
        keyWindow.show()
    }

    //opens the tutorial window.
    fun openTutorial() {
        val tutorialDialogue = mutableListOf("Hi there! It seems you might be a little confused on what to do, don't worry, it's not too hard to get the hang of!", "This game is essentially an escape room, you will collect items and use them to unlock rooms and progress the game.", "First, let's talk about movement!", "To move around, simply press the move button on the room window!", "Great, now you should have a new window called movement, there are four buttons here.", "The first two are linked, next and back both cycle though the list of places that you can go to.", "The next is Cancel, this ends the operation.", "And finally, we have Go, which takes you to the place that is selected.", "This button layout is used widely throughout the game, so make sure to familiarize yourself with it.", "If you try to move into certain rooms, you may find they are locked, this will bring up another window that asks you to select and use an item to gain access to the room.", "To get items, there are two methods.", "You can search using the button labelled as such on the room window.", "Or you can use an item by pressing the use item button on the room window", "This brings up another item select screen.", "Keep in mind, any items you use will be removed from your inventory!", "To figure out what item goes where, press the inspect button on the player info page. (You may only do this if there is an item in your inventory.)", "This brings up a menu with short descriptions for all of the items, some of which contain clues!", "Reading room descriptions, and even trying to enter locked rooms can also give you hints.", "That should about do it for this tutorial, have fun!")
        tutorialWindow = TutorialWindow(tutorialDialogue)
        tutorialWindow.show()
        tutorialused = true
    }

    // moves the player to a new room.
    fun move(destination: String) {
        // Checks which room you're going to
        for (room in rooms) {
            if (room.name == destination) {
                // Checks if the room is locked
                if (room.lock == "Empty") {
                    location = destination
                    mainWindow.updateUI()
                    roomWindow.updateUI()
                    travelWindow.hide()
                    keyWindow.hide()
                    if (location == "Bedroom Balcony") {
                        rooms[23].lock = "Empty"
                    }
                }
                //opens key window.
                else {
                    keyWindow = KeyWindow(this, inventory, room, true)
                    keyWindow.show()
                }
            }
        }
    }

    // Hides windows no longer needed and re-enables the buttons on roomWindow.
    // keyWExists = whether or not keyWindow has to be hidden.
    fun endMove(keyWExists: Boolean) {
        if (keyWExists == true) {
            keyWindow.hide()
        }
        travelWindow.hide()
        roomWindow.fixButton()
    }

    // Some doors require multiple items to open, this function handles the change from one lock to another.
    // target = Room that requires it's locks changed
    fun nextKey(target: Room) {
        // Checks if there is a second lock, then if there is a third lock, if there are neither, the player can move.
        if (target.lock2 == "Empty") {
            if (target.lock3 == "Empty") {
                move(target.name)
            }
            // If the second lock is empty but there is a third lock
            // (occurs when there was previously a second lock that has now been satisfied.)
            // this will apply the third lock to the door.
            else {
                target.lock = target.lock3
                target.lock3 = "Empty"
                target.lockdes = target.lockdes3
                keyWindow.updateUI()
            }
        }
        // If there is a second lock, this will apply it to the door.
        else {
            target.lock = target.lock2
            target.lock2 = "Empty"
            target.lockdes = target.lockdes2
            keyWindow.updateUI()
        }
    }

    // Removes an item from the players inventory once it has been used.
    // target = item in list that needs removing.
    fun removeKey(target: Int) {
        inventory.removeAt(target)
        mainWindow.updateUI()
    }

    // Hides all windows and shows the end dialogue.
    fun winConMet() {
        mainWindow.hide()
        roomWindow.hide()
        travelWindow.hide()
        keyWindow.hide()
        if (inspectUsed == true) {
            inspectWindow.hide()
        }
        if (tutorialused == true) {
            tutorialWindow.hide()
        }
        val winDialogue = mutableListOf("Well, after all that time, you finally managed to mow the lawn, meaning you can finally go home.", "As you get back in your car and begin the drive home you are filled with an immense sense of satisfaction.", "Congratulations, you finished the game!")
        wordWindow = WordWindow(true, winDialogue)
        wordWindow.show()
    }
}


/**
 * Player window, shows current location and inventory.
 * Lets the player inspect items, and has access to the tutorial.
 */
class MainWindow(val player: Player) {
    val frame = JFrame("Player Info")
    var inventory = " "
    private val panel = JPanel().apply { layout = null }

    // Every window has a titleLabel, it's what appears at the top of the window.
    private val titleLabel = JLabel("Current location: ${player.location}")
    // Shows the items in the player's inventory.
    private val invLabel = JLabel("Inventory:")
    // Button that opens the inspect menu.
    private val inspectButton = JButton("Inspect")
    // Button that opens the tutorial.
    private val tutorialButton = JButton("Help")

    init {
        setupLayout()
        setupStyles()
        setupWindow()
        setupActions()
        updateUI()
    }

    // Most of these functions are in every window.
    // Arranges all of the text and buttons on the window.
    private fun setupLayout() {
        panel.preferredSize = java.awt.Dimension(420, 150)

        titleLabel.setBounds(10, 0, 450, 30)
        invLabel.setBounds(30, 40, 340, 70)
        inspectButton.setBounds(20, 100, 80, 30)
        tutorialButton.setBounds(120, 100, 80, 30)

        panel.add(titleLabel)
        panel.add(invLabel)
        panel.add(inspectButton)
        panel.add(tutorialButton)
    }

    // Just adjusts text.
    private fun setupStyles() {
        titleLabel.font = Font(Font.SANS_SERIF, Font.BOLD, 20)
        invLabel.font = Font(Font.SANS_SERIF, Font.PLAIN, 11)
    }

    // Gives each window certain properties.
    private fun setupWindow() {
        frame.isResizable = false
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.contentPane = panel
        frame.pack()
        frame.setLocationRelativeTo(null)
    }

    // Connects each button to a function.
    private fun setupActions() {
        inspectButton.addActionListener { player.openInspectMenu() }
        tutorialButton.addActionListener { openTutorial() }
    }

    // Updates the visual appearance of the window, depending on certain values.
    fun updateUI() {
        inventory = player.inventory.joinToString()
        titleLabel.text = "Current location: ${player.location}"
        invLabel.text = "<html><wrap>Inventory: ${inventory}<wrap><html>"
        // Stops the player inspecting if there is nothing in their inventory.
        if (player.inventory.isNotEmpty()) {
            inspectButton.isEnabled = true
        }
        else {
            inspectButton.isEnabled = false
        }
    }

    // Shows the window.
    fun show() {
        frame.isVisible = true
    }

    // Hides the window.
    fun hide() {
        frame.isVisible = false
    }

    // Calls the function that opens the tutorial.
    fun openTutorial() {
        player.openTutorial()
    }
}

/**
 * Room window, gives a description of the room.
 * Lets you open the travel menu, search for items, and use them.
 *  @property loc, The index of the room the player is in.
 *  @property rooms, The list of rooms.
 */
class RoomWindow(val player: Player, val rooms: MutableList<Room>) {
    var loc = 0
    val frame = JFrame("Room")
    private val panel = JPanel().apply { layout = null }

    // Button that opens the move menu
    private val travelButton = JButton("Move")
    // Button that gives the player an item. (If applicable.)
    private val searchButton = JButton("Search")
    // Opens the menu to use an item to obtain a different item.
    private val itemButton = JButton("Use Item")
    // Describes the room
    private val descLabel = JLabel("<html><wrap>${rooms[loc].roomDesc}</wrap></html>")
    // Tells you the last item you found/if there was nothing in the room.
    private val resLabel = JLabel(" ")

    init {
        setupLayout()
        setupStyles()
        setupWindow()
        setupActions()
        updateUI()
    }

    private fun setupLayout() {
        panel.preferredSize = java.awt.Dimension(400, 220)

        descLabel.setBounds(30, 10, 340, 70)
        searchButton.setBounds(20, 100, 100, 30)
        itemButton.setBounds(150, 100, 100, 30)
        travelButton.setBounds(20, 150, 100, 30)
        resLabel.setBounds(20, 180, 340, 30)

        panel.add(descLabel)
        panel.add(searchButton)
        panel.add(itemButton)
        panel.add(travelButton)
        panel.add(resLabel)
    }

    private fun setupStyles() {
        descLabel.font = Font(Font.SANS_SERIF, Font.PLAIN, 11)
    }

    private fun setupWindow() {
        frame.isResizable = false
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.contentPane = panel
        frame.pack()
        frame.setLocationRelativeTo(null)
    }

    private fun setupActions() {
        searchButton.addActionListener { player.search(rooms[loc]) }
        itemButton.addActionListener { player.openKeyWindow(rooms[loc]) }
        travelButton.addActionListener { openTravelMenu() }
    }

    // Disables buttons and calls the function that opens the travel menu.
    private fun openTravelMenu() {
        travelButton.isEnabled = false
        searchButton.isEnabled = false
        itemButton.isEnabled = false
        player.openTravelMenu(rooms[loc].adjacent)
    }

    // Tells the player if there's nothing in the room.
    fun failSearch() {
        resLabel.text = "You couldn't find anything useful..."
    }

    // Tells the player what item they found.
    fun foundItem(item: String) {
        resLabel.text = "You found $item!"
    }

    // Updates the visual appearance of the window.
    fun updateUI() {
        // Checks what room the player is in.
        for (room in rooms) {
            if (room.name == player.location) {
                loc = rooms.indexOf(room)
            }
        }
        descLabel.text = "<html><wrap>${rooms[loc].roomDesc}</wrap></html>"
        travelButton.isEnabled = true
        searchButton.isEnabled = true
        // Checks whether the player has any items to use.
        if (player.inventory.isNotEmpty()) {
            itemButton.isEnabled = true
        }
        else {
            itemButton.isEnabled = false
        }
    }

    // Enables all of the buttons
    fun fixButton() {
        travelButton.isEnabled = true
        searchButton.isEnabled = true
        itemButton.isEnabled = true
    }

    fun show() {
        frame.isVisible = true
    }

    fun hide() {
        frame.isVisible = false
    }
}

/**
 * Movement window, Lets the player choose a room and go there.
 *  @property curDes, The index of the room the player wants to travel to.
 *  @property rooms, The list of rooms that the player can travel to.
 */
class TravelWindow(val player: Player, val rooms: MutableList<Room>) {
    var curDes = 0

    val frame = JFrame("Movement")
    private val panel = JPanel().apply { layout = null }
    // Shows the current destination.
    private val desLabel = JLabel("Current destination: ${rooms[curDes].name}")
    // Lets the player select a room.
    private val cycleUpButton = JButton("Next")
    private val cycleDownButton = JButton("Back")
    // Cancels movement.
    private val cancelButton = JButton("Cancel")
    // Moves the player.
    private val goButton = JButton("Go")

    init {
        setupLayout()
        setupStyles()
        setupWindow()
        setupActions()
    }

    private fun setupLayout() {
        panel.preferredSize = java.awt.Dimension(400, 220)

        desLabel.setBounds(30, 10, 340, 70)
        cycleUpButton.setBounds(20, 100, 110, 30)
        cycleDownButton.setBounds(145, 100, 110, 30)
        cancelButton.setBounds(270, 100, 110, 30)
        goButton.setBounds(20, 150, 150, 30)

        panel.add(desLabel)
        panel.add(cycleUpButton)
        panel.add(cycleDownButton)
        panel.add(goButton)
        panel.add(cancelButton)
    }

    private fun setupStyles() {
        desLabel.font = Font(Font.SANS_SERIF, Font.PLAIN, 11)
    }

    private fun setupWindow() {
        frame.isResizable = false
        frame.contentPane = panel
        frame.defaultCloseOperation = JFrame.DO_NOTHING_ON_CLOSE
        frame.pack()
        frame.setLocationRelativeTo(null)
    }

    private fun setupActions() {
        cycleUpButton.addActionListener { cycleUp() }
        cycleDownButton.addActionListener { cycleDown() }
        cancelButton.addActionListener { cancel() }
        goButton.addActionListener { startMoveProccess() }
    }

    // selects the next room in the list.
    private fun cycleUp() {
        // Loops the list if the player reaches the end.
        if (curDes == rooms.size - 1) {
            curDes = 0
        }
        else {
            curDes += 1
        }
        updateUI()
    }

    // selects the previous room in the list.
    private fun cycleDown() {
        // Loops the list if the player reaches teh end.
        if (curDes == 0) {
            curDes = rooms.size - 1
        }
        else {
            curDes -= 1
        }
        updateUI()
    }

    // Calls the function that moves the player and dissables buttons.
    private fun startMoveProccess() {
        player.move(rooms[curDes].name)
        cycleUpButton.isEnabled = false
        cycleDownButton.isEnabled = false
        goButton.isEnabled = false
        cancelButton.isEnabled = false
    }

    // Cancels movement
    private fun cancel() {
        player.endMove(false)
    }

    // updates the visual appearance of the window.
    private fun updateUI() {
        desLabel.text = "Current destination: ${rooms[curDes].name}"
    }

    fun show() {
        frame.isVisible = true
    }

    fun hide() {
        frame.isVisible = false
        cycleUpButton.isEnabled = true
        cycleDownButton.isEnabled = true
        goButton.isEnabled = true
    }
}

/**
 * Item selection screen, Allows the player unlock doors and grab items.
 *  @property curItem, The index of the item the player has selected.
 *  @property keys, List of items in the players inventory.
 *  @property room, Room the player is trying to enter.
 *  @property door, whether the player is trying to enter a room or unlock an item.
 */
class KeyWindow(val player: Player, val keys: MutableList<String>, val room: Room, val door: Boolean) {
    var curItem = 0

    val frame = JFrame("Items")
    private val panel = JPanel().apply { layout = null }

    private val infoLabel = JLabel("")
    private val keyLabel = JLabel("What item will you use?")
    private val itemLabel = JLabel("")
    private val cycleUpButton = JButton("Next")
    private val cycleDownButton = JButton("Back")
    private val cancelButton = JButton("Cancel")
    private val useButton = JButton("Use")
    private val failLabel = JLabel(" ")

    init {
        setupLayout()
        setupStyles()
        setupWindow()
        setupActions()
        updateUI()
    }

    private fun setupLayout() {
        panel.preferredSize = java.awt.Dimension(600, 250)

        infoLabel.setBounds(30, 0, 540, 70)
        keyLabel.setBounds(30, 40, 340, 70)
        itemLabel.setBounds(30, 60, 340, 70)
        cycleUpButton.setBounds(20, 110, 110, 30)
        cycleDownButton.setBounds(145, 110, 110, 30)
        cancelButton.setBounds(270, 110, 110, 30)
        useButton.setBounds(20, 160, 150, 30)
        failLabel.setBounds(30, 180, 340, 70)

        panel.add(infoLabel)
        panel.add(keyLabel)
        panel.add(itemLabel)
        panel.add(cycleUpButton)
        panel.add(cycleDownButton)
        panel.add(cancelButton)
        panel.add(useButton)
        panel.add(failLabel)
    }

    private fun setupStyles() {
        itemLabel.font = Font(Font.SANS_SERIF, Font.PLAIN, 11)
    }

    private fun setupWindow() {
        frame.isResizable = false
        frame.contentPane = panel
        frame.defaultCloseOperation = JFrame.DO_NOTHING_ON_CLOSE
        frame.pack()
        frame.setLocationRelativeTo(null)
    }

    private fun setupActions() {
        cycleUpButton.addActionListener { cycleUp() }
        cycleDownButton.addActionListener { cycleDown() }
        useButton.addActionListener { checkKey() }
        cancelButton.addActionListener { cancel() }
    }

    private fun cycleUp() {
        if (curItem == keys.size - 1) {
            curItem = 0
        }
        else {
            curItem += 1
        }
        updateUI()
    }

    private fun cycleDown() {
        if (curItem == 0) {
            curItem = keys.size - 1
        }
        else {
            curItem -= 1
        }
        updateUI()
    }

    // Only new function in this window, checks if the key works.
    private fun checkKey() {
        // Checks whether you're unlocking a door or obtaining an item.
        if (door == true) {
            if (room.lock == keys[curItem]) {
                player.removeKey(curItem)
                room.lock = "Empty"
                player.nextKey(room)
            } else {
                tellFail()
            }
        }
        else {
            if (room.itemLock == keys[curItem]) {
                // Checks if the player has won the game.
                if (keys[curItem] ==  "Lawnmower") {
                    player.winConMet()
                }
                else {
                    player.removeKey(curItem)
                    player.getItem(room)
                }
            } else {
                tellFail()
            }
        }
    }

    private fun cancel() {
        player.endMove(true)
    }

    private fun tellFail() {
        failLabel.text = "That item didn't seem to work..."
    }

    // Updates the visual appearance of the window, and enables/disables buttons.
    fun updateUI() {
        if (door == true) {
            infoLabel.text = "<html><wrap>${room.lockdes}</wrap></html>"
        }
        // Checks if there are items in the player's inventory,
        // if there are none buttons are disabled, and vice versa.
        if (keys.size > 0) {
            // Checks if curItem is out of bounds, because otherwise the window breaks.
            if (curItem > keys.size - 1) {
                curItem = 0
            }
            itemLabel.text = "Item: ${keys[curItem]}"
            cycleUpButton.isEnabled = true
            cycleDownButton.isEnabled = true
            useButton.isEnabled = true
        }
        else {
            itemLabel.text = "Your inventory is empty."
            cycleUpButton.isEnabled = false
            cycleDownButton.isEnabled = false
            useButton.isEnabled = false
        }
    }

    fun show() {
        frame.isVisible = true
    }

    fun hide() {
        frame.isVisible = false
    }
}

/**
 * Item inspection, allows player to look at items.
 *  @property curItem, The index of the item the player has selected.
 *  @property inventory, List of items in the players inventory.
 */
class InspectWindow(val inventory: MutableList<Item>) {
    var curItem = 0

    val frame = JFrame("Inventory")
    private val panel = JPanel().apply { layout = null }

    private val infoLabel = JLabel("<html><wrap>${inventory[curItem].description}</wrap></html>")
    private val itemLabel = JLabel("Item: ${inventory[curItem].name}")
    private val cycleUpButton = JButton("Next")
    private val cycleDownButton = JButton("Back")

    init {
        setupLayout()
        setupStyles()
        setupWindow()
        setupActions()
    }

    private fun setupLayout() {
        panel.preferredSize = java.awt.Dimension(400, 110)

        infoLabel.setBounds(180, 40, 200, 70)
        itemLabel.setBounds(30, 0, 400, 70)
        cycleUpButton.setBounds(20, 60, 70, 30)
        cycleDownButton.setBounds(100, 60, 70, 30)


        panel.add(infoLabel)
        panel.add(itemLabel)
        panel.add(cycleUpButton)
        panel.add(cycleDownButton)
    }

    private fun setupStyles() {
        itemLabel.font = Font(Font.SANS_SERIF, Font.PLAIN, 22)
    }

    private fun setupWindow() {
        frame.isResizable = false
        frame.contentPane = panel
        frame.pack()
        frame.setLocationRelativeTo(null)
    }

    private fun setupActions() {
        cycleUpButton.addActionListener { cycleUp() }
        cycleDownButton.addActionListener { cycleDown() }
    }

    private fun cycleUp() {
        if (curItem == inventory.size - 1) {
            curItem = 0
        }
        else {
            curItem += 1
        }
        updateUI()
    }

    private fun cycleDown() {
        if (curItem == 0) {
            curItem = inventory.size - 1
        }
        else {
            curItem -= 1
        }
        updateUI()
    }

    private fun updateUI() {
        infoLabel.text = "<html><wrap>${inventory[curItem].description}</wrap></html>"
        itemLabel.text = "Item: ${inventory[curItem].name}"
    }

    fun show() {
        frame.isVisible = true
    }

    fun hide() {
        frame.isVisible = false
    }
}

/**
 * Window for displaying dialogue.
 *  @property curDialogue, The index of the line of dialogue the player is reading.
 *  @property complete, Whether the player has finished reading.
 *  @property won, Whether the player has won the game
 *  @property dialogue, A list of ines of dialogue that will be played.
 */
class WordWindow(val won: Boolean, val dialogue: MutableList<String>) {
    var curDialogue = 0
    var complete = false

    val frame = JFrame(" ")
    private val panel = JPanel().apply { layout = null }

    private val infoLabel = JLabel("<html><wrap>${dialogue[curDialogue]}</wrap></html>")
    private val nextButton = JButton("Continue")

    init {
        setupLayout()
        setupWindow()
        setupActions()
    }

    private fun setupLayout() {
        panel.preferredSize = java.awt.Dimension(250, 200)

        infoLabel.setBounds(30, 0, 190, 150)
        nextButton.setBounds(20, 150, 100, 30)

        panel.add(infoLabel)
        panel.add(nextButton)
    }

    private fun setupWindow() {
        frame.isResizable = false
        frame.contentPane = panel
        frame.pack()
        frame.setLocationRelativeTo(null)
        if (won == false) {
            frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        }
    }

    private fun setupActions() {
        nextButton.addActionListener { nextDialogue() }
    }

    // Advances to the next line and checks if you're finished reading.
    private fun nextDialogue() {
        // Checks if the player has reached the end of the dialogue.
        if (curDialogue == dialogue.size - 1) {
            // Checks if the player has won.
            if (won == true) {
                exitProcess(0)
            }
            else {
                complete = true
            }
        }
        else {
            curDialogue += 1
        }
        updateUI()
    }

    private fun updateUI() {
        infoLabel.text = "<html><wrap>${dialogue[curDialogue]}</wrap></html>"
        // Changes the button text if the player is on the last line.
        if (curDialogue == dialogue.size - 1) {
            nextButton.text = "Finish"
        }
    }

    fun show() {
        frame.isVisible = true
    }

    fun hide() {
        frame.isVisible = false
    }
}

/**
 * Tutorial.
 *  @property curDialogue, The index of the line of dialogue the player is reading.
 */
class TutorialWindow(val dialogue: MutableList<String>) {
    var curDialogue = 0

    val frame = JFrame("Tutorial")
    private val panel = JPanel().apply { layout = null }

    private val infoLabel = JLabel("<html><wrap>${dialogue[curDialogue]}</wrap></html>")
    private val nextButton = JButton("Continue")

    init {
        setupLayout()
        setupWindow()
        setupActions()
    }

    private fun setupLayout() {
        panel.preferredSize = java.awt.Dimension(250, 200)

        infoLabel.setBounds(30, 0, 190, 150)
        nextButton.setBounds(20, 150, 100, 30)

        panel.add(infoLabel)
        panel.add(nextButton)
    }

    private fun setupWindow() {
        frame.isResizable = false
        frame.contentPane = panel
        frame.pack()
        frame.setLocationRelativeTo(null)
    }

    private fun setupActions() {
        nextButton.addActionListener { nextDialogue() }
    }

    private fun nextDialogue() {
        if (curDialogue == dialogue.size - 1) {
            hide()
        }
        else {
            curDialogue += 1
        }
        updateUI()
    }

    private fun updateUI() {
        infoLabel.text = "<html><wrap>${dialogue[curDialogue]}</wrap></html>"
        // Changes the button text if the player is on the last line.
        if (curDialogue == dialogue.size - 1) {
            nextButton.text = "Finish"
        }
    }

    fun show() {
        frame.isVisible = true
    }

    fun hide() {
        frame.isVisible = false
    }
}