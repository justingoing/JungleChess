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
| Overview: | The player signs into their account |
| Primary actors: | Player |
| Secondary actors: | System |
| Properties: | - |
| Pre-conditions: | The player selects Sign In from the My Account page. |
| Main Flow: | 1. The player enters their username and password and clicks the Sign In button. <br> 2. The systems informs the player that the provided username and passwords are valid and verified in the account database. <br> 3. The session is saved in the log file. <br> 4. The player is redirected to the main screen. |
| Post-conditions: | The player successfully signs into their pre-existing account. |
| Alternate flow: | 1. The player enters an invalid username and/or password combination. <br> 2. The player is informed of the invalid login. <br> 3. The player is allowed another sign in attempt. |

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
| Overview: | The results of the match are stored in the database |
| Actors: | Database [primary], User [initiator] |
| Pre-conditions: | The match has ended. |
| Flow: | **Main Flow**: 1. The start and end times and dates are recorded to the database. <br> 2. The winner and loser are recorded to the database. <br> **Alternate Flows**: 2a. The user that quit the match is recorded to the database as the loser and the other user is recorded as the winner. <br> 3a. The user that quit is recorded to have withdrawn from the match. |
| Post-conditions: | The times, dates and results are stored in the database |

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
