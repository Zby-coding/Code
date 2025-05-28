
package day08.homework;

public class ArrayTest04 {

	public static void main(String[] args) {
		int[] arr1 = {1,2,3,4,5};   // 001
		int[] arr2 = {5,6,7};       // 002
		int[] temp = arr1;          // 001
	
		System.out.println("通过temp取出arr1中的元素: ");
	
		for(int i = 0;i<temp.length;i++) {				
			System.out.print(temp[i]+" ");   // 1 2 3 4 5 
		}		
		temp = arr2;                // 002
		System.out.println();
		System.out.println("通过temp取出arr2中的元素: ");
	
		for(int i = 0;i<temp.length;i++) {
			System.out.print(temp[i]+" ");    // 5 6 7 
		}			
	}
}