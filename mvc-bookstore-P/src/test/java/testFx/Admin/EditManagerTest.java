package testFx.Admin;



import com.example.kthimi.Controller.MainController;
import com.example.kthimi.Controller.Mockers.MockAuthenticationModel;
import com.example.kthimi.Model.AdministratorModel;
import com.example.kthimi.Model.AuthenticationModel;
import com.example.kthimi.Model.ManagerModel;
import com.example.kthimi.View.MainView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EditManagerTest extends ApplicationTest {

    private MainController mainController;
    private MainView mainView;
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws IOException, InterruptedException, TimeoutException {
        this.primaryStage = primaryStage;
        mainView = new MainView();
        mainController = new MainController(mainView);

        mainView.setPrimaryStage(primaryStage);

        primaryStage.setScene(new Scene(mainView.mainPage(), 800, 600));
        primaryStage.setTitle("Bookstore Application");
        primaryStage.show();

    }

    @Test
    public void checkLibrariansTry() throws TimeoutException, InterruptedException, IOException {
        AdministratorModel.InstantiateManagers();


        WaitForAsyncUtils.waitFor(4, TimeUnit.SECONDS, () -> !lookup("#Submit").queryAll().isEmpty());

        String validManagerUsername = "3";
        String validManagerPassword = "33";

        AuthenticationModel mockModel = new MockAuthenticationModel();
        mainController.setAuthenticationModel(mockModel);



        clickOn(mainView.getUsername()).write(validManagerUsername);
        Thread.sleep(1000);
        clickOn(mainView.getPassword()).write(validManagerPassword);
        Thread.sleep(1000);
        clickOn("#Submit");

        WaitForAsyncUtils.waitForFxEvents();

        Thread.sleep(1000);

        FxRobot robot = new FxRobot();


        robot.clickOn((Button) lookup("#bttManageManagers").query());
        Thread.sleep(1000);

        robot.clickOn((Button) lookup("#button").query());
        Thread.sleep(1000);

        TextField salary = lookup("#salary").query();
        robot.interact(salary::clear);
        robot.clickOn(salary).write("1000");

        robot.clickOn((Button) lookup("#bttSubmit").query());
        Thread.sleep(1000);

        assertEquals("Success!",((TextField) lookup("#magLoginWarning").query()).getText());




    }



}
