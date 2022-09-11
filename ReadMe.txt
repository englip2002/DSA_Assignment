========================
(1) Table Module
========================
a.	Reason to choose ADT Map and the implementation of Hash Map
    - Its nature which stored key and associated value as an entry.
    - Can be used for entity classes that consist of a unique/distinct key/id, where 
      the Table class consists of table Id and the Customer class consists of customer 
      Id, which both are unique identifiers that can be treated as the “keys” of the 
      instances.
    - Since the duplication of these keys is not allowed, ADT Map is quite suitable to 
      become the collection ADT of these entity classes besides ADT List or ADT Set.

b.	Display Tables Details
    - Retrieve table list from Tables.dat and display each table details including 
      table id, package served, number of seats, reserved date and table customers.
    - The table will not be reserved and no table customers if does not have reserved 
      date.
    - Display the total number of tables in table list and the total number of seats 
      reserved.

c.	Add New Customer
    - Input customer details such as first name, last name, gender and date of birth 
      to create a new customer which will be provide an auto generated customer id as 
      a unique identifier.
    - Display the new created customer.

d.	Create New Table
    - Input table details such as package served, number of seats, reserved date.
    - If reserved date has been specified, the system will force to input at least 1 
      table customer, otherwise skip the input for table customer.
    - Display the new created table.

e.	Edit Table Details
    - Edit options:
        i.	    Package Served.
        ii.	    Number of Seats – Cannot less than 1 or more than 10 seats. If 
                there’s exits table customers, the new number of seats cannot less 
                than the number of table customers exits.
        iii.    Reserved Date – If change from have reserved date to no reserved date, 
                the table customers will be removed. If change from  noreserved date to 
                have reserved date, the system will force to input at least 1 table 
                customer.
        iv.	    Table Customers – Specify new table customers or retained previous 
                table customers.
        v.	    All of the above – Edit for all fields.
    - Display the comparisons of before editing and after editing.

f.	Delete Table
    - Remove a table by selected table id from the table list.
    - Display the removed table details.

g.	Visualize Seat Occupying Rate
    - Illustrate the seats occupying rates in a bar graph that shows the distributions 
      of tables based on their seats occupying rates.
    - Seat Occupying Rate = Number of Table Customers / Number of Seats * 100%
    - Seat occupying rate of each table has been categories into 10 classes, which are 
      [> 0% to 10%], [> 10% to 20%], [> 20% to 30%], [> 30% to 40%], [> 40% to 50%], 
      [> 50% to 60%], [> 60% to 70%], [> 70% to 80%], [> 80% to 90%] and [> 90% to 100%].
    - Tables with 0% of seats occupying rate will not be taken account in the bar graph.
    - The details seats occupying rates for each table in the table list will be 
      displayed in a table.
    - Will not display the graph if the table list is empty.
    
========================
(2) Menu Module
========================

========================
(3) Reservation Module
========================

========================
(4) Catering Module
========================
Provides control to all activities relevant to kitchen affairs.

Available Options in the Catering Module menu: 
  1) Add dish to waiting queue
    - Allows user to add new dish to the waiting queue. 
    - The user need to choose a food from a set of dummy data for the new dish. 
    - The system will then auto-generate all necessary information, and provides
      the Dish ID to the user. 
    - All input errors will be handled by prompting the user to re-enter input. 

  2) Assign next dish in queue to a kitchen
    - Allows user to assign the next dish / dish at the front of the waiting queue
      to a kitchen's cooking queue, simultaneously removing that dish from the 
      waiting queue. 
    - The system will first provide a preview of the dish at the front of the 
      waiting queue, and prompts the user to select a kitchen from a given list
      to assign the dish. 
    - Note that if the selected kitchen's cooking queue was full, the assignation
      will be rejected. 
    - All input errors will be handled by prompting the user to re-enter input. 

  3) Serve a dish from a kitchen
    - Allows user to serve the next dish / dish at the front of the cooking queue 
      of any selected kitchen. 
    - If there are no records of any kitchens in the system, or if there are no 
      cooking dishes in queue, the operation will be rejected and the user will be 
      brought back to the main menu. 
    - If the operation is allowed, the system will first display a list of kitchens, 
      and prompts the user to select a kitchen to serve its next dish. 
    - If the selected kitchen's cooking queue is not empty, then the next dish is 
      served. Else, the operation will be rejected.  
    - All input errors will be handled by prompting the user to re-enter input. 

  4) Manage kitchen details
    - Opens a submenu for the user to choose from: 
        1) Add new kitchen
            - Allows user to add a new kitchen by entering the name and maximum
              cooking queue capacity of the new kitchen. 
            - If the entered name is already exists in the system, the system will 
              ask for double confirmation. 
        
        2) Edit existing kitchen details
            - Allows user to edit an existing kitchen's name and maximum cooking 
              queue capacity. 
            - If the entered name is already exists in the system, the system will 
              ask for double confirmation. 
            
        3) Remove a kitchen
            - Allows user to remove an existing kitchen from the system. 
            - Will ask for double confirmation before removing. 

        4) Return to previous menu
            - Brings the user back to the Catering Module menu. 
            
    - All input errors will be handled by prompting the user to re-enter input. 

  5) Display waiting queue
    - Displays the waiting queue and all the dishes it holds in a table format. 

  6) Display list of kitchens
    - Displays all the kitchens and their respective cooking queue in a simple 
      graphical bar, in a table format. 
    - The user can either select a kitchen by entering the kitchen ID to view its
      cooking queue in a table format, similar to "Catering Module menu -> Option 5", 
      or the user can return to the Catering Module menu by entering a blank input. 
    - All input errors will be handled by prompting the user to re-enter input. 

  7) Search for a dish in queue
    - Brings up a submenu that displays a list of parameters and their current value, 
      in which these values will be used to filter and search for a dish from the 
      waiting queue and all of the kithcen's cooking queue. 
    - Allows user to set values for multiple parameters for searching. 
    - Below is a list of all the allowed options in the Search submenu: 
        1) Dish ID
            - Allows user to edit the Dish ID parameter, which will search for any 
              dish's ID that contains the given value. 
        2) Food name
            - Allows user to edit the Food name parameter, which will search for any 
              dish's food's name that contains the given value. 
        3) Order date
            - Allows user to edit the Order date parameter, by either providing a 
              specific date or a range of dates in DD/MM/YYYY format, which will search 
              for any dish's order date that is within the range of the given start and 
              end dates. 
        4) Order time
            - Allows user to edit the Order time parameter, by either providing a 
              specific time or a range of times in HH:MM:SS format, which will search for 
              any dish's order time that is within the range of the given start and end 
              times. 
        5) Cooking date
            - Allows user to edit the Cooking date parameter, by either providing a 
              specific date or a range of dates in DD/MM/YYYY format, which will search 
              for any dish's cooking date that is within the range of the given start and 
              end dates. 
        6) Cooking time
            - Allows user to edit the Cooking time parameter, by either providing a 
              specific time or a range of times in HH:MM:SS format, which will search for 
              any dish's cooking time that is within the range of the given start and end 
              times. 
        7) Status
            - Allows user to edit the Status parameter, which will search for any 
              dish's status that contains the given value. 
        8) CLEAR ALL PARAMETERS
            - Clears and resets all search parameters to blank. 
        9) START SEARCHING
            - Conduct the search after the parameters have been set. If any of the 
              parameters are left blank, that parameter will be ignored during search. 
              The results will be displayed in table formats, one table for every queue 
              that contains at least one matching dish. 
        10) BACK TO MAIN MENU
            - Brings the user back to the Catering Module menu. 
            
    - All input errors will be handled by prompting the user to re-enter input. 

  8) Quit to main menu
    - Brings the user back to the Catering Services System Main Menu. 

========================
(5) Payment Module
========================
