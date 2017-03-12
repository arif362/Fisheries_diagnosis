import java.sql.*;
import java.util.*;
public class StringTokenizerTest
{
	DbUtil util=new DbUtil();
	String lastId="";
	String finalId;

	public StringTokenizerTest()
	{
		//lastId="";

		try
		{
			ResultSet rs=util.stmt.executeQuery("select expert_id from Fisheries_Expert");
			while(rs.next())
			{
				lastId=rs.getString(1);
				//System.out.println(lastId);
			}

		}

		catch(Exception e)
		{
			System.out.println(e);
		}




		if(lastId.equals(""))
		{
			finalId="F.E-1";
		}

		else
		{

			StringTokenizer token=new StringTokenizer(lastId,"-");
			String fixed=token.nextToken();
			int var=Integer.parseInt(token.nextToken());
			var++;

			finalId=fixed+"-"+var;
		}

		System.out.println(finalId);


	}

	public static void main(String args[])
	{
		StringTokenizerTest stt=new StringTokenizerTest();
	}
}