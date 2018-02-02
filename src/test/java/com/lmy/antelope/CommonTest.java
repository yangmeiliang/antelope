package com.lmy.antelope;

import com.alibaba.fastjson.JSON;
import com.lmy.antelope.entity.GoodsType;
import com.lmy.antelope.entity.TableData;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.apache.tomcat.util.http.fileupload.ProgressListener;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author yangmeiliang
 * @date 2018/1/22
 */
@Slf4j
public class CommonTest {

    @Test
    public void test01() {

        String[] strings = {"a1", null, "a3", "c1", "c3", "d1", "d2", "d3"};
        boolean flag = Arrays.stream(strings).anyMatch(StringUtils::isEmpty);
        System.out.println(flag);
    }

    @Test
    public void test02() {

        List<DemoEntity> list = Arrays.asList(
                new DemoEntity(379, "Bosch", "2017-08"),
                new DemoEntity(1561, "Bosch", "2017-09"),
                new DemoEntity(9202, "Siemens", "2017-08"),
                new DemoEntity(2278, "Siemens", "2017-09")
        );

        //按brand和month分组 key自己定义
        Map<String, Integer> map = list.stream()
                .collect(Collectors.toMap(o -> o.getBrand() + "||" + o.getMonth(), DemoEntity::getCount));

        list.forEach(entity -> {
            //获取上月份的数量
            Integer count = map.get(entity.getBrand() + "||" + getPreMonth(entity.getMonth()));
            Optional.ofNullable(count)
                    .map(o -> BigDecimal.valueOf(entity.getCount() - count)
                            .multiply(BigDecimal.valueOf(100))
                            .divide(BigDecimal.valueOf(count), 2, BigDecimal.ROUND_HALF_UP)
                            .doubleValue())
                    .ifPresent(entity::setPercent);
        });

        //筛选打印某一月份
        list.stream()
                .filter(entity -> Objects.equals(entity.getMonth(), "2017-09"))
                .map(JSON::toJSONString)
                .forEach(System.out::println);
    }

    /**
     * 获取指定月份的上一月日期
     */
    private String getPreMonth(String month) {
//        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
//            return sdf.format(DateUtils.addMonths(sdf.parse(month), -1));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return null;
    }

    @Test
    public void test03() throws Exception {
        List<Future<String>> results = new ArrayList<Future<String>>();
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            results.add(es.submit(() -> {
                String tid = String.valueOf(Thread.currentThread().getId());
                System.out.printf("Thread#%s : in call\n", tid);
                return "Thread-" + tid + " end call";
            }));
        }
        for (Future<String> res : results) {
            System.out.println(res.get());
        }
    }

    @Test
    public void test04() throws Exception {
        Class clazz = Class.forName("com.lmy.antelope.entity.TableData");
        Method setIndex = clazz.getMethod("setIndex", Integer.class);
        Method setType = clazz.getMethod("setType", String.class);
        Method setLevel = clazz.getMethod("setLevel", String.class);
        Method setMessage = clazz.getMethod("setMessage", String.class);

        List<Object[]> datas = getTableDatas();
        List<TableData> collect = datas.stream().map(data -> {
            try {
                TableData tableData = (TableData) clazz.newInstance();
                setIndex.invoke(tableData, data[0]);
                setType.invoke(tableData, data[1]);
                setLevel.invoke(tableData, data[2]);
                setMessage.invoke(tableData, data[3]);
                return tableData;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }).collect(Collectors.toList());

        collect.stream().map(JSON::toJSONString).forEach(System.out::println);
    }

    /**
     * 模拟造10条数据
     */
    private List<Object[]> getTableDatas() {
        return IntStream.range(0, 10)
                .mapToObj(i -> new Object[]{i, "type" + i, "level" + i, "message" + i})
                .collect(Collectors.toList());
    }

    @Test
    public void test05() {
        //模拟创建数据
        List<GoodsType> list = Arrays.asList(
                new GoodsType(0, "typeName0", null),
                new GoodsType(1, "typeName1", 0),
                new GoodsType(2, "typeName2", 1),
                new GoodsType(3, "typeName3", 2),
                new GoodsType(4, "typeName4", 3),
                new GoodsType(5, "typeName5", 4)
        );

        //根据父节点id分组
        Map<Integer, List<GoodsType>> map = list.stream()
                .filter(o -> Objects.nonNull(o.getTypeParent()))
                .collect(Collectors.groupingBy(GoodsType::getTypeParent));
        //循环处理子节点 构建树状结构
        list.forEach(goodsType -> {
            if (map.containsKey(goodsType.getTypeId())) {
                goodsType.setSubGoods(map.get(goodsType.getTypeId()));
            }
        });

        //获取指定节点的对象
        GoodsType result = list.stream().filter(goodsType -> goodsType.getTypeId() == 0).findFirst().orElse(null);
        System.out.println(JSON.toJSONString(result, true));
    }

    @Test
    public void test06() {
        ProgressListener progressListener = new ProgressListener() {
            @Override
            public void update(long l, long l1, int i) {

            }
        };

        FileUpload fileUpload = new FileUpload();

        fileUpload.setProgressListener(progressListener);
        FileItemFactory fileItemFactory = new DiskFileItemFactory();
    }




}
