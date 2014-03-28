/** 
 * This class provides methods for working with an array that expands 
 * to include any positive index value supplied by the caller. 
 */ 

import java.util.*;
 
public class ExpandableArray { 
 
/** 
 * Creates a new expandable array with no elements. 
 */ 
 public ExpandableArray() { 
 storage = new Object[0];
 } 
 
 
/** 
 * Sets the element at the given index position to the specified. 
 * value. If the internal array is not large enough to contain that 
 * element, the implementation expands the array to make room. 
 */ 
 public void set(int index, Object value) { 
	 // Check if set is asking for an array which is longer than the old array
	 if(storage.length<=index){
		 Object[] storage_new = new Object[index+1];
		 
		 //If the new array is longer populate the new array with the old
		 for(int i=0; i<=index; i++){
			 if(i<storage.length){	 
				 storage_new[i] = storage[i];
			 } else if(i==index) {
				 storage_new[i]=value;
			 } else {
				 storage_new[i]=null;
			 }
		 }
		 
		 storage = storage_new; // make the array now reference the new array
		 
		 // If new value to be set is not greater just change the old array
	 } else {
		 storage[index] = value; //Change the single index of the old array
	 }
 } 
 
 
/** 
 * Returns the element at the specified index position, or null if 
 * no such element exists. Note that this method never throws an 
 * out-of-bounds exception; if the index is outside the bounds of 
 * the array, the return value is simply null. 
 */ 
 public Object get(int index) { 
	 if (index >= storage.length) return null; 
	 return storage[index];
 } 

 private Object[] storage;
 
} 
