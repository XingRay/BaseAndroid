package com.ray.lib.android.util;

/**
 * Author      : leixing
 * Date        : 2017-04-26
 * Email       : leixing1012@gmail.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class GeoUtils {
    private static final double PI = 3.14159265358979324;

    //
    // Krasovsky 1940
    //
    // a = 6378245.0, 1/f = 298.3
    // b = a * (1 - f)
    // ee = (a^2 - b^2) / a^2;
    private static final double A = 6378245.0;
    private static final double EE = 0.00669342162296594323;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * @param lng1 经度
     * @param lat1 纬度
     * @param lng2 经度
     * @param lat2 纬度
     * @param gs   坐标系
     * @return 两点距离 单位 米
     */
    public static int distanceOfTwoPoints(double lng1, double lat1, double lng2, double lat2, GaussSphere gs) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * (gs == GaussSphere.WGS84 ? 6378137.0 : (gs == GaussSphere.Xian80 ? 6378140.0 : 6378245.0));
        s = Math.round(s * 10000) / (10000);

        return (int) s;
    }

    /**
     * 84到02转换
     *
     * @param point 84坐标系的点
     * @return 02坐标系的点
     */
    public static MapPoint wgs84ToGcj02(MapPoint point) {
        return wgs84ToGcj02(point.getLatitude(), point.getLongitude());
    }

    public static MapPoint gcj02ToBd09ll(double gcj_lat, double gcj_lon) {
        double x = gcj_lon, y = gcj_lat;
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * PI);
        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * PI);
        double bd_lon = z * Math.cos(theta) + 0.0065;
        double bd_lat = z * Math.sin(theta) + 0.006;
        return new MapPoint(bd_lat, bd_lon);
    }
    //
    // World Geodetic System ==> Mars Geodetic System

    public static MapPoint wgs84ToGcj02(double wgLat, double wgLon) {
        if (outOfChina(wgLat, wgLon)) {
            return new MapPoint(wgLat, wgLon);
        }

        double dLat = transformLat(wgLon - 105.0, wgLat - 35.0);
        double dLon = transformLon(wgLon - 105.0, wgLat - 35.0);

        double radLat = wgLat / 180.0 * PI;
        double magic = Math.sin(radLat);
        magic = 1 - EE * magic * magic;
        double sqrtMagic = Math.sqrt(magic);
        dLat = (dLat * 180.0) / ((A * (1 - EE)) / (magic * sqrtMagic) * PI);
        dLon = (dLon * 180.0) / (A / sqrtMagic * Math.cos(radLat) * PI);
        return new MapPoint(wgLat + dLat, wgLon + dLon);
    }

    /**
     * 02到84转换
     *
     * @param point 02坐标点
     * @return 84坐标点
     */
    public static MapPoint gcj02ToWgs84(MapPoint point) {
        double gcjLat = point.getLatitude();
        double gcjLon = point.getLongitude();

        return gcj02ToWgs84(gcjLat, gcjLon);
    }

    /**
     * 02到84转换
     */
    public static MapPoint gcj02ToWgs84(double gcjLat, double gcjLon) {
        MapPoint point02 = wgs84ToGcj02(gcjLat, gcjLon);

        double dLat = point02.getLatitude() - gcjLat;
        double dLon = point02.getLongitude() - gcjLon;

        return new MapPoint(gcjLat - dLat, gcjLon - dLon);
    }

    private static boolean outOfChina(double lat, double lon) {
        if (lon < 72.004 || lon > 137.8347)
            return true;
        return lat < 0.8293 || lat > 55.8271;
    }

    private static double transformLat(double x, double y) {
        double ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y
                + 0.2 * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * PI) + 20.0 * Math.sin(2.0 * x * PI)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(y * PI) + 40.0 * Math.sin(y / 3.0 * PI)) * 2.0 / 3.0;
        ret += (160.0 * Math.sin(y / 12.0 * PI) + 320 * Math.sin(y * PI / 30.0)) * 2.0 / 3.0;
        return ret;
    }

    private static double transformLon(double x, double y) {
        double ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1
                * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * PI) + 20.0 * Math.sin(2.0 * x * PI)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(x * PI) + 40.0 * Math.sin(x / 3.0 * PI)) * 2.0 / 3.0;
        ret += (150.0 * Math.sin(x / 12.0 * PI) + 300.0 * Math.sin(x / 30.0
                * PI)) * 2.0 / 3.0;
        return ret;
    }

    public enum GaussSphere {
        Beijing54, Xian80, WGS84,
    }

    public static class MapPoint {
        private double latitude;
        private double longitude;

        public MapPoint(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        @Override
        public String toString() {
            return "MapPoint{" +
                    "latitude=" + latitude +
                    ", longitude=" + longitude +
                    '}';
        }
    }
}
