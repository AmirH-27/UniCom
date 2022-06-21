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
   1. User Email Field `must be aiub student email`
   2. Next Button
   3. Password Field (Hidden) `must be equal or more than 5 characters`
   4. User Name Field (Hidden) `must be more than 1 character`
   5. Confirm Password Field (Hidden)
   
    ```
    Flow: 1->2->if(validEmail)
                    if(registeredUser)->3(visible)->2->login->move to Home view
                    else->4,3,5(visible)->2->if(validInput)->register->move to Home view
                                             else->show invalid input dialog
                else->show invalid email dialog
    ```

- [ ] 2. Home
   1. User Name Label `onClick()->show user settings dialog`
   2. Available Course Tiles `onClick()->move to channel view`
   3. Add Course Tile (+) `onClick()->show add channel dialog`

- [ ] 3. Channel
  - [ ]  1. Channel Header Layout
      1. Channel Name Label (Left)
      2. Total Member Count (2nd Right) `onClick()->show member list dialog`
      3. Leave Button `onClick()->show leave warning dialog`

  - [ ]  2. Message Layout
     - [ ]  1. Incoming Messages Layout
         1. User Name Label (Top-Left) `onClick()->show user info dialog if public`
         2. Message Creation Date Label (Top-2nd Left)
         3. Message Text Label (Bottom-Full)
         
     - [ ]  2. Outgoing Messages Layout
          1. Message Creation Date Label (Top-Right)
          2. Message Text Label (Bottom-Full)
         
  - [ ]  3. Create Message Layout
      1. Type Message Field (Left)
      2. Send Message Button (Right) `if(typeMessageField is not empty)->onClick()->sendMessage() | else->disabled`

### Dialog

- [ ] 1. Add Channel
   1. Course Searchbar Text Field (Top-Left)
   2. Section Searchbar Text Field (Top-2nd Left)
   3. Search Button (Top-Right)
  - [ ] 4. Search Course List Layout (Bottom)
      1. Search Course Item Layout
         1. Course Name (Middle-Left)
         2. Course Join Button (Middle-Right)

- [ ] 2. User Settings
   1. TODO
       
- [ ] 3. Member List
   1. Member Name `if(public)->onClick()->show user info dialog | else->disabled`
   2. Close Dialog Button `onClick()->close dialog`
   
- [ ] 4. User Info
   1. User Name Label 
   2. User Student ID
   3. User Email
   4. Close Dialog Button `onClick()->close dialog`

- [ ] 5. Leave Warning
   1. Warning Text Label `You can not join in this same course again`
   2. Leave Dialog Button `onClick()->leaveChannel()->close dialog->move to home view`
   3. Cancel Dialog Button `onClick()->close dialog`

- [ ] 6. Invalid Input
   1. Error Text Label `Error: Invalid TYPE`
   2. OK Dialog Button `onClick()->close dialog
