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
| Overview: | The user adds a new game info into the database |
| Actors: | User [primary, initiator]<br> Database [secondary]|
| Pre-conditions: | The user is signed in |
| Flow: | **Main Flow:** 1. The user chooses to start a new match<br> 2. A new game is added to the Database<br> 3. The user is added to the new game lobby|
| Post-conditions: | A new game is added to the Database and the user is added to it |

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
| Overview: | All users are added to a new game and game is marked in progress |
| Actors: | User [primary, initiator] <br> Database [secondary]|
| Pre-conditions: | The minimum number of users to start the game are in game lobby |
| Flow: | **Main Flow:** 1. The users choose to start the game<br> 2. A new game instance is made<br> 3. All users in lobby are added to the game<br> **Alternate Flow:** 1. The users quit the lobby<br> 2. The game lobby is removed from the Database<br> 3. The start game use case is cancelled|
| Post-conditions: | A new game instance is started and all users are added to the game |

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
| Overview: | The user deletes their existing account from the system |
| Actors: |  User [primary, initiator] |
| Pre-conditions: | The user is signed in |
| Flow: | **Main Flow:** 1. The user chooses to delete their account<br> 2. The system asks user to confirm delete account<br> 3. The user confirms account deletion<br> The system deletes the user's account<br> 4. The user is signed out<br> **Alternate Flow:** 3. The user cancels account deletion<br> 4.The unregister use case is cancelled|
| Post-conditions: | The user's account is deleted and they are signed out |

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
