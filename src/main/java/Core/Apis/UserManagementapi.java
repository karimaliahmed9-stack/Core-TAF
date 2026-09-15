package Core.Apis;

import Core.Assertions.HardAssert;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserManagementapi {

    //Endpoints
    private static final String CREATEACCOUNT_ENDPOINT = "/createAccount";
    private static final String DeleteAccount_ENDPOINT = "/deleteAccount";
    RequestSpecification requestSpecification;
    Response response;
    HardAssert hardAssert;

    //Constructor
    public UserManagementapi() {
        this.requestSpecification = RestAssured.given();
        hardAssert = new HardAssert();

    }

    //Api Method
    //name, email, password, title (for example: Mr, Mrs, Miss), birth_date, birth_month, birth_year, firstname,
    // lastname, company, address1, address2, country, zipcode, state, city, mobile_number
//    public UserManagementapi CreateUserAccount(String name, String email, String pass, String title, String birthdate,
//                                               String birthmonth, String birthyear, String Firstname, String LastName, String company, String address1, String address2,
//                                               String Country, String zipcode, String state, String city, String mobile) {
//        Map<String, String> formparam = new HashMap<>();
//        formparam.put("name", name);
//        formparam.put("email", email);
//        formparam.put("password", pass);
//        formparam.put("title", title);
//        formparam.put("birth_date", birthdate);
//        formparam.put("birth_month", birthmonth);
//        formparam.put("birth_year", birthyear);
//        formparam.put("firstname", Firstname);
//        formparam.put("lastname", LastName);
//        formparam.put("company", company);
//        formparam.put("address1", address1);
//        formparam.put("address2", address2);
//        formparam.put("country", Country);
//        formparam.put("zipcode", zipcode);
//        formparam.put("state", state);
//        formparam.put("city", city);
//        formparam.put("mobile_number", mobile);
//
//        response = requestSpecification.spec(Buildre.GetUserManagementSpecification(formparam))
//                .post(CREATEACCOUNT_ENDPOINT);
//        LogManager.Info("Create User Account API Response: " + response.asPrettyString());
//        return this;
//
//    }
//
//    public UserManagementapi DeleteUserAccount(String email, String password) {
//        Map<String, String> formparam = new HashMap<>();
//        formparam.put("email", email);
//        formparam.put("password", password);
//
//        response = requestSpecification.spec(Buildre.GetUserManagementSpecification(formparam))
//                .delete(DeleteAccount_ENDPOINT);
//        LogManager.Info("Delete User Account API Response: " + response.asPrettyString());
//        return this;
//
//    }
//
//
//    //Validations For User
//    @Step("Validate User Account Creation Response")
//    public UserManagementapi ValidateUserCreatedSuccessfully() {
//
//        String actualMessage = response.jsonPath().getString("message");
//
//        LogManager.Info("Actual Message = [" + actualMessage + "]");
//        LogManager.Info("Expected Message = [User created!]");
//        hardAssert.Equals(response.jsonPath().getString("message"), "User created!",
//                "User is not created ");
//        return this;
//    }
//
//    @Step("Validate User Account Deletion Response")
//    public UserManagementapi ValidateUserDeletedSuccessfully() {
//        hardAssert.Equals(response.jsonPath().get("message"), "This request method is not supported."
//                , "User is not deleted successfully");
//        return this;
//    }
//
//
}
