package data.Api.DeleteData;

import data.Application;
import exceptions.BadRequestException;
import exceptions.NotFoundException;
import exceptions.SystemBusyException;
import posts.Post;

import java.util.List;

public class PostDataDelete implements Icontroller{
    @Override
    public void Delete(String username)  {
        try {
            // delete user post services
            List<Post> authorPosts = Application.getPostService().getPosts(username);
            for (Post authorPost : authorPosts)
                Application.getPostService().deletePost(username, authorPost.getId());
        }
        catch (BadRequestException e){
            System.err.println(e.getMessage());
        }
        catch (NotFoundException e){
            System.err.println(e.getMessage());
        }
        catch (SystemBusyException e){
            System.err.println(e.getMessage());
        }

    }
}
