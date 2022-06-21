# UniCom
UniCom is an open communication platform for university students to collaborate with the course mates.

### -version 1.0 

User crude with communication channel.

**Domain:**

1. User
2. Channel
3. Message

**View:**

1. Login/Registration: 
   1. User Email Field
   2. Next Button
   3. Password Field (Hidden)
   4. User Name Field (Hidden)
   5. Confirm Password (Hidden)
   

    Flow: 1->2->if(registeredUser)->3(visible)->2->login->move to Home view
    ->else->4,3,5(visible)->2->register->move to Home view


2. Home:
   1. Available Course Tiles
   2. Add Course Tile (+)
   

    1->onClick()->move to channel view
    2->onClick()->move to add channel dialog


3. Channel:
   1. Channel Header Layout
      1. Channel Name (Left)
      2. Total Members (Right)
        

    b->onClick()->Show Member List dialog


   2. Message Layout
      1. Incoming Messages Layout
         1. User Name Field (Top-Left)
         2. Message Creation Date Field (Top-2nd Left)
         3. Message Text (Bottom-Full)
      2. Outgoing Messages Layout
         1. Message Creation Date Field (Top-Right)
         2. Message Test (Bottom-Full)
   3. Create Message Layout
      1. Type Message Field (Left)
      2. Send Message Button (Right)
      
    i.a->onClick()->Show User Info dialog if public
    iii.2->onClick()->sendMessage()

**Dialog:**

1. Add Channel:
   1. Course Searchbar Text Field (Top-Left)
   2. Section Searchbar Text Field (Top-2nd Left)
   3. Search Button (Top-Right)
   4. Search Course List Layout(Bottom)
      1. Search Course Item Layout
         1. Course Name (Middle-Left)
         2. Course Join Button (Middle-Right)
2. Member List:
   1. Member Name
3. User Info:
   1. User Name Label 
   2. User Student ID (if public)
   3. User Email (if public)
    