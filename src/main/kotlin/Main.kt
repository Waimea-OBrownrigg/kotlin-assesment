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
    val window = MainWindow(player)    // Spawn the UI, passing in the app state

    SwingUtilities.invokeLater { window.show() }
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
    var inventory = mutableListOf<Item>()

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
            "Empty",
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

        val bal2 = Room("Balcony",
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

        bath2.addDoor(hall2)

        gamesroom.addDoor(hall2)

        guestroom.addDoor(hall2)

        bedroom.addDoor(hall2)
        bedroom.addDoor(bal2)

        bal2.addDoor(mBedroom)

        mBedroom.addDoor(hall2)
        mBedroom.addDoor(bal2)

        shed.addDoor(garden)
    }

    fun changeLoc(newloc: Room) {
        location = newloc.name
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
        panel.preferredSize = java.awt.Dimension(400, 220)

        titleLabel.setBounds(30, 30, 340, 30)
        infoLabel.setBounds(30, 90, 340, 30)

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
        infoLabel.text = "Inventory: ${player.inventory}"
    }

    fun show() {
        frame.isVisible = true
    }
}

/**
 * Room UI window, gives a description and shows the rooms you can travel to
 */
class RoomWindow(val room: Room) {
    val frame = JFrame("Placeholder name")
    private val panel = JPanel().apply { layout = null }

    private val descLabel = JLabel(room.roomDesc)

    private val infoLabel = JLabel("Inventory:")

    init {
        setupLayout()
        setupStyles()
        setupWindow()
    }

    private fun setupLayout() {
        panel.preferredSize = java.awt.Dimension(400, 220)

        descLabel.setBounds(30, 30, 340, 30)
        infoLabel.setBounds(30, 90, 340, 30)

        panel.add(descLabel)
        panel.add(infoLabel)
    }

    private fun setupStyles() {
        descLabel.font = Font(Font.SANS_SERIF, Font.PLAIN, 11)
        infoLabel.font = Font(Font.SANS_SERIF, Font.PLAIN, 11)
    }

    private fun setupWindow() {
        frame.isResizable = false                           // Can't resize
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE  // Exit upon window close
        frame.contentPane = panel                           // Define the main content
        frame.pack()
        frame.setLocationRelativeTo(null)                   // Centre on the screen
    }

    fun show() {
        frame.isVisible = true
    }
}