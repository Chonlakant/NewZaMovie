package newzamovie.com.newzamovie.model;

import java.util.List;

/**
 * Created by root1 on 7/10/16.
 */
public class Cartoon {

    /**
     * status : ??
     * Cartoon : [{"title":"One Piece วันพีช ตอนที่ 748","detail":"One Piece วันพีช ตอนที่ 748","urlvdo":"http://bit.ly/29XxxW0","urlcover":"http://bit.ly/29CCQwA","status":"1"},{"title":"One Piece วันพีช ตอนที่ 747","detail":"One Piece วันพีช ตอนที่ 747","urlvdo":"http://bit.ly/29vmq7e","urlcover":"http://bit.ly/29CCQwA","status":"1"},{"title":"One Piece วันพีช ตอนที่ 746","detail":"One Piece วันพีช ตอนที่ 746","urlvdo":"http://bit.ly/29E5ChL","urlcover":"http://bit.ly/29CCQwA","status":"1"},{"title":"One Piece วันพีช ตอนที่ 745","detail":"One Piece วันพีช ตอนที่ 745","urlvdo":"http://bit.ly/29pqnJs","urlcover":"http://bit.ly/29CCQwA","status":"1"}]
     */

    private String status;
    /**
     * title : One Piece วันพีช ตอนที่ 748
     * detail : One Piece วันพีช ตอนที่ 748
     * urlvdo : http://bit.ly/29XxxW0
     * urlcover : http://bit.ly/29CCQwA
     * status : 1
     */

    private List<CartoonEntity> Cartoon;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CartoonEntity> getCartoon() {
        return Cartoon;
    }

    public void setCartoon(List<CartoonEntity> Cartoon) {
        this.Cartoon = Cartoon;
    }

    public static class CartoonEntity {
        private String title;
        private String detail;
        private String urlvdo;
        private String urlcover;
        private String status;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getUrlvdo() {
            return urlvdo;
        }

        public void setUrlvdo(String urlvdo) {
            this.urlvdo = urlvdo;
        }

        public String getUrlcover() {
            return urlcover;
        }

        public void setUrlcover(String urlcover) {
            this.urlcover = urlcover;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
