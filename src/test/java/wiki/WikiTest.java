package wiki;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import core.BaseTest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WikiTest extends BaseTest {
    private final static String URL = "https://ru.wikipedia.org/wiki/Java";

    @Test
    public void openAllHrefs() {
        Selenide.open(URL);
        ElementsCollection hrefs = Selenide.$$x("//div[@id='toc']//a[@href]");
        List<String> links = new ArrayList<>();
        
        /** 
         *     1 method:
         * for (int i = 0; i<hrefs.size(); i++) {
         *     links.add(hrefs.get(i).getAttribute("href"));
         * }
         *     2 method:
         * for(SelenideElement element : hrefs) {
         *     links.add(element.getAttribute("href"));
         * }
         *     3 method:
         */
        
        hrefs.forEach(x->links.add(x.getAttribute("href")));

        /** 
         *    1 способ: открытиe всех полученных ссылок с помощью stream.api
         * links.forEach(Selenide::open);
         *
         *    2 способ: открытие всех ссылок и выполнение каких-либо assert'ов
         * for (int i = 0; i<links.size(); i++) {
         *    String listUrl = links.get(i);
         *    Selenide.open(listUrl);
         *    String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
         *    Assert.assertEquals(currentUrl,listUrl);
         * }
         *
         *    3 способ: в рандомном порядке открыть все ссылки
         * Random random = new Random();
         * while (links.size() > 0) {
         *    int randomNumber = random.nextInt(links.size());
         *    Selenide.open(links.get(randomNumber));
         *    links.remove(WebDriverRunner.getWebDriver().getCurrentUrl());
         * }
         */

        List<Integer> linkLength = hrefs.stream().map(x->x.getAttribute("href").length()).collect(Collectors.toList());
    }
}
