# UniCom
UniCom is an open communication platform for university students to collaborate with the course mates.

## -version 1.0 

User crude with communication channel.

### Domain

- [x] 1. User
- [ ] 2. Channel
- [ ] 3. Message

### View

- [ ] 1. Login/Registration
   - User Email Field `must be aiub student email`
   - Next Button
   - Password Field (Hidden) `must be equal or more than 5 characters`
   - User Name Field (Hidden) `must be more than 1 character`
   - Confirm Password Field (Hidden)
   
    ```
    Flow: 1->2->if(validEmail)
                    if(registeredUser)->3(visible)->2->login->move to Home view
                    else->4,3,5(visible)->2->if(validInput)->register->move to Home view
                                             else->show invalid input dialog
                else->show invalid email dialog
    ```

- [ ] 2. Home
   - User Name Label `onClick()->show user settings dialog`
   - Available Course Tiles `onClick()->move to channel view`
   - Add Course Tile (+) `onClick()->show add channel dialog`

- [ ] 3. Channel
  - [ ]   Channel Header Layout
      - Channel Name Label (Left)
      - Total Member Count (2nd Right) `onClick()->show member list dialog`
      - Leave Button `onClick()->show leave warning dialog`

  - [ ]   Message Layout
     - [ ]   Incoming Messages Layout
         - User Name Label (Top-Left) `onClick()->show user info dialog if public`
         - Message Creation Date Label (Top-2nd Left)
         - Message Text Label (Bottom-Full)
         
     - [ ]   Outgoing Messages Layout
          - Message Creation Date Label (Top-Right)
          - Message Text Label (Bottom-Full)
         
  - [ ]   Create Message Layout
      - Type Message Field (Left)
      - Send Message Button (Right) `if(typeMessageField is not empty)->onClick()->sendMessage() | else->disabled`

### Dialog

- [ ] 1. Add Channel
   - Course Searchbar Text Field (Top-Left)
   - Section Searchbar Text Field (Top-2nd Left)
   - Search Button (Top-Right)
  - [ ]  Search Course List Layout (Bottom)
      - Search Course Item Layout
         - Course Name (Middle-Left)
         - Course Join Button (Middle-Right)

- [ ] 2. User Settings
   - TODO
       
- [ ] 3. Member List
   - Member Name `if(public)->onClick()->show user info dialog | else->disabled`
   - Close Dialog Button `onClick()->close dialog`
   
- [ ] 4. User Info
   - User Name Label 
   - User Student ID
   - User Email
   - Close Dialog Button `onClick()->close dialog`

- [ ] 5. Leave Warning
   - Warning Text Label `You can not join in this same course again`
   - Leave Dialog Button `onClick()->leaveChannel()->close dialog->move to home view`
   - Cancel Dialog Button `onClick()->close dialog`

- [ ] 6. Invalid Input
   - Error Text Label `Error: Invalid TYPE`
   - OK Dialog Button `onClick()->close dialog
