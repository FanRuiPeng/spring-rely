package com.bmf.test;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.ServiceSignature;
import com.aliyun.oss.common.comm.RequestMessage;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.internal.OSSRequestSigner;
import com.aliyun.oss.internal.SignUtils;
import com.aliyun.oss.model.InitiateMultipartUploadRequest;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PolicyConditions;
import com.bmf.tools.Man;
import com.bmf.tools.SubMan;
import com.bmf.tools.Week;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class JunitTest {

    @Test
    public void test() {
//        List<String>[] lists = new List[1];
//        List<Integer> integers = Arrays.asList(12);
//        int a = 1;
//        integers.get(--a);
//        System.out.println(a);
//        Object[] objects = lists;
//        objects[0] = integers;
//        String s = lists[0].get(0);
//        System.out.println(s);

//        Man[] men = new Man[2];
//        men[0] = new Man();
//        men[1] = new SubMan();
//        Stream.of(men).parallel().forEach(System.out::println);
//        for(Man m: men) {
//            System.out.println(m);
//        }

        Runnable runnable = () -> Week.values();
        Executors.newSingleThreadExecutor().submit(runnable);
        System.out.println(Week.Fri.ordinal());

//        Text text = new Text();
//        text.applyStyles(1);
        EnumSet es = EnumSet.allOf(Text.class);
        boolean contains = es.contains(Text.Style_1);
        System.out.println(contains);

        EnumMap<Text, Object> stringObjectEnumMap = new EnumMap<Text, Object>(Text.class);
        boolean b = stringObjectEnumMap.containsKey(Text.Style_1);
//        stringObjectEnumMap.put()
        System.out.println(b);


    }

    @Test
    public void ResourceTest() throws IOException, EncoderException, ClassNotFoundException, IllegalAccessException, InstantiationException {

        String endpoint = "oss-cn-qingdao.aliyuncs.com";
        String accessId = "LTAIELdnUQGZOXRB";
        String accessKey = "eyrq6k2S5gU12CabOHejd2uSnzs8YX";
        String bucket = "test123w";
        String dir = "/multipart.data?uploads";
        String host = "https://" + bucket + "." + endpoint;
        OSSClient client = new OSSClient(endpoint, accessId, accessKey);
//        long expireTime = 30;
//        long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
//        Date expiration = new Date(expireEndTime);
//        PolicyConditions policyConds = new PolicyConditions();
//        policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
//        policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);
//
//        String postPolicy = client.generatePostPolicy(expiration, policyConds);
//        byte[] binaryData = postPolicy.getBytes("utf-8");
//        String encodedPolicy = BinaryUtil.toBase64String(binaryData);
//        String postSignature = client.calculatePostSignature(postPolicy);
//
//        Map<String, String> respMap = new LinkedHashMap<String, String>();
//        respMap.put("accessid", accessId);
//        respMap.put("policy", encodedPolicy);
//        respMap.put("signature", postSignature);
//        //respMap.put("expire", formatISO8601Date(expiration));
//        respMap.put("dir", dir);
//        respMap.put("host", host);
//        respMap.put("expire", String.valueOf(expireEndTime / 1000));
//        JSONObject ja1 = JSONObject.fromObject(respMap);
//        System.out.println(ja1.toString());

        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("Content-type", "application/x-www-form-urlencoded");
        Calendar cd = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT")); // 设置时区为GMT
        String str = sdf.format(cd.getTime());
        stringStringHashMap.put("Date", str);
        stringStringHashMap.put("x-oss-date", str);
        RequestMessage requestMessage = new RequestMessage(new InitiateMultipartUploadRequest(bucket, dir));
        requestMessage.setHeaders(stringStringHashMap);
        String canonicalString = SignUtils.buildCanonicalString("POST", "/" + bucket + dir, requestMessage, null);
        String signature = ServiceSignature.create().computeSignature(accessKey, canonicalString);
        System.out.println(canonicalString);
        System.out.println(signature);

//        UrlResource urlResource = new UrlResource(new URL("http://zhuangdan.oss-cn-beijing.aliyuncs.com/video/shilian33tian.mp4"));
//        File file = urlResource.getFile();
//        if(file.exists()) {
//            MultimediaInfo info = new Encoder().getInfo(file);
//            System.out.println(info.toString());
//        }
//        Class<?> aClass = this.getClass().getClassLoader().loadClass("D:\\IDEAworkspace\\main\\build\\classes\\java\\main\\com\\code_space\\support\\im\\rong\\util\\ArraySplit.class");
//        Object o = aClass.newInstance();
    }


    public static Set union(Set s1, Set s2) {
        Set result = new HashSet(s1);
        result.addAll(s2);
        return result;
    }
}
