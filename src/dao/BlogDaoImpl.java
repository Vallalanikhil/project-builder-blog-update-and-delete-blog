package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Blog;
import utility.ConnectionManager;

public class BlogDaoImpl implements BlogDaoInterface{

	private static final String UPDATE_BLOGS = null;
	private static final String DELETE_BLOGS_BY_ID = null;

	@Override
	public void insertBlog(Blog blog) throws Exception {
		// TODO Auto-generated method stub
		Connection cn=ConnectionManager.getConnection();
		String sql="INSERT INTO BLOGS(blogId,blogName,blogDescription) VALUES(?,?,?,?)";
		PreparedStatement ps = cn.prepareStatement(sql);
		ps.setLong(1, blog.getBlogId());
		ps.setString(2,blog.getBlogTitle());
		ps.setString(3,blog.getBlogDescription());
		ps.executeUpdate();
		cn.close();
	}

	@Override
	public Blog selectBlog(int blogid) throws Exception{
		Blog blog = null;
		System.out.println(blogId);
		Connection con = ConnectionManager.getConnection();
		PreparedStatement preparedStatement = con.prepareStatement(SELECT_BLOGS_BY_ID);
		preparedStatement.setInt(1, blogId);
		System.out.println(preparedStatement);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			int Id = rs.getInt("blogId");
			String blogTitle = rs.getString("blogName");
			String blogDescription = rs.getString("blogDescription");				
			blog = new Blog();
			blog.setBlogId(Id);
			blog.setBlogTitle(blogTitle);
			blog.setBlogDescription(blogDescription);				
		}
		return blog;
	}

	@Override
	public List<Blog> selectAllBlogs() throws Exception {
		Blog blog = null;
		List<Blog> blogList = new ArrayList<>();
		Connection con = ConnectionManager.getConnection();
		PreparedStatement preparedStatement = con.prepareStatement("select * from BLOGS");
		System.out.println(preparedStatement);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			int Id = rs.getInt("blogId");
			String blogTitle = rs.getString("blogName");
			String blogDescription = rs.getString("blogDescription");				
			blog = new Blog();
			blog.setBlogId(Id);
			blog.setBlogTitle(blogTitle);
			blog.setBlogDescription(blogDescription);
			blogList.add(blog);
		}
		return blogList;
	}

	@Override
	public boolean deleteBlog(int id) throws Exception {
		System.out.println(id);
		boolean rowDeleted;
		Connection con = ConnectionManager.getConnection();
		PreparedStatement statement = con.prepareStatement(DELETE_BLOGS_BY_ID);
		statement.setInt(1, id);
		rowDeleted = statement.executeUpdate()>0;
		return rowDeleted;
	}

	@Override
	public boolean updateBlog(Blog blog) throws SQLException, Exception {
		// TODO Auto-generated method stub
		boolean rowUpdated = false;
		System.out.println(blog.getBlogId());
		System.out.println(blog.getBlogTitle());
		System.out.println(blog.getBlogDescription());
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement ps = connection.prepareStatement(UPDATE_BLOGS); 
		ps.setInt(1, blog.getBlogId());
		ps.setString(2, blog.getBlogTitle());
		ps.setString(3,blog.getBlogDescription());
		rowUpdated = ps.executeUpdate() > 0;
		System.out.println(rowUpdated);
		return rowUpdated;
	}
	
}
