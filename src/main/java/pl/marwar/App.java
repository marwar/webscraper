package pl.marwar;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Document doc;
        try {
            doc = SSLHelper.getConnection("https://superfund.pl/notowania").userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36").get();

//                    Jsoup
//                    .connect("https://superfund.pl/notowania")
//                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36")
//                    .header("Accept-Language", "*")
//                    .get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Elements csvButtons = doc.select("a.btn")
                .stream()
                .filter(button -> button.attr("href").contains("notowania"))
                .collect(Collectors.toCollection(Elements::new));

        System.out.println(csvButtons);

//        for (Element button: csvButtons
//             ) {
//
//
//        }
        Connection.Response sfgfuturepln;
        try {
            sfgfuturepln =
                    SSLHelper.getConnection("https://superfund.pl/notowania?ticker=SFGFUTUREPLN&amp;csv=true")
                            .userAgent(
                                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36")
                            .execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        System.out.println(sfgfuturepln.charset("CP1250").body());

        System.out.println("liczba linków: "+csvButtons.size());
    }
}
