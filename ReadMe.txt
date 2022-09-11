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
1) 	Add New Package: 
  -	This function is used to add a new package. 
  -	Will check if any same package name appears or not if yes, then cannot add else it can add successfully. 
  -	The add menu item in that new package can directly add continuously but the user can choose to not want to add directly. 

2)  Remove Package:
  -	This function is used to remove that specific package where the system will indicate a specific index number of the Array Set to remove a specific package by using the Package ID. 
  -	It will remove the menu items in the package simultaneously. 

3)	Add New Menu Item: 
  -	This function is used to add a new menu item. 
  -	Will check if any samemenu item name appears or not if yes, then cannot add else it can add successfully.

4)	Remove Menu Item:
  -	 This function is used to remove that specific menu item where the system will indicate a specific index number of the Array Set to remove a specific menu item by using the Menu Item ID. 

5)	Continue Add Menu Item To Package: 
  -	This function will let the user continue to add the menu item to the package where the package already exists. 
  -	This function will check the menu items in the package whether that menu item had existed in the package.  

6)	Remove Menu Item From Package:
  -	This function is used to remove specific menu items from the package. 	

7)	Modify Package: 
  -	To modify the package attributes. 
    -	Modify Package Name
    -	Modify Package Price 
    -	Modify Package Description
    -	Modify Menu Item Limit
    -	Modify All  

8)	Modify Menu Item:
  -	To modify menu item attributes. 
    -	Modify Menu Item Name 
    -	Modify Menu Item Description
    -	Modify Menu Item Category
    -	Modify All  

9)	Display Menu Item: 
  -	To display all the details of the menu items.  

10)	Display Package: 
  -	To display all the details of the package. 

11)	Search Package: 
  -	To search the package by using some filter attributes. 
    -	Search by Package Name
    -	Search by Price Range
    -	Search by Menu Item Name
    -	Filter the Package by using Price Range, Menu Item Range and Menu Item Category 

	Utility functions: 
1)	Add New Menu Item To Package 
  -	This function is an utility where called to add the Menu Item to Package. 

2)	Search Menu Item In Package By ID
  -	This function will search the menu item where inside in the package by using the menu item ID and return that specific element object. 

3)	Display Modify Package Table 
  -	Display the package table choice to the user.

4)	Display Modify Menu Item Table 
  -	Display the menu item table choice to the user.

5)	Display Search Table 
  -	Display the search table choice to the user.

6)	Search Specific Package By ID
  -	Search the package by id and return that specific element object.

7)	Search Specific Package By Name
  -	Search the package by name and return that specific element object.

8)	Check Menu Item Name
  -	To check is that the menu item name already exists. 

9)	Check Package Name
  -	To check is that the package name already exists. 

10)	Search Menu Item by Name in Package:
  -	To search the menu item by name in the package and print the package that consists of that specific menu item name. 

11)	Search Specific Menu Item by ID:
  -	To search the menu item ID

12)	Filter Package:
  -	To filter and find out the package that is match all the requirements of the user want to find a suitable package that the user want. 

========================
(3) Reservation Module
========================
Provides control to all activites relevent to reservation

Available Options in the Reservation Module menu:
1. Make Reservation for current account
   - Allows user to make reservation.
	1. Enter reservation details
	   - Let user to enter reservation details for further process.
	   - Need to be entered first, if not cannot access other function.
	2. Enter food into cart
	   - Allow user to enter food based on the package chosen in option 1.
	   - The total food will not exceed the limit set in every package.
	3. Remove food from cart
	   - Allow user to remove the food from cart if exist.
 	4. View Cart
	   - Allow user to view the food item in cart.
	5. CheckOut
	   - Generate bill and complete the reservation process.
	6. Exit
	   - Save the progress and back to previous stage.
2. View all reservation history
   - Allow user to view all the reservation history.
	1. Reservation Date
	   - The reservation history sort by reservation date.
      2. Serve Date
	   - The reservation history sort by serve date.
	3. Default
	   - The reservation history sort by default (sequence of input).
	4. Exit
	   - Back to previous stage.
3. View cart in reservation.
   - Allow user to choose reservation and view the food item in cart.
4. Remove Reservation
   - Allow user to remove reservation from reservation history.
5. Search reservation
   - Allow user to search reservation dynamically based on the input entered by user.
6. Exit
   - Back to previous stage.
   
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
