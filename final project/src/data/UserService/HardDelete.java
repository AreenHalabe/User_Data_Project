package data.UserService;

import activity.UserActivity;
import data.Application;
import data.Userdata.UserData;
import exceptions.BadRequestException;
import exceptions.NotFoundException;
import exceptions.SystemBusyException;
import iam.UserProfile;
import iam.UserType;
import payment.Transaction;
import posts.Post;

import java.util.List;

public class HardDelete implements DeleteService{
    // deleting depend on user type
    // to delete user service => call : UserService.deleteUser(username)
    // to delete post service => call :
    // UserData.getiPostService().deletePost(author, id);
    // to delete user activity => call :
    // UserData.getiUserActivityService().removeUserActivity(userId, id);
    // to delete payment service => call :
    // UserData.getiPayment().removeTransaction(username, id);


    @Override
    public void Delete(String username) throws SystemBusyException, NotFoundException, BadRequestException {
        // delete user profile data
        UserProfile userProfile = Application.getUserService().getUser(username);
        Application.getUserService().deleteUser(username);

        // delete user post services
        List<Post> authorPosts = Application.getPostService().getPosts(username);
        for (Post authorPost : authorPosts)
            Application.getPostService().deletePost(username, authorPost.getId());

        if (userProfile.getUserType() == UserType.REGULAR_USER) {
            // delete user activity services
            List<UserActivity> activities = Application.getUserActivityService().getUserActivity(username);
            for (UserActivity activity : activities)
                Application.getUserActivityService().removeUserActivity(username, activity.getId());

        } else if (userProfile.getUserType() == UserType.PREMIUM_USER) {
            // delete user activity services
            List<UserActivity> activities =Application.getUserActivityService().getUserActivity(username);
            for (UserActivity activity : activities)
                Application.getUserActivityService().removeUserActivity(username, activity.getId());

            // delete user payment services
            List<Transaction> transactions =Application.getPaymentService().getTransactions(username);
            for (Transaction transaction : transactions)
                Application.getPaymentService().removeTransaction(username, transaction.getId());
        }

    }
}
