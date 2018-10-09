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
| Extends: | R03 |
| Overview: | The user invites a user to play their match |
| Primary actors: | User [primary, initiator] |
| Pre-conditions: | User has created a match |
| Main Flow: | 1. User selects a different user to send an invitation to, and attempts to send the invitation. <br> 2. The System informs the other User of the invitation. <br> 3. The other user accepts the invite. <br> 4. The other user is added to the match. <br> The System cancels any other sent invitations. |
| Post-conditions: | Another user is added to the match |
| Alternate Flows: | **3a User cancels invite** <br> 3a1. The user cancels the invite. <br> 3a2. The System informs the other user the invite is cancelled. Return to 1. <br> **3b Other user rejects invite** 3b1. The other user rejects the invite. 3b2. The System informs the user of the invite rejection. Return to 1. <br> **3c User sends another invite** 3c1. Return to 1.|

| Use case id: | R05 |
| :--- | :--- |
| Use case name: | Match Options |
| Extends: | R03 |
| Overview: | The users of the match play the game |
| Actors: | User [primary, initiator], Database |
| Pre-conditions: | The minimum number of users to start the match have been added to the match |
| Main Flow: | 1. Database updates the match to reflect that it has started. <br> 2. The game is setup in its initial or saved state. <br> 3. The users make moves until one user has won the game. <br> 4. Extension point(R07) <br> The users are removed from the game.|
| Post-conditions: | User whose turn it is is able to make the first move |
| Alternate Flow: | **3a An illegal move is attempted** 3a1. A user attempts to make an illegal move. <br> 3a2. The System informs the active user that move is not allowed. Return to 3. <br> **3b Active user has failed to move** 3b1. No move has been made for awhile. <br> 3b2. The System informs the active user its their turn. Return to 3. <br> **3c User chooses to save game state** 3c1. Extension point(R06). Return to 2. <br> **3d User quits** 3d1. A user chooses to quit the match. <br> 3d2. The other user informed the user quit and is declared the winner. Return to 4.|

| Use case id: | R06 |
| :--- | :--- |
| Extends: | R05 |
| Use case name: | Save Match State |
| Overview: | The database saves the game state indefinitely. |
| Primary actors: | User [Primary, Initiator] |
| Pre-conditions: | User is currently playing a game. |
| Flow: | **Main Flow**: 1. User or opponent makes move. <br> 2. User signs off or stops playing. <br> 3. State is saved. <br> 4. User returns. |
| Post-conditions: | The game is ready to be played where the user left off. |

| Use case id: | R07 |
| :--- | :--- |
| Extends: | R05 |
| Use case name: | Record Match Results |
| Overview: | The results of the match are stored in the database |
| Actors: | Database [primary], User [initiator] |
| Pre-conditions: | The match has ended |
| Flow: | **Main Flow**: <br> 1. The start and end times and dates are recorded to the database. <br> 2. The winner and loser are recorded to the database. <br> **Alternate Flows**: <br> 2a A user quits <br> 2a1. The user that quit the match is recorded to the database as the loser and the other user is recorded as the winner. <br> 2a2. The user that quit is recorded to have withdrawn from the match. |
| Post-conditions: | The times, dates and results are stored in the database |

| Use case id: | R08 |
| :--- | :--- |
| Use case name: | View User Account |
| Overview: | The user selects a user's account to view and the selected user's data is displayed |
| Actors: | Database [primary], User [initiator] |
| Pre-conditions: | User is signed in |
| Main Flow: | 1. User searches by username and attempts to view it. <br> 2. Database retreives the selected user's account. <br> 3. The selected user's account is displayed. |
| Post-conditions: | The selected user's account is displayed |
| Alternate Flows: | **2a User not in database** <br>2a1. Database informs the user that the username is not associated to an account. |

| Use case id: | R09 |
| :--- | :--- |
| Use case name: | Unregister |
| Overview: | The user deletes their existing account from the database |
| Actors: |  User [primary, initiator], Database |
| Pre-conditions: | User is signed in <br> User is viewing their own account |
| Main Flow: | 1. User chooses to delete their account. <br> 2. Database asks user to confirm account deletion. <br> 3. The user confirms account deletion. <br> Extension Point: Cancel All Outstanding Invites <br> Extension Point: Remove Match History <br> 4. Database removes sign in data associated with the account. <br> 5. The user is signed out. |
| Post-conditions: | The user's account is deleted and the user is signed out |
| Alternate Flows: | **3a User cancels confirmation** <br> 3a1. The user cancels the account deletion. |

