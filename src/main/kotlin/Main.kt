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
) {

}


/**
 *
 */
class Player {
    var location = "Driveway"
    val inventory = mutableListOf<Item>()

    val rooms: MutableList<Room> = mutableListOf()

    lateinit var mainWindow: MainWindow
    lateinit var roomWindow: RoomWindow
    lateinit var travelWindow: TravelWindow
    lateinit var keyWindow: KeyWindow

    init {

        val driveway = Room("Driveway",
            "The mansion looms before you under a cloudy gray sky, a flattened cardboard box has found its way up onto the roof, where it lies, a blemish upon an otherwise spotless abode.",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty")

        val garden = Room("Garden",
            "Gardesc",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty")

        val pool = Room("Pool",
            "Pooldesc",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty")

        val greenhouse = Room("Greenhouse",
            "Greedesc",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty")

        val foyer = Room("Foyer",
            "Foydesc",
            "Empty",
            "CoolKey",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty")

        val garage = Room("Garage",
            "Gardesc",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty")

        val basement = Room("Basement",
            "Basdesc",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty")

        val hall1 = Room("Downstairs Hallway",
            "Halldesc",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty")

        val livroom1 = Room("Downstairs Living Room",
            "Livdesc",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty")

        val dinroom = Room("Dining Room",
            "Dindesc",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty")

        val kitchen = Room("Kitchen",
            "Kitdesc",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty")

        val pantry = Room("Pantry",
            "pandesc",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty")

        val bath1 = Room("Downstairs Bathroom",
            "Bathdesc",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty")

        val laundry = Room("Laundry",
            "Laundesc",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty")

        val hall2 = Room("Upstairs Hallway",
            "Halldesc",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty")

        val livroom2 = Room("Upstairs Living Room",
            "Livdesc",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty")

        val bal1 = Room("Balcony",
            "Looking out from the balcony, a beautiful landscape stretches before you, a see of trees with leaves in shades of red and orange and purple. Up above, the clouds stretch across the sky towards the horizon, where a warm glow signals the end of the day, better get back to searching, before the owner gets back.",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty")

        val bath2 = Room("Upstairs Bathroom",
            "Bathdesc",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty")

        val gamesroom = Room("Games Room",
            "Gamedesc",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty")

        val guestroom = Room("Guest Room",
            "Guestdesc",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty")

        val bedroom = Room("Bedroom",
            "Beddesc",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty")

        val bal2 = Room("Bedroom Balcony",
            "Baldesc",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty")

        val mBedroom = Room("Master Bedroom",
            "Beddesc",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Empty")

        val shed = Room("Shed",
            "At long, long last, you've found your way into the shed, and there, parked in the corner, A gleaming piece of machinery stands ready to be put into action. You've finally found the lawnmower.",
            "Empty",
            "Empty",
            "Empty",
            "Empty",
            "Lawnmower",
            "Empty",
            "Empty")

        val test = Item("Test", "testdesc")

        inventory.add(test)

        rooms.add(driveway)
        rooms.add(garden)
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
        garden.addDoor(pool)

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
                    keyWindow = KeyWindow(this, inventory)
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

    fun checkKey() {

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
    var curdes = 0

    val frame = JFrame("Placeholder name")
    private val panel = JPanel().apply { layout = null }

    private val desLabel = JLabel("Current destination: ${rooms[curdes].name}")
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
        if (curdes == rooms.size - 1) {
            curdes = 0
        }
        else {
            curdes += 1
        }
        updateUI()
    }

    private fun startMoveProccess() {
        player.move(rooms[curdes].name)
        cycleButton.isEnabled = false
        goButton.isEnabled = false
    }

    private fun updateUI() {
        desLabel.text = "Current destination: ${rooms[curdes].name}"
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

class KeyWindow(val player: Player, val keys: MutableList<Item>) {
    var curdes = 0

    val frame = JFrame("Placeholder name")
    private val panel = JPanel().apply { layout = null }

    private val infoLabel = JLabel("What item will you use")
    private val itemLabel = JLabel("Item: ${keys[curdes].name}")
    private val cycleButton = JButton("Next Item")
    private val goButton = JButton("Use")
    private val stopButton = JButton("Cancel")

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
        goButton.setBounds(180, 100, 150, 30)
        stopButton.setBounds(20, 150, 150, 30)

        panel.add(infoLabel)
        panel.add(itemLabel)
        panel.add(cycleButton)
        panel.add(goButton)
        panel.add(stopButton)
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
        goButton.addActionListener { consume() }
        stopButton.addActionListener { cancel() }
    }

    private fun cycle() {
        if (curdes == keys.size - 1) {
            curdes = 0
        }
        else {
            curdes += 1
        }
        updateUI()
    }

    private fun consume() {
        player.checkKey()
    }

    private fun cancel() {
        player.endMove()
    }

    private fun updateUI() {
        itemLabel.text = "Item: ${keys[curdes].name}"
    }

    fun show() {
        frame.isVisible = true
    }

    fun hide() {
        frame.isVisible = false
    }
}
