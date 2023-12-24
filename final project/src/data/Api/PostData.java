package data.Api;

import data.FileStorag.StoregeService;
import data.Userdata.UserData;
import posts.IPostService;
import posts.Post;

public class PostData implements Controller{
    private IPostService postService;

    public PostData(){
        this.postService= UserData.getiPostService();
    }
    @Override
    public void getData(String name, StoregeService storegeService) {
        var Posts = UserData.getiPostService().getPosts(name);
        String data="";
        for (var post: Posts) {
           // System.out.println(post);
            data = post.getId()+" , "+post.getTitle() + " , "+post.getDate() + " , "+post.getBody();
            storegeService.uploadData(data);

        }

    }
}
