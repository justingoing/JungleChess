# Use Case Documentation

| Use case id: | R01 |
| :--- | :--- |
| Use case name: | Register |
| Overview: | The user registers an account by providing valid data  |
| Actors: | User [primary, initiator], Database |
| Pre-conditions: | User was prompted to either sign in or register, and chose to register |
| Main Flow: | 1. User enters an email, a username, and a password. <br> 2. User attempts to register. <br> 3. System passes user data to database. <br> 4. Database creates a new user. <br> 5. Database informs system of successful user creation. <br> 6. System informs the user that the email, username, and password provided are all valid. <br> 7. User is signed into the new account. |
| Post-conditions: | User can sign in using the data provided in future attempts |
| Alternate Flows: | **4a Invalid data** <br> 4a1. Database informs the system that the email, username, and/or password provided are invalid. <br> 4a2. System informs the user that the data provided is invalid. <br> 4a3. User is allowed another attempt. Return to 1. |

| Use case id: | R02 |
| :--- | :--- |
| Use case name: | Sign In |
| Overview: | The user signs into their preexisting account |
| Actors: | User [primary, initiator], Database |
| Pre-conditions: | User has registered an account <br> User was prompted to either sign in or register, and chose to sign in |
| Main Flow: | 1. User enters a username and a password. <br> 2. User attempts to sign in. <br> 3. System consults database using the provided sign in data. <br> 4. The database informs system that the sign in is successful. <br> 5. System informs the user that the username and password combination is valid. <br> 6. User is signed in|
| Post-conditions: | User is signed into the system |
| Alternate Flows: | **4a Invalid data** <br> 4a1. Database informs the system of the invalid username and password combination. <br> 4a2. System informs the user that the username and password combination is invalid. <br> 4a3. User is allowed another attempt. Return to 1. <br> **4b Nonexistent username** <br> 4b1. Database informs system of the nonexistent username. <br> 4b2. System informs the user that the username provided is not associated to an account. <br> 4b3. User is allowed another attempt. Return to 1. |

| Use case id: | R03 |
| :--- | :--- |
| Use case name: | Create Match |
| Overview: | A new match is made |
| Actors: | User [primary, initiator] |
| Pre-conditions: | User is signed in |
| Main Flow: | 1. User chooses to start a new match. <br> 2. System creates match for user. <br> 3. System adds user to the match. |
| Post-conditions: | User is in a new match |
| Alternate Flows: | - |

| Use case id: | R04 |
| :--- | :--- |
| Use case name: | Invite to Match |
| Overview: | The user invites a user to play their match |
| Primary actors: | User [primary, initiator] |
| Pre-conditions: | User is signed in |
| Main Flow: | 1. Include(Create Match) <br> 2. User selects a different user to send an invitation to, and attempts to send the invitation. <br> 3. System informs the other user of the invitation. <br> 4. The other user accepts the invite. <br> 5. System add the other user to the match. <br> 6. The system cancels any other sent invitations. |
| Post-conditions: | The minimum number of users to start the match have been added to the match |
| Alternate Flows: | **4a User cancels invite** <br> 4a1. The user cancels the invite. <br> 4a2. The System informs the other user the invite is cancelled. Return to 1. <br> **4b Other user rejects invite** <br> 4b1. The other user rejects the invite. <br> 4b2. The System informs the user of the invite rejection. Return to 1. <br> **4c User sends another invite** <br> 4c1. Return to 1.|

| Use case id: | R05 |
| :--- | :--- |
| Use case name: | Play Match |
| Overview: | The users play the match |
| Actors: | User [primary, initiator] |
| Pre-conditions: | User is signed in |
| Main Flow: |  1. Include(Match Invite) <br> 2. The system informs the users the match is starting and the match is setup in its initial or saved state. <br> 3. The users make moves until one user has won the match. <br> Extension point: Record Match Results. <br> 4. The System removes the users from the match.|
| Post-conditions: | The match is terminated |
| Alternate Flows: | **3a An illegal move is attempted** <br> 3a1. A user attempts to make an illegal move. <br> 3a2. The system informs the active user that move is not allowed. Return to 3. <br> **3b Active user has failed to move** <br> 3b1. No move has been made in a fixed time limit. <br> 3b2. The system informs the active user its their turn. Return to 3. <br> **3c User chooses to save match state** <br> 3c1. User chooses to save the match state. <br> Extension point: Save Match State. Return to 3. <br> **3d User quits** <br> 3d1. A user chooses to quit the match. <br> 3d2. The system informs the other user that the other user has quit. <br> 3d3. The remaining user is declared the winner. Return to 3. |

| Use case id: | R06 |
| :--- | :--- |
| Extends: | R05 |
| Use case name: | Save Match State |
| Overview: | The system saves the match state indefinitely |
| Primary actors: | User [Primary, Initiator] |
| Pre-conditions: | User is currently playing a match |
| Main Flow: | 1. User or opponent makes move. <br> 2. User signs off or stops playing. <br> 3. System saves the state of the match. <br> 4. User returns. 5. System resumes match.|
| Post-conditions: | The match is ready to be played where the user left off |
| Alternate Flows: | - |

| Use case id: | R07 |
| :--- | :--- |
| Extends: | R05 |
| Use case name: | Record Match Results |
| Overview: | The results of the match are stored in the database |
| Actors: | Database [primary], User [initiator] |
| Pre-conditions: | The match was terminated |
| Main Flow: | 1. System passes relevant match data to the database. <br> 2. Database records data about the match. |
| Post-conditions: | The relevant data about the match is recorded in the database. |
| Alternate Flows: | - |

| Use case id: | R08 |
| :--- | :--- |
| Use case name: | View User Profile |
| Overview: | The user selects a user's account to view and the selected user's data is displayed |
| Actors: | Database [primary], User [initiator] |
| Pre-conditions: | User is signed in |
| Main Flow: | 1. User attempts to view another user's profile. <br> 2. Database retreives the selected user's profile. <br> 3. System displays the selected user's profile. |
| Post-conditions: | The selected user's profile is displayed |
| Alternate Flows: | **2a User not in database** <br> 2a1. Database informs the system that the username is invalid. <br> 2a2. System informs the user that the username provided is not associated to an account. Return to 1. |

| Use case id: | R09 |
| :--- | :--- |
| Use case name: | Unregister |
| Overview: | The user deletes their existing account from the database |
| Actors: |  User [initiator], Database [primary] |
| Pre-conditions: | User is signed in |
| Main Flow: | 1. User chooses to delete their account. <br> 2. System consults the user to confirm account deletion. <br> 3. The user confirms the account deletion. <br> 4. System informs the database of the account deletion. <br> 5. Database removes sign in data associated with the account. <br> 6. Database informs the system of the successful account deletion. <br> 7. System informs the user of the successful account deletion. <br> 8. The user is signed out. |
| Post-conditions: | The user's account is deleted and the user is signed out |
| Alternate Flows: | **3a User cancels confirmation** <br> 3a1. The user cancels the account deletion. |
