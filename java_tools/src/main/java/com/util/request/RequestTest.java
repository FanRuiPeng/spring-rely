package com.util.request;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

import static java.lang.System.*;

/**
 * Created by BMF on 2018/1/26.
 */
public class RequestTest {

    public static void main(String[] args) {
        try {
//            URL url = new URI("http", "www.tsingteng.com", "/sv1/launch/launch", null).toURL();
//            URL url = new URL("http://www.tsingteng.com/sv1/launch/launch");
//            URL url = new URL("http://www.tsingteng.com/sv1/auth/authParam");
            URL url = new URL("https://test123w.oss-cn-qingdao.aliyuncs.com/multipart.data?uploads");
//            showInfo(url);

//            readFromUrl(url);

//            connectUrl(url);

//            urlConnectionReader(url);

            HashMap<String, String> stringStringHashMap = new HashMap<>(1);
            stringStringHashMap.put("type", "wb");
            reverse(url, stringStringHashMap);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void reverse(URL url, HashMap<String, String> param) {
        try {
            Calendar cd = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("EEE d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
            sdf.setTimeZone(TimeZone.getTimeZone("GMT")); // 设置时区为GMT
            String str = sdf.format(cd.getTime());
            System.out.println(str);

//            Stream.of(param)

            URLConnection urlConnection = url.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
            httpURLConnection.setRequestProperty("Accept-Charset", StandardCharsets.UTF_8.name());
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpURLConnection.setRequestProperty("connection", "Keep-Alive");
            httpURLConnection.setRequestProperty("Authorization", "OSS LTAIELdnUQGZOXRB:s90Q2D7WcmoPDQ/dYVv7oNykkWo=");
            httpURLConnection.setRequestProperty("Date", str);
//            httpURLConnection.setRequestProperty("Content-Length", "" + encodedStr.length());
            httpURLConnection.setConnectTimeout(3000);
            httpURLConnection.setUseCaches(true);
            httpURLConnection.setRequestMethod("POST");

            httpURLConnection.setDoOutput(true);

            OutputStreamWriter streamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream());
            streamWriter.write("type=wb");
            streamWriter.flush();
            streamWriter.close();

            int responseCode = httpURLConnection.getResponseCode();
            out.println(responseCode);
            if (responseCode == 200) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                bufferedReader.lines().forEach(out::println);
                bufferedReader.close();
            } else {
                String responseMessage = httpURLConnection.getResponseMessage();
                System.out.println(responseMessage);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getErrorStream()));
                bufferedReader.lines().forEach(out::println);
                bufferedReader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
