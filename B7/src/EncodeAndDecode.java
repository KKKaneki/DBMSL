
import com.mongodb.*;
import com.mongodb.client.*;

import org.bson.Document;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.*;

public class EncodeAndDecode {
	public static void main(String[] args) throws ParseException {
		MongoClient client = new MongoClient("localhost",27017);
		
//		CREATE CREDENTIALS
		MongoCredential creds = MongoCredential.createCredential("Prady", "dbmsl_b7", "root".toCharArray());
		
		System.out.println("Connected to MongoDB.");
		
		MongoDatabase db = client.getDatabase("dbmsl_b7");
		System.out.println("Database Created");
		
//		CREATING COLLECTION
		
//		db.createCollection("student");
		System.out.println("Collection Created Successfully.");
		
		
		MongoCollection<Document> student = db.getCollection("student");

//		ENCODE
		JSONObject newStudent = new JSONObject();
		newStudent.put("Name", "Pradyumna");
		newStudent.put("Roll No", "1");
		newStudent.put("College", "PICT");
		newStudent.put("Age", "20");
		
		JSONObject newStudentCourse1 = new JSONObject();
		newStudentCourse1.put("name", "Python A-Z");
		newStudentCourse1.put("price", new Double(450));
		
		JSONObject newStudentCourse2 = new JSONObject();
		newStudentCourse2.put("name", "Basics of C");
		newStudentCourse2.put("price", new Double(350));
		
		JSONObject newStudentCourse3 = new JSONObject();
		newStudentCourse3.put("name", "Full Stack Web Development");
		newStudentCourse3.put("price", new Double(550));
		
		JSONArray courses = new JSONArray();
		courses.add(newStudentCourse1);
		courses.add(newStudentCourse2);
		courses.add(newStudentCourse3);
		courses.add(newStudentCourse3);


		newStudent.put("Courses",courses);
		
		Document newS = Document.parse(newStudent.toString());
		student.insertOne(newS);
		
		System.out.println("New Student has been inserted\n");
		
		// JSONObject j = (JSONObject) JSONValue.parse(newStudent.toString());
		// System.out.println("From Code: " + j.get("Name") + " is in " + j.get("College") + " and has completed " + ((JSONArray)j.get("Courses")).size() + " courses");
//		RETREIVE ALL THE DATA FROM DATABASE AND PRINT
		
		FindIterable<Document> students = student.find();
		JSONParser jp = new JSONParser();

		for(Document s : students) {
			JSONObject js = (JSONObject) jp.parse(s.toJson());
			JSONArray ja = (JSONArray) js.get("Courses");
			System.out.println(js.get("Name") + " is in " + js.get("College") + " and has completed " + ja.size() + " courses");
		}
	}

}
