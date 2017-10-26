public class BruteCollinearPoints {
    public BruteCollinearPoints(Point[] points) {
        for(int i = 0; i < points.length; i++) {
            for(int j = i+1; j < points.length; j++) {
                for(int k = j+1; k < points.length; k++) {
                    for(int l = k+1; l < points.length; l++) {
                        Point p = points[i];
                        Point q = points[j];
                        Point r = points[k];
                        Point s = points[l];

                        Double pSlopeToq = p.slopeTo(q);
                        Double pSlopeTor = p.slopeTo(r);
                        Double pSlopeTos = p.slopeTo(s);

                        if (pSlopeToq == pSlopeTor && pSlopeTor == pSlopeTos) {

                        }
                    }
                }
            }
        }
    }
 }