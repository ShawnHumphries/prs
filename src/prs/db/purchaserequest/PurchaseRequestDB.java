package prs.db.purchaserequest;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import prs.business.PurchaseRequest;
import prs.util.DBUtil;
import prs.util.StringUtil;

public class PurchaseRequestDB implements PurchaseRequestDAO {

	ArrayList<PurchaseRequest> purchaseRequests = null;
	
	@Override
	public boolean createRequest(PurchaseRequest inRequest) {

		String insertSql = "INSERT INTO requests (Description, Justification, DateNeeded, UserID, DeliveryMode, DocsAttached, Status, Total, SubmittedDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection connection = DBUtil.getConnection();
		try (PreparedStatement ps = connection.prepareStatement(insertSql))
		{
			ps.setString(1, inRequest.getDescription());
			ps.setString(2, inRequest.getJustification());
			ps.setDate(3, (Date) inRequest.getDateNeeded());
			ps.setInt(4, inRequest.getUserID());	
			ps.setString(5, inRequest.getDeliveryMode());
			ps.setBoolean(6, inRequest.isDocAttached());
			ps.setString(7, inRequest.getStatus());
			ps.setDouble(8, inRequest.getTotal());
			ps.setDate(9, (Date) inRequest.getSubmittedDate());
			ps.executeUpdate();
		}
		catch (SQLException e)
		{
			System.out.println(e);
			return false;
		}
		return true;
	}

	@Override
	public ArrayList<PurchaseRequest> getRequestsByUserID(int userID) {

		purchaseRequests = new ArrayList<>();
		String sql = "SELECT ID, Description, Justification, DateNeeded, UserID, DeliveryMode, DocsAttached, Status, Total, SubmittedDate "
				+ "from requests "
				+ "where UserID = ? "
				+ "order by SubmittedDate, DateNeeded";
        Connection connection = DBUtil.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql))
        {
        	ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
        	while (rs.next())
        	{
        		PurchaseRequest pr = getPurchaseRequestFromRow(rs);
        		purchaseRequests.add(pr);
        	}
        }
        catch (SQLException e)
        {
        	System.out.println(e);
        	return null;
        }
		return purchaseRequests;
	}
	
	@Override
	public ArrayList<PurchaseRequest> getPendingRequests() {

		purchaseRequests = new ArrayList<>();
		String sql = "SELECT ID, Description, Justification, DateNeeded, UserID, DeliveryMode, DocsAttached, Status, Total, SubmittedDate "
				+ "from requests "
				+ "where status = 'Submitted' OR status = 'Pending' "
				+ "order by ID";
        Connection connection = DBUtil.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql))
        {
            ResultSet rs = ps.executeQuery();
        	while (rs.next())
        	{
        		PurchaseRequest pr = getPurchaseRequestFromRow(rs);
        		purchaseRequests.add(pr);
        	}
        }
        catch (SQLException e)
        {
        	System.out.println(e);
        	return null;
        }
		return purchaseRequests;
	}

	@Override
	public int getLastInsertID() {

		String lastInsertIDSql = "SELECT LAST_INSERT_ID()";
		Connection connection = DBUtil.getConnection();
		try (PreparedStatement ps = connection.prepareStatement(lastInsertIDSql))
		{
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
            	return rs.getInt(1);
            }
            else
            {
            	rs.close();
            	return -1;
            }
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public boolean updateRequestStatus(int inRequestID, String inStatus) {

		String updateSql = "UPDATE requests SET Status = ? WHERE ID = ?";
		Connection connection = DBUtil.getConnection();
		try (PreparedStatement ps = connection.prepareStatement(updateSql))
		{
        	ps.setString(1, inStatus);
        	ps.setInt(2, inRequestID);
			ps.executeUpdate();
		}
		catch (SQLException e)
		{
			System.out.println(e);
			return false;
		}
		return true;
	}

	private static PurchaseRequest getPurchaseRequestFromRow(ResultSet rs) throws SQLException
	{
        PurchaseRequest purchaseRequest = new PurchaseRequest();

        purchaseRequest.setId(rs.getInt("ID"));
        purchaseRequest.setDescription(rs.getString("Description"));
        purchaseRequest.setJustification(rs.getString("Justification"));
        purchaseRequest.setDateNeeded(rs.getDate("DateNeeded"));
        purchaseRequest.setUserID(rs.getInt("UserID"));
        purchaseRequest.setDeliveryMode(rs.getString("DeliveryMode"));
        purchaseRequest.setDocAttached(rs.getBoolean("DocsAttached"));
        purchaseRequest.setStatus(rs.getString("Status"));
        purchaseRequest.setTotal(rs.getDouble("Total"));
        purchaseRequest.setFormattedTotal(StringUtil.getFormattedDouble(purchaseRequest.getTotal()));
        purchaseRequest.setSubmittedDate(rs.getDate("SubmittedDate"));
    	return purchaseRequest;
	}

}
