package data;

import activity.IUserActivityService;
import activity.UserActivity;
import activity.UserActivityService;
import data.Userdata.UserData;
import iam.IUserService;
import iam.UserProfile;
import iam.UserService;
import iam.UserType;
import payment.IPayment;
import payment.PaymentService;
import payment.Transaction;
import posts.IPostService;
import posts.Post;
import posts.PostService;

import java.time.Instant;

public class Application {

    private static final IUserActivityService userActivityService = new UserActivityService();
    private static final IPayment paymentService = new PaymentService();
    private static final IUserService userService = new UserService();
    private static final IPostService postService = new PostService();

    public static IUserActivityService getUserActivityService(){
        return userActivityService;
    }

    public static IPayment getPaymentService(){
        return paymentService;
    }

    public static IUserService getUserService(){
        return userService;
    }
    public static IPostService getPostService(){
        return postService;
    }


    public static void main(String[] args) {
        generateRandomData();
        Instant start = Instant.now();
        System.out.println("Application Started: " + start);
        //TODO Your application starts here. Do not Change the existing code
        var ob = userService.getUser("user3");
        System.out.println(ob.getUserType()+"................");

        UserData userData = new UserData();

        userData.getData("user5" , "pass5");


        //TODO Your application ends here. Do not Change the existing code
        Instant end = Instant.now();
        System.out.println("Application Ended: " + end);
    }


    private static void generateRandomData() {
        for (int i = 0; i < 100; i++) {
            generateUser(i);
            generatePost(i);
            generatePayment(i);
            generateActivity(i);
        }
        System.out.println("Data Generation Completed");
    }

    private static void generateActivity(int i) {
        for (int j = 0; j < 100; j++) {
            userActivityService.addUserActivity(new UserActivity("user" + i, "activity" + i + "." + j, Instant.now().toString()));
        }
    }

    private static void generatePayment(int i) {
        for (int j = 0; j < 100; j++) {
            paymentService.pay(new Transaction("user" + i, i * j, "description" + i + "." + j));
        }
    }

    private static void generatePost(int i) {
        for (int j = 0; j < 100; j++) {
            postService.addPost(new Post("title" + i + "." + j, "body" + i + "." + j, "user" + i, Instant.now().toString()));
        }
    }

    private static void generateUser(int i) {
        UserProfile user = new UserProfile();
        user.setUserName("user" + i);
        user.setFirstName("first" + i);
        user.setLastName("last" + i);
        user.setPhoneNumber("phone" + i);
        user.setEmail("email" + i);
        user.setPassword("pass" + i);
        user.setRole("role" + i);
        user.setDepartment("department" + i);
        user.setOrganization("organization" + i);
        user.setCountry("country" + i);
        user.setCity("city" + i);
        user.setStreet("street" + i);
        user.setPostalCode("postal" + i);
        user.setBuilding("building" + i);
        user.setUserType(getRandomUserType(i));
        userService.addUser(user);
    }

    private static UserType getRandomUserType(int i) {
        if (i > 0 && i < 3) {
            return UserType.NEW_USER;
        } else if (i > 3 && i < 7) {
            return UserType.REGULAR_USER;
        } else {
            return UserType.PREMIUM_USER;
        }
    }
}
