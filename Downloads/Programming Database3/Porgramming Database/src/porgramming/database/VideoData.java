package porgramming.database;

// Skeleton version of VideoData.java that links to a database.
// NOTE: You should not have to make any changes to the other
// Java GUI classes for this to work, if you complete it correctly.
// Indeed these classes shouldn't even need to be recompiled
import java.sql.*; // DB handling package
import java.util.*;

public class VideoData {


    private static Connection connection;
    private static Statement stmt;

    static {
        // standard code to open a connection and statement to an Access database
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String sourceURL = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=Videos.mdb;";
            connection = DriverManager.getConnection(sourceURL, "admin", "");
            stmt = connection.createStatement();
        } catch (Exception e) {
            // shouldn't happen if DB is set up correctly
            System.out.println(e);
               System.out.println("doesn't work");
        }
    }

	private static String pad(String string, int width) {
		if (string == null) {
			string = "";
		}
		width -= string.length();
		for (int i = 0; i < width; ++i) {
			string += " ";
		}
		return string;
	}

	private static String formatListEntry(ResultSet res) throws SQLException {
		return res.getString("ID") + " " + pad(stars(res.getInt("Rating")), 12)  + " " 
			+  pad(res.getString("PlayCount"), 19)  
			+ " " + pad(res.getString("Video"), 27) + " " + res.getString("Director") + "\n";
	}

    private static String listHeader() {        
        String output = "ID  Rating  Play Count    Video                               Director\n";
        output += "== =====  =========  ================   ===========\n";
        return output;
    }

    public static String listAll() {
        String output = listHeader();
        try {
            ResultSet res = stmt.executeQuery("SELECT * FROM VideoTable");
            while (res.next()) { // there is a result
                output += formatListEntry(res);
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        return output;
    }



    static String getDirector(String id) {
        try {
            // Need single quote marks ' around the id field in SQL. This is easy to get wrong!
            // For instance if id was "04" the SELECT statement would be:
            // SELECT * FROM VideoTable WHERE id = '04'
            ResultSet res = stmt.executeQuery("SELECT * FROM VideoTable WHERE ID = '" + id + "'");
            if (res.next()) { // there is a result
                return res.getString("Director");
            } else {
                return null; // null means no such item
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

// Add a new video 
    static void setVideo(String id, String video, String director) {
        // Use SQL INSERT statement to write to the database
        String updateStr = "INSERT INTO VideoTable(ID, Video, DIrector) VALUES('"
		+ id + "', '" + video + "', '" + director + "')";
        System.out.println(updateStr);
        try {
            stmt.executeUpdate(updateStr);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static int getRating(String id) {
        try {
            // Need single quote marks ' around the id field in SQL. This is easy to get wrong!
            // For instance if id was "04" the SELECT statement would be:
            // SELECT * FROM VideoTable WHERE id = '04'
            ResultSet res = stmt.executeQuery("SELECT * FROM VideoTable WHERE ID = '" + id + "'");
            if (res.next()) { // there is a result
                return res.getInt("Rating");
            } else {
                return -1;
            }
        } catch (Exception e) {
            System.out.println(e);
            return -1; // -1 means no such item
        }
    }

    public static void setRating(String id, int rating) {
        // SQL UPDATE statement required. For instance if priority is 5 and id is "04" then updateStr is
        // UPDATE Libary SET Priority = 5 WHERE id = '04'
        String updateStr = "UPDATE VideoTable SET Rating = " + rating + " WHERE ID = '" + id + "'";
        System.out.println(updateStr);
        try {
            stmt.executeUpdate(updateStr);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

// Get the video name
    static String getName(String id) {
        try {
            // Need single quote marks ' around the id field in SQL. This is easy to get wrong!
            // For instance if id was "04" the SELECT statement would be:
            // SELECT * FROM VideoTable WHERE id = '04'
            ResultSet res = stmt.executeQuery("SELECT * FROM VideoTable WHERE ID = '" + id + "'");
            if (res.next()) { // there is a result
                return res.getString("Video");
            } else {
                return null; // null means no such item
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    static void setName(String id, String video) {
		// complete this method yourself - similar to setRating()
        String updateStr = "UPDATE VideoTable SET Video = " + video + " WHERE ID = '" + id + "'";
        System.out.println(updateStr);
        try {
            stmt.executeUpdate(updateStr);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

	public static String getPlayCount(String id) {
        try {
            // Need single quote marks ' around the id field in SQL. This is easy to get wrong!
            // For instance if id was "04" the SELECT statement would be:
            // SELECT * FROM VideoTable WHERE id = '04'
            ResultSet res = stmt.executeQuery("SELECT * FROM VideoTable WHERE ID = '" + id + "'");
            if (res.next()) { // there is a result
                return res.getString("PlayCount");
            } else {
                return null; // null means no such item
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    static void setPlayCount(String id, String count) {
		// complete this method yourself - similar to setRating()
                String updateStr = "UPDATE VideoTable SET PlayCount = " + count + " WHERE ID = '" + id + "'";
        System.out.println(updateStr);
        try {
            stmt.executeUpdate(updateStr);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public static String stars(int rating) {
        String stars = "";
        for (int i = 0; i < rating; ++i) {
            stars += "*";
        }
        return stars;
    }

    // close the database
    public static void close() {
        try {
            connection.close();
        } catch (Exception e) {
            // this shouldn't happen
            System.out.println(e);
        }
    }
    
    static void setNewPlayCount(String id) {
        int count = 0;
        try {
            ResultSet res = stmt.executeQuery("SELECT * FROM VideoTable WHERE ID = '" + id + "'");
            if (res.next()) { // there is a result
                count = Integer.parseInt(res.getString("Playcount"));
            }
        } catch (Exception e) {
            System.out.println(e);
            
        }
    count += 1;
        
        String updateStr = "UPDATE VideoTable SET Playcount = " + count + " WHERE ID = '" + id + "'";
        System.out.println(updateStr);
        try {
            stmt.executeUpdate(updateStr);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    }
    
