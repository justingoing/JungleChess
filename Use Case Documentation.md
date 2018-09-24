# Use Case Documentation

| Use case id: | R1 |
| :--- | :--- |
| Use case name: | Register |
| Overview: | The user registers an account with their email, username, and password |
| Actors: | User [primary, initiator] |
| Pre-conditions: | User is prompted to either sign in or register. The user chooses register. |
| Flow: | **Main Flow:** 1. User enters their email, username, and password. <br> 2. User is informed that the email and username are valid. <br> 3. User is automatically logged in to the newly created account. <br> **Alternate flow:** 2a. User is informed that their email address or username is invalid. <br> 3a. User is allowed another attempt. |
| Post-conditions: | User is automatically logged in to the newly created account |

| Use case id: | R2 |
| :--- | :--- |
| Use case name: | Sign In |
| Overview: | The user signs into their prexisting account with username and password |
| Actors: | User [primary, initiator], Database |
| Pre-conditions: | User is prompted to either sign in or register. The user chooses sign in. |
| Flow: | **Main Flow** 1. User enters a username and a password. <br> 2. User attempts to sign in. <br> 3. User is informed of the successful login attempt and the session is saved in the database. <br> 4. The user is redirected to the lobby. <br> **Alternate Flows** 3a. User is informed of the invalid login attempt. <br> 4a. User is allowed another attempt. <br> 3b. User is informed that the given username is not associated to an account. <br> 4b. User is allowed another attempt. |
| Post-conditions: | User signs into their preexisting account |

| Use case id: | R3 |
| :--- | :--- |
| Use case name: | Create Match |
| Overview: | - |
| Primary actors: | - |
| Properties: | - |
| Pre-conditions: | - |
| Flow: | - |
| Post-conditions: | - |

| Use case id: | R4 |
| :--- | :--- |
| Use case name: | Join Match |
| Overview: | User joins the match |
| Primary actors: | User [primary, initiator] |
| Pre-conditions: | A user is invited to join a match |
| Flow: | 1. User accepts invitation to join a match <br> 2. User is loaded into match |
| Post-conditions: | User is now in a match |

| Use case id: | R5 |
| :--- | :--- |
| Use case name: | Invite Player |
| Overview: | Initiating User invites Primary User to match |
| Primary actors: | User [primary, initiator] |
| Pre-conditions: | Initiating User created a game |
| Flow: | **Main Flow** 1. Initiating User invites Primary User to match <br> 2. Primary User joins Match <br> **Alternate Flow** 2a. Primary User does not join match 2a. Initiating user can invite a friend |
| Post-conditions: | Primary User and Initiating User are in a match |

| Use case id: | R6 |
| :--- | :--- |
| Use case name: | Accept Invite |
| Overview: | Primary User receives Invitation for a match |
| Primary actors: | User [primary, initiator] |
| Pre-conditions: | Initiating user has created a game |
| Flow: | **Main Flow** 1. Primary user receives invite from Initiating User <br> 2. Primary user accepts invite <br> **Alternate flow** 2a. Primary User rejects invitation |
| Post-conditions: | Primary User is now in game with Initiating User |

| Use case id: | R7 |
| :--- | :--- |
| Use case name: | Reject Invite |
| Overview: | - |
| Primary actors: | - |
| Properties: | - |
| Pre-conditions: | - |
| Flow: | - |
| Post-conditions: | - |

| Use case id: | R8 |
| :--- | :--- |
| Use case name: | Start Game |
| Overview: | A game is started |
| Primary actors: | User [primary], User [secondary] |
| Properties: | - |
| Pre-conditions: | A game has been created, Secondary user accepted invite from primary user |
| Flow: | 1. Either user starts the game |
| Post-conditions: | A game is in progress |

| Use case id: | R9 |
| :--- | :--- |
| Use case name: | Play Match |
| Overview: | Primary User makes a move |
| Primary actors: | User [primary, initiator] |
| Properties: | - |
| Pre-conditions: | The game has been created and started |
| Flow: | 1. Primary user attempts to move a piece <br> 2. The move is verified as valid <br> 3. The piece is moved <br> 4. The Secondary User is notified that it is their turn <br> **Alternate Flow** 2a. The User is notified that the move is invalid <br> 4a. The Primary User's move is verified as a winning move and both players are notified the Primary user has won the game |
| Post-conditions: | The primary user executed a valid move and the game state has been updated |

| Use case id: | R10 |
| :--- | :--- |
| Use case name: | End Game |
| Overview: | The game data is saved in the database|
| Primary actors: | Database |
| Properties: | - |
| Pre-conditions: | A user executed a winning move or quit the game |
| Flow: | 1. The database is updated with the result of the game 2. The user profiles are updated with the results of the game |
| Post-conditions: | The database and user profiles have been updated with the correct results of the match |

| Use case id: | R11 |
| :--- | :--- |
| Use case name: | Quit Match |
| Overview: | - |
| Primary actors: | - |
| Properties: | - |
| Pre-conditions: | - |
| Flow: | - |
| Post-conditions: | - |

| Use case id: | R12 |
| :--- | :--- |
| Use case name: | Unregister |
| Overview: | - |
| Primary actors: | - |
| Properties: | - |
| Pre-conditions: | - |
| Flow: | - |
| Post-conditions: | - |

| Use case id: | R13 |
| :--- | :--- |
| Use case name: | Record Match History |
| Overview: | - |
| Primary actors: | - |
| Properties: | - |
| Pre-conditions: | - |
| Flow: | - |
| Post-conditions: | - |

| Use case id: | R14 |
| :--- | :--- |
| Use case name: | Store User Profiles |
| Overview: | - |
| Primary actors: | - |
| Properties: | - |
| Pre-conditions: | - |
| Flow: | - |
| Post-conditions: | - |

| Use case id: | R15 |
| :--- | :--- |
| Use case name: | Create Game Instance |
| Overview: | - |
| Primary actors: | - |
| Properties: | - |
| Pre-conditions: | - |
| Flow: | - |
| Post-conditions: | - |

| Use case id: | R16 |
| :--- | :--- |
| Use case name: | Save Game State |
| Overview: | - |
| Primary actors: | - |
| Properties: | - |
| Pre-conditions: | - |
| Flow: | - |
| Post-conditions: | - |

| Use case id: | R17 |
| :--- | :--- |
| Use case name: | Notify User When It Is Their Turn |
| Overview: | - |
| Primary actors: | - |
| Properties: | - |
| Pre-conditions: | - |
| Flow: | - |
| Post-conditions: | - |

| Use case id: | R18 |
| :--- | :--- |
| Use case name: | Enforce Rules |
| Overview: | - |
| Primary actors: | - |
| Properties: | - |
| Pre-conditions: | - |
| Flow: | - |
| Post-conditions: | - |
