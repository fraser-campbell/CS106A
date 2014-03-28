/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import javax.swing.*;

public class FacePamphlet extends Program 
					implements FacePamphletConstants {

	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		// You fill this in
		canvasing = new FacePamphletCanvas();
		add(canvasing);
		
		database = new FacePamphletDatabase();
		
		//North - buttons and text fields
		add(new JLabel("Name"), NORTH);
		nametext = new JTextField(TEXT_FIELD_SIZE);
		add(nametext, NORTH);
		add = new JButton("Add");
		add(add, NORTH);
		delete = new JButton("Delete");
		add(delete, NORTH);
		lookup = new JButton("Lookup");
		add(lookup, NORTH);
		
		//West - buttons and text fields
		statustext = new JTextField(TEXT_FIELD_SIZE);
		add(statustext, WEST);
		statusbutton = new JButton("Change Status");
		add(statusbutton, WEST);
		statustext.setActionCommand("Change Status");
		statustext.addActionListener(this);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST); 

		
		picturetext = new JTextField(TEXT_FIELD_SIZE);
		add(picturetext, WEST);
		picturebutton = new JButton("Change Picture");
		add(picturebutton, WEST);
		picturetext.setActionCommand("Change Picture");
		picturetext.addActionListener(this);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST); 

		
		friendtext = new JTextField(TEXT_FIELD_SIZE);
		add(friendtext, WEST);
		friendbutton = new JButton("Add Friend");
		add(friendbutton, WEST);
		friendtext.setActionCommand("Add Friend");
		friendtext.addActionListener(this);
		
		addActionListeners();
		
//		canvasing.displayProfile(profile);
    }
    
  
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
		// You fill this in as well as add any additional methods
    	if(e.getActionCommand().equals("Change Status") && !statustext.getText().equals("")){
    		if(profile != null){
    			profile.setStatus(statustext.getText());
    				canvasing.displayProfile(profile);
    				canvasing.showMessage("Status updated to " + profile.getStatus());
//    			println("Change Status: " + profile.getName() + " " + profile.getStatus());	
    		} else {
    			canvasing.showMessage("Please select a profile to change status");
    		}
    	}
    	
    	
    	if(e.getActionCommand().equals("Change Picture") && !picturetext.getText().equals("")) {
    		if(profile != null){ 
	    		GImage image = null; 
	    		 try { 
		    		 image = new GImage(picturetext.getText()); 
		    		 profile.setImage(image);
		    			canvasing.displayProfile(profile); //Test of profile function
		    			canvasing.showMessage("Picture updated");
	    		 } catch (ErrorException ex) { 
	    		 		canvasing.showMessage("Unable to open image file: " + picturetext.getText());
	    		 }
    		} else {
    			canvasing.showMessage("Please select a profile to change picture");
    		}
    	} 
    	
    	
    	if (e.getActionCommand().equals("Add Friend") && !friendtext.getText().equals("")){
    		if(profile != null){ 
    			String name = friendtext.getText();
    			// If the name exists in the database
    			if(database.containsProfile(name)){
    				if (profile.addFriend(name)==true){
    					//Need to add this friend recipricoly now so create variable profile_friend
    					FacePamphletProfile profile_friend  = database.getProfile(name);
    					//profile_name is the recipricol name we need to add
    					String profile_name = profile.getName();
    					profile_friend.addFriend(profile_name);
    					canvasing.displayProfile(profile);
    					canvasing.showMessage(name +  " added as a friend");
    				} else {
    					canvasing.showMessage(profile.getName() + " already has " + name + " as a friend");
    				}
    				// Friend does not exist	
    			} else {
    				canvasing.showMessage(name + " does not exist.");
    			}
    			// No profile selected
    		} else {
				canvasing.showMessage("Please select a profile to add friend");
			}
    	}
 
    	
    	if (e.getActionCommand().equals("Add")){
    		String name = nametext.getText();
    		println(name);
    		// i.e. if does not include profile name
    		if(!database.containsProfile(name)){
    			profile = new FacePamphletProfile(name);
    			database.addProfile(profile);
    			String message = "New profile created";
    			canvasing.displayProfile(profile); //Test of profile function
    			canvasing.showMessage(message);
            // i.e. if does include profile name
    		} else {
    			profile = database.getProfile(name);
    			String message = "A profile with the name" + profile.getName() + "already exists";
    			canvasing.displayProfile(profile); //Test of profile function
    			canvasing.showMessage(message);
    		}
    	}
    	
    	
    	if (e.getActionCommand().equals("Delete")){
    		String name = nametext.getText();
//    		println(name);
    		// i.e. if does not include profile name
    		if(!database.containsProfile(name)){
//    			println("Profile with name: " + name + " does not exist");
    			canvasing.showMessage("A profile with the name" + name + "does not exist");
    			profile = null; //When a profile is deleted want to remove current
        	// i.e. if does include profile name
    		} else {
    			profile = database.getProfile(name);
    			String message = "Profile of " + name + " deleted";
    			database.deleteProfile(name);
//    			println("Profile with name: " + name + " was deleted");
    			profile = null; //When a profile is deleted want to remove current
    			canvasing.displayProfile(profile);
    			canvasing.showMessage(message);
    		}
    	}
    	
    	
    	if (e.getActionCommand().equals("Lookup")){
    		String name = nametext.getText();
//    		println(name);
    		// i.e. if does not include profile  name
    		if(!database.containsProfile(name)){
    			profile = null; //When a profile is deleted want to remove current
    			canvasing.displayProfile(profile);
    			canvasing.showMessage("A profile with the" + name + "does not exist");
        	// i.e. if does include profile name
    		} else {
    			profile = database.getProfile(name);
    			canvasing.displayProfile(profile);
    			canvasing.showMessage("Displaying" + name);
//    			println("Lookup: " +  profile.toString());
    		}
    	}
    }
    
	//Instance Variables
    private FacePamphletCanvas canvas;	
    	//North
	private JTextField nametext;
	private JButton add;
	private JButton delete;
	private JButton lookup;

		//East
	private JButton statusbutton;
	private JTextField statustext;
	private JButton picturebutton;
	private JTextField picturetext;
	private JButton friendbutton;
	private JTextField friendtext;
	
	private FacePamphletDatabase database;
	private FacePamphletProfile profile;
	private FacePamphletCanvas canvasing;
}
