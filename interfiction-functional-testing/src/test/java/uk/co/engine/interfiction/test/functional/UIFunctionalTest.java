package uk.co.engine.interfiction.test.functional;

import com.github.dockerjava.api.model.AccessMode;
import com.github.dockerjava.api.model.Bind;
import com.github.dockerjava.api.model.Volume;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class UIFunctionalTest {

    @LocalServerPort
    private int port;

    @SuppressWarnings("rawtypes")
    @Container
    private final BrowserWebDriverContainer chrome = (BrowserWebDriverContainer) new BrowserWebDriverContainer()
            .withCapabilities(new ChromeOptions())
            .withSharedMemorySize(2147483648L); // https://github.com/testcontainers/testcontainers-java/issues/2552 (windows WSL2)

    @BeforeEach
    void setup() {
        // Temp: See bug above
        if (chrome.getShmSize() == null) {
            chrome.getBinds().add(new Bind("/dev/shm", new Volume("/dev/shm"), AccessMode.rw));
        }
    }

    protected RemoteWebDriver driver() {
        return this.chrome.getWebDriver();
    }

    protected void toPage(final String route) {
        // depending on your operation system try 172.17.0.1 as IP or container.getTestHostIpAddress()
        driver().get("http://host.docker.internal:" + port + route);
    }

    protected void input(final String selectorId, final CharSequence... text) {
        waitForVisibilityById(selectorId);
        final WebElement inputField = driver().findElementById(selectorId);
        inputField.sendKeys(text);
    }

    protected void waitForTitle(final String title) {
        WebDriverWait wait = new WebDriverWait(driver(), 45);
        wait.until(ExpectedConditions.titleIs(title));
    }

    protected void waitForVisibilityById(final String id) {
        WebDriverWait wait = new WebDriverWait(driver(), 5);
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id(id)));
    }


}
