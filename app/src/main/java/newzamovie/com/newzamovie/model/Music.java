package newzamovie.com.newzamovie.model;

import java.util.List;

/**
 * Created by root1 on 7/10/16.
 */
public class Music {


    /**
     * status : ??
     * loadpleng : [{"title":"อย่าร้อนตัว - เอ็ม อรรถพล","urlmp3":"http://bit.ly/29C0MSd","urlcover":"http://bit.ly/29uBnYr"},{"title":"คนไม่จำเป็น- Getsunova","urlmp3":"http://bit.ly/29nZlWO","urlcover":"http://bit.ly/29oE1fr"},{"title":"ทางของฝุ่น-atom","urlmp3":"http://bit.ly/29Dj5Dm","urlcover":"http://bit.ly/29FNO31"},{"title":"อันเฟรนด์ Unfriend Helmetheads","urlmp3":"http://bit.ly/29FNZv9","urlcover":"http://bit.ly/29FNn8W"},{"title":"ไม่เป็นไร - THE MOUSSES","urlmp3":"http://bit.ly/29ojsUJ","urlcover":"http://bit.ly/29ojLyL"},{"title":"กลับตัวกลับใจ- Dax","urlmp3":"http://bit.ly/29vq94u","urlcover":"http://bit.ly/29tAg8S"},{"title":"กราบขอร้อง - โทนี่ ผี","urlmp3":"http://bit.ly/29uNqov","urlcover":"http://bit.ly/29pt1Pr"},{"title":"คนมีเสน่ห์ - ป้าง นครินทร์","urlmp3":"http://bit.ly/29XCK09","urlcover":"http://bit.ly/29FPz0c"}]
     */

    private String status;
    /**
     * title : อย่าร้อนตัว - เอ็ม อรรถพล
     * urlmp3 : http://bit.ly/29C0MSd
     * urlcover : http://bit.ly/29uBnYr
     */

    private List<LoadplengEntity> loadpleng;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<LoadplengEntity> getLoadpleng() {
        return loadpleng;
    }

    public void setLoadpleng(List<LoadplengEntity> loadpleng) {
        this.loadpleng = loadpleng;
    }

    public static class LoadplengEntity {
        private String title;
        private String urlmp3;
        private String urlcover;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrlmp3() {
            return urlmp3;
        }

        public void setUrlmp3(String urlmp3) {
            this.urlmp3 = urlmp3;
        }

        public String getUrlcover() {
            return urlcover;
        }

        public void setUrlcover(String urlcover) {
            this.urlcover = urlcover;
        }
    }
}
