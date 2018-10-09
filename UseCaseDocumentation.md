# Use Case Documentation

| Use case id: | R01 |
| :--- | :--- |
| Use case name: | Register |
| Overview: | The user registers an account by providing valid data  |
| Actors: | User [primary, initiator], Database |
| Pre-conditions: | User was prompted to either sign in or register, and chose to register |
| Main Flow: | 1. User enters an email, a username, and a password. <br> 2. User attempts to register. <br> 3. System passes user info to database. <br> 4. Database creates a new user. <br> 5. Database informs system of successful user creation. <br> 6. System informs the user that the email, username, and password provided are all valid. <br> 7. User is signed into the new account. |
| Post-conditions: | User can sign in using the data provided in future attempts |
| Alternate Flows: | **4a Invalid data** <br> 4a1. Database informs the user that the email, username, or password provided are invalid. <br> 4a2. User is allowed another attempt. Return to 1. |

| Use case id: | R02 |
| :--- | :--- |
| Use case name: | Sign In |
| Overview: | The user signs into their preexisting account |
| Actors: | User [primary, initiator], Database |
| Pre-conditions: | User has registered an account. <br> User was prompted to either sign in or register, and chose to sign in |
| Main Flow: | 1. User enters a username and a password. <br> 2. User attempts to sign in. <br> 3. System consults database using the provided sign in information. <br> 4. The database informs system that the sign in is successful. <br> 5. System informs the user that the username and password combination is valid. <br> 6. User is signed in|
| Post-conditions: | User is signed into the system. |
| Alternate Flows: | **4a Invalid data** <br> 4a1. Database informs system of invalid username/password combination. <br> 4a2. System informs the user that the username and password combination is invalid. <br> 4a3. User is allowed another attempt. Return to 1. <br> **4b Nonexistent username** <br> 4b1. Database informs system of nonexistent username. <br> 4b2. System informs the user that the username provided is not associated to an account. <br> 4b3. User is allowed another attempt. Return to 1. |

| Use case id: | R03 |
| :--- | :--- |
| Use case name: | Create Match |
| Overview: | A new match is made |
| Actors: | User [primary, initiator] |
| Pre-conditions: | User is signed in |
| Main Flow: | 1. User chooses to start a new match. <br> 2. System creates match for user. <br> 3. System adds user to the match. |
| Post-conditions: | User is in a new match. |
| Alternate Flows: | - |

| Use case id: | R04 |
| :--- | :--- |
| Use case name: | Match Invite |
| Overview: | The user invites a user to play their match |
| Primary actors: | User [primary, initiator] |
| Pre-conditions: | User has created a match |
| Main Flow: | 1. Include(Create Match) 2. User selects a different user to send an invitation to, and attempts to send the invitation. <br> 3. The System informs the other User of the invitation. <br> 4. The other user accepts the invite. <br> 5. The other user is added to the match. <br> The System cancels any other sent invitations. |
| Post-conditions: | Another user is added to the match |
| Alternate Flows: | **4a User cancels invite** <br> 4a1. The user cancels the invite. <br> 4a2. The System informs the other user the invite is cancelled. Return to 1. <br> **4b Other user rejects invite** 4b1. The other user rejects the invite. 4b2. The System informs the user of the invite rejection. Return to 1. <br> **4c User sends another invite** 4c1. Return to 1.|

| Use case id: | R05 |
| :--- | :--- |
| Use case name: | Play Match |
| Overview: | The users of the match play the game |
| Actors: | User [primary, initiator] |
| Pre-conditions: | The minimum number of users to start the match have been added to the match |
| Main Flow: |  1. Include(Match Invite) <br> 2. The System informs the users the game is starting and the game is setup in its initial or saved state. <br> 3. The users make moves until one user has won the game. <br> Extension point: Record Match Results. <br> 4. The System removes the users from the game.|
| Post-conditions: | The users have finished playing a game |
| Alternate Flow: | **3a An illegal move is attempted** 3a1. A user attempts to make an illegal move. <br> 3a2. The System informs the active user that move is not allowed. Return to 3. <br> **3b Active user has failed to move** 3b1. No move has been made for awhile. <br> 3b2. The System informs the active user its their turn. Return to 3. <br> **3c User chooses to save game state** 3c1. User chooses to save the game state. <br> Extension point: Save Match State. Return to 2. <br> **3d User quits** 3d1. A user chooses to quit the match. <br> 3d2. The other user informed the user quit and is declared the winner. Return to 3.|

| Use case id: | R06 |
| :--- | :--- |
| Extends: | R05 |
| Use case name: | Save Match State |
| Overview: | The database saves the game state indefinitely. |
| Primary actors: | User [Primary, Initiator] |
| Pre-conditions: | User is currently playing a game. |
| Flow: | **Main Flow**: 1. User or opponent makes move. <br> 2. User signs off or stops playing. <br> 3. System saves the state of the game. <br> 4. User returns. 5. System resumes game.|
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

