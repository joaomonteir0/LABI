/**
 * Unitary Tests for testing the correctness of Time class methods
 * 
 * @author Nome e NMec do aluno 1
 * @author Nome e NMec do aluno 2
 * 
 * @version data
 */

import static java.lang.System.*;

public class TestTime
{
	public static void main (String[] args) throws NullPointerException, InvalidValueException
	{
		// Testing the null constructor
		out.printf("\nTesting the null constructor\n");
		Time time1 = new Time();
		out.printf("Time1 (zero time) = %s\n", time1.toString());
		assert time1.toString().equals("00:00:00");

		// Testing the isZero method
		out.printf("\nTesting the isZero method\n");
		assert time1.isZero() == true : "Problems in isZero method";
		
		// Testing the setup constructor from time in hours, minutes and seconds
		out.printf("\nTesting the setup constructor from time in hours, minutes and seconds\n");
		Time time2 = null;
		try
		{
			time2 = new Time(23,12,35);
			out.printf("Time2 (23 hours, 12 minutes and 35 seconds) = %s\n", time2.toString());
		}
		catch (InvalidValueException e)
		{
			throw new InvalidValueException (e.getMessage());
		} 
		assert time2.toString().equals("23:12:35"): "Problems in setup constructor from time in h:m:s";;

		// Testing again the isZero method
		out.printf("\nTesting again the isZero method\n");
		assert time2.isZero() == false : "Problems in isZero method";

		// Testing the setup constructor from object time using the equals method
		out.printf("\nTesting the setup constructor from object time using the equals method\n");
		Time time3 = null;
		try
		{
			time3 = new Time(time2);
			out.printf("Time3 (copy of Time2) = %s\n", time3.toString());
			assert time3.equals(time2): "Problems in setup constructor from a time object";
		}
		catch (NullPointerException e)
		{
			throw new NullPointerException (e.getMessage());
		} 

		// Testing the setup constructor from time in total seconds
		out.printf("\nTesting the setup constructor from time in total seconds\n");
		Time time4 = null;
		try
		{
			time4 = new Time(15025);
			out.printf("Time4 (15025 seconds) = %s\n", time4.toString());
		}
		catch (NullPointerException e)
		{
			throw new NullPointerException (e.getMessage());
		} 
		assert time4.toString().equals("04:10:25"): "Problems in setup constructor from time in total seconds";
		
		// Testing the totalSeconds method
		out.printf("\nTesting the totalSeconds method\n");
		int totalSeconds = time4.totalSeconds();
		out.printf("Total seconds of Time4 = %d\n", totalSeconds);
		assert totalSeconds == 15025: "Problems in totalSeconds methods";
		
		totalSeconds = time1.totalSeconds();
		out.printf("Total seconds of Time1 = %d\n", totalSeconds);
		assert totalSeconds == 0: "Problems in totalSeconds methods";

		// implement tests to verify the remaining methods
		// getHours - getMinutes - getSeconds - setHours - setMinutes - setSeconds
		out.printf("\nTesting getHours - getMinutes - getSeconds - setHours - setMinutes - setSeconds\n");
		Time time5 = null;
		try{
			time5 = new Time(12,14,15);
			out.printf("Testing time5 %s\n", time5.toString());
			out.printf("getHours =  %s, getMinutes = %s, getSeconds = %s\n", time5.getHours(), time5.getMinutes(), time5.getSeconds());
			time5.setHours(10);
			time5.setMinutes(20);
			time5.setSeconds(34);
			out.printf("Set time5 to (10:20:34) -> %s\n", time5.toString());
		}catch (NullPointerException e)
		{
			throw new NullPointerException (e.getMessage());
		}
		assert time5.toString().equals("10:20:34") : "Problems in setHours, setMinutes or setSeconds methods";

		// implement tests to verify the compareTo method with equal and different times
		out.printf("\nTesting compareTo() method (returns 0 if they're equal, a positive number if 1st > 2nd, a negative number if 1st < 2nd)\n");
		Time time6 = null;
		Time time7 = null;
		try{
			time6 = new Time(22,30,59);
			time7 = new Time(22,30,59);
			out.printf("(%s).compareTo(%s) = %s\n",time6.toString(), time7.toString(), time6.compareTo(time7));
		}catch (NullPointerException e){
			throw new NullPointerException (e.getMessage());
		}
		try{
			time6 = new Time(21,10,22);
			time7 = new Time(22,30, 59);
			out.printf("(%s).compareTo(%s) = %s\n",time6.toString(), time7.toString(), time6.compareTo(time7));
		}catch (NullPointerException e){
			throw new NullPointerException (e.getMessage());
		}
		try{
			time6 = new Time(23,10,22);
			time7 = new Time(22,30, 59);
			out.printf("(%s).compareTo(%s) = %s\n",time6.toString(), time7.toString(), time6.compareTo(time7));
		}catch (NullPointerException e){
			throw new NullPointerException (e.getMessage());
		}
		assert time7 == null : "Problems with pTime, it can't be null";

		// implement tests to verify the addTimes method with acceptable values
		Time addtime = null;
		Time time8 = null;
		Time time9 = null;
		out.printf("\nTesting a valid addTimes method\n");
		try
		{
			time8 = new Time(12,12,12);
			time9 = new Time(1,1,1);
			addtime = time8.addTimes(time9);
			out.printf("Sum of (12 hours, 12 minutes and 12 seconds) + (1 hours, 1 minutes and 1 seconds) = %s\n", addtime.toString());
		}
		catch (InvalidValueException e)
		{
			throw new InvalidValueException (e.getMessage());
		}
		assert addtime.totalSeconds() < 0 || addtime.totalSeconds() > 86398 : "Problems with the values, they're are invalid";
		assert time9 == null : "Problems with pTime in addTimes method";
		
		/*
		out.printf("\nTesting an invalid sum in addTimes method\n");
		try
		{
			time8 = new Time(23,59,58);
			time9 = new Time(1,1,1);
			addtime = time8.addTimes(time9);
			out.printf("Sum of (23 hours, 59 minutes and 59 seconds) + (1 hours, 1 minutes and 1 seconds) = %s\n", addtime);
		}
		catch (InvalidValueException e)
		{
			//out.printf("Error, time6+time7 > 23:59:59\n");
			throw new InvalidValueException (e.getMessage());
		}
		out.printf("\nTesting a null value in addTimes method\n");*/
		/*try
		{
			time8 = new Time(1,1,1);
			time9 = new Time(null);
			addtime = time8.addTimes(time9);
			out.printf("Sum of (23 hours, 59 minutes and 59 seconds) + (1 hours, 1 minutes and 1 seconds) = %s\n", addtime.toString());
		}
		catch (NullPointerException e)
		{
			throw new NullPointerException (e.getMessage());
		}*/

		// implement tests to verify the subTimes method with acceptable values
		Time subtime = null;
		Time time10 = null;
		Time time11 = null;
		out.printf("\nTesting a valid subTimes method\n");
		try
		{
			time10 = new Time(12,12,12);
			time11 = new Time(1,1,1);
			subtime = time10.subTimes(time11);
			out.printf("Sub of (12 hours, 12 minutes and 12 seconds) + (1 hours, 1 minutes and 1 seconds) = %s\n", subtime.toString());
		}
		catch (InvalidValueException e)
		{
			throw new InvalidValueException (e.getMessage());
		}
		assert subtime.totalSeconds() < 0 : "Problems with the values, they're are invalid";
		assert time11 == null : "Problems with pTime in subTimes method";
	}
}
