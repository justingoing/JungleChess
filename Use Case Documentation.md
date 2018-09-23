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
| Overview: | - |
| Primary actors: | - |
| Properties: | - |
| Pre-conditions: | - |
| Flow: | - |
| Post-conditions: | - |

| Use case id: | R5 |
| :--- | :--- |
| Use case name: | Invite Player |
| Overview: | - |
| Primary actors: | - |
| Properties: | - |
| Pre-conditions: | - |
| Flow: | - |
| Post-conditions: | - |

| Use case id: | R6 |
| :--- | :--- |
| Use case name: | Accept Invite |
| Overview: | - |
| Primary actors: | - |
| Properties: | - |
| Pre-conditions: | - |
| Flow: | - |
| Post-conditions: | - |

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
| Overview: | - |
| Primary actors: | - |
| Properties: | - |
| Pre-conditions: | - |
| Flow: | - |
| Post-conditions: | - |

| Use case id: | R9 |
| :--- | :--- |
| Use case name: | Play Game |
| Overview: | - |
| Primary actors: | - |
| Properties: | - |
| Pre-conditions: | - |
| Flow: | - |
| Post-conditions: | - |

| Use case id: | R10 |
| :--- | :--- |
| Use case name: | End Game |
| Overview: | - |
| Primary actors: | - |
| Properties: | - |
| Pre-conditions: | - |
| Flow: | - |
| Post-conditions: | - |

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
| Overview: | The system saves the game state for a potentially long period of time. |
| Primary actors: | User |
| Pre-conditions: | User is currently playing a game. |
| Flow: | **Main Flow**: 1. User or opponent makes move. <br> 2. User leaves game physically, but does not forfeit. <br> 3. User returns. |
| Post-conditions: | The game is ready to be played where the user left off. |

| Use case id: | R17 |
| :--- | :--- |
| Use case name: | Notify User When It Is Their Turn |
| Overview: | The opponent's turn is ended and the user receives a notification of it being their turn. |
| Primary actors: | User |
| Pre-conditions: | The user is playing a game and the opponent makes a move on their turn. |
| Flow: | **Main Flow**: 1. The user receives a notification that it is their turn. |
| Post-conditions: | It is the user's turn. |

| Use case id: | R18 |
| :--- | :--- |
| Use case name: | Enforce Rules |
| Overview: | The user or opponent makes a move and the system resolves the move. |
| Primary actors: | User |
| Pre-conditions: | The user is playing a game. |
| Flow: | **Main Flow**: 1. A player makes a move. <br> 2. The game state is resolved according to the rules. |
| Post-conditions: | It is the other players turn. |
