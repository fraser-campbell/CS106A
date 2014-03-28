/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas 
					implements FacePamphletConstants {
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	
	double lastX = 0;
	double lastY = 0;
	
	public FacePamphletCanvas() {
		// You fill this in
	}

	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {

		applicationmsg = new GLabel(msg, 0, 0);
		// Remove old message
		if(getElementAt(lastX, lastY) != null) {
			remove(getElementAt(lastX, lastY));
		}
		double x4_coord = getWidth()/2 - applicationmsg.getWidth()/2;
		double y4_coord = getHeight()-BOTTOM_MESSAGE_MARGIN;		
		applicationmsg.move(x4_coord, y4_coord);
		add(applicationmsg);
		applicationmsg.setFont(MESSAGE_FONT);	
		lastX = x4_coord;
		lastY = y4_coord;
	}
	
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		removeAll();
		addName(profile.getName());
		addImage(profile.getImage());
		addStatus(profile.getStatus(), profile);
		addFriends(profile.getFriends());
	}
		
		
		// You fill this in
	public void addName(String name){
		titname = new GLabel(name, LEFT_MARGIN, TOP_MARGIN);
		titname.setFont(PROFILE_STATUS_FONT);
		titname.setColor(Color.BLUE);
		add(titname);
	}
		
	
	public void addImage(GImage image){
		if(image == null){
			imagerect = new GRect(LEFT_MARGIN, TOP_MARGIN + IMAGE_MARGIN, IMAGE_HEIGHT, IMAGE_WIDTH);
			add(imagerect);
			
			imageword = new GLabel("No Image", 0, 0);
			double x_coord = LEFT_MARGIN + IMAGE_WIDTH/2 - imageword.getWidth();
			double y_coord = TOP_MARGIN + IMAGE_MARGIN + IMAGE_HEIGHT/2;
			imageword.move(x_coord, y_coord);
			imageword.setFont(PROFILE_IMAGE_FONT);
			add(imageword);
		} else { 
			image.setBounds(LEFT_MARGIN, TOP_MARGIN + IMAGE_MARGIN, IMAGE_WIDTH, IMAGE_HEIGHT);
			add(image);
			}
	}
		
	public void addStatus(String status, FacePamphletProfile profile){
		String str = "";
		if(status.equals(str)){
			status = "No current status";
		} else {
			statusline = new GLabel(profile.getName() + " is " + status, 0, 0);
		}
			double x2_coord = LEFT_MARGIN;
			double y2_coord = TOP_MARGIN + 2*IMAGE_MARGIN + IMAGE_HEIGHT;
			statusline.move(x2_coord, y2_coord);
			add(statusline);
	}

	
	public void addFriends(Iterator<String> friends){
		friendstitle = new GLabel("Friends",0, 0);
		double x3_coord = getWidth()/2 - friendstitle.getWidth()/2;
		double y3_coord = TOP_MARGIN + IMAGE_MARGIN + friendstitle.getHeight()/2;
		friendstitle.move(x3_coord, y3_coord);
		add(friendstitle);
		friendstitle.setFont(PROFILE_FRIEND_LABEL_FONT);
		
		Integer count = 1;
		while(friends.hasNext()){
			GLabel friendline = new GLabel(friends.next(), 0, 0);
			y3_coord += 1.5*friendline.getHeight()*count;
			friendline.move(x3_coord, y3_coord);
			add(friendline);
			friendline.setFont(PROFILE_FRIEND_FONT);
			count++;
		}
	}


	private GLabel titname;
	private GRect imagerect;
	private GLabel imageword;
	private GLabel statusline;
	private GLabel friendstitle;
	private GLabel applicationmsg;
}
