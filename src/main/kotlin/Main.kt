import com.formdev.flatlaf.themes.FlatMacDarkLaf
import java.awt.Color
import java.awt.Font
import javax.swing.*

/**
 * Application entry point
 */
fun main() {
    FlatMacDarkLaf.setup()          // Initialise the LAF

    val player = Player() // Get an app state object
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
 *  @property freeItem, An item you get just for entering the room and searching
 *  @property itemLock, An item required to get the item in this room
 *  @property item, The item you receive for fulfilling the criteria of itemLock
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
 *
 */
class Item(
    val name: String,
    val description: String,
    val reUsable: Boolean
) {

}


/**
 *
 */
class Player {
    var location = "Driveway"
    val inventory = mutableListOf<Item>()
    val items = mutableListOf<Item>()

    val rooms: MutableList<Room> = mutableListOf()

    lateinit var mainWindow: MainWindow
    lateinit var roomWindow: RoomWindow
    lateinit var travelWindow: TravelWindow
    lateinit var keyWindow: KeyWindow

    init {

        val driveway = Room("Driveway",
            "The mansion looms before you under a cloudy gray sky, a flattened cardboard box has found its way up onto the roof, where it lies, a blemish upon the otherwise magnificent architecture",
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
            "There's no way around it, the garage is a mess, all sorts of items line every wall, surely you can find something in here that will help you...",
            "On closer inspection, there's a trapdoor in one corner, how interesting.",
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
            "Once you fire a single shot with your trusty slingshot, the fiendish bat lets out a squeak and disappears through a crack in the ceiling, leaving the basement unguarded, a dark room that seems to have been forgotten long ago, cobwebs decorate the corners of the room, and there's barely anything stored here...",
            "Empty",
            "Basement Key",
            "Torch",
            "Slingshot",
            "You try the trapdoor, but it's locked...",
            "Entering the basement, you realize it's far too dark to see anything, and you can't seem to find a lightswitch...",
            "The moment you turn on the torch a small blur flies towards you and attacks! After you've retreated, you peer back in and spy a small bat hanging in the corner. What is it with you and small, furry creatures today?",
            "Metal Detector(Empty)",
            "Empty",
            "Empty")

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
            "Upon placing the banana in the fruit bowl, one of the paintings swing forwards, revealing a small safe set into the wall, unfortunately, you don't know the code for the safe, but there is a small key hanging beside it!",
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
            "As you're about to step into the kitchen, you spot it, a mouse, it stares up at you, it's tiny eyes no doubt filled with burning hatred, you hastily close the door, there's no knowing what that thing may do to you if you're alone with it.",
            "You've cleverly placed the mousetrap, but it doesn't have any bait...",
            "Empty",
            "Greenhouse Key",
            "Empty",
            "Empty")

        val pantry = Room("Pantry",
            "A quite frankly unreasonably large pantry, like seriously, how would you even manage to eat this much food?",
            "Empty",
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
            "he laundry is... Surprisingly cozy! A washing machine drones away... There are fluffy towels hung on the wall... And there's one of those de-humidifier thingies! Or maybe you're just weird.",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Vacuum",
            "Locker Key",
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

        val gamesroom = Room("Games Room",
            "While packing everything into the box, you find a Gameboy! It doesn't have a game in it though...",
            "After shoving everything that was on the floor into the box, you can finally walk around, what a relief! As for the gamesroom itself, it has a pool table, a dart board, some beanbags and one of those fancy jukeboxes, among other things.",
            "Cardboard Box",
            "Empty",
            "Empty",
            "You enter the gamesroom, but are immediately blocked by a knee-deep sea of bard games, card games, ad other various items, rendering further progress into the room impossible.",
            "Empty",
            "Empty",
            "AA Bateries",
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

        val CS = Item("Common Sense", "Might come in handy one of these days.", true)
        val vac = Item("Vacuum", "A vacuum in a mansion... Why is that familiar?", false)
        val net = Item("Net","A net affixed to a long pole.", false)
        val MT = Item("Mousetrap","Don't worry, it's a non-lethal one.", false)
        val cheese = Item("Cheese","Don't eat it! What if you need it later?", false)
        val BK = Item("Basement Key","A key to the basement, pretty self explanatory.", false)
        val GRK = Item("Guestroom Key","A key to the guestroom, pretty self explanatory.", false)
        val GHK = Item("Greenhouse Key","A key to the greenhouse, pretty self explanatory.", false)
        val LK = Item("Locker Key","A key to a locker, pretty self explanatory.", true)
        val RK = Item("Rusty Key","A key to the rusty, pretty self explanatory... Wait...", false)
        val banana = Item("Banana","It's a banana.", false)
        val note = Item("Handwritten Note","It reads as following: Thanks for having me! I left a gift in the upstairs livingroom!", false)
        val binoculars = Item("Binoculars","These things are so cool.", false)
        val knowledge = Item("Knowledge","You should check out that tree...", false)
        val torch = Item("Torch","You get a sudden urge to shine it in your eyes.", false)
        val LB = Item("Laundry Basket","While you're here, you could put these back were they belong.", false)
        val ball = Item("Ball","You feel like you could throw this very high...", false)
        val CB = Item("Cardboard Box","Though small, it looks like it could fit a lot.", false)
        val AAB = Item("AA Batteries","You never seem to have enough of these...", false)
        val sling = Item("Slingshot","You found the Fairy Slingshot! Wait, wrong game.", false)
        val MDE = Item("Metal Detector(Empty)","For some reason, it runs on AA batteries.", false)
        val MD = Item("Metal Detector","Well, it's on and beeping angrily at you.", false)
        val sNote = Item("Sticky Note","It reads: Shed Code: 0001", false)
        val LM = Item("Lawnmower","What are you doing reading this? Finish the game already!", false)

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
        items.add(LB)
        items.add(ball)
        items.add(CB)
        items.add(AAB)
        items.add(sling)
        items.add(MDE)
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


        makeWindows()
        showWindows()
    }

    private fun makeWindows() {
        mainWindow = MainWindow(this)
        roomWindow = RoomWindow(this, rooms)
    }

    fun showWindows() {
        mainWindow.show()
        roomWindow.show()
    }

    fun openTravelMenu(theCoolerRooms: MutableList<Room>) {
        travelWindow = TravelWindow(this, theCoolerRooms)
        travelWindow.show()
    }

    fun move(destination: String) {
        for (room in rooms) {
            if (room.name == destination) {
                if (room.lock == "Empty") {
                    location = destination
                    mainWindow.updateUI()
                    roomWindow.updateUI()
                    travelWindow.hide()
                }
                else {
                    keyWindow = KeyWindow(this, inventory, room)
                    keyWindow.show()
                }
            }
        }
    }

    fun endMove() {
        keyWindow.hide()
        travelWindow.hide()
        roomWindow.fixButton()
    }

    fun nextKey(target: Room) {
        if (target.lock2 == "Empty") {
            if (target.lock3 == "Empty") {
                move(target.name)
            }
            else {
                target.lock = target.lock3
                target.lock3 = "Empty"
            }
        }
        else {
            target.lock = target.lock2
            target.lock2 = "Empty"
        }
    }
}


/**
 * Player's UI window, shows current location and inventory
 */
class MainWindow(val player: Player) {
    val frame = JFrame("Placeholder name")
    private val panel = JPanel().apply { layout = null }

    private val titleLabel = JLabel("Current location: ${player.location}")

    private val infoLabel = JLabel("Inventory:")

    init {
        setupLayout()
        setupStyles()
        setupWindow()
        updateUI()
    }

    private fun setupLayout() {
        panel.preferredSize = java.awt.Dimension(420, 150)

        titleLabel.setBounds(10, 0, 450, 30)
        infoLabel.setBounds(30, 40, 340, 30)

        panel.add(titleLabel)
        panel.add(infoLabel)
    }

    private fun setupStyles() {
        titleLabel.font = Font(Font.SANS_SERIF, Font.BOLD, 20)
        infoLabel.font = Font(Font.SANS_SERIF, Font.PLAIN, 11)
    }

    private fun setupWindow() {
        frame.isResizable = false                           // Can't resize
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE  // Exit upon window close
        frame.contentPane = panel                           // Define the main content

        frame.pack()
        frame.setLocationRelativeTo(null)                   // Centre on the screen
    }

    fun updateUI() {
        titleLabel.text = "Current location: ${player.location}"
        infoLabel.text = "Inventory: ${player.inventory}"
    }

    fun show() {
        frame.isVisible = true
    }
}

/**
 * Room UI window, gives a description and shows the rooms you can travel to
 */
class RoomWindow(val player: Player, val rooms: MutableList<Room>) {
    var loc = 0
    val frame = JFrame("Placeholder name")
    private val panel = JPanel().apply { layout = null }

    private val travelButton = JButton("Move")
    private val descLabel = JLabel("<html><wrap>${rooms[loc].roomDesc}</wrap></html>")

    init {
        setupLayout()
        setupStyles()
        setupWindow()
        setupActions()
    }

    private fun setupLayout() {
        panel.preferredSize = java.awt.Dimension(400, 220)

        descLabel.setBounds(30, 10, 340, 70)
        travelButton.setBounds(20, 100, 100, 30)

        panel.add(descLabel)
        panel.add(travelButton)
    }

    private fun setupStyles() {
        descLabel.font = Font(Font.SANS_SERIF, Font.PLAIN, 11)
    }

    private fun setupWindow() {
        frame.isResizable = false                           // Can't resize
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE  // Exit upon window close
        frame.contentPane = panel                           // Define the main content
        frame.pack()
        frame.setLocationRelativeTo(null)                   // Centre on the screen
    }

    private fun setupActions() {
        travelButton.addActionListener { openTravelMenu() }
    }

    private fun openTravelMenu() {
        travelButton.isEnabled = false
        player.openTravelMenu(rooms[loc].adjacent)
    }

    fun updateUI() {
        for (room in rooms) {
            if (room.name == player.location) {
                loc = rooms.indexOf(room)
            }
        }
        descLabel.text = "<html><wrap>${rooms[loc].roomDesc}</wrap></html>"
        travelButton.isEnabled = true
    }

    fun fixButton() {
        travelButton.isEnabled = true
    }

    fun show() {
        frame.isVisible = true
    }
}

class TravelWindow(val player: Player, val rooms: MutableList<Room>) {
    var curDes = 0

    val frame = JFrame("Placeholder name")
    private val panel = JPanel().apply { layout = null }

    private val desLabel = JLabel("Current destination: ${rooms[curDes].name}")
    private val cycleButton = JButton("Next Location")
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
        cycleButton.setBounds(20, 100, 150, 30)
        goButton.setBounds(20, 150, 150, 30)

        panel.add(desLabel)
        panel.add(cycleButton)
        panel.add(goButton)
    }

    private fun setupStyles() {
        desLabel.font = Font(Font.SANS_SERIF, Font.PLAIN, 11)
    }

    private fun setupWindow() {
        frame.isResizable = false                           // Can't resize
        frame.contentPane = panel                           // Define the main content
        frame.pack()
        frame.setLocationRelativeTo(null)                   // Centre on the screen
    }

    private fun setupActions() {
        cycleButton.addActionListener { cycle() }
        goButton.addActionListener { startMoveProccess() }
    }

    private fun cycle() {
        if (curDes == rooms.size - 1) {
            curDes = 0
        }
        else {
            curDes += 1
        }
        updateUI()
    }

    private fun startMoveProccess() {
        player.move(rooms[curDes].name)
        cycleButton.isEnabled = false
        goButton.isEnabled = false
    }

    private fun updateUI() {
        desLabel.text = "Current destination: ${rooms[curDes].name}"
    }

    fun show() {
        frame.isVisible = true
    }

    fun hide() {
        frame.isVisible = false
        cycleButton.isEnabled = true
        goButton.isEnabled = true
    }
}

class KeyWindow(val player: Player, val keys: MutableList<Item>, val room: Room) {
    var curItem = 0

    val frame = JFrame("Placeholder name")
    private val panel = JPanel().apply { layout = null }

    private val infoLabel = JLabel("What item will you use?")
    private val itemLabel = JLabel("Item: ${keys[curItem].name}")
    private val cycleButton = JButton("Next Item")
    private val useButton = JButton("Use")
    private val cancelButton = JButton("Cancel")
    private val failLabel = JLabel(" ")

    init {
        setupLayout()
        setupStyles()
        setupWindow()
        setupActions()
    }

    private fun setupLayout() {
        panel.preferredSize = java.awt.Dimension(400, 220)

        infoLabel.setBounds(30, 10, 340, 70)
        itemLabel.setBounds(30, 50, 340, 70)
        cycleButton.setBounds(20, 100, 150, 30)
        useButton.setBounds(180, 100, 150, 30)
        cancelButton.setBounds(20, 150, 150, 30)
        failLabel.setBounds(30, 170, 340, 70)

        panel.add(infoLabel)
        panel.add(itemLabel)
        panel.add(cycleButton)
        panel.add(useButton)
        panel.add(cancelButton)
    }

    private fun setupStyles() {
        itemLabel.font = Font(Font.SANS_SERIF, Font.PLAIN, 11)
    }

    private fun setupWindow() {
        frame.isResizable = false                           // Can't resize
        frame.contentPane = panel                           // Define the main content
        frame.pack()
        frame.setLocationRelativeTo(null)                   // Centre on the screen
    }

    private fun setupActions() {
        cycleButton.addActionListener { cycle() }
        useButton.addActionListener { checkKey() }
        cancelButton.addActionListener { cancel() }
    }

    private fun cycle() {
        if (curItem == keys.size - 1) {
            curItem = 0
        }
        else {
            curItem += 1
        }
        updateUI()
    }

    private fun checkKey() {
        if (room.lock == keys[curItem].name) {
            player.nextKey(room)
        }
        else {
            tellFail()
        }
    }

    private fun cancel() {
        player.endMove()
    }

    private fun tellFail() {
        failLabel.text = "That item didn't seem to work..."
    }

    private fun updateUI() {
        itemLabel.text = "Item: ${keys[curItem].name}"
    }

    fun show() {
        frame.isVisible = true
    }

    fun hide() {
        frame.isVisible = false
    }
}
