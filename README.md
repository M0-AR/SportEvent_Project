## SportEvent_Project


Mohamad and Ismail : 
	 1- Home button 
	 2- Sing up navigation 
	 3- Home screen + Dato Event 
	 4- Result page (add distance, time and share button(try))
	 5- 

 Andry : 
	  1- Show first, event that have been done, first.
	  2- Reusable 

 Javad : 
 	1- Firebase (Cloude firebase (Best for our view) or Real firebase)


## 04/Jan/2021
    * UX 
        * 06/01-2021 Grupper præsenterer deres app for Ian og får feedback på deres UX
        * 08/01-2021 De resterende grupper præsenterer deres app for Ian og får feedback på deres UX
            * Show the closest event in ClosetEvent(Menu) ---Done--- 
            
    * 12/01-2021 Aflevering 
        * 14.00 Aflevering af 4. iteration - til projektstillere og til internt review
        * (Ask) 15.00 og derefter - gennemsyn af følgegruppers apps og opponentgrupper forbereder sparring til onsda
            * Firebase
            * (Ask) GBS
            * Logic
                * Open App -> Interent connection
                * Log in and sign up -> check if email exist and check if user type something
                * Sign up user
                    * Make id for every new user. 
                * HomeScreen
                    * Sign up event should be displyed according to closest event date. ---Done--- 
                    * Move Sign up Event -> Joined Event -> Finished Event. --- Need to test with a data base ---- 
                    * Sing up List 
                        * Delete the event when joinEndDate is done 
                        * Sign up Event 
                            * Participants button -> Current joined participants in the event
                            * Sign up -> Dialog (yes|no) ---Done--- 
                            * Button -> navigate to google maps -> see route
                    * Joned Event List 
                        * Button -> navigate to google maps -> see route
                        * Participants button = Sign up Participants button 
                        * Start button -> raceStartDate ---Done---  -> Dialog turn on GPS Tracking  
                            * Gps Activity 
                                * Distance ->  how much km the user reach 
                                * Time START click on Start Event FINISH click on finish button -> Joined Event list to Finish Event list & put participants in finsished participants list(Result) -> sort them according to ride race time 
                            * Result Activity 
                                * Participant(Entity) -> Result    
                                * Share Result button -> Put result data in the meassge 

    * 13/01-2021 
        * 11.00-14.00 Review / præsentation af apps.
        * 14.00-14.45 Ian og Sune holder møde med projektstillere (TBC)
    * 21/01-2021 
        * Endelig aflevering, afslutning og evaluering/dialog
          Udfyld evaluering af kurset inden klokken 10.55.
         * 13.00 Plenum - mindst 1 fra hver gruppe bør deltage
            * Ønskekrav?
            * Test (Fix bug)
    * 22/01-2020 
        * 3-ugersperiode slutter - projektfremlæggelser
          En fra hver gruppe laver en kort (12 minutter) demonstration af app'en over for lærere og censor
          Det er en fremvisning - ikke en eksamen.
          Nærmere beskrivelse og fremlæggelsesplan - opdateres

## Bug
    * Overskrift til signup event list - Closest Event (simplify meaning for the user)
  