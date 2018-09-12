package app;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

enum Point
{
	W,Z,EMPTY;	
}

//public class Point {
//
//	Map<Points, String> MapPoints = new HashMap<Points, String>();
//	
//	public Point()
//	{
//		for (Points P : Points.values())
//				MapPoints.put(P, "EMPTY");
//			
//	}
//	
//	public void changeStatPoint(Points point, String pointVal) 
//	{
//			MapPoints.put(point, pointVal);
//	}
	
//	public String[] getPointValues() 
//	{
//		
//		String[] pointValues = new String[24];
//		int countIndex = 0;
//		Iterator<Points> Itr = MapPoints.keySet().iterator();
//		
//		while(Itr.hasNext()) {
//			pointValues[countIndex] = MapPoints.get(Itr.next());
//			countIndex++;
//		}
//		
//		return pointValues;
//	}
	
//	public Boolean isPointEmpty(Points Point)
//	{
//		Boolean status;
//		if(MapPoints.get(Point) == "EMPTY") {
//			status = true;
//		}else {
//			status = false;
//		}
//		
//		return status;
//	}
	
	
//}
