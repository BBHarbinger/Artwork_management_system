package src;

import src.data.Artwork;
import src.data.Data;
import src.main.SystemController;

public class SelfTest {
    public static void main(String[] args) {
        Data data = new Data();
        data.load("src/data/dataset.csv");
        SystemController controller = new SystemController();
        for (Artwork artwork:data.getArtworkList()) {
            controller.printPic(artwork.getTitle(), artwork.getAuthor());
        }

    }
}
