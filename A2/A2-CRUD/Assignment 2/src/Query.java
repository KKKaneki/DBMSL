import java.sql.*;
import java.util.Scanner;

public class Query {
    void create_view() {

    }
    public static void main(String args[]) {
        try{

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment2","root","root");
            Statement stmt = con.createStatement();

            Scanner sc = new Scanner(System.in);
            //ResultSet rs = stmt.executeQuery("select * from customer");
            int choice=0;
            while(true){
                System.out.println("Menu\n1.View\n2.Index\n0.Exit\n");
                choice = sc.nextInt();
                if(choice == 0) {
                    break;
                }
                switch (choice) {
                    case 1: {
                        int ch;
                        System.out.println("1.Simple View\n2.Complex View\n3.Drop view\n");
                        ch = sc.nextInt();
                        switch (ch) {
                            case 1:{
                                //System.out.println("Enter query:");
                                //String str = sc.next();
                                try {
                                    stmt.executeUpdate("create or replace view myview1 as select cust_fname, cust_lname from customer");
                                    System.out.println("Query ok");
                                    ResultSet r = stmt.executeQuery("select * from myview1");
                                    while(r.next()) {
                                        System.out.println(r.getString(1) + "\t" + r.getString(2));
                                    }
                                } catch (SQLException e) {
                                    System.out.println(e);
                                }
                                break;
                            }
                            case 2: {
                                //System.out.println("Enter query:");
                                //String str = sc.next();
                                try {
                                    stmt.executeUpdate("create or replace view myview2 as select author_name,title from author, book where author.author_no=book.author_no");
                                    System.out.println("Query ok");
                                    ResultSet r = stmt.executeQuery("select * from myview2");
                                    while(r.next()) {
                                        System.out.println(r.getString(1) + "\t\t\t" + r.getString(2));
                                    }
                                } catch (SQLException e) {
                                    System.out.println(e);
                                }
                                break;
                            }
                            case 3: {
                                //System.out.println("Enter query for dropping view");
                                //String s = sc.next();
                                try {
                                    stmt.executeUpdate("drop view myview1");
                                    stmt.executeUpdate("drop view myview2");
                                    System.out.println("Query ok");
                                } catch (SQLException e) {
                                    System.out.println(e);
                                }
                            }
                        }
                        break;
                    }
                    case 2: {
                        int ch;
                        System.out.println("1.Simple Index\n2.Compound Index\n3.Unique Index\n4.Drop index");
                        ch = sc.nextInt();
                        switch (ch){
                            case 1:{
                                try {
                                    stmt.executeUpdate("create index pub_id on publisher(publisher_name)");
                                    System.out.println("Query ok");
                                    ResultSet r = stmt.executeQuery("show index from publisher");
                                    while(r.next()){
                                        System.out.println(r.getString(3) + "\t" + r.getString(5));
                                    }
                                } catch (SQLException e) {
                                    System.out.println(e);
                                }
                                break;
                            }
                            case 2: {
                                try{
                                    stmt.executeUpdate("create index cust_ind on customer(cust_fname,cust_lname)");
                                    System.out.println("Query ok");
                                    ResultSet r = stmt.executeQuery("show index from customer");
                                    while(r.next()){
                                        System.out.println(r.getString(3) + "\t" + r.getString(5));
                                    }
                                } catch (SQLException e) {
                                    System.out.println(e);
                                }
                                break;
                            }
                            case 3: {
                                try{
                                    stmt.executeUpdate("create unique index author_id on author(author_no)");
                                    System.out.println("Query ok");
                                    ResultSet r = stmt.executeQuery("show index from author");
                                    while(r.next()){
                                        System.out.println(r.getInt(2) + "\t" +r.getString(3) + "\t" + r.getString(5));
                                    }
                                } catch (SQLException e) {
                                    System.out.println(e);
                                }
                                break;
                            }
                            case 4: {
                                try{
                                    stmt.executeUpdate("alter table customer drop index cust_ind");
                                    stmt.executeUpdate("alter table publisher drop index pub_id");
                                } catch (SQLException e) {
                                    System.out.println(e);
                                }
                                break;
                            }
                        }
                        break;
                    }
                   
                }
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}