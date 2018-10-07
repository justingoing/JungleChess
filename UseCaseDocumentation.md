# Use Case Documentation

| Use case id: | R01 |
| :--- | :--- |
| Use case name: | Register |
| Overview: | The user registers an account by providing valid data  |
| Actors: | User [primary, initiator], Database |
| Pre-conditions: | User was prompted to either sign in or register, and chose to register |
| Main Flow: | 1. User enters an email, a username, and a password. <br> 2. User attempts to register. <br> 3. Database informs the user that the email, username, and password provided are all valid. <br> 4. Database records the new account. <br> 5. User is signed into the new account. |
| Post-conditions: | User can sign in using the data provided in future attempts |
| Alternate Flows: | **3a Invalid data** <br> 3a1. Database informs the user that the email, username, or password provided are invalid. <br> 3a2. User is allowed another attempt. Return to 1. |

| Use case id: | R02 |
| :--- | :--- |
| Use case name: | Sign In |
| Overview: | The user signs into their preexisting account |
| Actors: | User [primary, initiator], Database |
| Pre-conditions: | User has registered an account <br> User was prompted to either sign in or register, and chose to sign in |
| Main Flow: | 1. User enters a username and a password. <br> 2. User attempts to sign in. <br> 3. Database informs the user that the username and password combination is valid. <br> 4. Database saves the session. |
| Post-conditions: | User is immediately able to create a match |
| Alternate Flows: | **3a Invalid data** <br> 3a1. Database informs the user that the username and password combination is invalid. <br> 3a2. User is allowed another attempt. Return to 1. <br> **3b Inexistent username** <br> 3b1. Database informs the user that the username provided is not associated to an account. <br> 3b2. User is allowed another attempt. Return to 1. |

| Use case id: | R03 |
| :--- | :--- |
| Use case name: | Create Match |
| Overview: | A new match is made |
| Actors: | User [primary, initiator], Database |
| Pre-conditions: | User is signed in |
| Main Flow: | 1. User chooses to start a new match. <br> 2. Database creates a new instance of match. <br> 3. Database adds the user to the match. <br> Extension Point: Send Invite |
| Post-conditions: | Database creates a new match with one player |
| Alternate Flows: | - |

| Use case id: | R04 |
| :--- | :--- |
| Use case name: | Match Invite |
| Extends: R03|
| Overview: | The user invites a user to play their match |
| Primary actors: | User [primary, initiator] |
| Pre-conditions: | The User has created a match |
| Main Flow: | 1. User selects a different User to send an invitation to, and attempts to send the invitation. <br> 2. The System informs the other User of the invitation. <br> 3. The other User accepts the invite. <br> 4. The other User is added to the match. <br> The System cancels any other sent invitations. |
| Post-conditions: | Another user is added to the match |
| Alternate Flows: | **3a User cancels invite** <br> 3a1. The User cancels the invite. <br> 3a2. The System informs the other User the invite is cancelled. Return to 1. <br> **3b Other User rejects invite** 3b1. The other User rejects the invite. 3b2. The System informs the User of the invite rejection. Return to 1. <br> **3c User sends another invite** 3c1. Return to 1.|

| Use case id: | R05 |
| :--- | :--- |
| Use case name: | Start Match |
| Overview: | The match starts |
| Actors: | User [primary, initiator], Database |
| Pre-conditions: | The minimum number of users to start the match have been added to the match |
| Main Flow: | 1. Database updates the match to reflect that it has started. <br> Include(Save Match State) <br> Include(Notify User When It Is Their Turn) |
| Post-conditions: | User whose turn it is is able to make the first move |
| Alternate Flow: | - |

| Use case id: | R06 |
| :--- | :--- |
| Use case name: | Make Move |
| Overview: | Primary User makes a move |
| Primary actors: | User [primary, initiator] |
| Pre-conditions: | The game has been created and started |
| Flow: | **Main Flow** <br> 1. Primary user attempts to move a piece <br> 2. The move is verified as valid <br> 3. The piece is moved <br> 4. The secondary user is notified that it is their turn <br> **Alternate Flows** <br> 2a. The user is notified that the move is invalid <br> 4b. The primary user's move is verified as a winning move and both players are notified the primary user has won the game. 1a. A user quits. <br> Include(Quit Match)  |
| Post-conditions: | The primary user executed a valid move and the game state has been updated |

| Use case id: | R07 |
| :--- | :--- |
| Use case name: | Enforce Rules |
| Overview: | The user or opponent make a valid move and the state of the game changes. |
| Primary actors: | User [Primary, Initiator] |
| Pre-conditions: | The user is playing a game. |
| Flow: | **Main Flow**: 1. User or opponent makes a move. <br> 2. The state is changed according to the game rules. |
| Post-conditions: | It is the other players turn. |

| Use case id: | R08 |
| :--- | :--- |
| Use case name: | Notify User When It Is Their Turn |
| Overview: | The user receives a notification that it is their turn. |
| Primary actors: | User [Primary, Initiator] |
| Pre-conditions: | The user is playing a game and the opponent makes a move on their turn. |
| Flow: | **Main Flow**: 1. The user receives a notification that it is their turn. |
| Post-conditions: | It is the user's turn. |

| Use case id: | R09 |
| :--- | :--- |
| Use case name: | Save Match State |
| Overview: | The database saves the game state indefinitely. |
| Primary actors: | User [Primary, Initiator] |
| Pre-conditions: | User is currently playing a game. |
| Flow: | **Main Flow**: 1. User or opponent makes move. <br> 2. User signs off or stops playing. <br> 3. State is saved. <br> 4. User returns. |
| Post-conditions: | The game is ready to be played where the user left off. |

| Use case id: | R10 |
| :--- | :--- |
| Use case name: | Finish Match |
| Overview: | The game data is saved in the database|
| Primary actors: | Database, User[secondary, initiator] |
| Pre-conditions: | A user executed a winning move or quit the game |
| Flow: | **Main Flow** <br> 1. The database is updated with the result of the game <br> 2. The user's accounts are updated with the results of the game |
| Post-conditions: | The database and user accounts have been updated with the correct results of the match |

| Use case id: | R11|
| :--- | :--- |
| Use case name: | Quit Match |
| Overview: | The user quits the match |
| Primary actors: | User [primary, initiator] |
| Pre-conditions: | A game is in progress |
| Flow: | **Main Flow** <br> 1. The user elects to quit that game <br> 2. The secondary user is notified that the primary user has quit |
| Post-conditions: | The game is ended |

| Use case id: | R12 |
| :--- | :--- |
| Use case name: | Record Match Results |
| Overview: | The results of the match are stored in the database |
| Actors: | Database [primary], User [initiator] |
| Pre-conditions: | The match has ended |
| Flow: | **Main Flow**: <br> 1. The start and end times and dates are recorded to the database. <br> 2. The winner and loser are recorded to the database. <br> **Alternate Flows**: <br> 2a A user quits <br> 2a1. The user that quit the match is recorded to the database as the loser and the other user is recorded as the winner. <br> 2a2. The user that quit is recorded to have withdrawn from the match. |
| Post-conditions: | The times, dates and results are stored in the database |

| Use case id: | R13 |
| :--- | :--- |
| Use case name: | View User Account |
| Overview: | The user selects a user's account to view and the selected user's data is displayed |
| Actors: | Database [primary], User [initiator] |
| Pre-conditions: | User is signed in |
| Main Flow: | 1. User searches by username and attempts to view it. <br> 2. Database retreives the selected user's account. <br> 3. The selected user's account is displayed. |
| Post-conditions: | The selected user's account is displayed |
| Alternate Flows: | **2a User not in database** <br>2a1. Database informs the user that the username is not associated to an account. |

| Use case id: | R14 |
| :--- | :--- |
| Use case name: | Unregister |
| Overview: | The user deletes their existing account from the database |
| Actors: |  User [primary, initiator], Database |
| Pre-conditions: | User is signed in <br> User is viewing their own account |
| Main Flow: | 1. User chooses to delete their account. <br> 2. Database asks user to confirm account deletion. <br> 3. The user confirms account deletion. <br> Extension Point: Cancel All Outstanding Invites <br> Extension Point: Remove Match History <br> 4. Database removes sign in data associated with the account. <br> 5. The user is signed out. |
| Post-conditions: | The user's account is deleted and the user is signed out |
| Alternate Flows: | **3a User cancels confirmation** <br> 3a1. The user cancels the account deletion. |

