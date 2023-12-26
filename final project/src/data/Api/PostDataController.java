package data.Api;

import data.Application;
import data.FileStorag.StoregeService;

public class PostDataController implements Controller{

    @Override
    public void getData(String name, StoregeService storegeService) {
        var Posts = Application.getPostService().getPosts(name);
        String data="";
        for (var post: Posts) {
            data = post.getId()+" , "+post.getTitle() + " , "+post.getDate() + " , "+post.getBody();
            storegeService.uploadData(data);

        }

    }
}
