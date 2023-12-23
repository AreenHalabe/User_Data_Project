package data.Userdata;

import activity.IUserActivityService;
import iam.IUserService;
import iam.UserProfile;
import payment.IPayment;
import posts.IPostService;

public abstract class UserData {
    private IUserActivityService iUserActivityService;
    private IUserService userService;
    private IPayment iPayment;
    private IPostService iPostService;

    public UserData(IUserActivityService iUserActivityService , IUserService iUserService , IPayment iPayment , IPostService iPostService){
        this.iUserActivityService = iUserActivityService;
        this.userService=iUserService;
        this.iPayment=iPayment;
        this.iPostService=iPostService;
    }
}
