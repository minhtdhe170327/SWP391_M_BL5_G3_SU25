package dao;

import dbcontext.DBContext;
import entity.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author Administrator
 */
public class PostDAO extends DBContext{
    
   
     public List<Post> getPostsWithPagination(int page, int pageSize) {
        List<Post> posts = new ArrayList<>();
        query = "SELECT * FROM Post ORDER BY createdDate DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, (page - 1) * pageSize);
            ps.setInt(2, pageSize);
            rs = ps.executeQuery();
            while (rs.next()) {
                Post post = new Post();
                post.setId(rs.getInt("id"));
                post.setAccountId(rs.getInt("accountid"));
                post.setTitle(rs.getString("title"));
                post.setContent(rs.getString("content"));
                post.setCreatedDate(rs.getDate("createdDate"));
                post.setModifiedDate(rs.getDate("modifiedDate"));
                post.setViewCount(rs.getInt("viewCount"));
                post.setStatus(rs.getInt("status"));
                post.setFeatured(rs.getBoolean("featured"));
                posts.add(post);
            }
        } catch (Exception e) {
        }
        return posts;
    }

    public int getTotalPosts() {
        query = "SELECT COUNT(*) FROM Post";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }
     public Post getPostById(int postid) {
        query = "SELECT * FROM Post WHERE id=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, postid);
            rs = ps.executeQuery();
            if (rs.next()) {
                Post post = new Post();
                post.setId(rs.getInt("id"));
                post.setAccountId(rs.getInt("accountid"));
                post.setTitle(rs.getString("title"));
                post.setContent(rs.getString("content"));
                post.setCreatedDate(rs.getDate("createdDate"));
                post.setModifiedDate(rs.getDate("modifiedDate"));
                post.setViewCount(rs.getInt("viewCount"));
                post.setStatus(rs.getInt("status"));
                post.setFeatured(rs.getBoolean("featured"));
                return post;
            }
        } catch (Exception e) {
        }
        return null;
    }
      public List<Comment> getCommentsByPostId(int postid) {
        List<Comment> comments = new ArrayList<>();
        query = "SELECT * FROM Comment WHERE postId=? AND status=1 ORDER BY createdDate DESC";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, postid);
            rs = ps.executeQuery();
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setId(rs.getInt("id"));
                comment.setPostId(rs.getInt("postId"));
                comment.setAccountId(rs.getInt("accountId"));
                comment.setContent(rs.getString("content"));
                comment.setCreatedDate(rs.getDate("createdDate"));
                comment.setParentId(rs.getInt("parentId"));
                comment.setStatus(rs.getInt("status"));
                comments.add(comment);
            }
        } catch (Exception e) {
        }
        return comments;
    }
       public int getLikeCount(int postid) {
        query = "SELECT COUNT(*) FROM [Like] WHERE postId=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, postid);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public boolean hasLiked(int postid, int accid) {
        query = "SELECT COUNT(*) FROM [Like] WHERE postId=? AND accountId=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, postid);
            ps.setInt(2, accid);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
        }
        return false;
    }
     public List<PostImage> getPostImages(int postid) {
        List<PostImage> images = new ArrayList<>();
        query = "SELECT * FROM PostImage WHERE postId=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, postid);
            rs = ps.executeQuery();
            while (rs.next()) {
                PostImage image = new PostImage();
                image.setId(rs.getInt("id"));
                image.setPostId(rs.getInt("postId"));
                image.setImageUrl(rs.getString("imageUrl"));
                image.setThumbnail(rs.getBoolean("isThumbnail"));
                images.add(image);
            }
        } catch (Exception e) {
        }
        return images;
    }
       public boolean hasSaved(int accountId, int postId) {
        String query = "SELECT COUNT(*) FROM SavedPost WHERE accountId=? AND postId=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, accountId);
            ps.setInt(2, postId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {}
        return false;
    }
       
       public List<Post> searchPosts(String keyword) {
    List<Post> list = new ArrayList<>();
    String sql = "SELECT * FROM Post WHERE title LIKE ? OR content LIKE ? ORDER BY createdDate DESC";
    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, "%" + keyword + "%");
        ps.setString(2, "%" + keyword + "%");

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Post p = new Post();
                p.setId(rs.getInt("id"));
                p.setAccountId(rs.getInt("accountId"));
                p.setTitle(rs.getString("title"));
                p.setContent(rs.getString("content"));
                p.setCreatedDate(rs.getDate("createdDate"));
                p.setModifiedDate(rs.getDate("modifiedDate"));
                p.setViewCount(rs.getInt("viewCount"));
                p.setStatus(rs.getInt("status"));
                p.setFeatured(rs.getBoolean("featured"));

                list.add(p);
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}
       public int createPost(Post post) {
        query = "INSERT INTO Post (accountid, title, content, createdDate, status, featured) VALUES (?,?,?,GETDATE(),?,?)";
        try {
            ps = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, post.getAccountId());
            ps.setString(2, post.getTitle());
            ps.setString(3, post.getContent());
            ps.setInt(4, post.getStatus());
            ps.setBoolean(5, post.isFeatured());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return -1;
    }
    
    public void addPostImage(PostImage image) {
        query = "INSERT INTO PostImage (postId, imageUrl, isThumbnail) VALUES (?,?,?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, image.getPostId());
            ps.setString(2, image.getImageUrl());
            ps.setBoolean(3, image.isThumbnail());
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    
    
    public void updatePost(Post post) {
        query = "UPDATE Post SET title=?, content=?, modifiedDate=GETDATE(), status=?, featured=? WHERE id=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getContent());
            ps.setInt(3, post.getStatus());
            ps.setBoolean(4, post.isFeatured());
            ps.setInt(5, post.getId());
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public void deletePostImage(int imageid) {
        query = "DELETE FROM PostImage WHERE id=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, imageid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public List<Post> getPostsByAccountId(int accountId, int page, int pageSize) {
        List<Post> posts = new ArrayList<>();
        query = "SELECT * FROM Post WHERE accountid=? ORDER BY createdDate DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, accountId);
            ps.setInt(2, (page - 1) * pageSize);
            ps.setInt(3, pageSize);
            rs = ps.executeQuery();
            while (rs.next()) {
                Post post = new Post();
                post.setId(rs.getInt("id"));
                post.setAccountId(rs.getInt("accountid"));
                post.setTitle(rs.getString("title"));
                post.setContent(rs.getString("content"));
                post.setCreatedDate(rs.getDate("createdDate"));
                post.setModifiedDate(rs.getDate("modifiedDate"));
                post.setViewCount(rs.getInt("viewCount"));
                post.setStatus(rs.getInt("status"));
                post.setFeatured(rs.getBoolean("featured"));
                posts.add(post);
            }
        } catch (Exception e) {
        }
        return posts;
    }

    public int getTotalPostsByAccountId(int accountId) {
        query = "SELECT COUNT(*) FROM Post WHERE accountid=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, accountId);
            rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count;
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public List<Post> getSavedPostsByAccountId(int accountId) {
        List<Post> posts = new ArrayList<>();
        String query = "SELECT p.* FROM SavedPost s JOIN Post p ON s.postId = p.id WHERE s.accountId=? ORDER BY s.savedAt DESC";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, accountId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Post post = new Post();
                post.setId(rs.getInt("id"));
                post.setAccountId(rs.getInt("accountid"));
                post.setTitle(rs.getString("title"));
                post.setContent(rs.getString("content"));
                post.setCreatedDate(rs.getDate("createdDate"));
                post.setModifiedDate(rs.getDate("modifiedDate"));
                post.setViewCount(rs.getInt("viewCount"));
                post.setStatus(rs.getInt("status"));
                post.setFeatured(rs.getBoolean("featured"));
                posts.add(post);
            }
        } catch (Exception e) {}
        return posts;
    }
    
    public void savePost(int accountId, int postId) {
        String query = "INSERT INTO SavedPost (accountId, postId, savedAt) VALUES (?, ?, GETDATE())";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, accountId);
            ps.setInt(2, postId);
            ps.executeUpdate();
        } catch (Exception e) {}
    }

    public void unsavePost(int accountId, int postId) {
        String query = "DELETE FROM SavedPost WHERE accountId=? AND postId=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, accountId);
            ps.setInt(2, postId);
            ps.executeUpdate();
        } catch (Exception e) {}
    }
    
}