# Plan for Testing the Program

The test plan lays out the actions and data I will use to test the functionality of my program.

Terminology:

- **VALID** data values are those that the program expects
- **BOUNDARY** data values are at the limits of the valid range
- **INVALID** data values are those that the program should reject

---

## Testing movement (Valid)

I need to make sure that the player can move seamlessly from room to room.

### Test Data To Use

Enter and exit every room.

### Expected Test Result

Program should do as I say without issue.

---

## Testing if keys work (valid)

Players should be able to unlock doors and collect items by using the correct keys.

### Test Data To Use

Play through the game, make sure that every lock accepts the corresponding key.

### Expected Test Result

Doors should unlock and let you through, items should be obtainable.

---

## Testing the wrong key (invalid)

When using the wrong key, the program should tell you and not let you in.

### Test Data To Use

Use an incorrect item on a door.

### Expected Test Result

The game should notify you that the key does not work.

---

## Testing the last key in your inventory (Boundary)

When unlocking multiple locks on a single door, each item you use is deleted, if you select and use the last item on your list, it will be deleted, which may cause problems.

### Test Data To Use

Go to a door with multiple locks and use the last item in your inventory to unlock one of them.

### Expected Test Result

The game may go out of bounds as the item it has selected no longer exists.

---

## scrolling down/up from the first/last item in the list (Boundary)

When you're selecting an item in a list, you have the option to view the next/previous item in the list, using these controls at the end of the list to go out of bounds could be problematic.

### Test Data To Use

Select the previous item on the first item in the list and the next item on the last item in the list.

### Expected Test Result

The game should loop around to the other side of the list.

---

## Reaching the end of a dialogue list (Boundary)

Once you read the final line of dialogue in the list and press next, instead of looking for another line of dialogue it should close the window.

### Test Data To Use

Run through the intro.

### Expected Test Result

The game should start once the last continue button is pressed.

---

## Winning the game ()

Once you collect the lawnmower, the lawn should allow you to use it as an item which wins the game.

### Test Data To Use

Grab the lawnmower from the shed and use it on the lawn

### Expected Test Result

Should open the ending dialogue.

---