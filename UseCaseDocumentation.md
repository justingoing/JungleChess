# Use Case Documentation

| Use case id: | R01 |
| :--- | :--- |
| Use case name: | Register |
| Overview: | The user registers an account with their email, username, and password |
| Actors: | User [primary, initiator], Database |
| Pre-conditions: | User was prompted to either sign in or register. The user chose register. |
| Main Flow: | 1. User enters an email, a username, and a password. <br> 2. User attempts to register. <br> 3. Database informs the user that the email, username and password provided are all valid. <br> 4. Database records the new account. <br> 5. User is automatically signed into the new account. |
| Post-conditions: | User is signed into the new account |
| Alternate Flows: | **3a Invalid data** <br> 3a1. Database informs the user that the email, username or password provided are invalid. <br> 3a2. User is allowed another attempt. Return to 1. |

| Use case id: | R02 |
| :--- | :--- |
| Use case name: | Sign In |
| Overview: | The user signs into their preexisting account with username and password |
| Actors: | User [primary, initiator], Database |
| Pre-conditions: | User has registered an account. <br> User was prompted to either sign in or register. The user chose sign in. |
| Main Flow: | 1. User enters a username and a password. <br> 2. User attempts to sign in. <br> 3. Database informs the user that the username and password combination is valid. <br> 4. Database saves the session. |
| Post-conditions: | User signs into their account |
| Alternate Flows: | **3a Invalid data** <br> 3a1. Database informs the user that the username and password combination is invalid. <br> 3a2. User is allowed another attempt. Return to 1. <br> **3b Inexistent username** <br> 3b1. Database informs the user that the username provided is not associated to an account. <br> 3b2. User is allowed another attempt. Return to 1. |

| Use case id: | R03 |
| :--- | :--- |
| Use case name: | Create Match |
| Overview: | The user creates a new match instance |
| Actors: | User [primary, initiator], Database |
| Pre-conditions: | User is signed in |
| Main Flow: | 1. User chooses to start a new match. <br> 2. Database creates a new instance of match. <br> 3. Database adds the user to the match. <br> 4. Include(Send Invite). <br> 5. User can send many invites. Return to 4. |
| Post-conditions: | Database creates a new match with one player |
| Alternate Flows: | **4a No invites** <br> 4a1. User does not send any invites and chooses to play the computer. <br> 4a2. Include(Start Match). |

| Use case id: | R04 |
| :--- | :--- |
| Use case name: | Send Invite |
| Overview: | The user invites another user to play a match |
| Primary actors: | User [primary, initiator] |
| Pre-conditions: | The invitation sender created a match |
| Main Flow | 1. Inviter sends invitee an invitation to join their match. <br> 2. Database records the pending invite sent from the inviter. <br> 3. Invitee is notified of the pending invitation. |
| Post-conditions: | User is notified of the invitation |
| Alternate Flows: | - |

| Use case id: | R05 |
| :--- | :--- |
| Use case name: | Accept Invite |
| Overview: | A user accepts an invitation to play a match |
| Primary actors: | User [primary, initiator] |
| Pre-conditions: | User has invited the invitee |
| Flow: | **Main Flow** <br> 1. Primary user receives invite from Initiating User <br> 2. Primary user accepts invite <br> **Alternate Flows** <br> 2a. Primary User rejects invitation |
| Post-conditions: | Primary User is now in game with Initiating User |

| Use case id: | R06 |
| :--- | :--- |
| Use case name: | Reject Invite |
| Overview: | The user rejects an invitation to play a game with another user |
| Primary actors: | User [primary, initiator] |
| Pre-conditions: | The user is sent an invitation to play a game by another user. |
| Flow: | Main flow: 1. The user chooses to reject the invitation and does not join the game. |
| Post-conditions: | The user is not added to the game. |


| Use case id: | R07 |
| :--- | :--- |
| Use case name: | Join Match |
| Overview: | Invited user joins the match |
| Primary actors: | User [primary, initiator] |
| Pre-conditions: | User receives an invitation to play a match |
| Main Flow: | <br> 1. User accepts invitation to join a match. <br> 2. User is loaded into the match |
| Post-conditions: | User is now in a match |
| Alternate Flows: | - |

| Use case id: | R08 |
| :--- | :--- |
| Use case name: | Start Match |
| Overview: | All users are added to a new game and game is marked in progress |
| Actors: | User [primary, initiator], Database |
| Pre-conditions: | The minimum number of users to start the game are in game lobby |
| Flow: | **Main Flow:** 1. The users choose to start the game<br> 2. A new game instance is made<br> 3. All users in lobby are added to the game<br> **Alternate Flow:** 1a. The users quit the lobby<br> 2a. The game lobby is removed from the Database<br> 3a. The start game use case is cancelled|
| Post-conditions: | A new game instance is started and all users are added to the game |

| Use case id: | R09 |
| :--- | :--- |
| Use case name: | Make Move |
| Overview: | Primary User makes a move |
| Primary actors: | User [primary, initiator] |
| Pre-conditions: | The game has been created and started |
| Flow: | **Main Flow** <br> 1. Primary user attempts to move a piece <br> 2. The move is verified as valid <br> 3. The piece is moved <br> 4. The secondary user is notified that it is their turn <br> **Alternate Flows** <br> 2a. The user is notified that the move is invalid <br> 4b. The primary user's move is verified as a winning move and both players are notified the primary user has won the game |
| Post-conditions: | The primary user executed a valid move and the game state has been updated |

| Use case id: | R10 |
| :--- | :--- |
| Use case name: | Finish Match |
| Overview: | The game data is saved in the database|
| Primary actors: | Database, User[secondary, initiator] |
| Pre-conditions: | A user executed a winning move or quit the game |
| Flow: | **Main Flow** <br> 1. The database is updated with the result of the game <br> 2. The user profiles are updated with the results of the game |
| Post-conditions: | The database and user profiles have been updated with the correct results of the match |

| Use case id: | R11 |
| :--- | :--- |
| Use case name: | Quit Match |
| Overview: | The user quits the match |
| Primary actors: | User [primary, initiator] |
| Pre-conditions: | A game is in progress |
| Flow: | **Main Flow** <br> 1. The user elects to quit that game <br> 2. The secondary user is notified that the primary user has quit |
| Post-conditions: | The game is ended |

| Use case id: | R12 |
| :--- | :--- |
| Use case name: | Unregister |
| Overview: | The user deletes their existing account from the system |
| Actors: |  User [primary, initiator] |
| Pre-conditions: | The user is signed in |
| Flow: | **Main Flow:** 1. The user chooses to delete their account<br> 2. The system asks user to confirm delete account<br> 3. The user confirms account deletion<br> The system deletes the user's account<br> 4. The user is signed out<br> **Alternate Flow:** 3a. The user cancels account deletion<br> 4a.The unregister use case is cancelled|
| Post-conditions: | The user's account is deleted and they are signed out |

| Use case id: | R13 |
| :--- | :--- |
| Use case name: | Record Match History |
| Overview: | The results of the match are stored in the database |
| Actors: | Database [primary], User [initiator] |
| Pre-conditions: | The match has ended |
| Flow: | **Main Flow**: <br> 1. The start and end times and dates are recorded to the database. <br> 2. The winner and loser are recorded to the database. <br> **Alternate Flows**: <br> 2a A user quits <br> 2a1. The user that quit the match is recorded to the database as the loser and the other user is recorded as the winner. <br> 2a2. The user that quit is recorded to have withdrawn from the match. |
| Post-conditions: | The times, dates and results are stored in the database |

| Use case id: | R14 |
| :--- | :--- |
| Use case name: | View User Profile |
| Overview: | The user selects a profile to view and the selected user's data is displayed |
| Actors: | Database [primary], User [initiator] |
| Pre-conditions: | User is signed in |
| Flow: | **Main Flow**: 1. The user selects a user's profile they want to view. <br> 2. The selected user's data is retrieved from the database. <br> 3. Retrieved data is displayed to the user. |
| Post-conditions: | The selected user's match history is displayed |

| Use case id: | R15 |
| :--- | :--- |
| Use case name: | Save Match State |
| Overview: | The database saves the game state indefinitely. |
| Primary actors: | User [Primary, Initiator] |
| Pre-conditions: | User is currently playing a game. |
| Flow: | **Main Flow**: 1. User or opponent makes move. <br> 2. User signs off or stops playing. <br> 3. State is saved. <br> 4. User returns. |
| Post-conditions: | The game is ready to be played where the user left off. |

| Use case id: | R16 |
| :--- | :--- |
| Use case name: | Notify User When It Is Their Turn |
| Overview: | The opponent's turn ends and the user receives a notification that it is their turn. |
| Primary actors: | User [Primary, Initiator] |
| Pre-conditions: | The user is playing a game and the opponent makes a move on their turn. |
| Flow: | **Main Flow**: 1. The user receives a notification that it is their turn. |
| Post-conditions: | It is the user's turn. |

| Use case id: | R17 |
| :--- | :--- |
| Use case name: | Enforce Rules |
| Overview: | The user or opponent make a valid move and the state of the game changes. |
| Primary actors: | User [Primary, Initiator] |
| Pre-conditions: | The user is playing a game. |
| Flow: | **Main Flow**: 1. User or opponent makes a move. <br> 2. The state is changed according to the game rules. |
| Post-conditions: | It is the other players turn. |
