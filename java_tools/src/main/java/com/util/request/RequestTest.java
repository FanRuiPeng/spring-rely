package com.util.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import static java.lang.System.out;

/**
 * Created by BMF on 2018/1/26.
 */
public class RequestTest {

    public static void main(String[] args) {
        try {
//            URL url = new URI("http", "www.tsingteng.com", "/sv1/launch/launch", null).toURL();
//            URL url = new URL("http://www.tsingteng.com/sv1/launch/launch");
            URL url = new URL("https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=6_yAl2-g-cl-w4g_7PWW-tc9n26lGsfUUNEe-lHFSYSpzU8orWtPOqNDJ6LicjbKNijoAyxmoEu6Wcif4PL04fPQpsMNMmgLiqTAD5c_mxFnv1EkYNGTQU7GRDtzXuSCKCljMuCb9EKvyQ6TpJNEVeACAWNE");
//            URL url = new URL("https://test123w.oss-cn-qingdao.aliyuncs.com/multipart.data?uploads");
//            showInfo(url);

//            readFromUrl(url);

//            connectUrl(url);

//            urlConnectionReader(url);

            reverse(url,
                    "{\"user_list\":[{\"openid\":\"onAOPw-3t8xBlqhcpKPn5SmsVyic\",\"lang\":\"zh_CN\"},{\"openid\":\"onAOPwwbTSJ4I8_UzjilMEpHNHLI\",\"lang\":\"zh_CN\"},{\"openid\":\"onAOPw1Ws8auDYRPyKP3ceG3SjO8\",\"lang\":\"zh_CN\"},{\"openid\":\"onAOPw0Ivj7tS34AvzWquIMwwwrs\",\"lang\":\"zh_CN\"},{\"openid\":\"onAOPw50bM77MgiJeMPm3JPG8hA8\",\"lang\":\"zh_CN\"},{\"openid\":\"onAOPw3AhGryO8NmaNUXxHlPM8is\",\"lang\":\"zh_CN\"},{\"openid\":\"onAOPw8j9xkCUt5KH1IqIaq-9Yw8\",\"lang\":\"zh_CN\"},{\"openid\":\"onAOPw622lyIzNwP0Ktmnr3Z6kdU\",\"lang\":\"zh_CN\"},{\"openid\":\"onAOPw6nlPrzBsxp8E0QxExm7jNw\",\"lang\":\"zh_CN\"},{\"openid\":\"onAOPw5OauKnnaCrGyba7KYEYjoo\",\"lang\":\"zh_CN\"},{\"openid\":\"onAOPwzboRYwkbmfpWUHee_cVlWY\",\"lang\":\"zh_CN\"},{\"openid\":\"onAOPw9o_8-AvUq8CvZ4Vnuz4JOU\",\"lang\":\"zh_CN\"}]}");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void reverse(URL url, String param) {
        try {
            String str = getGMTTime();
            
            URLConnection urlConnection = url.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
            httpURLConnection.setRequestProperty("Accept-Charset", StandardCharsets.UTF_8.name());
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpURLConnection.setRequestProperty("connection", "Keep-Alive");
//            httpURLConnection.setRequestProperty("Authorization", "OSS LTAIELdnUQGZOXRB:s90Q2D7WcmoPDQ/dYVv7oNykkWo=");
            httpURLConnection.setRequestProperty("Date", str);
//            httpURLConnection.setRequestProperty("x-oss-date", str);
//            httpURLConnection.setRequestProperty("Content-Length", "" + encodedStr.length());
            httpURLConnection.setConnectTimeout(3000);
            httpURLConnection.setUseCaches(true);
            httpURLConnection.setRequestMethod("POST");

            httpURLConnection.setDoOutput(true);

            /*************************设置请求参数******************************/
            OutputStreamWriter streamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream());
            streamWriter.write(param);
            streamWriter.flush();
            streamWriter.close();

            /*************************获取响应状态码*****************************/
            int responseCode = httpURLConnection.getResponseCode();
            out.println("response code : " + responseCode);
            String responseMessage = httpURLConnection.getResponseMessage();
            out.println("response message : " + responseMessage);
            if (responseCode == 200) {
                /*************************获取响应内容*****************************/
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                out.print("response text : ");
                bufferedReader.lines().forEach(out::println);
                bufferedReader.close();
            } else {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getErrorStream()));
                out.print("response error text : ");
                bufferedReader.lines().forEach(out::println);
                bufferedReader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getGMTTime() {
        Calendar cd = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT")); // 设置时区为GMT
        String str = sdf.format(cd.getTime());
        System.out.println(str);
        return str;
    }

    private static void urlConnectionReader(URL url) {
        URLConnection urlConnection = null;
        BufferedReader in = null;
        try {
            urlConnection = url.openConnection();
            in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
//            String inputLine;
//            while ((inputLine = in.readLine()) != null) {
//                out.println(inputLine);
//            }
            in.lines().forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void connectUrl(URL url) {
        try {
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readFromUrl(URL url) throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));) {
//                String inputLine;
//                while ((inputLine = in.readLine()) != null) {
//                    out.println(inputLine);
//                }
            in.lines().forEach(out::println);
        }
    }

    private static void showInfo(URL url) {
        String protocol = url.getProtocol();
        out.println("protocol " + protocol);
        String authority = url.getAuthority();
        out.println("authority " + authority);
        String host = url.getHost();
        out.println("host " + host);
        int port = url.getPort();
        out.println("port " + port);
        String path = url.getPath();
        out.println("path " + path);
        String query = url.getQuery();
        out.println("query " + query);
        String file = url.getFile();
        out.println("file " + file);
        String ref = url.getRef();
        out.println("ref " + ref);
    }
}
