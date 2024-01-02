package data.Api.DeleteDataController;

import data.Application;
import exceptions.BadRequestException;
import exceptions.NotFoundException;
import exceptions.SystemBusyException;
import posts.Post;

import java.util.ArrayList;
import java.util.List;


public class PostDataDelete implements IDeleteDataController {
    @Override
    public void DeleteDataFromServices(String username)  {
        try {
            List<Post> authorPosts = Application.getPostService().getPosts(username);
            List<Post> copyOfAuthorPosts = new ArrayList<>(authorPosts);
            for (Post authorPost : copyOfAuthorPosts) {
                Application.getPostService().deletePost(username, authorPost.getId());
            }
        }
        catch (RuntimeException e){
            System.err.println("Something wrong try again");
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
        System.out.println("Deleted Successfully from Posts");

    }
}
