package data.Api;

import data.Application;
import data.FileStorag.StoregeService;
import exceptions.BadRequestException;
import exceptions.NotFoundException;
import exceptions.SystemBusyException;

public class PostDataController implements Controller{

    @Override
    public void getData(String name, StoregeService storegeService)  {
        try{
            var Posts = Application.getPostService().getPosts(name);
            String data="";
            for (var post: Posts) {
                data = post.getId()+" , "+post.getTitle() + " , "+post.getDate() + " , "+post.getBody();
                storegeService.uploadData(data);

            }
            System.out.println("upload (Posts_Data) successfully.");
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
