package engine.utils;


import engine.skeleton.App;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseGlobal {
    public static Random rand = new Random();
    public static void genSeed() {
        long seed = rand.nextLong();
        rand.setSeed(seed);
        System.out.println(String.format("seed = %d",seed));
    }
    public static void setSeed(long seed) {
        rand.setSeed(seed);
        System.out.println(String.format("seed = %d",seed));
    }
    public static double randRange(double minVal, double maxVal) {
        return minVal + rand.nextDouble() * (maxVal - minVal);
    }
    public static int randInt(double minVal, double maxVal){return (int) randRange(minVal,maxVal+1);}
    public static double randGaussian(double std, double mean) {
        return rand.nextGaussian()*std + mean;
    }
    public static <T> Object randChoose(List<T> list) {
        return list.get((int) BaseGlobal.randRange(0,list.size()));
    }
    public static boolean fileExists(String fileName) {
        File f = new File(fileName);
        return f.exists();
    }
    public static ArrayList<String> importFile(String fileName){
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            ArrayList<String> lineArray = new ArrayList<String>();
            String st = br.readLine();
            while (st != null) {
                lineArray.add(st);
                st = br.readLine();
            }
            return lineArray;
        }
        catch(IOException e) {
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }
    public static boolean regexCheckFullMatch(Pattern pattern, String input) {
        return pattern.matcher(input).matches();
    }
    public static boolean regexCheckMatch(Pattern pattern, String input) {
        return pattern.matcher(input).find();
    }
    public static String regexFindFirst(Pattern pattern, String input) {
        Matcher matcher = pattern.matcher(input);
        if(matcher.find()) {
            return input.substring(matcher.start(),matcher.end());
        }
        else {
            return null;
        }
    }
    public static ArrayList<String> regexFindAll(Pattern pattern, String input) {
        ArrayList<String> matchArray = new ArrayList<>();
        Matcher matcher = pattern.matcher(input);
        while(matcher.find()) {
            matchArray.add(input.substring(matcher.start(),matcher.end()));
        }
        return matchArray;
    }
    public static int limitMinMax(int value, int minVal, int maxVal) {
        return Math.max(minVal,Math.min(value,maxVal));
    }
    public static double limitMinMax(double value, double minVal, double maxVal) {
        return Math.max(minVal,Math.min(value,maxVal));
    }
    public static double roundTo(double value, double interval) {
        return Math.round(value / interval) * interval;
    }
    public static double clearFloatingPointRoundingError(double value) {
        return Double.parseDouble(String.format("%.10f",value));
    }
    public static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2-x1,2)+Math.pow(y2-y1,2));
    }
    public static double normalizeDir(double dir) {
        while(dir>360) {dir -= 360;}
        while(dir<0) {dir += 360;}
        return dir;
    }
    public static double[] dirToCoor(double dir) {
        dir = BaseGlobal.normalizeDir(dir);
        double[] result = new double[2];
        result[0] = Math.cos(dir/180 * Math.PI); // x
        result[1] = -Math.sin(dir/180 * Math.PI); // x
        return result;
    }
    public static double coorToDir(double x1, double y1, double x2, double y2) {
        double diffX = x2 - x1;
        double diffY = (-y2) - (-y1);
        double value;
        if(diffX==0 && diffY>=0) {value = 90;}
        else if(diffX==0 && diffY<0) {value = 270;}
        else{
            value = Math.atan(diffY / diffX) / Math.PI * 180;
            if(diffX>=0 && diffY>=0) {value += 0;}
            else if(diffX<0 && diffY>=0) {value += 180;}
            else if(diffX<0 && diffY<0) {value += 180;}
            else if(diffX>=0 && diffY<0) {value += 360;}
            else {throw new UnsupportedOperationException("ERROR: impossible else statement reached");}
        }
        return value;
    }
    public static double[] rotateAroundPoint(double x, double y, double pointX, double pointY, double angle) {
        double startAngle = coorToDir(pointX,pointY,x,y);
        double dist = distance(pointX,pointY,x,y);
        angle = startAngle + angle;
        double[] result = dirToCoor(angle);
        result[0] = pointX + result[0] * dist;
        result[1] = pointY + result[1] * dist;
        return result;
    }
    public static double dirDist(double dir1, double dir2) {
        return Math.min(Math.abs(dir2 - dir1),Math.min(Math.abs(dir2 - (dir1 + 360)),Math.abs(dir2 - (dir1 - 360))));
    }
    public static double degToRad(double deg) {
        return deg/180 * Math.PI;
    }
    public static double radToDeg(double rad) {
        return rad/Math.PI * 180;
    }
    public static double secondsToTicks(double seconds) {return seconds * App.getInstance().getPanel().getMaxFPS();}
    public static double ticksToSeconds(double ticks) {return ticks / App.getInstance().getPanel().getMaxFPS();}
}
