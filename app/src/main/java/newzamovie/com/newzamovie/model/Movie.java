package newzamovie.com.newzamovie.model;

import java.util.List;

/**
 * Created by root1 on 7/7/16.
 */
public class Movie {

    /**
     * status : ??
     * Movie : [{"title":"WARCRAFT: THE BEGINNING (2016) วอร์คราฟต์: กำเนิดศึกสองพิภพ","detail":"Master/เสียงไทยโรง","urlvdo":"http://bit.ly/29FxQG5","urlcover":"http://bit.ly/29utfXK","status":"1"},{"title":"THE LEGEND OF TARZAN ตำนานแห่งทาร์ซาน","detail":"เสียงไทยโรง Zoom V.1","urlvdo":"http://bit.ly/29FyiEC","urlcover":"http://bit.ly/29qolaQ","status":"1"},{"title":"BATMAN V SUPERMAN DAWN OF JUSTICE แบทแมน ปะทะ ซูเปอร์แมน แสงอรุณแห่งยุติธรรม","detail":" เสียงไทย HD","urlvdo":"http://bit.ly/29peB25","urlcover":"http://bit.ly/29wwfUz","status":"1"},{"title":"INDEPENDENCE DAY 2: RESURGENCE (2016) ไอดี 4 สงครามใหม่วันบดโลก","detail":"เสียงไทยโรง","urlvdo":"http://bit.ly/29tLJKZ","urlcover":"http://bit.ly/29uvavA","status":"1"},{"title":"CENTRAL INTELLIGENCE (2016) คู่สืบคู่แสบ","detail":"เสียงไทยโรง","urlvdo":"http://bit.ly/29DS9Xg","urlcover":"http://bit.ly/29ykUCC","status":"1"},{"title":"THE HUNTSMAN WINTER\u0092S WAR พรานป่าและราชินีน้ำแข็ง","detail":"เสียงไทย HD","urlvdo":"http://bit.ly/29uvoTr","urlcover":"http://bit.ly/29oMXkL","status":"1"},{"title":"X-MEN: APOCALYPSE (2016) เอ็กซ์เม็น อะพอคคาลิปส์","detail":"เสียงไทยโรง ","urlvdo":"http://bit.ly/29JsfB5","urlcover":"http://bit.ly/29Jsw7g","status":"1"},{"title":"CAPTAIN AMERICA: CIVIL WAR กัปตัน อเมริกา ศึกฮีโร่ระห่ำโลก","detail":"เสียงไทยโรง","urlvdo":"http://bit.ly/29tMiEB","urlcover":"http://bit.ly/29qq7c2","status":"1"},{"title":"TEENAGE MUTANT NINJA TURTLES 2 OUT OF THE SHADOWS (2016) เต่านินจา ภาค 2 จากเงาสู่ฮีโร่","detail":"เสียงไทยโรง","urlvdo":"http://bit.ly/29CpL6m","urlcover":"http://bit.ly/29v9luv","status":"1"}]
     */

    private String status;
    /**
     * title : WARCRAFT: THE BEGINNING (2016) วอร์คราฟต์: กำเนิดศึกสองพิภพ
     * detail : Master/เสียงไทยโรง
     * urlvdo : http://bit.ly/29FxQG5
     * urlcover : http://bit.ly/29utfXK
     * status : 1
     */

    private List<MovieEntity> Movie;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<MovieEntity> getMovie() {
        return Movie;
    }

    public void setMovie(List<MovieEntity> Movie) {
        this.Movie = Movie;
    }

    public static class MovieEntity {
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
